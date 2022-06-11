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

package com.nurujjamanpollob.fourkpixelbackend;

import com.mongodb.event.ServerHeartbeatFailedEvent;
import com.mongodb.event.ServerHeartbeatStartedEvent;
import com.mongodb.event.ServerHeartbeatSucceededEvent;
import com.nurujjamanpollob.fourkcommonlib.exception.MongoConnectionException;
import com.nurujjamanpollob.fourkcommonlib.mongoserver.MongoServerConnectionChecker;
import com.nurujjamanpollob.fourkcommonlib.mongoserver.MongoServerConnectionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FourKPixelBackendApplication {

    public static void main(String[] args) throws MongoConnectionException {


        MongoServerConnectionChecker connectionChecker = new MongoServerConnectionChecker("localhost", 27017);

        connectionChecker.setServerConnectionListener(new MongoServerConnectionListener() {
            /**
             * Listener for server heartbeat started events.
             *
             * @param event the server heartbeat started event
             */
            @Override
            public void serverHearbeatStarted(ServerHeartbeatStartedEvent event) {

                System.out.println("Please wait a while, connection test to database server is on way.");

            }

            /**
             * Listener for server heartbeat succeeded events.
             *
             * @param event the server heartbeat succeeded event
             */
            @Override
            public void serverHeartbeatSucceeded(ServerHeartbeatSucceededEvent event) {
                System.out.println("Server is up and running...");
                SpringApplication.run(FourKPixelBackendApplication.class, args);
            }

            /**
             * Listener for server heartbeat failed events.
             *
             * @param event the server heartbeat failed event
             */
            @Override
            public void serverHeartbeatFailed(ServerHeartbeatFailedEvent event) {

                System.out.println("Database server is not running now, so the application unable to run!");
            }
        });


        connectionChecker.invokeConnectionTest();


    }

}
