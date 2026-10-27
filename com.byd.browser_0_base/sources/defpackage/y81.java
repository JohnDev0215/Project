package defpackage;

import android.os.Bundle;
import defpackage.aa0;
import defpackage.t90;

/* JADX INFO: compiled from: ListPurchasesConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class y81 {
    public static r72 a(Bundle bundle) {
        if (!n3.u(bundle, "purchaseDetails.itemId", String.class) || !n3.u(bundle, "purchaseDetails.purchaseToken", String.class)) {
            return null;
        }
        r72 r72Var = new r72();
        r72Var.b = bundle.getString("purchaseDetails.itemId");
        r72Var.c = bundle.getString("purchaseDetails.purchaseToken");
        return r72Var;
    }

    public static void b(t90.d dVar) {
        ((aa0.q) dVar).a(6, new r72[0]);
    }
}
