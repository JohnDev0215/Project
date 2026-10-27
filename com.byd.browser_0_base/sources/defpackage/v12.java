package defpackage;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.Size;
import org.chromium.base.UnguessableToken;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.components.paintpreview.player.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PlayerFrameBitmapStateController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class v12 {
    public u12 a;
    public u12 b;
    public final UnguessableToken c;
    public final e22 d;
    public final Size e;
    public final a f;
    public final z12 g;

    public v12(UnguessableToken unguessableToken, e22 e22Var, Size size, PlayerCompositorDelegateImpl playerCompositorDelegateImpl, z12 z12Var) {
        this.c = unguessableToken;
        this.d = e22Var;
        this.e = size;
        this.f = playerCompositorDelegateImpl;
        if (playerCompositorDelegateImpl != null) {
            playerCompositorDelegateImpl.c.add(new an2(4, this));
        }
        this.g = z12Var;
    }

    public final u12 a(boolean z) {
        u12 u12Var = this.a;
        u12 u12Var2 = u12Var == null ? this.b : u12Var;
        if (!z && u12Var2 != null) {
            return u12Var2;
        }
        if (u12Var != null) {
            u12Var.c();
            this.a = null;
        }
        e22 e22Var = this.d;
        Size size = e22Var.f;
        u12 u12Var3 = new u12(this.c, size.getWidth(), size.getHeight(), e22Var.b(), this.e, this.f, this);
        this.a = u12Var3;
        if (this.b != null) {
            return u12Var3;
        }
        u12Var3.j = null;
        c(u12Var3);
        return this.b;
    }

    public final void b(u12 u12Var) {
        if (!(u12Var == this.b)) {
            if (u12Var.j == null) {
                c(u12Var);
            }
        } else {
            Bitmap[][] bitmapArr = u12Var.d;
            y12 y12Var = (y12) this.g;
            y12Var.getClass();
            y12Var.g.p(a22.a, bitmapArr);
        }
    }

    public final void c(u12 u12Var) {
        u12 u12Var2 = this.b;
        if (u12Var2 != null) {
            u12Var2.c();
        }
        this.b = u12Var;
        this.a = null;
        y12 y12Var = (y12) this.g;
        u12 u12VarA = y12Var.p.a(false);
        Matrix matrix = y12Var.n;
        matrix.reset();
        y12Var.o.set(0, 0);
        y12Var.d(1.0f, matrix);
        PropertyModel.r<Size> rVar = a22.b;
        Size size = u12VarA.b;
        PropertyModel propertyModel = y12Var.g;
        propertyModel.p(rVar, size);
        PropertyModel.r<Point> rVar2 = a22.c;
        e22 e22Var = y12Var.i;
        propertyModel.p(rVar2, e22Var.d);
        propertyModel.p(a22.d, e22Var.e(y12Var.j));
        propertyModel.p(a22.a, u12VarA.d);
    }
}
