package c.b.a.a.v;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1847a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1848b;

    public b(float f, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f1847a;
            f += ((b) cVar).f1848b;
        }
        this.f1847a = cVar;
        this.f1848b = f;
    }

    @Override // c.b.a.a.v.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f1847a.a(rectF) + this.f1848b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1847a.equals(bVar.f1847a) && this.f1848b == bVar.f1848b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f1847a, Float.valueOf(this.f1848b)});
    }
}
