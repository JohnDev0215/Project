package cerence.ark.assistant.core;

import android.car.Car;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class AudioFocus {
    private static final String TAG = "AudioFocus";
    private String funName;
    private final AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private final Map<Integer, String> mAudioFocusResultMap;
    private final Map<Integer, String> mAudioFocusStateMap;
    private final AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mFocusChangeListener;
    private AudioFocusRequest mFocusRequest;
    private final AtomicBoolean mHasFocus;

    /* JADX INFO: renamed from: lambda$new$0$cerence-ark-assistant-core-AudioFocus, reason: not valid java name */
    /* synthetic */ void m57lambda$new$0$cerencearkassistantcoreAudioFocus(int i) {
        Logger.info(TAG, Utils.format("%s: onAudioFocusChange: focusChange = %s", this.funName, this.mAudioFocusStateMap.getOrDefault(Integer.valueOf(i), String.valueOf(i))));
        if (i == -2) {
            this.mHasFocus.set(false);
        } else if (i == -1) {
            releaseAudioFocus();
        } else if (i == 1) {
            this.mHasFocus.set(true);
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
        this.mHasFocus = new AtomicBoolean(false);
        this.mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: cerence.ark.assistant.core.AudioFocus$$ExternalSyntheticLambda0
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                this.f$0.m57lambda$new$0$cerencearkassistantcoreAudioFocus(i);
            }
        };
        this.mAudioManager = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        if (!TextUtils.isEmpty(str)) {
            this.funName = Utils.format("%s_audio_focus", str);
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
        map2.put(2, "AUDIOFOCUS_REQUEST_DELAYED");
    }

    public int requestAudioFocus(int i, int i2, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.mFocusChangeListener = onAudioFocusChangeListener;
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(i).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(i2).build()).setOnAudioFocusChangeListener(this.mAudioFocusChangeListener).build();
        this.mFocusRequest = audioFocusRequestBuild;
        int iRequestAudioFocus = this.mAudioManager.requestAudioFocus(audioFocusRequestBuild);
        if (iRequestAudioFocus == 1) {
            this.mHasFocus.set(true);
        }
        Logger.info(TAG, Utils.format("%s: requestAudioFocus: focusGain: %s, streamType: %s; result: %s", this.funName, this.mAudioFocusStateMap.get(Integer.valueOf(i)), Integer.valueOf(i2), this.mAudioFocusResultMap.getOrDefault(Integer.valueOf(iRequestAudioFocus), String.valueOf(iRequestAudioFocus))));
        return iRequestAudioFocus;
    }

    public int requestAudioFocus(AudioFocusRequest audioFocusRequest) {
        if (audioFocusRequest == null) {
            Logger.error(TAG, "audioFocusRequest is null");
            return 0;
        }
        this.mFocusRequest = audioFocusRequest;
        int iRequestAudioFocus = this.mAudioManager.requestAudioFocus(audioFocusRequest);
        if (iRequestAudioFocus == 1) {
            this.mHasFocus.set(true);
        }
        Logger.info(TAG, Utils.format("%s: requestAudioFocus: focusGain: %s, result: %s", this.funName, this.mAudioFocusStateMap.get(Integer.valueOf(audioFocusRequest.getFocusGain())), this.mAudioFocusResultMap.getOrDefault(Integer.valueOf(iRequestAudioFocus), String.valueOf(iRequestAudioFocus))));
        return iRequestAudioFocus;
    }

    public int releaseAudioFocus() {
        int iAbandonAudioFocusRequest;
        AudioFocusRequest audioFocusRequest = this.mFocusRequest;
        if (audioFocusRequest != null) {
            iAbandonAudioFocusRequest = this.mAudioManager.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            Logger.info(TAG, Utils.format("%s: releaseAudioFocus: does not have audio focus", this.funName));
            iAbandonAudioFocusRequest = 0;
        }
        if (iAbandonAudioFocusRequest == 1) {
            this.mHasFocus.set(false);
            this.mFocusRequest = null;
        }
        Logger.info(TAG, Utils.format("%s: releaseAudioFocus: result: %s", this.funName, this.mAudioFocusResultMap.getOrDefault(Integer.valueOf(iAbandonAudioFocusRequest), String.valueOf(iAbandonAudioFocusRequest))));
        return iAbandonAudioFocusRequest;
    }

    public boolean hasAudioFocus() {
        return this.mHasFocus.get();
    }
}
