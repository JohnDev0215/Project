package d.b.a.a.w;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AbsoluteCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4826a;

    public a(float f2) {
        this.f4826a = f2;
    }

    @Override // d.b.a.a.w.c
    public float a(RectF rectF) {
        return this.f4826a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f4826a == ((a) obj).f4826a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f4826a)});
    }
}
