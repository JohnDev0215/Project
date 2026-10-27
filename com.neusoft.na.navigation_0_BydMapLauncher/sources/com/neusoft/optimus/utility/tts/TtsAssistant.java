package com.neusoft.optimus.utility.tts;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class TtsAssistant {
    private static final String cTag = "TtsAssistant";
    private static TtsAssistant mTtsAssistant = new TtsAssistant();
    private AudioManager mAudioManager;
    private Context mContext;
    private TextToSpeech mSpeech;
    private HashMap<String, String> utteranceMap = new HashMap<>();
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.neusoft.optimus.utility.tts.TtsAssistant.3
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            Log.d(TtsAssistant.cTag, "audioFocus changed : " + i);
            if (i == -3 || i == -2 || i == -1) {
                if (TtsAssistant.this.mSpeech != null) {
                    TtsAssistant.this.mSpeech.stop();
                }
                TtsAssistant.this.releaseAudioFocus();
            }
        }
    };

    public static TtsAssistant getInstance() {
        return mTtsAssistant;
    }

    public void connectTTSEngine(Context context) {
        this.mContext = context;
        initTtsAssistant();
    }

    private void initTtsAssistant() {
        TextToSpeech textToSpeech = new TextToSpeech(this.mContext, new TextToSpeech.OnInitListener() { // from class: com.neusoft.optimus.utility.tts.TtsAssistant.1
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public void onInit(int i) {
                Log.d(TtsAssistant.cTag, "TextToSpeech onInit");
            }
        }, "com.ecarx.xcttsengine");
        this.mSpeech = textToSpeech;
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: com.neusoft.optimus.utility.tts.TtsAssistant.2
            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String str) {
                Log.d(TtsAssistant.cTag, "mSpeech onStart s:" + str);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String str) {
                Log.d(TtsAssistant.cTag, "mSpeech onDone s:" + str);
                TtsAssistant.this.releaseAudioFocus();
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String str) {
                Log.d(TtsAssistant.cTag, "mSpeech onError s:" + str);
                TtsAssistant.this.releaseAudioFocus();
            }
        });
        this.mAudioManager = (AudioManager) this.mContext.getSystemService(Car.AUDIO_SERVICE);
    }

    public void playTTS(String str) {
        if (requestAudioFocus(9)) {
            Log.d(cTag, "playTTS, content:" + str);
            if (this.mSpeech != null) {
                this.utteranceMap.put("streamType", String.valueOf(9));
                this.utteranceMap.put("utteranceId", str);
                int iSpeak = this.mSpeech.speak(str, 0, this.utteranceMap);
                Log.d(cTag, "playTTS, tts speak result:" + iSpeak);
                if (iSpeak != 0) {
                    releaseAudioFocus();
                    return;
                }
                return;
            }
            return;
        }
        releaseAudioFocus();
        Log.d(cTag, "requestFocus Fail");
    }

    public void stop() {
        TextToSpeech textToSpeech = this.mSpeech;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
        releaseAudioFocus();
    }

    public void releaseAudioFocus() {
        Log.d(cTag, "audioFocus------------>release");
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.abandonAudioFocus(this.afChangeListener);
        }
    }

    private boolean requestAudioFocus(int i) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            return false;
        }
        if (audioManager.requestAudioFocus(this.afChangeListener, i, 2) == 1) {
            Log.d(cTag, "requestFocus--------->true");
            return true;
        }
        Log.d(cTag, "requestFocus-------->false");
        return false;
    }
}
