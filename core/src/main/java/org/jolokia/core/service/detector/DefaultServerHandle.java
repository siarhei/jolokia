package org.jolokia.core.service.detector;

/*
 * Copyright 2009-2013 Roland Huss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Map;

import org.jolokia.core.request.JolokiaRequest;
import org.jolokia.core.util.jmx.MBeanServerExecutor;
import org.json.simple.JSONObject;

/**
 * Information about the the server product the agent is running in.
 *
 * @author roland
 * @since 05.11.10
 */
public class DefaultServerHandle implements ServerHandle {

    // product name of server running
    private String product;

    // version number
    private String version;

    // vendor name
    private String vendor;

    /**
     * Constructor
     *
     * @param vendor product vendor (like RedHat or Oracle)
     * @param product name of the product
     * @param version version
     */
    public DefaultServerHandle(String vendor, String product, String version) {
        this.product = product;
        this.version = version;
        this.vendor = vendor;
    }

    public String getVendor() {
        return vendor;
    }

    public String getProduct() {
        return product;
    }

    public String getVersion() {
        return version;
    }

    public void preDispatch(MBeanServerExecutor pExecutor, JolokiaRequest pJmxReq) {
        // Do nothing
    }

    public JSONObject toJSONObject() {
        JSONObject ret = new JSONObject();
        addNullSafe(ret, "vendor", vendor);
        addNullSafe(ret, "product", product);
        addNullSafe(ret, "version", version);
        return ret;
    }

    private void addNullSafe(JSONObject pRet, String pKey, Object pValue) {
        if (pValue != null) {
            pRet.put(pKey,pValue);
        }
    }

    public Map<String, String> getExtraInfo(MBeanServerExecutor pServerManager) {
        return null;
    }
}
