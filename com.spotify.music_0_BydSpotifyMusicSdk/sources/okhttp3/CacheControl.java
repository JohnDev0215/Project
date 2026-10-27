package okhttp3;

import java.util.concurrent.TimeUnit;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CacheControl {
    public static final Companion n = new Companion(0);
    public static final CacheControl o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final CacheControl f91p;
    public final boolean a;
    public final boolean b;
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final int h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public String m;

    public static final class Builder {
        public boolean a;
        public boolean b;
        public final int c = -1;
        public int d = -1;
        public final int e = -1;
        public boolean f;

        public final CacheControl a() {
            return new CacheControl(this.a, this.b, this.c, -1, false, false, false, this.d, this.e, this.f, false, false, null);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static okhttp3.CacheControl a(okhttp3.Headers r25) {
            /*
                Method dump skipped, instruction units count: 488
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.CacheControl.Companion.a(okhttp3.Headers):okhttp3.CacheControl");
        }
    }

    static {
        Builder builder = new Builder();
        builder.a = true;
        o = builder.a();
        Builder builder2 = new Builder();
        builder2.f = true;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        v03.j(timeUnit, "timeUnit");
        long seconds = timeUnit.toSeconds(Integer.MAX_VALUE);
        builder2.d = seconds <= 2147483647L ? (int) seconds : Integer.MAX_VALUE;
        f91p = builder2.a();
    }

    public CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.a = z;
        this.b = z2;
        this.c = i;
        this.d = i2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = i3;
        this.i = i4;
        this.j = z6;
        this.k = z7;
        this.l = z8;
        this.m = str;
    }

    public final String toString() {
        String str = this.m;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.a) {
            sb.append("no-cache, ");
        }
        if (this.b) {
            sb.append("no-store, ");
        }
        int i = this.c;
        if (i != -1) {
            sb.append("max-age=");
            sb.append(i);
            sb.append(", ");
        }
        int i2 = this.d;
        if (i2 != -1) {
            sb.append("s-maxage=");
            sb.append(i2);
            sb.append(", ");
        }
        if (this.e) {
            sb.append("private, ");
        }
        if (this.f) {
            sb.append("public, ");
        }
        if (this.g) {
            sb.append("must-revalidate, ");
        }
        int i3 = this.h;
        if (i3 != -1) {
            sb.append("max-stale=");
            sb.append(i3);
            sb.append(", ");
        }
        int i4 = this.i;
        if (i4 != -1) {
            sb.append("min-fresh=");
            sb.append(i4);
            sb.append(", ");
        }
        if (this.j) {
            sb.append("only-if-cached, ");
        }
        if (this.k) {
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
        v03.i(string, "StringBuilder().apply(builderAction).toString()");
        this.m = string;
        return string;
    }
}
