/*
*Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/
package org.wso2.carbon.esb.mediator.test.script;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.automation.core.annotations.ExecutionEnvironment;
import org.wso2.carbon.automation.core.annotations.SetEnvironment;
import org.wso2.carbon.automation.core.utils.serverutils.ServerConfigurationManager;
import org.wso2.carbon.automation.utils.esb.JSONClient;
import org.wso2.carbon.esb.ESBIntegrationTest;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class GroovySetPayloadJSONTestCase extends ESBIntegrationTest {

    private final String GROOVY_JAR = "groovy-all-1.1-rc-1.jar";
    private String GROOVY_JAR_LOCATION = File.separator + "jar" + File.separator + GROOVY_JAR;

    private ServerConfigurationManager serverManager;
    private JSONClient jsonclient;

    @BeforeClass(alwaysRun = true)
    @SetEnvironment(executionEnvironments = {ExecutionEnvironment.integration_all})
    public void setEnvironment() throws Exception {
        super.init(1);
        serverManager = new ServerConfigurationManager(esbServer.getBackEndUrl());
        serverManager.copyToComponentLib(new File(getESBResourceLocation() + GROOVY_JAR_LOCATION));
        serverManager.restartGracefully();
        super.init(1);
        jsonclient = new JSONClient();
    }

    @SetEnvironment(executionEnvironments = {ExecutionEnvironment.integration_all})
    @Test(groups = {"wso2.esb", "localOnly"}, description = "Script Mediator -Run a Groovy script with setPayloadJson")
    public void testGroovySetPayloadJson() throws Exception {
        loadESBConfigurationFromClasspath("/artifacts/ESB/synapseconfig/script_mediator/groovy_script_with_setPayloadJson.xml");

        String query = "{\"key\":\"value\"}";
        String addUrl = getProxyServiceSecuredURL("MyMockProxy");
        String expectedResult = "{\"fileID\":\"89265\",\"mySiteID\":\"54571\"}";

        String actualResult = jsonclient.sendUserDefineRequest(addUrl, query).toString();

        assertEquals(actualResult, expectedResult, "Fault: value 'symbol' mismatched");
    }

    @AfterClass(alwaysRun = true)
    @SetEnvironment(executionEnvironments = {ExecutionEnvironment.integration_all})
    public void destroy() throws Exception {
        try {
            super.cleanup();
            Thread.sleep(5000);
        } finally {

            serverManager.removeFromComponentLib(GROOVY_JAR);
            serverManager.restartGracefully();
            serverManager.restoreToLastConfiguration();
            serverManager = null;
        }

    }
}
