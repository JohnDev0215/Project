package b.d.a.a.e0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class h implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1841a;

    public h(float f2) {
        this.f1841a = f2;
    }

    @Override // b.d.a.a.e0.c
    public float a(RectF rectF) {
        return rectF.height() * this.f1841a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof h) && this.f1841a == ((h) obj).f1841a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f1841a)});
    }
}
