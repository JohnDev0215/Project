package p;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class wz2 extends vz2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ String k;
    public final /* synthetic */ j23 l;
    public final /* synthetic */ int m;
    public final /* synthetic */ com.google.android.play.core.assetpacks.a n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public wz2(com.google.android.play.core.assetpacks.a aVar, j23 j23Var, int i, String str, j23 j23Var2, int i2) {
        super(j23Var);
        this.n = aVar;
        this.e = i;
        this.k = str;
        this.l = j23Var2;
        this.m = i2;
    }

    @Override // p.vz2
    public final void a() {
        try {
            com.google.android.play.core.assetpacks.a aVar = this.n;
            x23 x23Var = (x23) aVar.d.n;
            String str = aVar.a;
            int i = this.e;
            String str2 = this.k;
            Bundle bundle = new Bundle();
            bundle.putInt("session_id", i);
            bundle.putString("module_name", str2);
            Bundle bundleH = com.google.android.play.core.assetpacks.a.h();
            f03 f03Var = new f03(this.n, this.l, this.e, this.k, this.m);
            u23 u23Var = (u23) x23Var;
            Parcel parcelG0 = u23Var.g0();
            parcelG0.writeString(str);
            int i2 = n23.a;
            parcelG0.writeInt(1);
            bundle.writeToParcel(parcelG0, 0);
            parcelG0.writeInt(1);
            bundleH.writeToParcel(parcelG0, 0);
            parcelG0.writeStrongBinder(f03Var);
            u23Var.h0(parcelG0, 7);
        } catch (RemoteException e) {
            com.google.android.play.core.assetpacks.a.g.d(e, "notifyModuleCompleted", new Object[0]);
        }
    }
}
