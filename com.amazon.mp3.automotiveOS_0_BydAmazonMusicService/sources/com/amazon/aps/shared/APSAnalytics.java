package com.amazon.aps.shared;

import android.content.Context;
import android.util.Log;
import com.amazon.aps.shared.analytics.APSEvent;
import com.amazon.aps.shared.analytics.APSEventSeverity;
import com.amazon.aps.shared.analytics.APSEventType;
import com.amazon.aps.shared.util.APSNetworkManager;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class APSAnalytics {
    private static String apiKey = null;
    private static Context context = null;
    private static boolean isSamplingAllowed = false;
    private static String url;

    public static void init(Context context2) {
        init(context2, 1, "e9026ffd475a1a3691e6b2ce637a9b92aab1073ebf53a67c5f2583be8a804ecb");
    }

    public static void init(Context context2, int i, String str) {
        context = context2;
        setApiKey(str);
        setSamplingRate(i);
        setHttpUrl("https://prod.cm.publishers.advertising.a2z.com/logrecord/putlog");
    }

    public static void setApiKey(String str) {
        apiKey = getDefaultIfEmptyString(str, "e9026ffd475a1a3691e6b2ce637a9b92aab1073ebf53a67c5f2583be8a804ecb");
    }

    public static void setHttpUrl(String str) {
        url = getDefaultIfEmptyString(str, "https://prod.cm.publishers.advertising.a2z.com/logrecord/putlog");
    }

    public static String getHttpUrl() {
        return url;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setSamplingRate(int i) {
        boolean z = true;
        if (i < 0 || i > 100) {
            Log.e("APSAnalytics", "Invalid sampling rate - setting the default one");
            i = 1;
        }
        try {
            if (new Random().nextInt(100) + 1 > i) {
                z = false;
            }
            isSamplingAllowed = z;
        } catch (RuntimeException e) {
            Log.e("APSAnalytics", "Unable to set the sampling rate", e);
        }
    }

    public static void logEvent(APSEventSeverity aPSEventSeverity, APSEventType aPSEventType, String str, Exception exc) {
        try {
            if (isInitialized()) {
                processEvent(new APSEvent(context, aPSEventSeverity, aPSEventType.name()).setExceptionDetails(exc).setErrorDetails(str).build());
            } else {
                Log.d("APSAnalytics", "Analytics not initialized, and ignoring the event");
            }
        } catch (RuntimeException e) {
            Log.e("APSAnalytics", "Error in processing the event: ", e);
        }
    }

    private static void processEvent(APSEvent aPSEvent) {
        if (aPSEvent.getEventSeverity() == APSEventSeverity.FATAL) {
            processCriticalEvent(aPSEvent);
        }
    }

    public static boolean isInitialized() {
        return context != null && isSamplingAllowed;
    }

    private static void processCriticalEvent(APSEvent aPSEvent) {
        APSNetworkManager.getInstance(context).uploadEvent(aPSEvent);
    }

    public static String getDefaultIfEmptyString(String str, String str2) {
        return (str == null || str.trim().isEmpty()) ? str2 : str;
    }
}
