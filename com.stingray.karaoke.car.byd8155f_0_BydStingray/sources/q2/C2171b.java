package q2;

import B5.H;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Pair;
import e2.AbstractC1199g;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import l3.HandlerC1768y;
import m2.InterfaceC1794a;
import u2.C2516t;

/* JADX INFO: renamed from: q2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2171b implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f21163a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f21164b;
    public final m2.b c;
    public final C2172c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f21165e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f21166f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f21167g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h2.h f21168h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l9.n f21169i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final o2.l f21170j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final A2.c f21171k;
    public final UUID l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Looper f21172m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final H f21173n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21174p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public HandlerThread f21175q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public HandlerC1768y f21176r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public InterfaceC1794a f21177s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public g f21178t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public byte[] f21179u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public byte[] f21180v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public q f21181w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public r f21182x;

    public C2171b(UUID uuid, s sVar, m2.b bVar, C2172c c2172c, List list, boolean z6, boolean z10, byte[] bArr, HashMap map, A2.c cVar, Looper looper, l9.n nVar, o2.l lVar) {
        this.l = uuid;
        this.c = bVar;
        this.d = c2172c;
        this.f21164b = sVar;
        this.f21165e = z6;
        this.f21166f = z10;
        if (bArr != null) {
            this.f21180v = bArr;
            this.f21163a = null;
        } else {
            list.getClass();
            this.f21163a = Collections.unmodifiableList(list);
        }
        this.f21167g = map;
        this.f21171k = cVar;
        this.f21168h = new h2.h();
        this.f21169i = nVar;
        this.f21170j = lVar;
        this.o = 2;
        this.f21172m = looper;
        this.f21173n = new H(this, looper, 18);
    }

    @Override // q2.h
    public final boolean a() {
        n();
        return this.f21165e;
    }

    @Override // q2.h
    public final UUID b() {
        n();
        return this.l;
    }

    @Override // q2.h
    public final void c(k kVar) {
        n();
        if (this.f21174p < 0) {
            AbstractC1346c.p("DefaultDrmSession", "Session reference count less than zero: " + this.f21174p);
            this.f21174p = 0;
        }
        if (kVar != null) {
            h2.h hVar = this.f21168h;
            synchronized (hVar.l) {
                try {
                    ArrayList arrayList = new ArrayList(hVar.o);
                    arrayList.add(kVar);
                    hVar.o = Collections.unmodifiableList(arrayList);
                    Integer num = (Integer) hVar.f15212m.get(kVar);
                    if (num == null) {
                        HashSet hashSet = new HashSet(hVar.f15213n);
                        hashSet.add(kVar);
                        hVar.f15213n = Collections.unmodifiableSet(hashSet);
                    }
                    hVar.f15212m.put(kVar, Integer.valueOf(num != null ? num.intValue() + 1 : 1));
                } finally {
                }
            }
        }
        int i7 = this.f21174p + 1;
        this.f21174p = i7;
        if (i7 == 1) {
            AbstractC1346c.j(this.o == 2);
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DrmRequestHandler");
            this.f21175q = handlerThread;
            handlerThread.start();
            this.f21176r = new HandlerC1768y(this, this.f21175q.getLooper());
            if (l()) {
                h(true);
            }
        } else if (kVar != null && i() && this.f21168h.a(kVar) == 1) {
            kVar.c(this.o);
        }
        f fVar = this.d.f21183a;
        if (fVar.l != -9223372036854775807L) {
            fVar.o.remove(this);
            Handler handler = fVar.f21201u;
            handler.getClass();
            handler.removeCallbacksAndMessages(this);
        }
    }

    @Override // q2.h
    public final void d(k kVar) {
        n();
        int i7 = this.f21174p;
        if (i7 <= 0) {
            AbstractC1346c.p("DefaultDrmSession", "release() called on a session that's already fully released.");
            return;
        }
        int i10 = i7 - 1;
        this.f21174p = i10;
        if (i10 == 0) {
            this.o = 0;
            H h10 = this.f21173n;
            int i11 = AbstractC1343A.f15195a;
            h10.removeCallbacksAndMessages(null);
            HandlerC1768y handlerC1768y = this.f21176r;
            synchronized (handlerC1768y) {
                handlerC1768y.removeCallbacksAndMessages(null);
                handlerC1768y.f18619b = true;
            }
            this.f21176r = null;
            this.f21175q.quit();
            this.f21175q = null;
            this.f21177s = null;
            this.f21178t = null;
            this.f21181w = null;
            this.f21182x = null;
            byte[] bArr = this.f21179u;
            if (bArr != null) {
                this.f21164b.e(bArr);
                this.f21179u = null;
            }
        }
        if (kVar != null) {
            h2.h hVar = this.f21168h;
            synchronized (hVar.l) {
                try {
                    Integer num = (Integer) hVar.f15212m.get(kVar);
                    if (num != null) {
                        ArrayList arrayList = new ArrayList(hVar.o);
                        arrayList.remove(kVar);
                        hVar.o = Collections.unmodifiableList(arrayList);
                        if (num.intValue() == 1) {
                            hVar.f15212m.remove(kVar);
                            HashSet hashSet = new HashSet(hVar.f15213n);
                            hashSet.remove(kVar);
                            hVar.f15213n = Collections.unmodifiableSet(hashSet);
                        } else {
                            hVar.f15212m.put(kVar, Integer.valueOf(num.intValue() - 1));
                        }
                    }
                } finally {
                }
            }
            if (this.f21168h.a(kVar) == 0) {
                kVar.e();
            }
        }
        C2172c c2172c = this.d;
        int i12 = this.f21174p;
        f fVar = c2172c.f21183a;
        if (i12 == 1 && fVar.f21196p > 0 && fVar.l != -9223372036854775807L) {
            fVar.o.add(this);
            Handler handler = fVar.f21201u;
            handler.getClass();
            handler.postAtTime(new p2.p(this, 2), this, SystemClock.uptimeMillis() + fVar.l);
        } else if (i12 == 0) {
            fVar.f21194m.remove(this);
            if (fVar.f21198r == this) {
                fVar.f21198r = null;
            }
            if (fVar.f21199s == this) {
                fVar.f21199s = null;
            }
            m2.b bVar = fVar.f21191i;
            HashSet hashSet2 = (HashSet) bVar.l;
            hashSet2.remove(this);
            if (((C2171b) bVar.f18945m) == this) {
                bVar.f18945m = null;
                if (!hashSet2.isEmpty()) {
                    C2171b c2171b = (C2171b) hashSet2.iterator().next();
                    bVar.f18945m = c2171b;
                    r rVarH = c2171b.f21164b.h();
                    c2171b.f21182x = rVarH;
                    HandlerC1768y handlerC1768y2 = c2171b.f21176r;
                    int i13 = AbstractC1343A.f15195a;
                    rVarH.getClass();
                    handlerC1768y2.getClass();
                    handlerC1768y2.obtainMessage(1, new C2170a(C2516t.f22900a.getAndIncrement(), true, SystemClock.elapsedRealtime(), rVarH)).sendToTarget();
                }
            }
            if (fVar.l != -9223372036854775807L) {
                Handler handler2 = fVar.f21201u;
                handler2.getClass();
                handler2.removeCallbacksAndMessages(this);
                fVar.o.remove(this);
            }
        }
        fVar.l();
    }

    @Override // q2.h
    public final boolean e(String str) {
        n();
        byte[] bArr = this.f21179u;
        AbstractC1346c.k(bArr);
        return this.f21164b.x(str, bArr);
    }

    @Override // q2.h
    public final g f() {
        n();
        if (this.o == 1) {
            return this.f21178t;
        }
        return null;
    }

    @Override // q2.h
    public final InterfaceC1794a g() {
        n();
        return this.f21177s;
    }

    @Override // q2.h
    public final int getState() {
        n();
        return this.o;
    }

    public final void h(boolean z6) {
        long jMin;
        String str;
        Set set;
        boolean z10 = true;
        if (this.f21166f) {
            return;
        }
        byte[] bArr = this.f21179u;
        int i7 = AbstractC1343A.f15195a;
        if (this.f21180v == null) {
            m(1, z6, bArr);
            return;
        }
        if (this.o != 4) {
            try {
                this.f21164b.b(this.f21179u, this.f21180v);
            } catch (Exception | NoSuchMethodError e10) {
                j(e10, 1);
                z10 = false;
            }
            if (!z10) {
                return;
            }
        }
        if (AbstractC1199g.d.equals(this.l)) {
            n();
            byte[] bArr2 = this.f21179u;
            Pair pair = null;
            Map mapC = bArr2 == null ? null : this.f21164b.c(bArr2);
            if (mapC != null) {
                long j3 = -9223372036854775807L;
                try {
                    str = (String) mapC.get("LicenseDurationRemaining");
                } catch (NumberFormatException unused) {
                }
                long j10 = str != null ? Long.parseLong(str) : -9223372036854775807L;
                Long lValueOf = Long.valueOf(j10);
                try {
                    String str2 = (String) mapC.get("PlaybackDurationRemaining");
                    if (str2 != null) {
                        j3 = Long.parseLong(str2);
                    }
                } catch (NumberFormatException unused2) {
                }
                pair = new Pair(lValueOf, Long.valueOf(j3));
            }
            pair.getClass();
            jMin = Math.min(((Long) pair.first).longValue(), ((Long) pair.second).longValue());
        } else {
            jMin = Long.MAX_VALUE;
        }
        if (jMin <= 60) {
            AbstractC1346c.n("DefaultDrmSession", "Offline license has expired or will expire soon. Remaining seconds: " + jMin);
            m(2, z6, bArr);
            return;
        }
        if (jMin <= 0) {
            j(new w(), 2);
            return;
        }
        this.o = 4;
        h2.h hVar = this.f21168h;
        synchronized (hVar.l) {
            set = hVar.f15213n;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((k) it.next()).b();
        }
    }

    public final boolean i() {
        int i7 = this.o;
        return i7 == 3 || i7 == 4;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(java.lang.Throwable r6, int r7) {
        /*
            r5 = this;
            q2.g r0 = new q2.g
            boolean r1 = r6 instanceof android.media.MediaDrm.MediaDrmStateException
            r2 = 1
            if (r1 == 0) goto L17
            r7 = r6
            android.media.MediaDrm$MediaDrmStateException r7 = (android.media.MediaDrm.MediaDrmStateException) r7
            java.lang.String r7 = r7.getDiagnosticInfo()
            int r7 = h2.AbstractC1343A.s(r7)
            int r7 = h2.AbstractC1343A.r(r7)
            goto L62
        L17:
            int r1 = h2.AbstractC1343A.f15195a
            r3 = 23
            r4 = 6006(0x1776, float:8.416E-42)
            if (r1 < r3) goto L25
            boolean r1 = r6 instanceof android.media.MediaDrmResetException
            if (r1 == 0) goto L25
        L23:
            r7 = r4
            goto L62
        L25:
            boolean r1 = r6 instanceof android.media.NotProvisionedException
            r3 = 6002(0x1772, float:8.41E-42)
            if (r1 != 0) goto L5a
            boolean r1 = h6.n.m(r6)
            if (r1 == 0) goto L32
            goto L5a
        L32:
            boolean r1 = r6 instanceof android.media.DeniedByServerException
            if (r1 == 0) goto L39
            r7 = 6007(0x1777, float:8.418E-42)
            goto L62
        L39:
            boolean r1 = r6 instanceof q2.y
            if (r1 == 0) goto L40
            r7 = 6001(0x1771, float:8.409E-42)
            goto L62
        L40:
            boolean r1 = r6 instanceof q2.C2173d
            if (r1 == 0) goto L47
            r7 = 6003(0x1773, float:8.412E-42)
            goto L62
        L47:
            boolean r1 = r6 instanceof q2.w
            if (r1 == 0) goto L4e
            r7 = 6008(0x1778, float:8.419E-42)
            goto L62
        L4e:
            if (r7 != r2) goto L51
            goto L23
        L51:
            r1 = 2
            if (r7 != r1) goto L57
            r7 = 6004(0x1774, float:8.413E-42)
            goto L62
        L57:
            r1 = 3
            if (r7 != r1) goto L5c
        L5a:
            r7 = r3
            goto L62
        L5c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            r5.<init>()
            throw r5
        L62:
            r0.<init>(r6, r7)
            r5.f21178t = r0
            java.lang.String r7 = "DefaultDrmSession"
            java.lang.String r0 = "DRM session error"
            h2.AbstractC1346c.q(r7, r0, r6)
            boolean r7 = r6 instanceof java.lang.Exception
            if (r7 == 0) goto L94
            h2.h r7 = r5.f21168h
            java.lang.Object r0 = r7.l
            monitor-enter(r0)
            java.util.Set r7 = r7.f15213n     // Catch: java.lang.Throwable -> L91
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L91
            java.util.Iterator r7 = r7.iterator()
        L7e:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto La8
            java.lang.Object r0 = r7.next()
            q2.k r0 = (q2.k) r0
            r1 = r6
            java.lang.Exception r1 = (java.lang.Exception) r1
            r0.d(r1)
            goto L7e
        L91:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L91
            throw r5
        L94:
            boolean r7 = r6 instanceof java.lang.Error
            if (r7 == 0) goto Lb0
            boolean r7 = h6.n.n(r6)
            if (r7 != 0) goto La8
            boolean r7 = h6.n.m(r6)
            if (r7 == 0) goto La5
            goto La8
        La5:
            java.lang.Error r6 = (java.lang.Error) r6
            throw r6
        La8:
            int r6 = r5.o
            r7 = 4
            if (r6 == r7) goto Laf
            r5.o = r2
        Laf:
            return
        Lb0:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r7 = "Unexpected Throwable subclass"
            r5.<init>(r7, r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: q2.C2171b.j(java.lang.Throwable, int):void");
    }

    public final void k(Throwable th, boolean z6) {
        if ((th instanceof NotProvisionedException) || h6.n.m(th)) {
            this.c.v0(this);
        } else {
            j(th, z6 ? 1 : 2);
        }
    }

    public final boolean l() {
        Set set;
        if (i()) {
            return true;
        }
        try {
            try {
                byte[] bArrZ = this.f21164b.z();
                this.f21179u = bArrZ;
                this.f21164b.B(bArrZ, this.f21170j);
                this.f21177s = this.f21164b.w(this.f21179u);
                this.o = 3;
                h2.h hVar = this.f21168h;
                synchronized (hVar.l) {
                    set = hVar.f15213n;
                }
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).c(3);
                }
                this.f21179u.getClass();
                return true;
            } catch (NotProvisionedException unused) {
                this.c.v0(this);
                return false;
            }
        } catch (Exception | NoSuchMethodError e10) {
            if (h6.n.m(e10)) {
                this.c.v0(this);
                return false;
            }
            j(e10, 1);
            return false;
        }
    }

    public final void m(int i7, boolean z6, byte[] bArr) {
        try {
            q qVarL = this.f21164b.l(bArr, this.f21163a, i7, this.f21167g);
            this.f21181w = qVarL;
            HandlerC1768y handlerC1768y = this.f21176r;
            int i10 = AbstractC1343A.f15195a;
            qVarL.getClass();
            handlerC1768y.getClass();
            handlerC1768y.obtainMessage(2, new C2170a(C2516t.f22900a.getAndIncrement(), z6, SystemClock.elapsedRealtime(), qVarL)).sendToTarget();
        } catch (Exception | NoSuchMethodError e10) {
            k(e10, true);
        }
    }

    public final void n() {
        Thread threadCurrentThread = Thread.currentThread();
        Looper looper = this.f21172m;
        if (threadCurrentThread != looper.getThread()) {
            AbstractC1346c.A("DefaultDrmSession", "DefaultDrmSession accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + looper.getThread().getName(), new IllegalStateException());
        }
    }
}
