package l3;

import B5.RunnableC0079c;
import F8.AbstractC0287w;
import F8.C0269d;
import H7.InterfaceC0318c;
import N5.C0452h;
import Q7.InterfaceC0655h;
import S0.C0698j;
import T.AbstractC0731b;
import T.C0735d;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.f0;
import androidx.lifecycle.h0;
import androidx.lifecycle.j0;
import androidx.lifecycle.k0;
import b2.AbstractC0992b;
import b2.C0993c;
import c8.C1036a;
import com.stingray.karaoke.car.player.PlayerService;
import d2.C1066d;
import d5.C1078G;
import e2.e0;
import e8.C1236a;
import e8.C1240e;
import f8.InterfaceC1268d;
import g2.C1288a;
import g2.C1289b;
import g3.C1294c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h8.C1389f;
import io.sentry.C1489n1;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import k3.A0;
import k3.C1679y0;
import n2.C1923x;
import r9.C2322l;
import w8.EnumC2609d;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class P implements B5.t, J3.e, B2.t, d0.j, f7.c, Y2.d {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static int f18536p;
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f18537m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f18538n;
    public Object o;

    public /* synthetic */ P(int i7, boolean z6) {
        this.l = i7;
    }

    public static void A(P p10, S0.G g7, P6.a aVar, Object obj) {
        p10.getClass();
        aVar.getClass();
        C0698j c0698j = new C0698j(g7);
        synchronized (((C8.d) p10.o)) {
            try {
                if (obj == null) {
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static final void c(P p10, Network network, boolean z6) {
        boolean z10;
        Network[] allNetworks = ((ConnectivityManager) p10.f18537m).getAllNetworks();
        int length = allNetworks.length;
        boolean z11 = false;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                break;
            }
            Network network2 = allNetworks[i7];
            if (A7.m.a(network2, network)) {
                z10 = z6;
            } else {
                NetworkCapabilities networkCapabilities = ((ConnectivityManager) p10.f18537m).getNetworkCapabilities(network2);
                z10 = networkCapabilities != null && networkCapabilities.hasCapability(12);
            }
            if (z10) {
                z11 = true;
                break;
            }
            i7++;
        }
        N3.h hVar = (N3.h) p10.f18538n;
        synchronized (hVar) {
            try {
                if (((z3.i) hVar.l.get()) != null) {
                    hVar.f4586p = z11;
                } else {
                    hVar.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static boolean m(Editable editable, KeyEvent keyEvent, boolean z6) {
        T1.x[] xVarArr;
        if (!KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (xVarArr = (T1.x[]) editable.getSpans(selectionStart, selectionEnd, T1.x.class)) != null && xVarArr.length > 0) {
            for (T1.x xVar : xVarArr) {
                int spanStart = editable.getSpanStart(xVar);
                int spanEnd = editable.getSpanEnd(xVar);
                if ((z6 && spanStart == selectionStart) || ((!z6 && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public static void o(Bundle bundle) {
        if (bundle != null) {
            ClassLoader classLoader = P.class.getClassLoader();
            classLoader.getClass();
            bundle.setClassLoader(classLoader);
        }
    }

    public boolean B(Object obj) {
        if (obj instanceof B4.b) {
            ((B4.b) obj).c().f641a = true;
        }
        ((B4.c) this.f18538n).b(obj);
        return ((E1.d) this.o).c(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object C(S0.G r6, P6.a r7, S0.C0693e r8, s7.AbstractC2410c r9) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r9 instanceof S0.C0699k
            if (r0 == 0) goto L13
            r0 = r9
            S0.k r0 = (S0.C0699k) r0
            int r1 = r0.f7846p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f7846p = r1
            goto L18
        L13:
            S0.k r0 = new S0.k
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.f7845n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f7846p
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            S0.j r5 = r0.f7844m
            l3.P r6 = r0.l
            h8.AbstractC1386c.u(r9)
            r4 = r9
            r9 = r5
            r5 = r6
            r6 = r4
            goto L74
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            h8.AbstractC1386c.u(r9)
            S0.j r9 = new S0.j
            r7.getClass()
            r9.<init>(r6)
            java.lang.Object r6 = r5.o
            C8.d r6 = (C8.d) r6
            monitor-enter(r6)
            java.lang.Object r7 = r5.f18537m     // Catch: java.lang.Throwable -> L5e
            R0.b r7 = (R0.b) r7     // Catch: java.lang.Throwable -> L5e
            java.lang.Object r7 = r7.b(r9)     // Catch: java.lang.Throwable -> L5e
            S0.i r7 = (S0.C0697i) r7     // Catch: java.lang.Throwable -> L5e
            if (r7 != 0) goto L60
            java.lang.Object r7 = r5.f18538n     // Catch: java.lang.Throwable -> L5e
            R0.c r7 = (R0.c) r7     // Catch: java.lang.Throwable -> L5e
            java.lang.Object r7 = r7.a(r9)     // Catch: java.lang.Throwable -> L5e
            S0.i r7 = (S0.C0697i) r7     // Catch: java.lang.Throwable -> L5e
            goto L60
        L5e:
            r5 = move-exception
            goto L9a
        L60:
            if (r7 == 0) goto L66
            java.lang.Object r5 = r7.f7842a     // Catch: java.lang.Throwable -> L5e
            monitor-exit(r6)
            return r5
        L66:
            monitor-exit(r6)
            r0.l = r5
            r0.f7844m = r9
            r0.f7846p = r3
            java.lang.Object r6 = r8.b(r0)
            if (r6 != r1) goto L74
            return r1
        L74:
            java.lang.Object r7 = r5.o
            C8.d r7 = (C8.d) r7
            monitor-enter(r7)
            if (r6 != 0) goto L89
            java.lang.Object r5 = r5.f18538n     // Catch: java.lang.Throwable -> L97
            R0.c r5 = (R0.c) r5     // Catch: java.lang.Throwable -> L97
            S0.i r8 = new S0.i     // Catch: java.lang.Throwable -> L97
            r0 = 0
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L97
            r5.d(r9, r8)     // Catch: java.lang.Throwable -> L97
            goto L95
        L89:
            java.lang.Object r5 = r5.f18537m     // Catch: java.lang.Throwable -> L97
            R0.b r5 = (R0.b) r5     // Catch: java.lang.Throwable -> L97
            S0.i r8 = new S0.i     // Catch: java.lang.Throwable -> L97
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L97
            r5.e(r9, r8)     // Catch: java.lang.Throwable -> L97
        L95:
            monitor-exit(r7)
            return r6
        L97:
            r5 = move-exception
            monitor-exit(r7)
            throw r5
        L9a:
            monitor-exit(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: l3.P.C(S0.G, P6.a, S0.e, s7.c):java.lang.Object");
    }

    public void D(Object obj) {
        long id = Thread.currentThread().getId();
        if (id == AbstractC0731b.f8232a) {
            this.o = obj;
            return;
        }
        synchronized (this.f18538n) {
            b0.f fVar = (b0.f) ((AtomicReference) this.f18537m).get();
            int iA = fVar.a(id);
            if (iA < 0) {
                ((AtomicReference) this.f18537m).set(fVar.b(id, obj));
            } else {
                fVar.c[iA] = obj;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.AbstractCollection, java.util.List] */
    public void E(X x8) {
        L l = (L) this.f18537m;
        l.f18527g = x8;
        synchronized (l.d) {
            for (int iBeginBroadcast = l.f18526f.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((InterfaceC1748d) l.f18526f.getBroadcastItem(iBeginBroadcast)).E(x8);
                } catch (RemoteException unused) {
                }
            }
            l.f18526f.finishBroadcast();
        }
        MediaSession mediaSession = l.f18523a;
        if (x8.f18561w == null) {
            PlaybackState.Builder builder = new PlaybackState.Builder();
            builder.setState(x8.l, x8.f18552m, x8.o, x8.f18557s);
            builder.setBufferedPosition(x8.f18553n);
            builder.setActions(x8.f18554p);
            builder.setErrorMessage(x8.f18556r);
            for (W w10 : x8.f18558t) {
                PlaybackState.CustomAction customActionBuild = w10.f18551p;
                if (customActionBuild == null) {
                    PlaybackState.CustomAction.Builder builder2 = new PlaybackState.CustomAction.Builder(w10.l, w10.f18549m, w10.f18550n);
                    builder2.setExtras(w10.o);
                    customActionBuild = builder2.build();
                }
                if (customActionBuild != null) {
                    builder.addCustomAction(customActionBuild);
                }
            }
            builder.setActiveQueueItemId(x8.f18559u);
            builder.setExtras(x8.f18560v);
            x8.f18561w = builder.build();
        }
        mediaSession.setPlaybackState(x8.f18561w);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public F8.a0 F(W7.h r9, e8.C1236a r10, boolean r11) {
        /*
            r8 = this;
            r0 = 0
            r1 = 2
            r2 = 1
            java.lang.String r3 = "arrayType"
            A7.m.f(r9, r3)
            W7.A r3 = r9.f9885b
            boolean r4 = r3 instanceof W7.y
            r5 = 0
            if (r4 == 0) goto L13
            r4 = r3
            W7.y r4 = (W7.y) r4
            goto L14
        L13:
            r4 = r5
        L14:
            if (r4 == 0) goto L2e
            java.lang.Class r6 = java.lang.Void.TYPE
            java.lang.Class r4 = r4.f9901a
            boolean r6 = A7.m.a(r4, r6)
            if (r6 == 0) goto L21
            goto L2e
        L21:
            java.lang.String r4 = r4.getName()
            w8.d r4 = w8.EnumC2609d.b(r4)
            N7.k r4 = r4.d()
            goto L2f
        L2e:
            r4 = r5
        L2f:
            c8.c r6 = new c8.c
            java.lang.Object r7 = r8.f18537m
            N0.q r7 = (N0.q) r7
            r6.<init>(r7, r9, r2)
            java.lang.Object r9 = r7.f4399m
            c8.a r9 = (c8.C1036a) r9
            boolean r10 = r10.d
            if (r4 == 0) goto L6e
            T7.z r8 = r9.o
            N7.i r8 = r8.f8921p
            F8.A r8 = r8.p(r4)
            R7.i r9 = new R7.i
            R7.h r11 = r8.k()
            R7.h[] r1 = new R7.h[r1]
            r1[r0] = r11
            r1[r2] = r6
            r9.<init>(r1)
            F8.w r8 = g1.AbstractC1274a.Y(r8, r9)
            java.lang.String r9 = "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType"
            A7.m.d(r8, r9)
            F8.A r8 = (F8.A) r8
            if (r10 == 0) goto L65
            goto L6d
        L65:
            F8.A r9 = r8.L0(r2)
            F8.a0 r8 = F8.C0269d.j(r8, r9)
        L6d:
            return r8
        L6e:
            r4 = 6
            e8.a r0 = a.AbstractC0904a.S(r1, r10, r0, r5, r4)
            F8.w r8 = r8.G(r3, r0)
            r0 = 3
            if (r10 == 0) goto L86
            if (r11 == 0) goto L7d
            r2 = r0
        L7d:
            T7.z r9 = r9.o
            N7.i r9 = r9.f8921p
            F8.A r8 = r9.g(r2, r8, r6)
            return r8
        L86:
            T7.z r10 = r9.o
            N7.i r10 = r10.f8921p
            F8.A r10 = r10.g(r2, r8, r6)
            T7.z r9 = r9.o
            N7.i r9 = r9.f8921p
            F8.A r8 = r9.g(r0, r8, r6)
            F8.A r8 = r8.L0(r2)
            F8.a0 r8 = F8.C0269d.j(r10, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: l3.P.F(W7.h, e8.a, boolean):F8.a0");
    }

    public AbstractC0287w G(InterfaceC1268d interfaceC1268d, C1236a c1236a) {
        F8.A aK;
        boolean z6 = interfaceC1268d instanceof W7.y;
        N0.q qVar = (N0.q) this.f18537m;
        if (z6) {
            Class cls = Void.TYPE;
            Class cls2 = ((W7.y) interfaceC1268d).f9901a;
            N7.k kVarD = A7.m.a(cls2, cls) ? null : EnumC2609d.b(cls2.getName()).d();
            return kVarD != null ? ((C1036a) qVar.f4399m).o.f8921p.r(kVarD) : ((C1036a) qVar.f4399m).o.f8921p.v();
        }
        boolean z10 = false;
        if (!(interfaceC1268d instanceof W7.p)) {
            if (interfaceC1268d instanceof W7.h) {
                return F((W7.h) interfaceC1268d, c1236a, false);
            }
            if (interfaceC1268d instanceof W7.D) {
                W7.A aC = ((W7.D) interfaceC1268d).c();
                return aC != null ? G(aC, c1236a) : ((C1036a) qVar.f4399m).o.f8921p.n();
            }
            if (interfaceC1268d == null) {
                return ((C1036a) qVar.f4399m).o.f8921p.n();
            }
            throw new UnsupportedOperationException("Unsupported type: " + interfaceC1268d);
        }
        W7.p pVar = (W7.p) interfaceC1268d;
        if (!c1236a.d && c1236a.f14483a != 1) {
            z10 = true;
        }
        boolean zD = pVar.d();
        H8.h hVar = H8.h.f2882n;
        Type type = pVar.f9893a;
        if (!zD && !z10) {
            F8.A aK2 = k(pVar, c1236a, null);
            return aK2 != null ? aK2 : H8.i.c(hVar, type.toString());
        }
        F8.A aK3 = k(pVar, c1236a.b(3), null);
        if (aK3 != null && (aK = k(pVar, c1236a.b(2), aK3)) != null) {
            return zD ? new C1240e(aK3, aK) : C0269d.j(aK3, aK);
        }
        return H8.i.c(hVar, type.toString());
    }

    public void H() {
        d0.l lVar = (d0.l) this.f18537m;
        LinkedHashMap linkedHashMap = lVar.c;
        String str = (String) this.f18538n;
        List list = (List) linkedHashMap.remove(str);
        if (list != null) {
            list.remove((A7.o) this.o);
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        lVar.c.put(str, list);
    }

    public void I() {
        ArrayList arrayList = (ArrayList) this.o;
        if (arrayList.isEmpty()) {
            C0735d.Y("empty stack");
            throw null;
        }
        this.f18538n = arrayList.remove(arrayList.size() - 1);
    }

    @Override // Y2.d
    public int a(long j3) {
        long[] jArr = (long[]) this.o;
        int iB = AbstractC1343A.b(jArr, j3, false);
        if (iB < jArr.length) {
            return iB;
        }
        return -1;
    }

    @Override // f7.c
    public void b(Object obj) throws Throwable {
        try {
            boolean z6 = obj instanceof String;
            e7.g gVar = (e7.g) this.f18537m;
            if (z6) {
                q9.g gVar2 = gVar.f14482z;
                String str = (String) obj;
                gVar2.getClass();
                A7.m.f(str, "text");
                C2322l c2322l = C2322l.o;
                gVar2.g(1, m6.b.p(str));
            } else if (obj instanceof byte[]) {
                q9.g gVar3 = gVar.f14482z;
                byte[] bArr = (byte[]) obj;
                C2322l c2322l2 = C2322l.o;
                A7.m.f(bArr, "data");
                byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                A7.m.e(bArrCopyOf, "copyOf(...)");
                C2322l c2322l3 = new C2322l(bArrCopyOf);
                gVar3.getClass();
                gVar3.g(2, c2322l3);
            }
        } catch (IllegalStateException unused) {
            e7.g.f14481A.fine("websocket closed before we could write");
        }
        int[] iArr = (int[]) this.f18538n;
        int i7 = iArr[0] - 1;
        iArr[0] = i7;
        if (i7 == 0) {
            ((RunnableC0079c) this.o).run();
        }
    }

    @Override // Y2.d
    public long d(int i7) {
        AbstractC1346c.c(i7 >= 0);
        long[] jArr = (long[]) this.o;
        AbstractC1346c.c(i7 < jArr.length);
        return jArr[i7];
    }

    @Override // B2.t
    public void e() {
        ((B2.t) this.f18537m).e();
    }

    @Override // B5.t
    public void f(C5.d dVar) {
        int iOrdinal = dVar.i().ordinal();
        if (iOrdinal == 0) {
            throw new ClassCastException();
        }
        if (iOrdinal == 1) {
            throw new ClassCastException();
        }
        C5.g gVar = (C5.g) this.f18538n;
        String str = (String) this.f18537m;
        if (iOrdinal == 2) {
            C5.f fVar = (C5.f) dVar;
            if (H7.H.G(fVar.e("integrations"), str)) {
                ((B5.I) gVar).b(fVar);
                return;
            }
            return;
        }
        if (iOrdinal == 3) {
            C5.i iVar = (C5.i) dVar;
            if (H7.H.G(iVar.e("integrations"), str)) {
                ((B5.I) gVar).b(iVar);
                return;
            }
            return;
        }
        if (iOrdinal != 4) {
            throw new AssertionError("unknown type " + dVar.i());
        }
        C5.j jVar = (C5.j) dVar;
        B5.L lE = jVar.e("integrations");
        B5.L lE2 = ((B5.y) this.o).e("plan");
        B5.L lE3 = lE2 == null ? null : lE2.e("track");
        if (D5.g.h(lE3)) {
            if (H7.H.G(lE, str)) {
                ((B5.I) gVar).b(jVar);
                return;
            }
            return;
        }
        B5.L lE4 = lE3.e(jVar.c("event"));
        if (D5.g.h(lE4)) {
            if (!D5.g.h(lE)) {
                if (H7.H.G(lE, str)) {
                    ((B5.I) gVar).b(jVar);
                    return;
                }
                return;
            }
            B5.L lE5 = lE3.e("__default");
            if (D5.g.h(lE5)) {
                ((B5.I) gVar).b(jVar);
                return;
            } else {
                if (lE5.b("enabled") || "Segment.io".equals(str)) {
                    ((B5.I) gVar).b(jVar);
                    return;
                }
                return;
            }
        }
        if (!lE4.b("enabled")) {
            if ("Segment.io".equals(str)) {
                ((B5.I) gVar).b(jVar);
                return;
            }
            return;
        }
        B5.L l = new B5.L();
        B5.L lE6 = lE4.e("integrations");
        if (!D5.g.h(lE6)) {
            l.putAll(lE6);
        }
        l.putAll(lE);
        if (H7.H.G(l, str)) {
            ((B5.I) gVar).b(jVar);
        }
    }

    @Override // Y2.d
    public List g(long j3) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i7 = 0;
        while (true) {
            List list = (List) this.f18537m;
            if (i7 >= list.size()) {
                break;
            }
            int i10 = i7 * 2;
            long[] jArr = (long[]) this.f18538n;
            if (jArr[i10] <= j3 && j3 < jArr[i10 + 1]) {
                C1294c c1294c = (C1294c) list.get(i7);
                C1289b c1289b = c1294c.f14828a;
                if (c1289b.f14789e == -3.4028235E38f) {
                    arrayList2.add(c1294c);
                } else {
                    arrayList.add(c1289b);
                }
            }
            i7++;
        }
        Collections.sort(arrayList2, new C1.b(7));
        for (int i11 = 0; i11 < arrayList2.size(); i11++) {
            C1288a c1288aA = ((C1294c) arrayList2.get(i11)).f14828a.a();
            c1288aA.f14758e = (-1) - i11;
            c1288aA.f14759f = 1;
            arrayList.add(c1288aA.a());
        }
        return arrayList;
    }

    @Override // Y2.d
    public int h() {
        return ((long[]) this.o).length;
    }

    public Object i() {
        Object objA = ((E1.d) this.o).a();
        if (objA == null) {
            objA = ((B4.a) this.f18537m).m();
            if (Log.isLoggable("FactoryPools", 2)) {
                Log.v("FactoryPools", "Created new " + objA.getClass());
            }
        }
        if (objA instanceof B4.b) {
            ((B4.b) objA).c().f641a = false;
        }
        return objA;
    }

    public void j() {
        ((ArrayList) this.o).clear();
        this.f18538n = (E0.F) this.f18537m;
        ((E0.F) this.f18537m).m0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a2  */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r12v4, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v24, types: [java.lang.Object, m7.f] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public F8.A k(W7.p r22, e8.C1236a r23, F8.A r24) {
        /*
            Method dump skipped, instruction units count: 1050
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l3.P.k(W7.p, e8.a, F8.A):F8.A");
    }

    @Override // J3.e
    public boolean l() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f18537m;
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (networkCapabilities != null && networkCapabilities.hasCapability(12)) {
                return true;
            }
        }
        return false;
    }

    public void n(Object obj) {
        ((ArrayList) this.o).add(this.f18538n);
        this.f18538n = obj;
    }

    public Object p() {
        long id = Thread.currentThread().getId();
        if (id == AbstractC0731b.f8232a) {
            return this.o;
        }
        b0.f fVar = (b0.f) ((AtomicReference) this.f18537m).get();
        int iA = fVar.a(id);
        if (iA >= 0) {
            return fVar.c[iA];
        }
        return null;
    }

    public Object q() {
        return this.f18538n;
    }

    public T r() {
        return new T(((L) this.f18537m).f18523a.getCurrentControllerInfo());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f0 s(InterfaceC0318c interfaceC0318c, String str) {
        f0 f0VarA;
        A7.m.f(interfaceC0318c, "modelClass");
        A7.m.f(str, "key");
        k0 k0Var = (k0) this.f18537m;
        k0Var.getClass();
        LinkedHashMap linkedHashMap = k0Var.f11997a;
        f0 f0Var = (f0) linkedHashMap.get(str);
        boolean zC = interfaceC0318c.c(f0Var);
        h0 h0Var = (h0) this.f18538n;
        if (zC) {
            if (h0Var instanceof j0) {
                A7.m.c(f0Var);
                ((j0) h0Var).d(f0Var);
            }
            A7.m.d(f0Var, "null cannot be cast to non-null type T of androidx.lifecycle.viewmodel.ViewModelProviderImpl.getViewModel");
            return f0Var;
        }
        C0993c c0993c = new C0993c((AbstractC0992b) this.o);
        c0993c.f12203a.put(C1066d.f13337a, str);
        A7.m.f(h0Var, "factory");
        try {
            try {
                f0VarA = h0Var.b(interfaceC0318c, c0993c);
            } catch (AbstractMethodError unused) {
                f0VarA = h0Var.a(n7.z.i(interfaceC0318c));
            }
        } catch (AbstractMethodError unused2) {
            f0VarA = h0Var.c(n7.z.i(interfaceC0318c), c0993c);
        }
        A7.m.f(f0VarA, "viewModel");
        f0 f0Var2 = (f0) linkedHashMap.put(str, f0VarA);
        if (f0Var2 != null) {
            f0Var2.b();
        }
        return f0VarA;
    }

    @Override // J3.e
    public void shutdown() {
        ((ConnectivityManager) this.f18537m).unregisterNetworkCallback((J3.f) this.o);
    }

    public boolean t(CharSequence charSequence, int i7, int i10, T1.w wVar) {
        if ((wVar.c & 3) == 0) {
            T1.c cVar = (T1.c) this.o;
            U1.a aVarC = wVar.c();
            int iB = aVarC.b(8);
            if (iB != 0) {
                ((ByteBuffer) aVarC.o).getShort(iB + aVarC.l);
            }
            cVar.getClass();
            ThreadLocal threadLocal = T1.c.f8487b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i7 < i10) {
                sb.append(charSequence.charAt(i7));
                i7++;
            }
            TextPaint textPaint = cVar.f8488a;
            String string = sb.toString();
            int i11 = y1.c.f24115a;
            boolean zHasGlyph = textPaint.hasGlyph(string);
            int i12 = wVar.c & 4;
            wVar.c = zHasGlyph ? i12 | 2 : i12 | 1;
        }
        return (wVar.c & 3) == 2;
    }

    public String toString() {
        switch (this.l) {
            case 20:
                StringBuilder sb = new StringBuilder(32);
                sb.append((String) this.f18537m);
                sb.append('{');
                C1489n1 c1489n1 = (C1489n1) ((C1489n1) this.f18538n).f16625n;
                String str = "";
                while (c1489n1 != null) {
                    Object obj = c1489n1.f16624m;
                    sb.append(str);
                    if (obj == null || !obj.getClass().isArray()) {
                        sb.append(obj);
                    } else {
                        String strDeepToString = Arrays.deepToString(new Object[]{obj});
                        sb.append((CharSequence) strDeepToString, 1, strDeepToString.length() - 1);
                    }
                    c1489n1 = (C1489n1) c1489n1.f16625n;
                    str = ", ";
                }
                sb.append('}');
                return sb.toString();
            default:
                return super.toString();
        }
    }

    @Override // B2.t
    public B2.L u(int i7, int i10) {
        B2.t tVar = (B2.t) this.f18537m;
        if (i10 != 3) {
            return tVar.u(i7, i10);
        }
        SparseArray sparseArray = (SparseArray) this.o;
        Y2.n nVar = (Y2.n) sparseArray.get(i7);
        if (nVar != null) {
            return nVar;
        }
        Y2.n nVar2 = new Y2.n(tVar.u(i7, i10), (Y2.j) this.f18538n);
        sparseArray.put(i7, nVar2);
        return nVar2;
    }

    public boolean v() {
        P p10;
        return ((S0.O) this.f18537m).getValue() != this.o || ((p10 = (P) this.f18538n) != null && p10.v());
    }

    @Override // B2.t
    public void w(B2.F f10) {
        ((B2.t) this.f18537m).w(f10);
    }

    public h5.y x(A0 a02, C1679y0 c1679y0, List list) {
        String str;
        A7.m.f(a02, "mediaSession");
        A7.m.f(c1679y0, "controller");
        A7.m.f(list, "mediaItems");
        C1078G c1078gListIterator = ((d5.I) list).listIterator(0);
        while (c1078gListIterator.hasNext()) {
            e2.F f10 = (e2.F) c1078gListIterator.next();
            C0452h c0452h = PlayerService.f13012y;
            if (c0452h != null && (str = f10.f13881f.f13855b) != null) {
                c0452h.b(str);
            }
        }
        ArrayList arrayList = new ArrayList();
        C1923x c1923x = PlayerService.f13008u;
        if (c1923x == null) {
            A7.m.j("player");
            throw null;
        }
        int iO = c1923x.t0().o();
        for (int i7 = 0; i7 < iO; i7++) {
            C1923x c1923x2 = PlayerService.f13008u;
            if (c1923x2 == null) {
                A7.m.j("player");
                throw null;
            }
            e2.F f11 = c1923x2.t0().m(i7, (e0) c1923x2.f14069b, 0L).c;
            A7.m.e(f11, "getMediaItemAt(...)");
            arrayList.add(f11);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((e2.F) it.next()).f13879b == null) {
                UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();
                h5.u uVar = new h5.u();
                uVar.m(unsupportedOperationException);
                return uVar;
            }
        }
        return l9.l.T(arrayList);
    }

    public void y() {
        AndroidComposeView androidComposeView = ((E0.F) this.f18537m).f1487t;
        if (androidComposeView != null) {
            androidComposeView.s();
        }
    }

    public Object z(CharSequence charSequence, int i7, int i10, int i11, boolean z6, T1.o oVar) {
        int i12;
        char c;
        T1.q qVar = new T1.q((T1.t) ((B8.t) this.f18538n).f809p);
        int iCodePointAt = Character.codePointAt(charSequence, i7);
        boolean zA = true;
        int i13 = 0;
        int iCharCount = i7;
        loop0: while (true) {
            i12 = iCharCount;
            while (iCharCount < i10 && i13 < i11 && zA) {
                SparseArray sparseArray = qVar.c.f8520a;
                T1.t tVar = sparseArray == null ? null : (T1.t) sparseArray.get(iCodePointAt);
                if (qVar.f8510a == 2) {
                    if (tVar != null) {
                        qVar.c = tVar;
                        qVar.f8513f++;
                    } else {
                        if (iCodePointAt == 65038) {
                            qVar.a();
                        } else if (iCodePointAt != 65039) {
                            T1.t tVar2 = qVar.c;
                            if (tVar2.f8521b != null) {
                                if (qVar.f8513f != 1) {
                                    qVar.d = tVar2;
                                    qVar.a();
                                } else if (qVar.b()) {
                                    qVar.d = qVar.c;
                                    qVar.a();
                                } else {
                                    qVar.a();
                                }
                                c = 3;
                            } else {
                                qVar.a();
                            }
                        }
                        c = 1;
                    }
                    c = 2;
                } else if (tVar == null) {
                    qVar.a();
                    c = 1;
                } else {
                    qVar.f8510a = 2;
                    qVar.c = tVar;
                    qVar.f8513f = 1;
                    c = 2;
                }
                qVar.f8512e = iCodePointAt;
                if (c == 1) {
                    iCharCount = Character.charCount(Character.codePointAt(charSequence, i12)) + i12;
                    if (iCharCount < i10) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount);
                    }
                } else if (c == 2) {
                    int iCharCount2 = Character.charCount(iCodePointAt) + iCharCount;
                    if (iCharCount2 < i10) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount2);
                    }
                    iCharCount = iCharCount2;
                } else if (c == 3) {
                    if (z6 || !t(charSequence, i12, iCharCount, qVar.d.f8521b)) {
                        zA = oVar.a(charSequence, i12, iCharCount, qVar.d.f8521b);
                        i13++;
                    }
                }
            }
            break loop0;
        }
        if (qVar.f8510a == 2 && qVar.c.f8521b != null && ((qVar.f8513f > 1 || qVar.b()) && i13 < i11 && zA && (z6 || !t(charSequence, i12, iCharCount, qVar.c.f8521b)))) {
            oVar.a(charSequence, i12, iCharCount, qVar.c.f8521b);
        }
        return oVar.c();
    }

    public /* synthetic */ P(Object obj, Object obj2, Object obj3, int i7) {
        this.l = i7;
        this.f18537m = obj;
        this.f18538n = obj2;
        this.o = obj3;
    }

    public P(List list) {
        this.l = 9;
        this.f18538n = list;
        this.o = new ArrayList(list.size());
        this.f18537m = new ArrayList(list.size());
        for (int i7 = 0; i7 < list.size(); i7++) {
            ((ArrayList) this.o).add(new R3.l((List) ((W3.f) list.get(i7)).f9777b.f476m));
            ((ArrayList) this.f18537m).add(((W3.f) list.get(i7)).c.t0());
        }
    }

    public P(C1389f c1389f, V7.b bVar) {
        this.l = 17;
        this.f18537m = c1389f;
        this.f18538n = bVar;
        this.o = new ConcurrentHashMap();
    }

    public P(k0 k0Var, h0 h0Var, AbstractC0992b abstractC0992b) {
        this.l = 19;
        A7.m.f(k0Var, "store");
        A7.m.f(h0Var, "factory");
        A7.m.f(abstractC0992b, "extras");
        this.f18537m = k0Var;
        this.f18538n = h0Var;
        this.o = abstractC0992b;
    }

    public P(ArrayList arrayList) {
        this.l = 25;
        this.f18537m = Collections.unmodifiableList(new ArrayList(arrayList));
        this.f18538n = new long[arrayList.size() * 2];
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            C1294c c1294c = (C1294c) arrayList.get(i7);
            int i10 = i7 * 2;
            long[] jArr = (long[]) this.f18538n;
            jArr[i10] = c1294c.f14829b;
            jArr[i10 + 1] = c1294c.c;
        }
        long[] jArr2 = (long[]) this.f18538n;
        long[] jArrCopyOf = Arrays.copyOf(jArr2, jArr2.length);
        this.o = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    public P(G7.d dVar, Method[] methodArr, Method method) {
        this.l = 6;
        A7.m.f(dVar, "argumentRange");
        this.f18537m = dVar;
        this.f18538n = methodArr;
        this.o = method;
    }

    public P(N0.q qVar, c8.e eVar) {
        this.l = 24;
        A7.m.f(qVar, "c");
        A7.m.f(eVar, "typeParameterResolver");
        this.f18537m = qVar;
        this.f18538n = eVar;
        this.o = new io.sentry.internal.debugmeta.c(new Y4.e(19));
    }

    public P(B2.t tVar, Y2.j jVar) {
        this.l = 18;
        this.f18537m = tVar;
        this.f18538n = jVar;
        this.o = new SparseArray();
    }

    public P(View view) {
        this.l = 12;
        this.f18537m = view;
        this.f18538n = h6.r.n(m7.g.f19257m, new K7.K(this, 19));
        this.o = new B2.O(view);
    }

    public P(InterfaceC0655h interfaceC0655h, List list, P p10) {
        this.l = 8;
        A7.m.f(interfaceC0655h, "classifierDescriptor");
        A7.m.f(list, "arguments");
        this.f18537m = interfaceC0655h;
        this.f18538n = list;
        this.o = p10;
    }

    public P(ConnectivityManager connectivityManager, N3.h hVar) {
        this.l = 5;
        this.f18537m = connectivityManager;
        this.f18538n = hVar;
        J3.f fVar = new J3.f(this, 0);
        this.o = fVar;
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), fVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public P(d0.l lVar, String str, InterfaceC2772a interfaceC2772a) {
        this.l = 21;
        this.f18537m = lVar;
        this.f18538n = str;
        this.o = (A7.o) interfaceC2772a;
    }

    public P(B8.t tVar, C8.d dVar, T1.c cVar, Set set) {
        this.l = 13;
        this.f18537m = dVar;
        this.f18538n = tVar;
        this.o = cVar;
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            int[] iArr = (int[]) it.next();
            String str = new String(iArr, 0, iArr.length);
            z(str, 0, str.length(), 1, true, new B2.q(str, 2));
        }
    }

    public P(String str) {
        this.l = 20;
        C1489n1 c1489n1 = new C1489n1(15, false);
        this.f18538n = c1489n1;
        this.o = c1489n1;
        this.f18537m = str;
    }

    public P(E1.d dVar, B4.a aVar, B4.c cVar) {
        this.l = 1;
        this.o = dVar;
        this.f18537m = aVar;
        this.f18538n = cVar;
    }

    public P(S0.O o, P p10) {
        this.l = 15;
        this.f18537m = o;
        this.f18538n = p10;
        this.o = o.getValue();
    }

    public P(E0.F f10) {
        this.l = 3;
        this.f18537m = f10;
        this.o = new ArrayList();
        this.f18538n = f10;
    }

    public P(int i7) {
        this.l = i7;
        switch (i7) {
            case 11:
                this.f18537m = new AtomicReference(b0.g.f12197a);
                this.f18538n = new Object();
                break;
            case 14:
                this.o = new C8.d(11);
                break;
            case 16:
                this.f18537m = new WeakHashMap();
                this.f18538n = new WeakHashMap();
                this.o = new WeakHashMap();
                break;
            default:
                this.f18537m = new R0.b(16);
                R0.c cVar = new R0.c();
                cVar.f7350a = R0.a.f7342a;
                cVar.f7351b = R0.a.f7343b;
                cVar.c = 0;
                this.f18538n = cVar;
                this.o = new C8.d(11);
                break;
        }
    }

    public P(g4.l lVar, w4.g gVar, g4.p pVar) {
        this.l = 28;
        this.o = lVar;
        this.f18538n = gVar;
        this.f18537m = pVar;
    }

    public P(PlayerService playerService, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        this.l = 0;
        this.o = new ArrayList();
        if (playerService != null) {
            if (!TextUtils.isEmpty(str)) {
                if (componentName == null) {
                    int i7 = AbstractC1766w.f18616a;
                    Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.setPackage(playerService.getPackageName());
                    List<ResolveInfo> listQueryBroadcastReceivers = playerService.getPackageManager().queryBroadcastReceivers(intent, 0);
                    if (listQueryBroadcastReceivers.size() == 1) {
                        ActivityInfo activityInfo = listQueryBroadcastReceivers.get(0).activityInfo;
                        componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
                    } else {
                        if (listQueryBroadcastReceivers.size() > 1) {
                            h6.n.t("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
                        }
                        componentName = null;
                    }
                    if (componentName == null) {
                        h6.n.t("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                    }
                }
                if (componentName != null && pendingIntent == null) {
                    Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent2.setComponent(componentName);
                    pendingIntent = PendingIntent.getBroadcast(playerService, 0, intent2, Build.VERSION.SDK_INT >= 31 ? 33554432 : 0);
                }
                L l = new L(playerService, str, bundle);
                this.f18537m = l;
                Looper looperMyLooper = Looper.myLooper();
                l.d(new C1742G(), new Handler(looperMyLooper == null ? Looper.getMainLooper() : looperMyLooper));
                l.f18523a.setMediaButtonReceiver(pendingIntent);
                this.f18538n = new io.sentry.internal.debugmeta.c(playerService, l.c);
                if (f18536p == 0) {
                    f18536p = (int) (TypedValue.applyDimension(1, 320.0f, playerService.getResources().getDisplayMetrics()) + 0.5f);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        throw new IllegalArgumentException("context must not be null");
    }
}
