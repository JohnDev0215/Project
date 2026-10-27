package c6;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CacheControl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f3682a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f3683b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3684c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3685d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f3686e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f3687f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f3688g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f3689h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f3690i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final boolean f3691j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final boolean f3692k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final boolean f3693l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private String f3694m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final b f3681p = new b(null);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final d f3679n = new a().d().a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final d f3680o = new a().e().c(Integer.MAX_VALUE, TimeUnit.SECONDS).a();

    /* JADX INFO: compiled from: CacheControl.kt */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3695a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f3696b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3697c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3698d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3699e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f3700f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f3701g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f3702h;

        private final int b(long j7) {
            if (j7 > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) j7;
        }

        public final d a() {
            return new d(this.f3695a, this.f3696b, this.f3697c, -1, false, false, false, this.f3698d, this.f3699e, this.f3700f, this.f3701g, this.f3702h, null, null);
        }

        public final a c(int i7, TimeUnit timeUnit) {
            kotlin.jvm.internal.l.e(timeUnit, "timeUnit");
            if (i7 >= 0) {
                this.f3698d = b(timeUnit.toSeconds(i7));
                return this;
            }
            throw new IllegalArgumentException(("maxStale < 0: " + i7).toString());
        }

        public final a d() {
            this.f3695a = true;
            return this;
        }

        public final a e() {
            this.f3700f = true;
            return this;
        }
    }

    /* JADX INFO: compiled from: CacheControl.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        private final int a(String str, String str2, int i7) {
            int length = str.length();
            while (i7 < length) {
                if (y5.q.E(str2, str.charAt(i7), false, 2, null)) {
                    return i7;
                }
                i7++;
            }
            return str.length();
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00d9  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00dd  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final c6.d b(c6.t r32) {
            /*
                Method dump skipped, instruction units count: 395
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: c6.d.b.b(c6.t):c6.d");
        }
    }

    private d(boolean z6, boolean z7, int i7, int i8, boolean z8, boolean z9, boolean z10, int i9, int i10, boolean z11, boolean z12, boolean z13, String str) {
        this.f3682a = z6;
        this.f3683b = z7;
        this.f3684c = i7;
        this.f3685d = i8;
        this.f3686e = z8;
        this.f3687f = z9;
        this.f3688g = z10;
        this.f3689h = i9;
        this.f3690i = i10;
        this.f3691j = z11;
        this.f3692k = z12;
        this.f3693l = z13;
        this.f3694m = str;
    }

    public final boolean a() {
        return this.f3686e;
    }

    public final boolean b() {
        return this.f3687f;
    }

    public final int c() {
        return this.f3684c;
    }

    public final int d() {
        return this.f3689h;
    }

    public final int e() {
        return this.f3690i;
    }

    public final boolean f() {
        return this.f3688g;
    }

    public final boolean g() {
        return this.f3682a;
    }

    public final boolean h() {
        return this.f3683b;
    }

    public final boolean i() {
        return this.f3691j;
    }

    public String toString() {
        String str = this.f3694m;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.f3682a) {
            sb.append("no-cache, ");
        }
        if (this.f3683b) {
            sb.append("no-store, ");
        }
        if (this.f3684c != -1) {
            sb.append("max-age=");
            sb.append(this.f3684c);
            sb.append(", ");
        }
        if (this.f3685d != -1) {
            sb.append("s-maxage=");
            sb.append(this.f3685d);
            sb.append(", ");
        }
        if (this.f3686e) {
            sb.append("private, ");
        }
        if (this.f3687f) {
            sb.append("public, ");
        }
        if (this.f3688g) {
            sb.append("must-revalidate, ");
        }
        if (this.f3689h != -1) {
            sb.append("max-stale=");
            sb.append(this.f3689h);
            sb.append(", ");
        }
        if (this.f3690i != -1) {
            sb.append("min-fresh=");
            sb.append(this.f3690i);
            sb.append(", ");
        }
        if (this.f3691j) {
            sb.append("only-if-cached, ");
        }
        if (this.f3692k) {
            sb.append("no-transform, ");
        }
        if (this.f3693l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "StringBuilder().apply(builderAction).toString()");
        this.f3694m = string;
        return string;
    }

    public /* synthetic */ d(boolean z6, boolean z7, int i7, int i8, boolean z8, boolean z9, boolean z10, int i9, int i10, boolean z11, boolean z12, boolean z13, String str, kotlin.jvm.internal.g gVar) {
        this(z6, z7, i7, i8, z8, z9, z10, i9, i10, z11, z12, z13, str);
    }
}
