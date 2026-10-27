package com.ecarx.xui.adaptapi.policy;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class AudioPolicy implements IAudioPolicy {
    public static final int STREAM_AM = 13;
    public static final int STREAM_BLUETOOTH_SCO = 6;
    public static final int STREAM_BTAUDIO = 3;
    public static final int STREAM_COMING_HINT = 15;
    public static final int STREAM_ECALL = 11;
    public static final int STREAM_FM = 14;
    public static final int STREAM_MIC = -2;
    public static final int STREAM_MIC_PHONE = -3;
    public static final int STREAM_NAVI_TTS = 10;
    public static final int STREAM_TTS = 9;
    public static final String TAG = "AudioPolicy";
    private AudioManager mAudioManager;
    private IMicResourceCallback mMicResourceCallback_ecarx;
    private IMicStateCallback mMicStateCallback_ecax;
    AudioManager.OnAudioFocusChangeListener mOnChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.ecarx.xui.adaptapi.policy.AudioPolicy.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i == -1) {
                if (AudioPolicy.this.mMicResourceCallback_ecarx != null) {
                    Log.d(AudioPolicy.TAG, "onAudioFocusChange mMicResourceCallback_ecarx Phone");
                    AudioPolicy.this.mMicResourceCallback_ecarx.onAbort(i);
                    return;
                }
                return;
            }
            if (i != 1) {
                if (i == 2 && AudioPolicy.this.mMicResourceCallback_ecarx != null) {
                    Log.d(AudioPolicy.TAG, "onAudioFocusChange mMicResourceCallback_ecarx Phone");
                    AudioPolicy.this.mMicResourceCallback_ecarx.onAbort(i);
                    return;
                }
                return;
            }
            if (AudioPolicy.this.mMicResourceCallback_ecarx != null) {
                Log.d(AudioPolicy.TAG, "onAudioFocusChange mMicResourceCallback_ecarx Phone");
                AudioPolicy.this.mMicResourceCallback_ecarx.onResume();
            }
        }
    };
    AudioManager.OnAudioFocusChangeListener mOnChangeListenerVR = new AudioManager.OnAudioFocusChangeListener() { // from class: com.ecarx.xui.adaptapi.policy.AudioPolicy.2
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i == -1) {
                if (AudioPolicy.this.mMicStateCallback_ecax != null) {
                    Log.d(AudioPolicy.TAG, "onAudioFocusChange mMicStateCallback_ecax VR");
                    AudioPolicy.this.mMicStateCallback_ecax.notifyMicStateChange(1);
                    return;
                }
                return;
            }
            if (i != 1) {
                if (i == 2 && AudioPolicy.this.mMicStateCallback_ecax != null) {
                    Log.d(AudioPolicy.TAG, "onAudioFocusChange mMicStateCallback_ecax VR");
                    AudioPolicy.this.mMicStateCallback_ecax.notifyMicStateChange(1);
                    return;
                }
                return;
            }
            if (AudioPolicy.this.mMicStateCallback_ecax != null) {
                Log.d(AudioPolicy.TAG, "onAudioFocusChange mMicStateCallback_ecax VR");
                AudioPolicy.this.mMicStateCallback_ecax.notifyMicStateChange(2);
            }
        }
    };

    public static final class AudioFunction {
        public static final String FUNCTION_AUDIO_ASSISTANT = "AUDIO_ASSISTANT";
        public static final String FUNCTION_AUX = "AUX";
        public static final String FUNCTION_BLUETOOTH_HFP = "BLUETOOTH_HFP";
        public static final String FUNCTION_CALL_BCALL = "CALL_BCALL";
        public static final String FUNCTION_CALL_ECALL = "CALL_ECALL";
        public static final String FUNCTION_CALL_ICALL = "CALL_ICALL";
        public static final String FUNCTION_CALL_ONCALL = "CALL_ONCALL";
        public static final String FUNCTION_NAVIGATION_GUIDE = "NAVIGATION_GUIDE";
        public static final String FUNCTION_NAVIGATION_TURN_HINT = "NAVIGATION_TURN_HINT";
        public static final String FUNCTION_PHONE_COMING_HINT = "FUNCTION_PHONE_COMING_HINT";
        public static final String FUNCTION_RADIO_AM = "RADIO_AM";
        public static final String FUNCTION_RADIO_FM = "RADIO_FM";
        public static final String FUNCTION_STREAM_BT_AUDIO = "STREAM_BT_AUDIO";
        public static final String FUNCTION_TTS = "TTS";
    }

    public static final class MicRequestType {
        public static final int REQUEST_TYPE_CALL = 2;
        public static final int REQUEST_TYPE_ECALL = 0;
        public static final int REQUEST_TYPE_RECORD = 3;
        public static final int REQUEST_TYPE_SYSTEM = 1;
    }

    public static final class MicState {
        public static final int MIC_STATE_HOLD = 1;
        public static final int MIC_STATE_IDLE = 2;
    }

    public AudioPolicy(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
    }

    @Override // com.ecarx.xui.adaptapi.policy.IAudioPolicy
    public int getAudioStreamTypeForFunction(String str) {
        Log.d(TAG, "getAudioStreamTypeForFunction audioFunction = " + str);
        if (str != null && !str.equals("")) {
            int i = 9;
            if (str.equals(AudioFunction.FUNCTION_AUDIO_ASSISTANT)) {
                return 9;
            }
            if (str.equals(AudioFunction.FUNCTION_RADIO_FM)) {
                i = 14;
            } else if (str.equals(AudioFunction.FUNCTION_RADIO_AM)) {
                i = 13;
            } else if (str.equals(AudioFunction.FUNCTION_BLUETOOTH_HFP)) {
                i = 6;
            } else if (str.equals(AudioFunction.FUNCTION_NAVIGATION_GUIDE)) {
                i = 10;
            } else if (str.equals(AudioFunction.FUNCTION_NAVIGATION_TURN_HINT)) {
                i = 4;
            } else if (!str.equals(AudioFunction.FUNCTION_TTS)) {
                if (str.equals("AUX")) {
                    Log.d(TAG, " STREAM_AUX was cancel");
                    return 0;
                }
                if (str.equals(AudioFunction.FUNCTION_CALL_ECALL)) {
                    i = 11;
                } else if (str.equals(AudioFunction.FUNCTION_CALL_ONCALL) || str.equals(AudioFunction.FUNCTION_CALL_ICALL) || str.equals(AudioFunction.FUNCTION_CALL_BCALL)) {
                    Log.d(TAG, "STREAM_ICALL was cancel");
                } else if (str.equals(AudioFunction.FUNCTION_STREAM_BT_AUDIO)) {
                    i = 3;
                } else {
                    if (!str.equals(AudioFunction.FUNCTION_PHONE_COMING_HINT)) {
                        return 0;
                    }
                    i = 15;
                }
            }
            Log.d(TAG, "getAudioStreamTypeForFunction return value = " + i);
            return i;
        }
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IAudioPolicy
    public boolean requestMic(int i, IMicResourceCallback iMicResourceCallback) {
        Log.d(TAG, "requestMic requestType = " + i);
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            Log.d(TAG, "requestMic mAudioManager is null");
            return false;
        }
        if (iMicResourceCallback == null) {
            Log.d(TAG, "requestMic callback is null");
        } else if (i == 2 || i == 3 || i == 1) {
            int iRequestAudioFocus = audioManager.requestAudioFocus(this.mOnChangeListener, -3, 2);
            Log.d(TAG, "requestAudioFocus返回的值，1是成功：" + iRequestAudioFocus);
            this.mMicResourceCallback_ecarx = iMicResourceCallback;
            return iRequestAudioFocus == 1;
        }
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IAudioPolicy
    public boolean releaseMic(IMicResourceCallback iMicResourceCallback) {
        Log.d(TAG, "releaseMic ");
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            Log.d(TAG, "requestMic mAudioManager is null");
            return false;
        }
        if (iMicResourceCallback != null) {
            int iAbandonAudioFocus = audioManager.abandonAudioFocus(this.mOnChangeListener);
            Log.d(TAG, "releaseMic result = " + iAbandonAudioFocus);
            return iAbandonAudioFocus == 1;
        }
        Log.d(TAG, "requestMic callback is null");
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IAudioPolicy
    public int getMicState() {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            Log.d(TAG, "requestMic mAudioManager is null");
            return -1;
        }
        int iRequestAudioFocus = audioManager.requestAudioFocus(this.mOnChangeListener, -2, 2);
        Log.d(TAG, "requestAudioFocus返回的值，1是成功：" + iRequestAudioFocus);
        int i = iRequestAudioFocus != 1 ? 1 : 2;
        Log.d(TAG, "getMicState result = " + i);
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IAudioPolicy
    public void micFocusGot() {
        Log.d(TAG, "micFocusGot");
    }

    @Override // com.ecarx.xui.adaptapi.policy.IAudioPolicy
    public boolean registerMicStateChange(IMicStateCallback iMicStateCallback) {
        Log.d(TAG, "registerMicStateChange");
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            Log.d(TAG, "requestMic mAudioManager is null");
            return false;
        }
        if (iMicStateCallback != null) {
            this.mMicStateCallback_ecax = iMicStateCallback;
            int iRequestAudioFocus = audioManager.requestAudioFocus(this.mOnChangeListenerVR, -2, 1);
            Log.d(TAG, "requestAudioFocus返回的值，1是成功：" + iRequestAudioFocus);
            return iRequestAudioFocus == 1;
        }
        Log.d(TAG, "requestMic callback is null");
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IAudioPolicy
    public boolean unregisterMicStateChange(IMicStateCallback iMicStateCallback) {
        Log.d(TAG, "unregisterMicStateChange");
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            Log.d(TAG, "requestMic mAudioManager is null");
            return false;
        }
        if (iMicStateCallback != null) {
            int iAbandonAudioFocus = audioManager.abandonAudioFocus(this.mOnChangeListenerVR);
            Log.d(TAG, "unregisterMicStateChange result = " + iAbandonAudioFocus);
            return iAbandonAudioFocus == 1;
        }
        Log.d(TAG, "requestMic callback is null");
        return false;
    }

    public boolean isKCHighProject() {
        String str = SystemProperties.get("ro.product.device");
        Log.d(TAG, "getProject mProject = " + str);
        return str.equals(VoiceAssistantPolicy.KC2QJ) || str.equals(VoiceAssistantPolicy.KC2HBZG);
    }
}
