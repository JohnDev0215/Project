package defpackage;

import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SimpleModalDialogController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ym2 implements di1.a {
    public final bi1 e;
    public Callback<Integer> f;

    public ym2(bi1 bi1Var, Callback<Integer> callback) {
        this.e = bi1Var;
        this.f = callback;
    }

    @Override // di1.a
    public final void b(int i) {
        Callback<Integer> callback = this.f;
        this.f = null;
        callback.onResult(Integer.valueOf(i));
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        bi1 bi1Var = this.e;
        if (i == 0) {
            bi1Var.b(1, propertyModel);
        } else {
            bi1Var.b(2, propertyModel);
        }
    }
}
