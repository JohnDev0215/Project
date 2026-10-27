package defpackage;

import J.N;
import android.os.Bundle;
import android.speech.RecognitionListener;
import java.util.ArrayList;
import org.chromium.content.browser.SpeechRecognitionImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Gz implements RecognitionListener {
    public final /* synthetic */ SpeechRecognitionImpl a;

    public Gz(SpeechRecognitionImpl speechRecognitionImpl) {
        this.a = speechRecognitionImpl;
    }

    public final void a(Bundle bundle, boolean z) {
        boolean z2 = (this.a.f && z) ? false : z;
        ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
        String[] strArr = (String[]) stringArrayList.toArray(new String[stringArrayList.size()]);
        float[] floatArray = bundle.getFloatArray("confidence_scores");
        SpeechRecognitionImpl speechRecognitionImpl = this.a;
        N.MnkvkoGY(speechRecognitionImpl.e, speechRecognitionImpl, strArr, floatArray, z2);
    }

    @Override // android.speech.RecognitionListener
    public void onBeginningOfSpeech() {
        this.a.a = 2;
        SpeechRecognitionImpl speechRecognitionImpl = this.a;
        N.M2Nl8ID9(speechRecognitionImpl.e, speechRecognitionImpl);
    }

    @Override // android.speech.RecognitionListener
    public void onBufferReceived(byte[] bArr) {
    }

    @Override // android.speech.RecognitionListener
    public void onEndOfSpeech() {
        SpeechRecognitionImpl speechRecognitionImpl = this.a;
        if (speechRecognitionImpl.f) {
            return;
        }
        N.MFB2QCbe(speechRecognitionImpl.e, speechRecognitionImpl);
        SpeechRecognitionImpl speechRecognitionImpl2 = this.a;
        N.M2TRi32V(speechRecognitionImpl2.e, speechRecognitionImpl2);
        this.a.a = 0;
    }

    @Override // android.speech.RecognitionListener
    public void onError(int i) {
        int i2;
        switch (i) {
            case 1:
            case 2:
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                i2 = 4;
                break;
            case 3:
                i2 = 3;
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                i2 = 2;
                break;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
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
        this.a.a(i2);
    }

    @Override // android.speech.RecognitionListener
    public void onEvent(int i, Bundle bundle) {
    }

    @Override // android.speech.RecognitionListener
    public void onPartialResults(Bundle bundle) {
        a(bundle, true);
    }

    @Override // android.speech.RecognitionListener
    public void onReadyForSpeech(Bundle bundle) {
        this.a.a = 1;
        SpeechRecognitionImpl speechRecognitionImpl = this.a;
        N.MdhaXGcn(speechRecognitionImpl.e, speechRecognitionImpl);
    }

    @Override // android.speech.RecognitionListener
    public void onResults(Bundle bundle) {
        a(bundle, false);
        this.a.a(0);
    }

    @Override // android.speech.RecognitionListener
    public void onRmsChanged(float f) {
    }
}
