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

import com.mongodb.*;
import com.nurujjamanpollob.fourkcommonlib.exception.MongoConnectionException;

import java.util.Arrays;
import java.util.List;

// TODO - This class missing Unit test, documentation, some constructor parameters
public class MongoServerConnectionChecker {


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

    private MongoAuthMechanism mongoAuthMechanism;

    private boolean useSSL = false;


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Suppress default constructor
     */
    private MongoServerConnectionChecker() {
    }

    /**
     * @param mongoURL the mongodb URL, for example <pre><code>localhost</code></pre>
     * @author Nurujjaman Pollob 2022
     * @apiNote This construct param used to create and configure {@link com.mongodb.client.MongoClient} instance with a server address,
     * Using default port setting. No auth is used.
     */
    public MongoServerConnectionChecker(String mongoURL) {

        this.isUseAuthentication = false;
        this.connectionStrategy = ConnectionStrategy.SIMPLE_URL;
        this.mongoURL = mongoURL;
        useSSL = false;

    }


    /**
     * @param connectionServerAddresses the {@link ServerAddress} array, for example {@link ServerAddress#ServerAddress(String, int)} array
     * @author Nurujjaman Pollob 2022
     * @apiNote This construct param used to create and configure {@link com.mongodb.client.MongoClient} instance with several server connection.
     * No auth is used.
     */
    public MongoServerConnectionChecker(ServerAddress[] connectionServerAddresses) {

        this.isUseAuthentication = false;
        this.connectionStrategy = ConnectionStrategy.MULTIPLE_URL;
        this.serverAddresses = connectionServerAddresses;
        useSSL = false;

    }

    /**
     * @param mongoURL        the mongodb URL, for example <pre><code>localhost</code></pre>
     * @param mongoServerPort the server port, for example <pre><code>27017</code></pre>
     * @author Nurujjaman Pollob 2022
     * @apiNote This construct param used to create and configure {@link com.mongodb.client.MongoClient} instance with a server address
     * and customized port setting
     */
    public MongoServerConnectionChecker(String mongoURL, Integer mongoServerPort) {

        this.isUseAuthentication = false;
        this.connectionStrategy = ConnectionStrategy.URL_AND_PORT;
        this.mongoURL = mongoURL;
        this.serverPort = mongoServerPort;
        useSSL = false;
    }

    /**
     * @param mongoURL        the mongodb server URL, for instance <pre><code>localhost</code></pre>
     * @param mongoServerPort the mongodb server port, for example <pre><code>27017</code></pre>
     * @param userName        the database or server username
     * @param password        the database or server password
     * @param databaseName    the database name to check connection for
     * @param authMechanism   the auth mechanism name to create instance for a {@link MongoCredential}. <br />
     *                        <b>Note:</b> If you use {@link MongoAuthMechanism#GSSAPI}, you may need to set following JVM system properties: <br />
     *                        <ul>
     *                        <li>java.security.krb5.realm=MYREALM.ME</li>
     *                        <li>java.security.krb5.kdc=mykdc.myrealm.me</li>
     *                        </ul>
     * @author Nurujjaman Pollob 2022
     * @apiNote This construct param used to create and configure {@link com.mongodb.client.MongoClient} instance with a server address
     * and port setting.
     * This parameter using {@link MongoCredential} to generate credential for {@link com.mongodb.client.MongoClient} instance and auth with mongodb server.
     */
    public MongoServerConnectionChecker(String mongoURL, Integer mongoServerPort, String userName, String password, String databaseName, MongoAuthMechanism authMechanism) {

        this.isUseAuthentication = true;
        this.connectionStrategy = ConnectionStrategy.URL_AND_PORT;
        this.mongoURL = mongoURL;
        this.serverPort = mongoServerPort;
        this.mongoServerUserName = userName;
        this.mongoServerPassword = password;
        this.mongoDatabaseName = databaseName;
        this.mongoAuthMechanism = authMechanism;

        if (authMechanism == MongoAuthMechanism.X_509) {
            useSSL = true;
        }
    }

