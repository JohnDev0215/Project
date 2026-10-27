package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import defpackage.xj2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ShapePath.java */
/* JADX INFO: loaded from: classes.dex */
public final class wj2 extends xj2.f {
    public final /* synthetic */ List b;
    public final /* synthetic */ Matrix c;

    public wj2(ArrayList arrayList, Matrix matrix) {
        this.b = arrayList;
        this.c = matrix;
    }

    @Override // xj2.f
    public final void a(Matrix matrix, sj2 sj2Var, int i, Canvas canvas) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((xj2.f) it.next()).a(this.c, sj2Var, i, canvas);
        }
    }
}
