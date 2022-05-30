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

package com.nurujjamanpollob.fourkcommonlib.validation;

import com.nurujjamanpollob.fourkcommonlib.exception.InvalidUserException;
import com.nurujjamanpollob.fourkcommonlib.model.User;
import com.nurujjamanpollob.fourkcommonlib.utility.UtilityCollection;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Record that used to validate an instance of {@link com.nurujjamanpollob.fourkcommonlib.model.User}
 * before saving in database, or update in database.
 */
public record UserValidation(User userToValidate) {

    /**
     * @author Nurujjaman Pollob 2022
     * Method to invoke all private method inside this class, and validate {@link User} instance.
     */
    // TODO: need to implement private methods
    public void validateUser() throws InvalidUserException {

        // Validate User Name
        validateUserName();

        // Validate user age
        validateUserBirthInformation();

        // Validate first name
        validateFirstName();

        // Validate last name
        validateLastName();
    }

    /**
     * @author Nurujjaman Pollob 2022
     * Method that validate a username
     * @throws InvalidUserException if the username length is too short, long or contains not allowed character
     */
    private void validateUserName() throws InvalidUserException {

        // Get username for User Object
        String username = userToValidate().getUserName();

        // Check if username is null
        if(null == username){
            throw new InvalidUserException("Username can't be null");
        }
        /*
        Check if username length is between 3 - 20 character
        If now throw InvalidUserException
         */

        int charLen = username.length();
        if(charLen < 3 || charLen > 20){
            throw new InvalidUserException("The username is too long or too short!");
        }


        // Create Username Pattern
        Pattern userNamePattern = Pattern.compile("^[a-z\\d]*$");

        // Create Matcher
        Matcher uNameMatcher = userNamePattern.matcher(username);

        // Username contains invalid character
        if(!uNameMatcher.matches()){

            throw new InvalidUserException("The Username contains invalid character");
        }

    }

    /**
     * @author Nurujjaman Pollob 2022
     * Method to Validate a User birthday information.
     * If User is younger than 13 year, then will throw {@link InvalidUserException}
     * @throws InvalidUserException if user birthday information is younger than 13 year from current date.
     */
    private void validateUserBirthInformation() throws InvalidUserException{

        int day = userToValidate().getUserBirthDay();
        int month = userToValidate.getUserBirthMonth();
        int year = userToValidate.getUserBirthYear();

        // User Birthday
        LocalDate bDay = LocalDate.of(year, UtilityCollection.getMonthInstanceFromInteger(month), day);

        // Today's date
        LocalDate today = LocalDate.now();

        // User age period
        Period age = Period.between(bDay, today);

        // Get user age from period
        int userAge = age.getYears();

        if(userAge < 13){
            throw new InvalidUserException("The user is younger to use this website.");
        }

    }

    /**
     * @author Nurujjaman Pollob 2022
     * Method to validate User First name
     * @throws InvalidUserException if the user first name contains character other than a-z,A-Z or the name length is not between 3 - 16
     */
    private void validateFirstName() throws InvalidUserException{
        validateName(userToValidate().getUserFirstName());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * Method to validate User First name
     * @throws InvalidUserException if the user first name contains character other than a-z,A-Z or the name length is not between 3 - 16
     */
    private void validateLastName() throws  InvalidUserException{
        validateName(userToValidate().getUserLastname());
    }
    /**
     * @author Nurujjaman Pollob 2022
     * Method to validate User Full name(First + Last)
     * @throws InvalidUserException if the user first name contains character other than a-z,A-Z or the name length is not between 3 - 16
     */
    private void validateName(String name) throws InvalidUserException {

        if(name == null || name.length() > 16 || name.length() < 3){
            throw new InvalidUserException("The Name can't be null");
        }
        // Create name pattern
        Pattern namePattern = Pattern.compile("^[A-Za-z]*$");

        Matcher matcher = namePattern.matcher(name);

        if(!matcher.matches()){
            throw new InvalidUserException("User First name or last name is invalid");
        }


    }

}
