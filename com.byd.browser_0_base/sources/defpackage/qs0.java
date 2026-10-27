package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import defpackage.aa0;
import defpackage.t90;
import java.util.ArrayList;

/* JADX INFO: compiled from: GetDetailsConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class qs0 extends vd1 {
    public final /* synthetic */ t90.b k;

    public qs0(aa0.i iVar) {
        this.k = iVar;
    }

    @Override // defpackage.vd1
    public final void q(String str, Bundle bundle) {
        boolean zEquals = "getDetails.response".equals(str);
        t90.b bVar = this.k;
        if (!zEquals) {
            Log.w("cr_DigitalGoods", "Wrong callback name given: " + str + ".");
            rs0.a(bVar);
            return;
        }
        if (bundle == null) {
            Log.w("cr_DigitalGoods", "No args provided.");
            rs0.a(bVar);
            return;
        }
        if (!n3.u(bundle, "getDetails.responseCode", Integer.class) || !n3.u(bundle, "getDetails.detailsList", Parcelable[].class)) {
            rs0.a(bVar);
            return;
        }
        int i = bundle.getInt("getDetails.responseCode");
        Parcelable[] parcelableArray = bundle.getParcelableArray("getDetails.detailsList");
        qh1 qh1Var = new qh1(2);
        ArrayList arrayList = new ArrayList();
        for (Parcelable parcelable : parcelableArray) {
            if (parcelable instanceof Bundle) {
                Object objB = qh1Var.b((Bundle) parcelable);
                if (objB != null) {
                    arrayList.add(objB);
                }
            } else {
                Log.w("cr_DigitalGoods", "Passed a Parcelable that was not a Bundle.");
            }
        }
        ((aa0.i) bVar).a(Integer.valueOf(n3.z(i, bundle)), (a31[]) arrayList.toArray(new a31[0]));
    }
}
