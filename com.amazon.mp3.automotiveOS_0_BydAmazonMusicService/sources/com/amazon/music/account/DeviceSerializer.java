package com.amazon.music.account;

import com.amazon.music.account.Device;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.commons.lang3.Validate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class DeviceSerializer {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceSerializer.class.getSimpleName());

    DeviceSerializer() {
    }

    public String serialize(Device device) {
        Validate.notNull(device);
        try {
            return deviceToJson(device).toString();
        } catch (JSONException e) {
            LOG.error("Unable to serialize Device", (Throwable) e);
            return null;
        }
    }

    public Device deserialize(String str) {
        Validate.notNull(str);
        try {
            return jsonToDevice(new JSONObject(str));
        } catch (JSONException e) {
            LOG.error("Unable to deserialize Device", (Throwable) e);
            return null;
        }
    }

    private JSONObject deviceToJson(Device device) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Device.AuthorizationState authorizationState = device.getAuthorizationState();
        jSONObject.putOpt("authorizationState", authorizationState != null ? authorizationState.name() : null);
        Device.AvailableState availableState = device.getAvailableState();
        jSONObject.putOpt("availableState", availableState != null ? availableState.name() : null);
        JSONArray jSONArray = new JSONArray();
        Iterator<Device.Capability> it = device.getCapabilities().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().name());
        }
        jSONObject.put("capabilities", jSONArray);
        return jSONObject;
    }

    private Device jsonToDevice(JSONObject jSONObject) throws JSONException {
        String strOptString = jSONObject.optString("authorizationState", null);
        Device.AuthorizationState authorizationStateValueOf = strOptString != null ? Device.AuthorizationState.valueOf(strOptString) : null;
        String strOptString2 = jSONObject.optString("availableState", null);
        Device.AvailableState availableStateValueOf = strOptString2 != null ? Device.AvailableState.valueOf(strOptString2) : null;
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("capabilities");
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(Device.Capability.valueOf(jSONArray.getString(i)));
        }
        return new Device(authorizationStateValueOf, availableStateValueOf, hashSet);
    }
}
