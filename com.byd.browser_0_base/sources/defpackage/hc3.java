package defpackage;

import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class hc3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ g f;

    public /* synthetic */ hc3(g gVar, int i) {
        this.e = i;
        this.f = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        g gVar = this.f;
        switch (i) {
            case 0:
                gVar.v(12, false);
                break;
            case 1:
                gVar.D();
                break;
            default:
                gVar.E(16, true);
                break;
        }
    }
}
