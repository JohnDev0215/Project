package c.b.a.a.i0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AbsoluteCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3852a;

    public a(float f2) {
        this.f3852a = f2;
    }

    @Override // c.b.a.a.i0.c
    public float a(RectF rectF) {
        return this.f3852a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f3852a == ((a) obj).f3852a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f3852a)});
    }
}
