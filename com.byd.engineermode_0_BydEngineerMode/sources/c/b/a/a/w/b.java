package c.b.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f2184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f2185b;

    public b(float f, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f2184a;
            f += ((b) cVar).f2185b;
        }
        this.f2184a = cVar;
        this.f2185b = f;
    }

    @Override // c.b.a.a.w.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f2184a.a(rectF) + this.f2185b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2184a.equals(bVar.f2184a) && this.f2185b == bVar.f2185b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2184a, Float.valueOf(this.f2185b)});
    }
}
