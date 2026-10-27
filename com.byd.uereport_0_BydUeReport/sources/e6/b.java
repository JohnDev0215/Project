package e6;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f4649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4650b;

    public b(float f8, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f4649a;
            f8 += ((b) cVar).f4650b;
        }
        this.f4649a = cVar;
        this.f4650b = f8;
    }

    @Override // e6.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f4649a.a(rectF) + this.f4650b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4649a.equals(bVar.f4649a) && this.f4650b == bVar.f4650b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4649a, Float.valueOf(this.f4650b)});
    }
}
