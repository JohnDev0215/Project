package android.os;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.content.Context;
import android.media.AudioAttributes;
import android.util.Log;
import com.android.internal.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Vibrator {
    private static final String TAG = "Vibrator";
    public static final int VIBRATION_EFFECT_SUPPORT_NO = 2;
    public static final int VIBRATION_EFFECT_SUPPORT_UNKNOWN = 0;
    public static final int VIBRATION_EFFECT_SUPPORT_YES = 1;
    public static final int VIBRATION_INTENSITY_HIGH = 3;
    public static final int VIBRATION_INTENSITY_LOW = 1;
    public static final int VIBRATION_INTENSITY_MEDIUM = 2;
    public static final int VIBRATION_INTENSITY_OFF = 0;
    private int mDefaultHapticFeedbackIntensity;
    private int mDefaultNotificationVibrationIntensity;
    private int mDefaultRingVibrationIntensity;
    private final String mPackageName;

    @SystemApi
    public interface OnVibratorStateChangedListener {
        void onVibratorStateChanged(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VibrationEffectSupport {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VibrationIntensity {
    }

    @SystemApi
    public void addVibratorStateListener(OnVibratorStateChangedListener onVibratorStateChangedListener) {
    }

    @SystemApi
    public void addVibratorStateListener(Executor executor, OnVibratorStateChangedListener onVibratorStateChangedListener) {
    }

    public abstract void cancel();

    public abstract boolean hasAmplitudeControl();

    public abstract boolean hasVibrator();

    @SystemApi
    public boolean isVibrating() {
        return false;
    }

    @SystemApi
    public void removeVibratorStateListener(OnVibratorStateChangedListener onVibratorStateChangedListener) {
    }

    public abstract void vibrate(int i, String str, VibrationEffect vibrationEffect, String str2, AudioAttributes audioAttributes);

    public Vibrator() {
        this.mPackageName = ActivityThread.currentPackageName();
        loadVibrationIntensities(ActivityThread.currentActivityThread().getSystemContext());
    }

    protected Vibrator(Context context) {
        this.mPackageName = context.getOpPackageName();
        loadVibrationIntensities(context);
    }

    private void loadVibrationIntensities(Context context) {
        this.mDefaultHapticFeedbackIntensity = loadDefaultIntensity(context, R.integer.config_defaultHapticFeedbackIntensity);
        this.mDefaultNotificationVibrationIntensity = loadDefaultIntensity(context, R.integer.config_defaultNotificationVibrationIntensity);
        this.mDefaultRingVibrationIntensity = loadDefaultIntensity(context, R.integer.config_defaultRingVibrationIntensity);
    }

    private int loadDefaultIntensity(Context context, int i) {
        if (context != null) {
            return context.getResources().getInteger(i);
        }
        return 2;
    }

    public int getDefaultHapticFeedbackIntensity() {
        return this.mDefaultHapticFeedbackIntensity;
    }

    public int getDefaultNotificationVibrationIntensity() {
        return this.mDefaultNotificationVibrationIntensity;
    }

    public int getDefaultRingVibrationIntensity() {
        return this.mDefaultRingVibrationIntensity;
    }

    public boolean setAlwaysOnEffect(int i, VibrationEffect vibrationEffect, AudioAttributes audioAttributes) {
        return setAlwaysOnEffect(Process.myUid(), this.mPackageName, i, vibrationEffect, audioAttributes);
    }

    public boolean setAlwaysOnEffect(int i, String str, int i2, VibrationEffect vibrationEffect, AudioAttributes audioAttributes) {
        Log.w(TAG, "Always-on effects aren't supported");
        return false;
    }

    @Deprecated
    public void vibrate(long j) {
        vibrate(j, (AudioAttributes) null);
    }

    @Deprecated
    public void vibrate(long j, AudioAttributes audioAttributes) {
        try {
            vibrate(VibrationEffect.createOneShot(j, -1), audioAttributes);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to create VibrationEffect", e);
        }
    }

    @Deprecated
    public void vibrate(long[] jArr, int i) {
        vibrate(jArr, i, null);
    }

    @Deprecated
    public void vibrate(long[] jArr, int i, AudioAttributes audioAttributes) {
        if (i < -1 || i >= jArr.length) {
            Log.e(TAG, "vibrate called with repeat index out of bounds (pattern.length=" + jArr.length + ", index=" + i + ")");
            throw new ArrayIndexOutOfBoundsException();
        }
        try {
            vibrate(VibrationEffect.createWaveform(jArr, i), audioAttributes);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to create VibrationEffect", e);
        }
    }

    public void vibrate(VibrationEffect vibrationEffect) {
        vibrate(vibrationEffect, (AudioAttributes) null);
    }

    public void vibrate(VibrationEffect vibrationEffect, AudioAttributes audioAttributes) {
        vibrate(Process.myUid(), this.mPackageName, vibrationEffect, null, audioAttributes);
    }

    public int[] areEffectsSupported(int... iArr) {
        int[] iArr2 = new int[iArr.length];
        Arrays.fill(iArr2, 2);
        return iArr2;
    }

    public final int areAllEffectsSupported(int... iArr) {
        int i = 1;
        for (int i2 : areEffectsSupported(iArr)) {
            if (i2 == 2) {
                return 2;
            }
            if (i2 == 0) {
                i = 0;
            }
        }
        return i;
    }

    public boolean[] arePrimitivesSupported(int... iArr) {
        return new boolean[iArr.length];
    }

    public final boolean areAllPrimitivesSupported(int... iArr) {
        for (boolean z : arePrimitivesSupported(iArr)) {
            if (!z) {
                return false;
            }
        }
        return true;
    }
}
