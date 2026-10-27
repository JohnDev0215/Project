package E3;

import B5.G;
import N0.q;
import Q8.o;
import android.webkit.MimeTypeMap;
import c9.C1046g;
import c9.u;
import c9.y;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import m7.n;
import r9.AbstractC2312b;
import r9.D;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class l implements g {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C1046g f1737f = new C1046g(true, true, -1, -1, false, false, false, -1, -1, false, false, false, null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C1046g f1738g = new C1046g(true, false, -1, -1, false, false, false, -1, -1, true, false, false, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final K3.m f1740b;
    public final n c;
    public final n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1741e;

    public l(String str, K3.m mVar, n nVar, n nVar2, boolean z6) {
        this.f1739a = str;
        this.f1740b = mVar;
        this.c = nVar;
        this.d = nVar2;
        this.f1741e = z6;
    }

    public static String d(String str, y yVar) {
        String strB;
        String str2 = yVar != null ? yVar.f12627a : null;
        if ((str2 == null || o.z0(str2, "text/plain", false)) && (strB = N3.d.b(MimeTypeMap.getSingleton(), str)) != null) {
            return strB;
        }
        if (str2 != null) {
            return Q8.h.c1(str2, ';');
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0208 A[Catch: Exception -> 0x0203, TryCatch #5 {Exception -> 0x0203, blocks: (B:91:0x01d4, B:93:0x01da, B:95:0x01fa, B:97:0x01ff, B:96:0x01fd, B:101:0x0208, B:102:0x020d), top: B:122:0x01d4 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0094 A[Catch: Exception -> 0x0054, TRY_ENTER, TryCatch #4 {Exception -> 0x0054, blocks: (B:20:0x004b, B:64:0x014d, B:109:0x0219, B:110:0x021e, B:36:0x0094, B:38:0x009e, B:45:0x00d0, B:47:0x00d4, B:51:0x00ed, B:61:0x0139, B:53:0x0105, B:55:0x0111, B:56:0x011a, B:41:0x00b6, B:43:0x00c0, B:58:0x0124, B:59:0x012b, B:60:0x012c), top: B:121:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012c A[Catch: Exception -> 0x0054, TryCatch #4 {Exception -> 0x0054, blocks: (B:20:0x004b, B:64:0x014d, B:109:0x0219, B:110:0x021e, B:36:0x0094, B:38:0x009e, B:45:0x00d0, B:47:0x00d4, B:51:0x00ed, B:61:0x0139, B:53:0x0105, B:55:0x0111, B:56:0x011a, B:41:0x00b6, B:43:0x00c0, B:58:0x0124, B:59:0x012b, B:60:0x012c), top: B:121:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01da A[Catch: Exception -> 0x0203, TryCatch #5 {Exception -> 0x0203, blocks: (B:91:0x01d4, B:93:0x01da, B:95:0x01fa, B:97:0x01ff, B:96:0x01fd, B:101:0x0208, B:102:0x020d), top: B:122:0x01d4 }] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r13v19, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r13v7, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v19 */
    @Override // E3.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(q7.InterfaceC2189c r14) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 549
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E3.l.a(q7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(H0.b r5, s7.AbstractC2410c r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof E3.j
            if (r0 == 0) goto L13
            r0 = r6
            E3.j r0 = (E3.j) r0
            int r1 = r0.f1732n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1732n = r1
            goto L18
        L13:
            E3.j r0 = new E3.j
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f1732n
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            h8.AbstractC1386c.u(r6)
            goto L90
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            h8.AbstractC1386c.u(r6)
            android.graphics.Bitmap$Config r6 = N3.d.f4575a
            android.os.Looper r6 = android.os.Looper.myLooper()
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            boolean r6 = A7.m.a(r6, r2)
            m7.n r2 = r4.c
            if (r6 == 0) goto L63
            K3.m r4 = r4.f1740b
            K3.b r4 = r4.o
            boolean r4 = r4.l
            if (r4 != 0) goto L5d
            java.lang.Object r4 = r2.getValue()
            c9.h r4 = (c9.InterfaceC1047h) r4
            c9.C r4 = (c9.C) r4
            g9.j r4 = r4.a(r5)
            c9.H r4 = r4.f()
            goto L93
        L5d:
            android.os.NetworkOnMainThreadException r4 = new android.os.NetworkOnMainThreadException
            r4.<init>()
            throw r4
        L63:
            java.lang.Object r4 = r2.getValue()
            c9.h r4 = (c9.InterfaceC1047h) r4
            c9.C r4 = (c9.C) r4
            g9.j r4 = r4.a(r5)
            r0.f1732n = r3
            S8.i r5 = new S8.i
            q7.c r6 = h6.e.k(r0)
            r5.<init>(r3, r6)
            r5.s()
            N3.e r6 = new N3.e
            r0 = 0
            r6.<init>(r4, r0, r5)
            r4.e(r6)
            r5.u(r6)
            java.lang.Object r6 = r5.q()
            if (r6 != r1) goto L90
            return r1
        L90:
            r4 = r6
            c9.H r4 = (c9.H) r4
        L93:
            boolean r5 = r4.j()
            if (r5 != 0) goto Lbd
            r5 = 304(0x130, float:4.26E-43)
            int r6 = r4.o
            if (r6 == r5) goto Lbd
            B3.m r5 = r4.f12518r
            if (r5 == 0) goto La6
            N3.d.a(r5)
        La6:
            A9.k r5 = new A9.k
            java.lang.String r0 = "HTTP "
            java.lang.String r1 = ": "
            java.lang.StringBuilder r6 = M5.b.j(r0, r6, r1)
            java.lang.String r4 = r4.f12515n
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r5.<init>(r4)
            throw r5
        Lbd:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: E3.l.b(H0.b, s7.c):java.lang.Object");
    }

    public final r9.o c() {
        Object value = this.d.getValue();
        A7.m.c(value);
        return ((C3.h) value).f995a;
    }

    public final H0.b e() {
        q qVar = new q();
        qVar.T(this.f1739a);
        K3.m mVar = this.f1740b;
        u uVar = mVar.f3572j;
        A7.m.f(uVar, "headers");
        qVar.o = uVar.q();
        for (Map.Entry entry : mVar.f3573k.f3584a.entrySet()) {
            Object key = entry.getKey();
            A7.m.d(key, "null cannot be cast to non-null type java.lang.Class<kotlin.Any>");
            Class cls = (Class) key;
            Object value = entry.getValue();
            if (value == null) {
                ((LinkedHashMap) qVar.l).remove(cls);
            } else {
                if (((LinkedHashMap) qVar.l).isEmpty()) {
                    qVar.l = new LinkedHashMap();
                }
                LinkedHashMap linkedHashMap = (LinkedHashMap) qVar.l;
                Object objCast = cls.cast(value);
                A7.m.c(objCast);
                linkedHashMap.put(cls, objCast);
            }
        }
        K3.b bVar = mVar.f3575n;
        boolean z6 = bVar.l;
        boolean z10 = mVar.o.l;
        if (!z10 && z6) {
            qVar.s(C1046g.o);
        } else if (!z10 || z6) {
            if (!z10 && !z6) {
                qVar.s(f1738g);
            }
        } else if (bVar.f3512m) {
            qVar.s(C1046g.f12558n);
        } else {
            qVar.s(f1737f);
        }
        return qVar.r();
    }

    public final J3.b f(C3.g gVar) throws Throwable {
        Throwable th;
        J3.b bVar;
        try {
            r9.o oVarC = c();
            G g7 = gVar.l;
            if (g7.f654m) {
                throw new IllegalStateException("snapshot is closed");
            }
            D dC = AbstractC2312b.c(oVarC.k((z) ((C3.b) g7.f655n).c.get(0)));
            try {
                bVar = new J3.b(dC);
                try {
                    dC.close();
                    th = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                try {
                    dC.close();
                } catch (Throwable th4) {
                    h6.q.l(th3, th4);
                }
                th = th3;
                bVar = null;
            }
            if (th == null) {
                return bVar;
            }
            throw th;
        } catch (IOException unused) {
            return null;
        }
    }

    public final B3.l g(C3.g gVar) {
        G g7 = gVar.l;
        if (g7.f654m) {
            throw new IllegalStateException("snapshot is closed");
        }
        z zVar = (z) ((C3.b) g7.f655n).c.get(1);
        r9.o oVarC = c();
        String str = this.f1740b.f3571i;
        if (str == null) {
            str = this.f1739a;
        }
        return new B3.l(zVar, oVarC, str, gVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final C3.g h(C3.g r4, H0.b r5, c9.H r6, J3.b r7) {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E3.l.h(C3.g, H0.b, c9.H, J3.b):C3.g");
    }
}
