package n2;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f4095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4096b;

    public b(float f3, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f4095a;
            f3 += ((b) cVar).f4096b;
        }
        this.f4095a = cVar;
        this.f4096b = f3;
    }

    @Override // n2.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f4095a.a(rectF) + this.f4096b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4095a.equals(bVar.f4095a) && this.f4096b == bVar.f4096b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4095a, Float.valueOf(this.f4096b)});
    }
}
