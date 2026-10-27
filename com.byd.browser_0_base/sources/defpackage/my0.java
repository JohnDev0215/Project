package defpackage;

import android.view.View;
import defpackage.di1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: IncognitoReauthDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class my0 {
    public final bi1 a;
    public final PropertyModel b;

    public my0(bi1 bi1Var, View view, xr1 xr1Var) {
        a aVar = new a();
        this.a = bi1Var;
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, aVar);
        bVar.e(di1.h, view);
        bVar.b(di1.q, false);
        bVar.b(di1.v, true);
        bVar.b(di1.y, true);
        bVar.e(di1.z, xr1Var);
        this.b = bVar.a();
    }

    /* JADX INFO: compiled from: IncognitoReauthDialog.java */
    public class a implements di1.a {
        @Override // di1.a
        public final void b(int i) {
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
        }
    }
}
