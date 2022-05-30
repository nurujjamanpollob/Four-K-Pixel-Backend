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
                "nurujjamanpollob", // Username
                null,
                "Nurujjaman",
                "Pollob",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because it has capital latter
     */
    @Test
    public void testUsernameInvalidBecauseItHasCapitalLatter() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "Nurujjamanpollob", // Username
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
     * @author Nurujjaman Pollob
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because it has special character
     */
    @Test
    public void testUsernameInvalidBecauseItHasSpecialCharacter() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "nurujjamanpollob$", // Username
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
     * @author Nurujjaman Pollob
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because username length is short
     */
    @Test
    public void TestUsernameInvalidBecauseItsLengthIsShort() {


        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "np", // Username
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
     * @apiNote Test method to test a username, which should throw {@link InvalidUserException} because username length is long
     */
    @Test
    public void testUsernameInvalidBecauseItsLengthIsLong() {

        assertThrows(InvalidUserException.class, () -> new UserValidation(new User(
                "np1020304050607080901", // Username
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
     * @apiNote Test method to test a user age,
     * this test should not throw any exception
     * because the user age is more than thirteen year.
     */
    @Test
    public void testUserAgeIsValidBecauseHeIsAboveThirteenYearOld() {

        assertDoesNotThrow(() -> new UserValidation(new User(
                "nurujjamanpollob",
                null,
                "Nurujjaman",
                "Pollob",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                1998, // User birth year
                12, // User birth month
                7, // User birth day
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
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                2010, // User birth year
                4, // User birth month
                29, // User birth day
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
                null,
                null, // User first name
                null, // User last name
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
                null,
                "Nurujjaman$", // User First Name
                "Pollob", // User Last Name
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
                null,
                "NP", // User First Name
                "Pollob", // User Last Name
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
                null,
                "Nurujjaman", // User First Name
                "Pollob1", // User Last Name
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
                null,
                "NurujjamanNurujja", // User First Name
                "Pollob", // User Last Name
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
                null,
                "Nurujjaman", // User First Name
                "Pollob", // User Last Name
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
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
