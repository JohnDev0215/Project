package defpackage;

import android.graphics.Matrix;
import android.util.Size;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: PlayerFrameScaleController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b22 {
    public final e22 b;
    public final Size c;
    public final Matrix d;
    public final z12 e;
    public final nw2<Boolean> f;
    public final Callback<Boolean> g;
    public float a = 0.0f;
    public boolean h = true;

    public b22(Matrix matrix, y12 y12Var, yq2 yq2Var, aq3 aq3Var) {
        this.b = y12Var.i;
        this.c = y12Var.b;
        this.d = matrix;
        this.e = y12Var;
        this.f = yq2Var;
        this.g = aq3Var;
    }
}
