package c9;

import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: c9.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1046g {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1046g f12558n = new C1046g(true, false, -1, -1, false, false, false, -1, -1, false, false, false, null);
    public static final C1046g o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f12559a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f12560b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f12561e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f12562f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f12563g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f12564h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12565i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f12566j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f12567k;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f12568m;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        A7.m.f(timeUnit, "timeUnit");
        long seconds = timeUnit.toSeconds(Integer.MAX_VALUE);
        o = new C1046g(false, false, -1, -1, false, false, false, seconds <= 2147483647L ? (int) seconds : Integer.MAX_VALUE, -1, true, false, false, null);
    }

    public C1046g(boolean z6, boolean z10, int i7, int i10, boolean z11, boolean z12, boolean z13, int i11, int i12, boolean z14, boolean z15, boolean z16, String str) {
        this.f12559a = z6;
        this.f12560b = z10;
        this.c = i7;
        this.d = i10;
        this.f12561e = z11;
        this.f12562f = z12;
        this.f12563g = z13;
        this.f12564h = i11;
        this.f12565i = i12;
        this.f12566j = z14;
        this.f12567k = z15;
        this.l = z16;
        this.f12568m = str;
    }

    public final String toString() {
        String str = this.f12568m;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.f12559a) {
            sb.append("no-cache, ");
        }
        if (this.f12560b) {
            sb.append("no-store, ");
        }
        int i7 = this.c;
        if (i7 != -1) {
            sb.append("max-age=");
            sb.append(i7);
            sb.append(", ");
        }
        int i10 = this.d;
        if (i10 != -1) {
            sb.append("s-maxage=");
            sb.append(i10);
            sb.append(", ");
        }
        if (this.f12561e) {
            sb.append("private, ");
        }
        if (this.f12562f) {
            sb.append("public, ");
        }
        if (this.f12563g) {
            sb.append("must-revalidate, ");
        }
        int i11 = this.f12564h;
        if (i11 != -1) {
            sb.append("max-stale=");
            sb.append(i11);
            sb.append(", ");
        }
        int i12 = this.f12565i;
        if (i12 != -1) {
            sb.append("min-fresh=");
            sb.append(i12);
            sb.append(", ");
        }
        if (this.f12566j) {
            sb.append("only-if-cached, ");
        }
        if (this.f12567k) {
            sb.append("no-transform, ");
        }
        if (this.l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        this.f12568m = string;
        return string;
    }
}
