package P8;

import B8.x;
import H4.u;
import H7.InterfaceC0317b;
import H7.InterfaceC0321f;
import H7.InterfaceC0322g;
import H7.w;
import K.C0329a0;
import K.Z;
import K7.A0;
import N0.K;
import Q.M0;
import Q.S0;
import Q.T;
import Q.X0;
import Q5.B;
import Q5.C0545a;
import Q5.C0565f;
import Q5.C0607p1;
import Q5.C0611q1;
import Q5.C0614r1;
import Q5.C0618s1;
import Q5.F0;
import Q5.N;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0666t;
import Q7.L;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.S;
import T.X;
import U5.v;
import Z7.AbstractC0901d;
import Z7.C0902e;
import Z7.C0903f;
import Z7.F;
import a.AbstractC0904a;
import a4.AbstractC0927h;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.inputmethod.ExtractedText;
import b4.C0995a;
import c6.C1034a;
import c9.y;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import com.stingray.karaoke.car.utils.FontSize;
import j8.EnumC1580z;
import j8.f0;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import n0.C1873b;
import n0.C1877f;
import n0.C1891t;
import n3.C1930E;
import n3.C1945j;
import o8.C2058c;
import o8.C2061f;
import p0.C2071b;
import r9.AbstractC2312b;
import r9.C2318h;
import r9.D;
import v8.AbstractC2564d;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class n implements H4.r, u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static C1877f f5477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static C1873b f5478b;
    public static C2071b c;
    public static final /* synthetic */ int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f5479e = 0;

    public static boolean A(R7.h hVar, C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        return hVar.e(c2058c) != null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0144, code lost:
    
        if (r6 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x014b, code lost:
    
        return !N7.i.y(r12);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean B(Q7.InterfaceC0652e r12, Q7.InterfaceC0650c r13) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P8.n.B(Q7.e, Q7.c):boolean");
    }

    public static final boolean C(InterfaceC0317b interfaceC0317b) {
        L7.e eVarI;
        L7.e eVarL;
        A7.m.f(interfaceC0317b, "<this>");
        if (interfaceC0317b instanceof H7.l) {
            w wVar = (w) interfaceC0317b;
            Field fieldD = com.bumptech.glide.d.D(wVar);
            if (!(fieldD != null ? fieldD.isAccessible() : true)) {
                return false;
            }
            Method methodE = com.bumptech.glide.d.E(wVar.c());
            if (!(methodE != null ? methodE.isAccessible() : true)) {
                return false;
            }
            Method methodE2 = com.bumptech.glide.d.E(((H7.l) interfaceC0317b).d());
            if (!(methodE2 != null ? methodE2.isAccessible() : true)) {
                return false;
            }
        } else if (interfaceC0317b instanceof w) {
            w wVar2 = (w) interfaceC0317b;
            Field fieldD2 = com.bumptech.glide.d.D(wVar2);
            if (!(fieldD2 != null ? fieldD2.isAccessible() : true)) {
                return false;
            }
            Method methodE3 = com.bumptech.glide.d.E(wVar2.c());
            if (!(methodE3 != null ? methodE3.isAccessible() : true)) {
                return false;
            }
        } else if (interfaceC0317b instanceof H7.p) {
            Field fieldD3 = com.bumptech.glide.d.D(((H7.p) interfaceC0317b).f());
            if (!(fieldD3 != null ? fieldD3.isAccessible() : true)) {
                return false;
            }
            Method methodE4 = com.bumptech.glide.d.E((InterfaceC0321f) interfaceC0317b);
            if (!(methodE4 != null ? methodE4.isAccessible() : true)) {
                return false;
            }
        } else if (interfaceC0317b instanceof InterfaceC0322g) {
            Field fieldD4 = com.bumptech.glide.d.D(((InterfaceC0322g) interfaceC0317b).f());
            if (!(fieldD4 != null ? fieldD4.isAccessible() : true)) {
                return false;
            }
            Method methodE5 = com.bumptech.glide.d.E((InterfaceC0321f) interfaceC0317b);
            if (!(methodE5 != null ? methodE5.isAccessible() : true)) {
                return false;
            }
        } else {
            if (!(interfaceC0317b instanceof InterfaceC0321f)) {
                throw new UnsupportedOperationException("Unknown callable: " + interfaceC0317b + " (" + interfaceC0317b.getClass() + ')');
            }
            InterfaceC0321f interfaceC0321f = (InterfaceC0321f) interfaceC0317b;
            Method methodE6 = com.bumptech.glide.d.E(interfaceC0321f);
            if (!(methodE6 != null ? methodE6.isAccessible() : true)) {
                return false;
            }
            K7.r rVarA = A0.a(interfaceC0317b);
            Object objS = (rVarA == null || (eVarL = rVarA.l()) == null) ? null : eVarL.s();
            AccessibleObject accessibleObject = objS instanceof AccessibleObject ? (AccessibleObject) objS : null;
            if (!(accessibleObject != null ? accessibleObject.isAccessible() : true)) {
                return false;
            }
            K7.r rVarA2 = A0.a(interfaceC0321f);
            Member memberS = (rVarA2 == null || (eVarI = rVarA2.i()) == null) ? null : eVarI.s();
            Constructor constructor = memberS instanceof Constructor ? (Constructor) memberS : null;
            if (!(constructor != null ? constructor.isAccessible() : true)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean D(H0.b r3, c9.H r4) {
        /*
            java.lang.String r0 = "request"
            A7.m.f(r3, r0)
            r0 = 200(0xc8, float:2.8E-43)
            r1 = 0
            int r2 = r4.o
            if (r2 == r0) goto L56
            r0 = 410(0x19a, float:5.75E-43)
            if (r2 == r0) goto L56
            r0 = 414(0x19e, float:5.8E-43)
            if (r2 == r0) goto L56
            r0 = 501(0x1f5, float:7.02E-43)
            if (r2 == r0) goto L56
            r0 = 203(0xcb, float:2.84E-43)
            if (r2 == r0) goto L56
            r0 = 204(0xcc, float:2.86E-43)
            if (r2 == r0) goto L56
            r0 = 307(0x133, float:4.3E-43)
            if (r2 == r0) goto L34
            r0 = 308(0x134, float:4.32E-43)
            if (r2 == r0) goto L56
            r0 = 404(0x194, float:5.66E-43)
            if (r2 == r0) goto L56
            r0 = 405(0x195, float:5.68E-43)
            if (r2 == r0) goto L56
            switch(r2) {
                case 300: goto L56;
                case 301: goto L56;
                case 302: goto L34;
                default: goto L33;
            }
        L33:
            return r1
        L34:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = c9.H.g(r4, r0)
            if (r0 != 0) goto L56
            c9.g r0 = r4.b()
            r2 = -1
            int r0 = r0.c
            if (r0 != r2) goto L56
            c9.g r0 = r4.b()
            boolean r0 = r0.f12562f
            if (r0 != 0) goto L56
            c9.g r0 = r4.b()
            boolean r0 = r0.f12561e
            if (r0 != 0) goto L56
            return r1
        L56:
            c9.g r4 = r4.b()
            boolean r4 = r4.f12560b
            if (r4 != 0) goto L67
            c9.g r3 = r3.m()
            boolean r3 = r3.f12560b
            if (r3 != 0) goto L67
            r1 = 1
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: P8.n.D(H0.b, c9.H):boolean");
    }

    public static boolean E(int i7, Rect rect, Rect rect2) {
        if (i7 == 17) {
            int i10 = rect.right;
            int i11 = rect2.right;
            return (i10 > i11 || rect.left >= i11) && rect.left > rect2.left;
        }
        if (i7 == 33) {
            int i12 = rect.bottom;
            int i13 = rect2.bottom;
            return (i12 > i13 || rect.top >= i13) && rect.top > rect2.top;
        }
        if (i7 == 66) {
            int i14 = rect.left;
            int i15 = rect2.left;
            return (i14 < i15 || rect.right <= i15) && rect.right < rect2.right;
        }
        if (i7 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i16 = rect.top;
        int i17 = rect2.top;
        return (i16 < i17 || rect.bottom <= i17) && rect.bottom < rect2.bottom;
    }

    public static final boolean F(L l) {
        A7.m.f(l, "<this>");
        return l.c() == null;
    }

    public static l G(InterfaceC2785n interfaceC2785n) {
        l lVar = new l();
        lVar.o = h6.e.g(lVar, lVar, interfaceC2785n);
        return lVar;
    }

    public static final O3.q H(Context context, S3.o oVar, final String str) {
        O3.q qVar;
        final int i7 = 0;
        final int i10 = 1;
        if (oVar == null) {
            throw new A9.k();
        }
        if (!A7.m.a(str, "__LottieInternalDefaultCacheKey__")) {
            oVar.getClass();
            HashMap map = O3.d.f5084a;
            final WeakReference weakReference = new WeakReference(context);
            final Context applicationContext = context.getApplicationContext();
            Callable callable = new Callable() { // from class: O3.b
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    o oVar2;
                    D dC;
                    Context context2 = (Context) weakReference.get();
                    if (context2 == null) {
                        context2 = applicationContext;
                    }
                    String str2 = str;
                    a aVarA = str2 == null ? null : U3.e.f9012b.a(str2);
                    if (aVarA != null) {
                        return new o(aVarA);
                    }
                    try {
                        dC = AbstractC2312b.c(AbstractC2312b.j(context2.getResources().openRawResource(R.raw.eq)));
                    } catch (Resources.NotFoundException e10) {
                        oVar2 = new o(e10);
                    }
                    if (d.c(dC, d.c).booleanValue()) {
                        ZipInputStream zipInputStream = new ZipInputStream(new C2318h(dC, 1));
                        try {
                            o oVarB = d.b(context2, zipInputStream, str2);
                            AbstractC0927h.b(zipInputStream);
                            return oVarB;
                        } catch (Throwable th) {
                            AbstractC0927h.b(zipInputStream);
                            throw th;
                        }
                    }
                    if (!d.c(dC, d.d).booleanValue()) {
                        String[] strArr = Z3.a.f10885p;
                        return d.a(new Z3.b(dC), str2, true);
                    }
                    try {
                        D dC2 = AbstractC2312b.c(AbstractC2312b.j(new GZIPInputStream(new C2318h(dC, 1))));
                        String[] strArr2 = Z3.a.f10885p;
                        return d.a(new Z3.b(dC2), str2, true);
                    } catch (IOException e11) {
                        oVar2 = new o(e11);
                    }
                    return oVar2;
                    oVar2 = new o(e10);
                    return oVar2;
                }
            };
            O3.a aVarA = str == null ? null : U3.e.f9012b.a(str);
            qVar = aVarA != null ? new O3.q(aVarA) : null;
            HashMap map2 = O3.d.f5084a;
            if (str != null && map2.containsKey(str)) {
                qVar = (O3.q) map2.get(str);
            }
            if (qVar != null) {
                return qVar;
            }
            O3.q qVar2 = new O3.q(callable);
            if (str != null) {
                final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                qVar2.b(new O3.n() { // from class: O3.c
                    @Override // O3.n
                    public final void a(Object obj) {
                        switch (i7) {
                            case 0:
                                HashMap map3 = d.f5084a;
                                map3.remove(str);
                                atomicBoolean.set(true);
                                if (map3.size() == 0) {
                                    d.d();
                                }
                                break;
                            default:
                                HashMap map4 = d.f5084a;
                                map4.remove(str);
                                atomicBoolean.set(true);
                                if (map4.size() == 0) {
                                    d.d();
                                }
                                break;
                        }
                    }
                });
                qVar2.a(new O3.n() { // from class: O3.c
                    @Override // O3.n
                    public final void a(Object obj) {
                        switch (i10) {
                            case 0:
                                HashMap map3 = d.f5084a;
                                map3.remove(str);
                                atomicBoolean.set(true);
                                if (map3.size() == 0) {
                                    d.d();
                                }
                                break;
                            default:
                                HashMap map4 = d.f5084a;
                                map4.remove(str);
                                atomicBoolean.set(true);
                                if (map4.size() == 0) {
                                    d.d();
                                }
                                break;
                        }
                    }
                });
                if (!atomicBoolean.get()) {
                    map2.put(str, qVar2);
                    if (map2.size() == 1) {
                        O3.d.d();
                    }
                }
            }
            return qVar2;
        }
        oVar.getClass();
        HashMap map3 = O3.d.f5084a;
        StringBuilder sb = new StringBuilder("rawRes");
        sb.append((context.getResources().getConfiguration().uiMode & 48) == 32 ? "_night_" : "_day_");
        sb.append(R.raw.eq);
        final String string = sb.toString();
        final WeakReference weakReference2 = new WeakReference(context);
        final Context applicationContext2 = context.getApplicationContext();
        Callable callable2 = new Callable() { // from class: O3.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                o oVar2;
                D dC;
                Context context2 = (Context) weakReference2.get();
                if (context2 == null) {
                    context2 = applicationContext2;
                }
                String str2 = string;
                a aVarA2 = str2 == null ? null : U3.e.f9012b.a(str2);
                if (aVarA2 != null) {
                    return new o(aVarA2);
                }
                try {
                    dC = AbstractC2312b.c(AbstractC2312b.j(context2.getResources().openRawResource(R.raw.eq)));
                } catch (Resources.NotFoundException e10) {
                    oVar2 = new o(e10);
                }
                if (d.c(dC, d.c).booleanValue()) {
                    ZipInputStream zipInputStream = new ZipInputStream(new C2318h(dC, 1));
                    try {
                        o oVarB = d.b(context2, zipInputStream, str2);
                        AbstractC0927h.b(zipInputStream);
                        return oVarB;
                    } catch (Throwable th) {
                        AbstractC0927h.b(zipInputStream);
                        throw th;
                    }
                }
                if (!d.c(dC, d.d).booleanValue()) {
                    String[] strArr = Z3.a.f10885p;
                    return d.a(new Z3.b(dC), str2, true);
                }
                try {
                    D dC2 = AbstractC2312b.c(AbstractC2312b.j(new GZIPInputStream(new C2318h(dC, 1))));
                    String[] strArr2 = Z3.a.f10885p;
                    return d.a(new Z3.b(dC2), str2, true);
                } catch (IOException e11) {
                    oVar2 = new o(e11);
                }
                return oVar2;
                oVar2 = new o(e10);
                return oVar2;
            }
        };
        O3.a aVarA2 = string == null ? null : U3.e.f9012b.a(string);
        qVar = aVarA2 != null ? new O3.q(aVarA2) : null;
        HashMap map4 = O3.d.f5084a;
        if (string != null && map4.containsKey(string)) {
            qVar = (O3.q) map4.get(string);
        }
        if (qVar != null) {
            return qVar;
        }
        O3.q qVar3 = new O3.q(callable2);
        if (string == null) {
            return qVar3;
        }
        final AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
        qVar3.b(new O3.n() { // from class: O3.c
            @Override // O3.n
            public final void a(Object obj) {
                switch (i7) {
                    case 0:
                        HashMap map32 = d.f5084a;
                        map32.remove(string);
                        atomicBoolean2.set(true);
                        if (map32.size() == 0) {
                            d.d();
                        }
                        break;
                    default:
                        HashMap map42 = d.f5084a;
                        map42.remove(string);
                        atomicBoolean2.set(true);
                        if (map42.size() == 0) {
                            d.d();
                        }
                        break;
                }
            }
        });
        qVar3.a(new O3.n() { // from class: O3.c
            @Override // O3.n
            public final void a(Object obj) {
                switch (i10) {
                    case 0:
                        HashMap map32 = d.f5084a;
                        map32.remove(string);
                        atomicBoolean2.set(true);
                        if (map32.size() == 0) {
                            d.d();
                        }
                        break;
                    default:
                        HashMap map42 = d.f5084a;
                        map42.remove(string);
                        atomicBoolean2.set(true);
                        if (map42.size() == 0) {
                            d.d();
                        }
                        break;
                }
            }
        });
        if (atomicBoolean2.get()) {
            return qVar3;
        }
        map4.put(string, qVar3);
        if (map4.size() != 1) {
            return qVar3;
        }
        O3.d.d();
        return qVar3;
    }

    public static int I(int i7, Rect rect, Rect rect2) {
        int i10;
        int i11;
        if (i7 == 17) {
            i10 = rect.left;
            i11 = rect2.right;
        } else if (i7 == 33) {
            i10 = rect.top;
            i11 = rect2.bottom;
        } else if (i7 == 66) {
            i10 = rect2.left;
            i11 = rect.right;
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i10 = rect2.top;
            i11 = rect.bottom;
        }
        return Math.max(0, i10 - i11);
    }

    public static final int J(EnumC1580z enumC1580z) {
        int i7 = enumC1580z == null ? -1 : x.f818a[enumC1580z.ordinal()];
        if (i7 == 1) {
            return 1;
        }
        int i10 = 2;
        if (i7 != 2) {
            i10 = 3;
            if (i7 != 3) {
                i10 = 4;
                if (i7 != 4) {
                    return 1;
                }
            }
        }
        return i10;
    }

    public static int K(int i7, Rect rect, Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs(((rect.width() / 2) + rect.left) - ((rect2.width() / 2) + rect2.left));
        }
        return Math.abs(((rect.height() / 2) + rect.top) - ((rect2.height() / 2) + rect2.top));
    }

    public static y L(String str) {
        A7.m.f(str, "<this>");
        try {
            return w(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static V3.a M(Z3.b bVar, O3.a aVar) {
        return new V3.a(0, Y3.p.a(bVar, aVar, 1.0f, Y3.f.f10670m, false));
    }

    public static V3.b N(Z3.a aVar, O3.a aVar2, boolean z6) {
        return new V3.b(Y3.p.a(aVar, aVar2, z6 ? AbstractC0927h.c() : 1.0f, Y3.f.f10671n, false), 3);
    }

    public static V3.a O(Z3.b bVar, O3.a aVar, int i7) {
        L8.p pVar = new L8.p();
        pVar.l = i7;
        ArrayList arrayListA = Y3.p.a(bVar, aVar, 1.0f, pVar, false);
        for (int i10 = 0; i10 < arrayListA.size(); i10++) {
            C0995a c0995a = (C0995a) arrayListA.get(i10);
            W3.c cVar = (W3.c) c0995a.f12212b;
            W3.c cVar2 = (W3.c) c0995a.c;
            if (cVar != null && cVar2 != null) {
                float[] fArr = cVar.f9760a;
                int length = fArr.length;
                float[] fArr2 = cVar2.f9760a;
                if (length != fArr2.length) {
                    int length2 = fArr.length + fArr2.length;
                    float[] fArr3 = new float[length2];
                    System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
                    System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
                    Arrays.sort(fArr3);
                    float f10 = Float.NaN;
                    int i11 = 0;
                    for (int i12 = 0; i12 < length2; i12++) {
                        float f11 = fArr3[i12];
                        if (f11 != f10) {
                            fArr3[i11] = f11;
                            i11++;
                            f10 = fArr3[i12];
                        }
                    }
                    float[] fArrCopyOfRange = Arrays.copyOfRange(fArr3, 0, i11);
                    c0995a = new C0995a(cVar.b(fArrCopyOfRange), cVar2.b(fArrCopyOfRange));
                }
            }
            arrayListA.set(i10, c0995a);
        }
        return new V3.a(1, arrayListA);
    }

    public static V3.a P(Z3.a aVar, O3.a aVar2) {
        return new V3.a(2, Y3.p.a(aVar, aVar2, 1.0f, Y3.f.o, false));
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList Q(h2.t r28) {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P8.n.Q(h2.t):java.util.ArrayList");
    }

    public static V3.a R(Z3.b bVar, O3.a aVar) {
        return new V3.a(3, Y3.p.a(bVar, aVar, AbstractC0927h.c(), Y3.f.f10673q, true));
    }

    public static TypedValue S(Context context, int i7) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i7, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 N0.L, still in use, count: 2, list:
          (r2v0 N0.L) from 0x00d0: MOVE (r25v0 N0.L) = (r2v0 N0.L)
          (r2v0 N0.L) from 0x007f: MOVE (r25v2 N0.L) = (r2v0 N0.L)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static final N0.L T(N0.L r26, a1.k r27) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P8.n.T(N0.L, a1.k):N0.L");
    }

    public static int U(int i7, Context context, String str) {
        TypedValue typedValueS = S(context, i7);
        if (typedValueS != null) {
            return typedValueS.data;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i7)));
    }

    public static final ExtractedText V(T0.y yVar) {
        ExtractedText extractedText = new ExtractedText();
        String str = yVar.f8481a.l;
        extractedText.text = str;
        extractedText.startOffset = 0;
        extractedText.partialEndOffset = str.length();
        extractedText.partialStartOffset = -1;
        long j3 = yVar.f8482b;
        extractedText.selectionStart = K.e(j3);
        extractedText.selectionEnd = K.d(j3);
        extractedText.flags = !Q8.h.D0(yVar.f8481a.l, '\n') ? 1 : 0;
        return extractedText;
    }

    public static Bundle W(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(n.class.getClassLoader());
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            h6.n.j("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public static final void b(g0.o oVar, String str, D.L l, C0761q c0761q, int i7) {
        g0.o oVar2;
        c0761q.S(274759410);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarD = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar, 0.8f);
            long j3 = l6.d.c(c0761q).f18696b;
            long jG = h6.f.g(FontSize.Subtitle1, c0761q);
            long j10 = l6.d.c(c0761q).f18707h0;
            long j11 = l6.d.c(c0761q).f18709i0;
            m6.b bVar = m6.c.f19252a;
            bVar.getClass();
            c0761q.Q(922098432);
            float fO = bVar.O(6, c0761q) * 0.25f;
            c0761q.p(false);
            w0.c.k(oVarD, oVarC, str, j3, jG, 0, 0, null, null, false, null, new K6.n(j10, j11, fO, bVar.P(c0761q)), new D.L(bVar.O(6, c0761q), AbstractC2673a.K(l, c0761q), bVar.O(6, c0761q) * 0.5f, AbstractC2673a.H(l, c0761q)), c0761q, ((i10 << 3) & 896) | 48, 0, 2016);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new N(oVar2, str, l, i7, 1);
        }
    }

    public static final void c(h6.h hVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1411804518);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(hVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else if (((Boolean) com.bumptech.glide.c.R(hVar.l.f18230b, c0761q, 0).getValue()).booleanValue()) {
            c0761q.Q(83284944);
            AbstractC0904a.l(null, new C0607p1(hVar, null, false, null, 254), B.f6259a, c0761q, 384, 1);
            c0761q.p(false);
        } else {
            c0761q.Q(83599594);
            AbstractC0904a.l(null, new C0607p1(hVar, AbstractC2673a.X(R.string.Global_Faq, c0761q), false, null, 250), b0.g.b(1483073864, new I5.y(hVar, 5), c0761q), c0761q, 384, 1);
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 4, hVar);
        }
    }

    public static final long d(int i7) {
        if (i7 > 0) {
            return i7;
        }
        throw new IllegalArgumentException("The span value should be higher than 0");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final void e(f6.h hVar, f6.g gVar, e6.h hVar2, C1034a c1034a, f6.o oVar, C0761q c0761q, int i7) {
        String strB1;
        n3.y yVar;
        A7.m.f(hVar, "mainViewModel");
        A7.m.f(gVar, "homeViewModel");
        A7.m.f(hVar2, "exploreViewModel");
        A7.m.f(c1034a, "companionViewModel");
        A7.m.f(oVar, "myKaraokeViewModel");
        c0761q.S(-1107448921);
        int i10 = (i7 & 6) == 0 ? (c0761q.h(hVar) ? 4 : 2) | i7 : i7;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(gVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(hVar2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(c1034a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(oVar) ? 16384 : 8192;
        }
        int i11 = i10;
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            NavigationType navigationType = NavigationType.TAB;
            W5.e eVar = hVar.l;
            C1930E c1930eB = eVar.b(navigationType);
            X xW = C0735d.w(c1930eB.f19799D, null, null, c0761q, 48, 2);
            W5.r rVar = StingrayKaraokeTabScreen.Companion;
            C1945j c1945j = (C1945j) xW.getValue();
            StingrayKaraokeTabScreen stingrayKaraokeTabScreen = null;
            String str = (c1945j == null || (yVar = c1945j.f19865m) == null) ? null : yVar.f19918r;
            rVar.getClass();
            if (str != null) {
                String strB12 = Q8.h.b1(str, "/", str);
                strB1 = Q8.h.b1(strB12, "?", strB12);
            } else {
                strB1 = null;
            }
            if (strB1 != null) {
                switch (strB1.hashCode()) {
                    case -1642127065:
                        if (strB1.equals("MyKaraokePage")) {
                            stingrayKaraokeTabScreen = StingrayKaraokeTabScreen.MyKaraokePage;
                        }
                        break;
                    case -420727794:
                        if (strB1.equals("Homepage")) {
                            stingrayKaraokeTabScreen = StingrayKaraokeTabScreen.Homepage;
                        }
                        break;
                    case 219281314:
                        if (strB1.equals("ExplorePage")) {
                            stingrayKaraokeTabScreen = StingrayKaraokeTabScreen.ExplorePage;
                        }
                        break;
                    case 1317962651:
                        if (strB1.equals("CompanionPage")) {
                            stingrayKaraokeTabScreen = StingrayKaraokeTabScreen.CompanionPage;
                        }
                        break;
                }
            }
            if (stingrayKaraokeTabScreen != null) {
                eVar.j(stingrayKaraokeTabScreen);
            }
            h(c1930eB, eVar.a(), gVar, hVar2, c1034a, oVar, c0761q, (i11 << 3) & 524160);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W5.j(hVar, gVar, hVar2, c1034a, oVar, i7, 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:221:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void f(final java.lang.String r77, final g0.o r78, long r79, n0.AbstractC1887p r81, long r82, n0.AbstractC1887p r84, n0.C1891t r85, float r86, final long r87, S0.w r89, S0.A r90, S0.AbstractC0703o r91, long r92, Y0.j r94, final Y0.i r95, long r96, int r98, boolean r99, int r100, int r101, z7.InterfaceC2782k r102, final N0.L r103, p0.h r104, T.C0761q r105, final int r106, final int r107, final int r108, final int r109) {
        /*
            Method dump skipped, instruction units count: 1337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P8.n.f(java.lang.String, g0.o, long, n0.p, long, n0.p, n0.t, float, long, S0.w, S0.A, S0.o, long, Y0.j, Y0.i, long, int, boolean, int, int, z7.k, N0.L, p0.h, T.q, int, int, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static final void g(g0.o oVar, String str, String str2, T0.o oVar2, InterfaceC2782k interfaceC2782k, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z6, C0761q c0761q, int i7) {
        int i10;
        boolean z10;
        Object obj;
        ?? r22;
        Object obj2;
        T0.o oVar3;
        int i11 = 1;
        A7.m.f(str, "searchText");
        A7.m.f(str2, "hintText");
        A7.m.f(interfaceC2782k, "onValueChange");
        A7.m.f(interfaceC2772a, "onSubmit");
        A7.m.f(interfaceC2772a2, "onClear");
        c0761q.S(-1666756761);
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
        int i12 = i10 | 3072;
        if ((i7 & 24576) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2772a2) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i12 |= c0761q.g(z6) ? 8388608 : 4194304;
        }
        int i13 = i12;
        if ((i13 & 4793491) == 4793490 && c0761q.x()) {
            c0761q.K();
            oVar3 = oVar2;
        } else {
            g0.o oVarG = androidx.compose.foundation.layout.a.g(androidx.compose.foundation.layout.c.d(oVar, 1.0f), 2);
            c0761q.Q(1540986247);
            int i14 = i13 & 458752;
            boolean z11 = i14 == 131072;
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            Object obj3 = objG;
            if (z11 || objG == s6) {
                N7.g gVar = new N7.g(interfaceC2772a, i11);
                c0761q.a0(gVar);
                obj3 = gVar;
            }
            c0761q.p(false);
            g0.o oVarM = h6.q.m(androidx.compose.ui.input.key.a.a(oVarG, (InterfaceC2782k) obj3), J.e.a(m6.c.f19252a.f0(c0761q)));
            N0.L lB = N0.L.b(((O5.d) c0761q.k(O5.c.f5157b)).d, 0L, h6.f.g(FontSize.H10, c0761q), null, null, 0L, null, 0L, null, null, null, 16777213);
            C0329a0 c0329a0 = new C0329a0(Boolean.FALSE, i11, 3, 113);
            c0761q.Q(1541027727);
            boolean z12 = i14 == 131072;
            Object objG2 = c0761q.G();
            if (z12 || objG2 == s6) {
                z10 = false;
                C0611q1 c0611q1 = new C0611q1(interfaceC2772a, false ? 1 : 0);
                c0761q.a0(c0611q1);
                obj = c0611q1;
            } else {
                z10 = false;
                obj = objG2;
            }
            c0761q.p(z10);
            Z z13 = new Z(47, (InterfaceC2782k) obj);
            J.d dVar = ((Q.S) c0761q.k(T.f5665a)).c;
            S0 s02 = S0.f5662a;
            long j3 = C1891t.f19451j;
            long j10 = l6.d.c(c0761q).f18710j;
            long j11 = l6.d.c(c0761q).f18710j;
            long j12 = l6.d.c(c0761q).f18710j;
            long j13 = l6.d.c(c0761q).f18710j;
            long j14 = l6.d.c(c0761q).f18696b;
            long j15 = l6.d.c(c0761q).f18696b;
            long j16 = l6.d.c(c0761q).f18696b;
            M0 m0C = S0.c(l6.d.c(c0761q).f18696b, j16, j14, j15, j13, j12, j10, j11, l6.d.c(c0761q).f18696b, j3, j3, j3, j3, l6.d.c(c0761q).d, l6.d.c(c0761q).d, l6.d.c(c0761q).d, l6.d.c(c0761q).d, c0761q, 134186496);
            c0761q.Q(1541007023);
            boolean z14 = (i13 & 57344) == 16384;
            Object objG3 = c0761q.G();
            if (z14 || objG3 == s6) {
                r22 = 0;
                C0614r1 c0614r1 = new C0614r1(false ? 1 : 0, interfaceC2782k);
                c0761q.a0(c0614r1);
                obj2 = c0614r1;
            } else {
                r22 = 0;
                obj2 = objG3;
            }
            c0761q.p(r22);
            X0.a(str, (InterfaceC2782k) obj2, oVarM, z6, false, lB, null, b0.g.b(925433292, new C0618s1(str2, r22), c0761q), null, b0.g.b(1574026062, new I5.u(str, 3, interfaceC2772a2), c0761q), null, null, null, false, null, c0329a0, z13, true, 0, 0, null, dVar, m0C, c0761q, ((i13 >> 3) & 14) | 817889280 | ((i13 >> 12) & 7168), 12582912, 0, 1867088);
            oVar3 = null;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0565f(oVar, str, str2, oVar3, interfaceC2782k, interfaceC2772a, interfaceC2772a2, z6, i7);
        }
    }

    public static final void h(C1930E c1930e, StingrayKaraokeTabScreen stingrayKaraokeTabScreen, f6.g gVar, e6.h hVar, C1034a c1034a, f6.o oVar, C0761q c0761q, int i7) {
        int i10;
        boolean z6;
        c0761q.S(278315534);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c1930e) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(stingrayKaraokeTabScreen) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(gVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(hVar) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(c1034a) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(oVar) ? 131072 : 65536;
        }
        int i11 = i10;
        if ((74899 & i11) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            String strName = stingrayKaraokeTabScreen.name();
            c0761q.Q(665424426);
            boolean zH = c0761q.h(gVar) | ((i11 & 7168) == 2048) | c0761q.h(c1930e) | c0761q.h(c1034a) | c0761q.h(oVar);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                z6 = false;
                F0 f02 = new F0(gVar, hVar, c1930e, c1034a, oVar, 3);
                c0761q.a0(f02);
                objG = f02;
            } else {
                z6 = false;
            }
            c0761q.p(z6);
            h6.q.b(c1930e, strName, null, null, null, null, null, null, null, null, (InterfaceC2782k) objG, c0761q, i11 & 14, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new v(c1930e, stingrayKaraokeTabScreen, gVar, hVar, c1034a, oVar, i7, 1);
        }
    }

    public static final long i(float f10, float f11) {
        return (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object j(android.content.Context r15, S3.o r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, s7.AbstractC2410c r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P8.n.j(android.content.Context, S3.o, java.lang.String, java.lang.String, java.lang.String, java.lang.String, s7.c):java.lang.Object");
    }

    public static final ExtractedText k(T0.y yVar) {
        ExtractedText extractedText = new ExtractedText();
        String str = yVar.f8481a.l;
        extractedText.text = str;
        extractedText.startOffset = 0;
        extractedText.partialEndOffset = str.length();
        extractedText.partialStartOffset = -1;
        long j3 = yVar.f8482b;
        extractedText.selectionStart = K.e(j3);
        extractedText.selectionEnd = K.d(j3);
        extractedText.flags = !Q8.h.D0(yVar.f8481a.l, '\n') ? 1 : 0;
        return extractedText;
    }

    public static String l(String str, int i7, int i10) {
        if (i7 < 0) {
            return com.bumptech.glide.c.r0("%s (%s) must not be negative", str, Integer.valueOf(i7));
        }
        if (i10 >= 0) {
            return com.bumptech.glide.c.r0("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i7), Integer.valueOf(i10));
        }
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i10, "negative size: "));
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m(int r9, android.graphics.Rect r10, android.graphics.Rect r11, android.graphics.Rect r12) {
        /*
            boolean r0 = n(r9, r10, r11)
            boolean r1 = n(r9, r10, r12)
            r2 = 0
            if (r1 != 0) goto L78
            if (r0 != 0) goto Lf
            goto L78
        Lf:
            java.lang.String r0 = "direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."
            r1 = 130(0x82, float:1.82E-43)
            r3 = 33
            r4 = 66
            r5 = 17
            r6 = 1
            if (r9 == r5) goto L3d
            if (r9 == r3) goto L36
            if (r9 == r4) goto L2f
            if (r9 != r1) goto L29
            int r7 = r10.bottom
            int r8 = r12.top
            if (r7 > r8) goto L77
            goto L43
        L29:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r0)
            throw r9
        L2f:
            int r7 = r10.right
            int r8 = r12.left
            if (r7 > r8) goto L77
            goto L43
        L36:
            int r7 = r10.top
            int r8 = r12.bottom
            if (r7 < r8) goto L77
            goto L43
        L3d:
            int r7 = r10.left
            int r8 = r12.right
            if (r7 < r8) goto L77
        L43:
            if (r9 == r5) goto L77
            if (r9 != r4) goto L48
            goto L77
        L48:
            int r11 = I(r9, r10, r11)
            if (r9 == r5) goto L6a
            if (r9 == r3) goto L65
            if (r9 == r4) goto L60
            if (r9 != r1) goto L5a
            int r9 = r12.bottom
            int r10 = r10.bottom
        L58:
            int r9 = r9 - r10
            goto L6f
        L5a:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r0)
            throw r9
        L60:
            int r9 = r12.right
            int r10 = r10.right
            goto L58
        L65:
            int r9 = r10.top
            int r10 = r12.top
            goto L58
        L6a:
            int r9 = r10.left
            int r10 = r12.left
            goto L58
        L6f:
            int r9 = java.lang.Math.max(r6, r9)
            if (r11 >= r9) goto L76
            r2 = r6
        L76:
            return r2
        L77:
            return r6
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: P8.n.m(int, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect):boolean");
    }

    public static boolean n(int i7, Rect rect, Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static void o(long j3, String str, boolean z6) {
        if (!z6) {
            throw new IllegalArgumentException(com.bumptech.glide.c.r0(str, Long.valueOf(j3)));
        }
    }

    public static void p(int i7, int i10) {
        String strR0;
        if (i7 < 0 || i7 >= i10) {
            if (i7 < 0) {
                strR0 = com.bumptech.glide.c.r0("%s (%s) must not be negative", "index", Integer.valueOf(i7));
            } else {
                if (i10 < 0) {
                    throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i10, "negative size: "));
                }
                strR0 = com.bumptech.glide.c.r0("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i7), Integer.valueOf(i10));
            }
            throw new IndexOutOfBoundsException(strR0);
        }
    }

    public static void q(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void r(int i7, int i10) {
        if (i7 < 0 || i7 > i10) {
            throw new IndexOutOfBoundsException(l("index", i7, i10));
        }
    }

    public static void s(int i7, int i10, int i11) {
        if (i7 < 0 || i10 < i7 || i10 > i11) {
            throw new IndexOutOfBoundsException((i7 < 0 || i7 > i11) ? l("start index", i7, i11) : (i10 < 0 || i10 > i11) ? l("end index", i10, i11) : com.bumptech.glide.c.r0("end index (%s) must not be less than start index (%s)", Integer.valueOf(i10), Integer.valueOf(i7)));
        }
    }

    public static final C0661n t(f0 f0Var) {
        switch (f0Var == null ? -1 : x.f819b[f0Var.ordinal()]) {
            case 1:
                C0661n c0661n = AbstractC0662o.d;
                A7.m.e(c0661n, "INTERNAL");
                return c0661n;
            case 2:
                C0661n c0661n2 = AbstractC0662o.f7189a;
                A7.m.e(c0661n2, "PRIVATE");
                return c0661n2;
            case 3:
                C0661n c0661n3 = AbstractC0662o.f7190b;
                A7.m.e(c0661n3, "PRIVATE_TO_THIS");
                return c0661n3;
            case 4:
                C0661n c0661n4 = AbstractC0662o.c;
                A7.m.e(c0661n4, "PROTECTED");
                return c0661n4;
            case 5:
                C0661n c0661n5 = AbstractC0662o.f7191e;
                A7.m.e(c0661n5, "PUBLIC");
                return c0661n5;
            case 6:
                C0661n c0661n6 = AbstractC0662o.f7192f;
                A7.m.e(c0661n6, "LOCAL");
                return c0661n6;
            default:
                C0661n c0661n7 = AbstractC0662o.f7189a;
                A7.m.e(c0661n7, "PRIVATE");
                return c0661n7;
        }
    }

    public static String u(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("%21", "!").replace("%27", "'").replace("%28", "(").replace("%29", ")").replace("%7E", "~");
        } catch (UnsupportedEncodingException e10) {
            throw new RuntimeException(e10);
        }
    }

    public static R7.b v(R7.h hVar, C2058c c2058c) {
        Object next;
        A7.m.f(c2058c, "fqName");
        Iterator it = hVar.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (A7.m.a(((R7.b) next).a(), c2058c)) {
                break;
            }
        }
        return (R7.b) next;
    }

    public static y w(String str) {
        A7.m.f(str, "<this>");
        Matcher matcher = y.d.matcher(str);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException(("No subtype found for: \"" + str + '\"').toString());
        }
        String strGroup = matcher.group(1);
        A7.m.e(strGroup, "typeSubtype.group(1)");
        Locale locale = Locale.US;
        A7.m.e(locale, "US");
        String lowerCase = strGroup.toLowerCase(locale);
        A7.m.e(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        String strGroup2 = matcher.group(2);
        A7.m.e(strGroup2, "typeSubtype.group(2)");
        A7.m.e(strGroup2.toLowerCase(locale), "this as java.lang.String).toLowerCase(locale)");
        ArrayList arrayList = new ArrayList();
        Matcher matcher2 = y.f12626e.matcher(str);
        int iEnd = matcher.end();
        while (iEnd < str.length()) {
            matcher2.region(iEnd, str.length());
            if (!matcher2.lookingAt()) {
                StringBuilder sb = new StringBuilder("Parameter is not formatted correctly: \"");
                String strSubstring = str.substring(iEnd);
                A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
                sb.append(strSubstring);
                sb.append("\" for: \"");
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.k(sb, str, '\"').toString());
            }
            String strGroup3 = matcher2.group(1);
            if (strGroup3 == null) {
                iEnd = matcher2.end();
            } else {
                String strGroup4 = matcher2.group(2);
                if (strGroup4 == null) {
                    strGroup4 = matcher2.group(3);
                } else if (Q8.o.z0(strGroup4, "'", false) && Q8.o.s0(strGroup4, "'", false) && strGroup4.length() > 2) {
                    strGroup4 = strGroup4.substring(1, strGroup4.length() - 1);
                    A7.m.e(strGroup4, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                arrayList.add(strGroup3);
                arrayList.add(strGroup4);
                iEnd = matcher2.end();
            }
        }
        return new y(str, lowerCase, (String[]) arrayList.toArray(new String[0]));
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, java.util.Map] */
    public static final String x(InterfaceC0666t interfaceC0666t) {
        C2061f c2061f;
        InterfaceC0650c interfaceC0650cY = N7.i.y(interfaceC0666t) ? y(interfaceC0666t) : null;
        if (interfaceC0650cY == null) {
            return null;
        }
        InterfaceC0650c interfaceC0650cK = AbstractC2564d.k(interfaceC0650cY);
        if (interfaceC0650cK instanceof L) {
            N7.i.y(interfaceC0650cK);
            InterfaceC0650c interfaceC0650cB = AbstractC2564d.b(AbstractC2564d.k(interfaceC0650cK), C0902e.f10935p);
            if (interfaceC0650cB == null || (c2061f = (C2061f) Z7.g.f10941a.get(AbstractC2564d.g(interfaceC0650cB))) == null) {
                return null;
            }
            return c2061f.b();
        }
        if (!(interfaceC0650cK instanceof T7.K)) {
            return null;
        }
        int i7 = AbstractC0901d.l;
        LinkedHashMap linkedHashMap = F.f10919i;
        String strD = h6.f.d((T7.K) interfaceC0650cK);
        C2061f c2061f2 = strD == null ? null : (C2061f) linkedHashMap.get(strD);
        if (c2061f2 != null) {
            return c2061f2.b();
        }
        return null;
    }

    public static final InterfaceC0650c y(InterfaceC0650c interfaceC0650c) {
        A7.m.f(interfaceC0650c, "<this>");
        if (!F.f10920j.contains(interfaceC0650c.getName()) && !Z7.g.d.contains(AbstractC2564d.k(interfaceC0650c).getName())) {
            return null;
        }
        if (interfaceC0650c instanceof L ? true : interfaceC0650c instanceof Q7.K) {
            return AbstractC2564d.b(interfaceC0650c, C0902e.f10937r);
        }
        if (interfaceC0650c instanceof T7.K) {
            return AbstractC2564d.b(interfaceC0650c, C0902e.f10938s);
        }
        return null;
    }

    public static final InterfaceC0650c z(InterfaceC0650c interfaceC0650c) {
        A7.m.f(interfaceC0650c, "<this>");
        InterfaceC0650c interfaceC0650cY = y(interfaceC0650c);
        if (interfaceC0650cY != null) {
            return interfaceC0650cY;
        }
        int i7 = C0903f.l;
        C2061f name = interfaceC0650c.getName();
        A7.m.e(name, "name");
        if (C0903f.b(name)) {
            return AbstractC2564d.b(interfaceC0650c, C0902e.f10939t);
        }
        return null;
    }
}
