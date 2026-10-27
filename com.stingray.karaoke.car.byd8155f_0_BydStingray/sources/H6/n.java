package h6;

import A.C0022k;
import A.C0032p;
import F8.C0284t;
import F8.C0289y;
import F8.O;
import F8.T;
import Q7.Q;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.S;
import T.X;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import android.support.v4.media.MediaDescriptionCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.A;
import c9.H;
import c9.u;
import c9.w;
import e0.C1183w;
import e5.AbstractC1228b;
import g1.AbstractC1274a;
import g2.C1288a;
import h.x;
import h.y;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import i.C1394a;
import i.C1395b;
import io.sentry.C1457d;
import io.sentry.C1480k1;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.J;
import io.sentry.U0;
import io.sentry.exception.SentryHttpClientException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import k2.C;
import n3.C1930E;
import n3.P;
import n7.AbstractC2000o;
import o3.C2034g;
import o3.C2036i;
import o3.C2042o;
import o3.C2043p;
import s8.C2413a;
import s8.C2415c;
import s8.C2416d;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15585a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f15586b = false;
    public static boolean c = true;
    public static boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f15587e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f15588f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f15589g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static float f15590h = 11.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static float f15591i = 20.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static int f15592j = 7;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static boolean f15593k;
    public static boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static int f15594m;

    public static final void a(boolean z6, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7, int i10) {
        int i11;
        c0761q.S(-361453782);
        int i12 = i10 & 1;
        if (i12 != 0) {
            i11 = i7 | 6;
        } else if ((i7 & 6) == 0) {
            i11 = (c0761q.g(z6) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(interfaceC2772a) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            if (i12 != 0) {
                z6 = true;
            }
            X xR = C0735d.R(interfaceC2772a, c0761q);
            c0761q.R(-971159753);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = new C1395b(z6, xR);
                c0761q.a0(objG);
            }
            C1395b c1395b = (C1395b) objG;
            c0761q.p(false);
            c0761q.R(-971159481);
            boolean zF = c0761q.f(c1395b) | c0761q.g(z6);
            Object objG2 = c0761q.G();
            if (zF || objG2 == s6) {
                objG2 = new I.b(1, c1395b, z6);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.i((InterfaceC2772a) objG2, c0761q);
            y yVarA = i.e.a(c0761q);
            if (yVarA == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner");
            }
            x xVarA = yVarA.a();
            A a10 = (A) c0761q.k(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            c0761q.R(-971159120);
            boolean zF2 = c0761q.f(xVarA) | c0761q.f(a10) | c0761q.f(c1395b);
            Object objG3 = c0761q.G();
            if (zF2 || objG3 == s6) {
                objG3 = new C0022k(xVarA, a10, c1395b, 13);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            C0735d.c(a10, xVarA, (InterfaceC2782k) objG3, c0761q);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C1394a(z6, interfaceC2772a, i7, i10);
        }
    }

    public static final C1930E b(Context context) {
        A7.m.f(context, "context");
        C1930E c1930e = new C1930E(context);
        P p10 = c1930e.f19817v;
        p10.a(new C2034g(p10));
        c1930e.f19817v.a(new C2036i());
        c1930e.f19817v.a(new C2042o());
        return c1930e;
    }

    public static void c(String str, EnumC1495p1 enumC1495p1, String str2, Throwable th) {
        C1457d c1457d = new C1457d();
        c1457d.f16515q = "Logcat";
        c1457d.f16513n = str2;
        c1457d.f16517s = enumC1495p1;
        if (str != null) {
            c1457d.c(str, "tag");
        }
        if (th != null && th.getMessage() != null) {
            c1457d.c(th.getMessage(), "throwable");
        }
        U0.b().e(c1457d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0012, code lost:
    
        if (r5 == false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int d(int r2, int r3, int r4, boolean r5) {
        /*
            r0 = 0
            if (r3 < r4) goto L9
            if (r5 == 0) goto L6
            goto L26
        L6:
            int r0 = r4 - r3
            goto L26
        L9:
            if (r5 != 0) goto Le
            if (r3 > r2) goto L18
            goto L12
        Le:
            int r1 = r4 - r3
            if (r1 <= r2) goto L18
        L12:
            if (r5 == 0) goto L15
            goto L16
        L15:
            int r2 = r2 - r3
        L16:
            r0 = r2
            goto L26
        L18:
            if (r5 == 0) goto L1d
            if (r3 > r2) goto L24
            goto L21
        L1d:
            int r1 = r4 - r3
            if (r1 <= r2) goto L24
        L21:
            if (r5 != 0) goto L15
            goto L16
        L24:
            if (r5 != 0) goto L6
        L26:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.n.d(int, int, int, boolean):int");
    }

    public static final g0.o e(g0.o oVar, float f10) {
        return f10 == 1.0f ? oVar : androidx.compose.ui.graphics.a.b(oVar, 0.0f, 0.0f, f10, 0.0f, 0.0f, 0L, null, true, 126971);
    }

    public static void f(J j3, H0.b bVar, H h10) {
        A7.m.f(j3, "hub");
        A7.m.f(bVar, "request");
        A7.m.f(h10, "response");
        h3.y yVarA = io.sentry.util.j.a(((w) bVar.f2765m).f12624h);
        io.sentry.protocol.j jVar = new io.sentry.protocol.j();
        jVar.l = "SentryOkHttpInterceptor";
        StringBuilder sb = new StringBuilder("HTTP Client Error with status code: ");
        int i7 = h10.o;
        sb.append(i7);
        C1480k1 c1480k1 = new C1480k1(new io.sentry.exception.a(jVar, new SentryHttpClientException(sb.toString()), Thread.currentThread(), true));
        C1523x c1523x = new C1523x();
        c1523x.c(bVar, "okHttp:request");
        c1523x.c(h10, "okHttp:response");
        io.sentry.protocol.n nVar = new io.sentry.protocol.n();
        nVar.l = (String) yVarA.f15499m;
        nVar.f16750n = (String) yVarA.f15500n;
        nVar.f16756u = (String) yVarA.o;
        boolean zIsSendDefaultPii = j3.q().isSendDefaultPii();
        u uVar = (u) bVar.o;
        nVar.f16751p = zIsSendDefaultPii ? uVar.d("Cookie") : null;
        nVar.f16749m = (String) bVar.f2766n;
        nVar.f16752q = r.p(l(j3, uVar));
        AbstractC1274a abstractC1274a = (AbstractC1274a) bVar.f2767p;
        Long lValueOf = abstractC1274a != null ? Long.valueOf(abstractC1274a.B()) : null;
        if (lValueOf != null && lValueOf.longValue() != -1) {
            nVar.f16754s = Long.valueOf(lValueOf.longValue());
        }
        io.sentry.protocol.p pVar = new io.sentry.protocol.p();
        boolean zIsSendDefaultPii2 = j3.q().isSendDefaultPii();
        u uVar2 = h10.f12517q;
        pVar.l = zIsSendDefaultPii2 ? uVar2.d("Set-Cookie") : null;
        pVar.f16759m = r.p(l(j3, uVar2));
        pVar.f16760n = Integer.valueOf(i7);
        B3.m mVar = h10.f12518r;
        Long lValueOf2 = mVar != null ? Long.valueOf(mVar.i()) : null;
        if (lValueOf2 != null && lValueOf2.longValue() != -1) {
            pVar.o = Long.valueOf(lValueOf2.longValue());
        }
        c1480k1.o = nVar;
        c1480k1.f15978m.d(pVar);
        j3.s(c1480k1, c1523x);
    }

    public static Parcelable g(Parcelable parcelable, Parcelable.Creator creator) {
        if (parcelable == null) {
            return null;
        }
        Parcelable parcelable2 = (Parcelable) p(parcelable);
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelable2.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            return (Parcelable) p((Parcelable) creator.createFromParcel(parcelObtain));
        } finally {
            parcelObtain.recycle();
        }
    }

    public static final O h(O o, Q q4) {
        if (q4 == null || o.a() == 1) {
            return o;
        }
        if (q4.e0() != o.a()) {
            C2415c c2415c = new C2415c(o);
            F8.H.f2437m.getClass();
            return new F8.P(1, new C2413a(o, c2415c, false, F8.H.f2438n));
        }
        if (!o.c()) {
            return new F8.P(o.b());
        }
        E8.b bVar = E8.l.f1859e;
        A7.m.e(bVar, "NO_LOCKS");
        return new F8.P(1, new C0289y(bVar, new C1183w(o, 9)));
    }

    public static int i(String str, String str2, Throwable th) {
        c(str, EnumC1495p1.ERROR, str2, th);
        return Log.e(str, str2, th);
    }

    public static void j(String str, String str2) {
        c(str, EnumC1495p1.ERROR, str2, null);
        Log.e(str, str2);
    }

    public static byte[] k(k2.h hVar, String str, byte[] bArr, Map map) throws q2.x {
        Map map2;
        List list;
        C c10 = new C(hVar);
        Collections.emptyMap();
        Uri uri = Uri.parse(str);
        AbstractC1346c.l(uri, "The uri must be set.");
        k2.m mVar = new k2.m(uri, 0L, 2, bArr, map, 0L, -1L, null, 1);
        int i7 = 0;
        while (true) {
            try {
                k2.k kVar = new k2.k(c10, mVar);
                try {
                    return AbstractC1228b.b(kVar);
                } catch (k2.x e10) {
                    int i10 = e10.o;
                    String str2 = null;
                    if ((i10 == 307 || i10 == 308) && i7 < 5 && (map2 = e10.f17708p) != null && (list = (List) map2.get("Location")) != null && !list.isEmpty()) {
                        str2 = (String) list.get(0);
                    }
                    if (str2 == null) {
                        throw e10;
                    }
                    i7++;
                    k2.l lVarA = mVar.a();
                    lVarA.f17667a = Uri.parse(str2);
                    mVar = lVarA.a();
                } finally {
                    AbstractC1343A.g(kVar);
                }
            } catch (Exception e11) {
                c10.f17632n.getClass();
                c10.l.f();
                throw new q2.x(e11);
            }
        }
    }

    public static LinkedHashMap l(J j3, u uVar) {
        if (!j3.q().isSendDefaultPii()) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = uVar.size();
        for (int i7 = 0; i7 < size; i7++) {
            String strL = uVar.l(i7);
            List list = io.sentry.util.a.f16915a;
            if (!io.sentry.util.a.f16915a.contains(strL.toUpperCase(Locale.ROOT))) {
                linkedHashMap.put(strL, uVar.s(i7));
            }
        }
        return linkedHashMap;
    }

    public static boolean m(Throwable th) {
        return AbstractC1343A.f15195a == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/NotProvisionedException;.<init>(");
    }

    public static boolean n(Throwable th) {
        return AbstractC1343A.f15195a == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/ResourceBusyException;.<init>(");
    }

    public static final boolean o(C0761q c0761q) {
        return (((Configuration) c0761q.k(AndroidCompositionLocals_androidKt.f11687a)).uiMode & 48) == 32;
    }

    public static Object p(Parcelable parcelable) {
        int i7 = AbstractC1343A.f15195a;
        if (i7 >= 21 && i7 < 23) {
            if (parcelable instanceof MediaBrowserCompat$MediaItem) {
                MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) parcelable;
                MediaDescriptionCompat mediaDescriptionCompat = mediaBrowserCompat$MediaItem.f11239m;
                return new MediaBrowserCompat$MediaItem(new MediaDescriptionCompat(mediaDescriptionCompat.l, mediaDescriptionCompat.f11240m, mediaDescriptionCompat.f11241n, mediaDescriptionCompat.o, mediaDescriptionCompat.f11242p, mediaDescriptionCompat.f11243q, mediaDescriptionCompat.f11244r, mediaDescriptionCompat.f11245s), mediaBrowserCompat$MediaItem.l);
            }
            if (parcelable instanceof MediaDescriptionCompat) {
                MediaDescriptionCompat mediaDescriptionCompat2 = (MediaDescriptionCompat) parcelable;
                return new MediaDescriptionCompat(mediaDescriptionCompat2.l, mediaDescriptionCompat2.f11240m, mediaDescriptionCompat2.f11241n, mediaDescriptionCompat2.o, mediaDescriptionCompat2.f11242p, mediaDescriptionCompat2.f11243q, mediaDescriptionCompat2.f11244r, mediaDescriptionCompat2.f11245s);
            }
        }
        return parcelable;
    }

    public static final C1930E q(n3.O[] oArr, C0761q c0761q) {
        int i7 = 1;
        Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
        Object[] objArrCopyOf = Arrays.copyOf(oArr, oArr.length);
        C2043p c2043p = C2043p.f20480m;
        S0.p pVar = new S0.p(context, 29);
        io.sentry.internal.debugmeta.c cVar = d0.o.f13275a;
        io.sentry.internal.debugmeta.c cVar2 = new io.sentry.internal.debugmeta.c(c2043p, 15, pVar);
        boolean zH = c0761q.h(context);
        Object objG = c0761q.G();
        if (zH || objG == C0753m.f8270a) {
            objG = new Q1.a(context, i7);
            c0761q.a0(objG);
        }
        C1930E c1930e = (C1930E) w0.c.Z(objArrCopyOf, cVar2, (InterfaceC2772a) objG, c0761q, 0, 4);
        for (n3.O o : oArr) {
            c1930e.f19817v.a(o);
        }
        A7.m.f(c1930e, "<this>");
        c0761q.R(-941334997);
        c0761q.R(-2071393061);
        Boolean bool = Boolean.TRUE;
        X xR = C0735d.R(bool, c0761q);
        X xR2 = C0735d.R(bool, c0761q);
        A9.b bVarG = ((A) c0761q.k(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner())).g();
        A7.m.e(bVarG, "LocalLifecycleOwner.current.lifecycle");
        C0735d.c(bVarG, c1930e, new C0032p(c1930e, bVarG, xR, xR2, 3), c0761q);
        c0761q.p(false);
        c0761q.p(false);
        return c1930e;
    }

    public static void r(C1288a c1288a) {
        c1288a.f14764k = -3.4028235E38f;
        c1288a.f14763j = Integer.MIN_VALUE;
        CharSequence charSequence = c1288a.f14756a;
        if (charSequence instanceof Spanned) {
            if (!(charSequence instanceof Spannable)) {
                c1288a.f14756a = SpannableString.valueOf(charSequence);
            }
            CharSequence charSequence2 = c1288a.f14756a;
            charSequence2.getClass();
            Spannable spannable = (Spannable) charSequence2;
            for (Object obj : spannable.getSpans(0, spannable.length(), Object.class)) {
                if ((obj instanceof AbsoluteSizeSpan) || (obj instanceof RelativeSizeSpan)) {
                    spannable.removeSpan(obj);
                }
            }
        }
    }

    public static float s(int i7, float f10, int i10, int i11) {
        float f11;
        if (f10 == -3.4028235E38f) {
            return -3.4028235E38f;
        }
        if (i7 == 0) {
            f11 = i11;
        } else {
            if (i7 != 1) {
                if (i7 != 2) {
                    return -3.4028235E38f;
                }
                return f10;
            }
            f11 = i10;
        }
        return f10 * f11;
    }

    public static void t(String str, String str2) {
        c(str, EnumC1495p1.WARNING, str2, null);
        Log.w(str, str2);
    }

    public static void u(String str, String str2, Throwable th) {
        c(str, EnumC1495p1.WARNING, str2, th);
        Log.w(str, str2, th);
    }

    public static T v(T t10) {
        if (!(t10 instanceof C0284t)) {
            return new C2416d(t10, true);
        }
        C0284t c0284t = (C0284t) t10;
        O[] oArr = c0284t.c;
        A7.m.f(oArr, "<this>");
        Q[] qArr = c0284t.f2480b;
        A7.m.f(qArr, "other");
        int iMin = Math.min(oArr.length, qArr.length);
        ArrayList<m7.i> arrayList = new ArrayList(iMin);
        for (int i7 = 0; i7 < iMin; i7++) {
            arrayList.add(new m7.i(oArr[i7], qArr[i7]));
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        for (m7.i iVar : arrayList) {
            arrayList2.add(h((O) iVar.l, (Q) iVar.f19259m));
        }
        return new C0284t(qArr, (O[]) arrayList2.toArray(new O[0]), true);
    }
}
