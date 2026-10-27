package e7;

import A7.m;
import B5.u;
import B8.D;
import Q7.N;
import android.app.Notification;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import d5.I;
import d8.C1129p;
import e2.C1207o;
import e2.C1208p;
import e2.L;
import e4.InterfaceC1226b;
import g4.i;
import g4.l;
import g4.p;
import h2.AbstractC1348e;
import h2.t;
import h3.C1350a;
import io.sentry.A;
import io.sentry.C1489n1;
import io.sentry.C1521w0;
import io.sentry.EnumC1495p1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import io.sentry.S;
import io.sentry.android.core.B;
import io.sentry.protocol.j;
import io.sentry.protocol.s;
import io.sentry.protocol.y;
import io.sentry.transport.n;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;
import k4.C1685c;
import k4.q;
import k4.r;
import k4.w;
import k4.z;
import l3.P;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class f implements B4.a, N, S, io.sentry.android.replay.util.b, r, InterfaceC1226b {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f14480m;

    public /* synthetic */ f(char c, int i7) {
        this.l = i7;
    }

    public static s k(Throwable th, j jVar, Long l, List list, boolean z6) {
        Package r02 = th.getClass().getPackage();
        String name = th.getClass().getName();
        s sVar = new s();
        String message = th.getMessage();
        if (r02 != null) {
            name = name.replace(r02.getName() + ".", "");
        }
        String name2 = r02 != null ? r02.getName() : null;
        if (list != null && !list.isEmpty()) {
            y yVar = new y(list);
            if (z6) {
                yVar.f16807n = Boolean.TRUE;
            }
            sVar.f16771p = yVar;
        }
        sVar.o = l;
        sVar.l = name;
        sVar.f16772q = jVar;
        sVar.f16770n = name2;
        sVar.f16769m = message;
        return sVar;
    }

    @Override // k4.r
    public q A(w wVar) {
        return new C1685c((z) this.f14480m, 1);
    }

    @Override // io.sentry.android.replay.util.b
    public int a(int i7) {
        return ((Layout) this.f14480m).getLineStart(i7);
    }

    @Override // io.sentry.android.replay.util.b
    public int b() {
        return ((Layout) this.f14480m).getLineCount();
    }

    @Override // io.sentry.android.replay.util.b
    public Integer c() {
        int i7;
        Layout layout = (Layout) this.f14480m;
        if (!(layout.getText() instanceof Spanned)) {
            return null;
        }
        CharSequence text = layout.getText();
        m.d(text, "null cannot be cast to non-null type android.text.Spanned");
        ForegroundColorSpan[] foregroundColorSpanArr = (ForegroundColorSpan[]) ((Spanned) text).getSpans(0, layout.getText().length(), ForegroundColorSpan.class);
        m.e(foregroundColorSpanArr, "spans");
        int i10 = Integer.MIN_VALUE;
        Integer numValueOf = null;
        for (ForegroundColorSpan foregroundColorSpan : foregroundColorSpanArr) {
            CharSequence text2 = layout.getText();
            m.d(text2, "null cannot be cast to non-null type android.text.Spanned");
            int spanStart = ((Spanned) text2).getSpanStart(foregroundColorSpan);
            CharSequence text3 = layout.getText();
            m.d(text3, "null cannot be cast to non-null type android.text.Spanned");
            int spanEnd = ((Spanned) text3).getSpanEnd(foregroundColorSpan);
            if (spanStart != -1 && spanEnd != -1 && (i7 = spanEnd - spanStart) > i10) {
                numValueOf = Integer.valueOf(foregroundColorSpan.getForegroundColor());
                i10 = i7;
            }
        }
        if (numValueOf != null) {
            return Integer.valueOf(numValueOf.intValue() | (-16777216));
        }
        return null;
    }

    @Override // io.sentry.android.replay.util.b
    public int d(int i7) {
        return ((Layout) this.f14480m).getLineVisibleEnd(i7);
    }

    @Override // io.sentry.android.replay.util.b
    public int e(int i7) {
        return ((Layout) this.f14480m).getLineTop(i7);
    }

    @Override // io.sentry.S
    public boolean f() {
        boolean zIsShutdown;
        synchronized (((ScheduledExecutorService) this.f14480m)) {
            zIsShutdown = ((ScheduledExecutorService) this.f14480m).isShutdown();
        }
        return zIsShutdown;
    }

    @Override // io.sentry.android.replay.util.b
    public float g(int i7, int i10) {
        return ((Layout) this.f14480m).getPrimaryHorizontal(i10);
    }

    @Override // io.sentry.android.replay.util.b
    public int h(int i7) {
        return ((Layout) this.f14480m).getEllipsisCount(i7);
    }

    @Override // io.sentry.android.replay.util.b
    public int i(int i7) {
        return ((Layout) this.f14480m).getLineBottom(i7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4 */
    public List j(u uVar) {
        String str;
        int i7;
        List listSingletonList;
        t tVar = new t((byte[]) uVar.o);
        ArrayList arrayList = (I) this.f14480m;
        while (tVar.a() > 0) {
            int iV = tVar.v();
            int iV2 = tVar.f15242b + tVar.v();
            if (iV == 134) {
                arrayList = new ArrayList();
                int iV3 = tVar.v() & 31;
                for (int i10 = 0; i10 < iV3; i10++) {
                    String strT = tVar.t(3, StandardCharsets.UTF_8);
                    int iV4 = tVar.v();
                    boolean z6 = (iV4 & 128) != 0;
                    if (z6) {
                        i7 = iV4 & 63;
                        str = "application/cea-708";
                    } else {
                        str = "application/cea-608";
                        i7 = 1;
                    }
                    byte bV = (byte) tVar.v();
                    tVar.I(1);
                    if (z6) {
                        boolean z10 = (bV & 64) != 0;
                        byte[] bArr = AbstractC1348e.f15208a;
                        listSingletonList = Collections.singletonList(z10 ? new byte[]{1} : new byte[]{0});
                    } else {
                        listSingletonList = null;
                    }
                    C1207o c1207o = new C1207o();
                    c1207o.f14246m = L.l(str);
                    c1207o.d = strT;
                    c1207o.f14232G = i7;
                    c1207o.f14248p = listSingletonList;
                    arrayList.add(new C1208p(c1207o));
                }
            }
            tVar.H(iV2);
            arrayList = arrayList;
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003a A[Catch: all -> 0x002a, DONT_GENERATE, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x000f, B:7:0x0016, B:9:0x0022, B:13:0x002c, B:14:0x003a), top: B:20:0x0005, inners: #0 }] */
    @Override // io.sentry.S
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void l(long r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f14480m
            java.util.concurrent.ScheduledExecutorService r0 = (java.util.concurrent.ScheduledExecutorService) r0
            monitor-enter(r0)
            java.lang.Object r1 = r3.f14480m     // Catch: java.lang.Throwable -> L2a
            java.util.concurrent.ScheduledExecutorService r1 = (java.util.concurrent.ScheduledExecutorService) r1     // Catch: java.lang.Throwable -> L2a
            boolean r1 = r1.isShutdown()     // Catch: java.lang.Throwable -> L2a
            if (r1 != 0) goto L3a
            java.lang.Object r1 = r3.f14480m     // Catch: java.lang.Throwable -> L2a
            java.util.concurrent.ScheduledExecutorService r1 = (java.util.concurrent.ScheduledExecutorService) r1     // Catch: java.lang.Throwable -> L2a
            r1.shutdown()     // Catch: java.lang.Throwable -> L2a
            java.lang.Object r1 = r3.f14480m     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            java.util.concurrent.ScheduledExecutorService r1 = (java.util.concurrent.ScheduledExecutorService) r1     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            boolean r4 = r1.awaitTermination(r4, r2)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            if (r4 != 0) goto L3a
            java.lang.Object r4 = r3.f14480m     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            java.util.concurrent.ScheduledExecutorService r4 = (java.util.concurrent.ScheduledExecutorService) r4     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            r4.shutdownNow()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            goto L3a
        L2a:
            r3 = move-exception
            goto L3c
        L2c:
            java.lang.Object r3 = r3.f14480m     // Catch: java.lang.Throwable -> L2a
            java.util.concurrent.ScheduledExecutorService r3 = (java.util.concurrent.ScheduledExecutorService) r3     // Catch: java.lang.Throwable -> L2a
            r3.shutdownNow()     // Catch: java.lang.Throwable -> L2a
            java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L2a
            r3.interrupt()     // Catch: java.lang.Throwable -> L2a
        L3a:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
            return
        L3c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: e7.f.l(long):void");
    }

    @Override // B4.a
    public Object m() {
        switch (this.l) {
            case 2:
                u uVar = (u) this.f14480m;
                return new i((A4.i) uVar.f736n, (P) uVar.o);
            default:
                D d = (D) this.f14480m;
                return new p((j4.e) d.f747m, (j4.e) d.f748n, (j4.e) d.o, (j4.e) d.f749p, (l) d.f750q, (l) d.f751r, (P) d.f752s);
        }
    }

    public void n(Object obj) {
        boolean z6 = obj instanceof List;
        ArrayList arrayList = null;
        MediaBrowserService.Result result = (MediaBrowserService.Result) this.f14480m;
        if (!z6) {
            if (!(obj instanceof Parcel)) {
                result.sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) obj;
            parcel.setDataPosition(0);
            result.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
            return;
        }
        List<Parcel> list = (List) obj;
        if (list != null) {
            arrayList = new ArrayList(list.size());
            for (Parcel parcel2 : list) {
                parcel2.setDataPosition(0);
                arrayList.add((MediaBrowser.MediaItem) MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel2));
                parcel2.recycle();
            }
        }
        result.sendResult(arrayList);
    }

    public void o(C1489n1 c1489n1, K k10, Object obj) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) c1489n1.f16624m;
        if (obj == null) {
            cVar.j();
            return;
        }
        if (obj instanceof Character) {
            c1489n1.L(Character.toString(((Character) obj).charValue()));
            return;
        }
        if (obj instanceof String) {
            c1489n1.L((String) obj);
            return;
        }
        if (obj instanceof Boolean) {
            c1489n1.M(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Number) {
            c1489n1.K((Number) obj);
            return;
        }
        if (obj instanceof Date) {
            try {
                c1489n1.L(AbstractC1985B.m((Date) obj));
                return;
            } catch (Exception e10) {
                k10.s(EnumC1495p1.ERROR, "Error when serializing Date", e10);
                cVar.j();
                return;
            }
        }
        if (obj instanceof TimeZone) {
            try {
                c1489n1.L(((TimeZone) obj).getID());
                return;
            } catch (Exception e11) {
                k10.s(EnumC1495p1.ERROR, "Error when serializing TimeZone", e11);
                cVar.j();
                return;
            }
        }
        if (obj instanceof InterfaceC1485m0) {
            ((InterfaceC1485m0) obj).serialize(c1489n1, k10);
            return;
        }
        if (obj instanceof Collection) {
            p(c1489n1, k10, (Collection) obj);
            return;
        }
        if (obj.getClass().isArray()) {
            p(c1489n1, k10, Arrays.asList((Object[]) obj));
            return;
        }
        if (obj instanceof Map) {
            s(c1489n1, k10, (Map) obj);
            return;
        }
        if (obj instanceof Locale) {
            c1489n1.L(obj.toString());
            return;
        }
        if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            Charset charset = io.sentry.util.b.f16916a;
            int length = atomicIntegerArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i7 = 0; i7 < length; i7++) {
                arrayList.add(Integer.valueOf(atomicIntegerArray.get(i7)));
            }
            p(c1489n1, k10, arrayList);
            return;
        }
        if (obj instanceof AtomicBoolean) {
            c1489n1.M(((AtomicBoolean) obj).get());
            return;
        }
        if (obj instanceof URI) {
            c1489n1.L(obj.toString());
            return;
        }
        if (obj instanceof InetAddress) {
            c1489n1.L(obj.toString());
            return;
        }
        if (obj instanceof UUID) {
            c1489n1.L(obj.toString());
            return;
        }
        if (obj instanceof Currency) {
            c1489n1.L(obj.toString());
            return;
        }
        if (obj instanceof Calendar) {
            s(c1489n1, k10, io.sentry.util.b.a((Calendar) obj));
            return;
        }
        if (obj.getClass().isEnum()) {
            c1489n1.L(obj.toString());
            return;
        }
        try {
            o(c1489n1, k10, ((C1.j) this.f14480m).p(k10, obj));
        } catch (Exception e12) {
            k10.s(EnumC1495p1.ERROR, "Failed serializing unknown object.", e12);
            c1489n1.L("[OBJECT]");
        }
    }

    public void p(C1489n1 c1489n1, K k10, Collection collection) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) c1489n1.f16624m;
        cVar.o();
        cVar.b();
        int i7 = cVar.f16974n;
        int[] iArr = cVar.f16973m;
        if (i7 == iArr.length) {
            cVar.f16973m = Arrays.copyOf(iArr, i7 * 2);
        }
        int[] iArr2 = cVar.f16973m;
        int i10 = cVar.f16974n;
        cVar.f16974n = i10 + 1;
        iArr2[i10] = 1;
        cVar.l.write(91);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            o(c1489n1, k10, it.next());
        }
        cVar.g(']', 1, 2);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // e4.InterfaceC1226b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean q(java.lang.Object r5, java.io.File r6, e4.h r7) {
        /*
            r4 = this;
            java.io.InputStream r5 = (java.io.InputStream) r5
            java.lang.String r7 = "StreamEncoder"
            r0 = 65536(0x10000, float:9.1835E-41)
            java.lang.Class<byte[]> r1 = byte[].class
            java.lang.Object r4 = r4.f14480m
            F.l r4 = (F.l) r4
            java.lang.Object r0 = r4.d(r0, r1)
            byte[] r0 = (byte[]) r0
            r1 = 0
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            io.sentry.instrumentation.file.g r2 = h6.m.m(r3, r6)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
        L1d:
            int r6 = r5.read(r0)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            r3 = -1
            if (r6 == r3) goto L2c
            r2.write(r0, r1, r6)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            goto L1d
        L28:
            r5 = move-exception
            goto L4c
        L2a:
            r5 = move-exception
            goto L37
        L2c:
            r2.close()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            r2.close()     // Catch: java.io.IOException -> L32
        L32:
            r4.i(r0)
            r1 = 1
            goto L4b
        L37:
            r6 = 3
            boolean r6 = android.util.Log.isLoggable(r7, r6)     // Catch: java.lang.Throwable -> L28
            if (r6 == 0) goto L43
            java.lang.String r6 = "Failed to encode data onto the OutputStream"
            android.util.Log.d(r7, r6, r5)     // Catch: java.lang.Throwable -> L28
        L43:
            if (r2 == 0) goto L48
            r2.close()     // Catch: java.io.IOException -> L48
        L48:
            r4.i(r0)
        L4b:
            return r1
        L4c:
            if (r2 == 0) goto L51
            r2.close()     // Catch: java.io.IOException -> L51
        L51:
            r4.i(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: e7.f.q(java.lang.Object, java.io.File, e4.h):boolean");
    }

    @Override // io.sentry.S
    public Future r(Runnable runnable, long j3) {
        return ((ScheduledExecutorService) this.f14480m).schedule(runnable, j3, TimeUnit.MILLISECONDS);
    }

    public void s(C1489n1 c1489n1, K k10, Map map) throws IOException {
        c1489n1.i();
        for (Object obj : map.keySet()) {
            if (obj instanceof String) {
                c1489n1.w((String) obj);
                o(c1489n1, k10, map.get(obj));
            }
        }
        c1489n1.n();
    }

    @Override // io.sentry.S
    public Future submit(Runnable runnable) {
        return ((ScheduledExecutorService) this.f14480m).submit(runnable);
    }

    public String toString() {
        switch (this.l) {
            case 7:
                StringBuilder sb = new StringBuilder();
                C1129p c1129p = (C1129p) this.f14480m;
                sb.append(c1129p);
                sb.append(": ");
                sb.append(((Map) C7.a.N(c1129p.f13617u, C1129p.f13614y[0])).keySet());
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public /* synthetic */ f(Object obj, int i7) {
        this.l = i7;
        this.f14480m = obj;
    }

    public f(C1129p c1129p) {
        this.l = 7;
        m.f(c1129p, "packageFragment");
        this.f14480m = c1129p;
    }

    public f(int i7, byte b10) {
        this.l = i7;
        switch (i7) {
            case 13:
                this.f14480m = Executors.newSingleThreadScheduledExecutor(new A(1));
                break;
            case 14:
                this.f14480m = new B(C1521w0.l);
                break;
            case 15:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            default:
                this.f14480m = new LinkedHashSet();
                break;
            case 16:
                Looper mainLooper = Looper.getMainLooper();
                m.e(mainLooper, "getMainLooper()");
                this.f14480m = new Handler(mainLooper);
                break;
            case 17:
                this.f14480m = new io.sentry.util.d(new C1350a(13));
                break;
            case 18:
                this.f14480m = new n();
                break;
            case 24:
                this.f14480m = new z(7);
                break;
            case 25:
                this.f14480m = new k4.n(500L);
                break;
        }
    }

    public f(Context context) {
        this.l = 8;
        this.f14480m = new P6.a(context, 3, false);
    }

    public f(int i7) {
        this.l = 10;
        C1.j jVar = new C1.j();
        jVar.f944m = new HashSet();
        jVar.l = i7;
        this.f14480m = jVar;
    }

    public f(Notification notification) {
        this.l = 22;
        notification.getClass();
        this.f14480m = notification;
    }

    public f(d9.a aVar) {
        this.l = 1;
        this.f14480m = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), aVar);
    }
}
