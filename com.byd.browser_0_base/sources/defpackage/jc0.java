package defpackage;

import android.text.TextUtils;
import java.util.List;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.components.offline_items_collection.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: DownloadInterstitialMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class jc0 implements a.InterfaceC0134a {
    public final /* synthetic */ kc0 e;

    public jc0(kc0 kc0Var) {
        this.e = kc0Var;
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void d(OfflineItem offlineItem, UpdateDelta updateDelta) {
        kc0 kc0Var = this.e;
        PropertyModel propertyModel = kc0Var.b;
        PropertyModel.r<OfflineItem> rVar = lc0.a;
        if (propertyModel.j(rVar) == null) {
            if (TextUtils.equals(kc0Var.c, offlineItem.x.j())) {
                kc0Var.b.l(lc0.k, false);
            }
        } else if (!offlineItem.e.equals(((OfflineItem) kc0Var.b.j(rVar)).e)) {
            return;
        }
        kc0Var.b.p(rVar, offlineItem);
        switch (offlineItem.A) {
            case 0:
            case 1:
                kc0Var.a(1);
                break;
            case 2:
                if (kc0Var.b.h(lc0.b) != 3) {
                    kc0Var.a(2);
                }
                break;
            case 3:
            case 4:
            case 5:
                kc0Var.a(3);
                break;
            case 6:
                kc0Var.a(4);
                break;
        }
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void b(List<OfflineItem> list) {
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void e(ny nyVar) {
    }
}
