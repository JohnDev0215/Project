package h6;

import Q7.InterfaceC0649b;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import T.C0761q;
import T7.K;
import a.AbstractC0904a;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.stingray.karaoke.car.utils.FontSize;
import io.sentry.C1489n1;
import io.sentry.X0;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import n3.C1937b;
import n3.y;
import n7.AbstractC1985B;
import n7.z;
import r.l0;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15575a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static C0.r f15576b;

    public static final m0.d a(long j3, long j10) {
        return new m0.d(m0.c.d(j3), m0.c.e(j3), m0.f.d(j10) + m0.c.d(j3), m0.f.b(j10) + m0.c.e(j3));
    }

    public static int b(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c(Q7.InterfaceC0666t r5, int r6) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.c(Q7.t, int):java.lang.String");
    }

    public static final String d(InterfaceC0649b interfaceC0649b) {
        A7.m.f(interfaceC0649b, "<this>");
        if (r8.d.o(interfaceC0649b)) {
            return null;
        }
        InterfaceC0657j interfaceC0657jO = interfaceC0649b.o();
        InterfaceC0652e interfaceC0652e = interfaceC0657jO instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657jO : null;
        if (interfaceC0652e == null || interfaceC0652e.getName().f20573m) {
            return null;
        }
        InterfaceC0649b interfaceC0649bA = interfaceC0649b.b1();
        K k10 = interfaceC0649bA instanceof K ? (K) interfaceC0649bA : null;
        if (k10 == null) {
            return null;
        }
        return e.r(interfaceC0652e, c(k10, 3));
    }

    public static boolean e(String str, List list) {
        if (list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str.contains(str2)) {
                return true;
            }
            if (str.matches(str2)) {
                return true;
            }
        }
        return false;
    }

    public static final float f(int i7, C0761q c0761q) {
        TypedValue typedValue = new TypedValue();
        ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getValue(i7, typedValue, true);
        return typedValue.getFloat();
    }

    public static final long g(FontSize fontSize, C0761q c0761q) {
        A7.m.f(fontSize, "fontSize");
        return AbstractC0904a.I(C7.a.W(fontSize.getBaseMultiplier() * m6.c.f19252a.w(6, c0761q)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        if (r5.c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.res.ColorStateList h(android.content.Context r8, int r9) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            x1.i r1 = new x1.i
            r1.<init>(r0, r8)
            java.lang.Object r2 = x1.AbstractC2671j.c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = x1.AbstractC2671j.f23709b     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3c
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3c
            r4 = 0
            if (r3 == 0) goto L50
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3c
            if (r5 <= 0) goto L50
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3c
            x1.h r5 = (x1.C2669h) r5     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L50
            android.content.res.Configuration r6 = r5.f23705b     // Catch: java.lang.Throwable -> L3c
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3c
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L4d
            if (r8 != 0) goto L3f
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L49
            goto L3f
        L3c:
            r8 = move-exception
            goto Lb9
        L3f:
            if (r8 == 0) goto L4d
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3c
            if (r6 != r7) goto L4d
        L49:
            android.content.res.ColorStateList r3 = r5.f23704a     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            goto L52
        L4d:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3c
        L50:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            r3 = r4
        L52:
            if (r3 == 0) goto L55
            goto Lb8
        L55:
            java.lang.ThreadLocal r2 = x1.AbstractC2671j.f23708a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L67
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L67:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L76
            r3 = 31
            if (r2 > r3) goto L76
            goto L87
        L76:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = x1.AbstractC2664c.a(r0, r2, r8)     // Catch: java.lang.Exception -> L7f
            goto L87
        L7f:
            r2 = move-exception
            java.lang.String r3 = "ResourcesCompat"
            java.lang.String r5 = "Failed to inflate ColorStateList, leaving it to the framework"
            h6.n.u(r3, r5, r2)
        L87:
            if (r4 == 0) goto Lb4
            java.lang.Object r2 = x1.AbstractC2671j.c
            monitor-enter(r2)
            java.util.WeakHashMap r0 = x1.AbstractC2671j.f23709b     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L9f
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L9f
            if (r3 != 0) goto La1
            android.util.SparseArray r3 = new android.util.SparseArray     // Catch: java.lang.Throwable -> L9f
            r3.<init>()     // Catch: java.lang.Throwable -> L9f
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L9f
            goto La1
        L9f:
            r8 = move-exception
            goto Lb2
        La1:
            x1.h r0 = new x1.h     // Catch: java.lang.Throwable -> L9f
            android.content.res.Resources r1 = r1.f23706a     // Catch: java.lang.Throwable -> L9f
            android.content.res.Configuration r1 = r1.getConfiguration()     // Catch: java.lang.Throwable -> L9f
            r0.<init>(r4, r1, r8)     // Catch: java.lang.Throwable -> L9f
            r3.append(r9, r0)     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            r3 = r4
            goto Lb8
        Lb2:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            throw r8
        Lb4:
            android.content.res.ColorStateList r3 = r0.getColorStateList(r9, r8)
        Lb8:
            return r3
        Lb9:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.h(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static /* synthetic */ Collection i(y8.p pVar, y8.f fVar, int i7) {
        if ((i7 & 1) != 0) {
            fVar = y8.f.f24196m;
        }
        y8.n.f24214a.getClass();
        return pVar.d(fVar, y8.k.f24208n);
    }

    public static String j(Context context, int i7) {
        String strValueOf;
        A7.m.f(context, "context");
        if (i7 <= 16777215) {
            return String.valueOf(i7);
        }
        try {
            strValueOf = context.getResources().getResourceName(i7);
        } catch (Resources.NotFoundException unused) {
            strValueOf = String.valueOf(i7);
        }
        A7.m.e(strValueOf, "try {\n                  …tring()\n                }");
        return strValueOf;
    }

    public static Drawable k(Context context, int i7) {
        return l0.b().c(context, i7);
    }

    public static P8.k l(y yVar) {
        A7.m.f(yVar, "<this>");
        return P8.m.d0(yVar, C1937b.f19852u);
    }

    public static final int m(float f10, float f11, C0761q c0761q, int i7) {
        if ((i7 & 2) != 0) {
            f11 = m6.c.f19252a.F(c0761q);
        }
        Object obj = n(f10, f11, c0761q, 0).first;
        A7.m.e(obj, "first");
        return ((Number) obj).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final android.util.Pair n(float r9, float r10, T.C0761q r11, int r12) {
        /*
            r12 = r12 & 2
            if (r12 == 0) goto La
            m6.b r10 = m6.c.f19252a
            float r10 = r10.F(r11)
        La:
            float r12 = r9 + r10
            m6.b r0 = m6.c.f19252a
            float r1 = r0.A0(r11)
            float r1 = r1 + r10
            float r12 = r12 / r1
            int r12 = (int) r12
            int r0 = r0.N(r11)
            r1 = 1
            int r12 = x3.AbstractC2673a.v(r12, r1, r0)
        L1e:
            float r0 = (float) r12
            float r0 = r9 / r0
            float r0 = r0 + r10
            m6.b r1 = m6.c.f19252a
            r1.getClass()
            r2 = 2066249664(0x7b2877c0, float:8.747347E35)
            r11.Q(r2)
            r2 = 230(0xe6, float:3.22E-43)
            float r2 = (float) r2
            r3 = 280(0x118, float:3.92E-43)
            float r3 = (float) r3
            r4 = 270(0x10e, float:3.78E-43)
            float r4 = (float) r4
            r5 = 250(0xfa, float:3.5E-43)
            float r5 = (float) r5
            T.y r6 = k6.AbstractC1692D.f18238a
            java.lang.Object r6 = r11.k(r6)
            com.stingray.karaoke.car.utils.LayoutMode r6 = (com.stingray.karaoke.car.utils.LayoutMode) r6
            com.stingray.karaoke.car.utils.LayoutMode r7 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r8 = 0
            if (r6 != r7) goto L47
            goto L89
        L47:
            com.stingray.karaoke.car.utils.LayoutMode r7 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r6 != r7) goto L4c
            goto L7a
        L4c:
            com.stingray.karaoke.car.utils.LayoutMode r7 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r6 != r7) goto L51
            goto L86
        L51:
            com.stingray.karaoke.car.utils.LayoutMode r7 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r6 == r7) goto L88
            com.stingray.karaoke.car.utils.LayoutMode r7 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r6 == r7) goto L88
            com.stingray.karaoke.car.utils.LayoutMode r7 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r6 != r7) goto L5e
            goto L88
        L5e:
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r6 == r5) goto L71
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r6 == r5) goto L71
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r6 != r5) goto L6b
            goto L71
        L6b:
            A9.k r9 = new A9.k
            r9.<init>()
            throw r9
        L71:
            r5 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r5 = M5.b.r(r11, r5, r1, r11, r8)
            if (r5 == 0) goto L7c
        L7a:
            r2 = r3
            goto L89
        L7c:
            r3 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r1 = A7.k.u(r11, r3, r1, r11, r8)
            if (r1 == 0) goto L86
            goto L89
        L86:
            r2 = r4
            goto L89
        L88:
            r2 = r5
        L89:
            r11.p(r8)
            int r1 = java.lang.Float.compare(r0, r2)
            if (r1 <= 0) goto L95
            int r12 = r12 + 1
            goto L1e
        L95:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r12)
            a1.e r10 = new a1.e
            r10.<init>(r0)
            android.util.Pair r9 = android.util.Pair.create(r9, r10)
            java.lang.String r10 = "create(...)"
            A7.m.e(r9, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.n(float, float, T.q, int):android.util.Pair");
    }

    public static Comparable o(a1.e eVar, Comparable comparable) {
        return eVar.compareTo(comparable) >= 0 ? eVar : comparable;
    }

    public static final byte[] p(URL url) throws IllegalAccessException, IOException, InvocationTargetException {
        InputStream inputStreamOpenStream = url.openStream();
        try {
            A7.m.c(inputStreamOpenStream);
            byte[] bArrW = z.w(inputStreamOpenStream);
            AbstractC1985B.c(inputStreamOpenStream, null);
            return bArrW;
        } finally {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0038, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int q(int r7, java.lang.CharSequence r8) {
        /*
            int r0 = r8.length()
            r1 = -1
            if (r7 < r0) goto L8
            return r1
        L8:
            char r0 = r8.charAt(r7)
            r2 = 92
            r3 = 60
            if (r0 != r3) goto L39
        L12:
            int r7 = r7 + 1
            int r0 = r8.length()
            if (r7 >= r0) goto L38
            char r0 = r8.charAt(r7)
            r4 = 10
            if (r0 == r4) goto L38
            if (r0 == r3) goto L38
            r4 = 62
            if (r0 == r4) goto L35
            if (r0 == r2) goto L2b
            goto L12
        L2b:
            int r0 = r7 + 1
            boolean r4 = h6.i.s(r0, r8)
            if (r4 == 0) goto L12
            r7 = r0
            goto L12
        L35:
            int r7 = r7 + 1
            return r7
        L38:
            return r1
        L39:
            r0 = 0
            r3 = r7
        L3b:
            int r4 = r8.length()
            if (r3 >= r4) goto L79
            char r4 = r8.charAt(r3)
            if (r4 == 0) goto L76
            r5 = 32
            if (r4 == r5) goto L76
            if (r4 == r2) goto L6a
            r6 = 40
            if (r4 == r6) goto L65
            r5 = 41
            if (r4 == r5) goto L5e
            boolean r4 = java.lang.Character.isISOControl(r4)
            if (r4 == 0) goto L73
            if (r3 == r7) goto L7d
            goto L60
        L5e:
            if (r0 != 0) goto L62
        L60:
            r1 = r3
            goto L7d
        L62:
            int r0 = r0 + (-1)
            goto L73
        L65:
            int r0 = r0 + 1
            if (r0 <= r5) goto L73
            goto L7d
        L6a:
            int r4 = r3 + 1
            boolean r5 = h6.i.s(r4, r8)
            if (r5 == 0) goto L73
            r3 = r4
        L73:
            int r3 = r3 + 1
            goto L3b
        L76:
            if (r3 == r7) goto L7d
            goto L60
        L79:
            int r1 = r8.length()
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.q(int, java.lang.CharSequence):int");
    }

    public static int r(int i7, CharSequence charSequence) {
        while (i7 < charSequence.length()) {
            switch (charSequence.charAt(i7)) {
                case '[':
                    return -1;
                case '\\':
                    int i10 = i7 + 1;
                    if (i.s(i10, charSequence)) {
                        i7 = i10;
                    }
                    break;
                case ']':
                    return i7;
            }
            i7++;
        }
        return charSequence.length();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int s(java.lang.CharSequence r3, int r4, char r5) {
        /*
        L0:
            int r0 = r3.length()
            if (r4 >= r0) goto L28
            char r0 = r3.charAt(r4)
            r1 = 92
            if (r0 != r1) goto L18
            int r1 = r4 + 1
            boolean r2 = h6.i.s(r1, r3)
            if (r2 == 0) goto L18
            r4 = r1
            goto L25
        L18:
            if (r0 != r5) goto L1b
            return r4
        L1b:
            r1 = 41
            if (r5 != r1) goto L25
            r1 = 40
            if (r0 != r1) goto L25
            r3 = -1
            return r3
        L25:
            int r4 = r4 + 1
            goto L0
        L28:
            int r3 = r3.length()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.s(java.lang.CharSequence, int, char):int");
    }

    public static void t(X0 x02, C1489n1 c1489n1, io.sentry.K k10) {
        if (x02.l != null) {
            c1489n1.w("event_id");
            c1489n1.I(k10, x02.l);
        }
        c1489n1.w("contexts");
        c1489n1.I(k10, x02.f15978m);
        if (x02.f15979n != null) {
            c1489n1.w("sdk");
            c1489n1.I(k10, x02.f15979n);
        }
        if (x02.o != null) {
            c1489n1.w("request");
            c1489n1.I(k10, x02.o);
        }
        AbstractMap abstractMap = x02.f15980p;
        if (abstractMap != null && !abstractMap.isEmpty()) {
            c1489n1.w("tags");
            c1489n1.I(k10, x02.f15980p);
        }
        if (x02.f15981q != null) {
            c1489n1.w("release");
            c1489n1.L(x02.f15981q);
        }
        if (x02.f15982r != null) {
            c1489n1.w("environment");
            c1489n1.L(x02.f15982r);
        }
        if (x02.f15983s != null) {
            c1489n1.w("platform");
            c1489n1.L(x02.f15983s);
        }
        if (x02.f15984t != null) {
            c1489n1.w("user");
            c1489n1.I(k10, x02.f15984t);
        }
        if (x02.f15986v != null) {
            c1489n1.w("server_name");
            c1489n1.L(x02.f15986v);
        }
        if (x02.f15987w != null) {
            c1489n1.w("dist");
            c1489n1.L(x02.f15987w);
        }
        List list = x02.f15988x;
        if (list != null && !list.isEmpty()) {
            c1489n1.w("breadcrumbs");
            c1489n1.I(k10, x02.f15988x);
        }
        if (x02.f15989y != null) {
            c1489n1.w("debug_meta");
            c1489n1.I(k10, x02.f15989y);
        }
        AbstractMap abstractMap2 = x02.f15990z;
        if (abstractMap2 == null || abstractMap2.isEmpty()) {
            return;
        }
        c1489n1.w("extra");
        c1489n1.I(k10, x02.f15990z);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean u(E0.F r3, boolean r4, io.sentry.F1 r5) {
        /*
            L0.j r0 = r3.q()
            r1 = 0
            if (r0 == 0) goto L15
            L0.u r2 = io.sentry.android.replay.w.f16459a
            java.util.LinkedHashMap r0 = r0.l
            java.lang.Object r0 = r0.get(r2)
            if (r0 != 0) goto L12
            goto L13
        L12:
            r1 = r0
        L13:
            java.lang.String r1 = (java.lang.String) r1
        L15:
            java.lang.String r0 = "unmask"
            boolean r0 = A7.m.a(r1, r0)
            r2 = 0
            if (r0 == 0) goto L1f
            return r2
        L1f:
            java.lang.String r0 = "mask"
            boolean r0 = A7.m.a(r1, r0)
            r1 = 1
            if (r0 == 0) goto L29
            return r1
        L29:
            if (r4 == 0) goto L2e
            java.lang.String r3 = "android.widget.ImageView"
            goto L54
        L2e:
            L0.j r4 = r3.q()
            if (r4 == 0) goto L3f
            L0.u r0 = L0.r.f3957u
            java.util.LinkedHashMap r4 = r4.l
            boolean r4 = r4.containsKey(r0)
            if (r4 != r1) goto L3f
            goto L4f
        L3f:
            L0.j r3 = r3.q()
            if (r3 == 0) goto L52
            L0.u r4 = L0.i.f3893i
            java.util.LinkedHashMap r3 = r3.l
            boolean r3 = r3.containsKey(r4)
            if (r3 != r1) goto L52
        L4f:
            java.lang.String r3 = "android.widget.TextView"
            goto L54
        L52:
            java.lang.String r3 = "android.view.View"
        L54:
            io.sentry.u r4 = r5.getExperimental()
            io.sentry.J1 r4 = r4.f16913a
            java.util.concurrent.CopyOnWriteArraySet r4 = r4.d
            boolean r4 = r4.contains(r3)
            if (r4 == 0) goto L63
            return r2
        L63:
            io.sentry.u r4 = r5.getExperimental()
            io.sentry.J1 r4 = r4.f16913a
            java.util.concurrent.CopyOnWriteArraySet r4 = r4.c
            boolean r3 = r4.contains(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.u(E0.F, boolean, io.sentry.F1):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0195  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void v(E0.F r25, io.sentry.android.replay.viewhierarchy.d r26, boolean r27, io.sentry.F1 r28) {
        /*
            Method dump skipped, instruction units count: 926
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.v(E0.F, io.sentry.android.replay.viewhierarchy.d, boolean, io.sentry.F1):void");
    }
}
