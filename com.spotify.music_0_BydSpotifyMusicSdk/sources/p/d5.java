package p;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d5 implements v10 {
    public final v10 a;
    public final float b;

    public d5(float f, v10 v10Var) {
        while (v10Var instanceof d5) {
            v10Var = ((d5) v10Var).a;
            f += ((d5) v10Var).b;
        }
        this.a = v10Var;
        this.b = f;
    }

    @Override // p.v10
    public final float a(RectF rectF) {
        return Math.max(0.0f, this.a.a(rectF) + this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d5)) {
            return false;
        }
        d5 d5Var = (d5) obj;
        return this.a.equals(d5Var.a) && this.b == d5Var.b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, Float.valueOf(this.b)});
    }
}
