package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class q03 {
    public final String a;
    public final long b;
    public final int c;
    public final boolean d;
    public final boolean e;
    public final byte[] f;

    public q03(String str, long j, int i, boolean z, boolean z2, byte[] bArr) {
        this.a = str;
        this.b = j;
        this.c = i;
        this.d = z;
        this.e = z2;
        this.f = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof q03) {
            q03 q03Var = (q03) obj;
            String str = this.a;
            if (str != null ? str.equals(q03Var.a) : q03Var.a == null) {
                if (this.b == q03Var.b && this.c == q03Var.c && this.d == q03Var.d && this.e == q03Var.e && Arrays.equals(this.f, q03Var.f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.a;
        int iHashCode = str == null ? 0 : str.hashCode();
        long j = this.b;
        return ((((((((((iHashCode ^ 1000003) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ this.c) * 1000003) ^ (true != this.d ? 1237 : 1231)) * 1000003) ^ (true != this.e ? 1237 : 1231)) * 1000003) ^ Arrays.hashCode(this.f);
    }

    public final String toString() {
        String string = Arrays.toString(this.f);
        String str = this.a;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 126 + String.valueOf(string).length());
        sb.append("ZipEntry{name=");
        sb.append(str);
        sb.append(", size=");
        sb.append(this.b);
        sb.append(", compressionMethod=");
        sb.append(this.c);
        sb.append(", isPartial=");
        sb.append(this.d);
        sb.append(", isEndOfArchive=");
        sb.append(this.e);
        sb.append(", headerBytes=");
        sb.append(string);
        sb.append("}");
        return sb.toString();
    }
}
