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

    private User userObject;

    /**
     * @author Nurujjaman Pollob 2022
     * Test method to test a username, which is valid to use
     * And should not throw any exception
     */
    @Test()
    public void testSimpleUsernameIsValid() {

        userObject = new User(
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
                1998,
                12,
                7,
                11111,
                false,
                true,
                false,
                true
        );
        assertDoesNotThrow(() -> new UserValidation(userObject).validateUser());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * Test method to test a username, which should throw {@link InvalidUserException} because it has capital latter
     */
    @Test
    public void usernameInvalidBecauseItHasCapitalLatter(){

        userObject = new User(
                "Nurujjamanpollob",
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
        );

        assertThrows(InvalidUserException.class, ()-> new UserValidation(userObject).validateUser());
    }

    /**
     * @author Nurujjaman Pollob
     * Test method to test a username, which should throw {@link InvalidUserException} because it has special character
     */
    @Test
    public void usernameInvalidBecauseItHasSpecialCharacter(){

        userObject = new User(
                "nurujjamanpollob$",
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
        );

        assertThrows(InvalidUserException.class, ()-> new UserValidation(userObject).validateUser());
    }

    /**
     * @author Nurujjaman Pollob
     * Test method to test a username, which should throw {@link InvalidUserException} because username length is short
     */
    @Test
    public void usernameInvalidBecauseItsLengthIsShort(){

        userObject = new User(
                "np",
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
        );

        assertThrows(InvalidUserException.class, ()-> new UserValidation(userObject).validateUser());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * Test method to test a username, which should throw {@link InvalidUserException} because username length is long
     */
    @Test
    public void usernameInvalidBecauseItsLengthIsLong(){

        userObject = new User(
                "np1020304050607080901",
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
        );

        assertThrows(InvalidUserException.class, ()-> new UserValidation(userObject).validateUser());
    }

}
