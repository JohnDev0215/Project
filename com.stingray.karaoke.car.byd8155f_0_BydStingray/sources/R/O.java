package R;

import C0.InterfaceC0112t;
import C0.V;
import Q.AbstractC0524p;
import T.C0735d;
import T.C0754m0;
import T.C0761q;
import n0.C1891t;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class O {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f7278b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final float f7281g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final float f7282h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final g0.o f7283i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f7277a = io.sentry.config.a.e(0, 0, 0, 0);
    public static final float c = 12;
    public static final float d = 4;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f7279e = 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f7280f = 24;

    static {
        float f10 = 16;
        f7278b = f10;
        f7281g = f10;
        f7282h = f10;
        float f11 = 48;
        f7283i = androidx.compose.foundation.layout.c.a(g0.l.f14684a, f11, f11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x042b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0483  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x04d8  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0585  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0641  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0645  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x069b  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x069e  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x06b5  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x06ce  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x06e6  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x06fd  */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v36 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v39 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(java.lang.String r40, z7.InterfaceC2785n r41, B2.C0074n r42, z7.InterfaceC2785n r43, z7.InterfaceC2785n r44, z7.InterfaceC2785n r45, z7.InterfaceC2785n r46, z7.InterfaceC2785n r47, z7.InterfaceC2785n r48, z7.InterfaceC2785n r49, boolean r50, boolean r51, boolean r52, C.k r53, D.L r54, Q.M0 r55, z7.InterfaceC2785n r56, T.C0761q r57, int r58, int r59) {
        /*
            Method dump skipped, instruction units count: 1929
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: R.O.a(java.lang.String, z7.n, B2.n, z7.n, z7.n, z7.n, z7.n, z7.n, z7.n, z7.n, boolean, boolean, boolean, C.k, D.L, Q.M0, z7.n, T.q, int, int):void");
    }

    public static final void b(long j3, N0.L l, InterfaceC2785n interfaceC2785n, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1208685580);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.e(j3) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(l) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            l9.d.o(j3, l, interfaceC2785n, c0761q, i10 & 1022);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new F(j3, l, interfaceC2785n, i7, 1);
        }
    }

    public static final void c(long j3, InterfaceC2785n interfaceC2785n, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(660142980);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.e(j3) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            C0735d.a(AbstractC0524p.f6016a.a(new C1891t(j3)), interfaceC2785n, c0761q, (i10 & 112) | 8);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(j3, interfaceC2785n, i7);
        }
    }

    public static final Object d(C0.J j3) {
        Object objI = j3.i();
        InterfaceC0112t interfaceC0112t = objI instanceof InterfaceC0112t ? (InterfaceC0112t) objI : null;
        if (interfaceC0112t != null) {
            return interfaceC0112t.z();
        }
        return null;
    }

    public static final int e(V v10) {
        if (v10 != null) {
            return v10.f871m;
        }
        return 0;
    }

    public static final int f(V v10) {
        if (v10 != null) {
            return v10.l;
        }
        return 0;
    }
}
