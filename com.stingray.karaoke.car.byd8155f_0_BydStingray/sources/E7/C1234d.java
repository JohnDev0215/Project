package e7;

import A7.m;
import N0.q;
import N1.C0420a;
import P8.n;
import c9.C;
import c9.F;
import c9.H;
import c9.InterfaceC1047h;
import c9.v;
import c9.w;
import c9.y;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: e7.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1234d extends A9.b {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final y f14474s;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f14475n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f14476p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public InterfaceC1047h f14477q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public H f14478r;

    static {
        Pattern pattern = y.d;
        f14474s = n.L("text/plain;charset=UTF-8");
    }

    public final void a1() {
        boolean z6 = C1235e.f14479C;
        String str = this.o;
        String str2 = this.f14475n;
        if (z6) {
            C1235e.B.fine("xhr open " + str2 + ": " + str);
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        if ("POST".equals(str2)) {
            treeMap.put("Content-type", new LinkedList(Collections.singletonList("text/plain;charset=UTF-8")));
        }
        treeMap.put("Accept", new LinkedList(Collections.singletonList("*/*")));
        N0("requestHeaders", treeMap);
        String str3 = this.f14476p;
        if (z6) {
            C1235e.B.fine("sending xhr with url " + str + " | data " + str3);
        }
        q qVar = new q();
        for (Map.Entry entry : treeMap.entrySet()) {
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                qVar.o((String) entry.getKey(), (String) it.next());
            }
        }
        w wVarC = null;
        F fW = str3 != null ? com.bumptech.glide.d.w(str3, f14474s) : null;
        m.f(str, "<this>");
        try {
            v vVar = new v();
            vVar.f(null, str);
            wVarC = vVar.c();
        } catch (IllegalArgumentException unused) {
        }
        qVar.S(wVarC);
        qVar.J(str2, fW);
        ((C) this.f14477q).a(qVar.r()).e(new C0420a(this));
    }
}
