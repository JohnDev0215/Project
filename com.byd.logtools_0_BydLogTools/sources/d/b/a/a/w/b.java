package d.b.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f4827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4828b;

    public b(float f2, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f4827a;
            f2 += ((b) cVar).f4828b;
        }
        this.f4827a = cVar;
        this.f4828b = f2;
    }

    @Override // d.b.a.a.w.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f4827a.a(rectF) + this.f4828b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4827a.equals(bVar.f4827a) && this.f4828b == bVar.f4828b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4827a, Float.valueOf(this.f4828b)});
    }
}
