package defpackage;

import defpackage.kw;
import org.chromium.chrome.browser.tasks.tab_management.q1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class y43 implements kw.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ y51 b;

    public /* synthetic */ y43(y51 y51Var, int i) {
        this.a = i;
        this.b = y51Var;
    }

    @Override // kw.a
    public final void a(kw kwVar) {
        int i = this.a;
        y51 y51Var = this.b;
        switch (i) {
            case 0:
                q1 q1Var = (q1) y51Var;
                q1Var.getClass();
                q1Var.G = kwVar.b();
                break;
            default:
                l43 l43Var = (l43) y51Var;
                l43Var.getClass();
                l43Var.G = kwVar.b();
                break;
        }
    }
}
