package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class tb3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ yb3 f;

    public /* synthetic */ tb3(yb3 yb3Var, int i) {
        this.e = i;
        this.f = yb3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        yb3 yb3Var = this.f;
        switch (i) {
            case 0:
                zp1 zp1Var = yb3Var.n;
                yb3Var.a((Tab) zp1Var.get(), "IPH_DownloadPageScreenshot");
                af2 af2VarY1 = af2.y1((Tab) zp1Var.get());
                if (af2VarY1 != null) {
                    n3.X0("Tab.Screenshot");
                    af2VarY1.e++;
                }
                break;
            case 1:
                h7 h7Var = yb3Var.l;
                if (h7Var != null) {
                    ((i7) h7Var).c(null);
                }
                break;
            default:
                h7 h7Var2 = yb3Var.l;
                if (h7Var2 != null) {
                    ((i7) h7Var2).c(null);
                }
                break;
        }
    }
}
