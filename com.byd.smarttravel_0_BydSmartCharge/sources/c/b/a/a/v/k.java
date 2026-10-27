package c.b.a.a.v;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: RelativeCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1863a;

    public k(float f) {
        this.f1863a = f;
    }

    @Override // c.b.a.a.v.c
    public float a(RectF rectF) {
        return this.f1863a * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof k) && this.f1863a == ((k) obj).f1863a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f1863a)});
    }
}
