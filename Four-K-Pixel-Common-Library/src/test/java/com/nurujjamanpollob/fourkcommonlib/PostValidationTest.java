/*
 * Copyright (c) 2022 Nurujjaman Pollob, All Right Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * If you have contributed in codebase,
 * and want to add your name or copyright in a particular class or method,
 * you must follow this following pattern:
 * <code>
 *     // For a new method created by you,
 *     //like this example method with name fooMethod()
 *     //then use following format:
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
 * </code>
 */

package com.nurujjamanpollob.fourkcommonlib;

import com.nurujjamanpollob.fourkcommonlib.exception.InvalidPostException;
import com.nurujjamanpollob.fourkcommonlib.model.Post;
import com.nurujjamanpollob.fourkcommonlib.validation.PostValidation;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test {@link com.nurujjamanpollob.fourkcommonlib.validation.PostValidation} class's validation process
 */
public class PostValidationTest {

    private Post userPost;


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test a post Title, which is null, not within size limit 1 - 150,
     * and should throw {@link InvalidPostException}
     */
    @Test
    public void testPostTitleInvalidBecauseItsNull(){

        userPost = new Post(
                "nurujjamanpollob",
                null, // Post title goes here
                "Post description",
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertThrows(InvalidPostException.class, ()-> new PostValidation(userPost).validatePost());
    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test a post Title, which is empty, not within size limit 1 - 150,
     * and should throw {@link InvalidPostException}
     */
    @Test
    public void testPostTitleInvalidBecauseItsEmpty(){

        userPost = new Post(
                "nurujjamanpollob",
                "", // Post title goes here
                "Post description",
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertThrows(InvalidPostException.class, ()-> new PostValidation(userPost).validatePost());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test a post Title, which is too long, not within size limit 1 - 150,
     * and should throw {@link InvalidPostException}
     */
    @Test
    public void testPostTitleInvalidBecauseTitleTooLong(){

        userPost = new Post(
                "nurujjamanpollob",
                "djhfjdshfjdshfdsjfhdsjfhdsjfhdsjfhdsjfhdsfjhdsfjhdsjfdhfjdhfjdshfjdshfdjshfdsjhf" +
                        "dsfgdhfgdshfgdshfgdsddsfgdhsfghfgdshfgdhfdgfhdsgfhfgdshfgddhsgfgdhfgdsh", // Post title goes here
                "Post description",
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertThrows(InvalidPostException.class, ()-> new PostValidation(userPost).validatePost());
    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test a post title, which is within size limit 1 - 150,
     * and should not throw any exception
     */
    @Test
    public void testPostTitleIsOk(){

        userPost = new Post(
                "nurujjamanpollob",
                "Nature and beauty, watch my shot, and follow me!", // Post title goes here
                "Post description",
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertDoesNotThrow(()-> new PostValidation(userPost).validatePost());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to test Post description, that is too long,
     * post description can go up to 5000 character, this method should throw
     * {@link InvalidPostException}
     */
    @Test
    public void testPostDescriptionIsInvalidBecauseItsTooLong(){

        userPost = new Post(
                "nurujjamanpollob",
                "Nature and beauty, watch my shot, and follow me!",
                "Post description test should be exceed 5000 characters." + // Post description goes here
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters." +
                        "Post description test should be exceed 5000 characters.",
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertThrows(InvalidPostException.class, ()-> new PostValidation(userPost).validatePost());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate post description, which is optional and nullable and
     * should not throw any exception.
     */
    @Test
    public void testPostDescriptionValidCanBeNullable(){

        userPost = new Post(
                "nurujjamanpollob",
                "Nature and beauty, watch my shot, and follow me!",
                null, // Post description
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertDoesNotThrow(()-> new PostValidation(userPost).validatePost());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate post description, which is optional and empty and
     * should not throw any exception.
     */
    @Test
    public void testPostDescriptionValidCanBeEmpty(){

        userPost = new Post(
                "nurujjamanpollob",
                "Nature and beauty, watch my shot, and follow me!",
                "", // Post description
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertDoesNotThrow(()-> new PostValidation(userPost).validatePost());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate post description, which is optional, and it's length is under 5000 character
     * should not throw any exception.
     */
    @Test
    public void testPostDescriptionValid(){

        userPost = new Post(
                "nurujjamanpollob",
                "Nature and beauty, watch my shot, and follow me!",
                "This image is subject to copyright. If you need to use this image in somewhere else, please credit to original author.", // post description goes here
                new String[]{"testfiles/image/1.png"},
                "Testing, Image, Validating",
                System.currentTimeMillis());

        assertDoesNotThrow(()-> new PostValidation(userPost).validatePost());

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate post tag, which is larger than 250 character, treated as long tag.
     */
    @Test
    public void testPostTagInvalidBecauseItsLarge(){


        userPost = new Post(
                "nurujjamanpollob",
                "Nature and beauty, watch my shot, and follow me!",
                "This image is subject to copyright. If you need to use this image in somewhere else, please credit to original author.",
                new String[]{"testfiles/image/1.png"},
                "The post tag is more than 250 character" +
                        "The post tag is more than 250 character" +
                        "The post tag is more than 250 character" +
                        "The post tag is more than 250 character" +
                        "The post tag is more than 250 character" +
                        "The post tag is more than 250 character" +
                        "The post tag is more than 250 character",
                System.currentTimeMillis()); // Post tags goes here

        assertThrows(InvalidPostException.class, ()-> new PostValidation(userPost).validatePost());

    }


}
