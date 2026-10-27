package p;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class wj2 implements f82 {
    public final String a;
    public final Bundle b;

    public wj2(Bundle bundle, String str) {
        v03.j(str, "query");
        this.a = str;
        this.b = bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wj2)) {
            return false;
        }
        wj2 wj2Var = (wj2) obj;
        return v03.b(this.a, wj2Var.a) && v03.b(this.b, wj2Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Text(query=" + this.a + ", extras=" + this.b + ')';
    }
}
