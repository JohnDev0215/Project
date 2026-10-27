package h3;

/* JADX INFO: loaded from: classes.dex */
public final class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15291a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f15292b;
    public final int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f15293e;

    public H(int i7, int i10) {
        this(Integer.MIN_VALUE, i7, i10);
    }

    public final void a() {
        int i7 = this.d;
        this.d = i7 == Integer.MIN_VALUE ? this.f15292b : i7 + this.c;
        this.f15293e = this.f15291a + this.d;
    }

    public final void b() {
        if (this.d == Integer.MIN_VALUE) {
            throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
        }
    }

    public H(int i7, int i10, int i11) {
        String str;
        if (i7 != Integer.MIN_VALUE) {
            str = i7 + "/";
        } else {
            str = "";
        }
        this.f15291a = str;
        this.f15292b = i10;
        this.c = i11;
        this.d = Integer.MIN_VALUE;
        this.f15293e = "";
    }
}
