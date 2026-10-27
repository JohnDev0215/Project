package g1;

import H7.w;
import io.sentry.C1489n1;
import k1.AbstractC1598c;
import q8.C2206j;

/* JADX INFO: renamed from: g1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1276c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f14706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f14707b;
    public final /* synthetic */ Object c;

    public C1276c(Object obj) {
        this.f14706a = obj;
    }

    public final Object a(w wVar, Object obj) {
        A7.m.f(wVar, "property");
        return this.f14706a;
    }

    public final void b(w wVar, Object obj) {
        AbstractC1598c abstractC1598cH;
        A7.m.f(wVar, "property");
        Object obj2 = this.f14706a;
        switch (this.f14707b) {
            case 1:
                A7.m.f(wVar, "property");
                if (((C2206j) this.c).f21320a) {
                    throw new IllegalStateException("Cannot modify readonly DescriptorRendererOptions");
                }
                break;
            default:
                A7.m.f(wVar, "property");
                break;
        }
        this.f14706a = obj;
        switch (this.f14707b) {
            case 0:
                C1286m c1286m = (C1286m) obj;
                k1.g gVar = ((C1277d) this.c).f14710b;
                String name = wVar.getName();
                A7.m.d(c1286m, "null cannot be cast to non-null type androidx.constraintlayout.compose.DimensionDescription");
                C1489n1 c1489n1 = c1286m.f14737b;
                boolean z6 = ((String) c1489n1.f16624m) == null;
                C1489n1 c1489n12 = c1286m.f14736a;
                C1489n1 c1489n13 = c1286m.c;
                if (z6 && ((String) c1489n13.f16624m) == null) {
                    abstractC1598cH = c1489n12.h();
                } else {
                    k1.g gVar2 = new k1.g(new char[0]);
                    if (((String) c1489n1.f16624m) != null) {
                        gVar2.G("min", c1489n1.h());
                    }
                    if (((String) c1489n13.f16624m) != null) {
                        gVar2.G("max", c1489n13.h());
                    }
                    gVar2.G("value", c1489n12.h());
                    abstractC1598cH = gVar2;
                }
                gVar.G(name, abstractC1598cH);
                return;
            default:
                A7.m.f(wVar, "property");
                return;
        }
    }

    public final String toString() {
        return "ObservableProperty(value=" + this.f14706a + ')';
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C1276c(Object obj, C2206j c2206j) {
        this(obj);
        this.f14707b = 1;
        this.c = c2206j;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C1276c(C1277d c1277d, C1286m c1286m) {
        this(c1286m);
        this.f14707b = 0;
        this.c = c1277d;
    }
}
