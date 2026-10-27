package p;

import android.graphics.drawable.Drawable;
import coil.decode.DataSource;

/* JADX INFO: loaded from: classes.dex */
public final class bh2 extends r31 {
    public final Drawable a;
    public final q31 b;
    public final DataSource c;
    public final mi1 d;
    public final String e;
    public final boolean f;
    public final boolean g;

    public bh2(Drawable drawable, q31 q31Var, DataSource dataSource, mi1 mi1Var, String str, boolean z, boolean z2) {
        this.a = drawable;
        this.b = q31Var;
        this.c = dataSource;
        this.d = mi1Var;
        this.e = str;
        this.f = z;
        this.g = z2;
    }

    @Override // p.r31
    public final q31 a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof bh2) {
            bh2 bh2Var = (bh2) obj;
            if (v03.b(this.a, bh2Var.a)) {
                if (v03.b(this.b, bh2Var.b) && this.c == bh2Var.c && v03.b(this.d, bh2Var.d) && v03.b(this.e, bh2Var.e) && this.f == bh2Var.f && this.g == bh2Var.g) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31;
        mi1 mi1Var = this.d;
        int iHashCode2 = (iHashCode + (mi1Var != null ? mi1Var.hashCode() : 0)) * 31;
        String str = this.e;
        return Boolean.hashCode(this.g) + ((Boolean.hashCode(this.f) + ((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31)) * 31);
    }
}
