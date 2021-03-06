package org.wso2.carbon.mediator.fault.test;

/*
*  Copyright (c) WSO2 Inc. (http://wso2.com) All Rights Reserved.

  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*
*/

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestRunner extends TestSuite {

    public static Test suite() throws Exception {
        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(Soap11ActorsTest.class);
        testSuite.addTestSuite(Soap11DetailsTest.class);
        testSuite.addTestSuite(Soap11ExpStringTest.class);
        testSuite.addTestSuite(Soap11FCodeClientTest.class);
        testSuite.addTestSuite(Soap11FCodeMustundTest.class);
        testSuite.addTestSuite(Soap11FCodeServerTest.class);
        testSuite.addTestSuite(Soap11FCodeVrTest.class);
        testSuite.addTestSuite(Soap11ValStringTest.class);
        testSuite.addTestSuite(Soap12DetailsTest.class);
        testSuite.addTestSuite(Soap12ExpReason.class);
        testSuite.addTestSuite(Soap12FCodeEncodeTest.class);
        testSuite.addTestSuite(Soap12FCodeMustund.class);
        testSuite.addTestSuite(Soap12FCodeReceiverTest.class);
        testSuite.addTestSuite(Soap12FCodeSenderTest.class);
        testSuite.addTestSuite(Soap12FCodeVrTest.class);
        testSuite.addTestSuite(Soap12RoleTest.class);
        testSuite.addTestSuite(Soap12ValReasonTest.class);


        return testSuite;
    }
}
