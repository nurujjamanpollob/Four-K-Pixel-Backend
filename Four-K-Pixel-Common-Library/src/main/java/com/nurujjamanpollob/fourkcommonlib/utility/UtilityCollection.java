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

import java.io.File;
import java.math.BigInteger;
import java.time.DateTimeException;
import java.time.Month;
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
     * @apiNote Suppress default constructor
     */
    private UtilityCollection() {
    }

    /**
     * @param password the password to check
     * @return true if its encoded, else false.
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to check if a String is encoded by {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}
     * @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder for more information.
     */
    public static boolean isPasswordEncodedWithBcryptPasswordEncryptor(String password) {

        // Create password detection pattern
        Pattern BCRYPT_PATTERN = compile("\\A\\$2([ayb])?\\$(\\d\\d)\\$[./\\dA-Za-z]{53}");

        // Return result
        return BCRYPT_PATTERN.matcher(password).matches();

    }

    /**
     * @param inputString the String that needs to be converted
     * @return {@link java.math.BigInteger}
     * @author Nurujjaman Pollob 2022
     * @apiNote Converts {@link java.lang.String} to {@link java.math.BigInteger}
     * This algorithm works in simple way, get each character from Input String,
     * Then, get ASCII value(0-127) for this character,
     * and then, store in {@link java.lang.StringBuilder} collection,
     * finally, it will be converted to {@link java.math.BigInteger} instance, and returned.
     */
    public static BigInteger stringToBigIntegerConverter(String inputString) throws NullPointerException {

        StringBuilder result = new StringBuilder();

        for (char c : inputString.toCharArray()) {
            result.append((int) c);
        }

        return new BigInteger(result.toString());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @param month the number as month
     * @return {@link Month}
     * @throws DateTimeException if the Integer parameter is not between 1 - 12
     * @author Nurujjaman Pollob 2022
     * @apiNote This method used to return a {@link Month} instance
     * The integer range should be between 1 -12
     */
    public static Month getMonthInstanceFromInteger(Integer month) throws DateTimeException {

        if(month < 1 || month > 12){
            throw new DateTimeException("Not a valid range of month");
        }

        return
                month == 1 ? Month.JANUARY
                        : month == 2 ? Month.FEBRUARY
                        : month == 3 ? Month.MARCH
                        : month == 4 ? Month.APRIL
                        : month == 5 ? Month.MAY
                        : month == 6 ? Month.JUNE
                        : month == 7 ? Month.JULY
                        : month == 8 ? Month.AUGUST
                        : month == 9 ? Month.SEPTEMBER
                        : month == 10 ? Month.OCTOBER
                        : month == 11 ? Month.NOVEMBER
                        : Month.DECEMBER;

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to get a file length, from given path
     * @param filePath the file absolute path
     * @return -1 if file is not found, else file length in bytes is returned.
     */
    public static long checkAndGetFileLength(String filePath){

        File file = new File(filePath);

        if(file.exists()){

            return file.length();
        }

        return -1;
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This method used to delete an array of file from disk
     * @param filePaths the file absolute paths to delete a file from disk
     * @return true if all file is deleted successfully, false can be returned for a mixed of successful and unsuccessful deletion.
     * bit its guaranteed that, least one file deletion is unsuccessful.
     */
    public static boolean deleteFiles(String[] filePaths){

        boolean deletedAll = true;

        for(String filePath : filePaths){
            File file = new File(filePath);

            if(file.exists()){

                if(!file.delete()){

                    deletedAll = false;
                }
            }
        }

        return deletedAll;
    }
}
