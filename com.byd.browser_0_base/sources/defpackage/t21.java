package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.b;
import org.chromium.chrome.browser.tasks.tab_management.d;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class t21 implements MessageCardView.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ t21(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.a
    public final void a(int i) {
        int i2 = this.a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                xe3 xe3Var = ((b) obj).h;
                xe3Var.shouldTriggerHelpUI("IPH_TabGroupsDragAndDrop");
                xe3Var.dismissed("IPH_TabGroupsDragAndDrop");
                break;
            default:
                ((d) obj).c(i);
                break;
        }
    }
}
