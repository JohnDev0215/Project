package defpackage;

import android.content.res.ColorStateList;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: MenuButtonProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class df1 {
    public static final PropertyModel.o a;
    public static final PropertyModel.r<z6> b;
    public static final PropertyModel.r<String> c;
    public static final PropertyModel.n d;
    public static final PropertyModel.n e;
    public static final PropertyModel.n f;
    public static final PropertyModel.r<nw2<ef1>> g;
    public static final PropertyModel.r<a> h;
    public static final PropertyModel.r<b> i;
    public static final PropertyModel.o j;
    public static final v62[] k;

    /* JADX INFO: compiled from: MenuButtonProperties.java */
    public static class a {
        public final boolean a;
        public final boolean b;

        public a(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }
    }

    /* JADX INFO: compiled from: MenuButtonProperties.java */
    public static class b {
        public final ColorStateList a;
        public final int b;

        public b(int i, ColorStateList colorStateList) {
            this.a = colorStateList;
            this.b = i;
        }
    }

    static {
        PropertyModel.o oVar = new PropertyModel.o();
        a = oVar;
        PropertyModel.r<z6> rVar = new PropertyModel.r<>();
        b = rVar;
        PropertyModel.r<String> rVar2 = new PropertyModel.r<>();
        c = rVar2;
        PropertyModel.n nVar = new PropertyModel.n();
        d = nVar;
        PropertyModel.n nVar2 = new PropertyModel.n();
        e = nVar2;
        PropertyModel.n nVar3 = new PropertyModel.n();
        f = nVar3;
        PropertyModel.r<nw2<ef1>> rVar3 = new PropertyModel.r<>();
        g = rVar3;
        PropertyModel.r<a> rVar4 = new PropertyModel.r<>(null, true);
        h = rVar4;
        PropertyModel.r<b> rVar5 = new PropertyModel.r<>(null, true);
        i = rVar5;
        PropertyModel.o oVar2 = new PropertyModel.o();
        j = oVar2;
        k = new v62[]{oVar, rVar, rVar2, nVar, nVar2, nVar3, rVar3, rVar4, rVar5, oVar2};
    }
}
