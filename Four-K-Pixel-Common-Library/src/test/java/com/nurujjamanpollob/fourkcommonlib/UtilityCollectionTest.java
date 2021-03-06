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

import java.io.IOException;

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
     * @apiNote This test method test a password encrypted with {@link BCryptPasswordEncoder}
     * So the method {@link UtilityCollection#isPasswordEncodedWithBcryptPasswordEncryptor(String)} should return true
     * Note, this algorithm is not perfect, and it may evaluate wrong result
     */
    @Test
    public void testBcryptPasswordEncodedPassword(){

        assertTrue(UtilityCollection.isPasswordEncodedWithBcryptPasswordEncryptor("$2a$10$6Yo4UW7jAWZEcM63u2OZludKALQku4SNFGvCG.fN789AyLisbywGO"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test encoded password, but with some character removed.
     * So the method {@link UtilityCollection#isPasswordEncodedWithBcryptPasswordEncryptor(String)} should return false
     * Note, this algorithm is not perfect, and it may evaluate wrong result
     */
    @Test
    public void testBcryptEncodedPasswordInvalidBecauseSomeCharacterIsRemoved(){

        assertFalse(UtilityCollection.isPasswordEncodedWithBcryptPasswordEncryptor("$2a$10$6Yo4UW7jAWZEcM63u2OZludKALQku4SCG."));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test encoded password, but it's identify factor replaced with z
     * So the method {@link UtilityCollection#isPasswordEncodedWithBcryptPasswordEncryptor(String)} should return false
     * Note, this algorithm is not perfect, and it may evaluate wrong result
     */
    @Test
    public void testBcryptEncodedPasswordInvalidBecauseOfIdentifyFactorIsMissing(){

        assertFalse(UtilityCollection.isPasswordEncodedWithBcryptPasswordEncryptor("$2z$10$6Yo4UW7jAWZEcM63u2OZludKALQku4SNFGvCG.fN789AyLisbywGO"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test a String to convert to a {@link java.math.BigInteger} for mainly generate an Individual account ID number
     * And store in database
     */
    @Test
    public void testStringToBigIntegerConvert(){
        assertTrue(UtilityCollection.stringToBigIntegerConverter("nurujjamanpollob").intValue() > 0);
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test a dng image from disk, should contain this text <quote>image</quote> from {@link UtilityCollection#fileMimeTypeFromPath(String)} from returned String
     * @throws IOException If a Disk IO exception is occurred.
     */
    @Test
    public void testGetDngFileMimeType() throws IOException {

       assertTrue(UtilityCollection.fileMimeTypeFromPath("testfiles/image/test-image-large.dng").contains("image"));

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to extract correct file mime type, from a text file which is renamed to png, but it's actually a text file,
     * should return text/plain
     * @throws IOException there is not much information on this exception,
     * basically, a disk io error or accessing file header exception can throw this exception.
     */
    @Test
    public void testGetMimeTypeShouldContainTextFromAWrongExtension() throws IOException {

        assertTrue(UtilityCollection.fileMimeTypeFromPath("testfiles/image/not-a-image-file.png").contains("text/plain"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This method invoke {@link UtilityCollection#unixEpochTimeToHumanReadableTimeConverter(Long)} to convert a Unix like time (Current time) to human-readable format.
     * which should contain 20 present in output String, here 20 means twenty century.
     */
    @Test
    public void testCurrentUnixEpochTimeToHumanReadableFormat(){

        // Get current Unix-Like time
        long currentTimeMillis = System.currentTimeMillis();

        // Convert to human-readable format
        String currentTimeString = UtilityCollection.unixEpochTimeToHumanReadableTimeConverter(currentTimeMillis);

        // Assert output
        assertTrue(currentTimeString.contains("20"));
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This method invoke {@link UtilityCollection#unixEpochTimeToHumanReadableTimeConverter(Long)} to convert a Unix like time to human-readable format.
     * The supplied unix time long is invalid, and it should not contain 20 in output String, here 20 means twenty century.
     */
    @Test
    public void testInvalidUnixEpochTimeToHumanReadableFormat(){

        // Convert to human-readable format
        String currentTimeString = UtilityCollection.unixEpochTimeToHumanReadableTimeConverter( 1000000L);//The unix time is invalid

        // Assert output
        assertFalse(currentTimeString.contains("20"));
    }
}
