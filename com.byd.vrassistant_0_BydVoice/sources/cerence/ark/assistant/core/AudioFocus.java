package cerence.ark.assistant.core;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AudioFocus {
    private static final String TAG = "AudioFocus";
    private String funName;
    private final AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private final Map<Integer, String> mAudioFocusResultMap;
    private final Map<Integer, String> mAudioFocusStateMap;
    private final AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mFocusChangeListener;
    private AudioFocusRequest mFocusRequest;
    private boolean mHasFocus;

    /* JADX INFO: renamed from: lambda$new$0$cerence-ark-assistant-core-AudioFocus, reason: not valid java name */
    /* synthetic */ void m39lambda$new$0$cerencearkassistantcoreAudioFocus(int i) {
        Logger.debug(TAG, String.format("%s => onAudioFocusChange: focusChange = %s", this.funName, this.mAudioFocusStateMap.get(Integer.valueOf(i))));
        if (i == -2) {
            this.mHasFocus = false;
        } else if (i == -1) {
            releaseAudioFocus();
            this.mHasFocus = false;
        } else if (i == 1) {
            this.mHasFocus = true;
        }
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.mFocusChangeListener;
        if (onAudioFocusChangeListener != null) {
            onAudioFocusChangeListener.onAudioFocusChange(i);
        }
    }

    public AudioFocus(Context context, String str) {
        HashMap map = new HashMap(16);
        this.mAudioFocusStateMap = map;
        HashMap map2 = new HashMap(16);
        this.mAudioFocusResultMap = map2;
        this.funName = "AudioFocus";
        this.mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: cerence.ark.assistant.core.AudioFocus$$ExternalSyntheticLambda0
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                this.f$0.m39lambda$new$0$cerencearkassistantcoreAudioFocus(i);
            }
        };
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        if (!TextUtils.isEmpty(str)) {
            this.funName = String.format("%s_audio_focus", str);
        }
        map.put(1, "AUDIOFOCUS_GAIN");
        map.put(2, "AUDIOFOCUS_GAIN_TRANSIENT");
        map.put(3, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
        map.put(4, "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE");
        map.put(-1, "AUDIOFOCUS_LOSS");
        map.put(-2, "AUDIOFOCUS_LOSS_TRANSIENT");
        map.put(-3, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
        map2.put(0, "AUDIOFOCUS_REQUEST_FAILED");
        map2.put(1, "AUDIOFOCUS_REQUEST_GRANTED");
    }

    public int requestAudioFocus(int i, int i2, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.mFocusChangeListener = onAudioFocusChangeListener;
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(i).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(i2).build()).setOnAudioFocusChangeListener(this.mAudioFocusChangeListener).build();
        this.mFocusRequest = audioFocusRequestBuild;
        int iRequestAudioFocus = this.mAudioManager.requestAudioFocus(audioFocusRequestBuild);
        if (iRequestAudioFocus == 1) {
            this.mHasFocus = true;
        } else if (iRequestAudioFocus == 0) {
            this.mHasFocus = false;
        }
        String strValueOf = this.mAudioFocusResultMap.get(Integer.valueOf(iRequestAudioFocus));
        String str = TAG;
        Object[] objArr = new Object[4];
        objArr[0] = this.funName;
        objArr[1] = this.mAudioFocusStateMap.get(Integer.valueOf(i));
        objArr[2] = Integer.valueOf(i2);
        if (strValueOf == null) {
            strValueOf = Integer.valueOf(iRequestAudioFocus);
        }
        objArr[3] = strValueOf;
        Logger.debug(str, String.format("%s => requestAudioFocus: focusGain=%s, streamType=%s; result=%s", objArr));
        return iRequestAudioFocus;
    }

    public int releaseAudioFocus() {
        int iAbandonAudioFocusRequest;
        AudioFocusRequest audioFocusRequest = this.mFocusRequest;
        if (audioFocusRequest != null) {
            iAbandonAudioFocusRequest = this.mAudioManager.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            Logger.debug(TAG, String.format("%s => releaseAudioFocus: not request focus", this.funName));
            iAbandonAudioFocusRequest = 0;
        }
        if (iAbandonAudioFocusRequest == 1) {
            this.mHasFocus = false;
        } else if (iAbandonAudioFocusRequest == 0) {
            this.mHasFocus = true;
        }
        String strValueOf = this.mAudioFocusResultMap.get(Integer.valueOf(iAbandonAudioFocusRequest));
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = this.funName;
        if (strValueOf == null) {
            strValueOf = Integer.valueOf(iAbandonAudioFocusRequest);
        }
        objArr[1] = strValueOf;
        Logger.debug(str, String.format("%s => releaseAudioFocus: result=%s", objArr));
        return iAbandonAudioFocusRequest;
    }

    public boolean hasAudioFocus() {
        return this.mHasFocus;
    }
}
