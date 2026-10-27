package defpackage;

import org.chromium.chrome.browser.ntp.a;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TasksSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m73 implements n1.d {
    public sr1 e;
    public final a f;
    public final PropertyModel g;

    public m73(PropertyModel propertyModel, h02 h02Var, a aVar, boolean z) {
        this.g = propertyModel;
        propertyModel.l(n73.g, z);
        propertyModel.l(n73.h, z);
        propertyModel.p(n73.o, h02Var);
        this.f = aVar;
        propertyModel.p(n73.n, aVar);
        propertyModel.l(n73.f, true);
        propertyModel.l(n73.a, true);
        propertyModel.l(n73.i, false);
        propertyModel.l(n73.e, false);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void d() {
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void e() {
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void k() {
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void s() {
    }
}
