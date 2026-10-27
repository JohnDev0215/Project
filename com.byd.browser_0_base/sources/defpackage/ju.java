package defpackage;

import defpackage.di1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CloseAllTabsDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ju implements di1.a {
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ bi1 f;
    public final /* synthetic */ boolean g;

    public ju(Runnable runnable, bi1 bi1Var, boolean z) {
        this.e = runnable;
        this.f = bi1Var;
        this.g = z;
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 5) {
            n3.X0("MobileCloseAllTabsDialog.CancelledWithTouchOutside");
        }
        oj2.O(this.g ? "Tab.CloseAllTabsDialog.ClosedAllTabs.Incognito" : "Tab.CloseAllTabsDialog.ClosedAllTabs.NonIncognito", i == 1);
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        bi1 bi1Var = this.f;
        if (i == 0) {
            this.e.run();
            n3.X0("MobileCloseAllTabsDialog.ClosedAllTabs");
            bi1Var.b(1, propertyModel);
        } else if (i == 1) {
            n3.X0("MobileCloseAllTabsDialog.Cancelled");
            bi1Var.b(2, propertyModel);
        }
    }
}
