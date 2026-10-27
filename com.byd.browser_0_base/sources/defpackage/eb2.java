package defpackage;

import defpackage.di1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: RequestDesktopUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eb2 implements di1.a {
    public final /* synthetic */ bi1 e;
    public final /* synthetic */ xe3 f;

    public eb2(bi1 bi1Var, xe3 xe3Var) {
        this.e = bi1Var;
        this.f = xe3Var;
    }

    @Override // di1.a
    public final void b(int i) {
        this.f.dismissed("IPH_RequestDesktopSiteAppMenu");
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 0) {
            this.e.b(1, propertyModel);
        }
    }
}
