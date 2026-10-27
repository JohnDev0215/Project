package com.ecarx.xui.adaptapi.policy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ecarx.xui.adaptapi.sign.SignVerify;

/* JADX INFO: loaded from: classes2.dex */
public class Policy extends AdaptAPI {
    public static final String TAG = "Policy";
    public static Context mContext;
    private static Policy mPolicy;
    private ActivityWatcherPolicy mActivityWatcherPolicy;
    private AudioPolicy mAudioPolicy;
    private SpeedImpl mSpeedImpl;
    private StoragePolicy mStoragePolicy;
    private VoiceAssistantPolicy mVoiceAssistantPolicy;
    private WindowManagerPolicy mWindowManagerPolicy;

    public void updateMediaStateRecoveryIntent(Intent intent) {
    }

    public Policy(Context context) {
        mContext = context;
    }

    public static Policy create(Context context) {
        Log.d(TAG, "Policy create start 123");
        if (context == null || !SignVerify.instance().appVerify(context)) {
            return null;
        }
        Policy policy = new Policy(context);
        mPolicy = policy;
        return policy;
    }

    public IVoiceAssistantPolicy getVoiceAssistantPolicy() {
        Log.d(TAG, "getVoiceAssistantPolicy ");
        if (mContext == null) {
            return null;
        }
        if (this.mVoiceAssistantPolicy == null) {
            this.mVoiceAssistantPolicy = new VoiceAssistantPolicy(mContext);
        }
        return this.mVoiceAssistantPolicy;
    }

    public IStoragePolicy getStoragePolicy() {
        Log.d(TAG, "getStoragePolicy ");
        if (mContext == null) {
            return null;
        }
        if (this.mStoragePolicy == null) {
            this.mStoragePolicy = new StoragePolicy(mContext);
        }
        return this.mStoragePolicy;
    }

    public IAudioPolicy getAudioPolicy() {
        if (mContext == null) {
            return null;
        }
        Log.d(TAG, "getAudioPolicy 123");
        if (this.mAudioPolicy == null) {
            this.mAudioPolicy = new AudioPolicy(mContext);
        }
        return this.mAudioPolicy;
    }

    public IWindowManagerPolicy getWindowManagerPolicy() {
        Log.d(TAG, "getWindowManagerPolicy 456");
        if (this.mWindowManagerPolicy == null) {
            this.mWindowManagerPolicy = new WindowManagerPolicy();
        }
        return this.mWindowManagerPolicy;
    }

    public IActivityWatcherPolicy getActivityWatcher() {
        Log.d(TAG, "getActivityWatcher.");
        if (this.mActivityWatcherPolicy == null) {
            this.mActivityWatcherPolicy = new ActivityWatcherPolicy();
        }
        return this.mActivityWatcherPolicy;
    }

    public ISpeed getSpeedInfo() {
        Log.d(TAG, "getSpeedInfo");
        if (this.mSpeedImpl == null) {
            this.mSpeedImpl = new SpeedImpl();
        }
        return this.mSpeedImpl;
    }
}
