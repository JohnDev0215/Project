package p;

/* JADX INFO: loaded from: classes.dex */
public final class p03 {
    public final int a;
    public final String b;
    public final long c;
    public final long d;
    public final int e;

    public p03(int i, String str, long j, long j2, int i2) {
        this.a = i;
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof p03) {
            p03 p03Var = (p03) obj;
            if (this.a == p03Var.a) {
                String str = p03Var.b;
                String str2 = this.b;
                if (str2 != null ? str2.equals(str) : str == null) {
                    if (this.c == p03Var.c && this.d == p03Var.d && this.e == p03Var.e) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = (this.a ^ 1000003) * 1000003;
        String str = this.b;
        int iHashCode = (i ^ (str == null ? 0 : str.hashCode())) * 1000003;
        long j = this.c;
        int i2 = (iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.d;
        return ((i2 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.e;
    }

    public final String toString() {
        String str = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 157);
        sb.append("SliceCheckpoint{fileExtractionStatus=");
        sb.append(this.a);
        sb.append(", filePath=");
        sb.append(str);
        sb.append(", fileOffset=");
        sb.append(this.c);
        sb.append(", remainingBytes=");
        sb.append(this.d);
        sb.append(", previousChunk=");
        sb.append(this.e);
        sb.append("}");
        return sb.toString();
    }
}
