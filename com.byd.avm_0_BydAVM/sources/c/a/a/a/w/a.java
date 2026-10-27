package c.a.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AbsoluteCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f2062a;

    public a(float f) {
        this.f2062a = f;
    }

    @Override // c.a.a.a.w.c
    public float a(RectF rectF) {
        return this.f2062a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f2062a == ((a) obj).f2062a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f2062a)});
    }
}
