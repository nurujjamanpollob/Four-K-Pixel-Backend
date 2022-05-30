/*
 *  Copyright (c) 2022 Nurujjaman Pollob, All right reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 *  If you have contributed in codebase,
 *  and want to add your name or copyright in a particular class or method,
 *  you must follow this following pattern:
 *  <code>
 *      // For a new method created by you,
 *      //like this example method with name fooMethod()
 *      //then use following format:
 *
 *     >>>
 *     @author $Name and $CurrentYear.
 *     $Documentation here.
 *     $Notes
 *     public boolean fooMethod(){}
 *     <<<
 *
 *     // For an existing method
 *
 *     >>>
 *     $Current Method Documentation(Update if needed)
 *
 *     Updated by $YourName
 *     $Update summery
 *     $Notes(If any)
 *     <<<
 *
 *     // For a new class of file, that is not created by anyone else
 *     >>>
 *     Copyright (c) $CurrentYear $Name, All right reserved.
 *
 *     $Copyright Text.
 *     $Notes(If Any)
 *     <<<
 *
 *     // For a existing class, if you want to add your own copyright for your work.
 *
 *     >>>
 *     $Current Copyright text
 *
 *     $YourCopyrightText
 *     <<<
 *
 *     Done! Clean code!!
 *  </code>
 */

package com.nurujjamanpollob.fourkcommonlib;

import com.nurujjamanpollob.fourkcommonlib.exception.InvalidUserException;
import com.nurujjamanpollob.fourkcommonlib.model.User;
import com.nurujjamanpollob.fourkcommonlib.validation.UserValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test Class to validate {@link com.nurujjamanpollob.fourkcommonlib.validation.UserValidation} functionality
 */
