package p;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class kf0 extends r31 {
    public final Drawable a;
    public final q31 b;
    public final Throwable c;

    public kf0(Drawable drawable, q31 q31Var, Throwable th) {
        this.a = drawable;
        this.b = q31Var;
        this.c = th;
    }

    @Override // p.r31
    public final q31 a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof kf0) {
            kf0 kf0Var = (kf0) obj;
            if (v03.b(this.a, kf0Var.a)) {
                if (v03.b(this.b, kf0Var.b) && v03.b(this.c, kf0Var.c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        Drawable drawable = this.a;
        return this.c.hashCode() + ((this.b.hashCode() + ((drawable != null ? drawable.hashCode() : 0) * 31)) * 31);
    }
}
