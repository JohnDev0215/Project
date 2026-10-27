package p;

import android.graphics.Bitmap;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class g12 implements pg2 {
    public final uv2 a;
    public final f12 b;

    public g12(int i, uv2 uv2Var) {
        this.a = uv2Var;
        this.b = new f12(i, this);
    }

    @Override // p.pg2
    public final void a(int i) {
        int i2;
        if (i >= 40) {
            this.b.f(-1);
            return;
        }
        if (10 > i || i >= 20) {
            return;
        }
        f12 f12Var = this.b;
        synchronized (f12Var.c) {
            i2 = f12Var.d;
        }
        f12Var.f(i2 / 2);
    }

    @Override // p.pg2
    public final ni1 c(mi1 mi1Var) {
        e12 e12Var = (e12) this.b.b(mi1Var);
        if (e12Var != null) {
            return new ni1(e12Var.a, e12Var.b);
        }
        return null;
    }

    @Override // p.pg2
    public final void d(mi1 mi1Var, Bitmap bitmap, Map map) {
        int i;
        Object objRemove;
        int iC = l13.c(bitmap);
        f12 f12Var = this.b;
        synchronized (f12Var.c) {
            i = f12Var.a;
        }
        if (iC <= i) {
            this.b.c(mi1Var, new e12(bitmap, map, iC));
            return;
        }
        f12 f12Var2 = this.b;
        f12Var2.getClass();
        synchronized (f12Var2.c) {
            oh1 oh1Var = f12Var2.b;
            oh1Var.getClass();
            objRemove = ((LinkedHashMap) oh1Var.e).remove(mi1Var);
            if (objRemove != null) {
                f12Var2.d -= f12Var2.d(mi1Var, objRemove);
            }
        }
        if (objRemove != null) {
            f12Var2.a(mi1Var, objRemove, null);
        }
        this.a.g(mi1Var, bitmap, map, iC);
    }
}