    /**
     * @param mongoURL      the mongodb server URL, for instance <pre><code>localhost</code></pre>
     * @param userName      the database or server username
     * @param password      the database or server password
     * @param databaseName  the database name to check connection for
     * @param authMechanism the auth mechanism name to create instance for a {@link MongoCredential}. <br />
     *                      <b>Note:</b> If you use {@link MongoAuthMechanism#GSSAPI}, you may need to set following JVM system properties: <br />
     *                      <ul>
     *                      <li>java.security.krb5.realm=MYREALM.ME</li>
     *                      <li>java.security.krb5.kdc=mykdc.myrealm.me</li>
     *                      </ul>
     * @author Nurujjaman Pollob 2022
     * @apiNote This construct param used to create and configure {@link com.mongodb.client.MongoClient} instance with a server address, and without a preferred port setting.
     * This parameter using {@link MongoCredential} to generate credential for {@link com.mongodb.client.MongoClient} instance and auth with mongodb server.
     */

    public MongoServerConnectionChecker(String mongoURL, String userName, String password, String databaseName, MongoAuthMechanism authMechanism) {

        this.isUseAuthentication = true;
        this.connectionStrategy = ConnectionStrategy.SIMPLE_URL;
        this.mongoURL = mongoURL;
        this.mongoServerUserName = userName;
        this.mongoServerPassword = password;
        this.mongoDatabaseName = databaseName;
        this.mongoAuthMechanism = authMechanism;

        if (authMechanism == MongoAuthMechanism.X_509) {
            useSSL = true;
        }
    }

    /**
     * @param serverAddresses the {@link ServerAddress} array, for example {@link ServerAddress#ServerAddress(String, int)} array
     * @param userName        the database or server username
     * @param password        the database or server password
     * @param databaseName    the database name to check connection for
     * @param authMechanism   the auth mechanism name to create instance for a {@link MongoCredential}. <br />
     *                        <b>Note:</b> If you use {@link MongoAuthMechanism#GSSAPI}, you may need to set following JVM system properties: <br />
     *                        <ul>
     *                        <li>java.security.krb5.realm=MYREALM.ME</li>
     *                        <li>java.security.krb5.kdc=mykdc.myrealm.me</li>
     *                        </ul>
     * @author Nurujjaman Pollob 2022
     * @apiNote This construct param used to create and configure {@link com.mongodb.client.MongoClient} instance with a list of server addresses from {@link ServerAddress}
     * This parameter using {@link MongoCredential} to generate credential for {@link com.mongodb.client.MongoClient} instance and auth with mongodb server
     */
    public MongoServerConnectionChecker(ServerAddress[] serverAddresses, String userName, String password, String databaseName, MongoAuthMechanism authMechanism) {

        this.isUseAuthentication = true;
        this.connectionStrategy = ConnectionStrategy.MULTIPLE_URL;
        this.serverAddresses = serverAddresses;
        this.mongoServerUserName = userName;
        this.mongoServerPassword = password;
        this.mongoDatabaseName = databaseName;
        this.mongoAuthMechanism = authMechanism;

        if (authMechanism == MongoAuthMechanism.X_509) {
            useSSL = true;
        }
    }


    /**
     * @throws MongoConnectionException when you pass a null value for this method {@link MongoServerConnectionChecker#setServerConnectionListener(MongoServerConnectionListener)}
     *                                  or, you forget to pass a {@link MongoServerConnectionListener} instance, or you pass a value to {@link MongoServerConnectionChecker#setServerConnectionListener(MongoServerConnectionListener)} after invoke this method,
     *                                  which is unchecked and treated the listener as null.
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to invoke MongoDB connection test.
     */
    public void invokeConnectionTest() throws MongoConnectionException {

        // Set up client option
        if (serverConnectionListener != null) {
            initializeConnectionListener();
        } else {

            // Listener is null, throw exception
            throw new MongoConnectionException("Is seems you are trying to test server connection, without setting up listener.");
        }

        try {

            // Initialize Mongo Client
            client = initializeMongoClient();

            // Start client session
            client.startSession();

        } catch (Exception e) {
            // end connection if threw exception
            endMongoConnection();
            throw new MongoConnectionException(e.getMessage());
        }

    }

    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method that construct and create an instance of {@link MongoClientOptions} and set up connection listener
     */
    private void initializeConnectionListener() {

        // Set up mongo client option with listener
        clientOptions = new MongoClientOptions.Builder()
                .sslEnabled(useSSL)
                .addServerMonitorListener(serverConnectionListener)
                .build();
    }

