package defpackage;

import android.os.Bundle;
import android.util.Log;
import defpackage.aa0;
import defpackage.t90;

/* JADX INFO: compiled from: ConsumeConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class ux extends vd1 {
    public final /* synthetic */ t90.a k;

    public ux(aa0.e eVar) {
        this.k = eVar;
    }

    @Override // defpackage.vd1
    public final void q(String str, Bundle bundle) {
        boolean zEquals = "consume.response".equals(str);
        t90.a aVar = this.k;
        if (!zEquals) {
            Log.w("cr_DigitalGoods", "Wrong callback name given: " + str + ".");
            vx.a(aVar);
            return;
        }
        if (bundle == null) {
            Log.w("cr_DigitalGoods", "No args provided.");
            vx.a(aVar);
        } else if (bundle.get("consume.responseCode") instanceof Integer) {
            ((aa0.e) aVar).c(Integer.valueOf(n3.z(bundle.getInt("consume.responseCode"), bundle)));
        } else {
            Log.w("cr_DigitalGoods", "Poorly formed args provided.");
            vx.a(aVar);
        }
    }
}
