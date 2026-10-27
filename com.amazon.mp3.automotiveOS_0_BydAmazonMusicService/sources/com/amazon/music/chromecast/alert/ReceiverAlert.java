package com.amazon.music.chromecast.alert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.Enum;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ReceiverAlert<T extends Enum<T>> {

    @Nullable
    private final JSONObject jsonAttributes;
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    protected ReceiverAlert(@NonNull JSONObject jSONObject) throws InstantiationException {
        JSONObject jSONObject2;
        if (jSONObject.has("attributes")) {
            try {
                jSONObject2 = jSONObject.getJSONObject("attributes");
            } catch (JSONException e) {
                this.logger.error("Alert JSON is not parsable", (Throwable) e);
                throw new InstantiationException();
            }
        } else {
            jSONObject2 = null;
        }
        this.jsonAttributes = jSONObject2;
    }

    @Nullable
    public String getAttribute(@NonNull T t) {
        JSONObject jSONObject = this.jsonAttributes;
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.getString(t.toString());
        } catch (JSONException e) {
            this.logger.error("Alert JSON is not parsable", (Throwable) e);
            return null;
        }
    }
}
