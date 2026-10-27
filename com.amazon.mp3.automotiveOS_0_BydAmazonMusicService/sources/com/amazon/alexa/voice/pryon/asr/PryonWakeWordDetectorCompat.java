package com.amazon.alexa.voice.pryon.asr;

import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.amazon.pryon.android.asr.PryonLite;
import com.amazon.pryon.android.asr.PryonLiteCallbacks;

/* JADX INFO: loaded from: classes2.dex */
public class PryonWakeWordDetectorCompat {
    private static final String TAG = "PryonWakeWordDetectorCompat";
    private final PryonWakeWordDetector pryonDetector;

    public PryonWakeWordDetectorCompat() {
        this(null, false, false);
    }

    public PryonWakeWordDetectorCompat(PryonLite.Config config, boolean z, boolean z2) {
        PryonWakeWordDetector pryonWakeWordDetector = null;
        try {
            pryonWakeWordDetector = new PryonWakeWordDetector(config, z, z2);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Pryon initialization failed", e);
        } catch (UnsupportedOperationException unused) {
            String property = System.getProperty("ro.product.cpu.abi", EnvironmentCompat.MEDIA_UNKNOWN);
            Log.e(TAG, "Pryon unavailable on platform " + property);
        }
        this.pryonDetector = pryonWakeWordDetector;
    }

    public void start() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            pryonWakeWordDetector.start();
        } else {
            Log.i(TAG, "Ignoring call to start wake word detector.");
        }
    }

    public void stop() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            pryonWakeWordDetector.stop();
        } else {
            Log.i(TAG, "Ignoring call to stop wake word detector.");
        }
    }

    public void release() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector != null) {
            pryonWakeWordDetector.release();
        } else {
            Log.i(TAG, "Ignoring call to release wake word detector.");
        }
    }

    public void setPryonLiteCallbacks(PryonLiteCallbacks pryonLiteCallbacks) {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector == null) {
            Log.i(TAG, "Ignoring call to setPryonLiteCallbacks in wake word detector.");
        } else {
            pryonWakeWordDetector.setPryonLiteCallbacks(pryonLiteCallbacks);
        }
    }

    public void removePryonLiteCallbacks() {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector == null) {
            Log.i(TAG, "Ignoring call to removePryonLiteCallbacks in wake word detector.");
        } else {
            pryonWakeWordDetector.removePryonLiteCallbacks();
        }
    }

    public int setSensitivityThreshold(int i) {
        PryonWakeWordDetector pryonWakeWordDetector = this.pryonDetector;
        if (pryonWakeWordDetector == null) {
            Log.i(TAG, "Ignoring call to setSensitivityThreshold in wake word detector.");
            return -1;
        }
        return pryonWakeWordDetector.setSensitivityThreshold(i);
    }
}
