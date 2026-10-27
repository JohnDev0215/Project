package x;

/* JADX INFO: renamed from: x.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2643k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2648n f23549a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23550b;

    public C2643k(int i7, C2648n c2648n) {
        this.f23549a = c2648n;
        this.f23550b = i7;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AnimationResult(endReason=");
        int i7 = this.f23550b;
        sb.append(i7 != 1 ? i7 != 2 ? "null" : "Finished" : "BoundReached");
        sb.append(", endState=");
        sb.append(this.f23549a);
        sb.append(')');
        return sb.toString();
    }
}
