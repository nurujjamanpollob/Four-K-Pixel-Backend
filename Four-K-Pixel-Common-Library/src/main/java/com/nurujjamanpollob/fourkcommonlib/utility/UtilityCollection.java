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
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Month;
import java.util.Date;
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
    @SuppressWarnings({"UnusedReturnValue"})
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


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to convert a <a href="https://en.wikipedia.org/wiki/Unix_time">Unix Time</a> to a human-readable format.
     * Time from calls like {@link System#currentTimeMillis()} should be formatted to a human-readable format with this method.
     * This method represent output date in following format:
     * <pre>
     *     <code>2022-06-04 at 11:47:33 UTC</code>
     * </pre>
     *
     * If you need to customize the output, or use other formatter instance,
     * use this method {@link UtilityCollection#unixEpochTimeToHumanReadableTimeConverter(Long, SimpleDateFormat)}.
     * @param unixTimeEpoch the unix time long to format as human-readable String.
     * @return Human-readable Unix Epoch time in <pre><code>yyyy-MM-dd 'at' HH:mm:ss z</code></pre> format.
     */
    public static String unixEpochTimeToHumanReadableTimeConverter(Long unixTimeEpoch){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(unixTimeEpoch);

        return formatter.format(date);

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to convert a <a href="https://en.wikipedia.org/wiki/Unix_time">Unix Time</a> to a human-readable format.
     * Time from calls like {@link System#currentTimeMillis()} should be formatted to a human-readable format with this method.
     *
     * The time will be formatted according to simpleDateFormat instance.
     * If you use this following format: <pre><code>yyyy-MM-dd 'at' HH:mm:ss z</code></pre> to format time,
     * then use this method {@link UtilityCollection#unixEpochTimeToHumanReadableTimeConverter(Long)}.
     * @param unixTimeEpoch the unix time long to format as human-readable String.
     * @param simpleDateFormat the {@link SimpleDateFormat} to format the unix time to a human-readable format.
     * @return Human-readable Unix Epoch time in your preferred {@link SimpleDateFormat}
     */
    public static String unixEpochTimeToHumanReadableTimeConverter(Long unixTimeEpoch, SimpleDateFormat simpleDateFormat){

        Date date = new Date(unixTimeEpoch);
        return simpleDateFormat.format(date);

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This method returns file mime type from {@link Files#probeContentType(Path)}, here is excerpt from this method:
     * <pre>
     *     <code>
     *
     *     {@link Files#probeContentType(Path)} Probes the content type of a file.
     *     This method uses the installed {@link java.nio.file.spi.FileTypeDetector}
     *     implementations to probe the given file to determine its content type. Each file type detector's {@link Files#probeContentType(Path)}}
     *     is invoked, in turn, to probe the file type.
     *     If the file is recognized then the content type is returned.
     *     If the file is not recognized by any of the installed file type detectors then a system-default file type detector is invoked to guess the content type.
     *     A given invocation of the Java virtual machine maintains a system-wide list of file type detectors.
     *     Installed file type detectors are loaded using the service-provider loading facility defined by the {@link java.util.ServiceLoader} class.
     *
     *     Installed file type detectors are loaded using the system class loader.
     *     If the system class loader cannot be found then the platform class loader is used.
     *     File type detectors are typically installed by placing them in a JAR file on the application class path,
     *     the JAR file contains a provider-configuration file named java.nio.file.spi.FileTypeDetector in the resource directory META-INF/services,
     *     and the file lists one or more fully-qualified names of concrete subclass of FileTypeDetector that have a zero argument constructor.
     *     If the process of locating or instantiating the installed file type detectors fails then an unspecified error is thrown.
     *     The ordering that installed providers are located is implementation specific.
     *     The return value of this method is the string form of the value of a Multipurpose Internet Mail Extension (MIME) content type as defined by <a href="https://www.ietf.org/rfc/rfc2045.txt">RFC 2045: Multipurpose Internet Mail Extensions (MIME) Part One: Format of Internet Message Bodies.</a>
     *
     *     The string is guaranteed to be parsable according to the grammar in the RFC.
     *
     *     </code>
     * </pre>
     *
     * @param filePath the file absolute path, to find the file in a specific location and get its content type.
     * @return The content type of the file, or null if the content type cannot be determined.
     * @throws IOException If a disk IO exception is occurred.
     */
    public static String fileMimeTypeFromPath(String filePath) throws IOException {

        return Files.probeContentType(Path.of(filePath));
    }
}
