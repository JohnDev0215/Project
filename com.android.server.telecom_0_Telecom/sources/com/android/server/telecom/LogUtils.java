package com.android.server.telecom;

import android.content.Context;
import android.os.SystemClock;
import android.telecom.Log;
import android.telecom.Logging.EventManager;
import android.telecom.Logging.SessionManager;
import com.android.server.telecom.Analytics;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {

    public static class EventTimer {
        private Map<String, Long> mTimings = new HashMap();
        private long mLastElapsedMillis = SystemClock.elapsedRealtime();

        public void record(String str) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.mTimings.put(str, Long.valueOf(jElapsedRealtime - this.mLastElapsedMillis));
            this.mLastElapsedMillis = jElapsedRealtime;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Long> entry : this.mTimings.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append(", ");
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void eventRecordAdded(EventManager.EventRecord eventRecord) {
        EventManager.Loggable recordEntry = eventRecord.getRecordEntry();
        if (recordEntry instanceof Call) {
            Call call = (Call) recordEntry;
            Log.i("LogUtils", "EventRecord added as Call: " + call, new Object[0]);
            Analytics.CallInfo analytics = call.getAnalytics();
            if (analytics != null) {
                analytics.setCallEvents(eventRecord);
                return;
            } else {
                Log.w("LogUtils", "Could not get Analytics CallInfo.", new Object[0]);
                return;
            }
        }
        Log.w("LogUtils", "Non-Call EventRecord Added.", new Object[0]);
    }

    public static void initLogging(Context context) {
        Log.setTag("Telecom");
        Log.setSessionContext(context);
        for (EventManager.TimedEventPair timedEventPair : LogUtils$Events$Timings.sTimedEvents) {
            Log.addRequestResponsePair(timedEventPair);
        }
        Log.registerEventListener(new EventManager.EventListener() { // from class: com.android.server.telecom.-$$Lambda$LogUtils$ovQ4D3SE6-h8eyzg02OdbD1Yi80
            public final void eventRecordAdded(EventManager.EventRecord eventRecord) {
                LogUtils.eventRecordAdded(eventRecord);
            }
        });
        Log.registerSessionListener(new SessionManager.ISessionListener() { // from class: com.android.server.telecom.-$$Lambda$B19P5WklEz49TOttkdLnpKFWR3w
            public final void sessionComplete(String str, long j) {
                Analytics.addSessionTiming(str, j);
            }
        });
    }
}
