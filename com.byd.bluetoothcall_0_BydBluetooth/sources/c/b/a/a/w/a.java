package c.b.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AbsoluteCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f2748a;

    public a(float f) {
        this.f2748a = f;
    }

    @Override // c.b.a.a.w.c
    public float a(RectF rectF) {
        return this.f2748a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f2748a == ((a) obj).f2748a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f2748a)});
    }
}
