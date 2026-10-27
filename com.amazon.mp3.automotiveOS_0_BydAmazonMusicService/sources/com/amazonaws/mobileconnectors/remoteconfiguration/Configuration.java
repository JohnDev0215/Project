package com.amazonaws.mobileconnectors.remoteconfiguration;

import java.util.Date;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public interface Configuration {
    JSONObject getAsJsonObject();

    String getAsJsonString();

    Date getTimestamp();
}
