package D8;

import F8.AbstractC0272g;
import java.util.Collection;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class e extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1223m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f1224n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(g gVar, int i7) {
        super(0);
        this.f1223m = i7;
        this.f1224n = gVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1223m) {
            case 0:
                y8.f fVar = y8.f.f24196m;
                y8.n.f24214a.getClass();
                return this.f1224n.i(fVar, y8.k.f24208n);
            default:
                g gVar = this.f1224n;
                gVar.f1226g.getClass();
                k kVar = gVar.f1229j;
                A7.m.f(kVar, "classDescriptor");
                Collection collectionO = ((AbstractC0272g) kVar.C()).o();
                A7.m.e(collectionO, "classDescriptor.typeConstructor.supertypes");
                return collectionO;
        }
    }
}
