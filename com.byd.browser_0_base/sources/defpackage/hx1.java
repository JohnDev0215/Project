package defpackage;

import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordGenerationDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hx1 implements di1.a {
    public final Callback<Boolean> e;

    public hx1(ko0 ko0Var) {
        this.e = ko0Var;
    }

    @Override // di1.a
    public final void b(int i) {
        this.e.onResult(Boolean.FALSE);
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        Callback<Boolean> callback = this.e;
        if (i == 0) {
            callback.onResult(Boolean.TRUE);
        } else {
            if (i != 1) {
                return;
            }
            callback.onResult(Boolean.FALSE);
        }
    }
}
