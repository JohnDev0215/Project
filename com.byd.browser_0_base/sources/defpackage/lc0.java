package defpackage;

import org.chromium.base.Callback;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: DownloadInterstitialProperties.java */
/* JADX INFO: loaded from: classes.dex */
public interface lc0 extends t81 {
    public static final PropertyModel.r<OfflineItem> a;
    public static final PropertyModel.p b;
    public static final PropertyModel.r<String> c;
    public static final PropertyModel.n d;
    public static final PropertyModel.r<String> e;
    public static final PropertyModel.r<Callback<OfflineItem>> f;
    public static final PropertyModel.n g;
    public static final PropertyModel.r<String> h;
    public static final PropertyModel.r<Callback<OfflineItem>> i;
    public static final PropertyModel.r<Runnable> j;
    public static final PropertyModel.n k;
    public static final v62[] l;

    static {
        PropertyModel.r<OfflineItem> rVar = new PropertyModel.r<>();
        a = rVar;
        PropertyModel.p pVar = new PropertyModel.p();
        b = pVar;
        PropertyModel.r<String> rVar2 = new PropertyModel.r<>();
        c = rVar2;
        PropertyModel.n nVar = new PropertyModel.n();
        d = nVar;
        PropertyModel.r<String> rVar3 = new PropertyModel.r<>();
        e = rVar3;
        PropertyModel.r<Callback<OfflineItem>> rVar4 = new PropertyModel.r<>();
        f = rVar4;
        PropertyModel.n nVar2 = new PropertyModel.n();
        g = nVar2;
        PropertyModel.r<String> rVar5 = new PropertyModel.r<>();
        h = rVar5;
        PropertyModel.r<Callback<OfflineItem>> rVar6 = new PropertyModel.r<>();
        i = rVar6;
        PropertyModel.r<Runnable> rVar7 = new PropertyModel.r<>();
        j = rVar7;
        PropertyModel.n nVar3 = new PropertyModel.n();
        k = nVar3;
        l = new v62[]{t81.m, t81.n, t81.o, t81.p, t81.q, t81.r, t81.s, t81.t, t81.u, t81.v, t81.w, t81.x, t81.y, t81.z, rVar, pVar, rVar2, nVar, rVar3, rVar4, nVar2, rVar5, rVar6, rVar7, nVar3};
    }
}
