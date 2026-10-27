package com.amazon.music.account;

import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class DeviceCapabilitiesSerializer {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceCapabilitiesSerializer.class.getSimpleName());

    DeviceCapabilitiesSerializer() {
    }

    public String serialize(DeviceCapabilities deviceCapabilities) {
        Validate.notNull(deviceCapabilities);
        try {
            return deviceCapabilitiesToJson(deviceCapabilities).toString();
        } catch (JSONException e) {
            LOG.error("Unable to serialize DeviceCapabilities", (Throwable) e);
            return null;
        }
    }

    public DeviceCapabilities deserialize(String str) {
        Validate.notNull(str);
        try {
            return jsonToDeviceCapabilities(new JSONObject(str));
        } catch (JSONException e) {
            LOG.error("Unable to deserialize DeviceCapabilities", (Throwable) e);
            return null;
        }
    }

    private JSONObject deviceCapabilitiesToJson(DeviceCapabilities deviceCapabilities) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("maxCatalogDeviceAuthorizations", deviceCapabilities.getMaxCatalogDeviceAuthorizations());
        jSONObject.putOpt("deviceLosingCatalogDeviceAuthorizationNextName", deviceCapabilities.getDeviceLosingCatalogDeviceAuthorizationNextName());
        jSONObject.putOpt("deviceLosingCatalogDeviceAuthorizationNextId", deviceCapabilities.getDeviceLosingCatalogDeviceAuthorizationNextId());
        return jSONObject;
    }

    private DeviceCapabilities jsonToDeviceCapabilities(JSONObject jSONObject) throws JSONException {
        return new DeviceCapabilities(jSONObject.getInt("maxCatalogDeviceAuthorizations"), jSONObject.optString("deviceLosingCatalogDeviceAuthorizationNextName", null), jSONObject.optString("deviceLosingCatalogDeviceAuthorizationNextId", null));
    }
}