    /**
     * @param mongoServerConnectionListener the {@link MongoServerConnectionListener} instance to receive network connection callback.
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to set a {@link MongoServerConnectionListener} instance to receive network connection callback.
     */
    public void setServerConnectionListener(MongoServerConnectionListener mongoServerConnectionListener) {

        this.serverConnectionListener = mongoServerConnectionListener;
    }

    /**
     * @return {@link com.mongodb.client.MongoClient} from {@link MongoServerConnectionChecker#initializeMongoConnectionWithAuth()}
     * if {@link MongoServerConnectionChecker#isUseAuthentication} true, else {@link MongoServerConnectionChecker#initializeMongoConnectionWithoutAuth()} is returned.
     * @author Nurujjaman Pollob 2022
     * @apiNote Method that return a {@link com.mongodb.client.MongoClient} instance, according to constructor param call.
     * @see MongoServerConnectionChecker#initializeMongoConnectionWithAuth()
     * @see MongoServerConnectionChecker#initializeMongoConnectionWithoutAuth()
     */
    private MongoClient initializeMongoClient() {

        if (isUseAuthentication) {

            // Initialize MongoClient instance with auth
            return initializeMongoConnectionWithAuth();
        }
        // Initialize MongoClient instance without auth
        return initializeMongoConnectionWithoutAuth();
    }

    /**
     * @return {@link MongoClient}
     * @author Nurujjaman Pollob 2022
     * @apiNote This method return a {@link MongoClient}, the parameter is depending on {@link ConnectionStrategy} values.
     * This is obviously usages no auth
     */
    private MongoClient initializeMongoConnectionWithoutAuth() {


        // Create MongoClient with mongo connect url and using default port
        if (connectionStrategy == ConnectionStrategy.SIMPLE_URL) {
            return new MongoClient(mongoURL, clientOptions);
        }

        // Create MongoClient with mongo connect url and server port
        if (connectionStrategy == ConnectionStrategy.URL_AND_PORT) {
            return new MongoClient(new ServerAddress(mongoURL, serverPort), clientOptions);
        }

        // Create MongoClient with list of server addresses
        return new MongoClient(Arrays.asList(serverAddresses), clientOptions);
    }

