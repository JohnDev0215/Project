package com.ts.appservice.power.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ExtAudioManager {
    public static final int AUDIO_COMMAND_FAILED = -1;
    public static final int AUDIO_COMMAND_SUCCESS = 1;
    public static final int MUTE_DISABLE = 0;
    public static final int MUTE_ENABLE = 1;
    private static final String TAG = ExtAudioManager.class.getSimpleName();
    public static final int USAGE_POWER_STATE = 36;
    private static volatile ExtAudioManager sInstance;
    private Handler mAudioHandler;
    private AudioManager mAudioManager;
    private Context mContext;
    private AudioFocusRequest mPowerFocusRequest;
    private boolean mIsAbandonAudioFocus = true;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.ts.appservice.power.audio.-$$Lambda$ExtAudioManager$lmVeMYCWkVC0SWg9pQAoZ9Oma1U
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            this.f$0.lambda$new$1$ExtAudioManager(i);
        }
    };

    public static ExtAudioManager getInstance(Context context, Handler handler) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (ExtAudioManager.class) {
                if (sInstance == null) {
                    sInstance = new ExtAudioManager(context.getApplicationContext(), handler);
                }
            }
        }
        return sInstance;
    }

    private ExtAudioManager(Context context, Handler handler) {
        LogUtil.info(TAG, "TsAudioManager");
        this.mContext = context;
        this.mAudioHandler = handler;
        initManager();
        initFocusRequest();
    }

    private void initFocusRequest() {
        AudioAttributes focusAttr = new AudioAttributes.Builder().setUsage(36).build();
        this.mPowerFocusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(focusAttr).setOnAudioFocusChangeListener(this.mAudioFocusListener).build();
    }

    private void initManager() {
        LogUtil.info(TAG, "initManager");
        this.mAudioHandler.post(new Runnable() { // from class: com.ts.appservice.power.audio.-$$Lambda$ExtAudioManager$MozzF9SEq5DvzX9tM9xSucs3DYM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initManager$0$ExtAudioManager();
            }
        });
    }

    public /* synthetic */ void lambda$initManager$0$ExtAudioManager() {
        try {
            LogUtil.info(TAG, "AudioHandler initManager");
            this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
            LogUtil.info(TAG, "AudioHandler initManager mAudioExtManager : OK");
        } catch (Exception exception) {
            LogUtil.error(TAG, Log.getStackTraceString(exception));
        }
    }

    public boolean requestAudioFocus() {
        LogUtil.warning(TAG, "requestAudioFocus");
        if (this.mAudioManager == null) {
            LogUtil.error(TAG, "requestAudioFocus mAudioExtManager : null");
            initManager();
            return false;
        }
        try {
            LogUtil.info(TAG, "AudioHandler requestAudioFocus mIsAbandonAudioFocus : " + this.mIsAbandonAudioFocus);
            int result = this.mAudioManager.requestAudioFocus(this.mPowerFocusRequest);
            boolean bool = 1 == result;
            LogUtil.warning(TAG, "requestAudioFocus result : " + result + " bool : " + bool);
            if (bool) {
                this.mIsAbandonAudioFocus = false;
            }
        } catch (Exception exception) {
            LogUtil.error(TAG, "requestAudioFocus", exception);
        }
        return true;
    }

    public boolean abandonAudioFocus() {
        LogUtil.warning(TAG, "abandonAudioFocus");
        if (this.mAudioManager == null) {
            LogUtil.error(TAG, "requestAudioFocus mAudioExtManager : null");
            initManager();
            return false;
        }
        try {
            LogUtil.info(TAG, "AudioHandler abandonAudioFocus mIsAbandonAudioFocus : " + this.mIsAbandonAudioFocus);
            int result = this.mAudioManager.abandonAudioFocusRequest(this.mPowerFocusRequest);
            boolean bool = 1 == result;
            LogUtil.warning(TAG, "abandonAudioFocus result : " + result + " bool : " + bool);
            if (bool) {
                this.mIsAbandonAudioFocus = true;
            }
        } catch (Exception exception) {
            LogUtil.error(TAG, "abandonAudioFocus", exception);
        }
        return true;
    }

    public /* synthetic */ void lambda$new$1$ExtAudioManager(int focusChange) {
        LogUtil.warning(TAG, "focusChange:" + focusChange);
        if (focusChange == -2 || focusChange == -1) {
            this.mIsAbandonAudioFocus = true;
        } else if (focusChange == 1 || focusChange == 2) {
            this.mIsAbandonAudioFocus = false;
        }
    }
}
