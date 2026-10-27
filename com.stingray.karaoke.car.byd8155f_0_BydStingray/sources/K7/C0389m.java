package K7;

import Q7.AbstractC0662o;
import Q7.InterfaceC0657j;
import T.C0746i0;
import h8.AbstractC1386c;
import h8.C1391h;
import l8.InterfaceC1788f;
import o8.AbstractC2062g;
import o8.C2061f;
import p8.C2138m;

/* JADX INFO: renamed from: K7.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0389m extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Q7.L f3806m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final j8.G f3807n;
    public final m8.e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final InterfaceC1788f f3808p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0746i0 f3809q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f3810r;

    public C0389m(Q7.L l, j8.G g7, m8.e eVar, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0) {
        String string;
        C1391h c1391h;
        String string2;
        A7.m.f(g7, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        this.f3806m = l;
        this.f3807n = g7;
        this.o = eVar;
        this.f3808p = interfaceC1788f;
        this.f3809q = c0746i0;
        if ((eVar.f19291m & 4) == 4) {
            string2 = interfaceC1788f.n(eVar.f19293p.f19281n).concat(interfaceC1788f.n(eVar.f19293p.o));
        } else {
            n8.d dVarB = n8.h.b(g7, interfaceC1788f, c0746i0, true);
            if (dVarB == null) {
                throw new r0("No field signature for property: " + l);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(Z7.w.a(dVarB.c));
            InterfaceC0657j interfaceC0657jO = l.o();
            A7.m.e(interfaceC0657jO, "descriptor.containingDeclaration");
            if (A7.m.a(l.e(), AbstractC0662o.d) && (interfaceC0657jO instanceof D8.k)) {
                C2138m c2138m = m8.k.f19333i;
                A7.m.e(c2138m, "classModuleName");
                Integer num = (Integer) AbstractC1386c.h(((D8.k) interfaceC0657jO).f1241p, c2138m);
                String strReplaceAll = AbstractC2062g.f20574a.l.matcher(num != null ? interfaceC1788f.n(num.intValue()) : "main").replaceAll("_");
                A7.m.e(strReplaceAll, "replaceAll(...)");
                string = "$".concat(strReplaceAll);
            } else if (!A7.m.a(l.e(), AbstractC0662o.f7189a) || !(interfaceC0657jO instanceof Q7.D) || (c1391h = ((D8.t) l).f1276Q) == null || c1391h.f15611m == null) {
                string = "";
            } else {
                StringBuilder sb2 = new StringBuilder("$");
                String strE = c1391h.l.e();
                A7.m.e(strE, "className.internalName");
                sb2.append(C2061f.e(Q8.h.a1('/', strE, strE)).b());
                string = sb2.toString();
            }
            sb.append(string);
            sb.append("()");
            sb.append(dVarB.d);
            string2 = sb.toString();
        }
        this.f3810r = string2;
    }

    @Override // K7.v0
    public final String i() {
        return this.f3810r;
    }
}
