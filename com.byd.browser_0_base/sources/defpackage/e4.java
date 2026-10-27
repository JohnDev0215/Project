package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class e4 implements l00 {
    public final l00 a;
    public final float b;

    public e4(float f, l00 l00Var) {
        while (l00Var instanceof e4) {
            l00Var = ((e4) l00Var).a;
            f += ((e4) l00Var).b;
        }
        this.a = l00Var;
        this.b = f;
    }

    @Override // defpackage.l00
    public final float a(RectF rectF) {
        return Math.max(0.0f, this.a.a(rectF) + this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e4)) {
            return false;
        }
        e4 e4Var = (e4) obj;
        return this.a.equals(e4Var.a) && this.b == e4Var.b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, Float.valueOf(this.b)});
    }
}
