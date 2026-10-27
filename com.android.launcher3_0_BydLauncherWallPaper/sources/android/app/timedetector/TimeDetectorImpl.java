package android.app.timedetector;

import android.app.timedetector.ITimeDetectorService;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;

/* JADX INFO: loaded from: classes.dex */
public final class TimeDetectorImpl implements TimeDetector {
    private static final boolean DEBUG = false;
    private static final String TAG = "timedetector.TimeDetector";
    private final ITimeDetectorService mITimeDetectorService = ITimeDetectorService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.TIME_DETECTOR_SERVICE));

    @Override // android.app.timedetector.TimeDetector
    public void suggestTelephonyTime(TelephonyTimeSuggestion telephonyTimeSuggestion) {
        try {
            this.mITimeDetectorService.suggestTelephonyTime(telephonyTimeSuggestion);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.app.timedetector.TimeDetector
    public void suggestManualTime(ManualTimeSuggestion manualTimeSuggestion) {
        try {
            this.mITimeDetectorService.suggestManualTime(manualTimeSuggestion);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.app.timedetector.TimeDetector
    public void suggestNetworkTime(NetworkTimeSuggestion networkTimeSuggestion) {
        try {
            this.mITimeDetectorService.suggestNetworkTime(networkTimeSuggestion);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
