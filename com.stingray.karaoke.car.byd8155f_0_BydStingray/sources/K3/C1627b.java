package k3;

import a.AbstractC0904a;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import g5.C1300a;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: renamed from: k3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1627b {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f17885j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f17886k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f17887m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f17888n;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f17889p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f17890q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f17891r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1 f17892a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17893b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Uri f17894e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CharSequence f17895f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bundle f17896g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1300a f17897h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f17898i;

    static {
        int i7 = AbstractC1343A.f15195a;
        f17885j = Integer.toString(0, 36);
        f17886k = Integer.toString(1, 36);
        l = Integer.toString(2, 36);
        f17887m = Integer.toString(3, 36);
        f17888n = Integer.toString(4, 36);
        o = Integer.toString(5, 36);
        f17889p = Integer.toString(6, 36);
        f17890q = Integer.toString(7, 36);
        f17891r = Integer.toString(8, 36);
    }

    public C1627b(y1 y1Var, int i7, int i10, int i11, Uri uri, CharSequence charSequence, Bundle bundle, boolean z6, C1300a c1300a) {
        this.f17892a = y1Var;
        this.f17893b = i7;
        this.c = i10;
        this.d = i11;
        this.f17894e = uri;
        this.f17895f = charSequence;
        this.f17896g = new Bundle(bundle);
        this.f17898i = z6;
        this.f17897h = c1300a;
    }

    public static C1627b a(int i7, Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(f17885j);
        y1 y1VarA = bundle2 == null ? null : y1.a(bundle2);
        int i10 = bundle.getInt(f17886k, -1);
        int i11 = bundle.getInt(l, 0);
        CharSequence charSequence = bundle.getCharSequence(f17887m, "");
        Bundle bundle3 = bundle.getBundle(f17888n);
        boolean z6 = i7 < 3 || bundle.getBoolean(o, true);
        Uri uri = (Uri) bundle.getParcelable(f17889p);
        int i12 = bundle.getInt(f17890q, 0);
        int[] intArray = bundle.getIntArray(f17891r);
        C1624a c1624a = new C1624a(i12, i11);
        if (y1VarA != null) {
            c1624a.e(y1VarA);
        }
        if (i10 != -1) {
            c1624a.d(i10);
        }
        if (uri != null && (AbstractC0904a.A(uri.getScheme(), "content") || AbstractC0904a.A(uri.getScheme(), "android.resource"))) {
            AbstractC1346c.b("Only content or resource Uris are supported for CommandButton", AbstractC0904a.A(uri.getScheme(), "content") || AbstractC0904a.A(uri.getScheme(), "android.resource"));
            c1624a.f17879e = uri;
        }
        c1624a.f17880f = charSequence;
        if (bundle3 == null) {
            bundle3 = Bundle.EMPTY;
        }
        c1624a.c(bundle3);
        c1624a.f17882h = z6;
        if (intArray == null) {
            intArray = new int[]{6};
        }
        AbstractC1346c.c(intArray.length != 0);
        C1300a c1300a = C1300a.f14976n;
        c1624a.f17883i = intArray.length == 0 ? C1300a.f14976n : new C1300a(Arrays.copyOf(intArray, intArray.length));
        return c1624a.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.os.Bundle b() {
        /*
            r5 = this;
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            k3.y1 r1 = r5.f17892a
            if (r1 == 0) goto L12
            android.os.Bundle r1 = r1.b()
            java.lang.String r2 = k3.C1627b.f17885j
            r0.putBundle(r2, r1)
        L12:
            r1 = -1
            int r2 = r5.f17893b
            if (r2 == r1) goto L1c
            java.lang.String r1 = k3.C1627b.f17886k
            r0.putInt(r1, r2)
        L1c:
            int r1 = r5.c
            if (r1 == 0) goto L25
            java.lang.String r2 = k3.C1627b.f17890q
            r0.putInt(r2, r1)
        L25:
            int r1 = r5.d
            if (r1 == 0) goto L2e
            java.lang.String r2 = k3.C1627b.l
            r0.putInt(r2, r1)
        L2e:
            java.lang.String r1 = ""
            java.lang.CharSequence r2 = r5.f17895f
            if (r2 == r1) goto L39
            java.lang.String r1 = k3.C1627b.f17887m
            r0.putCharSequence(r1, r2)
        L39:
            android.os.Bundle r1 = r5.f17896g
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L46
            java.lang.String r2 = k3.C1627b.f17888n
            r0.putBundle(r2, r1)
        L46:
            android.net.Uri r1 = r5.f17894e
            if (r1 == 0) goto L4f
            java.lang.String r2 = k3.C1627b.f17889p
            r0.putParcelable(r2, r1)
        L4f:
            boolean r1 = r5.f17898i
            if (r1 != 0) goto L58
            java.lang.String r2 = k3.C1627b.o
            r0.putBoolean(r2, r1)
        L58:
            g5.a r5 = r5.f17897h
            int r1 = r5.f14977m
            int[] r2 = r5.l
            r3 = 1
            r4 = 0
            if (r1 != r3) goto L6a
            P8.n.p(r4, r1)
            r1 = r2[r4]
            r3 = 6
            if (r1 == r3) goto L75
        L6a:
            int r5 = r5.f14977m
            int[] r5 = java.util.Arrays.copyOfRange(r2, r4, r5)
            java.lang.String r1 = k3.C1627b.f17891r
            r0.putIntArray(r1, r5)
        L75:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1627b.b():android.os.Bundle");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1627b)) {
            return false;
        }
        C1627b c1627b = (C1627b) obj;
        return AbstractC0904a.A(this.f17892a, c1627b.f17892a) && this.f17893b == c1627b.f17893b && this.c == c1627b.c && this.d == c1627b.d && AbstractC0904a.A(this.f17894e, c1627b.f17894e) && TextUtils.equals(this.f17895f, c1627b.f17895f) && this.f17898i == c1627b.f17898i && this.f17897h.equals(c1627b.f17897h);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17892a, Integer.valueOf(this.f17893b), Integer.valueOf(this.c), Integer.valueOf(this.d), this.f17895f, Boolean.valueOf(this.f17898i), this.f17894e, this.f17897h});
    }
}
