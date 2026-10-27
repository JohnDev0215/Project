package com.amazon.music.wakeword;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetectorCompat;
import com.amazon.music.wakeword.WakeWordErrorHandler;
import com.amazon.pryon.android.asr.PryonLite;
import com.amazon.pryon.android.asr.PryonLiteCallbacks;

/* JADX INFO: loaded from: classes4.dex */
public class WakeWordController {
    private static final String TAG = "WakeWordController";
    private ApplicationForegroundProvider applicationForegroundProvider;
    private Context context;
    private PryonWakeWordDetectorCompat pryonWakeWordDetectorCompat;
    private Handler handler = new Handler();
    private boolean isEnabled = true;
    private Runnable runnable = new Runnable() { // from class: com.amazon.music.wakeword.WakeWordController.1
        @Override // java.lang.Runnable
        public void run() {
            if (WakeWordController.this.isInitialized() && WakeWordController.this.applicationForegroundProvider != null && WakeWordController.this.applicationForegroundProvider.isApplicationInForeground()) {
                Log.i(WakeWordController.TAG, "Starting WakeWord detection.");
                WakeWordController.this.pryonWakeWordDetectorCompat.start();
            }
        }
    };
    private WakeWordErrorHandler wakeWordErrorHandler = new WakeWordErrorHandler();

    public WakeWordController(@NonNull Context context, @NonNull PryonLite.Config config, @NonNull PryonLiteCallbacks pryonLiteCallbacks, @NonNull ApplicationForegroundProvider applicationForegroundProvider) {
        this.context = context.getApplicationContext();
        this.applicationForegroundProvider = applicationForegroundProvider;
        initializePryon(this.context, config, pryonLiteCallbacks);
    }

    private void initializePryon(Context context, PryonLite.Config config, PryonLiteCallbacks pryonLiteCallbacks) {
        this.pryonWakeWordDetectorCompat = new PryonWakeWordDetectorCompat(config, false, false);
        if (!isInitialized()) {
            this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.PRYON_LIBRARY_ERROR, "Initialization failed for some reason.");
        } else {
            this.pryonWakeWordDetectorCompat.setPryonLiteCallbacks(pryonLiteCallbacks);
        }
    }

    boolean isInitialized() {
        return this.pryonWakeWordDetectorCompat != null;
    }

    public void startWakeWordDetection() {
        if (!isInitialized()) {
            this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED, "Failing to start detection.");
            return;
        }
        if (!isMicrophonePermissionsStillAvailable(this.context)) {
            this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.MICROPHONE_PERMISSIONS_REVOKED, "Failing to start wakeWord detection");
        } else if (this.isEnabled) {
            this.handler.removeCallbacks(this.runnable);
            this.handler.postDelayed(this.runnable, 300L);
        }
    }

    public void stopWakeWordDetection() {
        if (!isInitialized()) {
            this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED, "Failing to stop detection.");
        } else {
            Log.i(TAG, "Stopping WakeWord detection for a generic reason.");
            this.pryonWakeWordDetectorCompat.stop();
        }
    }

    public void alexaUiOpened() {
        if (!isInitialized()) {
            this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED, "Failing to stop detection when alexa ui is opened.");
        } else {
            Log.i(TAG, "Stopping WakeWord detection due to Alexa ui opened.");
            this.pryonWakeWordDetectorCompat.stop();
        }
    }

    public void alexaUiClosed() {
        if (!isInitialized()) {
            this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED, "Failing to start detection when alexa ui is closed.");
            return;
        }
        ApplicationForegroundProvider applicationForegroundProvider = this.applicationForegroundProvider;
        if (applicationForegroundProvider == null || applicationForegroundProvider.isApplicationInForeground()) {
            startWakeWordDetection();
        }
    }

    public void notifyAppFocusChanged(boolean z) {
        if (isInitialized()) {
            if (z) {
                this.handler.removeCallbacks(this.runnable);
                Log.i(TAG, "Stopping WakeWord detection due to app being in the background.");
                this.pryonWakeWordDetectorCompat.stop();
                return;
            }
            startWakeWordDetection();
            return;
        }
        this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED, "Failing to stop/start detection when the app focus has changed. isAppInBackground: " + z);
    }

    public void exit() {
        if (!isInitialized()) {
            this.wakeWordErrorHandler.handleError(WakeWordErrorHandler.WakeWordErrorType.PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED, "Failing to exit.");
            return;
        }
        Log.i(TAG, "Stopping WakeWord detection due to exit method called.");
        this.pryonWakeWordDetectorCompat.stop();
        this.pryonWakeWordDetectorCompat.removePryonLiteCallbacks();
        this.pryonWakeWordDetectorCompat.release();
        this.pryonWakeWordDetectorCompat = null;
        this.wakeWordErrorHandler = null;
    }

    public static boolean isMicrophonePermissionsStillAvailable(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") == 0;
    }

    @Deprecated
    public void setUserEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public int setDetectionThreshold(int i) {
        PryonWakeWordDetectorCompat pryonWakeWordDetectorCompat = this.pryonWakeWordDetectorCompat;
        if (pryonWakeWordDetectorCompat == null) {
            return -1;
        }
        return pryonWakeWordDetectorCompat.setSensitivityThreshold(i);
    }
}
