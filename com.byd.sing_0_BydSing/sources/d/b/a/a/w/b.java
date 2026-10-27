package d.b.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f3997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f3998b;

    public b(float f, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f3997a;
            f += ((b) cVar).f3998b;
        }
        this.f3997a = cVar;
        this.f3998b = f;
    }

    @Override // d.b.a.a.w.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f3997a.a(rectF) + this.f3998b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f3997a.equals(bVar.f3997a) && this.f3998b == bVar.f3998b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3997a, Float.valueOf(this.f3998b)});
    }
}
