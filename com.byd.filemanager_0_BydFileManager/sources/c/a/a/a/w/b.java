package c.a.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f2363a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f2364b;

    public b(float f, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f2363a;
            f += ((b) cVar).f2364b;
        }
        this.f2363a = cVar;
        this.f2364b = f;
    }

    @Override // c.a.a.a.w.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f2363a.a(rectF) + this.f2364b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2363a.equals(bVar.f2363a) && this.f2364b == bVar.f2364b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2363a, Float.valueOf(this.f2364b)});
    }
}
