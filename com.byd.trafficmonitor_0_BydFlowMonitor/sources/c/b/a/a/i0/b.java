package c.b.a.a.i0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f3853a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3854b;

    public b(float f2, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f3853a;
            f2 += ((b) cVar).f3854b;
        }
        this.f3853a = cVar;
        this.f3854b = f2;
    }

    @Override // c.b.a.a.i0.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f3853a.a(rectF) + this.f3854b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f3853a.equals(bVar.f3853a) && this.f3854b == bVar.f3854b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3853a, Float.valueOf(this.f3854b)});
    }
}
