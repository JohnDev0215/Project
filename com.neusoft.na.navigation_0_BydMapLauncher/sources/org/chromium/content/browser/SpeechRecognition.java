package org.chromium.content.browser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.PackageUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class SpeechRecognition {
    private static final int PROVIDER_MIN_VERSION = 300207030;
    private static final String PROVIDER_PACKAGE_NAME = "com.google.android.googlequicksearchbox";
    private static final int STATE_AWAITING_SPEECH = 1;
    private static final int STATE_CAPTURING_SPEECH = 2;
    private static final int STATE_IDLE = 0;
    private static ComponentName sRecognitionProvider;
    private final Context mContext;
    private boolean mContinuous = false;
    private final Intent mIntent;
    private final RecognitionListener mListener;
    private long mNativeSpeechRecognizerImplAndroid;
    private SpeechRecognizer mRecognizer;
    private int mState;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnAudioEnd(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnAudioStart(long j);

    private native void nativeOnRecognitionEnd(long j);

    private native void nativeOnRecognitionError(long j, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnRecognitionResults(long j, String[] strArr, float[] fArr, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnSoundEnd(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnSoundStart(long j);

    class Listener implements RecognitionListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // android.speech.RecognitionListener
        public void onBufferReceived(byte[] bArr) {
        }

        @Override // android.speech.RecognitionListener
        public void onEvent(int i, Bundle bundle) {
        }

        @Override // android.speech.RecognitionListener
        public void onRmsChanged(float f) {
        }

        Listener() {
        }

        @Override // android.speech.RecognitionListener
        public void onBeginningOfSpeech() {
            SpeechRecognition.this.mState = 2;
            SpeechRecognition speechRecognition = SpeechRecognition.this;
            speechRecognition.nativeOnSoundStart(speechRecognition.mNativeSpeechRecognizerImplAndroid);
        }

        @Override // android.speech.RecognitionListener
        public void onEndOfSpeech() {
            if (SpeechRecognition.this.mContinuous) {
                return;
            }
            SpeechRecognition speechRecognition = SpeechRecognition.this;
            speechRecognition.nativeOnSoundEnd(speechRecognition.mNativeSpeechRecognizerImplAndroid);
            SpeechRecognition speechRecognition2 = SpeechRecognition.this;
            speechRecognition2.nativeOnAudioEnd(speechRecognition2.mNativeSpeechRecognizerImplAndroid);
            SpeechRecognition.this.mState = 0;
        }

        @Override // android.speech.RecognitionListener
        public void onError(int i) {
            int i2;
            switch (i) {
                case 1:
                case 2:
                case 4:
                    i2 = 4;
                    break;
                case 3:
                    i2 = 3;
                    break;
                case 5:
                    i2 = 2;
                    break;
                case 6:
                    i2 = 1;
                    break;
                case 7:
                    i2 = 9;
                    break;
                case 8:
                case 9:
                    i2 = 5;
                    break;
                default:
                    return;
            }
            SpeechRecognition.this.terminate(i2);
        }

        @Override // android.speech.RecognitionListener
        public void onPartialResults(Bundle bundle) {
            handleResults(bundle, true);
        }

        @Override // android.speech.RecognitionListener
        public void onReadyForSpeech(Bundle bundle) {
            SpeechRecognition.this.mState = 1;
            SpeechRecognition speechRecognition = SpeechRecognition.this;
            speechRecognition.nativeOnAudioStart(speechRecognition.mNativeSpeechRecognizerImplAndroid);
        }

        @Override // android.speech.RecognitionListener
        public void onResults(Bundle bundle) {
            handleResults(bundle, false);
            SpeechRecognition.this.terminate(0);
        }

        private void handleResults(Bundle bundle, boolean z) {
            if (SpeechRecognition.this.mContinuous && z) {
                z = false;
            }
            boolean z2 = z;
            ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
            String[] strArr = (String[]) stringArrayList.toArray(new String[stringArrayList.size()]);
            float[] floatArray = bundle.getFloatArray("confidence_scores");
            SpeechRecognition speechRecognition = SpeechRecognition.this;
            speechRecognition.nativeOnRecognitionResults(speechRecognition.mNativeSpeechRecognizerImplAndroid, strArr, floatArray, z2);
        }
    }

    public static boolean initialize(Context context) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            return false;
        }
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 4).iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            if (serviceInfo.packageName.equals(PROVIDER_PACKAGE_NAME) && PackageUtils.getPackageVersion(context, serviceInfo.packageName) >= PROVIDER_MIN_VERSION) {
                sRecognitionProvider = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                return true;
            }
        }
        return false;
    }

    private SpeechRecognition(Context context, long j) {
        this.mContext = context;
        this.mNativeSpeechRecognizerImplAndroid = j;
        Listener listener = new Listener();
        this.mListener = listener;
        this.mIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        ComponentName componentName = sRecognitionProvider;
        if (componentName != null) {
            this.mRecognizer = SpeechRecognizer.createSpeechRecognizer(context, componentName);
        } else {
            this.mRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        }
        this.mRecognizer.setRecognitionListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void terminate(int i) {
        int i2 = this.mState;
        if (i2 != 0) {
            if (i2 == 2) {
                nativeOnSoundEnd(this.mNativeSpeechRecognizerImplAndroid);
            }
            nativeOnAudioEnd(this.mNativeSpeechRecognizerImplAndroid);
            this.mState = 0;
        }
        if (i != 0) {
            nativeOnRecognitionError(this.mNativeSpeechRecognizerImplAndroid, i);
        }
        this.mRecognizer.destroy();
        this.mRecognizer = null;
        nativeOnRecognitionEnd(this.mNativeSpeechRecognizerImplAndroid);
        this.mNativeSpeechRecognizerImplAndroid = 0L;
    }

    private static SpeechRecognition createSpeechRecognition(Context context, long j) {
        return new SpeechRecognition(context, j);
    }

    private void startRecognition(String str, boolean z, boolean z2) {
        if (this.mRecognizer == null) {
            return;
        }
        this.mContinuous = z;
        this.mIntent.putExtra("android.speech.extra.DICTATION_MODE", z);
        this.mIntent.putExtra("android.speech.extra.LANGUAGE", str);
        this.mIntent.putExtra("android.speech.extra.PARTIAL_RESULTS", z2);
        this.mRecognizer.startListening(this.mIntent);
    }

    private void abortRecognition() {
        SpeechRecognizer speechRecognizer = this.mRecognizer;
        if (speechRecognizer == null) {
            return;
        }
        speechRecognizer.cancel();
        terminate(2);
    }

    private void stopRecognition() {
        SpeechRecognizer speechRecognizer = this.mRecognizer;
        if (speechRecognizer == null) {
            return;
        }
        this.mContinuous = false;
        speechRecognizer.stopListening();
    }
}
