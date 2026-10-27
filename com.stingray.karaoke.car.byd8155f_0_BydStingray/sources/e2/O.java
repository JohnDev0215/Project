package e2;

import android.os.Bundle;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public class O extends Exception {
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f13986p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f13987q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f13988r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String f13989s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f13990t;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f13991m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Bundle f13992n;

    static {
        int i7 = AbstractC1343A.f15195a;
        o = Integer.toString(0, 36);
        f13986p = Integer.toString(1, 36);
        f13987q = Integer.toString(2, 36);
        f13988r = Integer.toString(3, 36);
        f13989s = Integer.toString(4, 36);
        f13990t = Integer.toString(5, 36);
    }

    public O(String str, Throwable th, int i7, Bundle bundle, long j3) {
        super(str, th);
        this.l = i7;
        this.f13992n = bundle;
        this.f13991m = j3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
    
        if (r3 == null) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(e2.O r8) {
        /*
            r7 = this;
            r0 = 1
            if (r7 != r8) goto L4
            return r0
        L4:
            r1 = 0
            if (r8 == 0) goto L64
            java.lang.Class r2 = r7.getClass()
            java.lang.Class r3 = r8.getClass()
            if (r2 == r3) goto L12
            goto L64
        L12:
            java.lang.Throwable r2 = r7.getCause()
            java.lang.Throwable r3 = r8.getCause()
            if (r2 == 0) goto L3e
            if (r3 == 0) goto L3e
            java.lang.String r4 = r2.getMessage()
            java.lang.String r5 = r3.getMessage()
            int r6 = h2.AbstractC1343A.f15195a
            boolean r4 = java.util.Objects.equals(r4, r5)
            if (r4 != 0) goto L2f
            return r1
        L2f:
            java.lang.Class r2 = r2.getClass()
            java.lang.Class r3 = r3.getClass()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L43
            return r1
        L3e:
            if (r2 != 0) goto L64
            if (r3 == 0) goto L43
            goto L64
        L43:
            int r2 = r7.l
            int r3 = r8.l
            if (r2 != r3) goto L62
            java.lang.String r2 = r7.getMessage()
            java.lang.String r3 = r8.getMessage()
            int r4 = h2.AbstractC1343A.f15195a
            boolean r2 = java.util.Objects.equals(r2, r3)
            if (r2 == 0) goto L62
            long r2 = r7.f13991m
            long r7 = r8.f13991m
            int r7 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r7 != 0) goto L62
            goto L63
        L62:
            r0 = r1
        L63:
            return r0
        L64:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: e2.O.a(e2.O):boolean");
    }

    public Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putInt(o, this.l);
        bundle.putLong(f13986p, this.f13991m);
        bundle.putString(f13987q, getMessage());
        bundle.putBundle(f13990t, this.f13992n);
        Throwable cause = getCause();
        if (cause != null) {
            bundle.putString(f13988r, cause.getClass().getName());
            bundle.putString(f13989s, cause.getMessage());
        }
        return bundle;
    }
}
