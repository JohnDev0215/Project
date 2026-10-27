package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LoadProgressMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f91 {
    public final PropertyModel a;
    public final i20 b;
    public final h91 c;
    public final boolean d;
    public boolean e;

    /* JADX INFO: compiled from: LoadProgressMediator.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (navigationHandle.c) {
                return;
            }
            boolean zH = jk1.h(navigationHandle.e, tabImpl.isIncognito());
            f91 f91Var = f91.this;
            if (zH) {
                f91Var.a(false);
                return;
            }
            h91 h91Var = f91Var.c;
            PropertyModel.p pVar = g91.a;
            h91Var.a.n(pVar, 2);
            h91Var.b.removeMessages(1);
            if (!f91Var.e) {
                f91Var.a.n(pVar, 0);
            }
            f91Var.c(tabImpl.q());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void I0(TabImpl tabImpl) {
            f91.this.a(false);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void L0(TabImpl tabImpl, boolean z) {
            if (z) {
                float fQ = tabImpl.q();
                f91 f91Var = f91.this;
                if (fQ > 0.05f && tabImpl.q() < 1.0f) {
                    f91Var.c(1.0f);
                }
                f91Var.a(true);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void Q(Tab tab, boolean z, boolean z2) {
            if (z && z2) {
                f91 f91Var = f91.this;
                if (f91Var.e) {
                    return;
                }
                h91 h91Var = f91Var.c;
                h91Var.c = 0.0f;
                PropertyModel.p pVar = g91.a;
                PropertyModel propertyModel = h91Var.a;
                propertyModel.n(pVar, 0);
                propertyModel.m(g91.b, h91Var.c);
                h91Var.b.sendEmptyMessage(1);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void l1(Tab tab, float f) {
            if (tab.getUrl() == null || bk3.l(tab.getUrl()) || jk1.h(tab.getUrl(), tab.isIncognito())) {
                return;
            }
            f91.this.c(f);
        }
    }

    public f91(zp1<Tab> zp1Var, PropertyModel propertyModel, boolean z) {
        this.a = propertyModel;
        this.c = new h91(propertyModel);
        this.d = z;
        this.b = new i20(zp1Var, new a(), new x10(6, this));
        b(zp1Var.get());
    }

    public final void a(boolean z) {
        PropertyModel.p pVar = g91.a;
        h91 h91Var = this.c;
        h91Var.a.n(pVar, 2);
        h91Var.b.removeMessages(1);
        this.a.n(pVar, z ? 1 : 2);
    }

    public final void b(Tab tab) {
        if (tab == null) {
            if (this.d) {
                a(false);
            }
        } else {
            if (!tab.k()) {
                a(false);
                return;
            }
            if (jk1.h(tab.getUrl(), tab.isIncognito())) {
                a(false);
                return;
            }
            if (!this.e) {
                this.a.n(g91.a, 0);
            }
            c(tab.q());
        }
    }

    public final void c(float f) {
        if (this.e) {
            return;
        }
        float fMax = Math.max(f, 0.05f);
        this.a.m(g91.b, fMax);
        if (a8.h(fMax, 1.0f)) {
            a(true);
        }
    }
}
