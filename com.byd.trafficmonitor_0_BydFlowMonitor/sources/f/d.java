package f;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CacheControl.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f5298a = new a().c().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f5299b = new a().d().b(Integer.MAX_VALUE, TimeUnit.SECONDS).a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f5300c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f5301d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5302e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5303f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f5304g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f5305h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f5306i;
    public final int j;
    public final int k;
    public final boolean l;
    public final boolean m;
    public String n;

    /* JADX INFO: compiled from: CacheControl.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5307a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5308b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5309c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5310d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5311e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5312f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f5313g;

        public d a() {
            return new d(this);
        }

        public a b(int i2, TimeUnit timeUnit) {
            if (i2 >= 0) {
                long seconds = timeUnit.toSeconds(i2);
                this.f5310d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i2);
        }

        public a c() {
            this.f5307a = true;
            return this;
        }

        public a d() {
            this.f5312f = true;
            return this;
        }
    }

    public d(boolean z, boolean z2, int i2, int i3, boolean z3, boolean z4, boolean z5, int i4, int i5, boolean z6, boolean z7, String str) {
        this.f5300c = z;
        this.f5301d = z2;
        this.f5302e = i2;
        this.f5303f = i3;
        this.f5304g = z3;
        this.f5305h = z4;
        this.f5306i = z5;
        this.j = i4;
        this.k = i5;
        this.l = z6;
        this.m = z7;
        this.n = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static f.d k(f.q r21) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f.d.k(f.q):f.d");
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        if (this.f5300c) {
            sb.append("no-cache, ");
        }
        if (this.f5301d) {
            sb.append("no-store, ");
        }
        if (this.f5302e != -1) {
            sb.append("max-age=");
            sb.append(this.f5302e);
            sb.append(", ");
        }
        if (this.f5303f != -1) {
            sb.append("s-maxage=");
            sb.append(this.f5303f);
            sb.append(", ");
        }
        if (this.f5304g) {
            sb.append("private, ");
        }
        if (this.f5305h) {
            sb.append("public, ");
        }
        if (this.f5306i) {
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
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public boolean b() {
        return this.f5304g;
    }

    public boolean c() {
        return this.f5305h;
    }

    public int d() {
        return this.f5302e;
    }

    public int e() {
        return this.j;
    }

    public int f() {
        return this.k;
    }

    public boolean g() {
        return this.f5306i;
    }

    public boolean h() {
        return this.f5300c;
    }

    public boolean i() {
        return this.f5301d;
    }

    public boolean j() {
        return this.l;
    }

    public String toString() {
        String str = this.n;
        if (str != null) {
            return str;
        }
        String strA = a();
        this.n = strA;
        return strA;
    }

    public d(a aVar) {
        this.f5300c = aVar.f5307a;
        this.f5301d = aVar.f5308b;
        this.f5302e = aVar.f5309c;
        this.f5303f = -1;
        this.f5304g = false;
        this.f5305h = false;
        this.f5306i = false;
        this.j = aVar.f5310d;
        this.k = aVar.f5311e;
        this.l = aVar.f5312f;
        this.m = aVar.f5313g;
    }
}
