package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class xd0 {
    public final ae0 a;
    public final byte[] b;

    public xd0(ae0 ae0Var, byte[] bArr) {
        if (ae0Var == null) {
            throw new NullPointerException("encoding is null");
        }
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        this.a = ae0Var;
        this.b = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xd0)) {
            return false;
        }
        xd0 xd0Var = (xd0) obj;
        if (this.a.equals(xd0Var.a)) {
            return Arrays.equals(this.b, xd0Var.b);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.b);
    }

    public final String toString() {
        return "EncodedPayload{encoding=" + this.a + ", bytes=[...]}";
    }
}
