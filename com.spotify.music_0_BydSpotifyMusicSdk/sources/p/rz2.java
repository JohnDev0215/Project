package p;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class rz2 extends vz2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ j23 k;
    public final /* synthetic */ com.google.android.play.core.assetpacks.a l;
    public final /* synthetic */ Object m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ rz2(com.google.android.play.core.assetpacks.a aVar, j23 j23Var, Object obj, j23 j23Var2, int i) {
        super(j23Var);
        this.e = i;
        this.l = aVar;
        this.m = obj;
        this.k = j23Var2;
    }

    @Override // p.vz2
    public final void a() {
        int i = this.e;
        Object obj = this.m;
        int i2 = 0;
        com.google.android.play.core.assetpacks.a aVar = this.l;
        j23 j23Var = this.k;
        switch (i) {
            case 0:
                List<String> list = (List) obj;
                ArrayList arrayList = new ArrayList(list.size());
                for (String str : list) {
                    Bundle bundle = new Bundle();
                    bundle.putString("module_name", str);
                    arrayList.add(bundle);
                }
                try {
                    x23 x23Var = (x23) aVar.d.n;
                    String str2 = aVar.a;
                    Bundle bundleH = com.google.android.play.core.assetpacks.a.h();
                    c03 c03Var = new c03(aVar, j23Var, i2);
                    u23 u23Var = (u23) x23Var;
                    Parcel parcelG0 = u23Var.g0();
                    parcelG0.writeString(str2);
                    parcelG0.writeTypedList(arrayList);
                    int i3 = n23.a;
                    parcelG0.writeInt(1);
                    bundleH.writeToParcel(parcelG0, 0);
                    parcelG0.writeStrongBinder(c03Var);
                    u23Var.h0(parcelG0, 14);
                } catch (RemoteException e) {
                    com.google.android.play.core.assetpacks.a.g.d(e, "cancelDownloads(%s)", list);
                }
                break;
            default:
                try {
                    x23 x23Var2 = (x23) aVar.d.n;
                    String str3 = aVar.a;
                    Bundle bundleK = com.google.android.play.core.assetpacks.a.k((Map) obj);
                    e03 e03Var = new e03(aVar, j23Var, i2);
                    u23 u23Var2 = (u23) x23Var2;
                    Parcel parcelG02 = u23Var2.g0();
                    parcelG02.writeString(str3);
                    int i4 = n23.a;
                    parcelG02.writeInt(1);
                    bundleK.writeToParcel(parcelG02, 0);
                    parcelG02.writeStrongBinder(e03Var);
                    u23Var2.h0(parcelG02, 5);
                } catch (RemoteException e2) {
                    com.google.android.play.core.assetpacks.a.g.d(e2, "syncPacks", new Object[0]);
                    j23Var.a(new RuntimeException(e2));
                    return;
                }
                break;
        }
    }
}
