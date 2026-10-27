package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import defpackage.aa0;
import defpackage.t90;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListPurchasesConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class x81 extends vd1 {
    public final /* synthetic */ t90.d k;

    public x81(aa0.q qVar) {
        this.k = qVar;
    }

    @Override // defpackage.vd1
    public final void q(String str, Bundle bundle) {
        boolean zEquals = "listPurchases.response".equals(str);
        t90.d dVar = this.k;
        if (!zEquals) {
            Log.w("cr_DigitalGoods", "Wrong callback name given: " + str + ".");
            y81.b(dVar);
            return;
        }
        if (bundle == null) {
            Log.w("cr_DigitalGoods", "No args provided.");
            y81.b(dVar);
            return;
        }
        if (!n3.u(bundle, "listPurchases.responseCode", Integer.class) || !n3.u(bundle, "listPurchases.purchasesList", Parcelable[].class)) {
            y81.b(dVar);
            return;
        }
        int i = bundle.getInt("listPurchases.responseCode");
        Parcelable[] parcelableArray = bundle.getParcelableArray("listPurchases.purchasesList");
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
        ((aa0.q) dVar).a(Integer.valueOf(n3.z(i, bundle)), (r72[]) arrayList.toArray(new r72[0]));
    }
}
