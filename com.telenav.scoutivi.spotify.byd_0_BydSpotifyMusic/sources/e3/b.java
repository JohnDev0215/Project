package e3;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f6176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f6177b;

    public b(float f7, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f6176a;
            f7 += ((b) cVar).f6177b;
        }
        this.f6176a = cVar;
        this.f6177b = f7;
    }

    @Override // e3.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f6176a.a(rectF) + this.f6177b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f6176a.equals(bVar.f6176a) && this.f6177b == bVar.f6177b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f6176a, Float.valueOf(this.f6177b)});
    }
}
