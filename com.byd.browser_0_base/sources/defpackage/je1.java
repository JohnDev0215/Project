package defpackage;

import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: compiled from: MediaSessionManagerImplBase.java */
/* JADX INFO: loaded from: classes.dex */
public class je1 {
    public final String a;
    public final int b;
    public final int c;

    public je1(String str, int i, int i2) {
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof je1)) {
            return false;
        }
        je1 je1Var = (je1) obj;
        int i = this.c;
        String str = this.a;
        int i2 = this.b;
        return (i2 < 0 || je1Var.b < 0) ? TextUtils.equals(str, je1Var.a) && i == je1Var.c : TextUtils.equals(str, je1Var.a) && i2 == je1Var.b && i == je1Var.c;
    }

    public final int hashCode() {
        return Objects.hash(this.a, Integer.valueOf(this.c));
    }
}
