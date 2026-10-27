package defpackage;

import android.speech.tts.UtteranceProgressListener;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: tz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1575tz extends UtteranceProgressListener {
    public final /* synthetic */ C1630uz a;

    public C1575tz(C1630uz c1630uz) {
        this.a = c1630uz;
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onDone(String str) {
        C1630uz c1630uz = this.a;
        Objects.requireNonNull(c1630uz);
        PostTask.b(AbstractC0538bE.a, new Jz(c1630uz, str));
    }

    @Override // android.speech.tts.UtteranceProgressListener
    @Deprecated
    public void onError(String str) {
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onError(String str, int i) {
        C1630uz c1630uz = this.a;
        Objects.requireNonNull(c1630uz);
        PostTask.b(AbstractC0538bE.a, new Kz(c1630uz, str));
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onStart(String str) {
        C1630uz c1630uz = this.a;
        Objects.requireNonNull(c1630uz);
        PostTask.b(AbstractC0538bE.a, new Lz(c1630uz, str));
    }
}
