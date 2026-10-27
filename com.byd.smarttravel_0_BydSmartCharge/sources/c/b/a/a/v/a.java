package c.b.a.a.v;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AbsoluteCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1846a;

    public a(float f) {
        this.f1846a = f;
    }

    @Override // c.b.a.a.v.c
    public float a(RectF rectF) {
        return this.f1846a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f1846a == ((a) obj).f1846a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f1846a)});
    }
}
