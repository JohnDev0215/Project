package p;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class uz2 extends vz2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ int k;
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ int n;
    public final /* synthetic */ j23 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ com.google.android.play.core.assetpacks.a f198p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ uz2(com.google.android.play.core.assetpacks.a aVar, j23 j23Var, int i, String str, String str2, int i2, j23 j23Var2, int i3) {
        super(j23Var);
        this.e = i3;
        this.f198p = aVar;
        this.k = i;
        this.l = str;
        this.m = str2;
        this.n = i2;
        this.o = j23Var2;
    }

    @Override // p.vz2
    public final void a() {
        int i = this.e;
        int i2 = 1;
        com.google.android.play.core.assetpacks.a aVar = this.f198p;
        int i3 = this.k;
        String str = this.l;
        String str2 = this.m;
        int i4 = this.n;
        j23 j23Var = this.o;
        switch (i) {
            case 0:
                try {
                    x23 x23Var = (x23) aVar.d.n;
                    String str3 = aVar.a;
                    Bundle bundle = new Bundle();
                    bundle.putInt("session_id", i3);
                    bundle.putString("module_name", str);
                    bundle.putString("slice_id", str2);
                    bundle.putInt("chunk_number", i4);
                    Bundle bundleH = com.google.android.play.core.assetpacks.a.h();
                    c03 c03Var = new c03(aVar, j23Var, 2);
                    u23 u23Var = (u23) x23Var;
                    Parcel parcelG0 = u23Var.g0();
                    parcelG0.writeString(str3);
                    int i5 = n23.a;
                    parcelG0.writeInt(1);
                    bundle.writeToParcel(parcelG0, 0);
                    parcelG0.writeInt(1);
                    bundleH.writeToParcel(parcelG0, 0);
                    parcelG0.writeStrongBinder(c03Var);
                    u23Var.h0(parcelG0, 6);
                } catch (RemoteException e) {
                    com.google.android.play.core.assetpacks.a.g.d(e, "notifyChunkTransferred", new Object[0]);
                    return;
                }
                break;
            default:
                try {
                    x23 x23Var2 = (x23) aVar.d.n;
                    String str4 = aVar.a;
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("session_id", i3);
                    bundle2.putString("module_name", str);
                    bundle2.putString("slice_id", str2);
                    bundle2.putInt("chunk_number", i4);
                    Bundle bundleH2 = com.google.android.play.core.assetpacks.a.h();
                    c03 c03Var2 = new c03(aVar, j23Var, i2);
                    u23 u23Var2 = (u23) x23Var2;
                    Parcel parcelG02 = u23Var2.g0();
                    parcelG02.writeString(str4);
                    int i6 = n23.a;
                    parcelG02.writeInt(1);
                    bundle2.writeToParcel(parcelG02, 0);
                    parcelG02.writeInt(1);
                    bundleH2.writeToParcel(parcelG02, 0);
                    parcelG02.writeStrongBinder(c03Var2);
                    u23Var2.h0(parcelG02, 11);
                } catch (RemoteException e2) {
                    com.google.android.play.core.assetpacks.a.g.c("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(i4), Integer.valueOf(i3));
                    j23Var.a(new RuntimeException(e2));
                }
                break;
        }
    }
}
