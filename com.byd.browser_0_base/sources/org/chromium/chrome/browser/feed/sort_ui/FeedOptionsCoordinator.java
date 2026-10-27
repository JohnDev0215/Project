package org.chromium.chrome.browser.feed.sort_ui;

import J.N;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.ag;
import defpackage.bg;
import defpackage.bq;
import defpackage.cg;
import defpackage.el0;
import defpackage.ho;
import defpackage.sk;
import defpackage.v62;
import defpackage.wb;
import defpackage.zf;
import defpackage.zj;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes.dex */
public class FeedOptionsCoordinator {
    public final FeedOptionsView a;
    public final FeedOptionsView b;
    public final Context c;
    public final ArrayList d;
    public final PropertyModel e;
    public a f;

    public interface a {
    }

    public FeedOptionsCoordinator(Context context) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i = R$layout.feed_options_panel;
        FeedOptionsView feedOptionsView = (FeedOptionsView) layoutInflaterFrom.inflate(i, (ViewGroup) null, false);
        sk skVar = bq.a;
        FeedOptionsView feedOptionsView2 = N.M09VlOh_("FeedHeaderStickToTop") ? (FeedOptionsView) LayoutInflater.from(context).inflate(i, (ViewGroup) null, false) : null;
        this.c = context;
        this.a = feedOptionsView;
        PropertyModel.n nVar = el0.a;
        HashMap mapE = PropertyModel.e(new v62[]{nVar});
        int i2 = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = false;
        mapE.put(nVar, aVar);
        PropertyModel propertyModel = new PropertyModel(mapE);
        this.e = propertyModel;
        j.b(propertyModel, feedOptionsView, new zf(7));
        if (feedOptionsView2 != null) {
            this.b = feedOptionsView2;
            j.b(propertyModel, feedOptionsView2, new ag(3));
        }
        int iM3tcgrxA = N.M3tcgrxA();
        ArrayList<PropertyModel> arrayList = new ArrayList();
        arrayList.add(a(1, R$string.feed_sort_publisher, R$string.feed_options_sort_by_grouped, iM3tcgrxA == 1));
        arrayList.add(a(2, R$string.latest, R$string.feed_options_sort_by_latest, iM3tcgrxA == 2));
        for (PropertyModel propertyModel2 : arrayList) {
            int i3 = 6;
            j.b(propertyModel2, this.a.a(), new bg(i3));
            FeedOptionsView feedOptionsView3 = this.b;
            if (feedOptionsView3 != null) {
                j.b(propertyModel2, feedOptionsView3.a(), new cg(i3));
            }
        }
        this.d = arrayList;
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v3, types: [T, wb] */
    public final PropertyModel a(int i, int i2, int i3, boolean z) {
        HashMap mapE = PropertyModel.e(ho.j);
        PropertyModel.j jVar = ho.f;
        int i4 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(jVar, dVar);
        PropertyModel.l<String> lVar = ho.h;
        Context context = this.c;
        ?? string = context.getResources().getString(i2);
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string;
        mapE.put(lVar, gVar);
        PropertyModel.n nVar = ho.g;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z;
        mapE.put(nVar, aVar);
        PropertyModel.r<Callback<PropertyModel>> rVar = ho.a;
        ?? wbVar = new wb(26, this);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = wbVar;
        mapE.put(rVar, gVar2);
        PropertyModel.r<String> rVar2 = ho.b;
        ?? string2 = context.getResources().getString(i3);
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = string2;
        mapE.put(rVar2, gVar3);
        return new PropertyModel(mapE);
    }
}
