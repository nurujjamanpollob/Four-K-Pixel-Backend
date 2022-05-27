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

package com.nurujjamanpollob.fourkcommonlib.utility;

import java.math.BigInteger;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * This class is expected to be store static methods only,
 * and to write methods that frequently inside project.
 * This class should not accept constructor.
 */
@SuppressWarnings({"unused"})
public class UtilityCollection {

    /**
     * @author Nurujjaman Pollob 2022
     * Method to check if a String is encoded by {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}
     * @param password the password to check
     * @return true if its encoded, else false.
     * @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder for more information.
     */
    public static boolean isPasswordEncodedWithBcryptPasswordEncryptor(String password){

        if(password == null || password.length() != 60){
            return false;
        }

        // Create password detection pattern
        Pattern BCRYPT_PATTERN = compile("\\A\\$2([ayb])?\\$(\\d\\d)\\$[./\\dA-Za-z]{53}");

        // Return result
        return BCRYPT_PATTERN.matcher(password).matches();

    }

    /**
     * @author Nurujjaman Pollob
     * Converts {@link java.lang.String} to {@link java.math.BigInteger}
     * This algorithm works in simple way, get each character from Input String,
     * Then, get ASCII value(0-127) for this character,
     * and then, store in {@link java.lang.StringBuilder} collection,
     * finally, it will be converted to {@link java.math.BigInteger} instance, and returned.
     * @param inputString the String that needs to be converted
     * @return {@link java.math.BigInteger}
     */
    public static BigInteger stringToBigIntegerConverter(String inputString){

        StringBuilder result = new StringBuilder();

        for(char c : inputString.toCharArray()){
            result.append((int) c);
        }

        return new BigInteger(result.toString());
    }
}
