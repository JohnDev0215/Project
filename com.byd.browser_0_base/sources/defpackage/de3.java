package defpackage;

import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TouchToFillCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class de3 {
    public final ke3 a;
    public final PropertyModel b;

    /* JADX WARN: Type inference failed for: r1v0, types: [T, x10] */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, p81] */
    public de3() {
        ke3 ke3Var = new ke3();
        this.a = ke3Var;
        ?? x10Var = new x10(10, ke3Var);
        PropertyModel.n nVar = le3.a;
        PropertyModel.l<p81<xa1>> lVar = le3.b;
        PropertyModel.l<Callback<Integer>> lVar2 = le3.c;
        HashMap mapE = PropertyModel.e(new v62[]{nVar, lVar, lVar2});
        int i = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = false;
        mapE.put(nVar, aVar);
        ?? p81Var = new p81();
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = p81Var;
        mapE.put(lVar, gVar);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = x10Var;
        this.b = C0175b.p(mapE, lVar2, gVar2, mapE);
    }
}
