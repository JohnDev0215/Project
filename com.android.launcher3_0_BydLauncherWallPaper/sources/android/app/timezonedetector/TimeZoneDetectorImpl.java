package android.app.timezonedetector;

import android.app.timezonedetector.ITimeZoneDetectorService;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;

/* JADX INFO: loaded from: classes.dex */
public final class TimeZoneDetectorImpl implements TimeZoneDetector {
    private static final boolean DEBUG = false;
    private static final String TAG = "timezonedetector.TimeZoneDetector";
    private final ITimeZoneDetectorService mITimeZoneDetectorService = ITimeZoneDetectorService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.TIME_ZONE_DETECTOR_SERVICE));

    @Override // android.app.timezonedetector.TimeZoneDetector
    public void suggestManualTimeZone(ManualTimeZoneSuggestion manualTimeZoneSuggestion) {
        try {
            this.mITimeZoneDetectorService.suggestManualTimeZone(manualTimeZoneSuggestion);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.app.timezonedetector.TimeZoneDetector
    public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion) {
        try {
            this.mITimeZoneDetectorService.suggestTelephonyTimeZone(telephonyTimeZoneSuggestion);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
