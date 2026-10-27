package c.a.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: RelativeCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f2381a;

    public k(float f) {
        this.f2381a = f;
    }

    @Override // c.a.a.a.w.c
    public float a(RectF rectF) {
        return this.f2381a * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof k) && this.f2381a == ((k) obj).f2381a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f2381a)});
    }
}
