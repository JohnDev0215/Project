package b.d.a.a.e0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1825a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1826b;

    public b(float f2, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f1825a;
            f2 += ((b) cVar).f1826b;
        }
        this.f1825a = cVar;
        this.f1826b = f2;
    }

    @Override // b.d.a.a.e0.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f1825a.a(rectF) + this.f1826b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1825a.equals(bVar.f1825a) && this.f1826b == bVar.f1826b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f1825a, Float.valueOf(this.f1826b)});
    }
}
