package z2;

import B2.C0064d;
import android.content.Context;
import android.graphics.Point;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Pair;
import android.view.Surface;
import d5.d0;
import e2.C1200h;
import e2.C1207o;
import e2.C1208p;
import e2.L;
import e2.c0;
import e2.f0;
import e2.q0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.x;
import h3.y;
import io.sentry.android.core.RunnableC1447x;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import l5.C1778f;
import n2.AbstractC1905e;
import n2.C1906f;
import n2.C1907g;
import n2.C1913m;
import n2.C1924y;
import n2.RunnableC1918s;
import n2.SurfaceHolderCallbackC1920u;
import n2.a0;
import o2.C2026c;
import p7.C2111a;
import u2.C2492C;
import w8.C2607b;

/* JADX INFO: renamed from: z2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2759e extends s2.q {

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    public static final int[] f24274x1 = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    public static boolean f24275y1;

    /* JADX INFO: renamed from: z1, reason: collision with root package name */
    public static boolean f24276z1;

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public final Context f24277N0;

    /* JADX INFO: renamed from: O0, reason: collision with root package name */
    public final boolean f24278O0;

    /* JADX INFO: renamed from: P0, reason: collision with root package name */
    public final m2.b f24279P0;

    /* JADX INFO: renamed from: Q0, reason: collision with root package name */
    public final int f24280Q0;

    /* JADX INFO: renamed from: R0, reason: collision with root package name */
    public final boolean f24281R0;

    /* JADX INFO: renamed from: S0, reason: collision with root package name */
    public final o f24282S0;

    /* JADX INFO: renamed from: T0, reason: collision with root package name */
    public final s9.d f24283T0;

    /* JADX INFO: renamed from: U0, reason: collision with root package name */
    public C0064d f24284U0;

    /* JADX INFO: renamed from: V0, reason: collision with root package name */
    public boolean f24285V0;

    /* JADX INFO: renamed from: W0, reason: collision with root package name */
    public boolean f24286W0;

    /* JADX INFO: renamed from: X0, reason: collision with root package name */
    public i f24287X0;

    /* JADX INFO: renamed from: Y0, reason: collision with root package name */
    public boolean f24288Y0;

    /* JADX INFO: renamed from: Z0, reason: collision with root package name */
    public List f24289Z0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public Surface f24290a1;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public C2761g f24291b1;
    public h2.u c1;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public boolean f24292d1;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public int f24293e1;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public int f24294f1;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public long f24295g1;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public int f24296h1;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    public int f24297i1;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public int f24298j1;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public long f24299k1;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public int f24300l1;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public long f24301m1;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public q0 f24302n1;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    public q0 f24303o1;

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    public int f24304p1;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    public boolean f24305q1;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    public int f24306r1;

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    public C2758d f24307s1;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    public n f24308t1;

    /* JADX INFO: renamed from: u1, reason: collision with root package name */
    public long f24309u1;

    /* JADX INFO: renamed from: v1, reason: collision with root package name */
    public long f24310v1;
    public boolean w1;

    public C2759e(Context context, s2.j jVar, Handler handler, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u) {
        super(2, jVar, 30.0f);
        Context applicationContext = context.getApplicationContext();
        this.f24277N0 = applicationContext;
        this.f24280Q0 = 50;
        this.f24287X0 = null;
        this.f24279P0 = new m2.b(handler, surfaceHolderCallbackC1920u);
        this.f24278O0 = true;
        this.f24282S0 = new o(applicationContext, this);
        this.f24283T0 = new s9.d();
        this.f24281R0 = "NVIDIA".equals(AbstractC1343A.c);
        this.c1 = h2.u.c;
        this.f24293e1 = 1;
        this.f24294f1 = 0;
        this.f24302n1 = q0.d;
        this.f24306r1 = 0;
        this.f24303o1 = null;
        this.f24304p1 = -1000;
        this.f24309u1 = -9223372036854775807L;
        this.f24310v1 = -9223372036854775807L;
    }

    public static int A0(s2.n nVar, C1208p c1208p) {
        int i7;
        int iIntValue;
        int i10 = c1208p.f14322u;
        if (i10 == -1 || (i7 = c1208p.f14323v) == -1) {
            return -1;
        }
        String str = c1208p.f14316n;
        str.getClass();
        if ("video/dolby-vision".equals(str)) {
            Pair pairD = s2.v.d(c1208p);
            str = (pairD == null || !((iIntValue = ((Integer) pairD.first).intValue()) == 512 || iIntValue == 1 || iIntValue == 2)) ? "video/hevc" : "video/avc";
        }
        switch (str) {
            case "video/3gpp":
            case "video/av01":
            case "video/mp4v-es":
            case "video/x-vnd.on2.vp8":
                return ((i10 * i7) * 3) / 4;
            case "video/hevc":
                return Math.max(2097152, ((i10 * i7) * 3) / 4);
            case "video/avc":
                String str2 = AbstractC1343A.d;
                if ("BRAVIA 4K 2015".equals(str2) || ("Amazon".equals(AbstractC1343A.c) && ("KFSOWI".equals(str2) || ("AFTS".equals(str2) && nVar.f22098f)))) {
                    return -1;
                }
                return ((AbstractC1343A.f(i7, 16) * AbstractC1343A.f(i10, 16)) * 768) / 4;
            case "video/x-vnd.on2.vp9":
                return ((i10 * i7) * 3) / 8;
            default:
                return -1;
        }
    }

    public static List B0(Context context, s2.i iVar, C1208p c1208p, boolean z6, boolean z10) {
        List listE;
        String str = c1208p.f14316n;
        if (str == null) {
            return d0.f13415p;
        }
        if (AbstractC1343A.f15195a >= 26 && "video/dolby-vision".equals(str) && !h6.r.d(context)) {
            String strB = s2.v.b(c1208p);
            if (strB == null) {
                listE = d0.f13415p;
            } else {
                iVar.getClass();
                listE = s2.v.e(strB, z6, z10);
            }
            if (!listE.isEmpty()) {
                return listE;
            }
        }
        return s2.v.g(iVar, c1208p, z6, z10);
    }

    public static int C0(s2.n nVar, C1208p c1208p) {
        if (c1208p.o == -1) {
            return A0(nVar, c1208p);
        }
        List list = c1208p.f14318q;
        int size = list.size();
        int length = 0;
        for (int i7 = 0; i7 < size; i7++) {
            length += ((byte[]) list.get(i7)).length;
        }
        return c1208p.o + length;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008f A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean z0(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 3206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: z2.C2759e.z0(java.lang.String):boolean");
    }

    @Override // s2.q, n2.AbstractC1905e
    public final void C(float f10, float f11) {
        super.C(f10, f11);
        i iVar = this.f24287X0;
        if (iVar != null) {
            iVar.i(f10);
        } else {
            this.f24282S0.h(f10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.Surface D0(s2.n r6) {
        /*
            r5 = this;
            z2.i r0 = r5.f24287X0
            r1 = 0
            r2 = 0
            if (r0 != 0) goto La9
            android.view.Surface r0 = r5.f24290a1
            if (r0 == 0) goto Lb
            return r0
        Lb:
            int r0 = h2.AbstractC1343A.f15195a
            r3 = 35
            if (r0 < r3) goto L16
            boolean r0 = r6.f22100h
            if (r0 == 0) goto L16
            return r2
        L16:
            boolean r0 = r5.H0(r6)
            h2.AbstractC1346c.j(r0)
            z2.g r0 = r5.f24291b1
            if (r0 == 0) goto L2e
            boolean r3 = r0.l
            boolean r4 = r6.f22098f
            if (r3 == r4) goto L2e
            if (r0 == 0) goto L2e
            r0.release()
            r5.f24291b1 = r2
        L2e:
            z2.g r0 = r5.f24291b1
            if (r0 != 0) goto La6
            android.content.Context r0 = r5.f24277N0
            boolean r6 = r6.f22098f
            r2 = 1
            if (r6 == 0) goto L42
            boolean r0 = z2.C2761g.a(r0)
            if (r0 == 0) goto L40
            goto L44
        L40:
            r0 = r1
            goto L45
        L42:
            int r0 = z2.C2761g.o
        L44:
            r0 = r2
        L45:
            h2.AbstractC1346c.j(r0)
            z2.f r0 = new z2.f
            java.lang.String r3 = "ExoPlayer:PlaceholderSurface"
            r0.<init>(r3)
            if (r6 == 0) goto L54
            int r6 = z2.C2761g.o
            goto L55
        L54:
            r6 = r1
        L55:
            r0.start()
            android.os.Handler r3 = new android.os.Handler
            android.os.Looper r4 = r0.getLooper()
            r3.<init>(r4, r0)
            r0.f24311m = r3
            h2.i r4 = new h2.i
            r4.<init>(r3)
            r0.l = r4
            monitor-enter(r0)
            android.os.Handler r3 = r0.f24311m     // Catch: java.lang.Throwable -> L84
            android.os.Message r6 = r3.obtainMessage(r2, r6, r1)     // Catch: java.lang.Throwable -> L84
            r6.sendToTarget()     // Catch: java.lang.Throwable -> L84
        L74:
            z2.g r6 = r0.f24313p     // Catch: java.lang.Throwable -> L84
            if (r6 != 0) goto L88
            java.lang.RuntimeException r6 = r0.o     // Catch: java.lang.Throwable -> L84
            if (r6 != 0) goto L88
            java.lang.Error r6 = r0.f24312n     // Catch: java.lang.Throwable -> L84
            if (r6 != 0) goto L88
            r0.wait()     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            goto L74
        L84:
            r5 = move-exception
            goto La4
        L86:
            r1 = r2
            goto L74
        L88:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L84
            if (r1 == 0) goto L92
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            r6.interrupt()
        L92:
            java.lang.RuntimeException r6 = r0.o
            if (r6 != 0) goto La3
            java.lang.Error r6 = r0.f24312n
            if (r6 != 0) goto La2
            z2.g r6 = r0.f24313p
            r6.getClass()
            r5.f24291b1 = r6
            goto La6
        La2:
            throw r6
        La3:
            throw r6
        La4:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L84
            throw r5
        La6:
            z2.g r5 = r5.f24291b1
            return r5
        La9:
            h2.AbstractC1346c.j(r1)
            h2.AbstractC1346c.k(r2)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: z2.C2759e.D0(s2.n):android.view.Surface");
    }

    public final void E0() {
        if (this.f24296h1 > 0) {
            this.f19640r.getClass();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j3 = jElapsedRealtime - this.f24295g1;
            int i7 = this.f24296h1;
            m2.b bVar = this.f24279P0;
            Handler handler = (Handler) bVar.l;
            if (handler != null) {
                handler.post(new t(bVar, i7, j3));
            }
            this.f24296h1 = 0;
            this.f24295g1 = jElapsedRealtime;
        }
    }

    public final void F0() {
        int i7;
        s2.k kVar;
        if (!this.f24305q1 || (i7 = AbstractC1343A.f15195a) < 23 || (kVar = this.f22137V) == null) {
            return;
        }
        this.f24307s1 = new C2758d(this, kVar);
        if (i7 >= 33) {
            Bundle bundle = new Bundle();
            bundle.putInt("tunnel-peek", 1);
            kVar.d(bundle);
        }
    }

    @Override // s2.q
    public final C1907g G(s2.n nVar, C1208p c1208p, C1208p c1208p2) {
        C1907g c1907gB = nVar.b(c1208p, c1208p2);
        C0064d c0064d = this.f24284U0;
        c0064d.getClass();
        int i7 = c1208p2.f14322u;
        int i10 = c0064d.f556b;
        int i11 = c1907gB.f19660e;
        if (i7 > i10 || c1208p2.f14323v > c0064d.c) {
            i11 |= 256;
        }
        if (C0(nVar, c1208p2) > c0064d.d) {
            i11 |= 64;
        }
        int i12 = i11;
        return new C1907g(nVar.f22095a, c1208p, c1208p2, i12 != 0 ? 0 : c1907gB.d, i12);
    }

    public final void G0(s2.k kVar, int i7, long j3) {
        Surface surface;
        Trace.beginSection("releaseOutputBuffer");
        kVar.f(j3, i7);
        Trace.endSection();
        this.f22121I0.f19651e++;
        this.f24297i1 = 0;
        if (this.f24287X0 == null) {
            q0 q0Var = this.f24302n1;
            boolean zEquals = q0Var.equals(q0.d);
            m2.b bVar = this.f24279P0;
            if (!zEquals && !q0Var.equals(this.f24303o1)) {
                this.f24303o1 = q0Var;
                bVar.y0(q0Var);
            }
            o oVar = this.f24282S0;
            boolean z6 = oVar.d != 3;
            oVar.d = 3;
            oVar.f24349k.getClass();
            oVar.f24344f = AbstractC1343A.H(SystemClock.elapsedRealtime());
            if (!z6 || (surface = this.f24290a1) == null) {
                return;
            }
            Handler handler = (Handler) bVar.l;
            if (handler != null) {
                handler.post(new RunnableC1447x(bVar, surface, SystemClock.elapsedRealtime(), 2));
            }
            this.f24292d1 = true;
        }
    }

    @Override // s2.q
    public final s2.m H(IllegalStateException illegalStateException, s2.n nVar) {
        Surface surface = this.f24290a1;
        C2757c c2757c = new C2757c(illegalStateException, nVar);
        System.identityHashCode(surface);
        if (surface != null) {
            surface.isValid();
        }
        return c2757c;
    }

    public final boolean H0(s2.n nVar) {
        return AbstractC1343A.f15195a >= 23 && !this.f24305q1 && !z0(nVar.f22095a) && (!nVar.f22098f || C2761g.a(this.f24277N0));
    }

    public final void I0(s2.k kVar, int i7) {
        Trace.beginSection("skipVideoBuffer");
        kVar.g(i7);
        Trace.endSection();
        this.f22121I0.f19652f++;
    }

    public final void J0(int i7, int i10) {
        C1906f c1906f = this.f22121I0;
        c1906f.f19654h += i7;
        int i11 = i7 + i10;
        c1906f.f19653g += i11;
        this.f24296h1 += i11;
        int i12 = this.f24297i1 + i11;
        this.f24297i1 = i12;
        c1906f.f19655i = Math.max(i12, c1906f.f19655i);
        int i13 = this.f24280Q0;
        if (i13 <= 0 || this.f24296h1 < i13) {
            return;
        }
        E0();
    }

    public final void K0(long j3) {
        C1906f c1906f = this.f22121I0;
        c1906f.f19657k += j3;
        c1906f.l++;
        this.f24299k1 += j3;
        this.f24300l1++;
    }

    @Override // s2.q
    public final int P(m2.g gVar) {
        return (AbstractC1343A.f15195a < 34 || !this.f24305q1 || gVar.f18957r >= this.f19645w) ? 0 : 32;
    }

    @Override // s2.q
    public final boolean Q() {
        return this.f24305q1 && AbstractC1343A.f15195a < 23;
    }

    @Override // s2.q
    public final float R(float f10, C1208p[] c1208pArr) {
        float fMax = -1.0f;
        for (C1208p c1208p : c1208pArr) {
            float f11 = c1208p.f14324w;
            if (f11 != -1.0f) {
                fMax = Math.max(fMax, f11);
            }
        }
        if (fMax == -1.0f) {
            return -1.0f;
        }
        return fMax * f10;
    }

    @Override // s2.q
    public final ArrayList S(s2.i iVar, C1208p c1208p, boolean z6) {
        List listB0 = B0(this.f24277N0, iVar, c1208p, z6, this.f24305q1);
        HashMap map = s2.v.f22170a;
        ArrayList arrayList = new ArrayList(listB0);
        Collections.sort(arrayList, new C2111a(new C2026c(c1208p, 4), 1));
        return arrayList;
    }

    @Override // s2.q
    public final H0.b T(s2.n nVar, C1208p c1208p, MediaCrypto mediaCrypto, float f10) {
        int i7;
        C1200h c1200h;
        int i10;
        C0064d c0064d;
        int i11;
        Point point;
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        int i12;
        float f11;
        Point point2;
        int i13;
        byte b10;
        boolean z6;
        Pair pairD;
        int iA0;
        String str = nVar.c;
        C1208p[] c1208pArr = this.f19643u;
        c1208pArr.getClass();
        int iMax = c1208p.f14322u;
        int iC0 = C0(nVar, c1208p);
        int length = c1208pArr.length;
        float f12 = c1208p.f14324w;
        int i14 = c1208p.f14322u;
        C1200h c1200h2 = c1208p.B;
        int i15 = c1208p.f14323v;
        if (length == 1) {
            if (iC0 != -1 && (iA0 = A0(nVar, c1208p)) != -1) {
                iC0 = Math.min((int) (iC0 * 1.5f), iA0);
            }
            c0064d = new C0064d(iMax, i15, iC0, 5);
            i7 = i14;
            c1200h = c1200h2;
            i10 = i15;
        } else {
            int length2 = c1208pArr.length;
            int iMax2 = i15;
            int i16 = 0;
            boolean z10 = false;
            while (i16 < length2) {
                C1208p c1208p2 = c1208pArr[i16];
                C1208p[] c1208pArr2 = c1208pArr;
                if (c1200h2 != null && c1208p2.B == null) {
                    C1207o c1207oA = c1208p2.a();
                    c1207oA.f14227A = c1200h2;
                    c1208p2 = new C1208p(c1207oA);
                }
                if (nVar.b(c1208p, c1208p2).d != 0) {
                    int i17 = c1208p2.f14323v;
                    i13 = length2;
                    int i18 = c1208p2.f14322u;
                    b10 = -1;
                    z10 |= i18 == -1 || i17 == -1;
                    iMax = Math.max(iMax, i18);
                    iMax2 = Math.max(iMax2, i17);
                    iC0 = Math.max(iC0, C0(nVar, c1208p2));
                } else {
                    i13 = length2;
                    b10 = -1;
                }
                i16++;
                c1208pArr = c1208pArr2;
                length2 = i13;
            }
            if (z10) {
                AbstractC1346c.z("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + iMax + "x" + iMax2);
                boolean z11 = i15 > i14;
                int i19 = z11 ? i15 : i14;
                if (z11) {
                    i11 = i14;
                    c1200h = c1200h2;
                } else {
                    c1200h = c1200h2;
                    i11 = i15;
                }
                float f13 = i11 / i19;
                int[] iArr = f24274x1;
                i7 = i14;
                i10 = i15;
                int i20 = 0;
                while (i20 < 9) {
                    int i21 = iArr[i20];
                    int[] iArr2 = iArr;
                    int i22 = (int) (i21 * f13);
                    if (i21 <= i19 || i22 <= i11) {
                        break;
                    }
                    if (!z11) {
                        i22 = i21;
                    }
                    if (!z11) {
                        i21 = i22;
                    }
                    boolean z12 = z11;
                    MediaCodecInfo.CodecCapabilities codecCapabilities = nVar.d;
                    if (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) {
                        i12 = i19;
                        f11 = f13;
                        point2 = null;
                    } else {
                        i12 = i19;
                        int widthAlignment = videoCapabilities.getWidthAlignment();
                        int heightAlignment = videoCapabilities.getHeightAlignment();
                        f11 = f13;
                        point2 = new Point(AbstractC1343A.f(i22, widthAlignment) * widthAlignment, AbstractC1343A.f(i21, heightAlignment) * heightAlignment);
                    }
                    if (point2 != null) {
                        point = point2;
                        if (nVar.f(point2.x, point2.y, f12)) {
                            break;
                        }
                    }
                    i20++;
                    iArr = iArr2;
                    z11 = z12;
                    i19 = i12;
                    f13 = f11;
                }
                point = null;
                if (point != null) {
                    iMax = Math.max(iMax, point.x);
                    iMax2 = Math.max(iMax2, point.y);
                    C1207o c1207oA2 = c1208p.a();
                    c1207oA2.f14252t = iMax;
                    c1207oA2.f14253u = iMax2;
                    iC0 = Math.max(iC0, A0(nVar, new C1208p(c1207oA2)));
                    AbstractC1346c.z("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + iMax + "x" + iMax2);
                }
            } else {
                i7 = i14;
                c1200h = c1200h2;
                i10 = i15;
            }
            c0064d = new C0064d(iMax, iMax2, iC0, 5);
        }
        this.f24284U0 = c0064d;
        int i23 = this.f24305q1 ? this.f24306r1 : 0;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", i7);
        mediaFormat.setInteger("height", i10);
        AbstractC1346c.x(mediaFormat, c1208p.f14318q);
        if (f12 != -1.0f) {
            mediaFormat.setFloat("frame-rate", f12);
        }
        AbstractC1346c.w(mediaFormat, "rotation-degrees", c1208p.f14325x);
        if (c1200h != null) {
            C1200h c1200h3 = c1200h;
            AbstractC1346c.w(mediaFormat, "color-transfer", c1200h3.c);
            AbstractC1346c.w(mediaFormat, "color-standard", c1200h3.f14113a);
            AbstractC1346c.w(mediaFormat, "color-range", c1200h3.f14114b);
            byte[] bArr = c1200h3.d;
            if (bArr != null) {
                mediaFormat.setByteBuffer("hdr-static-info", ByteBuffer.wrap(bArr));
            }
        }
        if ("video/dolby-vision".equals(c1208p.f14316n) && (pairD = s2.v.d(c1208p)) != null) {
            AbstractC1346c.w(mediaFormat, "profile", ((Integer) pairD.first).intValue());
        }
        mediaFormat.setInteger("max-width", c0064d.f556b);
        mediaFormat.setInteger("max-height", c0064d.c);
        AbstractC1346c.w(mediaFormat, "max-input-size", c0064d.d);
        int i24 = AbstractC1343A.f15195a;
        if (i24 >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f10 != -1.0f) {
                mediaFormat.setFloat("operating-rate", f10);
            }
        }
        if (this.f24281R0) {
            z6 = true;
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        } else {
            z6 = true;
        }
        if (i23 != 0) {
            mediaFormat.setFeatureEnabled("tunneled-playback", z6);
            mediaFormat.setInteger("audio-session-id", i23);
        }
        if (i24 >= 35) {
            mediaFormat.setInteger("importance", Math.max(0, -this.f24304p1));
        }
        Surface surfaceD0 = D0(nVar);
        if (this.f24287X0 != null && !AbstractC1343A.E(this.f24277N0)) {
            mediaFormat.setInteger("allow-frame-drop", 0);
        }
        return new H0.b(nVar, mediaFormat, c1208p, surfaceD0, mediaCrypto, (y) null);
    }

    @Override // s2.q
    public final void U(m2.g gVar) {
        if (this.f24286W0) {
            ByteBuffer byteBuffer = gVar.f18958s;
            byteBuffer.getClass();
            if (byteBuffer.remaining() >= 7) {
                byte b10 = byteBuffer.get();
                short s6 = byteBuffer.getShort();
                short s10 = byteBuffer.getShort();
                byte b11 = byteBuffer.get();
                byte b12 = byteBuffer.get();
                byteBuffer.position(0);
                if (b10 == -75 && s6 == 60 && s10 == 1 && b11 == 4) {
                    if (b12 == 0 || b12 == 1) {
                        byte[] bArr = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bArr);
                        byteBuffer.position(0);
                        s2.k kVar = this.f22137V;
                        kVar.getClass();
                        Bundle bundle = new Bundle();
                        bundle.putByteArray("hdr10-plus-info", bArr);
                        kVar.d(bundle);
                    }
                }
            }
        }
    }

    @Override // s2.q
    public final void Z(Exception exc) {
        AbstractC1346c.q("MediaCodecVideoRenderer", "Video codec error", exc);
        m2.b bVar = this.f24279P0;
        Handler handler = (Handler) bVar.l;
        if (handler != null) {
            handler.post(new t(bVar, exc, 3));
        }
    }

    @Override // s2.q
    public final void a0(long j3, long j10, String str) {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr;
        m2.b bVar = this.f24279P0;
        Handler handler = (Handler) bVar.l;
        if (handler != null) {
            handler.post(new t(bVar, str, j3, j10));
        }
        this.f24285V0 = z0(str);
        s2.n nVar = this.f22144c0;
        nVar.getClass();
        boolean z6 = false;
        if (AbstractC1343A.f15195a >= 29 && "video/x-vnd.on2.vp9".equals(nVar.f22096b)) {
            MediaCodecInfo.CodecCapabilities codecCapabilities = nVar.d;
            if (codecCapabilities == null || (codecProfileLevelArr = codecCapabilities.profileLevels) == null) {
                codecProfileLevelArr = new MediaCodecInfo.CodecProfileLevel[0];
            }
            int length = codecProfileLevelArr.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    break;
                }
                if (codecProfileLevelArr[i7].profile == 16384) {
                    z6 = true;
                    break;
                }
                i7++;
            }
        }
        this.f24286W0 = z6;
        F0();
    }

    @Override // n2.AbstractC1905e, n2.W
    public final void b(int i7, Object obj) {
        Handler handler;
        o oVar = this.f24282S0;
        if (i7 == 1) {
            Surface surface = obj instanceof Surface ? (Surface) obj : null;
            Surface surface2 = this.f24290a1;
            m2.b bVar = this.f24279P0;
            if (surface2 == surface) {
                if (surface != null) {
                    q0 q0Var = this.f24303o1;
                    if (q0Var != null) {
                        bVar.y0(q0Var);
                    }
                    Surface surface3 = this.f24290a1;
                    if (surface3 == null || !this.f24292d1 || (handler = (Handler) bVar.l) == null) {
                        return;
                    }
                    handler.post(new RunnableC1447x(bVar, surface3, SystemClock.elapsedRealtime(), 2));
                    return;
                }
                return;
            }
            this.f24290a1 = surface;
            if (this.f24287X0 == null) {
                r rVar = oVar.f24342b;
                if (rVar.f24357e != surface) {
                    rVar.b();
                    rVar.f24357e = surface;
                    rVar.d(true);
                }
                oVar.d(1);
            }
            this.f24292d1 = false;
            int i10 = this.f19641s;
            s2.k kVar = this.f22137V;
            if (kVar != null && this.f24287X0 == null) {
                s2.n nVar = this.f22144c0;
                nVar.getClass();
                Surface surface4 = this.f24290a1;
                boolean z6 = (surface4 != null && surface4.isValid()) || (AbstractC1343A.f15195a >= 35 && nVar.f22100h) || H0(nVar);
                int i11 = AbstractC1343A.f15195a;
                if (i11 < 23 || !z6 || this.f24285V0) {
                    m0();
                    X();
                } else {
                    Surface surfaceD0 = D0(nVar);
                    if (i11 >= 23 && surfaceD0 != null) {
                        kVar.m(surfaceD0);
                    } else {
                        if (i11 < 35) {
                            throw new IllegalStateException();
                        }
                        kVar.k();
                    }
                }
            }
            if (surface != null) {
                q0 q0Var2 = this.f24303o1;
                if (q0Var2 != null) {
                    bVar.y0(q0Var2);
                }
                if (i10 == 2) {
                    i iVar = this.f24287X0;
                    if (iVar != null) {
                        iVar.d(true);
                    } else {
                        oVar.c(true);
                    }
                }
            } else {
                this.f24303o1 = null;
                i iVar2 = this.f24287X0;
                if (iVar2 != null) {
                    l lVar = iVar2.f24328n;
                    lVar.getClass();
                    int i12 = h2.u.c.f15243a;
                    lVar.l = null;
                }
            }
            F0();
            return;
        }
        if (i7 == 7) {
            obj.getClass();
            n nVar2 = (n) obj;
            this.f24308t1 = nVar2;
            i iVar3 = this.f24287X0;
            if (iVar3 != null) {
                iVar3.f24328n.f24337j = nVar2;
                return;
            }
            return;
        }
        if (i7 == 10) {
            obj.getClass();
            int iIntValue = ((Integer) obj).intValue();
            if (this.f24306r1 != iIntValue) {
                this.f24306r1 = iIntValue;
                if (this.f24305q1) {
                    m0();
                    return;
                }
                return;
            }
            return;
        }
        if (i7 == 16) {
            obj.getClass();
            this.f24304p1 = ((Integer) obj).intValue();
            s2.k kVar2 = this.f22137V;
            if (kVar2 != null && AbstractC1343A.f15195a >= 35) {
                Bundle bundle = new Bundle();
                bundle.putInt("importance", Math.max(0, -this.f24304p1));
                kVar2.d(bundle);
                return;
            }
            return;
        }
        if (i7 == 4) {
            obj.getClass();
            int iIntValue2 = ((Integer) obj).intValue();
            this.f24293e1 = iIntValue2;
            s2.k kVar3 = this.f22137V;
            if (kVar3 != null) {
                kVar3.i(iIntValue2);
                return;
            }
            return;
        }
        if (i7 == 5) {
            obj.getClass();
            int iIntValue3 = ((Integer) obj).intValue();
            this.f24294f1 = iIntValue3;
            i iVar4 = this.f24287X0;
            if (iVar4 != null) {
                iVar4.g(iIntValue3);
                return;
            }
            r rVar2 = oVar.f24342b;
            if (rVar2.f24362j == iIntValue3) {
                return;
            }
            rVar2.f24362j = iIntValue3;
            rVar2.d(true);
            return;
        }
        if (i7 == 13) {
            obj.getClass();
            List list = (List) obj;
            this.f24289Z0 = list;
            i iVar5 = this.f24287X0;
            if (iVar5 != null) {
                iVar5.k(list);
                return;
            }
            return;
        }
        if (i7 != 14) {
            if (i7 == 11) {
                this.f22132Q = (C1924y) obj;
                return;
            }
            return;
        }
        obj.getClass();
        h2.u uVar = (h2.u) obj;
        if (uVar.f15243a == 0 || uVar.f15244b == 0) {
            return;
        }
        this.c1 = uVar;
        i iVar6 = this.f24287X0;
        if (iVar6 != null) {
            Surface surface5 = this.f24290a1;
            AbstractC1346c.k(surface5);
            iVar6.h(surface5, uVar);
        }
    }

    @Override // s2.q
    public final void b0(String str) {
        m2.b bVar = this.f24279P0;
        Handler handler = (Handler) bVar.l;
        if (handler != null) {
            handler.post(new t(bVar, str, 6));
        }
    }

    @Override // s2.q
    public final C1907g c0(C1778f c1778f) {
        C1907g c1907gC0 = super.c0(c1778f);
        C1208p c1208p = (C1208p) c1778f.f18639n;
        c1208p.getClass();
        m2.b bVar = this.f24279P0;
        Handler handler = (Handler) bVar.l;
        if (handler != null) {
            handler.post(new t(bVar, c1208p, c1907gC0));
        }
        return c1907gC0;
    }

    @Override // s2.q
    public final void d0(C1208p c1208p, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        s2.k kVar = this.f22137V;
        if (kVar != null) {
            kVar.i(this.f24293e1);
        }
        if (this.f24305q1) {
            integer = c1208p.f14322u;
            integer2 = c1208p.f14323v;
        } else {
            mediaFormat.getClass();
            boolean z6 = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
            integer = z6 ? (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1 : mediaFormat.getInteger("width");
            integer2 = z6 ? (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1 : mediaFormat.getInteger("height");
        }
        float integer3 = c1208p.f14326y;
        if (AbstractC1343A.f15195a >= 30 && mediaFormat != null && mediaFormat.containsKey("sar-width") && mediaFormat.containsKey("sar-height")) {
            integer3 = mediaFormat.getInteger("sar-width") / mediaFormat.getInteger("sar-height");
        }
        int i7 = c1208p.f14325x;
        if (i7 == 90 || i7 == 270) {
            integer3 = 1.0f / integer3;
            int i10 = integer2;
            integer2 = integer;
            integer = i10;
        }
        this.f24302n1 = new q0(integer, integer2, integer3);
        i iVar = this.f24287X0;
        if (iVar == null || !this.w1) {
            this.f24282S0.g(c1208p.f14324w);
        } else {
            C1207o c1207oA = c1208p.a();
            c1207oA.f14252t = integer;
            c1207oA.f14253u = integer2;
            c1207oA.f14256x = integer3;
            C1208p c1208p2 = new C1208p(c1207oA);
            AbstractC1346c.j(false);
            iVar.f24328n.f24331b.g(c1208p2.f14324w);
            iVar.c = c1208p2;
            if (iVar.f24324i) {
                AbstractC1346c.j(iVar.f24323h != -9223372036854775807L);
                iVar.f24325j = true;
                iVar.f24326k = iVar.f24323h;
            } else {
                iVar.e();
                iVar.f24324i = true;
                iVar.f24325j = false;
                iVar.f24326k = -9223372036854775807L;
            }
        }
        this.w1 = false;
    }

    @Override // s2.q
    public final void f0(long j3) {
        super.f0(j3);
        if (this.f24305q1) {
            return;
        }
        this.f24298j1--;
    }

    @Override // n2.AbstractC1905e
    public final void g() {
        i iVar = this.f24287X0;
        if (iVar != null) {
            o oVar = (o) iVar.f24328n.f24333f.l;
            if (oVar.d == 0) {
                oVar.d = 1;
                return;
            }
            return;
        }
        o oVar2 = this.f24282S0;
        if (oVar2.d == 0) {
            oVar2.d = 1;
        }
    }

    @Override // s2.q
    public final void g0() {
        i iVar = this.f24287X0;
        if (iVar != null) {
            s2.p pVar = this.f22123J0;
            iVar.j(pVar.f22106b, pVar.c, -this.f24309u1, this.f19645w);
        } else {
            this.f24282S0.d(2);
        }
        this.w1 = true;
        F0();
    }

    @Override // s2.q
    public final void h0(m2.g gVar) {
        Surface surface;
        boolean z6 = this.f24305q1;
        if (!z6) {
            this.f24298j1++;
        }
        if (AbstractC1343A.f15195a >= 23 || !z6) {
            return;
        }
        long j3 = gVar.f18957r;
        y0(j3);
        q0 q0Var = this.f24302n1;
        boolean zEquals = q0Var.equals(q0.d);
        m2.b bVar = this.f24279P0;
        if (!zEquals && !q0Var.equals(this.f24303o1)) {
            this.f24303o1 = q0Var;
            bVar.y0(q0Var);
        }
        this.f22121I0.f19651e++;
        o oVar = this.f24282S0;
        boolean z10 = oVar.d != 3;
        oVar.d = 3;
        oVar.f24349k.getClass();
        oVar.f24344f = AbstractC1343A.H(SystemClock.elapsedRealtime());
        if (z10 && (surface = this.f24290a1) != null) {
            Handler handler = (Handler) bVar.l;
            if (handler != null) {
                handler.post(new RunnableC1447x(bVar, surface, SystemClock.elapsedRealtime(), 2));
            }
            this.f24292d1 = true;
        }
        f0(j3);
    }

    @Override // s2.q
    public final void i0(C1208p c1208p) throws C1913m {
        i iVar = this.f24287X0;
        if (iVar == null) {
            return;
        }
        try {
            iVar.c(c1208p);
            throw null;
        } catch (v e10) {
            throw e(e10, c1208p, false, 7000);
        }
    }

    @Override // s2.q
    public final boolean k0(long j3, long j10, s2.k kVar, ByteBuffer byteBuffer, int i7, int i10, int i11, long j11, boolean z6, boolean z10, C1208p c1208p) throws C1913m {
        long j12;
        long j13;
        boolean z11;
        kVar.getClass();
        s2.p pVar = this.f22123J0;
        long j14 = j11 - pVar.c;
        i iVar = this.f24287X0;
        if (iVar != null) {
            try {
                z11 = false;
            } catch (v e10) {
                e = e10;
                z11 = false;
            }
            try {
                return iVar.b(j11 + (-this.f24309u1), z10, j3, j10, new B5.u(this, kVar, i7, j14));
            } catch (v e11) {
                e = e11;
                throw e(e, e.l, z11, 7001);
            }
        }
        int iA = this.f24282S0.a(j11, j3, j10, pVar.f22106b, z10, this.f24283T0);
        if (iA == 4) {
            return false;
        }
        if (z6 && !z10) {
            I0(kVar, i7);
            return true;
        }
        Surface surface = this.f24290a1;
        s9.d dVar = this.f24283T0;
        if (surface == null) {
            if (dVar.f22385a >= 30000) {
                return false;
            }
            I0(kVar, i7);
            K0(dVar.f22385a);
            return true;
        }
        if (iA == 0) {
            this.f19640r.getClass();
            long jNanoTime = System.nanoTime();
            n nVar = this.f24308t1;
            if (nVar != null) {
                nVar.d(j14, jNanoTime, c1208p, this.f22139X);
            }
            G0(kVar, i7, jNanoTime);
            K0(dVar.f22385a);
            return true;
        }
        if (iA != 1) {
            if (iA == 2) {
                Trace.beginSection("dropVideoBuffer");
                kVar.g(i7);
                Trace.endSection();
                J0(0, 1);
                K0(dVar.f22385a);
                return true;
            }
            if (iA != 3) {
                if (iA == 5) {
                    return false;
                }
                throw new IllegalStateException(String.valueOf(iA));
            }
            I0(kVar, i7);
            K0(dVar.f22385a);
            return true;
        }
        long j15 = dVar.f22386b;
        long j16 = dVar.f22385a;
        if (j15 == this.f24301m1) {
            I0(kVar, i7);
            j13 = j16;
        } else {
            n nVar2 = this.f24308t1;
            if (nVar2 != null) {
                j12 = j16;
                nVar2.d(j14, j15, c1208p, this.f22139X);
            } else {
                j12 = j16;
            }
            G0(kVar, i7, j15);
            j13 = j12;
        }
        K0(j13);
        this.f24301m1 = j15;
        return true;
    }

    @Override // n2.AbstractC1905e
    public final String l() {
        return "MediaCodecVideoRenderer";
    }

    @Override // n2.AbstractC1905e
    public final boolean n() {
        return this.f22114E0 && this.f24287X0 == null;
    }

    @Override // s2.q
    public final void o0() {
        super.o0();
        this.f24298j1 = 0;
    }

    @Override // s2.q, n2.AbstractC1905e
    public final boolean p() {
        boolean zP = super.p();
        i iVar = this.f24287X0;
        if (iVar != null) {
            return ((o) iVar.f24328n.f24333f.l).b(false);
        }
        if (zP && (this.f22137V == null || this.f24290a1 == null || this.f24305q1)) {
            return true;
        }
        return this.f24282S0.b(zP);
    }

    @Override // s2.q, n2.AbstractC1905e
    public final void q() {
        m2.b bVar = this.f24279P0;
        this.f24303o1 = null;
        this.f24310v1 = -9223372036854775807L;
        i iVar = this.f24287X0;
        if (iVar != null) {
            ((o) iVar.f24328n.f24333f.l).d(0);
        } else {
            this.f24282S0.d(0);
        }
        F0();
        this.f24292d1 = false;
        this.f24307s1 = null;
        try {
            super.q();
            C1906f c1906f = this.f22121I0;
            bVar.getClass();
            synchronized (c1906f) {
            }
            Handler handler = (Handler) bVar.l;
            if (handler != null) {
                handler.post(new RunnableC1918s(bVar, 9, c1906f));
            }
            bVar.y0(q0.d);
        } catch (Throwable th) {
            C1906f c1906f2 = this.f22121I0;
            bVar.getClass();
            synchronized (c1906f2) {
                Handler handler2 = (Handler) bVar.l;
                if (handler2 != null) {
                    handler2.post(new RunnableC1918s(bVar, 9, c1906f2));
                }
                bVar.y0(q0.d);
                throw th;
            }
        }
    }

    @Override // n2.AbstractC1905e
    public final void r(boolean z6, boolean z10) {
        this.f22121I0 = new C1906f();
        a0 a0Var = this.o;
        a0Var.getClass();
        boolean z11 = a0Var.f19623b;
        AbstractC1346c.j((z11 && this.f24306r1 == 0) ? false : true);
        if (this.f24305q1 != z11) {
            this.f24305q1 = z11;
            m0();
        }
        C1906f c1906f = this.f22121I0;
        m2.b bVar = this.f24279P0;
        Handler handler = (Handler) bVar.l;
        if (handler != null) {
            handler.post(new t(bVar, c1906f, 4));
        }
        boolean z12 = this.f24288Y0;
        o oVar = this.f24282S0;
        if (!z12) {
            if (this.f24289Z0 != null && this.f24287X0 == null) {
                p2.q qVar = new p2.q(this.f24277N0, oVar);
                h2.v vVar = this.f19640r;
                vVar.getClass();
                qVar.f20762g = vVar;
                AbstractC1346c.j(!qVar.f20759b);
                if (((k) qVar.f20760e) == null) {
                    if (((j) qVar.d) == null) {
                        qVar.d = new j();
                    }
                    qVar.f20760e = new k((j) qVar.d);
                }
                l lVar = new l(qVar);
                qVar.f20759b = true;
                this.f24287X0 = lVar.f24330a;
            }
            this.f24288Y0 = true;
        }
        i iVar = this.f24287X0;
        if (iVar == null) {
            h2.v vVar2 = this.f19640r;
            vVar2.getClass();
            oVar.f24349k = vVar2;
            oVar.d = z10 ? 1 : 0;
            return;
        }
        C2607b c2607b = new C2607b(this, 8);
        h5.r rVar = h5.r.l;
        iVar.l = c2607b;
        iVar.f24327m = rVar;
        n nVar = this.f24308t1;
        if (nVar != null) {
            iVar.f24328n.f24337j = nVar;
        }
        if (this.f24290a1 != null && !this.c1.equals(h2.u.c)) {
            this.f24287X0.h(this.f24290a1, this.c1);
        }
        this.f24287X0.g(this.f24294f1);
        this.f24287X0.i(this.f22135T);
        List list = this.f24289Z0;
        if (list != null) {
            this.f24287X0.k(list);
        }
        ((o) this.f24287X0.f24328n.f24333f.l).d = z10 ? 1 : 0;
    }

    @Override // s2.q, n2.AbstractC1905e
    public final void s(long j3, boolean z6) {
        i iVar = this.f24287X0;
        if (iVar != null) {
            iVar.a(true);
            i iVar2 = this.f24287X0;
            s2.p pVar = this.f22123J0;
            iVar2.j(pVar.f22106b, pVar.c, -this.f24309u1, this.f19645w);
            this.w1 = true;
        }
        super.s(j3, z6);
        i iVar3 = this.f24287X0;
        o oVar = this.f24282S0;
        if (iVar3 == null) {
            r rVar = oVar.f24342b;
            rVar.f24364m = 0L;
            rVar.f24366p = -1L;
            rVar.f24365n = -1L;
            oVar.f24345g = -9223372036854775807L;
            oVar.f24343e = -9223372036854775807L;
            oVar.d(1);
            oVar.f24346h = -9223372036854775807L;
        }
        if (z6) {
            i iVar4 = this.f24287X0;
            if (iVar4 != null) {
                iVar4.d(false);
            } else {
                oVar.c(false);
            }
        }
        F0();
        this.f24297i1 = 0;
    }

    @Override // s2.q
    public final boolean s0(s2.n nVar) {
        Surface surface = this.f24290a1;
        return (surface != null && surface.isValid()) || (AbstractC1343A.f15195a >= 35 && nVar.f22100h) || H0(nVar);
    }

    @Override // n2.AbstractC1905e
    public final void t() {
        i iVar = this.f24287X0;
        if (iVar == null || !this.f24278O0) {
            return;
        }
        l lVar = iVar.f24328n;
        if (lVar.f24340n == 2) {
            return;
        }
        x xVar = lVar.f24338k;
        if (xVar != null) {
            xVar.f15248a.removeCallbacksAndMessages(null);
        }
        lVar.l = null;
        lVar.f24340n = 2;
    }

    @Override // s2.q
    public final boolean t0(m2.g gVar) {
        if (!gVar.e(67108864) || m() || gVar.e(536870912)) {
            return false;
        }
        long j3 = this.f24310v1;
        return j3 != -9223372036854775807L && j3 - (gVar.f18957r - this.f22123J0.c) > 100000 && !gVar.e(TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) && gVar.f18957r < this.f19645w;
    }

    @Override // n2.AbstractC1905e
    public final void u() {
        try {
            try {
                I();
                m0();
                q2.h hVar = this.f22131P;
                if (hVar != null) {
                    hVar.d(null);
                }
                this.f22131P = null;
            } catch (Throwable th) {
                q2.h hVar2 = this.f22131P;
                if (hVar2 != null) {
                    hVar2.d(null);
                }
                this.f22131P = null;
                throw th;
            }
        } finally {
            this.f24288Y0 = false;
            this.f24309u1 = -9223372036854775807L;
            C2761g c2761g = this.f24291b1;
            if (c2761g != null) {
                c2761g.release();
                this.f24291b1 = null;
            }
        }
    }

    @Override // n2.AbstractC1905e
    public final void v() {
        this.f24296h1 = 0;
        this.f19640r.getClass();
        this.f24295g1 = SystemClock.elapsedRealtime();
        this.f24299k1 = 0L;
        this.f24300l1 = 0;
        i iVar = this.f24287X0;
        if (iVar != null) {
            ((o) iVar.f24328n.f24333f.l).e();
        } else {
            this.f24282S0.e();
        }
    }

    @Override // s2.q
    public final int v0(s2.i iVar, C1208p c1208p) {
        boolean z6;
        int i7 = 4;
        int i10 = 1;
        int i11 = 0;
        if (!L.k(c1208p.f14316n)) {
            return AbstractC1905e.d(0, 0, 0, 0);
        }
        boolean z10 = c1208p.f14319r != null;
        Context context = this.f24277N0;
        List listB0 = B0(context, iVar, c1208p, z10, false);
        if (z10 && listB0.isEmpty()) {
            listB0 = B0(context, iVar, c1208p, false, false);
        }
        if (listB0.isEmpty()) {
            return AbstractC1905e.d(1, 0, 0, 0);
        }
        int i12 = c1208p.f14304L;
        if (i12 != 0 && i12 != 2) {
            return AbstractC1905e.d(2, 0, 0, 0);
        }
        s2.n nVar = (s2.n) listB0.get(0);
        boolean zD = nVar.d(c1208p);
        if (zD) {
            z6 = true;
        } else {
            for (int i13 = 1; i13 < listB0.size(); i13++) {
                s2.n nVar2 = (s2.n) listB0.get(i13);
                if (nVar2.d(c1208p)) {
                    zD = true;
                    z6 = false;
                    nVar = nVar2;
                    break;
                }
            }
            z6 = true;
        }
        int i14 = zD ? 4 : 3;
        int i15 = nVar.e(c1208p) ? 16 : 8;
        int i16 = nVar.f22099g ? 64 : 0;
        int i17 = z6 ? 128 : 0;
        if (AbstractC1343A.f15195a >= 26 && "video/dolby-vision".equals(c1208p.f14316n) && !h6.r.d(context)) {
            i17 = 256;
        }
        if (zD) {
            List listB02 = B0(context, iVar, c1208p, z10, true);
            if (!listB02.isEmpty()) {
                HashMap map = s2.v.f22170a;
                ArrayList arrayList = new ArrayList(listB02);
                Collections.sort(arrayList, new C2111a(new C2026c(c1208p, i7), i10));
                s2.n nVar3 = (s2.n) arrayList.get(0);
                if (nVar3.d(c1208p) && nVar3.e(c1208p)) {
                    i11 = 32;
                }
            }
        }
        return i14 | i15 | i11 | i16 | i17;
    }

    @Override // n2.AbstractC1905e
    public final void w() {
        E0();
        int i7 = this.f24300l1;
        if (i7 != 0) {
            long j3 = this.f24299k1;
            m2.b bVar = this.f24279P0;
            Handler handler = (Handler) bVar.l;
            if (handler != null) {
                handler.post(new t(bVar, j3, i7));
            }
            this.f24299k1 = 0L;
            this.f24300l1 = 0;
        }
        i iVar = this.f24287X0;
        if (iVar != null) {
            ((o) iVar.f24328n.f24333f.l).f();
        } else {
            this.f24282S0.f();
        }
    }

    @Override // s2.q, n2.AbstractC1905e
    public final void x(C1208p[] c1208pArr, long j3, long j10, C2492C c2492c) {
        super.x(c1208pArr, j3, j10, c2492c);
        if (this.f24309u1 == -9223372036854775807L) {
            this.f24309u1 = j3;
        }
        f0 f0Var = this.f19635A;
        if (f0Var.p()) {
            this.f24310v1 = -9223372036854775807L;
            return;
        }
        c2492c.getClass();
        this.f24310v1 = f0Var.g(c2492c.f22663a, new c0()).d;
    }

    @Override // s2.q, n2.AbstractC1905e
    public final void z(long j3, long j10) throws C1913m {
        super.z(j3, j10);
        i iVar = this.f24287X0;
        if (iVar != null) {
            try {
                iVar.f(j3, j10);
            } catch (v e10) {
                throw e(e10, e10.l, false, 7001);
            }
        }
    }
}
