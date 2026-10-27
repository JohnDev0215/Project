package org.chromium.device.vibration;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.util.Log;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojom.device.VibrationManager;

/* JADX INFO: loaded from: classes3.dex */
public class VibrationManagerImpl implements VibrationManager {
    private static final long MAXIMUM_VIBRATION_DURATION_MS = 10000;
    private static final long MINIMUM_VIBRATION_DURATION_MS = 1;
    private static final String TAG = "VibrationManagerImpl";
    private static AndroidVibratorWrapper sVibratorWrapper;
    private final AudioManager mAudioManager;
    private final boolean mHasVibratePermission;
    private final Vibrator mVibrator;

    @Override // org.chromium.mojo.bindings.Interface, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // org.chromium.mojo.bindings.ConnectionErrorHandler
    public void onConnectionError(MojoException mojoException) {
    }

    public static class AndroidVibratorWrapper {
        protected AndroidVibratorWrapper() {
        }

        public void vibrate(Vibrator vibrator, long j) {
            vibrator.vibrate(j);
        }

        public void cancel(Vibrator vibrator) {
            vibrator.cancel();
        }
    }

    public static void setVibratorWrapperForTesting(AndroidVibratorWrapper androidVibratorWrapper) {
        sVibratorWrapper = androidVibratorWrapper;
    }

    public VibrationManagerImpl(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
        if (sVibratorWrapper == null) {
            sVibratorWrapper = new AndroidVibratorWrapper();
        }
        boolean z = context.checkCallingOrSelfPermission("android.permission.VIBRATE") == 0;
        this.mHasVibratePermission = z;
        if (z) {
            return;
        }
        Log.w(TAG, "Failed to use vibrate API, requires VIBRATE permission.");
    }

    @Override // org.chromium.mojom.device.VibrationManager
    public void vibrate(long j, VibrationManager.VibrateResponse vibrateResponse) {
        long jMax = Math.max(1L, Math.min(j, 10000L));
        if (this.mAudioManager.getRingerMode() != 0 && this.mHasVibratePermission) {
            sVibratorWrapper.vibrate(this.mVibrator, jMax);
        }
        vibrateResponse.call();
    }

    @Override // org.chromium.mojom.device.VibrationManager
    public void cancel(VibrationManager.CancelResponse cancelResponse) {
        if (this.mHasVibratePermission) {
            sVibratorWrapper.cancel(this.mVibrator);
        }
        cancelResponse.call();
    }
}
