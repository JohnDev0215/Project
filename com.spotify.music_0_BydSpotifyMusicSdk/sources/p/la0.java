package p;

import android.content.Context;
import android.util.DisplayMetrics;

/* JADX INFO: loaded from: classes.dex */
public final class la0 implements rd2 {
    public final Context a;

    public la0(Context context) {
        this.a = context;
    }

    @Override // p.rd2
    public final Object a(c00 c00Var) {
        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
        t90 t90Var = new t90(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels));
        return new qd2(t90Var, t90Var);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof la0) {
            if (v03.b(this.a, ((la0) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
