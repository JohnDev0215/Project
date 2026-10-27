package p;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public final class o42 {
    public final Resources a;
    public final Resources.Theme b;

    public o42(Resources resources, Resources.Theme theme) {
        this.a = resources;
        this.b = theme;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o42.class != obj.getClass()) {
            return false;
        }
        o42 o42Var = (o42) obj;
        return this.a.equals(o42Var.a) && ap1.a(this.b, o42Var.b);
    }

    public final int hashCode() {
        return ap1.b(this.a, this.b);
    }
}
