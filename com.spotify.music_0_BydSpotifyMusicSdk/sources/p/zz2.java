package p;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzat;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zz2 extends vz2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ zz2(Object obj, j23 j23Var, Object obj2, int i) {
        super(j23Var);
        this.e = i;
        this.l = obj;
        this.k = obj2;
    }

    @Override // p.vz2
    public final void a() {
        x23 u23Var;
        int i = this.e;
        int i2 = 1;
        Object obj = this.k;
        Object obj2 = this.l;
        switch (i) {
            case 0:
                try {
                    x23 x23Var = (x23) ((com.google.android.play.core.assetpacks.a) obj2).e.n;
                    String str = ((com.google.android.play.core.assetpacks.a) obj2).a;
                    Bundle bundleH = com.google.android.play.core.assetpacks.a.h();
                    e03 e03Var = new e03((com.google.android.play.core.assetpacks.a) obj2, (j23) obj, i2);
                    u23 u23Var2 = (u23) x23Var;
                    Parcel parcelG0 = u23Var2.g0();
                    parcelG0.writeString(str);
                    int i3 = n23.a;
                    parcelG0.writeInt(1);
                    bundleH.writeToParcel(parcelG0, 0);
                    parcelG0.writeStrongBinder(e03Var);
                    u23Var2.h0(parcelG0, 10);
                } catch (RemoteException e) {
                    com.google.android.play.core.assetpacks.a.g.d(e, "keepAlive", new Object[0]);
                    return;
                }
                break;
            case 1:
                g03 g03Var = (g03) obj2;
                vz2 vz2Var = (vz2) obj;
                IInterface iInterface = g03Var.n;
                ArrayList<vz2> arrayList = g03Var.d;
                tz2 tz2Var = g03Var.b;
                if (iInterface == null && !g03Var.g) {
                    tz2Var.e("Initiate binding to the service.", new Object[0]);
                    arrayList.add(vz2Var);
                    zf1 zf1Var = new zf1(g03Var);
                    g03Var.m = zf1Var;
                    g03Var.g = true;
                    if (!g03Var.a.bindService(g03Var.h, zf1Var, 1)) {
                        tz2Var.e("Failed to bind to the service.", new Object[0]);
                        g03Var.g = false;
                        for (vz2 vz2Var2 : arrayList) {
                            zzat zzatVar = new zzat();
                            j23 j23Var = vz2Var2.c;
                            if (j23Var != null) {
                                j23Var.a(zzatVar);
                            }
                        }
                        arrayList.clear();
                    }
                } else if (g03Var.g) {
                    tz2Var.e("Waiting to bind to the service.", new Object[0]);
                    arrayList.add(vz2Var);
                } else {
                    vz2Var.run();
                }
                break;
            default:
                zf1 zf1Var2 = (zf1) obj2;
                g03 g03Var2 = (g03) zf1Var2.b;
                IBinder iBinder = (IBinder) obj;
                ((qo) g03Var2.i).getClass();
                int i4 = v23.b;
                if (iBinder == null) {
                    u23Var = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
                    u23Var = iInterfaceQueryLocalInterface instanceof x23 ? (x23) iInterfaceQueryLocalInterface : new u23(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
                }
                g03Var2.n = u23Var;
                Object obj3 = zf1Var2.b;
                g03 g03Var3 = (g03) obj3;
                g03Var3.b.e("linkToDeath", new Object[0]);
                try {
                    g03Var3.n.asBinder().linkToDeath(g03Var3.k, 0);
                } catch (RemoteException e2) {
                    g03Var3.b.d(e2, "linkToDeath failed", new Object[0]);
                }
                g03 g03Var4 = (g03) obj3;
                g03Var4.g = false;
                Iterator it = g03Var4.d.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
                ((g03) obj3).d.clear();
                break;
        }
    }

    public zz2(zf1 zf1Var, IBinder iBinder) {
        this.e = 2;
        this.l = zf1Var;
        this.k = iBinder;
    }
}
