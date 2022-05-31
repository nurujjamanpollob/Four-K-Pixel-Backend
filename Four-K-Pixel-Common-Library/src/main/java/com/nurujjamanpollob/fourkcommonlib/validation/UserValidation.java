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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
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
    public void validateUser() throws InvalidUserException {

        // Validate User Name
        validateUserName();

        // Validate user age
        validateUserBirthInformation();

        // Validate first name
        validateFirstName();

        // Validate last name
        validateLastName();

        //Validate User Address line One
        validateUserAddressLineOne();

        // Validate address line two
        validateUserAddressLineTwo();

        // Validate user city
        validateUserCity();

        // Validate user country
        validateUserCountryName();

        // Validate user bio
        validateUserBio();

        // Validate user short description
        validateUserShortDescription();

        // Validate user hobby
        validateUserHobby();

        // Validate user recovery question
        validateRecoveryQuestion();

        // Validate password
        validatePassword();





    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method that validate a username
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
     * @apiNote Method to Validate a User birthday information.
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

        // The user is younger
        if(userAge < 13){
            throw new InvalidUserException("The user is younger to use this website.");
        }

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate User First name
     * @throws InvalidUserException if the user first name contains character other than a-z,A-Z or the name length is not between 3 - 16
     */
    private void validateFirstName() throws InvalidUserException{
        validateName(userToValidate().getUserFirstName());

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate User First name
     * @throws InvalidUserException if the user first name contains character other than a-z,A-Z or the name length is not between 3 - 16
     */
    private void validateLastName() throws  InvalidUserException{
        validateName(userToValidate().getUserLastname());
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate User Full name(First + Last)
     * @throws InvalidUserException if the user first name contains character other than a-z,A-Z or the name length is not between 3 - 16
     */
    private void validateName(String name) throws InvalidUserException {

        if(name == null || name.length() > 16 || name.length() < 3){
            throw new InvalidUserException("The Name can't be null");
        }
        // Create name pattern
        Pattern namePattern = Pattern.compile("^[A-Za-z]*$");

        Matcher matcher = namePattern.matcher(name);

        // Contains invalid character
        if(!matcher.matches()){
            throw new InvalidUserException("User First name or last name is invalid");
        }


    }

    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote Method to validate user address line one.
     * For more information see {@link UserValidation#validateAddressInformation(String, boolean)}
     */
    private void validateUserAddressLineOne() throws InvalidUserException {

        validateAddressInformation(userToValidate().getUserAddressLineOne(), false);

    }

    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote Method to validate user address line two.
     * For more information see {@link UserValidation#validateAddressInformation(String, boolean)}
     */
    private void validateUserAddressLineTwo() throws InvalidUserException {

        validateAddressInformation(userToValidate().getUserAddressLineTwo(), true);

    }

    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote Method to validate user city
     * For more information see {@link UserValidation#validateAddressInformation(String, boolean)}
     */
    private void validateUserCity() throws InvalidUserException {

        validateAddressInformation(userToValidate().getUserCityName(), false);

    }

    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote Method that used to simply validate user address related information
     *
     * @param userAddress the user address to check for validity
     * @param isOptional used to determine if address option is optional, so the checker can skip the minimum length and user is allowed to skip this field.
     *
     * @throws InvalidUserException if there is character other than
     * <code>A-Z a-z 0-9 whitespace comma colon semicolon first bracket(open & close) & </code>
     * or user Address length is not between 5 - 200 character
     */
    private void validateAddressInformation(String userAddress, boolean isOptional) throws InvalidUserException{


        if(!isOptional) {

            // User address length is null, or it's length is not between 5 - 200 range
            if (userAddress == null || userAddress.length() < 5 || userAddress.length() > 200) {
                throw new InvalidUserException("The user address may be null or the address length is too long or short");
            }
            // check address
            checkAddress(userAddress);
        }else {
            // Optional address length is too long
            if(userAddress != null && userAddress.length() > 200){
                throw new InvalidUserException("The Optional address line is too long. it should be under 200 characters");
            }

            // Optional address is not null, so let's check
            if(userAddress != null){
                checkAddress(userAddress);
            }
        }


    }

    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote Method that used to simply validate user address related information
     * @param address the user address to check for validity
     * @throws InvalidUserException if there is character other than
     * <code>A-Z a-z 0-9 whitespace comma colon semicolon first bracket(open & close) & </code>
     * or user Address length is not between 5 - 200 character
     */
    private void checkAddress(String address) throws InvalidUserException {

        //Create address Pattern
        Pattern addressPattern = Pattern.compile("^[A-Za-z\s\\d-_,.():&]*$");
        Matcher matcher = addressPattern.matcher(address);

        // Contains invalid character
        if (!matcher.matches()) {
            throw new InvalidUserException("The user address contains invalid character, allowed character is > A-Z a-z 0-9 whitespace comma colon semicolon first bracket(open & close) & <");
        }

    }


    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote Method to validate user country code or name, which should contains alphabets only.
     *
     * @throws InvalidUserException if the country code or name has invalid character,
     * or it's name length is not between 2 - 50 character.
     */
    private void validateUserCountryName() throws InvalidUserException{

        String country = userToValidate.getUserCountryName();

        if(country == null || country.length() < 2 || country.length() > 50){
            throw new InvalidUserException("The country name or code length is too short or too long!");
        }
        // Create country name matching pattern
        Pattern countryNamePattern = Pattern.compile("^[A-Za-z]*$");
        Matcher matcher = countryNamePattern.matcher(country);

        // Country name/code has invalid character
        if(!matcher.matches()){

            throw new InvalidUserException("The user country name or code seems contains invalid character!");
        }

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This method used to validate user bio
     * For more information see {@link UserValidation#validateUserBioAndDetails(String, int, int)}
     * @throws InvalidUserException if the input string is null or the length is not between 2-100 character
     */
    private void validateUserBio() throws InvalidUserException {

        validateUserBioAndDetails(userToValidate().getUserBio(), 2, 100);
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This method used to validate user short description
     * For more information see {@link UserValidation#validateUserBioAndDetails(String, int, int)}
     * @throws InvalidUserException if the input string is null or the length is not between 15-500 character
     */
    private void validateUserShortDescription() throws InvalidUserException {

        validateUserBioAndDetails(userToValidate.getUserShortDescription(), 15, 500);
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote This method used to validate user hobby
     * For more information see {@link UserValidation#validateUserBioAndDetails(String, int, int)}
     * @throws InvalidUserException if the input string is null or the length is not between 3-150 character
     */
    private void validateUserHobby() throws InvalidUserException{

        String hobby = userToValidate.getUserHobby(); // Optional Field

        // Allows not null and empty Strings
        if(hobby != null && hobby.length() != 0) {

            validateUserBioAndDetails(hobby, 3, 150);
        }
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate password, and also encrypt a password if it's not encrypted by {@link BCryptPasswordEncoder}
     * For more information, see {@link UserValidation#validatePasswordAndRecoveryQuestion(String, int, int)}
     * @throws InvalidUserException if password is null, contains new line character, or length is not between 6 - 128
     */
    private void validatePassword() throws InvalidUserException {

        String password = userToValidate().getPassword();
        validatePasswordAndRecoveryQuestion(password, 6, 128);

        // Check if password encrypted with BcryptPasswordEncoder
        // If not encrypt it and assign to user object
        if(!UtilityCollection.isPasswordEncodedWithBcryptPasswordEncryptor(password)){
            userToValidate.setPassword(new BCryptPasswordEncoder().encode(password));
        }
    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to validate user secret question, and also encrypt a password if it's not encrypted by {@link BCryptPasswordEncoder}
     * For more information, see {@link UserValidation#validatePasswordAndRecoveryQuestion(String, int, int)}
     * @throws InvalidUserException if password is null, contains new line character, or length is not between 12 - 256
     */
    private void validateRecoveryQuestion() throws InvalidUserException{

        validatePasswordAndRecoveryQuestion(userToValidate().getUserRecoveryQuestion(), 12, 256);
    }

    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote This method used to validate password or recovery question length and compare it with minLimit and maxLimit,
     * Also checks for new line character, if there is one new line character
     * or the passed secretString length is less than minLimit or higher than maxLimit,
     * it should throw {@link InvalidUserException}
     * @param secretString the secret String to validate
     * @param minLimit the minimum length for secretString
     * @param maxLimit the maximum length for secretString
     * @throws InvalidUserException if the String length is lower than minLimit, or higher than maxLimit or contains new line or the secretString is null.
     */
    private void validatePasswordAndRecoveryQuestion(String secretString, int minLimit, int maxLimit) throws InvalidUserException{

        // Check for secret sting nullity and limit
        if(secretString == null || secretString.length() < minLimit || secretString.length() > maxLimit){

            throw new InvalidUserException("The password secret message is null or it is too small or too long");
        }

        // Containing linebreak is not allowed
        if (secretString.contains("\n")){
            throw new InvalidUserException("Password or recovery question seems invalid!");
        }
    }
    /**
     * @author Nurujjaman Pollob 2022
     *
     * @apiNote Method to validate a user details, like, user bio, user description, user hobby etc.
     * This method checks only for min and max range and nullity of user details
     * @param userDetails The user details need to be tested
     * @param maxCharLimit sets max char limit for a user details.
     * @param minCharLimit sets min char limit for a user details
     * @throws InvalidUserException if the passed userDetails is null, or it either character limit from maxCharLimit is crossed
     * or the userDetails has fewer character then minCharLimit
     */
    private void validateUserBioAndDetails(String userDetails, int minCharLimit, int maxCharLimit) throws InvalidUserException{

        if(userDetails == null || userDetails.length() < minCharLimit || userDetails.length() > maxCharLimit){

            throw new InvalidUserException("It seems user provided details null or it's too short or too long");
        }
    }

}
