package defpackage;

import android.util.Pair;
import java.util.Date;
import org.chromium.base.Callback;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ListProperties.java */
/* JADX INFO: loaded from: classes.dex */
public interface t81 {
    public static final v62[] A;
    public static final PropertyModel.n m;
    public static final PropertyModel.r<Callback<OfflineItem>> n;
    public static final PropertyModel.r<Callback<OfflineItem>> o;
    public static final PropertyModel.r<Callback<OfflineItem>> p;
    public static final PropertyModel.r<Callback<OfflineItem>> q;
    public static final PropertyModel.r<Callback<OfflineItem>> r;
    public static final PropertyModel.r<Callback<OfflineItem>> s;
    public static final PropertyModel.r<Callback<OfflineItem>> t;
    public static final PropertyModel.r<b> u;
    public static final PropertyModel.r<a> v;
    public static final PropertyModel.r<Callback<f81>> w;
    public static final PropertyModel.n x;
    public static final PropertyModel.r<Runnable> y;
    public static final PropertyModel.r<Callback<Pair<Date, String>>> z;

    /* JADX INFO: compiled from: ListProperties.java */
    @FunctionalInterface
    public interface a {
        void a(int i, String str, n62 n62Var);
    }

    /* JADX INFO: compiled from: ListProperties.java */
    @FunctionalInterface
    public interface b {
        Runnable a(OfflineItem offlineItem, int i, int i2, zo1 zo1Var);
    }

    static {
        PropertyModel.n nVar = new PropertyModel.n();
        m = nVar;
        PropertyModel.r<Callback<OfflineItem>> rVar = new PropertyModel.r<>();
        n = rVar;
        PropertyModel.r<Callback<OfflineItem>> rVar2 = new PropertyModel.r<>();
        o = rVar2;
        PropertyModel.r<Callback<OfflineItem>> rVar3 = new PropertyModel.r<>();
        p = rVar3;
        PropertyModel.r<Callback<OfflineItem>> rVar4 = new PropertyModel.r<>();
        q = rVar4;
        PropertyModel.r<Callback<OfflineItem>> rVar5 = new PropertyModel.r<>();
        r = rVar5;
        PropertyModel.r<Callback<OfflineItem>> rVar6 = new PropertyModel.r<>();
        s = rVar6;
        PropertyModel.r<Callback<OfflineItem>> rVar7 = new PropertyModel.r<>();
        t = rVar7;
        PropertyModel.r<b> rVar8 = new PropertyModel.r<>();
        u = rVar8;
        PropertyModel.r<a> rVar9 = new PropertyModel.r<>();
        v = rVar9;
        PropertyModel.r<Callback<f81>> rVar10 = new PropertyModel.r<>();
        w = rVar10;
        PropertyModel.n nVar2 = new PropertyModel.n();
        x = nVar2;
        PropertyModel.r<Runnable> rVar11 = new PropertyModel.r<>();
        y = rVar11;
        PropertyModel.r<Callback<Pair<Date, String>>> rVar12 = new PropertyModel.r<>();
        z = rVar12;
        A = new v62[]{nVar, rVar, rVar2, rVar3, rVar4, rVar5, rVar6, rVar7, rVar8, rVar9, rVar10, nVar2, rVar11, rVar12};
    }
}
