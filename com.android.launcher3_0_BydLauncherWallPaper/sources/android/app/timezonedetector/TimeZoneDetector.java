package android.app.timezonedetector;

/* JADX INFO: loaded from: classes.dex */
public interface TimeZoneDetector {
    void suggestManualTimeZone(ManualTimeZoneSuggestion manualTimeZoneSuggestion);

    void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion);

    static ManualTimeZoneSuggestion createManualTimeZoneSuggestion(String str, String str2) {
        ManualTimeZoneSuggestion manualTimeZoneSuggestion = new ManualTimeZoneSuggestion(str);
        manualTimeZoneSuggestion.addDebugInfo(str2);
        return manualTimeZoneSuggestion;
    }
}
