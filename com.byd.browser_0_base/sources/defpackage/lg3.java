package defpackage;

import android.speech.tts.UtteranceProgressListener;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: compiled from: TtsPlatformImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lg3 extends UtteranceProgressListener {
    public final /* synthetic */ TtsPlatformImpl a;

    public lg3(TtsPlatformImpl ttsPlatformImpl) {
        this.a = ttsPlatformImpl;
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public final void onDone(String str) {
        TtsPlatformImpl ttsPlatformImpl = this.a;
        ttsPlatformImpl.getClass();
        PostTask.d(rh3.a, new kg3(ttsPlatformImpl, str, 0));
    }

    @Override // android.speech.tts.UtteranceProgressListener
    @Deprecated
    public final void onError(String str) {
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public final void onStart(String str) {
        TtsPlatformImpl ttsPlatformImpl = this.a;
        ttsPlatformImpl.getClass();
        PostTask.d(rh3.a, new ed2(ttsPlatformImpl, 2, str));
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public final void onError(String str, int i) {
        TtsPlatformImpl ttsPlatformImpl = this.a;
        ttsPlatformImpl.getClass();
        PostTask.d(rh3.a, new ul2(ttsPlatformImpl, 5, str));
    }
}
