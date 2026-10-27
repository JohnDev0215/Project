package n3;

import G.g0;
import android.os.Bundle;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: renamed from: n3.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@N("navigation")
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Ln3/D;", "Ln3/O;", "Ln3/B;", "navigation-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class C1929D extends O {
    public final P c;

    public C1929D(P p10) {
        A7.m.f(p10, "navigatorProvider");
        this.c = p10;
    }

    @Override // n3.O
    public final void d(List list, C1933H c1933h) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C1945j c1945j = (C1945j) it.next();
            y yVar = c1945j.f19865m;
            A7.m.d(yVar, "null cannot be cast to non-null type androidx.navigation.NavGraph");
            C1927B c1927b = (C1927B) yVar;
            A7.z zVar = new A7.z();
            zVar.l = c1945j.c();
            int i7 = c1927b.f19791v;
            String str = c1927b.f19793x;
            if (i7 == 0 && str == null) {
                StringBuilder sb = new StringBuilder("no start destination defined via app:startDestination for ");
                int i10 = c1927b.f19917q;
                sb.append(i10 != 0 ? String.valueOf(i10) : "the root navigation");
                throw new IllegalStateException(sb.toString().toString());
            }
            y yVarR = str != null ? c1927b.r(str, false) : (y) c1927b.f19790u.c(i7);
            if (yVarR == null) {
                if (c1927b.f19792w == null) {
                    String strValueOf = c1927b.f19793x;
                    if (strValueOf == null) {
                        strValueOf = String.valueOf(c1927b.f19791v);
                    }
                    c1927b.f19792w = strValueOf;
                }
                String str2 = c1927b.f19792w;
                A7.m.c(str2);
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("navigation destination ", str2, " is not a direct child of this NavGraph"));
            }
            if (str != null) {
                if (!str.equals(yVarR.f19918r)) {
                    w wVarQ = yVarR.q(str);
                    Bundle bundle = wVarQ != null ? wVarQ.f19908m : null;
                    if (bundle != null && !bundle.isEmpty()) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putAll(bundle);
                        Bundle bundle3 = (Bundle) zVar.l;
                        if (bundle3 != null) {
                            bundle2.putAll(bundle3);
                        }
                        zVar.l = bundle2;
                    }
                }
                LinkedHashMap linkedHashMap = yVarR.f19916p;
                if (n7.z.y(linkedHashMap).isEmpty()) {
                    continue;
                } else {
                    ArrayList arrayListP = h6.e.p(n7.z.y(linkedHashMap), new g0(zVar, 1));
                    if (!arrayListP.isEmpty()) {
                        throw new IllegalArgumentException(("Cannot navigate to startDestination " + yVarR + ". Missing required arguments [" + arrayListP + ']').toString());
                    }
                }
            }
            O oB = this.c.b(yVarR.l);
            C1948m c1948mB = b();
            Bundle bundleD = yVarR.d((Bundle) zVar.l);
            C1930E c1930e = c1948mB.f19883h;
            oB.d(AbstractC1387d.j(l9.n.K(c1930e.f19800a, yVarR, bundleD, c1930e.k(), c1930e.f19811p)), c1933h);
        }
    }

    @Override // n3.O
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public C1927B a() {
        return new C1927B(this);
    }
}
