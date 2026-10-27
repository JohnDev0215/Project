package b.a.a.a.b0;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f1646a;

    public a(float f) {
        this.f1646a = f;
    }

    @Override // b.a.a.a.b0.c
    public float a(RectF rectF) {
        return this.f1646a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f1646a == ((a) obj).f1646a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f1646a)});
    }
}
