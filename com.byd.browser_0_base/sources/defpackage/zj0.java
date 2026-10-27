package defpackage;

import java.util.HashMap;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FastCheckoutCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zj0 {
    public final ek0 a = new ek0();
    public final PropertyModel b;
    public gk0 c;
    public ji d;

    /* JADX WARN: Type inference failed for: r2v3, types: [T, ya1] */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, ya1] */
    public zj0() {
        HashMap mapE = PropertyModel.e(fk0.n);
        PropertyModel.n nVar = fk0.a;
        int i = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = false;
        mapE.put(nVar, aVar);
        PropertyModel.p pVar = fk0.b;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = 0;
        mapE.put(pVar, dVar);
        PropertyModel.l<ya1> lVar = fk0.d;
        ?? ya1Var = new ya1();
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = ya1Var;
        mapE.put(lVar, gVar);
        PropertyModel.l<ya1> lVar2 = fk0.f;
        ?? ya1Var2 = new ya1();
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = ya1Var2;
        this.b = C0175b.p(mapE, lVar2, gVar2, mapE);
    }
}
