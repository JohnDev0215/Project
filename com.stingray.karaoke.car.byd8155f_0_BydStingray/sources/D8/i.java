package D8;

import F8.AbstractC0267b;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.O;
import T7.AbstractC0783b;
import c8.C1036a;
import d8.C1120g;
import d8.C1122i;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractC0267b {
    public final /* synthetic */ int c = 0;
    public final E8.i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AbstractC0783b f1232e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(C1122i c1122i) {
        super(((C1036a) c1122i.f13588u.f4399m).f12411a);
        this.f1232e = c1122i;
        E8.l lVar = ((C1036a) c1122i.f13588u.f4399m).f12411a;
        C1120g c1120g = new C1120g(c1122i, 0);
        lVar.getClass();
        this.d = new E8.i(lVar, c1120g);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01d2  */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r6v23, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r6v25, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r6v33 */
    @Override // F8.AbstractC0272g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Collection b() {
        /*
            Method dump skipped, instruction units count: 950
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: D8.i.b():java.util.Collection");
    }

    @Override // F8.AbstractC0272g
    public final O d() {
        switch (this.c) {
            case 0:
                return O.f7181n;
            default:
                return ((C1036a) ((C1122i) this.f1232e).f13588u.f4399m).f12420m;
        }
    }

    @Override // F8.AbstractC0267b
    /* JADX INFO: renamed from: i */
    public final InterfaceC0652e n() {
        switch (this.c) {
            case 0:
                return (k) this.f1232e;
            default:
                return (C1122i) this.f1232e;
        }
    }

    @Override // F8.K
    public final boolean m() {
        switch (this.c) {
        }
        return true;
    }

    @Override // F8.AbstractC0267b, F8.K
    public final InterfaceC0654g n() {
        switch (this.c) {
            case 0:
                return (k) this.f1232e;
            default:
                return (C1122i) this.f1232e;
        }
    }

    @Override // F8.K
    public final List p() {
        switch (this.c) {
        }
        return (List) this.d.a();
    }

    public final String toString() {
        switch (this.c) {
            case 0:
                String str = ((k) this.f1232e).getName().l;
                A7.m.e(str, "name.toString()");
                return str;
            default:
                String strB = ((C1122i) this.f1232e).getName().b();
                A7.m.e(strB, "name.asString()");
                return strB;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(k kVar) {
        super(((B8.i) kVar.f1248w.f779a).f762a);
        this.f1232e = kVar;
        E8.o oVar = ((B8.i) kVar.f1248w.f779a).f762a;
        h hVar = new h(kVar, 0);
        E8.l lVar = (E8.l) oVar;
        lVar.getClass();
        this.d = new E8.i(lVar, hVar);
    }
}
