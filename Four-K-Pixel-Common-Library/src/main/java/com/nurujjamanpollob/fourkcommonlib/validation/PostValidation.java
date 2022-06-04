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

package com.nurujjamanpollob.fourkcommonlib.validation;

import com.nurujjamanpollob.fourkcommonlib.exception.InvalidPostException;
import com.nurujjamanpollob.fourkcommonlib.model.Post;
import com.nurujjamanpollob.fourkcommonlib.utility.UtilityCollection;
import com.nurujjamanpollob.fourkcommonlib.utility.Variables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Nurujjaman Pollob 2022
 * @apiNote This record used to validate method value form this class {@link com.nurujjamanpollob.fourkcommonlib.model.Post}
 */
public record PostValidation(Post postToValidate) {

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Invoking this method will validate all method value from {@link Post}
     * @throws InvalidPostException if the validation methods throws {@link InvalidPostException}
     */
    public void validatePost() throws InvalidPostException{

        // Validate post title
        validatePostTitle();

        // Validate post description
        validatePostDescription();

        // Validate post tags
        validatePostTags();

        // Validate post attachments(Image expected)
        validateAttachments();

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote method to validate post title, the post title is requires,
     * and it's length must be between 1 - 150 character
     * @throws InvalidPostException if the title is null or the title length is not between 1 - 150 character
     */
    private void validatePostTitle() throws InvalidPostException {

        String postTitle = postToValidate.getPostTitle();

        if(postTitle == null || postTitle.length() < 1 || postTitle.length() > 150){
            throw new InvalidPostException("Post title is missing, or it's length is not between 1 - 150 character");
        }

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote method to validate post description, it can be nullable and optional,
     * and it's length should not cross 5000 character
     * @throws InvalidPostException if the description is over 5000 character
     */
    private void validatePostDescription() throws InvalidPostException{

        String postDescription = postToValidate.getPostDescription();

        if(postDescription != null && postDescription.length() > 5000){
            throw new InvalidPostException("Post description length is too long, it should be under 5000 character");
        }
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote method to validate post tags, it can be nullable and optional,
     * and it's length should not cross 250 character
     * @throws InvalidPostException if the description is over 250 character
     */
    private void validatePostTags() throws InvalidPostException {

        String postTags = postToValidate().getPostTags();

        if(postTags != null && postTags.length() > 250){
            throw new InvalidPostException("It seems the post tag length is too large, it should be under 250 characters");
        }
    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote method to validate user uploaded attachments, which should be not null and must be image file and size under 15MB
     * @throws InvalidPostException if an attachment file is not found on server, or is not an image file, or image file with larger size(15MB >)
     */
    private void validateAttachments() throws InvalidPostException{

        String[] attachmentsPath = postToValidate().getImageFilesLocation();

        for(String s : attachmentsPath){

           if (!isFileIsValidImageAndSizeWithinMaxLimit(s)){

               // Delete all uploaded file from the server
               UtilityCollection.deleteFiles(attachmentsPath);
               throw new InvalidPostException("It seems this file is not an image file, or the file is empty or the image length is more than 15MB");
           }
        }
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote method to validate user uploaded attachment, which should be not null and must be image file and size under 15MB
     * @return false if an attachment file is not found on server, or is not an image file, or image file with larger size(15MB >), true otherwise
     */
    private boolean isFileIsValidImageAndSizeWithinMaxLimit(String filePath) {

        // Get file length
        long fileLength = UtilityCollection.checkAndGetFileLength(filePath);

        /*
        // fileLength -1 means a file is not found
        // Variables.UPLOAD_FILE_MAX_SIZE is the max file size in the byte,
        // which is 15MB for a single file now
         */
        if(fileLength != -1 && fileLength <= Variables.UPLOAD_FILE_MAX_SIZE){

            try {
              // Is mime type image?
              return UtilityCollection.fileMimeTypeFromPath(filePath).contains("image");
            } catch (IOException e) {
                return false;
            }
        }

        /*
        Either the file is not found, or it's size it too long
         */
        return false;
    }
}
