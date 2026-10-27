package defpackage;

import java.util.function.Consumer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.page_info.PageInfoController;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d63 implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ d63(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.a;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                g63 g63Var = (g63) obj2;
                z7.j0(g63Var.g, (Tab) obj, g63Var.T.f() && g63Var.T.get().r());
                break;
            default:
                PageInfoController pageInfoController = (PageInfoController) obj2;
                pageInfoController.p = (Runnable) obj;
                pageInfoController.l.b.dismiss();
                break;
        }
    }
}
