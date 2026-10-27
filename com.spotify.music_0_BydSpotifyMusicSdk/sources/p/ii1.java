package p;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ii1 {
    public final String a;
    public final int b;
    public final int c;

    public ii1(String str, int i, int i2) {
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ii1)) {
            return false;
        }
        ii1 ii1Var = (ii1) obj;
        int i = this.c;
        String str = this.a;
        int i2 = this.b;
        return (i2 < 0 || ii1Var.b < 0) ? TextUtils.equals(str, ii1Var.a) && i == ii1Var.c : TextUtils.equals(str, ii1Var.a) && i2 == ii1Var.b && i == ii1Var.c;
    }

    public final int hashCode() {
        return ap1.b(this.a, Integer.valueOf(this.c));
    }
}
