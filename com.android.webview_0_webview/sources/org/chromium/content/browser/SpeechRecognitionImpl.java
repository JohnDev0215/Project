package org.chromium.content.browser;

import J.N;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import defpackage.Gz;
import defpackage.Is;
import defpackage.Ts;
import defpackage.Z7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SpeechRecognitionImpl {
    public int a;
    public SpeechRecognizer d;
    public long e;
    public boolean f = false;
    public final RecognitionListener c = new Gz(this);
    public final Intent b = new Intent("android.speech.action.RECOGNIZE_SPEECH");

    public SpeechRecognitionImpl(long j) {
        this.e = j;
        SpeechRecognizer speechRecognizerCreateSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(Is.a);
        this.d = speechRecognizerCreateSpeechRecognizer;
        speechRecognizerCreateSpeechRecognizer.setRecognitionListener(this.c);
    }

    private void abortRecognition() {
        SpeechRecognizer speechRecognizer = this.d;
        if (speechRecognizer == null) {
            return;
        }
        speechRecognizer.cancel();
        a(2);
    }

    public static SpeechRecognitionImpl createSpeechRecognition(long j) {
        return new SpeechRecognitionImpl(j);
    }

    private void startRecognition(String str, boolean z, boolean z2) {
        if (this.d == null) {
            return;
        }
        this.f = z;
        this.b.putExtra("android.speech.extra.DICTATION_MODE", z);
        this.b.putExtra("android.speech.extra.LANGUAGE", str);
        this.b.putExtra("android.speech.extra.PARTIAL_RESULTS", z2);
        this.d.startListening(this.b);
    }

    private void stopRecognition() {
        SpeechRecognizer speechRecognizer = this.d;
        if (speechRecognizer == null) {
            return;
        }
        this.f = false;
        speechRecognizer.stopListening();
    }

    public final void a(int i) {
        long j = this.e;
        if (j == 0) {
            return;
        }
        int i2 = this.a;
        if (i2 != 0) {
            if (i2 == 2) {
                N.MFB2QCbe(j, this);
            }
            N.M2TRi32V(this.e, this);
            this.a = 0;
        }
        if (i != 0) {
            N.MUf_fHKN(this.e, this, i);
        }
        try {
            this.d.destroy();
        } catch (IllegalArgumentException e) {
            StringBuilder sbF = Z7.f("Destroy threw exception ");
            sbF.append(this.d);
            Ts.f("SpeechRecog", sbF.toString(), e);
        }
        this.d = null;
        N.MmgQYR9M(this.e, this);
        this.e = 0L;
    }
}
