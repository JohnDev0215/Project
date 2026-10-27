package d.b.a.a.x;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AbsoluteCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4747a;

    public a(float f2) {
        this.f4747a = f2;
    }

    @Override // d.b.a.a.x.c
    public float a(RectF rectF) {
        return this.f4747a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f4747a == ((a) obj).f4747a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f4747a)});
    }
}
