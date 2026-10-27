package e;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: CacheControl.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f3317a = new a().c().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f3318b = new a().e().b(Integer.MAX_VALUE, TimeUnit.SECONDS).a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f3319c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f3320d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3321e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f3323g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3324h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f3325i;
    public final int j;
    public final int k;
    public final boolean l;
    public final boolean m;
    public final boolean n;

    @Nullable
    public String o;

    /* JADX INFO: compiled from: CacheControl.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3326a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3327b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3328c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3329d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3330e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f3331f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f3332g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f3333h;

        public d a() {
            return new d(this);
        }

        public a b(int i2, TimeUnit timeUnit) {
            if (i2 >= 0) {
                long seconds = timeUnit.toSeconds(i2);
                this.f3329d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i2);
        }

        public a c() {
            this.f3326a = true;
            return this;
        }

        public a d() {
            this.f3327b = true;
            return this;
        }

        public a e() {
            this.f3331f = true;
            return this;
        }
    }

    public d(boolean z, boolean z2, int i2, int i3, boolean z3, boolean z4, boolean z5, int i4, int i5, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.f3319c = z;
        this.f3320d = z2;
        this.f3321e = i2;
        this.f3322f = i3;
        this.f3323g = z3;
        this.f3324h = z4;
        this.f3325i = z5;
        this.j = i4;
        this.k = i5;
        this.l = z6;
        this.m = z7;
        this.n = z8;
        this.o = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static e.d k(e.s r22) {
        /*
            Method dump skipped, instruction units count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e.d.k(e.s):e.d");
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        if (this.f3319c) {
            sb.append("no-cache, ");
        }
        if (this.f3320d) {
            sb.append("no-store, ");
        }
        if (this.f3321e != -1) {
            sb.append("max-age=");
            sb.append(this.f3321e);
            sb.append(", ");
        }
        if (this.f3322f != -1) {
            sb.append("s-maxage=");
            sb.append(this.f3322f);
            sb.append(", ");
        }
        if (this.f3323g) {
            sb.append("private, ");
        }
        if (this.f3324h) {
            sb.append("public, ");
        }
        if (this.f3325i) {
            sb.append("must-revalidate, ");
        }
        if (this.j != -1) {
            sb.append("max-stale=");
            sb.append(this.j);
            sb.append(", ");
        }
        if (this.k != -1) {
            sb.append("min-fresh=");
            sb.append(this.k);
            sb.append(", ");
        }
        if (this.l) {
            sb.append("only-if-cached, ");
        }
        if (this.m) {
            sb.append("no-transform, ");
        }
        if (this.n) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public boolean b() {
        return this.f3323g;
    }

    public boolean c() {
        return this.f3324h;
    }

    public int d() {
        return this.f3321e;
    }

    public int e() {
        return this.j;
    }

    public int f() {
        return this.k;
    }

    public boolean g() {
        return this.f3325i;
    }

    public boolean h() {
        return this.f3319c;
    }

    public boolean i() {
        return this.f3320d;
    }

    public boolean j() {
        return this.l;
    }

    public String toString() {
        String str = this.o;
        if (str != null) {
            return str;
        }
        String strA = a();
        this.o = strA;
        return strA;
    }

    public d(a aVar) {
        this.f3319c = aVar.f3326a;
        this.f3320d = aVar.f3327b;
        this.f3321e = aVar.f3328c;
        this.f3322f = -1;
        this.f3323g = false;
        this.f3324h = false;
        this.f3325i = false;
        this.j = aVar.f3329d;
        this.k = aVar.f3330e;
        this.l = aVar.f3331f;
        this.m = aVar.f3332g;
        this.n = aVar.f3333h;
    }
}
