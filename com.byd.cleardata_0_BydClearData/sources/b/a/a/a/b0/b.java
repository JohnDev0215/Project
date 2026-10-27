package b.a.a.a.b0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f1647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f1648b;

    public b(float f, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f1647a;
            f += ((b) cVar).f1648b;
        }
        this.f1647a = cVar;
        this.f1648b = f;
    }

    @Override // b.a.a.a.b0.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f1647a.a(rectF) + this.f1648b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1647a.equals(bVar.f1647a) && this.f1648b == bVar.f1648b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f1647a, Float.valueOf(this.f1648b)});
    }
}
