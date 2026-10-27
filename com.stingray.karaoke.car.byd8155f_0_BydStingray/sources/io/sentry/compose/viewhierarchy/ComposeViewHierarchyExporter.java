package io.sentry.compose.viewhierarchy;

import C0.N;
import E0.F;
import L0.j;
import L0.u;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import g0.o;
import io.sentry.C1489n1;
import io.sentry.K;
import io.sentry.protocol.G;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import m0.d;

/* JADX INFO: loaded from: classes.dex */
public final class ComposeViewHierarchyExporter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final K f16505a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile C1489n1 f16506b;

    public ComposeViewHierarchyExporter(K k10) {
        this.f16505a = k10;
    }

    public static void a(C1489n1 c1489n1, G g7, F f10, F f11) {
        d dVarT;
        if (f11.W()) {
            G g10 = new G();
            Iterator it = f11.E().iterator();
            while (it.hasNext()) {
                o oVar = ((N) it.next()).f863a;
                if (oVar instanceof AppendedSemanticsElement) {
                    AppendedSemanticsElement appendedSemanticsElement = (AppendedSemanticsElement) oVar;
                    appendedSemanticsElement.getClass();
                    j<Map.Entry> jVar = new j();
                    jVar.f3909m = appendedSemanticsElement.f11692a;
                    appendedSemanticsElement.f11693b.b(jVar);
                    for (Map.Entry entry : jVar) {
                        String str = ((u) entry.getKey()).f3965a;
                        if ("SentryTag".equals(str) || "TestTag".equals(str)) {
                            if (entry.getValue() instanceof String) {
                                g10.o = (String) entry.getValue();
                            }
                        }
                    }
                }
            }
            int iS = f11.s();
            int I2 = f11.I();
            g10.f16668q = Double.valueOf(iS);
            g10.f16667p = Double.valueOf(I2);
            d dVarT2 = c1489n1.t(f11);
            if (dVarT2 != null) {
                double d = dVarT2.f18882a;
                double d10 = dVarT2.f18883b;
                if (f10 != null && (dVarT = c1489n1.t(f10)) != null) {
                    d -= (double) dVarT.f18882a;
                    d10 -= (double) dVarT.f18883b;
                }
                g10.f16669r = Double.valueOf(d);
                g10.f16670s = Double.valueOf(d10);
            }
            String str2 = g10.o;
            if (str2 != null) {
                g10.f16665m = str2;
            } else {
                g10.f16665m = "@Composable";
            }
            if (g7.f16673v == null) {
                g7.f16673v = new ArrayList();
            }
            g7.f16673v.add(g10);
            V.d dVarK = f11.K();
            int i7 = dVarK.f9279n;
            for (int i10 = 0; i10 < i7; i10++) {
                a(c1489n1, g10, f11, (F) dVarK.l[i10]);
            }
        }
    }
}
