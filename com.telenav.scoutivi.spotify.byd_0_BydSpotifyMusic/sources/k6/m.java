package k6;

/* JADX INFO: compiled from: Settings.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f7480c = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f7481a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f7482b = new int[10];

    /* JADX INFO: compiled from: Settings.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public final int a(int i7) {
        return this.f7482b[i7];
    }

    public final int b() {
        if ((this.f7481a & 2) != 0) {
            return this.f7482b[1];
        }
        return -1;
    }

    public final int c() {
        if ((this.f7481a & 128) != 0) {
            return this.f7482b[7];
        }
        return 65535;
    }

    public final int d() {
        if ((this.f7481a & 16) != 0) {
            return this.f7482b[4];
        }
        return Integer.MAX_VALUE;
    }

    public final int e(int i7) {
        return (this.f7481a & 32) != 0 ? this.f7482b[5] : i7;
    }

    public final boolean f(int i7) {
        return ((1 << i7) & this.f7481a) != 0;
    }

    public final void g(m other) {
        kotlin.jvm.internal.l.e(other, "other");
        for (int i7 = 0; i7 < 10; i7++) {
            if (other.f(i7)) {
                h(i7, other.a(i7));
            }
        }
    }

    public final m h(int i7, int i8) {
        if (i7 >= 0) {
            int[] iArr = this.f7482b;
            if (i7 < iArr.length) {
                this.f7481a = (1 << i7) | this.f7481a;
                iArr[i7] = i8;
            }
        }
        return this;
    }

    public final int i() {
        return Integer.bitCount(this.f7481a);
    }
}
