package Y4;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f10706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f10707b;

    public b(float f10, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f10706a;
            f10 += ((b) cVar).f10707b;
        }
        this.f10706a = cVar;
        this.f10707b = f10;
    }

    @Override // Y4.c
    public final float a(RectF rectF) {
        return Math.max(0.0f, this.f10706a.a(rectF) + this.f10707b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f10706a.equals(bVar.f10706a) && this.f10707b == bVar.f10707b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f10706a, Float.valueOf(this.f10707b)});
    }
}
