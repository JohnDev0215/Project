package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5047d;

    public n0(int i2, int i3) {
        this.f5044a = i2;
        this.f5045b = i3;
    }

    public n0(long j, long j2) {
        this.f5046c = j;
        this.f5047d = j2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof n0)) {
            return false;
        }
        n0 n0Var = (n0) obj;
        return n0Var.f5045b == this.f5045b && n0Var.f5044a == this.f5044a && n0Var.f5047d == this.f5047d && n0Var.f5046c == this.f5046c;
    }

    public int hashCode() {
        int i2 = this.f5044a ^ this.f5045b;
        long j = this.f5046c;
        int i3 = (i2 ^ ((int) j)) ^ ((int) (j >> 32));
        long j2 = this.f5047d;
        return (i3 ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
