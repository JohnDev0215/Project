package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class ConfigurationImpl implements Configuration {
    private final String json;
    private final Date timestamp;

    public ConfigurationImpl(String str) {
        this(str, null);
    }

    public ConfigurationImpl(String str, Date date) {
        if (str == null) {
            throw new NullPointerException("The JSON may not be null.");
        }
        this.json = str;
        this.timestamp = date;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Configuration
    public String getAsJsonString() {
        return this.json;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Configuration
    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Configuration
    public JSONObject getAsJsonObject() {
        try {
            return new JSONObject(this.json);
        } catch (JSONException e) {
            throw new IllegalStateException("The configuration is invalid.", e);
        }
    }
}
