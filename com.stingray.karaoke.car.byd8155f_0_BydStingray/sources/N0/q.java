package N0;

import H7.InterfaceC0318c;
import S0.InterfaceC0702n;
import a.AbstractC0904a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Pair;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import androidx.lifecycle.f0;
import androidx.lifecycle.h0;
import androidx.lifecycle.k0;
import androidx.recyclerview.widget.RecyclerView;
import b2.AbstractC0992b;
import c8.C1036a;
import c9.C1046g;
import e3.C1218a;
import e3.C1220c;
import e3.C1223f;
import g1.AbstractC1274a;
import g2.C1288a;
import g2.C1289b;
import h2.AbstractC1343A;
import io.sentry.EnumC1495p1;
import io.sentry.android.core.C1427c;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.T;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import l3.P;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import o8.C2057b;
import o8.C2061f;
import s3.C2381a;
import s3.C2404x;
import t8.C2448a;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class q implements t, m7.f, Y2.d, h8.m {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f4399m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f4400n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f4401p;

    public /* synthetic */ q(V3.a aVar, A9.b bVar, V3.b bVar2, V3.b bVar3, A9.b bVar4) {
        this.f4399m = aVar;
        this.f4400n = bVar;
        this.o = bVar2;
        this.f4401p = bVar3;
        this.l = bVar4;
    }

    public float A(int i7, boolean z6) {
        Layout layout = (Layout) this.f4399m;
        int lineEnd = layout.getLineEnd(layout.getLineForOffset(i7));
        if (i7 > lineEnd) {
            i7 = lineEnd;
        }
        return z6 ? layout.getPrimaryHorizontal(i7) : layout.getSecondaryHorizontal(i7);
    }

    public float B(int i7, boolean z6, boolean z10) {
        int i10;
        int i11;
        if (!z10) {
            return A(i7, z6);
        }
        Layout layout = (Layout) this.f4399m;
        int iC = O0.s.c(layout, i7, z10);
        int lineStart = layout.getLineStart(iC);
        int lineEnd = layout.getLineEnd(iC);
        if (i7 != lineStart && i7 != lineEnd) {
            return A(i7, z6);
        }
        if (i7 == 0 || i7 == layout.getText().length()) {
            return A(i7, z6);
        }
        int iC2 = C(i7, z10);
        boolean z11 = layout.getParagraphDirection(layout.getLineForOffset(D(iC2))) == -1;
        int I2 = I(lineEnd, lineStart);
        int iD = D(iC2);
        int i12 = lineStart - iD;
        int i13 = I2 - iD;
        Bidi bidiP = p(iC2);
        Bidi bidiCreateLineBidi = bidiP != null ? bidiP.createLineBidi(i12, i13) : null;
        if (bidiCreateLineBidi == null || bidiCreateLineBidi.getRunCount() == 1) {
            boolean zIsRtlCharAt = layout.isRtlCharAt(lineStart);
            if (z6 || z11 == zIsRtlCharAt) {
                z11 = !z11;
            }
            return i7 == lineStart ? z11 : !z11 ? layout.getLineLeft(iC) : layout.getLineRight(iC);
        }
        int runCount = bidiCreateLineBidi.getRunCount();
        O0.l[] lVarArr = new O0.l[runCount];
        for (int i14 = 0; i14 < runCount; i14++) {
            lVarArr[i14] = new O0.l(bidiCreateLineBidi.getRunStart(i14) + lineStart, bidiCreateLineBidi.getRunLimit(i14) + lineStart, bidiCreateLineBidi.getRunLevel(i14) % 2 == 1);
        }
        int runCount2 = bidiCreateLineBidi.getRunCount();
        byte[] bArr = new byte[runCount2];
        for (int i15 = 0; i15 < runCount2; i15++) {
            bArr[i15] = (byte) bidiCreateLineBidi.getRunLevel(i15);
        }
        Bidi.reorderVisually(bArr, 0, lVarArr, 0, runCount);
        if (i7 == lineStart) {
            int i16 = 0;
            while (true) {
                if (i16 >= runCount) {
                    i11 = -1;
                    break;
                }
                if (lVarArr[i16].f5019a == i7) {
                    i11 = i16;
                    break;
                }
                i16++;
            }
            boolean z12 = (z6 || z11 == lVarArr[i11].c) ? !z11 : z11;
            return (i11 == 0 && z12) ? layout.getLineLeft(iC) : (i11 != runCount - 1 || z12) ? z12 ? layout.getPrimaryHorizontal(lVarArr[i11 - 1].f5019a) : layout.getPrimaryHorizontal(lVarArr[i11 + 1].f5019a) : layout.getLineRight(iC);
        }
        int I9 = i7 > I2 ? I(i7, lineStart) : i7;
        int i17 = 0;
        while (true) {
            if (i17 >= runCount) {
                i10 = -1;
                break;
            }
            if (lVarArr[i17].f5020b == I9) {
                i10 = i17;
                break;
            }
            i17++;
        }
        boolean z13 = (z6 || z11 == lVarArr[i10].c) ? z11 : !z11;
        return (i10 == 0 && z13) ? layout.getLineLeft(iC) : (i10 != runCount - 1 || z13) ? z13 ? layout.getPrimaryHorizontal(lVarArr[i10 - 1].f5020b) : layout.getPrimaryHorizontal(lVarArr[i10 + 1].f5020b) : layout.getLineRight(iC);
    }

    public int C(int i7, boolean z6) {
        ArrayList arrayList = (ArrayList) this.l;
        int iA = AbstractC1999n.A(arrayList, Integer.valueOf(i7));
        int i10 = iA < 0 ? -(iA + 1) : iA + 1;
        if (z6 && i10 > 0) {
            int i11 = i10 - 1;
            if (i7 == ((Number) arrayList.get(i11)).intValue()) {
                return i11;
            }
        }
        return i10;
    }

    public int D(int i7) {
        if (i7 == 0) {
            return 0;
        }
        return ((Number) ((ArrayList) this.l).get(i7 - 1)).intValue();
    }

    public boolean E() {
        return ((ArrayList) this.l).size() > 0;
    }

    public void F() {
        J("HEAD", null);
    }

    public void G(String str, String str2) {
        A7.m.f(str2, "value");
        A7.D d = (A7.D) this.o;
        d.getClass();
        AbstractC0904a.t(str);
        AbstractC0904a.v(str2, str);
        d.x(str);
        d.i(str, str2);
    }

    public boolean H() {
        if (((FrameMetricsAggregator) this.f4399m) != null) {
            SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) this.f4400n;
            if (sentryAndroidOptions.isEnableFramesTracking() && !sentryAndroidOptions.isEnablePerformanceV2()) {
                return true;
            }
        }
        return false;
    }

    public int I(int i7, int i10) {
        while (i7 > i10) {
            char cCharAt = ((Layout) this.f4399m).getText().charAt(i7 - 1);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != 5760 && ((A7.m.g(cCharAt, 8192) < 0 || A7.m.g(cCharAt, 8202) > 0 || cCharAt == 8199) && cCharAt != 8287 && cCharAt != 12288)) {
                break;
            }
            i7--;
        }
        return i7;
    }

    public void J(String str, AbstractC1274a abstractC1274a) {
        A7.m.f(str, "method");
        if (str.length() <= 0) {
            throw new IllegalArgumentException("method.isEmpty() == true");
        }
        if (abstractC1274a == null) {
            if (str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT")) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("method ", str, " must have a request body.").toString());
            }
        } else if (!h6.j.o(str)) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("method ", str, " must not have a request body.").toString());
        }
        this.f4400n = str;
        this.f4401p = abstractC1274a;
    }

    public C2381a K(int i7, int i10, int i11) {
        C2381a c2381a = (C2381a) ((E1.c) this.f4399m).a();
        if (c2381a != null) {
            c2381a.f22244a = i7;
            c2381a.f22245b = i10;
            c2381a.c = i11;
            return c2381a;
        }
        C2381a c2381a2 = new C2381a();
        c2381a2.f22244a = i7;
        c2381a2.f22245b = i10;
        c2381a2.c = i11;
        return c2381a2;
    }

    public void L(AbstractC1274a abstractC1274a) {
        A7.m.f(abstractC1274a, "body");
        J("PATCH", abstractC1274a);
    }

    public void M(AbstractC1274a abstractC1274a) {
        A7.m.f(abstractC1274a, "body");
        J("POST", abstractC1274a);
    }

    public void N(C2381a c2381a) {
        ((ArrayList) this.f4400n).add(c2381a);
        int i7 = c2381a.f22244a;
        C2404x c2404x = (C2404x) this.o;
        if (i7 == 1) {
            c2404x.d(c2381a.f22245b, c2381a.c);
            return;
        }
        if (i7 == 2) {
            int i10 = c2381a.f22245b;
            int i11 = c2381a.c;
            RecyclerView recyclerView = c2404x.f22362a;
            recyclerView.M(i10, i11, false);
            recyclerView.f12146r0 = true;
            return;
        }
        if (i7 == 4) {
            c2404x.c(c2381a.f22245b, c2381a.c);
        } else if (i7 == 8) {
            c2404x.e(c2381a.f22245b, c2381a.c);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + c2381a);
        }
    }

    public void O(AbstractC1274a abstractC1274a) {
        A7.m.f(abstractC1274a, "body");
        J("PUT", abstractC1274a);
    }

    public void P(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            C2381a c2381a = (C2381a) arrayList.get(i7);
            c2381a.getClass();
            ((E1.c) this.f4399m).c(c2381a);
        }
        arrayList.clear();
    }

    public void Q(Runnable runnable, String str) {
        try {
            if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {
                runnable.run();
            } else {
                io.sentry.android.core.B b10 = (io.sentry.android.core.B) this.l;
                ((Handler) b10.f16042a).post(new T(this, runnable, str, 1));
            }
        } catch (Throwable unused) {
            if (str != null) {
                ((SentryAndroidOptions) this.f4400n).getLogger().k(EnumC1495p1.WARNING, "Failed to execute ".concat(str), new Object[0]);
            }
        }
    }

    public int R(int i7, int i10) {
        int i11;
        int i12;
        ArrayList arrayList = (ArrayList) this.f4400n;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            C2381a c2381a = (C2381a) arrayList.get(size);
            int i13 = c2381a.f22244a;
            if (i13 == 8) {
                int i14 = c2381a.f22245b;
                int i15 = c2381a.c;
                if (i14 < i15) {
                    i12 = i14;
                    i11 = i15;
                } else {
                    i11 = i14;
                    i12 = i15;
                }
                if (i7 < i12 || i7 > i11) {
                    if (i7 < i14) {
                        if (i10 == 1) {
                            c2381a.f22245b = i14 + 1;
                            c2381a.c = i15 + 1;
                        } else if (i10 == 2) {
                            c2381a.f22245b = i14 - 1;
                            c2381a.c = i15 - 1;
                        }
                    }
                } else if (i12 == i14) {
                    if (i10 == 1) {
                        c2381a.c = i15 + 1;
                    } else if (i10 == 2) {
                        c2381a.c = i15 - 1;
                    }
                    i7++;
                } else {
                    if (i10 == 1) {
                        c2381a.f22245b = i14 + 1;
                    } else if (i10 == 2) {
                        c2381a.f22245b = i14 - 1;
                    }
                    i7--;
                }
            } else {
                int i16 = c2381a.f22245b;
                if (i16 <= i7) {
                    if (i13 == 1) {
                        i7 -= c2381a.c;
                    } else if (i13 == 2) {
                        i7 += c2381a.c;
                    }
                } else if (i10 == 1) {
                    c2381a.f22245b = i16 + 1;
                } else if (i10 == 2) {
                    c2381a.f22245b = i16 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            C2381a c2381a2 = (C2381a) arrayList.get(size2);
            if (c2381a2.f22244a == 8) {
                int i17 = c2381a2.c;
                if (i17 == c2381a2.f22245b || i17 < 0) {
                    arrayList.remove(size2);
                    ((E1.c) this.f4399m).c(c2381a2);
                }
            } else if (c2381a2.c <= 0) {
                arrayList.remove(size2);
                ((E1.c) this.f4399m).c(c2381a2);
            }
        }
        return i7;
    }

    public void S(c9.w wVar) {
        A7.m.f(wVar, "url");
        this.f4399m = wVar;
    }

    public void T(String str) {
        A7.m.f(str, "url");
        if (Q8.o.z0(str, "ws:", true)) {
            String strSubstring = str.substring(3);
            A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
            str = "http:".concat(strSubstring);
        } else if (Q8.o.z0(str, "wss:", true)) {
            String strSubstring2 = str.substring(4);
            A7.m.e(strSubstring2, "this as java.lang.String).substring(startIndex)");
            str = "https:".concat(strSubstring2);
        }
        A7.m.f(str, "<this>");
        c9.v vVar = new c9.v();
        vVar.f(null, str);
        this.f4399m = vVar.c();
    }

    @Override // Y2.d
    public int a(long j3) {
        long[] jArr = (long[]) this.f4400n;
        int iB = AbstractC1343A.b(jArr, j3, false);
        if (iB < jArr.length) {
            return iB;
        }
        return -1;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // N0.t
    public float b() {
        return ((Number) this.o.getValue()).floatValue();
    }

    @Override // N0.t
    public boolean c() {
        ArrayList arrayList = (ArrayList) this.l;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (((s) arrayList.get(i7)).f4407a.c()) {
                return true;
            }
        }
        return false;
    }

    @Override // Y2.d
    public long d(int i7) {
        return ((long[]) this.f4400n)[i7];
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // N0.t
    public float e() {
        return ((Number) this.f4401p.getValue()).floatValue();
    }

    public void f(E3.f fVar, Class cls) {
        ((ArrayList) this.o).add(new m7.i(fVar, cls));
    }

    @Override // Y2.d
    public List g(long j3) {
        C1220c c1220c = (C1220c) this.f4399m;
        ArrayList<Pair> arrayList = new ArrayList();
        c1220c.g(j3, c1220c.f14390h, arrayList);
        TreeMap treeMap = new TreeMap();
        c1220c.i(j3, false, c1220c.f14390h, treeMap);
        HashMap map = (HashMap) this.f4401p;
        c1220c.h(j3, (Map) this.o, map, c1220c.f14390h, treeMap);
        ArrayList arrayList2 = new ArrayList();
        for (Pair pair : arrayList) {
            String str = (String) ((HashMap) this.l).get(pair.second);
            if (str != null) {
                byte[] bArrDecode = Base64.decode(str, 0);
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
                C1223f c1223f = (C1223f) map.get(pair.first);
                c1223f.getClass();
                arrayList2.add(new C1289b(null, null, null, bitmapDecodeByteArray, c1223f.c, 0, c1223f.f14406e, c1223f.f14405b, 0, Integer.MIN_VALUE, -3.4028235E38f, c1223f.f14407f, c1223f.f14408g, false, -16777216, c1223f.f14411j, 0.0f));
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            C1223f c1223f2 = (C1223f) map.get(entry.getKey());
            c1223f2.getClass();
            C1288a c1288a = (C1288a) entry.getValue();
            CharSequence charSequence = c1288a.f14756a;
            charSequence.getClass();
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) charSequence;
            for (C1218a c1218a : (C1218a[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), C1218a.class)) {
                spannableStringBuilder.replace(spannableStringBuilder.getSpanStart(c1218a), spannableStringBuilder.getSpanEnd(c1218a), (CharSequence) "");
            }
            for (int i7 = 0; i7 < spannableStringBuilder.length(); i7++) {
                if (spannableStringBuilder.charAt(i7) == ' ') {
                    int i10 = i7 + 1;
                    int i11 = i10;
                    while (i11 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i11) == ' ') {
                        i11++;
                    }
                    int i12 = i11 - i10;
                    if (i12 > 0) {
                        spannableStringBuilder.delete(i7, i12 + i7);
                    }
                }
            }
            if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(0) == ' ') {
                spannableStringBuilder.delete(0, 1);
            }
            for (int i13 = 0; i13 < spannableStringBuilder.length() - 1; i13++) {
                if (spannableStringBuilder.charAt(i13) == '\n') {
                    int i14 = i13 + 1;
                    if (spannableStringBuilder.charAt(i14) == ' ') {
                        spannableStringBuilder.delete(i14, i13 + 2);
                    }
                }
            }
            if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == ' ') {
                spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
            }
            for (int i15 = 0; i15 < spannableStringBuilder.length() - 1; i15++) {
                if (spannableStringBuilder.charAt(i15) == ' ') {
                    int i16 = i15 + 1;
                    if (spannableStringBuilder.charAt(i16) == '\n') {
                        spannableStringBuilder.delete(i15, i16);
                    }
                }
            }
            if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == '\n') {
                spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
            }
            c1288a.f14758e = c1223f2.c;
            c1288a.f14759f = c1223f2.d;
            c1288a.f14760g = c1223f2.f14406e;
            c1288a.f14761h = c1223f2.f14405b;
            c1288a.l = c1223f2.f14407f;
            c1288a.f14764k = c1223f2.f14410i;
            c1288a.f14763j = c1223f2.f14409h;
            c1288a.f14767p = c1223f2.f14411j;
            arrayList2.add(c1288a.a());
        }
        return arrayList2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r1v1, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r2v1, types: [A7.o, z7.a] */
    @Override // m7.f
    public Object getValue() {
        f0 f0Var = (f0) this.l;
        if (f0Var != null) {
            return f0Var;
        }
        k0 k0Var = (k0) ((A7.o) this.f4400n).a();
        h0 h0Var = (h0) ((A7.o) this.o).a();
        AbstractC0992b abstractC0992b = (AbstractC0992b) ((A7.o) this.f4401p).a();
        A7.m.f(k0Var, "store");
        A7.m.f(h0Var, "factory");
        A7.m.f(abstractC0992b, "extras");
        P p10 = new P(k0Var, h0Var, abstractC0992b);
        InterfaceC0318c interfaceC0318c = (InterfaceC0318c) this.f4399m;
        A7.m.f(interfaceC0318c, "modelClass");
        String strA = interfaceC0318c.a();
        if (strA == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        f0 f0VarS = p10.s(interfaceC0318c, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(strA));
        this.l = f0VarS;
        return f0VarS;
    }

    @Override // Y2.d
    public int h() {
        return ((long[]) this.f4400n).length;
    }

    @Override // h8.m
    public void i() {
        ((B8.D) this.f4400n).i();
        C2448a c2448a = new C2448a((R7.b) AbstractC1998m.r0((ArrayList) this.l));
        ((HashMap) ((B8.D) this.o).f748n).put((C2061f) this.f4401p, c2448a);
    }

    @Override // h8.m
    public void j(C2061f c2061f, t8.f fVar) {
        ((B8.D) this.f4399m).j(c2061f, fVar);
    }

    @Override // h8.m
    public h8.n k(C2061f c2061f) {
        return ((B8.D) this.f4399m).k(c2061f);
    }

    @Override // h8.m
    public h8.m l(C2057b c2057b, C2061f c2061f) {
        return ((B8.D) this.f4399m).l(c2057b, c2061f);
    }

    @Override // h8.m
    public void m(C2061f c2061f, C2057b c2057b, C2061f c2061f2) {
        ((B8.D) this.f4399m).m(c2061f, c2057b, c2061f2);
    }

    public void n(H3.a aVar, Class cls) {
        ((ArrayList) this.f4399m).add(new m7.i(aVar, cls));
    }

    public void o(String str, String str2) {
        A7.m.f(str, "name");
        A7.m.f(str2, "value");
        ((A7.D) this.o).g(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.text.Bidi p(int r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r14.o
            boolean[] r0 = (boolean[]) r0
            boolean r1 = r0[r15]
            java.lang.Object r2 = r14.f4400n
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            if (r1 == 0) goto L13
            java.lang.Object r14 = r2.get(r15)
            java.text.Bidi r14 = (java.text.Bidi) r14
            return r14
        L13:
            java.lang.Object r1 = r14.l
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            r3 = 0
            if (r15 != 0) goto L1c
            r4 = r3
            goto L28
        L1c:
            int r4 = r15 + (-1)
            java.lang.Object r4 = r1.get(r4)
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
        L28:
            java.lang.Object r1 = r1.get(r15)
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            int r10 = r1 - r4
            java.lang.Object r5 = r14.f4401p
            char[] r5 = (char[]) r5
            if (r5 == 0) goto L40
            int r6 = r5.length
            if (r6 >= r10) goto L3e
            goto L40
        L3e:
            r12 = r5
            goto L43
        L40:
            char[] r5 = new char[r10]
            goto L3e
        L43:
            java.lang.Object r5 = r14.f4399m
            android.text.Layout r5 = (android.text.Layout) r5
            java.lang.CharSequence r6 = r5.getText()
            android.text.TextUtils.getChars(r6, r4, r1, r12, r3)
            boolean r1 = java.text.Bidi.requiresBidi(r12, r3, r10)
            r4 = 1
            r13 = 0
            if (r1 == 0) goto L78
            int r1 = r14.D(r15)
            int r1 = r5.getLineForOffset(r1)
            int r1 = r5.getParagraphDirection(r1)
            r5 = -1
            if (r1 != r5) goto L67
            r11 = r4
            goto L68
        L67:
            r11 = r3
        L68:
            java.text.Bidi r1 = new java.text.Bidi
            r9 = 0
            r7 = 0
            r8 = 0
            r5 = r1
            r6 = r12
            r5.<init>(r6, r7, r8, r9, r10, r11)
            int r3 = r1.getRunCount()
            if (r3 != r4) goto L79
        L78:
            r1 = r13
        L79:
            r2.set(r15, r1)
            r0[r15] = r4
            if (r1 == 0) goto L89
            java.lang.Object r15 = r14.f4401p
            char[] r15 = (char[]) r15
            if (r12 != r15) goto L88
            r12 = r13
            goto L89
        L88:
            r12 = r15
        L89:
            r14.f4401p = r12
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: N0.q.p(int):java.text.Bidi");
    }

    @Override // h8.m
    public void q(C2061f c2061f, Object obj) {
        ((B8.D) this.f4399m).q(c2061f, obj);
    }

    public H0.b r() {
        Map mapUnmodifiableMap;
        c9.w wVar = (c9.w) this.f4399m;
        if (wVar == null) {
            throw new IllegalStateException("url == null");
        }
        String str = (String) this.f4400n;
        c9.u uVarM = ((A7.D) this.o).m();
        AbstractC1274a abstractC1274a = (AbstractC1274a) this.f4401p;
        LinkedHashMap linkedHashMap = (LinkedHashMap) this.l;
        byte[] bArr = d9.b.f13645a;
        A7.m.f(linkedHashMap, "<this>");
        if (linkedHashMap.isEmpty()) {
            mapUnmodifiableMap = n7.v.l;
        } else {
            mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(linkedHashMap));
            A7.m.e(mapUnmodifiableMap, "{\n    Collections.unmodi…(LinkedHashMap(this))\n  }");
        }
        return new H0.b(wVar, str, uVarM, abstractC1274a, mapUnmodifiableMap);
    }

    public void s(C1046g c1046g) {
        A7.m.f(c1046g, "cacheControl");
        String string = c1046g.toString();
        if (string.length() == 0) {
            ((A7.D) this.o).x("Cache-Control");
        } else {
            G("Cache-Control", string);
        }
    }

    public C1427c t() {
        FrameMetricsAggregator frameMetricsAggregator;
        int i7;
        int i10;
        SparseIntArray sparseIntArray;
        if (!H() || (frameMetricsAggregator = (FrameMetricsAggregator) this.f4399m) == null) {
            return null;
        }
        SparseIntArray[] sparseIntArrayArr = (SparseIntArray[]) frameMetricsAggregator.f11736a.f15238b;
        int i11 = 0;
        if (sparseIntArrayArr.length <= 0 || (sparseIntArray = sparseIntArrayArr[0]) == null) {
            i7 = 0;
            i10 = 0;
        } else {
            int i12 = 0;
            i7 = 0;
            i10 = 0;
            while (i11 < sparseIntArray.size()) {
                int iKeyAt = sparseIntArray.keyAt(i11);
                int iValueAt = sparseIntArray.valueAt(i11);
                i12 += iValueAt;
                if (iKeyAt > 700) {
                    i10 += iValueAt;
                } else if (iKeyAt > 16) {
                    i7 += iValueAt;
                }
                i11++;
            }
            i11 = i12;
        }
        return new C1427c(i11, i7, i10);
    }

    public boolean u(int i7) {
        ArrayList arrayList = (ArrayList) this.f4400n;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            C2381a c2381a = (C2381a) arrayList.get(i10);
            int i11 = c2381a.f22244a;
            if (i11 == 8) {
                if (z(c2381a.c, i10 + 1) == i7) {
                    return true;
                }
            } else if (i11 == 1) {
                int i12 = c2381a.f22245b;
                int i13 = c2381a.c + i12;
                while (i12 < i13) {
                    if (z(i12, i10 + 1) == i7) {
                        return true;
                    }
                    i12++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public void v() {
        ArrayList arrayList = (ArrayList) this.f4400n;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((C2404x) this.o).a((C2381a) arrayList.get(i7));
        }
        P(arrayList);
        ArrayList arrayList2 = (ArrayList) this.l;
        int size2 = arrayList2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            C2381a c2381a = (C2381a) arrayList2.get(i10);
            int i11 = c2381a.f22244a;
            C2404x c2404x = (C2404x) this.o;
            if (i11 == 1) {
                c2404x.a(c2381a);
                c2404x.d(c2381a.f22245b, c2381a.c);
            } else if (i11 == 2) {
                c2404x.a(c2381a);
                int i12 = c2381a.f22245b;
                int i13 = c2381a.c;
                RecyclerView recyclerView = c2404x.f22362a;
                recyclerView.M(i12, i13, true);
                recyclerView.f12146r0 = true;
                recyclerView.f12140o0.c += i13;
            } else if (i11 == 4) {
                c2404x.a(c2381a);
                c2404x.c(c2381a.f22245b, c2381a.c);
            } else if (i11 == 8) {
                c2404x.a(c2381a);
                c2404x.e(c2381a.f22245b, c2381a.c);
            }
        }
        P(arrayList2);
    }

    public void w(AbstractC1274a abstractC1274a) {
        J("DELETE", abstractC1274a);
    }

    public void x(C2381a c2381a) {
        int i7;
        int i10 = c2381a.f22244a;
        if (i10 == 1 || i10 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iR = R(c2381a.f22245b, i10);
        int i11 = c2381a.f22245b;
        int i12 = c2381a.f22244a;
        if (i12 == 2) {
            i7 = 0;
        } else {
            if (i12 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + c2381a);
            }
            i7 = 1;
        }
        int i13 = 1;
        for (int i14 = 1; i14 < c2381a.c; i14++) {
            int iR2 = R((i7 * i14) + c2381a.f22245b, c2381a.f22244a);
            int i15 = c2381a.f22244a;
            if (i15 == 2 ? iR2 != iR : !(i15 == 4 && iR2 == iR + 1)) {
                C2381a c2381aK = K(i15, iR, i13);
                y(c2381aK, i11);
                ((E1.c) this.f4399m).c(c2381aK);
                if (c2381a.f22244a == 4) {
                    i11 += i13;
                }
                i13 = 1;
                iR = iR2;
            } else {
                i13++;
            }
        }
        ((E1.c) this.f4399m).c(c2381a);
        if (i13 > 0) {
            C2381a c2381aK2 = K(c2381a.f22244a, iR, i13);
            y(c2381aK2, i11);
            ((E1.c) this.f4399m).c(c2381aK2);
        }
    }

    public void y(C2381a c2381a, int i7) {
        C2404x c2404x = (C2404x) this.o;
        c2404x.a(c2381a);
        int i10 = c2381a.f22244a;
        if (i10 != 2) {
            if (i10 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            c2404x.c(i7, c2381a.c);
        } else {
            int i11 = c2381a.c;
            RecyclerView recyclerView = c2404x.f22362a;
            recyclerView.M(i7, i11, true);
            recyclerView.f12146r0 = true;
            recyclerView.f12140o0.c += i11;
        }
    }

    public int z(int i7, int i10) {
        ArrayList arrayList = (ArrayList) this.f4400n;
        int size = arrayList.size();
        while (i10 < size) {
            C2381a c2381a = (C2381a) arrayList.get(i10);
            int i11 = c2381a.f22244a;
            if (i11 == 8) {
                int i12 = c2381a.f22245b;
                if (i12 == i7) {
                    i7 = c2381a.c;
                } else {
                    if (i12 < i7) {
                        i7--;
                    }
                    if (c2381a.c <= i7) {
                        i7++;
                    }
                }
            } else {
                int i13 = c2381a.f22245b;
                if (i13 > i7) {
                    continue;
                } else if (i11 == 2) {
                    int i14 = c2381a.c;
                    if (i7 < i13 + i14) {
                        return -1;
                    }
                    i7 -= i14;
                } else if (i11 == 1) {
                    i7 += c2381a.c;
                }
            }
            i10++;
        }
        return i7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public q(InterfaceC0318c interfaceC0318c, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3) {
        A7.m.f(interfaceC0318c, "viewModelClass");
        this.f4399m = interfaceC0318c;
        this.f4400n = (A7.o) interfaceC2772a;
        this.o = (A7.o) interfaceC2772a2;
        this.f4401p = (A7.o) interfaceC2772a3;
    }

    public q(C1220c c1220c, HashMap map, HashMap map2, HashMap map3) {
        this.f4399m = c1220c;
        this.f4401p = map2;
        this.l = map3;
        this.o = Collections.unmodifiableMap(map);
        TreeSet treeSet = new TreeSet();
        int i7 = 0;
        c1220c.d(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            jArr[i7] = ((Long) it.next()).longValue();
            i7++;
        }
        this.f4400n = jArr;
    }

    public q(C0411f c0411f, L l, List list, a1.b bVar, InterfaceC0702n interfaceC0702n) {
        u uVar;
        String strSubstring;
        ArrayList arrayList;
        int i7;
        int i10;
        ArrayList arrayList2;
        String str;
        int i11;
        int i12;
        C0411f c0411f2 = c0411f;
        this.f4399m = c0411f2;
        this.f4400n = list;
        m7.g gVar = m7.g.f19257m;
        this.o = h6.r.n(gVar, new p(this, 1));
        this.f4401p = h6.r.n(gVar, new p(this, 0));
        C0411f c0411f3 = AbstractC0412g.f4375a;
        int length = c0411f2.l.length();
        List list2 = c0411f2.f4374n;
        list2 = list2 == null ? n7.u.l : list2;
        ArrayList arrayList3 = new ArrayList();
        int size = list2.size();
        int i13 = 0;
        int i14 = 0;
        while (true) {
            uVar = l.f4359b;
            if (i13 >= size) {
                break;
            }
            C0409d c0409d = (C0409d) list2.get(i13);
            u uVar2 = (u) c0409d.f4371a;
            int i15 = c0409d.f4372b;
            if (i15 != i14) {
                arrayList3.add(new C0409d(i14, i15, uVar));
            }
            u uVarA = uVar.a(uVar2);
            int i16 = c0409d.c;
            arrayList3.add(new C0409d(i15, i16, uVarA));
            i13++;
            i14 = i16;
        }
        if (i14 != length) {
            arrayList3.add(new C0409d(i14, length, uVar));
        }
        if (arrayList3.isEmpty()) {
            arrayList3.add(new C0409d(0, 0, uVar));
        }
        ArrayList arrayList4 = new ArrayList(arrayList3.size());
        int size2 = arrayList3.size();
        int i17 = 0;
        while (i17 < size2) {
            C0409d c0409d2 = (C0409d) arrayList3.get(i17);
            int i18 = c0409d2.f4372b;
            int i19 = c0409d2.c;
            if (i18 != i19) {
                strSubstring = c0411f2.l.substring(i18, i19);
                A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            } else {
                strSubstring = "";
            }
            C0411f c0411f4 = new C0411f(strSubstring, AbstractC0412g.b(c0411f2, i18, i19), null, null);
            u uVar3 = (u) c0409d2.f4371a;
            if (Y0.k.a(uVar3.f4410b, Integer.MIN_VALUE)) {
                arrayList = arrayList3;
                i7 = i17;
                i10 = size2;
                arrayList2 = arrayList4;
                str = strSubstring;
                uVar3 = new u(uVar3.f4409a, uVar.f4410b, uVar3.c, uVar3.d, uVar3.f4411e, uVar3.f4412f, uVar3.f4413g, uVar3.f4414h, uVar3.f4415i);
            } else {
                arrayList2 = arrayList4;
                i10 = size2;
                arrayList = arrayList3;
                i7 = i17;
                str = strSubstring;
            }
            L l10 = new L(l.f4358a, uVar.a(uVar3));
            List listB = c0411f4.b();
            List list3 = (List) this.f4400n;
            ArrayList arrayList5 = new ArrayList(list3.size());
            int size3 = list3.size();
            int i20 = 0;
            while (true) {
                i11 = c0409d2.f4372b;
                if (i20 >= size3) {
                    break;
                }
                Object obj = list3.get(i20);
                C0409d c0409d3 = (C0409d) obj;
                if (AbstractC0412g.c(i11, i19, c0409d3.f4372b, c0409d3.c)) {
                    arrayList5.add(obj);
                }
                i20++;
            }
            ArrayList arrayList6 = new ArrayList(arrayList5.size());
            int size4 = arrayList5.size();
            for (int i21 = 0; i21 < size4; i21++) {
                C0409d c0409d4 = (C0409d) arrayList5.get(i21);
                int i22 = c0409d4.f4372b;
                if (i11 <= i22 && (i12 = c0409d4.c) <= i19) {
                    arrayList6.add(new C0409d(i22 - i11, i12 - i11, c0409d4.f4371a));
                } else {
                    throw new IllegalArgumentException("placeholder can not overlap with paragraph.");
                }
            }
            s sVar = new s(new V0.c(str, l10, listB, arrayList6, interfaceC0702n, bVar), i11, i19);
            arrayList4 = arrayList2;
            arrayList4.add(sVar);
            i17 = i7 + 1;
            arrayList3 = arrayList;
            size2 = i10;
            c0411f2 = c0411f;
        }
        this.l = arrayList4;
    }

    public q(SentryAndroidOptions sentryAndroidOptions) {
        io.sentry.android.core.B b10 = new io.sentry.android.core.B();
        this.f4399m = null;
        this.o = new ConcurrentHashMap();
        this.f4401p = new WeakHashMap();
        if (h6.j.n("androidx.core.app.FrameMetricsAggregator", sentryAndroidOptions.getLogger()) != null) {
            this.f4399m = new FrameMetricsAggregator();
        }
        this.f4400n = sentryAndroidOptions;
        this.l = b10;
    }

    public q(C1036a c1036a, c8.e eVar, m7.f fVar) {
        A7.m.f(eVar, "typeParameterResolver");
        this.f4399m = c1036a;
        this.f4400n = eVar;
        this.o = fVar;
        this.f4401p = fVar;
        this.l = new P(this, eVar);
    }

    public q() {
        this.l = new LinkedHashMap();
        this.f4400n = "GET";
        this.o = new A7.D(5, false);
    }
}
