package org.chromium.content.browser;

import J.N;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import defpackage.xz;
import defpackage.zt1;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.R$string;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
public class SpeechRecognitionImpl {
    public static ComponentName f;
    public int a;
    public final Intent b;
    public SpeechRecognizer c;
    public long d;
    public boolean e = false;

    public SpeechRecognitionImpl(long j) {
        this.d = j;
        a aVar = new a();
        this.b = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        ComponentName componentName = f;
        if (componentName != null) {
            this.c = SpeechRecognizer.createSpeechRecognizer(xz.a, componentName);
        } else {
            this.c = SpeechRecognizer.createSpeechRecognizer(xz.a);
        }
        this.c.setRecognitionListener(aVar);
    }

    @SuppressLint({"WrongConstant"})
    public static ComponentName a(int i, String str) {
        Iterator<ResolveInfo> it = xz.a.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 4).iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            if (serviceInfo.packageName.equals(str) && (i == -1 || zt1.d(serviceInfo.packageName) >= i)) {
                return new ComponentName(serviceInfo.packageName, serviceInfo.name);
            }
        }
        return null;
    }

    @CalledByNative
    public static SpeechRecognitionImpl createSpeechRecognition(long j) {
        return new SpeechRecognitionImpl(j);
    }

    @CalledByNative
    public final void abortRecognition() {
        SpeechRecognizer speechRecognizer = this.c;
        if (speechRecognizer == null) {
            return;
        }
        speechRecognizer.cancel();
        b(2);
    }

    public final void b(int i) {
        long j = this.d;
        if (j == 0) {
            return;
        }
        int i2 = this.a;
        if (i2 != 0) {
            if (i2 == 2) {
                N.MFB2QCbe(j, this);
            }
            N.M2TRi32V(this.d, this);
            this.a = 0;
        }
        if (i != 0) {
            N.MUf_fHKN(this.d, this, i);
        }
        try {
            this.c.destroy();
        } catch (IllegalArgumentException e) {
            Log.w("cr_SpeechRecog", "Destroy threw exception " + this.c, e);
        }
        this.c = null;
        N.MmgQYR9M(this.d, this);
        this.d = 0L;
    }

    @CalledByNative
    public final void startRecognition(String str, boolean z, boolean z2) {
        if (this.c == null) {
            return;
        }
        this.e = z;
        Intent intent = this.b;
        intent.putExtra("android.speech.extra.DICTATION_MODE", z);
        intent.putExtra("android.speech.extra.LANGUAGE", str);
        intent.putExtra("android.speech.extra.PARTIAL_RESULTS", z2);
        try {
            this.c.startListening(intent);
        } catch (SecurityException unused) {
            Context context = xz.a;
            Toast.b(0, context, context.getResources().getString(R$string.speech_recognition_service_not_found)).e();
        }
    }

    @CalledByNative
    public final void stopRecognition() {
        SpeechRecognizer speechRecognizer = this.c;
        if (speechRecognizer == null) {
            return;
        }
        this.e = false;
        speechRecognizer.stopListening();
    }

    public class a implements RecognitionListener {
        public a() {
        }

        public final void a(Bundle bundle, boolean z) {
            SpeechRecognitionImpl speechRecognitionImpl = SpeechRecognitionImpl.this;
            if (speechRecognitionImpl.d == 0) {
                return;
            }
            if (speechRecognitionImpl.e && z) {
                z = false;
            }
            boolean z2 = z;
            ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
            String[] strArr = (String[]) stringArrayList.toArray(new String[stringArrayList.size()]);
            float[] floatArray = bundle.getFloatArray("confidence_scores");
            SpeechRecognitionImpl speechRecognitionImpl2 = SpeechRecognitionImpl.this;
            N.MnkvkoGY(speechRecognitionImpl2.d, speechRecognitionImpl2, strArr, floatArray, z2);
        }

        @Override // android.speech.RecognitionListener
        public final void onBeginningOfSpeech() {
            SpeechRecognitionImpl speechRecognitionImpl = SpeechRecognitionImpl.this;
            long j = speechRecognitionImpl.d;
            if (j == 0) {
                return;
            }
            speechRecognitionImpl.a = 2;
            N.M2Nl8ID9(j, speechRecognitionImpl);
        }

        @Override // android.speech.RecognitionListener
        public final void onEndOfSpeech() {
            SpeechRecognitionImpl speechRecognitionImpl = SpeechRecognitionImpl.this;
            if (speechRecognitionImpl.e) {
                return;
            }
            long j = speechRecognitionImpl.d;
            if (j == 0) {
                return;
            }
            N.MFB2QCbe(j, speechRecognitionImpl);
            N.M2TRi32V(speechRecognitionImpl.d, speechRecognitionImpl);
            speechRecognitionImpl.a = 0;
        }

        @Override // android.speech.RecognitionListener
        public final void onError(int i) {
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
            SpeechRecognitionImpl.this.b(i2);
        }

        @Override // android.speech.RecognitionListener
        public final void onPartialResults(Bundle bundle) {
            a(bundle, true);
        }

        @Override // android.speech.RecognitionListener
        public final void onReadyForSpeech(Bundle bundle) {
            SpeechRecognitionImpl speechRecognitionImpl = SpeechRecognitionImpl.this;
            long j = speechRecognitionImpl.d;
            if (j == 0) {
                return;
            }
            speechRecognitionImpl.a = 1;
            N.MdhaXGcn(j, speechRecognitionImpl);
        }

        @Override // android.speech.RecognitionListener
        public final void onResults(Bundle bundle) {
            a(bundle, false);
            SpeechRecognitionImpl.this.b(0);
        }

        @Override // android.speech.RecognitionListener
        public final void onBufferReceived(byte[] bArr) {
        }

        @Override // android.speech.RecognitionListener
        public final void onRmsChanged(float f) {
        }

        @Override // android.speech.RecognitionListener
        public final void onEvent(int i, Bundle bundle) {
        }
    }
}
