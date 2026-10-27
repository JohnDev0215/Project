package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import defpackage.aa0;
import defpackage.t90;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListPurchaseHistoryConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class v81 extends vd1 {
    public final /* synthetic */ t90.c k;

    public v81(aa0.m mVar) {
        this.k = mVar;
    }

    @Override // defpackage.vd1
    public final void q(String str, Bundle bundle) {
        boolean zEquals = "listPurchaseHistory.response".equals(str);
        t90.c cVar = this.k;
        if (!zEquals) {
            Log.w("cr_DigitalGoods", "Wrong callback name given: " + str + ".");
            w81.a(cVar);
            return;
        }
        if (bundle == null) {
            Log.w("cr_DigitalGoods", "No args provided.");
            w81.a(cVar);
            return;
        }
        if (!n3.u(bundle, "listPurchaseHistory.responseCode", Integer.class) || !n3.u(bundle, "listPurchaseHistory.purchasesList", Parcelable[].class)) {
            w81.a(cVar);
            return;
        }
        int i = bundle.getInt("listPurchaseHistory.responseCode");
        Parcelable[] parcelableArray = bundle.getParcelableArray("listPurchaseHistory.purchasesList");
        ArrayList arrayList = new ArrayList();
        for (Parcelable parcelable : parcelableArray) {
            if (parcelable instanceof Bundle) {
                r72 r72VarA = y81.a((Bundle) parcelable);
                if (r72VarA != null) {
                    arrayList.add(r72VarA);
                }
            } else {
                Log.w("cr_DigitalGoods", "Passed a Parcelable that was not a Bundle.");
            }
        }
        ((aa0.m) cVar).a(Integer.valueOf(n3.z(i, bundle)), (r72[]) arrayList.toArray(new r72[0]));
    }
}
