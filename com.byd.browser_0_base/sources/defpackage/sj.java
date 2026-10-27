package defpackage;

import defpackage.qj;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: BrowserStartupControllerImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sj implements Runnable {
    public final /* synthetic */ qj.a e;
    public final /* synthetic */ BrowserStartupControllerImpl f;

    public sj(BrowserStartupControllerImpl browserStartupControllerImpl, qj.a aVar) {
        this.f = browserStartupControllerImpl;
        this.e = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.f.g;
        qj.a aVar = this.e;
        if (z) {
            aVar.b();
        } else {
            aVar.a();
        }
    }
}
