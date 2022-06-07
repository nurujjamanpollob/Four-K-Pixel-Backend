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

import static org.junit.jupiter.api.Assertions.*;

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
                "nurujjamanpollob", // Username goes here
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


    InvalidUserException invalidUserException =  assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "Nurujjamanpollob", // Username goes here
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

    // Assert exception message
    assertTrue(invalidUserException.getMessage().contains("The Username contains invalid character"));
    }

    /**
     * @author Nurujjaman Pollob
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because it has special character
     */
    @Test
    public void testUsernameInvalidBecauseItHasSpecialCharacter() {


      InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

      // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The Username contains invalid character"));
    }

    /**
     * @author Nurujjaman Pollob
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because username length is short
     */
    @Test
    public void TestUsernameInvalidBecauseItsLengthIsShort() {


       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The username is too long or too short!"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because username length is long
     */
    @Test
    public void testUsernameInvalidBecauseItsLengthIsLong() {

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The username is too long or too short!"));
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


       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

       //Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The user is younger to use this website."));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name and last name is null
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseOfNull() {

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

       // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The Name can't be null or the name cant be shorter than 3 character or more than 16 characters"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name has special character
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseItHasInvalidSpecialCharacter() {

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("User First name or last name is invalid"));


    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name length is short
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseItsLengthIsShort() {


       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The Name can't be null or the name cant be shorter than 3 character or more than 16 characters"));


    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user last name has a numeric character
     */
    @Test
    public void testUserFirstLastNameIsInvalidBecauseItHasNumericCharacter() {


       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("User First name or last name is invalid"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method used to validate User First and Last name,
     * and it should throw {@link InvalidUserException} because user first name is too long
     */
    @Test
    public void testUserFirstNameIsInvalidBecauseItsTooLong() {

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The Name can't be null or the name cant be shorter than 3 character or more than 16 characters"));


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

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

       // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The user address contains invalid character"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Address line one, which is short, should throw {@link InvalidUserException}
     */
    @Test
    public void testAddressLineOneInvalidBecauseItsShort(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The user address may be null or the address length is too long or short"));


    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Address line two, which is contains unsupported character, should throw {@link InvalidUserException}
     */
    @Test
    public void testAddressLineTwoInvalidBecauseItHasContainsInvalidCharacter(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The user address contains invalid character"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Address line one, which is too long, should throw {@link InvalidUserException}
     * This test also cover length for address line two, city name.
     */
    @Test
    public void testAddressLineInvalidBecauseAddressLineOneIsTooLong(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
                UserValidation(new User(
                "nurujjamanpollob",
                "password geos here",
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                "Address Line One     1237128964387264237864" +
                        "37246327864738264873264382746328746328746328746328" +
                        "746327846327846328746328746238746328764328746832423874" +
                        "2364623487326473264372463274632476324362432764387246328" +
                        "7463274863247632874636", // Address line one
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The user address may be null or the address length is too long or short"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User City, which is contains unsupported character, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCityInvalidBecauseItHasContainsInvalidCharacter(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The user address contains invalid character"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which is contains unsupported character, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItHasContainsInvalidCharacter(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The user country name or code seems contains invalid character!"));

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which length is short, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItsShort(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The country name or code length is too short or too long!"));


    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which is null, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItsNull(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The country name or code length is too short or too long!"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate User Country name, which is too long, should throw {@link InvalidUserException}
     */
    @Test
    public void testUserCountryInvalidBecauseItsTooLong(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The country name or code length is too short or too long!"));

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

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a password, that contains a new character
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidatePasswordInvalidBecauseItHasNewLineCharacter(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here \n", // Password here
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
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

       // Assert exception message
       assertTrue(invalidUserException.getMessage().contains("Password or recovery question seems invalid!"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a password, that is too short
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidatePasswordInvalidBecauseItsTooShort(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "pswd", // Password here
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
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

       // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The password secret message is null or it is too small or too long"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a password, that is null
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidatePasswordInvalidBecauseItsNull(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                null, // Password here
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The password secret message is null or it is too small or too long"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a password, that is too long
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidatePasswordInvalidBecauseItsTooLong(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "pswdgdhfgdhsgfhdsgfhdjsgfhdsjgfjdsfgdshjfgdjsf" +
                        "fghdgdfhgdfhgdfhghdsgfdshfgdshdhfdsgfdhsfgdhfgdsh" +
                        "fgdsfhdgsfdfgdshfgdfhgdshfgdshfgds", // Password here
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
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

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The password secret message is null or it is too small or too long"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a password, which is seems good
     * and should not throw any exception
     */
    @Test
    public void testValidatePasswordIsGood(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "#$PsWrDDJK126867^%$#*()/?~`", // Password here
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
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
     * @apiNote This test method validates recovery question, which is invalid because it contains new line character
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidateRecoveryQuestionInvalidBecauseItHasNewLineCharacter(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "recovery question goes here \n", // Recovery question
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("Password or recovery question seems invalid!"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validates recovery question, which is invalid because it's too short
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidateRecoveryQuestionInvalidBecauseItsTooShort(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "rq", // Recovery question
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The password secret message is null or it is too small or too long"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validates recovery question, which is invalid because it's null
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidateRecoveryQuestionInvalidBecauseItsNull(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                null, // Recovery question
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The password secret message is null or it is too small or too long"));

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validates recovery question, which is invalid because it's too long
     * should throw {@link InvalidUserException}
     */
    @Test
    public void testValidateRecoveryQuestionInvalidBecauseItsTooLong(){

      InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "rqkhdhdhdhdhfhfhfhruyerhfdhdfydfydydydgfdhfghfyffyfyfyf" +
                        "yfgfehfgfgfhfdhyfdhdyfgysdgfhsdgfhjdsgfhjsdgfhjdsgfhjdsfgdshfgdshfgdsh" +
                        "fgsdhfgdshfgshdfgdshfgdshfgdshfgdshfgdshfxcvdsfhdsgfhdsgfdshfgdshfghdsfgdshfdsgf" +
                        "dvsdfgsfhdsgfhdsgfhdsgfhdsfgdhsfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdsfhdsgfhdsgfd", // Recovery question
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("The password secret message is null or it is too small or too long"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validates recovery question, which is simply valid
     * should not throw any question
     */
    @Test
    public void testValidateRecoveryQuestionValid(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description",
                "User Bio",
                "User hobby goes here",
                "Hey, you know my name? Its Nurujjaman Pollob.", // Recovery question
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
     * @apiNote Test method to validate a user short description, which is simply small then approved limit
     * and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserShortDescriptionInvalidBecauseItsTooShort(){

      InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "min char", // User short description goes here
                "User Bio",
                "User hobby goes here",
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

      // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a user short description, which is null
     * and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserShortDescriptionInvalidBecauseItsNull(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                null, // User short description goes here
                "User Bio",
                "User hobby goes here",
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Test method to validate a user short description, which is very tall then approved limit
     * and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserShortDescriptionInvalidBecauseItsTall(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "fdgsghdfhsfgshfghsfggdshfgdhsfghsdfgdshfgdshfgdshfgdshfdsghfds" +
                        "dfdsfgdshfghdsfgdhsfghdsfgdshfgdshfghdsfgdshfgdshfgdshfgdshfgdshfgdshfgdhsf" +
                        "dfdsfghdsgfhdsgfdsfgdsfgdshfgsdhfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshf" +
                        "dfvdsgfhgdsfhgdsfdsfdshfgdshfgsdhfgsdhfgdshfgdshfgdshfgdshfgdshfgdshfdsgfhdsgfhdsgf" +
                        "fdhdsfghdsfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdsh" +
                        "gfhdsgfhdsgfdshfdhsfgdshfgdshfgdshfdsghfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdshfgdhsf" +
                        "fdhgfdshgfhdsfgdhsfgdhsgfdshfgdshfgdshfgdshfgdshfgdshfgdhsfgdfhgdshfgdshfgdshfgdshfgdshfhghfg", // User short description goes here
                "User Bio",
                "User hobby goes here",
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validates short description, and this should not throw any exception
     */
    @Test
    public void testUserShortDescriptionValid(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                """
                        Hey,
                        It's me Nurujjaman Pollob. I'm a student and a noob programmer.
                        Glad to see you here 😙❤!️""", // User short description
                "User Bio",
                "User hobby goes here",
                "Recovery question goes here",
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
     * @apiNote This test method validate user bio, which is null and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserBioInvalidBecauseItsNull(){


       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                null, // User bio
                "User hobby goes here",
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));
    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validate user bio, which is small compare to size limit
     * and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserBioInvalidBecauseItsShort(){


       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                "M", // User bio
                "User hobby goes here",
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validate user bio, which is large compare to size limit
     * and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserBioInvalidBecauseItsLong(){


       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "gdhfgdhfgdshfgdsjfgdshjfgdsjhgfdsgfhdsjfgdsfgdjgdggdshfgdshfdshgfh" +
                        "gfdhdghdghdghdgfdffgdshfgdshfgdshfgdshgdshgdshgdshfgdsfhdsfhdsfgdshdgshffgdhsfg",
                "M", // User bio
                "User hobby goes here",
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validate user bio, which is normal, not null, within size limit 2 -150 character
     * So, it should not throw any exception.
     */
    @Test
    public void testUserBioIsValid(){


        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                "Hey, welcome to my profile! Have some coffee😇", // User bio
                "User hobby goes here",
                "recovery question goes here",
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
     * @apiNote This test method validate user hobby,
     * which is invalid because it can't meet minimum character limit
     * and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserHobbyIsInvalidBecauseHobbyLengthIsTooShort(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                "Hey, welcome to my profile! Have some coffee😇",
                "HB", // User Hobby goes here
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validate user hobby,
     * which is invalid because it exceeds maximum character limit
     * and should throw {@link InvalidUserException}
     */
    @Test
    public void testUserHobbyIsInvalidBecauseHobbyLengthIsTooLong(){

       InvalidUserException invalidUserException = assertThrows(InvalidUserException.class, ()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                "Hey, welcome to my profile! Have some coffee😇",
                "HB,ghsadghsagdhasgdsahdg,asdhasdhasdasdgashdgahsgdash,dgadgahsdgashdgashdgashd," +
                        "dshhgdasdhasgd,dsdghasgdhsadgashdgashdg,asdhasdgashdashdashdgsa,asdhsdhshdsahdg", // User Hobby goes here
                "recovery question goes here",
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        )).validateUser());

        // Assert exception message
        assertTrue(invalidUserException.getMessage().contains("It seems user provided details null or it's too short or too long"));

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This test method validate user hobby,
     * which is valid, and can be nullable, because this field is optional,
     * and should not throw any exception
     */
    @Test
    public void testUserHobbyIsValidItCanBeNullable(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                "Hey, welcome to my profile! Have some coffee😇",
                null, // User Hobby goes here
                "recovery question goes here",
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
     * @apiNote This test method validate user hobby,
     * which is valid, and can be nullable, because this field is optional,
     * and should not throw any exception
     */
    @Test
    public void testUserHobbyIsValidItCanBeEmpty(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                "Hey, welcome to my profile! Have some coffee😇",
                "", // User Hobby goes here
                "recovery question goes here",
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
     * @apiNote This test method validate user hobby,
     * which is valid, has content within size limit
     * and should not throw any exception
     */
    @Test
    public void testUserHobbyIsValid(){

        assertDoesNotThrow(()-> new UserValidation(new User(
                "nurujjamanpollob",
                "password goes here",
                "Nurujjaman",
                "Pollob",
                "Address line one",
                "Address line two (Optional)",
                "Dinajpur",
                "Bangladesh",
                "User short description goes here.",
                "Hey, welcome to my profile! Have some coffee😇",
                "Coding, Solving Math Problem, Gardening, Riding boat", // User Hobby goes here
                "recovery question goes here",
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

}
