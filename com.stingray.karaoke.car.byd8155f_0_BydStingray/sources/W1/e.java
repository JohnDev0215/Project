package W1;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f9679a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f9680b;

    public e(long j3, long j10) {
        if (j10 == 0) {
            this.f9679a = 0L;
            this.f9680b = 1L;
        } else {
            this.f9679a = j3;
            this.f9680b = j10;
        }
    }

    public final String toString() {
        return this.f9679a + "/" + this.f9680b;
    }
}
