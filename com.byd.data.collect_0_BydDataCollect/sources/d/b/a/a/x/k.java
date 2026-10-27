package d.b.a.a.x;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: RelativeCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4774a;

    public k(float f2) {
        this.f4774a = f2;
    }

    @Override // d.b.a.a.x.c
    public float a(RectF rectF) {
        return this.f4774a * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof k) && this.f4774a == ((k) obj).f4774a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f4774a)});
    }
}
