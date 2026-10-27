package p;

import android.graphics.Canvas;
import android.graphics.Matrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class rc2 extends xc2 {
    public final /* synthetic */ List b;
    public final /* synthetic */ Matrix c;

    public rc2(ArrayList arrayList, Matrix matrix) {
        this.b = arrayList;
        this.c = matrix;
    }

    @Override // p.xc2
    public final void a(Matrix matrix, nc2 nc2Var, int i, Canvas canvas) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((xc2) it.next()).a(this.c, nc2Var, i, canvas);
        }
    }
}
