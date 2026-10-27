package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.NetworkDetailsCollector;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkDetailsFactory {

    @NonNull
    private Context appContext;

    public NetworkDetailsFactory(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("appContext is null");
        }
        this.appContext = context;
    }

    public Map<String, String> create() {
        HashMap map = new HashMap();
        try {
            SharedPreferences sharedPreferences = this.appContext.getSharedPreferences("com.amazon.alexa.comms.mediaInsights.service.NETWORK_DETAILS", 0);
            for (NetworkDetailsCollector.NetworkDetailKeys networkDetailKeys : (NetworkDetailsCollector.NetworkDetailKeys[]) NetworkDetailsCollector.NetworkDetailKeys.class.getEnumConstants()) {
                String string = networkDetailKeys.toString();
                String string2 = sharedPreferences.getString(string, null);
                if (string2 != null) {
                    map.put(string, string2);
                }
            }
        } catch (Throwable th) {
            Log.d("MediaInsightsPublisher", "Failed to read network details from shared prefs", th);
        }
        return map;
    }
}
