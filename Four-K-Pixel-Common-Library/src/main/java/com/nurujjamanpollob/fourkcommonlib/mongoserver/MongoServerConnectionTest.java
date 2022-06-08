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

package com.nurujjamanpollob.fourkcommonlib.mongoserver;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.nurujjamanpollob.fourkcommonlib.exception.MongoConnectionException;
import com.nurujjamanpollob.fourkcommonlib.mongoserver.MongoServerConnectionListener;

// TODO - This class under writing, it needs attention!
public class MongoServerConnectionTest {


    private MongoServerConnectionListener serverConnectionListener;
    private MongoClient client;
    private MongoClientOptions clientOptions;
    private String mongoURL;
    private Integer serverPort;
    private ServerAddress[] serverAddresses;
    private ConnectionStrategy connectionStrategy;
    private String mongoServerUserName;
    private String mongoServerPassword;
    private String mongoDatabaseName;
    private Boolean isUseAuthentication = false;


    /**
     * @apiNote Suppress default constructor
     */
    private MongoServerConnectionTest() {
    }

    public MongoServerConnectionTest(String mongoURL) {

        this.isUseAuthentication = false;
        this.connectionStrategy = mongoURL.contains("mongodb://") ? ConnectionStrategy.CUSTOM_URL : ConnectionStrategy.SIMPLE_URL;
        this.mongoURL = mongoURL;

    }

    public MongoServerConnectionTest(String mongoURL, String userName, String password, String databaseName) {

        this.isUseAuthentication = true;
        this.connectionStrategy = mongoURL.contains("mongodb://") ? ConnectionStrategy.CUSTOM_URL : ConnectionStrategy.SIMPLE_URL;
        this.mongoURL = mongoURL;
        this.mongoServerUserName = userName;
        this.mongoServerPassword = password;
        this.mongoDatabaseName = databaseName;

    }

    public MongoServerConnectionTest(ServerAddress[] connectionServerAddresses) {

        this.isUseAuthentication = false;
        this.connectionStrategy = ConnectionStrategy.MULTIPLE_URL;
        this.serverAddresses = connectionServerAddresses;

    }

    public MongoServerConnectionTest(ServerAddress[] connectionServerAddresses, String userName, String password, String databaseName) {

        this.isUseAuthentication = true;
        this.connectionStrategy = ConnectionStrategy.MULTIPLE_URL;
        this.serverAddresses = connectionServerAddresses;
        this.mongoServerUserName = userName;
        this.mongoServerPassword = password;
        this.mongoDatabaseName = databaseName;

    }

    public MongoServerConnectionTest(String mongoURL, Integer mongoServerPort) {

        this.isUseAuthentication = false;
        this.connectionStrategy = ConnectionStrategy.URL_AND_PORT;
        this.mongoURL = mongoURL;
        this.serverPort = mongoServerPort;
    }

    public MongoServerConnectionTest(String mongoURL, Integer mongoServerPort, String userName, String password, String databaseName) {

        this.isUseAuthentication = true;
        this.connectionStrategy = ConnectionStrategy.URL_AND_PORT;
        this.mongoURL = mongoURL;
        this.serverPort = mongoServerPort;
        this.mongoServerUserName = userName;
        this.mongoServerPassword = password;
        this.mongoDatabaseName = databaseName;
    }


    public void invokeConnectionTest() throws MongoConnectionException {

        // Set up client option
        if (serverConnectionListener != null) {
            initializeConnectionListener();
        } else {
            throw new MongoConnectionException("Is seems you are trying to test server connection, without setting up listener.");
        }

        try {

            client = initializeMongoClient();

        } catch (Exception e) {
            // end connection if threw exception
            endMongoConnection();
            throw new MongoConnectionException(e.getMessage());
        }

    }

    private void initializeConnectionListener() {

        clientOptions = new MongoClientOptions.Builder()
                .addServerMonitorListener(serverConnectionListener)
                .build();
    }

    public void setServerConnectionListener(MongoServerConnectionListener mongoServerConnectionListener) {

        this.serverConnectionListener = mongoServerConnectionListener;
    }

    private MongoClient initializeMongoClient() {

        return null;
    }

    public void endMongoConnection() {

        if (client != null) {

            client.close();
        }
    }


}
