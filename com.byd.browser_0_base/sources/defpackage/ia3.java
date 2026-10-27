package defpackage;

import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TileMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ia3 {
    public final x93 a;
    public final Callback<gx0> b;
    public final hx0 c;

    public ia3(x93 x93Var, da3 da3Var, td2 td2Var, p82 p82Var) {
        this.a = x93Var;
        this.b = td2Var;
        this.c = p82Var;
        PropertyModel propertyModel = da3Var.g;
        propertyModel.p(ea3.a, new l51(10, this));
        propertyModel.p(ea3.b, new p82(1, this));
    }
}
