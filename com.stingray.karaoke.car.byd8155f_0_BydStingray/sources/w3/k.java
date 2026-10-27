package W3;

import android.graphics.PointF;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f9792a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f9793b;
    public boolean c;

    public k(PointF pointF, boolean z6, List list) {
        this.f9793b = pointF;
        this.c = z6;
        this.f9792a = new ArrayList(list);
    }

    public final void a(float f10, float f11) {
        if (this.f9793b == null) {
            this.f9793b = new PointF();
        }
        this.f9793b.set(f10, f11);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ShapeData{numCurves=");
        sb.append(this.f9792a.size());
        sb.append("closed=");
        return AbstractC1347d.i(sb, this.c, '}');
    }

    public k() {
        this.f9792a = new ArrayList();
    }
}
