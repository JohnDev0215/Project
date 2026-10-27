package org.chromium.content.browser;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class MediaSessionDelegate implements AudioManager.OnAudioFocusChangeListener {
    public static final double DEFAULT_VOLUME_MULTIPLIER = 1.0d;
    public static final double DUCKING_VOLUME_MULTIPLIER = 0.20000000298023224d;
    private static final String TAG = "MediaSession";
    private Context mContext;
    private int mFocusType;
    private boolean mIsDucking = false;
    private long mNativeMediaSessionDelegateAndroid;

    private native void nativeOnResume(long j);

    private native void nativeOnSetVolumeMultiplier(long j, double d);

    private native void nativeOnSuspend(long j, boolean z);

    private native void nativeRecordSessionDuck(long j);

    private MediaSessionDelegate(Context context, long j) {
        this.mContext = context;
        this.mNativeMediaSessionDelegateAndroid = j;
    }

    private static MediaSessionDelegate create(Context context, long j) {
        return new MediaSessionDelegate(context, j);
    }

    private void tearDown() {
        abandonAudioFocus();
        this.mNativeMediaSessionDelegateAndroid = 0L;
    }

    private boolean requestAudioFocus(boolean z) {
        this.mFocusType = z ? 3 : 1;
        return requestAudioFocusInternal();
    }

    private void abandonAudioFocus() {
        ((AudioManager) this.mContext.getSystemService(Car.AUDIO_SERVICE)).abandonAudioFocus(this);
    }

    private boolean requestAudioFocusInternal() {
        return ((AudioManager) this.mContext.getSystemService(Car.AUDIO_SERVICE)).requestAudioFocus(this, 3, this.mFocusType) == 1;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        long j = this.mNativeMediaSessionDelegateAndroid;
        if (j == 0) {
            return;
        }
        if (i == -3) {
            this.mIsDucking = true;
            nativeRecordSessionDuck(j);
            nativeOnSetVolumeMultiplier(this.mNativeMediaSessionDelegateAndroid, 0.20000000298023224d);
            return;
        }
        if (i == -2) {
            nativeOnSuspend(j, true);
            return;
        }
        if (i == -1) {
            abandonAudioFocus();
            nativeOnSuspend(this.mNativeMediaSessionDelegateAndroid, false);
        } else if (i != 1) {
            Log.w(TAG, "onAudioFocusChange called with unexpected value %d", Integer.valueOf(i));
        } else if (this.mIsDucking) {
            nativeOnSetVolumeMultiplier(j, 1.0d);
            this.mIsDucking = false;
        } else {
            nativeOnResume(j);
        }
    }
}
