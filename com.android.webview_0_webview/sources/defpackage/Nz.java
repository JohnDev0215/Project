package defpackage;

import android.speech.tts.UtteranceProgressListener;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Nz extends UtteranceProgressListener {
    public final /* synthetic */ TtsPlatformImpl a;

    public Nz(TtsPlatformImpl ttsPlatformImpl) {
        this.a = ttsPlatformImpl;
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onDone(String str) {
        TtsPlatformImpl ttsPlatformImpl = this.a;
        Objects.requireNonNull(ttsPlatformImpl);
        PostTask.b(AbstractC0538bE.a, new Jz(ttsPlatformImpl, str));
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onError(String str) {
        TtsPlatformImpl ttsPlatformImpl = this.a;
        Objects.requireNonNull(ttsPlatformImpl);
        PostTask.b(AbstractC0538bE.a, new Kz(ttsPlatformImpl, str));
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onStart(String str) {
        TtsPlatformImpl ttsPlatformImpl = this.a;
        Objects.requireNonNull(ttsPlatformImpl);
        PostTask.b(AbstractC0538bE.a, new Lz(ttsPlatformImpl, str));
    }
}
