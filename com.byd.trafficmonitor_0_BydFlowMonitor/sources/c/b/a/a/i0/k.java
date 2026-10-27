package c.b.a.a.i0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: RelativeCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3879a;

    public k(float f2) {
        this.f3879a = f2;
    }

    @Override // c.b.a.a.i0.c
    public float a(RectF rectF) {
        return this.f3879a * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof k) && this.f3879a == ((k) obj).f3879a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f3879a)});
    }
}
