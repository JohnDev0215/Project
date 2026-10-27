package defpackage;

import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.View;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ScrimProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jf2 {
    public static final PropertyModel.j a;
    public static final PropertyModel.h b;
    public static final PropertyModel.l<View> c;
    public static final PropertyModel.h d;
    public static final PropertyModel.l<Callback<Boolean>> e;
    public static final PropertyModel.l<Runnable> f;
    public static final PropertyModel.o g;
    public static final PropertyModel.p h;
    public static final PropertyModel.r<Drawable> i;
    public static final PropertyModel.r<GestureDetector> j;
    public static final PropertyModel.n k;
    public static final v62[] l;
    public static final v62[] m;

    static {
        PropertyModel.j jVar = new PropertyModel.j();
        a = jVar;
        PropertyModel.h hVar = new PropertyModel.h();
        b = hVar;
        PropertyModel.l<View> lVar = new PropertyModel.l<>();
        c = lVar;
        PropertyModel.h hVar2 = new PropertyModel.h();
        d = hVar2;
        PropertyModel.l<Callback<Boolean>> lVar2 = new PropertyModel.l<>();
        e = lVar2;
        PropertyModel.l<Runnable> lVar3 = new PropertyModel.l<>();
        f = lVar3;
        PropertyModel.o oVar = new PropertyModel.o();
        g = oVar;
        PropertyModel.p pVar = new PropertyModel.p();
        h = pVar;
        PropertyModel.r<Drawable> rVar = new PropertyModel.r<>();
        i = rVar;
        PropertyModel.r<GestureDetector> rVar2 = new PropertyModel.r<>();
        j = rVar2;
        PropertyModel.n nVar = new PropertyModel.n();
        k = nVar;
        v62[] v62VarArr = {jVar, hVar, lVar, hVar2, lVar2, lVar3, oVar};
        l = v62VarArr;
        m = PropertyModel.f(v62VarArr, new v62[]{pVar, rVar, rVar2, nVar});
    }
}
