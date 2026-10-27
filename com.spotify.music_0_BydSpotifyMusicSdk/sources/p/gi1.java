package p;

import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class gi1 {
    public ii1 a;

    public gi1(String str, int i, int i2) {
        if (str == null) {
            throw new NullPointerException("package shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        if (Build.VERSION.SDK_INT < 28) {
            this.a = new ii1(str, i, i2);
            return;
        }
        hi1 hi1Var = new hi1(str, i, i2);
        q2.w(str, i, i2);
        this.a = hi1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gi1)) {
            return false;
        }
        return this.a.equals(((gi1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
