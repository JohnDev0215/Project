package b.d.a.a.e0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1824a;

    public a(float f2) {
        this.f1824a = f2;
    }

    @Override // b.d.a.a.e0.c
    public float a(RectF rectF) {
        return this.f1824a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f1824a == ((a) obj).f1824a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f1824a)});
    }
}
