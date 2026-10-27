package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class h12 implements coil.size.a {
    public final View a;
    public final boolean b;

    public h12(View view, boolean z) {
        this.a = view;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h12) {
            h12 h12Var = (h12) obj;
            if (v03.b(this.a, h12Var.a)) {
                if (this.b == h12Var.b) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.b) + (this.a.hashCode() * 31);
    }
}
