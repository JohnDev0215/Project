package defpackage;

import android.content.Context;
import android.content.res.Resources;
import defpackage.di1;
import defpackage.kc0;
import defpackage.t81;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.share.ShareHelper;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: DownloadInterstitialMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class kc0 {
    public final nw2<Context> a;
    public final PropertyModel b;
    public final String c;
    public final org.chromium.components.offline_items_collection.a d;
    public final wp2 e;
    public final jc0 f;
    public bi1 g;

    /* JADX INFO: compiled from: DownloadInterstitialMediator.java */
    public static class a {
        public static void a(int i) {
            oj2.U(i, 10, "Download.Interstitial.UIAction");
        }
    }

    public kc0(p30 p30Var, PropertyModel propertyModel, String str, org.chromium.components.offline_items_collection.a aVar, wp2 wp2Var, bi1 bi1Var) {
        this.a = p30Var;
        this.b = propertyModel;
        this.c = str;
        this.d = aVar;
        this.e = wp2Var;
        this.g = bi1Var;
        final int i = 1;
        propertyModel.l(t81.m, true);
        final int i2 = 0;
        propertyModel.p(t81.n, new Callback(this) { // from class: fc0
            public final /* synthetic */ kc0 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i2;
                kc0 kc0Var = this.f;
                switch (i3) {
                    case 0:
                        kc0Var.getClass();
                        kc0Var.d.e(new wq(6), ((OfflineItem) kc0Var.b.j(lc0.a)).e);
                        kc0.a.a(5);
                        break;
                    default:
                        kc0Var.getClass();
                        HashSet<OfflineItem> hashSetE = CollectionUtil.e((OfflineItem) obj);
                        ShareHelper.c(0);
                        kc0.a.a(8);
                        ArrayList arrayList = new ArrayList();
                        for (OfflineItem offlineItem : hashSetE) {
                            kc0Var.d.d(offlineItem.e, new ec0(kc0Var, arrayList, offlineItem, hashSetE));
                        }
                        break;
                }
            }
        });
        propertyModel.p(t81.o, new Callback(this) { // from class: gc0
            public final /* synthetic */ kc0 f;

            {
                this.f = this;
            }

            /* JADX WARN: Type inference failed for: r10v2, types: [T, ym2] */
            /* JADX WARN: Type inference failed for: r1v5, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v12, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v6, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v9, types: [T, java.lang.String] */
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i2;
                kc0 kc0Var = this.f;
                switch (i3) {
                    case 0:
                        kc0Var.d.h(((OfflineItem) kc0Var.b.j(lc0.a)).e);
                        kc0.a.a(3);
                        break;
                    default:
                        ?? ym2Var = new ym2(kc0Var.g, new hc0(kc0Var, 2));
                        HashMap mapE = PropertyModel.e(di1.B);
                        PropertyModel.l<di1.a> lVar = di1.a;
                        int i4 = zj.a;
                        PropertyModel.g gVar = new PropertyModel.g();
                        gVar.a = ym2Var;
                        mapE.put(lVar, gVar);
                        PropertyModel.r<String> rVar = di1.c;
                        nw2<Context> nw2Var = kc0Var.a;
                        Resources resources = nw2Var.get().getResources();
                        int i5 = R$string.delete;
                        ?? string = resources.getString(i5);
                        PropertyModel.g gVar2 = new PropertyModel.g();
                        gVar2.a = string;
                        mapE.put(rVar, gVar2);
                        PropertyModel.r<CharSequence> rVar2 = di1.f;
                        ?? string2 = nw2Var.get().getString(R$string.confirm_delete_message, ((OfflineItem) kc0Var.b.j(lc0.a)).f);
                        PropertyModel.g gVar3 = new PropertyModel.g();
                        gVar3.a = string2;
                        mapE.put(rVar2, gVar3);
                        PropertyModel.r<String> rVar3 = di1.j;
                        ?? string3 = nw2Var.get().getString(i5);
                        PropertyModel.g gVar4 = new PropertyModel.g();
                        gVar4.a = string3;
                        mapE.put(rVar3, gVar4);
                        PropertyModel.r<String> rVar4 = di1.m;
                        ?? string4 = nw2Var.get().getString(R$string.cancel);
                        PropertyModel.g gVar5 = new PropertyModel.g();
                        gVar5.a = string4;
                        mapE.put(rVar4, gVar5);
                        kc0Var.g.j(new PropertyModel(mapE), 1, false);
                        break;
                }
            }
        });
        propertyModel.p(t81.p, new so(29, this));
        propertyModel.p(t81.q, new hc0(this, i2));
        propertyModel.p(t81.r, new Callback(this) { // from class: fc0
            public final /* synthetic */ kc0 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i;
                kc0 kc0Var = this.f;
                switch (i3) {
                    case 0:
                        kc0Var.getClass();
                        kc0Var.d.e(new wq(6), ((OfflineItem) kc0Var.b.j(lc0.a)).e);
                        kc0.a.a(5);
                        break;
                    default:
                        kc0Var.getClass();
                        HashSet<OfflineItem> hashSetE = CollectionUtil.e((OfflineItem) obj);
                        ShareHelper.c(0);
                        kc0.a.a(8);
                        ArrayList arrayList = new ArrayList();
                        for (OfflineItem offlineItem : hashSetE) {
                            kc0Var.d.d(offlineItem.e, new ec0(kc0Var, arrayList, offlineItem, hashSetE));
                        }
                        break;
                }
            }
        });
        propertyModel.p(t81.s, new Callback(this) { // from class: gc0
            public final /* synthetic */ kc0 f;

            {
                this.f = this;
            }

            /* JADX WARN: Type inference failed for: r10v2, types: [T, ym2] */
            /* JADX WARN: Type inference failed for: r1v5, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v12, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v6, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v9, types: [T, java.lang.String] */
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i;
                kc0 kc0Var = this.f;
                switch (i3) {
                    case 0:
                        kc0Var.d.h(((OfflineItem) kc0Var.b.j(lc0.a)).e);
                        kc0.a.a(3);
                        break;
                    default:
                        ?? ym2Var = new ym2(kc0Var.g, new hc0(kc0Var, 2));
                        HashMap mapE = PropertyModel.e(di1.B);
                        PropertyModel.l<di1.a> lVar = di1.a;
                        int i4 = zj.a;
                        PropertyModel.g gVar = new PropertyModel.g();
                        gVar.a = ym2Var;
                        mapE.put(lVar, gVar);
                        PropertyModel.r<String> rVar = di1.c;
                        nw2<Context> nw2Var = kc0Var.a;
                        Resources resources = nw2Var.get().getResources();
                        int i5 = R$string.delete;
                        ?? string = resources.getString(i5);
                        PropertyModel.g gVar2 = new PropertyModel.g();
                        gVar2.a = string;
                        mapE.put(rVar, gVar2);
                        PropertyModel.r<CharSequence> rVar2 = di1.f;
                        ?? string2 = nw2Var.get().getString(R$string.confirm_delete_message, ((OfflineItem) kc0Var.b.j(lc0.a)).f);
                        PropertyModel.g gVar3 = new PropertyModel.g();
                        gVar3.a = string2;
                        mapE.put(rVar2, gVar3);
                        PropertyModel.r<String> rVar3 = di1.j;
                        ?? string3 = nw2Var.get().getString(i5);
                        PropertyModel.g gVar4 = new PropertyModel.g();
                        gVar4.a = string3;
                        mapE.put(rVar3, gVar4);
                        PropertyModel.r<String> rVar4 = di1.m;
                        ?? string4 = nw2Var.get().getString(R$string.cancel);
                        PropertyModel.g gVar5 = new PropertyModel.g();
                        gVar5.a = string4;
                        mapE.put(rVar4, gVar5);
                        kc0Var.g.j(new PropertyModel(mapE), 1, false);
                        break;
                }
            }
        });
        propertyModel.p(t81.u, new t81.b() { // from class: ic0
            @Override // t81.b
            public final Runnable a(OfflineItem offlineItem, int i3, int i4, zo1 zo1Var) {
                return new qe3(9);
            }
        });
        propertyModel.p(t81.t, new hc0(this, i));
        propertyModel.p(t81.w, new m62(2));
        jc0 jc0Var = new jc0(this);
        this.f = jc0Var;
        aVar.k(jc0Var);
        a.a(0);
    }

    public final void a(int i) {
        PropertyModel.p pVar = lc0.b;
        PropertyModel propertyModel = this.b;
        if (i == propertyModel.h(pVar)) {
            return;
        }
        propertyModel.n(pVar, i);
        nw2<Context> nw2Var = this.a;
        if (i == 1) {
            propertyModel.p(lc0.c, nw2Var.get().getString(R$string.download_started));
            propertyModel.l(lc0.d, false);
            propertyModel.p(lc0.h, nw2Var.get().getString(R$string.download_notification_cancel_button));
            propertyModel.p(lc0.i, (Callback) propertyModel.j(t81.q));
            propertyModel.l(lc0.g, true);
            return;
        }
        if (i == 2) {
            propertyModel.p(lc0.c, nw2Var.get().getResources().getQuantityString(R$plurals.download_message_multiple_download_complete, 1, 1));
            propertyModel.p(lc0.e, nw2Var.get().getString(R$string.open_downloaded_label));
            propertyModel.p(lc0.f, (Callback) propertyModel.j(t81.n));
            propertyModel.l(lc0.d, true);
            propertyModel.p(lc0.h, nw2Var.get().getString(R$string.delete));
            propertyModel.p(lc0.i, (Callback) propertyModel.j(t81.s));
            propertyModel.l(lc0.g, true);
            a.a(1);
            return;
        }
        if (i == 3) {
            PropertyModel.r<String> rVar = lc0.c;
            Context context = nw2Var.get();
            int i2 = R$string.menu_download;
            propertyModel.p(rVar, context.getString(i2));
            propertyModel.p(lc0.e, nw2Var.get().getString(i2));
            propertyModel.p(lc0.f, (Callback) propertyModel.j(t81.p));
            propertyModel.l(lc0.d, true);
            propertyModel.l(lc0.g, false);
            return;
        }
        if (i != 4) {
            if (i != 5) {
                return;
            }
            propertyModel.l(lc0.k, true);
        } else {
            propertyModel.p(lc0.c, nw2Var.get().getString(R$string.menu_download));
            propertyModel.p(lc0.e, nw2Var.get().getString(R$string.download_notification_resume_button));
            propertyModel.p(lc0.f, (Callback) propertyModel.j(t81.p));
            propertyModel.l(lc0.d, true);
            propertyModel.l(lc0.g, false);
        }
    }
}
