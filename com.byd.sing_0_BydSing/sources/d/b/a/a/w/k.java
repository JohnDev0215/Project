package d.b.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: RelativeCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4013a;

    public k(float f) {
        this.f4013a = f;
    }

    @Override // d.b.a.a.w.c
    public float a(RectF rectF) {
        return this.f4013a * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof k) && this.f4013a == ((k) obj).f4013a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f4013a)});
    }
}
