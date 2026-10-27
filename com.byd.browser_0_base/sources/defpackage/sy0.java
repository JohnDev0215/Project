package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.a;
import org.chromium.chrome.browser.tasks.tab_management.c0;
import org.chromium.chrome.browser.tasks.tab_management.o1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class sy0 implements MessageCardView.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ sy0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.a
    public final void a(int i) {
        int i2 = this.a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((a) obj).i();
                break;
            default:
                o1 o1Var = (o1) obj;
                c0 c0Var = o1Var.i;
                if (i == 3 || i == 4) {
                    c0Var.g(6, i);
                } else {
                    c0Var.g(3, i);
                    o1Var.u(i);
                }
                break;
        }
    }
}
