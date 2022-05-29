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

import com.nurujjamanpollob.fourkcommonlib.utility.UtilityCollection;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test class for {@link com.nurujjamanpollob.fourkcommonlib.utility.UtilityCollection} class.
 *
 * This class should cover all method tests from this mentioned class.
 */
@SuppressWarnings({"unused"})
public class UtilityCollectionTest {


    /**
     * @author Nurujjaman Pollob 2022
     * This test method test a password encrypted with {@link BCryptPasswordEncoder}
     * So the method {@link UtilityCollection#isPasswordEncodedWithBcryptPasswordEncryptor(String)} should return true
     * Note, this algorithm is not perfect, and it may evaluate wrong result
     */
    @Test
    public void testBcryptPasswordEncodedPassword(){

        assertTrue(UtilityCollection.isPasswordEncodedWithBcryptPasswordEncryptor("$2a$10$6Yo4UW7jAWZEcM63u2OZludKALQku4SNFGvCG.fN789AyLisbywGO"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * Method to test encoded password, but with some character removed.
     * So the method {@link UtilityCollection#isPasswordEncodedWithBcryptPasswordEncryptor(String)} should return false
     * Note, this algorithm is not perfect, and it may evaluate wrong result
     */
    @Test
    public void testBcryptEncodedPasswordInvalidBecauseSomeCharacterIsRemoved(){

        assertFalse(UtilityCollection.isPasswordEncodedWithBcryptPasswordEncryptor("$2a$10$6Yo4UW7jAWZEcM63u2OZludKALQku4SCG."));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * Method to test encoded password, but it's identify factor replaced with z
     * So the method {@link UtilityCollection#isPasswordEncodedWithBcryptPasswordEncryptor(String)} should return false
     * Note, this algorithm is not perfect, and it may evaluate wrong result
     */
    @Test
    public void testBcryptEncodedPasswordInvalidBecauseOfIdentifyFactorIsMissing(){

        assertFalse(UtilityCollection.isPasswordEncodedWithBcryptPasswordEncryptor("$2z$10$6Yo4UW7jAWZEcM63u2OZludKALQku4SNFGvCG.fN789AyLisbywGO"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * Method to test a String to convert to a {@link java.math.BigInteger} for mainly generate an Individual account ID number
     * And store in database
     */
    @Test
    public void testStringToBigIntegerConvert(){
        assertTrue(UtilityCollection.stringToBigIntegerConverter("nurujjamanpollob").intValue() > 0);
    }
}
