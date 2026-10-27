package c.b.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f2749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f2750b;

    public b(float f, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f2749a;
            f += ((b) cVar).f2750b;
        }
        this.f2749a = cVar;
        this.f2750b = f;
    }

    @Override // c.b.a.a.w.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f2749a.a(rectF) + this.f2750b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2749a.equals(bVar.f2749a) && this.f2750b == bVar.f2750b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2749a, Float.valueOf(this.f2750b)});
    }
}
