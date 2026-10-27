package com.amazon.music.account;

import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class DeviceAuthorizationsSerializer {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceAuthorizationsSerializer.class.getSimpleName());

    DeviceAuthorizationsSerializer() {
    }

    public String serialize(DeviceAuthorizations deviceAuthorizations) {
        Validate.notNull(deviceAuthorizations);
        try {
            return deviceAuthorizationsToJson(deviceAuthorizations).toString();
        } catch (JSONException e) {
            LOG.error("Unable to serialize DeviceAuthorizations", (Throwable) e);
            return null;
        }
    }

    public DeviceAuthorizations deserialize(String str) {
        Validate.notNull(str);
        try {
            return jsonToDeviceAuthorizations(new JSONObject(str));
        } catch (JSONException e) {
            LOG.error("Unable to deserialize DeviceAuthorizations", (Throwable) e);
            return null;
        }
    }

    private JSONObject deviceAuthorizationsToJson(DeviceAuthorizations deviceAuthorizations) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("remainingLibraryDeviceAuthorizations", deviceAuthorizations.getRemainingLibraryDeviceAuthorizations());
        jSONObject.put("remainingCatalogDeviceAuthorizations", deviceAuthorizations.getRemainingCatalogDeviceAuthorizations());
        return jSONObject;
    }

    private DeviceAuthorizations jsonToDeviceAuthorizations(JSONObject jSONObject) throws JSONException {
        return new DeviceAuthorizations(jSONObject.getInt("remainingLibraryDeviceAuthorizations"), jSONObject.getInt("remainingCatalogDeviceAuthorizations"));
    }
}
