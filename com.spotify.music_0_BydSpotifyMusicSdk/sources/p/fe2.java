package p;

import coil.decode.DataSource;

/* JADX INFO: loaded from: classes.dex */
public final class fe2 extends et0 {
    public final s31 a;
    public final String b;
    public final DataSource c;

    public fe2(s31 s31Var, String str, DataSource dataSource) {
        this.a = s31Var;
        this.b = str;
        this.c = dataSource;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof fe2) {
            fe2 fe2Var = (fe2) obj;
            if (v03.b(this.a, fe2Var.a) && v03.b(this.b, fe2Var.b) && this.c == fe2Var.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        String str = this.b;
        return this.c.hashCode() + ((iHashCode + (str != null ? str.hashCode() : 0)) * 31);
    }
}
