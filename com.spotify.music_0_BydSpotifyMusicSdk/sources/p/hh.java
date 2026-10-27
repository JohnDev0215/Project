package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class hh extends o30 {
    public final String a;
    public final byte[] b;

    public hh(String str, byte[] bArr) {
        this.a = str;
        this.b = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof o30)) {
            return false;
        }
        o30 o30Var = (o30) obj;
        if (this.a.equals(((hh) o30Var).a)) {
            if (Arrays.equals(this.b, (o30Var instanceof hh ? (hh) o30Var : (hh) o30Var).b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.b);
    }

    public final String toString() {
        return "File{filename=" + this.a + ", contents=" + Arrays.toString(this.b) + "}";
    }
}
