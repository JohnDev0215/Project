package android.app.timedetector;

import android.os.SystemClock;
import android.os.TimestampedValue;

/* JADX INFO: loaded from: classes.dex */
public interface TimeDetector {
    void suggestManualTime(ManualTimeSuggestion manualTimeSuggestion);

    void suggestNetworkTime(NetworkTimeSuggestion networkTimeSuggestion);

    void suggestTelephonyTime(TelephonyTimeSuggestion telephonyTimeSuggestion);

    static ManualTimeSuggestion createManualTimeSuggestion(long j, String str) {
        ManualTimeSuggestion manualTimeSuggestion = new ManualTimeSuggestion(new TimestampedValue(SystemClock.elapsedRealtime(), Long.valueOf(j)));
        manualTimeSuggestion.addDebugInfo(str);
        return manualTimeSuggestion;
    }
}
