package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.a1;
import org.chromium.chrome.browser.tasks.tab_management.e1;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class mw implements k.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ mw(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.ui.modelutil.k.a
    public final void a(k kVar, Object obj) {
        int i = this.e;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                ((nw) obj2).d.h.run();
                break;
            case 1:
                a1 a1Var = (a1) obj2;
                v62 v62Var = (v62) obj;
                a1Var.getClass();
                if (v62Var == e1.a) {
                    a1Var.q.r(Boolean.valueOf(a1Var.e()));
                }
                break;
            default:
                C0219x0 c0219x0 = (C0219x0) obj2;
                v62 v62Var2 = (v62) obj;
                PropertyModel.p pVar = C0221y0.e;
                if (pVar != v62Var2) {
                    c0219x0.getClass();
                } else {
                    c0219x0.o.r(Boolean.valueOf(c0219x0.i.h(pVar) == 2));
                }
                break;
        }
    }
}
