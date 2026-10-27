package defpackage;

import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: InsecureDownloadDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class k01 implements di1.a {
    public final /* synthetic */ Callback e;
    public final /* synthetic */ bi1 f;

    public k01(ze0 ze0Var, bi1 bi1Var) {
        this.e = ze0Var;
        this.f = bi1Var;
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 1 || i == 2) {
            return;
        }
        Callback callback = this.e;
        if (callback != null) {
            callback.onResult(Boolean.FALSE);
        }
        oj2.U(3, 4, "Download.MixedContentDialog.Events");
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        boolean z = i == 0;
        Callback callback = this.e;
        if (callback != null) {
            callback.onResult(Boolean.valueOf(z));
        }
        this.f.b(z ? 1 : 2, propertyModel);
        oj2.U(z ? 1 : 2, 4, "Download.MixedContentDialog.Events");
    }
}
