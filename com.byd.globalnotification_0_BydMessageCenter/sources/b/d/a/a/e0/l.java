package b.d.a.a.e0;

import android.graphics.Canvas;
import android.graphics.Matrix;
import b.d.a.a.e0.m;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l extends m.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ List f1862b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Matrix f1863c;

    public l(m mVar, List list, Matrix matrix) {
        this.f1862b = list;
        this.f1863c = matrix;
    }

    @Override // b.d.a.a.e0.m.f
    public void a(Matrix matrix, b.d.a.a.d0.a aVar, int i, Canvas canvas) {
        Iterator it = this.f1862b.iterator();
        while (it.hasNext()) {
            ((m.f) it.next()).a(this.f1863c, aVar, i, canvas);
        }
    }
}
