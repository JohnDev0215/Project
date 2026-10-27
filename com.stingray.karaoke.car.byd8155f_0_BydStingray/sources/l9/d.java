package l9;

import A7.D;
import B8.A;
import C0.K;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0141x;
import D.P;
import D.S;
import D.T;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import K.X;
import N0.L;
import N0.q;
import Q.AbstractC0524p;
import Q.c1;
import Q5.A0;
import Q5.C0545a;
import Q5.C0556c2;
import Q5.C0561e;
import Q5.C0607p1;
import Q5.C0616s;
import Q5.C0620t;
import Q5.E0;
import Q5.O;
import Q5.T0;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0653f;
import S0.G;
import T.C0735d;
import T.C0752l0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.InterfaceC0744h0;
import T.R0;
import T7.C0790i;
import T7.Q;
import U5.v;
import X5.AbstractC0851c;
import X5.C0847a;
import X5.D0;
import a.AbstractC0904a;
import a8.C0930a;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import c6.C1034a;
import c8.C1036a;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import d4.C1071d;
import d8.C1122i;
import e3.C1224g;
import g1.AbstractC1274a;
import g6.C1306d;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h3.y;
import h4.InterfaceC1359a;
import h5.C1378d;
import h6.r;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import k4.B;
import k4.C1683a;
import k4.x;
import k4.z;
import k6.AbstractC1692D;
import l3.C1764u;
import l4.C1771b;
import l5.C1778f;
import l8.C1789g;
import n0.C1886o;
import n0.C1891t;
import n4.C1953A;
import n4.C1955a;
import n4.C1956b;
import n4.C1957c;
import n4.p;
import n4.t;
import n7.AbstractC1996k;
import o8.C2061f;
import org.xmlpull.v1.XmlPullParserException;
import p4.C2102a;
import p4.C2104c;
import r4.C2288a;
import r4.C2290c;
import r4.C2296i;
import u2.I;
import w.AbstractC2565A;
import w.F;
import x.AbstractC2613B;
import x.AbstractC2614C;
import x.AbstractC2631e;
import x.AbstractC2641j;
import x.C0;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f18866a = 0;

    public static final void A(f9.a aVar, f9.b bVar, String str) {
        f9.c.f14652i.fine(bVar.f14648b + ' ' + String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1)) + ": " + aVar.f14645a);
    }

    public static final Object[] B(int i7, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 2];
        AbstractC1996k.G(0, i7, 6, objArr, objArr2);
        AbstractC1996k.D(i7, i7 + 2, objArr.length, objArr, objArr2);
        return objArr2;
    }

    public static final Object[] C(int i7, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 1];
        AbstractC1996k.G(0, i7, 6, objArr, objArr2);
        AbstractC1996k.D(i7, i7 + 1, objArr.length, objArr, objArr2);
        return objArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object D(A9.b r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof N3.a
            if (r0 == 0) goto L13
            r0 = r7
            N3.a r0 = (N3.a) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            N3.a r0 = new N3.a
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f4573n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            m7.q r3 = m7.q.f19267a
            r4 = 1
            if (r2 == 0) goto L37
            if (r2 != r4) goto L2f
            A7.z r6 = r0.f4572m
            A9.b r0 = r0.l
            h8.AbstractC1386c.u(r7)     // Catch: java.lang.Throwable -> L2d
            goto L71
        L2d:
            r7 = move-exception
            goto L80
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            h8.AbstractC1386c.u(r7)
            androidx.lifecycle.t r7 = r6.O0()
            androidx.lifecycle.t r2 = androidx.lifecycle.EnumC0981t.o
            int r7 = r7.compareTo(r2)
            if (r7 < 0) goto L47
            return r3
        L47:
            A7.z r7 = new A7.z
            r7.<init>()
            r0.l = r6     // Catch: java.lang.Throwable -> L7b
            r0.f4572m = r7     // Catch: java.lang.Throwable -> L7b
            r0.o = r4     // Catch: java.lang.Throwable -> L7b
            S8.i r2 = new S8.i     // Catch: java.lang.Throwable -> L7b
            q7.c r0 = h6.e.k(r0)     // Catch: java.lang.Throwable -> L7b
            r2.<init>(r4, r0)     // Catch: java.lang.Throwable -> L7b
            r2.s()     // Catch: java.lang.Throwable -> L7b
            N3.b r0 = new N3.b     // Catch: java.lang.Throwable -> L7b
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L7b
            r7.l = r0     // Catch: java.lang.Throwable -> L7b
            r6.M0(r0)     // Catch: java.lang.Throwable -> L7b
            java.lang.Object r0 = r2.q()     // Catch: java.lang.Throwable -> L7b
            if (r0 != r1) goto L6f
            return r1
        L6f:
            r0 = r6
            r6 = r7
        L71:
            java.lang.Object r6 = r6.l
            androidx.lifecycle.z r6 = (androidx.lifecycle.InterfaceC0987z) r6
            if (r6 == 0) goto L7a
            r0.V0(r6)
        L7a:
            return r3
        L7b:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
        L80:
            java.lang.Object r6 = r6.l
            androidx.lifecycle.z r6 = (androidx.lifecycle.InterfaceC0987z) r6
            if (r6 == 0) goto L89
            r0.V0(r6)
        L89:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.d.D(A9.b, s7.c):java.lang.Object");
    }

    public static void H(L7.e eVar, Object[] objArr) {
        A7.m.f(objArr, "args");
        if (l.M(eVar) == objArr.length) {
            return;
        }
        StringBuilder sb = new StringBuilder("Callable expects ");
        sb.append(l.M(eVar));
        sb.append(" arguments, but ");
        throw new IllegalArgumentException(M5.b.h(sb, objArr.length, " were provided."));
    }

    public static long I(long j3, long j10) {
        long j11 = j3 + j10;
        if (((j3 ^ j10) < 0) || ((j3 ^ j11) >= 0)) {
            return j11;
        }
        StringBuilder sb = new StringBuilder("overflow: checkedAdd(");
        sb.append(j3);
        sb.append(", ");
        throw new ArithmeticException(android.hardware.bydauto.ac.tsimpl.a.f(j10, ")", sb));
    }

    public static q J(q qVar, InterfaceC0653f interfaceC0653f, W7.n nVar, int i7) {
        if ((i7 & 2) != 0) {
            nVar = null;
        }
        A7.m.f(qVar, "<this>");
        return new q((C1036a) qVar.f4399m, nVar != null ? new W2.j(qVar, interfaceC0653f, nVar, 0) : (c8.e) qVar.f4400n, r.n(m7.g.f19257m, new A(qVar, 27, interfaceC0653f)));
    }

    public static final int K(int i7) {
        A7.k.p(i7, "<this>");
        int iC = AbstractC2641j.c(i7);
        if (iC == 0) {
            return 3;
        }
        if (iC == 1) {
            return 1;
        }
        if (iC == 2) {
            return 2;
        }
        throw new A9.k();
    }

    public static final q L(q qVar, R7.h hVar) {
        A7.m.f(qVar, "<this>");
        A7.m.f(hVar, "additionalAnnotations");
        if (hVar.isEmpty()) {
            return qVar;
        }
        return new q((C1036a) qVar.f4399m, (c8.e) qVar.f4400n, r.n(m7.g.f19257m, new A(qVar, 28, hVar)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static com.bumptech.glide.h M(com.bumptech.glide.b bVar, ArrayList arrayList) {
        e4.j fVar;
        e4.j c1955a;
        F.l lVar;
        InterfaceC1359a interfaceC1359a = bVar.l;
        com.bumptech.glide.e eVar = bVar.f12667n;
        Context applicationContext = eVar.getApplicationContext();
        S6.e eVar2 = eVar.f12681h;
        com.bumptech.glide.h hVar = new com.bumptech.glide.h();
        n4.m mVar = new n4.m();
        D d = hVar.f12691g;
        synchronized (d) {
            d.l.add(mVar);
        }
        t tVar = new t();
        D d10 = hVar.f12691g;
        synchronized (d10) {
            d10.l.add(tVar);
        }
        Resources resources = applicationContext.getResources();
        ArrayList arrayListE = hVar.e();
        F.l lVar2 = bVar.o;
        C2288a c2288a = new C2288a(applicationContext, arrayListE, interfaceC1359a, lVar2);
        e4.j d11 = new n4.D(interfaceC1359a, new C1886o(3));
        p pVar = new p(hVar.e(), resources.getDisplayMetrics(), interfaceC1359a, lVar2);
        if (eVar2.f8072a.containsKey(com.bumptech.glide.c.class)) {
            c1955a = new n4.g(1);
            fVar = new n4.g(0);
        } else {
            fVar = new n4.f(pVar, 0);
            c1955a = new C1955a(pVar, 2, lVar2);
        }
        hVar.d("Animation", InputStream.class, Drawable.class, new C2102a(new C1778f(arrayListE, 3, lVar2), 1));
        hVar.d("Animation", ByteBuffer.class, Drawable.class, new C2102a(new C1778f(arrayListE, 3, lVar2), 0));
        e4.j c2104c = new C2104c(applicationContext);
        e4.k c1956b = new C1956b(lVar2);
        s4.a jVar = new C1.j(13, false);
        s4.a cVar = new s4.c(1);
        ContentResolver contentResolver = applicationContext.getContentResolver();
        hVar.a(ByteBuffer.class, new z(5));
        hVar.a(InputStream.class, new e7.f(lVar2, 26));
        hVar.d("Bitmap", ByteBuffer.class, Bitmap.class, fVar);
        hVar.d("Bitmap", InputStream.class, Bitmap.class, c1955a);
        String str = Build.FINGERPRINT;
        if ("robolectric".equals(str)) {
            lVar = lVar2;
        } else {
            lVar = lVar2;
            hVar.d("Bitmap", ParcelFileDescriptor.class, Bitmap.class, new n4.f(pVar, 1));
        }
        hVar.d("Bitmap", AssetFileDescriptor.class, Bitmap.class, new n4.D(interfaceC1359a, new C1886o(2)));
        hVar.d("Bitmap", ParcelFileDescriptor.class, Bitmap.class, d11);
        k4.r rVar = z.f18224m;
        hVar.c(Bitmap.class, Bitmap.class, rVar);
        hVar.d("Bitmap", Bitmap.class, Bitmap.class, new C1953A(0));
        hVar.b(Bitmap.class, c1956b);
        hVar.d("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new C1955a(resources, fVar));
        hVar.d("BitmapDrawable", InputStream.class, BitmapDrawable.class, new C1955a(resources, c1955a));
        hVar.d("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new C1955a(resources, d11));
        hVar.b(BitmapDrawable.class, new C1789g(interfaceC1359a, c1956b));
        F.l lVar3 = lVar;
        hVar.d("Animation", InputStream.class, C2290c.class, new C2296i(arrayListE, c2288a, lVar3));
        hVar.d("Animation", ByteBuffer.class, C2290c.class, c2288a);
        hVar.b(C2290c.class, new m6.b());
        hVar.c(C1071d.class, C1071d.class, rVar);
        hVar.d("Bitmap", C1071d.class, Bitmap.class, new C1957c(interfaceC1359a));
        hVar.d("legacy_append", Uri.class, Drawable.class, c2104c);
        hVar.d("legacy_append", Uri.class, Bitmap.class, new C1955a(c2104c, 1, interfaceC1359a));
        hVar.h(new com.bumptech.glide.load.data.h(2));
        hVar.c(File.class, ByteBuffer.class, new z(6));
        hVar.c(File.class, InputStream.class, new k4.f(new z(9), 8));
        hVar.d("legacy_append", File.class, File.class, new C1953A(2));
        hVar.c(File.class, ParcelFileDescriptor.class, new k4.f(new z(8), 8));
        hVar.c(File.class, File.class, rVar);
        hVar.h(new com.bumptech.glide.load.data.m(lVar3));
        if (!"robolectric".equals(str)) {
            hVar.h(new com.bumptech.glide.load.data.h(1));
        }
        k4.r aVar = new P6.a(applicationContext, 6, false);
        k4.r aVar2 = new P6.a(applicationContext, 4, false);
        k4.r aVar3 = new P6.a(applicationContext, 5, false);
        Class cls = Integer.TYPE;
        hVar.c(cls, InputStream.class, aVar);
        hVar.c(Integer.class, InputStream.class, aVar);
        hVar.c(cls, AssetFileDescriptor.class, aVar2);
        hVar.c(Integer.class, AssetFileDescriptor.class, aVar2);
        hVar.c(cls, Drawable.class, aVar3);
        hVar.c(Integer.class, Drawable.class, aVar3);
        hVar.c(Uri.class, InputStream.class, new P6.a(applicationContext, 9, false));
        hVar.c(Uri.class, AssetFileDescriptor.class, new P6.a(applicationContext, 8, false));
        k4.r xVar = new x(resources, 2);
        k4.r xVar2 = new x(resources, 0);
        k4.r xVar3 = new x(resources, 1);
        hVar.c(Integer.class, Uri.class, xVar);
        hVar.c(cls, Uri.class, xVar);
        hVar.c(Integer.class, AssetFileDescriptor.class, xVar2);
        hVar.c(cls, AssetFileDescriptor.class, xVar2);
        hVar.c(Integer.class, InputStream.class, xVar3);
        hVar.c(cls, InputStream.class, xVar3);
        hVar.c(String.class, InputStream.class, new e7.f(24, (byte) 0));
        hVar.c(Uri.class, InputStream.class, new e7.f(24, (byte) 0));
        hVar.c(String.class, InputStream.class, new z(13));
        hVar.c(String.class, ParcelFileDescriptor.class, new z(12));
        hVar.c(String.class, AssetFileDescriptor.class, new z(11));
        hVar.c(Uri.class, InputStream.class, new C1683a(applicationContext.getAssets(), 1));
        hVar.c(Uri.class, AssetFileDescriptor.class, new C1683a(applicationContext.getAssets(), 0));
        hVar.c(Uri.class, InputStream.class, new P6.a(applicationContext, 10, 0 == true ? 1 : 0));
        hVar.c(Uri.class, InputStream.class, new P6.a(applicationContext, 11, 0 == true ? 1 : 0));
        hVar.c(Uri.class, InputStream.class, new C1771b(applicationContext, InputStream.class));
        hVar.c(Uri.class, ParcelFileDescriptor.class, new C1771b(applicationContext, ParcelFileDescriptor.class));
        hVar.c(Uri.class, InputStream.class, new B(contentResolver, 2));
        hVar.c(Uri.class, ParcelFileDescriptor.class, new B(contentResolver, 1));
        hVar.c(Uri.class, AssetFileDescriptor.class, new B(contentResolver, 0));
        hVar.c(Uri.class, InputStream.class, new z(14));
        hVar.c(URL.class, InputStream.class, new io.sentry.hints.i(26));
        hVar.c(Uri.class, File.class, new P6.a(applicationContext, 7, false));
        hVar.c(k4.g.class, InputStream.class, new C1764u(2));
        hVar.c(byte[].class, ByteBuffer.class, new z(2));
        hVar.c(byte[].class, InputStream.class, new z(4));
        hVar.c(Uri.class, Uri.class, rVar);
        hVar.c(Drawable.class, Drawable.class, rVar);
        hVar.d("legacy_append", Drawable.class, Drawable.class, new C1953A(1));
        hVar.i(Bitmap.class, BitmapDrawable.class, new x(resources, 4));
        hVar.i(Bitmap.class, byte[].class, jVar);
        hVar.i(Drawable.class, byte[].class, new y(interfaceC1359a, jVar, cVar, 23));
        hVar.i(C2290c.class, byte[].class, cVar);
        e4.j d12 = new n4.D(interfaceC1359a, new n(3));
        hVar.d("legacy_append", ByteBuffer.class, Bitmap.class, d12);
        hVar.d("legacy_append", ByteBuffer.class, BitmapDrawable.class, new C1955a(resources, d12));
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            return hVar;
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    public static long P(long j3, long j10, RoundingMode roundingMode) {
        roundingMode.getClass();
        long j11 = j3 / j10;
        long j12 = j3 - (j10 * j11);
        if (j12 == 0) {
            return j11;
        }
        int i7 = ((int) ((j3 ^ j10) >> 63)) | 1;
        switch (f5.d.f14600a[roundingMode.ordinal()]) {
            case 1:
                if (j12 == 0) {
                    return j11;
                }
                throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
            case 2:
                return j11;
            case 3:
                if (i7 >= 0) {
                    return j11;
                }
                break;
            case 4:
                break;
            case 5:
                if (i7 <= 0) {
                    return j11;
                }
                break;
            case 6:
            case 7:
            case 8:
                long jAbs = Math.abs(j12);
                long jAbs2 = jAbs - (Math.abs(j10) - jAbs);
                if (jAbs2 == 0) {
                    if (roundingMode != RoundingMode.HALF_UP && (roundingMode != RoundingMode.HALF_EVEN || (1 & j11) == 0)) {
                        return j11;
                    }
                } else if (jAbs2 <= 0) {
                    return j11;
                }
            default:
                throw new AssertionError();
        }
        return j11 + ((long) i7);
    }

    public static final long Q(long j3, boolean z6, int i7, float f10) {
        int iH = ((z6 || C7.a.F(i7, 2)) && a1.a.d(j3)) ? a1.a.h(j3) : Integer.MAX_VALUE;
        if (a1.a.j(j3) != iH) {
            iH = AbstractC2673a.v(X.n(f10), a1.a.j(j3), iH);
        }
        int iG = a1.a.g(j3);
        int iMin = Math.min(0, 262142);
        int iMin2 = iH == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(iH, 262142);
        int iJ = io.sentry.config.a.J(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
        return io.sentry.config.a.e(iMin, iMin2, Math.min(iJ, 0), iG != Integer.MAX_VALUE ? Math.min(iJ, iG) : Integer.MAX_VALUE);
    }

    public static final String R(long j3) {
        String strF;
        if (j3 <= -999500000) {
            strF = android.hardware.bydauto.ac.tsimpl.a.f((j3 - ((long) 500000000)) / ((long) 1000000000), " s ", new StringBuilder());
        } else if (j3 <= -999500) {
            strF = android.hardware.bydauto.ac.tsimpl.a.f((j3 - ((long) 500000)) / ((long) 1000000), " ms", new StringBuilder());
        } else if (j3 <= 0) {
            strF = android.hardware.bydauto.ac.tsimpl.a.f((j3 - ((long) 500)) / ((long) 1000), " µs", new StringBuilder());
        } else if (j3 < 999500) {
            strF = android.hardware.bydauto.ac.tsimpl.a.f((j3 + ((long) 500)) / ((long) 1000), " µs", new StringBuilder());
        } else if (j3 < 999500000) {
            strF = android.hardware.bydauto.ac.tsimpl.a.f((j3 + ((long) 500000)) / ((long) 1000000), " ms", new StringBuilder());
        } else {
            strF = android.hardware.bydauto.ac.tsimpl.a.f((j3 + ((long) 500000000)) / ((long) 1000000000), " s ", new StringBuilder());
        }
        return String.format("%6s", Arrays.copyOf(new Object[]{strF}, 1));
    }

    public static long U(long j3, long j10) {
        l.E("a", j3);
        l.E("b", j10);
        if (j3 == 0) {
            return j10;
        }
        if (j10 == 0) {
            return j3;
        }
        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j3);
        long jNumberOfTrailingZeros = j3 >> iNumberOfTrailingZeros;
        int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros(j10);
        long j11 = j10 >> iNumberOfTrailingZeros2;
        while (jNumberOfTrailingZeros != j11) {
            long j12 = jNumberOfTrailingZeros - j11;
            long j13 = (j12 >> 63) & j12;
            long j14 = (j12 - j13) - j13;
            j11 += j13;
            jNumberOfTrailingZeros = j14 >> Long.numberOfTrailingZeros(j14);
        }
        return jNumberOfTrailingZeros << Math.min(iNumberOfTrailingZeros, iNumberOfTrailingZeros2);
    }

    public static Q V(C2061f c2061f, InterfaceC0652e interfaceC0652e) {
        if (c2061f == null) {
            a(19);
            throw null;
        }
        if (interfaceC0652e == null) {
            a(20);
            throw null;
        }
        Collection collectionH = interfaceC0652e.H();
        if (collectionH.size() != 1) {
            return null;
        }
        for (Q q4 : ((C0790i) collectionH.iterator().next()).D0()) {
            if (q4.getName().equals(c2061f)) {
                return q4;
            }
        }
        return null;
    }

    public static final int W(int i7, int i10) {
        return (i7 >> i10) & 31;
    }

    public static boolean X() {
        return e.d;
    }

    public static /* synthetic */ void a(int i7) {
        String str = i7 != 18 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i7 != 18 ? 3 : 2];
        switch (i7) {
            case 1:
            case 7:
            case 13:
                objArr[0] = "membersFromSupertypes";
                break;
            case 2:
            case 8:
            case 14:
                objArr[0] = "membersFromCurrent";
                break;
            case 3:
            case 9:
            case 15:
                objArr[0] = "classDescriptor";
                break;
            case 4:
            case 10:
            case 16:
                objArr[0] = "errorReporter";
                break;
            case 5:
            case 11:
            case 17:
                objArr[0] = "overridingUtil";
                break;
            case 6:
            case 12:
            case 19:
            default:
                objArr[0] = "name";
                break;
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
                break;
            case 20:
                objArr[0] = "annotationClass";
                break;
        }
        if (i7 != 18) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
        } else {
            objArr[1] = "resolveOverrides";
        }
        switch (i7) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "resolveOverridesForStaticMembers";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "resolveOverrides";
                break;
            case 18:
                break;
            case 19:
            case 20:
                objArr[2] = "getAnnotationParameterByName";
                break;
            default:
                objArr[2] = "resolveOverridesForNonStaticMembers";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 == 18) {
            throw new IllegalStateException(str2);
        }
    }

    public static U1.b a0(MappedByteBuffer mappedByteBuffer) throws IOException {
        long j3;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
        int i7 = byteBufferDuplicate.getShort() & 65535;
        if (i7 > 100) {
            throw new IOException("Cannot read metadata.");
        }
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 6);
        int i10 = 0;
        while (true) {
            if (i10 >= i7) {
                j3 = -1;
                break;
            }
            int i11 = byteBufferDuplicate.getInt();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            j3 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            if (1835365473 == i11) {
                break;
            }
            i10++;
        }
        if (j3 != -1) {
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) (j3 - ((long) byteBufferDuplicate.position()))));
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 12);
            long j10 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            for (int i12 = 0; i12 < j10; i12++) {
                int i13 = byteBufferDuplicate.getInt();
                long j11 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
                byteBufferDuplicate.getInt();
                if (1164798569 == i13 || 1701669481 == i13) {
                    byteBufferDuplicate.position((int) (j11 + j3));
                    U1.b bVar = new U1.b();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    bVar.o = byteBufferDuplicate;
                    bVar.l = iPosition;
                    int i14 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    bVar.f8960m = i14;
                    bVar.f8961n = ((ByteBuffer) bVar.o).getShort(i14);
                    return bVar;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    public static final void b(int i7, C0761q c0761q, g0.o oVar, String str, String str2, String str3) throws XmlPullParserException, IOException {
        int i10;
        c0761q.S(1317332387);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            S sB = P.b(AbstractC0127i.f1076a, g0.b.f14670v, c0761q, 48);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.l lVar = g0.l.f14684a;
            AbstractC0120b.b(c0761q, T.b(lVar, AbstractC1692D.c((LayoutMode) c0761q.k(AbstractC1692D.f18238a)) ? 0.3f : 0.08f));
            h(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f), str3, c0761q, ((i10 >> 6) & 112) | 6);
            AbstractC0120b.b(c0761q, T.b(lVar, 0.08f));
            i(T.b(lVar, 1.4f), true, str == null ? "" : str, str2 == null ? "" : str2, c0761q, 48);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0847a(oVar, str, str2, str3, i7, 1);
        }
    }

    public static LinkedHashSet b0(C2061f c2061f, Collection collection, Collection collection2, InterfaceC0652e interfaceC0652e, B8.l lVar, r8.l lVar2, boolean z6) {
        if (c2061f == null) {
            a(12);
            throw null;
        }
        if (collection == null) {
            a(13);
            throw null;
        }
        if (interfaceC0652e == null) {
            a(15);
            throw null;
        }
        if (lVar == null) {
            a(16);
            throw null;
        }
        if (lVar2 == null) {
            a(17);
            throw null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        lVar2.h(c2061f, collection, collection2, interfaceC0652e, new C0930a(lVar, linkedHashSet, z6));
        return linkedHashSet;
    }

    public static final void c(int i7, C0761q c0761q, g0.o oVar, String str, String str2, String str3) throws XmlPullParserException, IOException {
        int i10;
        c0761q.S(1662947019);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str2) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str3) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(oVar) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.d, g0.b.f14673y, c0761q, 54);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.l lVar = g0.l.f14684a;
            m6.b bVar = m6.c.f19252a;
            g0.o oVarO = androidx.compose.foundation.layout.a.o(lVar, 0.0f, 0.0f, 0.0f, bVar.H(6, c0761q) * 2, 7);
            LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
            A7.m.f(layoutMode, "<this>");
            h(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(oVarO, (layoutMode == LayoutMode.SQUARE_COMPACT || layoutMode == LayoutMode.SQUARE_NORMAL || layoutMode == LayoutMode.SQUARE_ULTRA) ? 0.4f : 0.6f), 1.0f), str3, c0761q, (i10 >> 3) & 112);
            i(androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.c.d(lVar, 1.0f), bVar.F(c0761q), 0.0f, 2), false, str == null ? "" : str, str2 == null ? "" : str2, c0761q, 48);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0847a(str, str2, str3, oVar, i7);
        }
    }

    public static LinkedHashSet c0(C2061f c2061f, AbstractCollection abstractCollection, Collection collection, InterfaceC0652e interfaceC0652e, B8.l lVar, r8.l lVar2) {
        if (c2061f == null) {
            a(0);
            throw null;
        }
        if (interfaceC0652e == null) {
            a(3);
            throw null;
        }
        if (lVar == null) {
            a(4);
            throw null;
        }
        if (lVar2 != null) {
            return b0(c2061f, abstractCollection, collection, interfaceC0652e, lVar, lVar2, false);
        }
        a(5);
        throw null;
    }

    public static final void d(int i7, C0761q c0761q, g0.o oVar, String str, String str2, String str3) throws XmlPullParserException, IOException {
        int i10;
        A7.m.f(oVar, "modifier");
        c0761q.S(888599693);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar, 1.0f);
            b0.b bVar = AbstractC0851c.f10229a;
            w0.c.C(oVarC, str3, 0, c0761q, ((i10 >> 6) & 112) | 24966);
            g0.o oVarC2 = androidx.compose.foundation.layout.c.c(oVar, 1.0f);
            K kE = AbstractC0133o.e(g0.b.l, false);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarC2);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            androidx.compose.foundation.layout.b bVar2 = androidx.compose.foundation.layout.b.f11474a;
            C0773y c0773y = AbstractC1692D.f18238a;
            if (AbstractC1692D.b((LayoutMode) c0761q.k(c0773y))) {
                c0761q.Q(1366667847);
                c((i10 >> 3) & 1022, c0761q, bVar2.a(androidx.compose.foundation.layout.c.b(lVar, 0.65f), new g0.g(0.0f, -0.25f)), str, str2, str3);
                c0761q.p(false);
            } else {
                c0761q.Q(1367119703);
                m6.b bVar3 = m6.c.f19252a;
                bVar3.getClass();
                c0761q.Q(57186376);
                LayoutMode layoutMode = (LayoutMode) c0761q.k(c0773y);
                float f10 = 0.45f;
                if (layoutMode != LayoutMode.WIDE_ULTRA && layoutMode != LayoutMode.NARROW_NORMAL && layoutMode != LayoutMode.NARROW_ULTRA && layoutMode != LayoutMode.SQUARE_ULTRA) {
                    if (layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                        throw new A9.k();
                    }
                    if (!A7.k.t(c0761q, 398348334, bVar3, c0761q, false) && A7.k.r(c0761q, 398349423, bVar3, c0761q, false)) {
                        f10 = 0.4f;
                    }
                }
                c0761q.p(false);
                b(i10 & 8176, c0761q, bVar2.a(androidx.compose.foundation.layout.c.b(lVar, f10), new g0.g(0.0f, -0.15f)), str, str2, str3);
                c0761q.p(false);
            }
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0847a(oVar, str, str2, str3, i7, 0);
        }
    }

    public static LinkedHashSet d0(C2061f c2061f, Collection collection, AbstractCollection abstractCollection, C1122i c1122i, V7.e eVar, r8.l lVar) {
        if (c2061f == null) {
            a(6);
            throw null;
        }
        if (collection == null) {
            a(7);
            throw null;
        }
        if (c1122i == null) {
            a(9);
            throw null;
        }
        if (eVar == null) {
            a(10);
            throw null;
        }
        if (lVar != null) {
            return b0(c2061f, collection, abstractCollection, c1122i, eVar, lVar, true);
        }
        a(11);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0285  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void e(g0.o r28, java.lang.String r29, boolean r30, D.L r31, T.C0761q r32, int r33) {
        /*
            Method dump skipped, instruction units count: 774
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.d.e(g0.o, java.lang.String, boolean, D.L, T.q, int):void");
    }

    public static C1224g e0(C1224g c1224g, String[] strArr, Map map) {
        int i7 = 0;
        if (c1224g == null) {
            if (strArr == null) {
                return null;
            }
            if (strArr.length == 1) {
                return (C1224g) map.get(strArr[0]);
            }
            if (strArr.length > 1) {
                C1224g c1224g2 = new C1224g();
                int length = strArr.length;
                while (i7 < length) {
                    c1224g2.a((C1224g) map.get(strArr[i7]));
                    i7++;
                }
                return c1224g2;
            }
        } else {
            if (strArr != null && strArr.length == 1) {
                c1224g.a((C1224g) map.get(strArr[0]));
                return c1224g;
            }
            if (strArr != null && strArr.length > 1) {
                int length2 = strArr.length;
                while (i7 < length2) {
                    c1224g.a((C1224g) map.get(strArr[i7]));
                    i7++;
                }
            }
        }
        return c1224g;
    }

    public static final void f(C1034a c1034a, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(c1034a, "viewModel");
        c0761q.S(-823698953);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c1034a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(c1034a, null, false, null, 254), b0.g.b(2032510389, new I5.y(c1034a, 3), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 2, c1034a);
        }
    }

    public static long f0(long j3, long j10) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(~j10) + Long.numberOfLeadingZeros(j10) + Long.numberOfLeadingZeros(~j3) + Long.numberOfLeadingZeros(j3);
        if (iNumberOfLeadingZeros > 65) {
            return j3 * j10;
        }
        long j11 = ((j3 ^ j10) >>> 63) + Long.MAX_VALUE;
        if ((iNumberOfLeadingZeros < 64) || ((j10 == Long.MIN_VALUE) & (j3 < 0))) {
            return j11;
        }
        long j12 = j3 * j10;
        return (j3 == 0 || j12 / j3 == j10) ? j12 : j11;
    }

    public static a1.c g() {
        return new a1.c(1.0f, 1.0f);
    }

    public static final void g0(String str) {
        throw new IllegalArgumentException(str);
    }

    public static final void h(g0.o oVar, String str, C0761q c0761q, int i7) throws XmlPullParserException, IOException {
        int i10;
        c0761q.S(-385010554);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            com.bumptech.glide.c.k(oVar, str, false, true, false, null, c0761q, (i10 & 14) | 27648 | (i10 & 112), 36);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new T0(oVar, str, i7, 4);
        }
    }

    public static final void h0(String str) {
        throw new IllegalStateException(str);
    }

    public static final void i(g0.o oVar, boolean z6, String str, String str2, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1489062200);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(str2) ? 2048 : 1024;
        }
        int i11 = i10;
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.e eVar = z6 ? g0.b.f14672x : g0.b.f14673y;
            C0119a c0119a = AbstractC0127i.f1076a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(m6.c.f19252a.H(6, c0761q) / 2), eVar, c0761q, 0);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            int i13 = z6 ? 5 : 3;
            long j3 = l6.d.c(c0761q).f18696b;
            long jG = h6.f.g(FontSize.f13040H6, c0761q);
            R0 r02 = O5.c.f5157b;
            c1.b(str, null, j3, jG, null, null, null, 0L, null, new Y0.i(i13), 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, (i11 >> 6) & 14, 3120, 54770);
            c1.b(AbstractC2673a.X(R.string.Global_InTheStyleOf, c0761q), null, l6.d.c(c0761q).d, h6.f.g(FontSize.Subtitle1, c0761q), null, null, null, 0L, null, new Y0.i(i13), 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, 0, 3120, 54770);
            c1.b(str2, null, l6.d.c(c0761q).f18696b, h6.f.g(FontSize.f13041H7, c0761q), null, null, null, 0L, null, new Y0.i(i13), 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, (i11 >> 9) & 14, 3120, 54770);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Q5.r(oVar, z6, str, str2, i7);
        }
    }

    public static final void i0(String str) {
        throw new IllegalStateException(str);
    }

    public static G j(int i7, S0.A a10, int i10, int i11) {
        if ((i11 & 2) != 0) {
            a10 = S0.A.f7800s;
        }
        return new G(i7, a10, (i11 & 4) != 0 ? 0 : i10, new S0.z(new S0.y[0]), 0);
    }

    public static final Class j0(ClassLoader classLoader, String str) {
        A7.m.f(str, "fqName");
        try {
            return Class.forName(str, false, classLoader);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x019c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x021f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x034a A[PHI: r9
  0x034a: PHI (r9v53 D.g) = (r9v46 D.g), (r9v54 D.g) binds: [B:225:0x0348, B:221:0x0341] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0361 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:263:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void k(g0.o r43, E.D r44, D.L r45, boolean r46, boolean r47, A.r r48, boolean r49, int r50, g0.e r51, D.InterfaceC0125g r52, g0.f r53, D.InterfaceC0123e r54, z7.InterfaceC2782k r55, T.C0761q r56, int r57, int r58, int r59) {
        /*
            Method dump skipped, instruction units count: 1177
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.d.k(g0.o, E.D, D.L, boolean, boolean, A.r, boolean, int, g0.e, D.g, g0.f, D.e, z7.k, T.q, int, int, int):void");
    }

    public static final void l(String str, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(str, "id");
        A7.m.f(interfaceC2772a, "onTermsClicked");
        A7.m.f(interfaceC2772a2, "onPrivacyClicked");
        A7.m.f(interfaceC2772a3, "onAcceptClicked");
        c0761q.S(-1694750701);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a3) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC1274a.h(str, false, false, false, AbstractC1692D.b((LayoutMode) c0761q.k(AbstractC1692D.f18238a)), b0.g.b(-481994068, new S5.j(interfaceC2772a, interfaceC2772a2, interfaceC2772a3, str), c0761q), c0761q, (i10 & 14) | 199680, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(str, (Object) interfaceC2772a, (Object) interfaceC2772a2, interfaceC2772a3, i7, 5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0526  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0573  */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m(g0.o r52, final java.lang.String r53, final java.lang.String r54, final java.lang.String r55, final com.stingray.karaoke.car.player.PlayerStatus r56, final V8.N r57, final boolean r58, final boolean r59, final z7.InterfaceC2772a r60, final z7.InterfaceC2772a r61, final z7.InterfaceC2772a r62, final z7.InterfaceC2772a r63, final boolean r64, T.C0761q r65, final int r66, final int r67) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1581
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.d.m(g0.o, java.lang.String, java.lang.String, java.lang.String, com.stingray.karaoke.car.player.PlayerStatus, V8.N, boolean, boolean, z7.a, z7.a, z7.a, z7.a, boolean, T.q, int, int):void");
    }

    public static final void n(g0.o oVar, D0 d02, boolean z6, InterfaceC2772a interfaceC2772a, boolean z10, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(d02, "viewModel");
        A7.m.f(interfaceC2772a, "onMiniPlayerClicked");
        c0761q.S(-1528144794);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(d02) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z10) ? 16384 : 8192;
        }
        int i11 = i10;
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            T.X xR = com.bumptech.glide.c.R(d02.f10147q0, c0761q, 0);
            T.X xR2 = com.bumptech.glide.c.R(d02.f10145o0, c0761q, 0);
            T.X xR3 = com.bumptech.glide.c.R(d02.f10146p0, c0761q, 0);
            T.X xR4 = com.bumptech.glide.c.R(d02.f10130Z, c0761q, 0);
            T.X xR5 = com.bumptech.glide.c.R(d02.f10155v0, c0761q, 0);
            T.X xR6 = com.bumptech.glide.c.R(d02.f10157w0, c0761q, 0);
            T.X xR7 = com.bumptech.glide.c.R(d02.f10148r0, c0761q, 0);
            T.X xR8 = com.bumptech.glide.c.R(d02.f10133c0, c0761q, 0);
            T.X xR9 = com.bumptech.glide.c.R(d02.f10139i0, c0761q, 0);
            k6.t.f18296a.k(48, c0761q, !((Boolean) xR6.getValue()).booleanValue());
            boolean z11 = z10 && !((Boolean) xR6.getValue()).booleanValue();
            I i12 = AbstractC2614C.c;
            F fB = AbstractC2565A.b(AbstractC2631e.q(300, 0, i12, 2), 2);
            C0 c0Q = AbstractC2631e.q(300, 0, AbstractC2613B.f23388b, 2);
            c0761q.Q(1626542046);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = new A3.c(19);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            F fA = fB.a(AbstractC2565A.e(c0Q, (InterfaceC2782k) objG));
            w.G gC = AbstractC2565A.c(AbstractC2631e.q(300, 0, i12, 2), 2);
            C0 c0Q2 = AbstractC2631e.q(300, 0, AbstractC2613B.f23387a, 2);
            c0761q.Q(1626549886);
            Object objG2 = c0761q.G();
            if (objG2 == s6) {
                objG2 = new A3.c(20);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            androidx.compose.animation.a.c(z11, oVar, fA, gC.a(AbstractC2565A.f(c0Q2, (InterfaceC2782k) objG2)), null, b0.g.b(-1854783346, new E0(d02.f10106D0, z6, interfaceC2772a, d02, xR7, xR8, xR4, xR9, xR, xR2, xR3, xR5), c0761q), c0761q, ((i11 << 3) & 112) | 196608, 16);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new A0(oVar, d02, z6, interfaceC2772a, z10, i7);
        }
    }

    public static final void o(long j3, L l, InterfaceC2785n interfaceC2785n, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-716124955);
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
            C0773y c0773y = c1.f5850a;
            C0735d.b(new C0752l0[]{AbstractC0524p.f6016a.a(new C1891t(j3)), c0773y.a(((L) c0761q.k(c0773y)).e(l))}, interfaceC2785n, c0761q, ((i10 >> 3) & 112) | 8);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new R.F(j3, l, interfaceC2785n, i7, 0);
        }
    }

    public static final void p(g0.o oVar, String str, boolean z6, boolean z10, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1725519830);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z10) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            if (z6) {
                c0761q.Q(202199123);
                g0.f fVar = g0.b.f14670v;
                C0119a c0119a = AbstractC0127i.f1076a;
                S sB = P.b(new C0124f(m6.c.f19252a.F(c0761q)), fVar, c0761q, 48);
                int i11 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                g0.o oVarD = g0.a.d(c0761q, oVar);
                InterfaceC0161k.f1681b.getClass();
                C0159i c0159i = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(c0159i);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, sB, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                    AbstractC1347d.l(i11, c0761q, i11, c0158h);
                }
                C0735d.V(c0761q, oVarD, C0160j.c);
                C7.a.n(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, AbstractC1692D.a((LayoutMode) c0761q.k(AbstractC1692D.f18238a)) ? 0.7f : 0.85f), 1.0f), str, c0761q, i10 & 112);
                y(lVar, true, 5, c0761q, 54);
                c0761q.p(true);
                c0761q.p(false);
            } else {
                c0761q.Q(202854556);
                g0.e eVar = g0.b.f14673y;
                C0119a c0119a2 = AbstractC0127i.f1076a;
                m6.b bVar = m6.c.f19252a;
                C0141x c0141xA = AbstractC0139v.a(new C0124f(bVar.H(6, c0761q)), eVar, c0761q, 48);
                int i12 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
                g0.o oVarD2 = g0.a.d(c0761q, oVar);
                InterfaceC0161k.f1681b.getClass();
                C0159i c0159i2 = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(c0159i2);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, c0141xA, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M2, C0160j.d);
                C0158h c0158h2 = C0160j.f1680f;
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                    AbstractC1347d.l(i12, c0761q, i12, c0158h2);
                }
                C0735d.V(c0761q, oVarD2, C0160j.c);
                C7.a.n(h6.q.m(androidx.compose.foundation.layout.a.d(z10 ? androidx.compose.foundation.layout.c.b(lVar, 0.5f) : androidx.compose.foundation.layout.c.d(lVar, 0.5f), 1.0f), J.e.a(bVar.e0(c0761q))), str, c0761q, i10 & 112);
                y(lVar, true, 3, c0761q, 54);
                c0761q.p(true);
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0616s(oVar, str, z6, z10, i7);
        }
    }

    public static final void q(g0.o oVar, InterfaceC2782k interfaceC2782k, List list, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, D.L l, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1634756125);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(list) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2772a2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.f(l) ? 131072 : 65536;
        }
        if ((74899 & i10) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.c(oVar, 1.0f), l);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarJ);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, c0141xA, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.l lVar = g0.l.f14684a;
            float f10 = 10;
            g0.o oVarK = androidx.compose.foundation.layout.a.k(androidx.compose.foundation.layout.c.d(lVar, 1.0f), f10);
            g0.g gVar = g0.b.l;
            K kE = AbstractC0133o.e(gVar, false);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarK);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            androidx.compose.foundation.layout.b bVar = androidx.compose.foundation.layout.b.f11474a;
            g0.g gVar2 = g0.b.f14664p;
            io.sentry.config.a.p(bVar.a(lVar, gVar2), null, false, "Sing All", 0L, null, null, 0L, 0.0f, interfaceC2772a, c0761q, ((i10 << 18) & 1879048192) | 3072, 502);
            c0761q.p(true);
            g0.o oVarK2 = androidx.compose.foundation.layout.a.k(androidx.compose.foundation.layout.c.d(lVar, 1.0f), f10);
            K kE2 = AbstractC0133o.e(gVar, false);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarK2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            io.sentry.config.a.p(bVar.a(lVar, gVar2), null, false, "Refresh", 0L, null, null, 0L, 0.0f, interfaceC2772a2, c0761q, ((i10 << 15) & 1879048192) | 3072, 502);
            c0761q.p(true);
            E.D dA = E.G.a(0, 0, c0761q, 0, 3);
            g0.o oVarB = androidx.compose.foundation.layout.c.b(lVar, 1.0f);
            c0761q.Q(-1247956140);
            boolean zH = c0761q.h(list) | ((i10 & 112) == 32);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new O(list, interfaceC2782k);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            io.sentry.config.a.q(oVarB, dA, null, false, null, null, null, false, (InterfaceC2782k) objG, c0761q, 6, 252);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new v(oVar, interfaceC2782k, list, interfaceC2772a, interfaceC2772a2, l, i7, 0);
        }
    }

    public static final void r(C1306d c1306d, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1269912168);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c1306d) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(c1306d, null, false, null, 254), b0.g.b(1987474330, new I5.y(c1306d, 16), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 10, c1306d);
        }
    }

    public static final void s(g0.o oVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-2105228848);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            O.F f10 = O.F.f4897a;
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            int i12 = (((((i10 << 3) & 112) | (((i10 >> 3) & 14) | 384)) << 6) & 896) | 6;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, f10, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            A7.k.o((i12 >> 6) & 14, bVar, c0761q, true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new O.G(oVar, bVar, i7, 0);
        }
    }

    public static final void t(g0.o oVar, String str, String str2, int i7, C0761q c0761q, int i10) {
        int i11;
        c0761q.S(-1074231540);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.d(i7) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean zA = AbstractC1692D.a((LayoutMode) c0761q.k(AbstractC1692D.f18238a));
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.l lVar = g0.l.f14684a;
            g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            String str3 = str == null ? "" : str;
            long jG = h6.f.g(zA ? FontSize.H9 : FontSize.f13041H7, c0761q);
            long j3 = l6.d.c(c0761q).f18696b;
            R0 r02 = O5.c.f5157b;
            int i13 = ((i11 << 18) & 1879048192) | 48;
            c1.b(str3, oVarD2, j3, jG, null, null, null, 0L, null, new Y0.i(i7), 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r02)).f5160e, c0761q, i13, 3120, 54768);
            g0.o oVarD3 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            String strX = AbstractC2673a.X(R.string.Global_InTheStyleOf, c0761q);
            c1.b(strX, oVarD3, l6.d.c(c0761q).c, h6.f.g(zA ? FontSize.Body1 : FontSize.Subtitle1, c0761q), null, null, null, 0L, null, new Y0.i(i7), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).f5159b, c0761q, i13, 0, 65008);
            g0.o oVarD4 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            String str4 = str2 == null ? "" : str2;
            c1.b(str4, oVarD4, l6.d.c(c0761q).f18696b, h6.f.g(zA ? FontSize.Subtitle1 : FontSize.H9, c0761q), null, null, null, 0L, null, new Y0.i(i7), 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, i13, 3120, 54768);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0561e(oVar, str, str2, i7, i10);
        }
    }

    public static final void u(g0.o oVar, Integer num, Integer num2, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-515942731);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(num) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(num2) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            C0119a c0119a = AbstractC0127i.f1076a;
            C0124f c0124f = new C0124f(m6.c.f19252a.s0(c0761q));
            g0.f fVar = g0.b.f14669u;
            S sB = P.b(c0124f, fVar, c0761q, 0);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, sB, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            int iIntValue = (num != null ? num.intValue() : 0) / 60;
            int iIntValue2 = (num != null ? num.intValue() : 0) - (iIntValue * 60);
            C0124f c0124f2 = new C0124f(m6.b.t0(c0761q));
            g0.l lVar = g0.l.f14684a;
            S sB2 = P.b(c0124f2, fVar, c0761q, 0);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, lVar);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            w(null, AbstractC2673a.X(R.string.Global_Duration, c0761q), c0761q, 0);
            x(null, iIntValue + ":" + Q8.h.Q0(2, String.valueOf(iIntValue2)), c0761q, 0);
            c0761q.p(true);
            S sB3 = P.b(new C0124f(m6.b.t0(c0761q)), fVar, c0761q, 0);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, lVar);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB3, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            w(null, AbstractC2673a.X(R.string.Global_Year, c0761q), c0761q, 0);
            x(null, (num2 == null || num2.intValue() <= 0) ? "-" : num2.toString(), c0761q, 0);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0556c2(oVar, num, num2, i7, 0);
        }
    }

    public static final void v(g0.o oVar, Integer num, Integer num2, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-654186876);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(num) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(num2) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            C0119a c0119a = AbstractC0127i.f1076a;
            m6.b bVar = m6.c.f19252a;
            S sB = P.b(new C0124f(bVar.s0(c0761q)), g0.b.f14669u, c0761q, 0);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, sB, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            int iIntValue = (num != null ? num.intValue() : 0) / 60;
            int iIntValue2 = (num != null ? num.intValue() : 0) - (iIntValue * 60);
            g0.f fVar = g0.b.f14671w;
            float f10 = 2;
            C0124f c0124f = new C0124f(bVar.F(c0761q) / f10);
            g0.l lVar = g0.l.f14684a;
            S sB2 = P.b(c0124f, fVar, c0761q, 48);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, lVar);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            w(null, AbstractC2673a.X(R.string.Global_Duration, c0761q), c0761q, 0);
            x(null, iIntValue + ":" + Q8.h.Q0(2, String.valueOf(iIntValue2)), c0761q, 0);
            c0761q.p(true);
            S sB3 = P.b(new C0124f(bVar.F(c0761q) / f10), fVar, c0761q, 48);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, lVar);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB3, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            w(null, AbstractC2673a.X(R.string.Global_Year, c0761q), c0761q, 0);
            x(null, (num2 == null || num2.intValue() <= 0) ? "-" : num2.toString(), c0761q, 0);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0556c2(oVar, num, num2, i7, 1);
        }
    }

    public static final void w(g0.o oVar, String str, C0761q c0761q, int i7) {
        long jG;
        g0.o oVar2;
        c0761q.S(-89689852);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            if (AbstractC1692D.a((LayoutMode) c0761q.k(AbstractC1692D.f18238a))) {
                c0761q.Q(306155067);
                jG = h6.f.g(FontSize.Subtitle1, c0761q);
            } else {
                c0761q.Q(306156404);
                jG = h6.f.g(FontSize.H9, c0761q);
            }
            c0761q.p(false);
            c1.b(str, lVar, l6.d.c(c0761q).c, jG, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5159b, c0761q, ((i10 >> 3) & 14) | ((i10 << 3) & 112), 0, 65520);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new T0(oVar2, str, i7, 2);
        }
    }

    public static final void x(g0.o oVar, String str, C0761q c0761q, int i7) {
        long jG;
        g0.o oVar2;
        c0761q.S(-214961123);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            if (AbstractC1692D.a((LayoutMode) c0761q.k(AbstractC1692D.f18238a))) {
                c0761q.Q(-1635468236);
                jG = h6.f.g(FontSize.Subtitle1, c0761q);
            } else {
                c0761q.Q(-1635466899);
                jG = h6.f.g(FontSize.H9, c0761q);
            }
            c0761q.p(false);
            c1.b(str, lVar, l6.d.c(c0761q).f18696b, jG, null, null, null, 0L, null, new Y0.i(3), 0L, 2, false, 2, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5160e, c0761q, ((i10 >> 3) & 14) | ((i10 << 3) & 112), 3120, 54768);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new T0(oVar2, str, i7, 3);
        }
    }

    public static final void y(g0.o oVar, boolean z6, int i7, C0761q c0761q, int i10) {
        int i11;
        c0761q.S(1011656872);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            int i12 = z6 ? R.string.Companion_Title : R.string.Driving_FeatureDisabled;
            int i13 = z6 ? R.string.Companion_Subtitle : R.string.Driving_ScanWithoutQR;
            g0.o oVarP = androidx.compose.foundation.layout.a.p(oVar);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarP);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.l lVar = g0.l.f14684a;
            g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            String strX = AbstractC2673a.X(i12, c0761q);
            long j3 = l6.d.c(c0761q).f18702f;
            long jG = h6.f.g(FontSize.f13042H8, c0761q);
            R0 r02 = O5.c.f5157b;
            int i15 = ((i11 << 21) & 1879048192) | 48;
            c1.b(strX, oVarD2, j3, jG, null, null, null, 0L, null, new Y0.i(i7), 0L, 3, false, 2, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, i15, 3120, 54768);
            c1.b(AbstractC2673a.X(i13, c0761q), androidx.compose.foundation.layout.c.d(lVar, 1.0f), l6.d.c(c0761q).f18696b, h6.f.g(FontSize.H10, c0761q), null, null, null, 0L, null, new Y0.i(i7), 0L, 0, true, 0, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, i15, 384, 60912);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0620t(oVar, z6, i7, i10, 0);
        }
    }

    public static final Object[] z(int i7, Object obj, Object obj2, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length + 2];
        AbstractC1996k.G(0, i7, 6, objArr, objArr2);
        AbstractC1996k.D(i7 + 2, i7, objArr.length, objArr, objArr2);
        objArr2[i7] = obj;
        objArr2[i7 + 1] = obj2;
        return objArr2;
    }

    public abstract boolean E(h5.p pVar, C1378d c1378d, C1378d c1378d2);

    public abstract boolean F(h5.p pVar, Object obj, Object obj2);

    public abstract boolean G(h5.p pVar, h5.o oVar, h5.o oVar2);

    public e2.K N(K2.a aVar) {
        ByteBuffer byteBuffer = aVar.f18955p;
        byteBuffer.getClass();
        AbstractC1346c.c(byteBuffer.position() == 0 && byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0);
        return O(aVar, byteBuffer);
    }

    public abstract e2.K O(K2.a aVar, ByteBuffer byteBuffer);

    public abstract C1378d S(h5.p pVar);

    public abstract h5.o T(h5.p pVar);

    public abstract void Y(h5.o oVar, h5.o oVar2);

    public abstract void Z(h5.o oVar, Thread thread);
}
