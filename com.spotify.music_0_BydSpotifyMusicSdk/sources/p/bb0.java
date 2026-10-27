package p;

import android.graphics.drawable.Drawable;
import coil.decode.DataSource;

/* JADX INFO: loaded from: classes.dex */
public final class bb0 extends et0 {
    public final Drawable a;
    public final boolean b;
    public final DataSource c;

    public bb0(Drawable drawable, boolean z, DataSource dataSource) {
        this.a = drawable;
        this.b = z;
        this.c = dataSource;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof bb0) {
            bb0 bb0Var = (bb0) obj;
            if (v03.b(this.a, bb0Var.a) && this.b == bb0Var.b && this.c == bb0Var.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + ((Boolean.hashCode(this.b) + (this.a.hashCode() * 31)) * 31);
    }
}