    /**
     * @return {@link MongoClient} with credential get from constructor to use a {@link MongoCredential} for authorization with mongodb server.
     * @author Nurujjaman Pollob 2022
     * @apiNote This method return a {@link MongoClient}, the parameter is depending on {@link ConnectionStrategy} and {@link MongoAuthMechanism} <br />
     * In created {@link com.mongodb.client.MongoClient} instance, It usages this list of {@link MongoCredential} instance, to generate this instance.
     * <br />
     * <ul>
     *     <li>{@link MongoCredential#createCredential(String, String, char[])}</li>
     *     <li>{@link MongoCredential#createScramSha1Credential(String, String, char[])}</li>
     *     <li>{@link MongoCredential#createScramSha256Credential(String, String, char[])}</li>
     *     <li>{@link MongoCredential#createMongoCRCredential(String, String, char[])}</li>
     *     <li>{@link MongoCredential#createMongoX509Credential(String)}</li>
     *     <li>{@link MongoCredential#createGSSAPICredential(String)}</li>
     *     <li>{@link MongoCredential#createPlainCredential(String, String, char[])}</li>
     * </ul>
     * <br />
     */
    private MongoClient initializeMongoConnectionWithAuth() {

        // Default auth mechanism
        if (mongoAuthMechanism == MongoAuthMechanism.DEFAULT) {

            // Create auth mechanism
            MongoCredential credential = MongoCredential.createCredential(mongoServerUserName, mongoDatabaseName, mongoServerPassword.toCharArray());

            // Return client
            return createAuthMongoClientAccordingToConnectionStrategy(credential);
        }

        // SCRAM-SHA-1 auth mechanism
        if (mongoAuthMechanism == MongoAuthMechanism.SCRAM_SHA_1) {

            // Create auth mechanism
            MongoCredential credential = MongoCredential.createScramSha1Credential(mongoServerUserName, mongoDatabaseName, mongoServerPassword.toCharArray());

            // Return client
            return createAuthMongoClientAccordingToConnectionStrategy(credential);
        }

        // SCRAM-SHA-256 auth mechanism
        if (mongoAuthMechanism == MongoAuthMechanism.SCRAM_SHA_256) {

            // Create auth mechanism
            MongoCredential credential = MongoCredential.createScramSha256Credential(mongoServerUserName, mongoDatabaseName, mongoServerPassword.toCharArray());

            // Return client
            return createAuthMongoClientAccordingToConnectionStrategy(credential);
        }

        // MongoDB-CR auth mechanism
        if (mongoAuthMechanism == MongoAuthMechanism.MONGODB_CR) {

            // Create auth mechanism
            MongoCredential credential = MongoCredential.createMongoCRCredential(mongoServerUserName, mongoDatabaseName, mongoServerPassword.toCharArray());

            // Return client
            return createAuthMongoClientAccordingToConnectionStrategy(credential);

        }

        // X.509 auth mechanism
        if (mongoAuthMechanism == MongoAuthMechanism.X_509) {


            // Create auth mechanism
            MongoCredential credential = MongoCredential.createMongoX509Credential(mongoServerUserName);

            // Return client
            return createAuthMongoClientAccordingToConnectionStrategy(credential);

        }

        // Kerberos (GSSAPI) auth mechanism
        if (mongoAuthMechanism == MongoAuthMechanism.GSSAPI) {
            // Create auth mechanism
            MongoCredential credential = MongoCredential.createGSSAPICredential(mongoServerUserName);

            // Return client
            return createAuthMongoClientAccordingToConnectionStrategy(credential);
        }

        MongoCredential credential = MongoCredential.createPlainCredential(mongoServerUserName, mongoDatabaseName, mongoServerPassword.toCharArray());

        // LDAP auth mechanism
        return createAuthMongoClientAccordingToConnectionStrategy(credential);


    }

    /**
     * @param mongoCredential the {@link MongoCredential} instance to contract with {@link com.mongodb.client.MongoClient}
     * @return {@link com.mongodb.client.MongoClient}
     * @author Nurujjaman Pollob 2022
     * @apiNote Create a {@link com.mongodb.client.MongoClient} instance with a passed {@link MongoCredential} instance,
     * and the generated {@link com.mongodb.client.MongoClient} is depending on {@link ConnectionStrategy} enum values to generate an accurate instance,
     * based on passed constructor method.
     */
    private MongoClient createAuthMongoClientAccordingToConnectionStrategy(MongoCredential mongoCredential) {

        if (connectionStrategy == ConnectionStrategy.URL_AND_PORT) {

            // Return client using customized port option
            return new MongoClient(new ServerAddress(mongoURL, serverPort), mongoCredential, clientOptions);
        }

        if (connectionStrategy == ConnectionStrategy.SIMPLE_URL) {

            // Return client using default port
            return new MongoClient(new ServerAddress(mongoURL), mongoCredential, clientOptions);
        }

        // Return client instance with multiple server address option
        return new MongoClient(List.of(serverAddresses), mongoCredential, clientOptions);

    }


    /**
     * @author Nurujjaman Pollob 2022
     * @apiNote Method to end a running {@link MongoClient} instance, if it's not null
     */
    public void endMongoConnection() {

        if (client != null) {
            client.close();
        }
    }


}
