package com.amazon.music.chromecast;

import androidx.annotation.Nullable;
import com.amazon.music.chromecast.alert.ReceiverAlert;
import com.amazon.music.chromecast.alert.UpdateFirmwareAlert;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastAlert {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ChromecastAlert.class);

    @Nullable
    public static ReceiverAlert<?> receiverAlertFromJSON(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("id");
            byte b = -1;
            if (string.hashCode() == -1084860865 && string.equals("update-firmware")) {
                b = 0;
            }
            if (b == 0) {
                return new UpdateFirmwareAlert(jSONObject);
            }
            LOG.error("Alert id is unknown: " + string);
            return null;
        } catch (InstantiationException | JSONException e) {
            LOG.error("Alert cannot be instantiated from JSON", e);
            return null;
        }
    }
}
