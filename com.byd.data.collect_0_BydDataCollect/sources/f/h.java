package f;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: CacheControl.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f5091a = new a().c().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f5092b = new a().d().b(Integer.MAX_VALUE, TimeUnit.SECONDS).a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f5093c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f5094d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f5095e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f5096f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f5097g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final boolean f5098h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final boolean f5099i;
    private final int j;
    private final int k;
    private final boolean l;
    private final boolean m;
    private final boolean n;

    @Nullable
    String o;

    /* JADX INFO: compiled from: CacheControl.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f5100a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f5101b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f5102c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f5103d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f5104e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f5105f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f5106g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f5107h;

        public h a() {
            return new h(this);
        }

        public a b(int i2, TimeUnit timeUnit) {
            if (i2 >= 0) {
                long seconds = timeUnit.toSeconds(i2);
                this.f5103d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i2);
        }

        public a c() {
            this.f5100a = true;
            return this;
        }

        public a d() {
            this.f5105f = true;
            return this;
        }
    }

    private h(boolean z, boolean z2, int i2, int i3, boolean z3, boolean z4, boolean z5, int i4, int i5, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.f5093c = z;
        this.f5094d = z2;
        this.f5095e = i2;
        this.f5096f = i3;
        this.f5097g = z3;
        this.f5098h = z4;
        this.f5099i = z5;
        this.j = i4;
        this.k = i5;
        this.l = z6;
        this.m = z7;
        this.n = z8;
        this.o = str;
    }

    private String a() {
        StringBuilder sb = new StringBuilder();
        if (this.f5093c) {
            sb.append("no-cache, ");
        }
        if (this.f5094d) {
            sb.append("no-store, ");
        }
        if (this.f5095e != -1) {
            sb.append("max-age=");
            sb.append(this.f5095e);
            sb.append(", ");
        }
        if (this.f5096f != -1) {
            sb.append("s-maxage=");
            sb.append(this.f5096f);
            sb.append(", ");
        }
        if (this.f5097g) {
            sb.append("private, ");
        }
        if (this.f5098h) {
            sb.append("public, ");
        }
        if (this.f5099i) {
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

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static f.h k(f.w r22) {
        /*
            Method dump skipped, instruction units count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f.h.k(f.w):f.h");
    }

    public boolean b() {
        return this.f5097g;
    }

    public boolean c() {
        return this.f5098h;
    }

    public int d() {
        return this.f5095e;
    }

    public int e() {
        return this.j;
    }

    public int f() {
        return this.k;
    }

    public boolean g() {
        return this.f5099i;
    }

    public boolean h() {
        return this.f5093c;
    }

    public boolean i() {
        return this.f5094d;
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

    h(a aVar) {
        this.f5093c = aVar.f5100a;
        this.f5094d = aVar.f5101b;
        this.f5095e = aVar.f5102c;
        this.f5096f = -1;
        this.f5097g = false;
        this.f5098h = false;
        this.f5099i = false;
        this.j = aVar.f5103d;
        this.k = aVar.f5104e;
        this.l = aVar.f5105f;
        this.m = aVar.f5106g;
        this.n = aVar.f5107h;
    }
}
