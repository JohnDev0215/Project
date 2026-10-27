package d.b.a.a.x;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f4748a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4749b;

    public b(float f2, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f4748a;
            f2 += ((b) cVar).f4749b;
        }
        this.f4748a = cVar;
        this.f4749b = f2;
    }

    @Override // d.b.a.a.x.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f4748a.a(rectF) + this.f4749b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4748a.equals(bVar.f4748a) && this.f4749b == bVar.f4749b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4748a, Float.valueOf(this.f4749b)});
    }
}
