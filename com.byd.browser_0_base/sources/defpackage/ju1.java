package defpackage;

import org.chromium.components.page_info.PageInfoController;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class ju1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ PageInfoController f;

    public /* synthetic */ ju1(PageInfoController pageInfoController, int i) {
        this.e = i;
        this.f = pageInfoController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        PageInfoController pageInfoController = this.f;
        switch (i) {
            case 0:
                pu1 pu1Var = pageInfoController.l;
                if (pu1Var != null) {
                    pu1Var.b.dismiss();
                }
                break;
            default:
                xu1 xu1Var = pageInfoController.r;
                if (xu1Var != null) {
                    xu1Var.f();
                    pageInfoController.r.d();
                    pageInfoController.r = null;
                    break;
                }
                break;
        }
    }
}
