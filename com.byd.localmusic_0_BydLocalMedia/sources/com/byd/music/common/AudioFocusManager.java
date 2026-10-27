package com.byd.music.common;

import android.content.ComponentName;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.api.ApiMethod;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.mediahelper.MediaButtonReceiver;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class AudioFocusManager {
    private static volatile AudioFocusManager mInstance;
    private String TAG = "AudioFocusManager";
    private int audioFocusState;
    public AudioManager audioManager;
    private ComponentName componentName;
    private AudioAttributes focusAttr;
    private AudioManager.OnAudioFocusChangeListener focusListener;
    private AudioFocusRequest focusRequest;
    private boolean isLossTransient;
    private volatile MediaSessionCompat mMediaSession;
    private ArrayList<String> requestTagList;

    private AudioFocusManager() {
        LogUtil.e("AudioFocusManager", "AudioFocusManager init enter");
        if (!Looper.getMainLooper().isCurrentThread()) {
            Looper.prepare();
            LogUtil.e(this.TAG, "AudioFocusManager must be init in main thread, test so let go");
        }
        this.isLossTransient = false;
        this.requestTagList = new ArrayList<>();
        this.mMediaSession = new MediaSessionCompat(Utils.getApp(), "MediaCenter MediaSession");
        this.mMediaSession.setFlags(3);
        LogUtil.e(this.TAG, "AudioFocusManager go to mMediaSession.setCallback");
        this.mMediaSession.setCallback(new MediaSessionCompat.Callback() { // from class: com.byd.music.common.AudioFocusManager.1
            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public boolean onMediaButtonEvent(Intent intent) {
                LogUtil.e(AudioFocusManager.this.TAG, "onMediaButtonEvent enter with intent extra = " + intent.getExtras());
                KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                if (keyEvent != null && keyEvent.getAction() == 1) {
                    LogUtil.e(AudioFocusManager.this.TAG, "onMediaButtonEvent keyEvent = " + keyEvent.toString() + ", keyEvent.getKeyCode = " + keyEvent.getKeyCode());
                    if (keyEvent.getSource() == 2023 && (OnLineMusic.musicEngine == 102 || OnLineMusic.musicEngine == 101)) {
                        LogUtil.e(AudioFocusManager.this.TAG, "onMediaButtonEvent 2023 but CPAA, just return.");
                        return super.onMediaButtonEvent(intent);
                    }
                    int keyCode = keyEvent.getKeyCode();
                    if (keyCode == 126) {
                        ApiMethod.playOrStop(true);
                        return true;
                    }
                    if (keyCode != 127) {
                        switch (keyCode) {
                            case 85:
                                ApiMethod.playOrStop(!CurrentMediaInfoUtils.getInstance().getPlayingState());
                                return true;
                            case 87:
                                ApiMethod.playNext();
                                return true;
                            case 88:
                                ApiMethod.playPre();
                                return true;
                        }
                    }
                    ApiMethod.playOrStop(false);
                    return true;
                }
                return super.onMediaButtonEvent(intent);
            }
        });
        LogUtil.e(this.TAG, "AudioFocusManager after set mMediaSession.setCallback");
        this.componentName = new ComponentName(Utils.getApp().getPackageName(), MediaButtonReceiver.class.getName());
        this.audioManager = (AudioManager) Utils.getApp().getSystemService("audio");
        this.focusListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.byd.music.common.-$$Lambda$AudioFocusManager$-lmuSJ5x1_jx0EaMoRG17oHawSE
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                this.f$0.lambda$new$0$AudioFocusManager(i);
            }
        };
        this.focusAttr = new AudioAttributes.Builder().setUsage(1).build();
        this.focusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(this.focusAttr).setOnAudioFocusChangeListener(this.focusListener).build();
    }

    public /* synthetic */ void lambda$new$0$AudioFocusManager(int i) {
        this.audioFocusState = i;
        if (i == -3) {
            LogUtil.e(this.TAG, "FOCUS_STATUE = AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
            return;
        }
        if (i == -2) {
            LogUtil.e(this.TAG, "FOCUS_STATUE = AUDIOFOCUS_LOSS_TRANSIENT");
            if (CurrentMediaInfoUtils.getInstance().getPlayingState()) {
                this.isLossTransient = true;
            }
            ApiMethod.playOrStop(false);
            this.audioManager.unregisterMediaButtonEventReceiver(this.componentName);
            return;
        }
        if (i == -1) {
            LogUtil.e(this.TAG, "FOCUS_STATUE = AUDIOFOCUS_LOSS, this = " + this.focusListener);
            ApiMethod.playOrStop(false);
            this.audioManager.unregisterMediaButtonEventReceiver(this.componentName);
            return;
        }
        if (i != 1) {
            if (i == 2) {
                LogUtil.e(this.TAG, "FOCUS_STATUE = AUDIOFOCUS_GAIN_TRANSIENT");
                return;
            } else {
                if (i != 3) {
                    return;
                }
                LogUtil.e(this.TAG, "FOCUS_STATUE = AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                return;
            }
        }
        LogUtil.e(this.TAG, "FOCUS_STATUE = AUDIOFOCUS_GAIN");
        if (DeviceInfoUtils.isInCall(false)) {
            AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.common.AudioFocusManager.2
                @Override // java.lang.Runnable
                public void run() {
                    if ((AudioFocusManager.this.isLossTransient || CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState()) && !CurrentMediaInfoUtils.getInstance().getPlayingState()) {
                        ApiMethod.playOrStop(true);
                        AudioFocusManager.this.isLossTransient = false;
                    }
                }
            }, 300L);
        } else if ((this.isLossTransient || CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState()) && !CurrentMediaInfoUtils.getInstance().getPlayingState()) {
            ApiMethod.playOrStop(true);
            this.isLossTransient = false;
        }
        this.audioManager.registerMediaButtonEventReceiver(this.componentName);
    }

    public static AudioFocusManager getInstance() {
        if (mInstance == null) {
            synchronized (AudioFocusManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioFocusManager();
                }
            }
        }
        return mInstance;
    }

    public boolean isHoldAudioFocus() {
        return this.audioFocusState == 1;
    }

    public int requestAudioFocus() {
        if (isHoldAudioFocus()) {
            return 1;
        }
        if (DeviceInfoUtils.isInCall(false)) {
            LogUtil.e(this.TAG, "requestAudioFocus fail due to isInCall and return 0 !");
            return 0;
        }
        int iRequestAudioFocus = this.audioManager.requestAudioFocus(this.focusRequest);
        if (iRequestAudioFocus == 1) {
            this.audioFocusState = 1;
            setMediaSessionActive(true);
        }
        LogUtil.e(this.TAG, "requestAudioFocus: finish with ret = " + iRequestAudioFocus);
        return iRequestAudioFocus;
    }

    public int releaseAudioFocus() {
        if (this.audioFocusState != 1) {
            LogUtil.e(this.TAG, "releaseAudioFocus: sorry, audioFocusState = " + this.audioFocusState);
            return -1;
        }
        int iAbandonAudioFocusRequest = this.audioManager.abandonAudioFocusRequest(this.focusRequest);
        if (iAbandonAudioFocusRequest == 1) {
            this.audioFocusState = -1;
            setMediaSessionActive(false);
        }
        LogUtil.e(this.TAG, "releaseAudioFocus: finish with ret = " + iAbandonAudioFocusRequest);
        return iAbandonAudioFocusRequest;
    }

    public int requestAudioFocusWithCount(String str) {
        LogUtil.d(this.TAG, "requestAudioFocusWithCount: enter, tag = " + str);
        int iRequestAudioFocus = requestAudioFocus();
        if (!this.requestTagList.contains(str) && iRequestAudioFocus > 0) {
            this.requestTagList.add(str);
        }
        return iRequestAudioFocus;
    }

    public int releaseAudioFocusWithCount(String str) {
        LogUtil.d(this.TAG, "releaseAudioFocusWithCount: enter, tag = " + str);
        if (!this.requestTagList.contains(str)) {
            return 0;
        }
        this.requestTagList.remove(str);
        if (!this.requestTagList.isEmpty()) {
            return 0;
        }
        LogUtil.d(this.TAG, "releaseAudioFocusWithCount: releaseAudioFocus ");
        return releaseAudioFocus();
    }

    public boolean isOnlineSourceActive() {
        return !this.requestTagList.isEmpty();
    }

    public void setMediaSessionActive(boolean z) {
        if (this.mMediaSession == null) {
            LogUtil.d(this.TAG, "setMediaSessionActive but mMediaSession is null");
            return;
        }
        if (z) {
            LogUtil.d(this.TAG, "setMediaSessionActive: true temp set false...");
            this.mMediaSession.setActive(false);
            if (this.mMediaSession.isActive()) {
                return;
            }
            LogUtil.e(this.TAG, "setMediaSessionActive: true ");
            this.mMediaSession.setActive(true);
            try {
                new AudioTrack(3, 48000, 12, 2, AudioTrack.getMinBufferSize(48000, 12, 2), 1).play();
                return;
            } catch (Throwable th) {
                LogUtil.e(this.TAG, "setMediaSessionActive: error ");
                th.printStackTrace();
                return;
            }
        }
        if (this.mMediaSession.isActive()) {
            LogUtil.e(this.TAG, "setMediaSessionActive: false, in fact, just check mediaSession active = true");
        }
    }
}
