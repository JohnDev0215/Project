package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzas extends com.google.android.gms.internal.cast.zzb implements zzat {
    public zzas() {
        super("com.google.android.gms.cast.framework.ISessionProvider");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            String string = parcel.readString();
            com.google.android.gms.internal.cast.zzc.zzb(parcel);
            IObjectWrapper iObjectWrapperZzb = zzb(string);
            parcel2.writeNoException();
            com.google.android.gms.internal.cast.zzc.zzf(parcel2, iObjectWrapperZzb);
        } else if (i == 2) {
            boolean zZzd = zzd();
            parcel2.writeNoException();
            com.google.android.gms.internal.cast.zzc.zzc(parcel2, zZzd);
        } else if (i == 3) {
            String strZzc = zzc();
            parcel2.writeNoException();
            parcel2.writeString(strZzc);
        } else {
            if (i != 4) {
                return false;
            }
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
        }
        return true;
    }
}
