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

package com.nurujjamanpollob.fourkcommonlib.model;

import com.nurujjamanpollob.fourkcommonlib.utility.UtilityCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collation = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    private String userName;
    private String password;
    private Integer userBirthYear;
    private Integer userBirthMonth;
    private Integer userBirthDay;
    private String userRecoveryQuestion;
    private Boolean isUserActive;
    private Integer userCreationTime;
    private String userCityName;
    private String userAddressLineOne;
    private String userAddressLineTwo;
    private String userCountryName;
    private boolean isBusiness;
    private boolean isPremium;
    private String userFirstName;
    private String userLastname;
    private String userHobby;
    private boolean isTwoFactorEnabled;
    private String userBio;
    private String userShortDescription;
    @Id
    private Integer userId;



    /**
     * @author Nurujjaman Pollob
     * Constructor parameter to create a user and save in No-SQL database
     */
    @SuppressWarnings({"unused"})
    public User(
            String userName,
            String password,
            String firstName,
            String lastName,
            String addressLineOne,
            String addressLineTwo,
            String city,
            String country,
            String userShortDescription,
            String userBio,
            String hobby,
            Integer userId,
            Integer birthYear,
            Integer birthMonth,
            Integer birthDay,
            Integer userCreationTime,
            Boolean isBusiness,
            Boolean isPremium,
            Boolean isTwoFactorEnabled,
            Boolean isUserActive
            ){

        this.userName = userName;
        this.password = password;
        this.userFirstName = firstName;
        this.userLastname = lastName;
        this.userAddressLineOne = addressLineOne;
        this.userAddressLineTwo = addressLineTwo;
        this.userCityName = city;
        this.userCountryName = country;
        this.userShortDescription = userShortDescription;
        this.userBio = userBio;
        this.userHobby = hobby;
        this.userBirthDay = birthDay;
        this.userBirthMonth = birthMonth;
        this.userBirthYear = birthYear;
        this.userCreationTime = userCreationTime;
        this.isBusiness = isBusiness;
        this.isPremium = isPremium;
        this.isTwoFactorEnabled = isTwoFactorEnabled;
        this.isUserActive = isUserActive;
        /*
        MongoBd do not generate automatic ID,
        so our objective is to convert username to Big Integer,
        and get its Integer Value
        So that it will be able to save a lot of database operations, increment and insertion,
        which is not optimal for performance.

        Using UTC time Integer conversion may lead to duplicated ID, if user creating at the same time.
         */
       this.userId = UtilityCollection.stringToBigIntegerConverter(userName).intValue();

    }
}
