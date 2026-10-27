package w2;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.Pair;
import d5.C1099v;
import d5.I;
import d5.b0;
import d5.d0;
import e2.C1195c;
import e2.C1208p;
import e2.g0;
import e2.h0;
import e2.j0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.RandomAccess;
import n2.D;
import u2.n0;

/* JADX INFO: loaded from: classes.dex */
public final class o extends r {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final b0 f23261j = new C1099v(new C1.b(14));
    public final Object c;
    public final Context d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l9.n f23262e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f23263f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C2595i f23264g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final A2.c f23265h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1195c f23266i;

    public o(Context context) {
        l9.n nVar = new l9.n(14);
        String str = C2595i.f23217r0;
        C2595i c2595i = new C2595i(new C2594h(context));
        this.c = new Object();
        this.d = context.getApplicationContext();
        this.f23262e = nVar;
        this.f23264g = c2595i;
        this.f23266i = C1195c.f14046g;
        boolean zF = AbstractC1343A.F(context);
        this.f23263f = zF;
        if (!zF && AbstractC1343A.f15195a >= 32) {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            this.f23265h = audioManager == null ? null : new A2.c(audioManager.getSpatializer());
        }
        boolean z6 = this.f23264g.f23230m0;
    }

    public static int b(int i7, int i10) {
        if (i7 == 0 || i7 != i10) {
            return Integer.bitCount(i7 & i10);
        }
        return Integer.MAX_VALUE;
    }

    public static void c(n0 n0Var, C2595i c2595i, HashMap map) {
        for (int i7 = 0; i7 < n0Var.f22881a; i7++) {
            h0 h0Var = (h0) c2595i.f14194A.get(n0Var.a(i7));
            if (h0Var != null) {
                g0 g0Var = h0Var.f14118a;
                h0 h0Var2 = (h0) map.get(Integer.valueOf(g0Var.c));
                if (h0Var2 == null || (h0Var2.f14119b.isEmpty() && !h0Var.f14119b.isEmpty())) {
                    map.put(Integer.valueOf(g0Var.c), h0Var);
                }
            }
        }
    }

    public static int d(C1208p c1208p, String str, boolean z6) {
        if (!TextUtils.isEmpty(str) && str.equals(c1208p.d)) {
            return 4;
        }
        String strG = g(str);
        String strG2 = g(c1208p.d);
        if (strG2 == null || strG == null) {
            return (z6 && strG2 == null) ? 1 : 0;
        }
        if (strG2.startsWith(strG) || strG.startsWith(strG2)) {
            return 3;
        }
        int i7 = AbstractC1343A.f15195a;
        return strG2.split("-", 2)[0].equals(strG.split("-", 2)[0]) ? 2 : 0;
    }

    public static String g(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "und")) {
            return null;
        }
        return str;
    }

    public static boolean h(C2595i c2595i, int i7, C1208p c1208p) {
        if ((i7 & 3584) == 0) {
            return false;
        }
        j0 j0Var = c2595i.f14209s;
        if (j0Var.c && (i7 & 2048) == 0) {
            return false;
        }
        if (j0Var.f14133b) {
            return !(c1208p.f14298F != 0 || c1208p.f14299G != 0) || ((i7 & 1024) != 0);
        }
        return true;
    }

    public static Pair i(int i7, i2.q qVar, int[][][] iArr, l lVar, Comparator comparator) {
        RandomAccess randomAccessB;
        boolean z6;
        i2.q qVar2 = qVar;
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (i10 < qVar2.f15716a) {
            if (i7 == ((int[]) qVar2.f15717b)[i10]) {
                n0 n0Var = ((n0[]) qVar2.c)[i10];
                for (int i11 = 0; i11 < n0Var.f22881a; i11++) {
                    g0 g0VarA = n0Var.a(i11);
                    d0 d0VarG = lVar.g(i10, g0VarA, iArr[i10][i11]);
                    int i12 = g0VarA.f14104a;
                    boolean[] zArr = new boolean[i12];
                    for (int i13 = 0; i13 < i12; i13++) {
                        m mVar = (m) d0VarG.get(i13);
                        int iA = mVar.a();
                        if (!zArr[i13] && iA != 0) {
                            if (iA == 1) {
                                randomAccessB = I.B(mVar);
                            } else {
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add(mVar);
                                for (int i14 = i13 + 1; i14 < i12; i14++) {
                                    m mVar2 = (m) d0VarG.get(i14);
                                    if (mVar2.a() == 2 && mVar.b(mVar2)) {
                                        arrayList2.add(mVar2);
                                        z6 = true;
                                        zArr[i14] = true;
                                    } else {
                                        z6 = true;
                                    }
                                }
                                randomAccessB = arrayList2;
                            }
                            arrayList.add(randomAccessB);
                        }
                    }
                }
            }
            i10++;
            qVar2 = qVar;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        List list = (List) Collections.max(arrayList, comparator);
        int[] iArr2 = new int[list.size()];
        for (int i15 = 0; i15 < list.size(); i15++) {
            iArr2[i15] = ((m) list.get(i15)).f23246n;
        }
        m mVar3 = (m) list.get(0);
        return Pair.create(new p(mVar3.f23245m, iArr2), Integer.valueOf(mVar3.l));
    }

    @Override // w2.r
    public final void a(C1195c c1195c) {
        boolean zEquals;
        synchronized (this.c) {
            zEquals = this.f23266i.equals(c1195c);
            this.f23266i = c1195c;
        }
        if (zEquals) {
            return;
        }
        f();
    }

    public final C2595i e() {
        C2595i c2595i;
        synchronized (this.c) {
            c2595i = this.f23264g;
        }
        return c2595i;
    }

    public final void f() {
        boolean z6;
        D d;
        A2.c cVar;
        synchronized (this.c) {
            try {
                z6 = this.f23264g.f23230m0 && !this.f23263f && AbstractC1343A.f15195a >= 32 && (cVar = this.f23265h) != null && cVar.f311b;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z6 || (d = this.f23269a) == null) {
            return;
        }
        d.f19525t.e(10);
    }

    public final void j(C2595i c2595i) {
        boolean zEquals;
        c2595i.getClass();
        synchronized (this.c) {
            zEquals = this.f23264g.equals(c2595i);
            this.f23264g = c2595i;
        }
        if (zEquals) {
            return;
        }
        if (c2595i.f23230m0 && this.d == null) {
            AbstractC1346c.z("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
        }
        D d = this.f23269a;
        if (d != null) {
            d.f19525t.e(10);
        }
    }
}
