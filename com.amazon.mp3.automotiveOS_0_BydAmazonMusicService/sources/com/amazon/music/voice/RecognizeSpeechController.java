package com.amazon.music.voice;

import android.os.Handler;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.alexa.voice.core.channel.Channel;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;

/* JADX INFO: loaded from: classes4.dex */
public class RecognizeSpeechController {
    private Channel channel;
    private EarconSpeechRecordingListener earconSpeechRecordingListener;
    private SpeechRecognizerPlugin speechRecognizerPlugin;
    private long recognizeSpeechTimeout = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
    private Handler handler = new Handler();
    private Runnable recognizeSpeechRunnable = new Runnable() { // from class: com.amazon.music.voice.RecognizeSpeechController.1
        @Override // java.lang.Runnable
        public void run() {
            RecognizeSpeechController.this.speechRecognizerPlugin.recognizeSpeech(RecognizeSpeechController.this.recognizeSpeechTimeout);
        }
    };

    public RecognizeSpeechController(Channel channel, SpeechRecognizerPlugin speechRecognizerPlugin) {
        this.channel = channel;
        this.speechRecognizerPlugin = speechRecognizerPlugin;
    }

    private void acquireChannel() {
        this.channel.acquireActivation();
        this.channel.bringToForeground();
    }

    private void releaseChannel() {
        this.channel.bringToBackground();
        this.channel.releaseActivation();
    }

    public void startRecognizeSpeech() {
        startRecognizeSpeech(CoroutineLiveDataKt.DEFAULT_TIMEOUT, true);
    }

    public void startRecognizeSpeech(boolean z) {
        startRecognizeSpeech(CoroutineLiveDataKt.DEFAULT_TIMEOUT, z);
    }

    public void startRecognizeSpeech(long j, boolean z) {
        acquireChannel();
        notifySpeechRecordingStarted(z);
        this.recognizeSpeechTimeout = j;
        this.handler.postDelayed(this.recognizeSpeechRunnable, 200L);
    }

    public void stopRecognizeSpeech() {
        this.handler.removeCallbacks(this.recognizeSpeechRunnable);
        releaseChannel();
    }

    public void addEarconSpeechRecordingListener(EarconSpeechRecordingListener earconSpeechRecordingListener) {
        this.earconSpeechRecordingListener = earconSpeechRecordingListener;
    }

    private void notifySpeechRecordingStarted(boolean z) {
        EarconSpeechRecordingListener earconSpeechRecordingListener = this.earconSpeechRecordingListener;
        if (earconSpeechRecordingListener != null) {
            earconSpeechRecordingListener.onSpeechRecordingTaskStarted(z);
        }
    }
}
