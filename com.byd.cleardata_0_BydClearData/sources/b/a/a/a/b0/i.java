package b.a.a.a.b0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class i implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f1660a;

    public i(float f) {
        this.f1660a = f;
    }

    @Override // b.a.a.a.b0.c
    public float a(RectF rectF) {
        return this.f1660a * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof i) && this.f1660a == ((i) obj).f1660a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f1660a)});
    }
}
