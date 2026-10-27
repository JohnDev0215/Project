package p;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class yz2 extends vz2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ j23 k;
    public final /* synthetic */ com.google.android.play.core.assetpacks.a l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yz2(com.google.android.play.core.assetpacks.a aVar, j23 j23Var, int i, j23 j23Var2) {
        super(j23Var);
        this.l = aVar;
        this.e = i;
        this.k = j23Var2;
    }

    @Override // p.vz2
    public final void a() {
        com.google.android.play.core.assetpacks.a aVar = this.l;
        try {
            x23 x23Var = (x23) aVar.d.n;
            String str = aVar.a;
            int i = this.e;
            Bundle bundle = new Bundle();
            bundle.putInt("session_id", i);
            Bundle bundleH = com.google.android.play.core.assetpacks.a.h();
            c03 c03Var = new c03(aVar, this.k, 3);
            u23 u23Var = (u23) x23Var;
            Parcel parcelG0 = u23Var.g0();
            parcelG0.writeString(str);
            int i2 = n23.a;
            parcelG0.writeInt(1);
            bundle.writeToParcel(parcelG0, 0);
            parcelG0.writeInt(1);
            bundleH.writeToParcel(parcelG0, 0);
            parcelG0.writeStrongBinder(c03Var);
            u23Var.h0(parcelG0, 9);
        } catch (RemoteException e) {
            com.google.android.play.core.assetpacks.a.g.d(e, "notifySessionFailed", new Object[0]);
        }
    }
}
