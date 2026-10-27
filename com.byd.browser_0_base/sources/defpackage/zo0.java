package defpackage;

import java.util.HashMap;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FooterItemProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zo0 {
    public static final PropertyModel.j a;
    public static final PropertyModel.l<Runnable> b;
    public static final v62[] c;

    static {
        PropertyModel.j jVar = new PropertyModel.j("label");
        a = jVar;
        PropertyModel.l<Runnable> lVar = new PropertyModel.l<>("on_click_handler");
        b = lVar;
        c = new v62[]{jVar, lVar};
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PropertyModel a(int i, Runnable runnable) {
        HashMap mapE = PropertyModel.e(c);
        PropertyModel.j jVar = a;
        int i2 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(jVar, dVar);
        PropertyModel.l<Runnable> lVar = b;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = runnable;
        return C0175b.p(mapE, lVar, gVar, mapE);
    }
}
