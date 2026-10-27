package defpackage;

import android.content.Context;
import defpackage.kc0;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hc0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ kc0 f;

    public /* synthetic */ hc0(kc0 kc0Var, int i) {
        this.e = i;
        this.f = kc0Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        kc0 kc0Var = this.f;
        switch (i) {
            case 0:
                kc0Var.a(3);
                kc0Var.d.a(((OfflineItem) kc0Var.b.j(lc0.a)).e);
                kc0.a.a(2);
                break;
            case 1:
                String str = ((OfflineItem) kc0Var.b.j(lc0.a)).f;
                pq2 pq2Var = new pq2(12, kc0Var);
                wa2 wa2Var = new wa2(kc0Var.a.get(), kc0Var.g);
                wa2Var.f = pq2Var;
                wa2Var.c = str;
                wa2Var.d = str;
                wa2Var.e = 0;
                wa2Var.g = 0;
                wa2Var.a(1, 3);
                kc0.a.a(9);
                break;
            default:
                kc0Var.getClass();
                if (((Integer) obj).intValue() == 1) {
                    Context context = kc0Var.a.get();
                    int i2 = R$string.delete_message;
                    PropertyModel.r<OfflineItem> rVar = lc0.a;
                    PropertyModel propertyModel = kc0Var.b;
                    kc0Var.e.c(tp2.a(context.getString(i2, ((OfflineItem) propertyModel.j(rVar)).f), null, 0, 52));
                    kc0Var.a(3);
                    kc0Var.d.c(((OfflineItem) propertyModel.j(rVar)).e);
                    kc0.a.a(6);
                }
                break;
        }
    }
}