public class UserValidationTest {

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to test a username, which is valid to use
     * And should not throw any exception
     */
    @Test()
    public void testSimpleUsernameIsValid() {


        assertDoesNotThrow(() -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman",
                "Pollob",
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998, // Birth year
                12, // Birth month
                7, // Birth Day
                11111,
                false,
                true,
                false,
                true
        )).validateUser());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because it has capital latter
     */
    @Test
    public void testUsernameInvalidBecauseItHasCapitalLatter() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "Nurujjamanpollob",
                "password geos here",
                "Nurujjaman",
                "Pollob",
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998, // Birth year
                12, // Birth month
                7, // Birth Day
                11111,
                false,
                true,
                false,
                true
        )).validateUser());
    }

    /**
     * @author Nurujjaman Pollob
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because it has special character
     */
    @Test
    public void testUsernameInvalidBecauseItHasSpecialCharacter() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob$",
                "password geos here",
                "Nurujjaman",
                "Pollob",
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998, // Birth year
                12, // Birth month
                7, // Birth Day
                11111,
                false,
                true,
                false,
                true
        )).validateUser());
    }

    /**
     * @author Nurujjaman Pollob
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because username length is short
     */
    @Test
    public void TestUsernameInvalidBecauseItsLengthIsShort() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "np",
                "password geos here",
                "Nurujjaman",
                "Pollob",
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998, // Birth year
                12, // Birth month
                7, // Birth Day
                11111,
                false,
                true,
                false,
                true
        )).validateUser());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because username length is long
     */
    @Test
    public void testUsernameInvalidBecauseItsLengthIsLong() {

        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob01234567890",
                "password geos here",
                "Nurujjaman",
                "Pollob",
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998, // Birth year
                12, // Birth month
                7, // Birth Day
                11111,
                false,
                true,
                false,
                true
        )).validateUser());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to test a user age,
     * this test should not throw any exception
     * because the user age is more than thirteen year.
     */
    @Test
    public void testUserAgeIsValidBecauseHeIsAboveThirteenYearOld() {

        assertDoesNotThrow(() -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman",
                "Pollob",
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998, // Birth year
                12, // Birth month
                7, // Birth Day
                11111,
                false,
                true,
                false,
                true
        )).validateUser());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to test a user age,
     * this test should throw {@link InvalidUserException}
     * because the user age is younger than thirteen year
     */
    @Test
    public void testUserIsYounger() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman",
                "Pollob",
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                2010, // Birth year
                7, // Birth month
                7, // Birth Day
                11111,
                false,
                true,
                false,
                true
        )).validateUser());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name and last name is null
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseOfNull() {

        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                null, // User First Name
                null, // User Last Name
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name has special character
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseItHasInvalidSpecialCharacter() {

        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman$", // User First Name
                "Pollob", // User Last Name
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name length is short
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseItsLengthIsShort() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "NP", // User First Name
                "Pollob", // User Last Name
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user last name has a numeric character
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseItHasNumericCharacter() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob1", // User Last name
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name is too long
     */
    @Test
    public void testUserFirstNameIsInvalidBecauseItsTooLong() {

        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "NurujjamanNurujjam", // User First Name
                "Pollob", // User Last name
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should not throw any exception, as the naming conversion is apprear to be valid
     */
    @Test
    public void testUserFirstLastNameValid() {

        assertDoesNotThrow(() -> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One",
                "Address Line Two",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Address line one, which is contains unsupported character, should throw {@link InvalidUserException}
     */
    @Test
    public void testAddressLineOneInvalidBecauseItHasContainsInvalidCharacter(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One$", // Address line one
                "Address Line Two", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Address line one, which is short, should throw {@link InvalidUserException}
     */
    @Test
    public void testAddressLineOneInvalidBecauseItsShort(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Alo", // Address line one
                "Address Line Two", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Address line two, which is contains unsupported character, should throw {@link InvalidUserException}
     */
    @Test
    public void testAddressLineTwoInvalidBecauseItHasContainsInvalidCharacter(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One", // Address line one
                "$ Address Line two", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Address line one, which is too long, should throw {@link InvalidUserException}
     * This test also cover length for address line two, city name.
     */
    @Test
    public void testAddressLineInvalidBecauseAddressLineOneIsTooLong(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One     123712896438726423786437246327864738264873264382746328746328746328746328746327846327846328746328746238746328764328746832423874" +
                        "23646234873264732643724632746324763243624327643872463287463274863247632874636", // Address line one
                "Address Line two", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User City, which is contains unsupported character, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCityInvalidBecauseItHasContainsInvalidCharacter(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One", // Address line one
                "Address Line two", // Address line two (Optional)
                "Dinajpur City $", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which is contains unsupported character, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItHasContainsInvalidCharacter(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One", // Address line one
                "Address Line two", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh 5270", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which length is short, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItsShort(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One", // Address line one
                "Address Line two", // Address line two (Optional)
                "Dinajpur", // User city
                "B", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which is null, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItsNull(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One", // Address line one
                "Address Line two", // Address line two (Optional)
                "Dinajpur", // User city
                null, // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which is too long, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItsTooLong(){

        assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One", // Address line one
                "Address Line two", // Address line two (Optional)
                "Dinajpur", // User city
                "gsdhjfgsjhadfghjasdggfhsdafhsdafghsdjafgsdhjafgsdgfdhd", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a valid address line one,
     * which has all supported character set, and should not throw any exception
     */
    @Test
    public void testAddressLineOneValidHasAllSupportedCharacter(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Village: Khattausna, Post-Office: Khattsusna (5270), Police_Station: Hakimpur & District: Dinajpur.", // Address line one
                "Address Line two", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a valid address line two,
     * which is optional and null, and should not throw any exception
     */
    @Test
    public void testAddressLineTwoValidCanBeNull(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Village: Khattausna, Post-Office: Khattsusna (5270), Police_Station: Hakimpur & District: Dinajpur.", // Address line one
                null, // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a valid address line two,
     * which is optional and empty, and should not throw any exception
     */
    @Test
    public void testAddressLineTwoValidCanBeEmpty(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Village: Khattausna, Post-Office: Khattsusna (5270), Police_Station: Hakimpur & District: Dinajpur.", // Address line one
                "", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a valid city name,
     * which is within size limit and has no invalid character, and should not throw any exception
     */
    @Test
    public void testValidCityHasAllValidCharacterAndValidLength(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address line one", // Address line one
                "Address line two (Optional)", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a valid country name,
     * which is within size limit and has no invalid character, and should not throw any exception
     */
    @Test
    public void testValidCountryName(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address line one", // Address line one
                "Address line two (Optional)", // Address line two (Optional)
                "Dinajpur", // User city
                "Bangladesh", // User country
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

    }

    //TODO: Need to write test for password and recovery question field!


}
