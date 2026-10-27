package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzae extends com.google.android.gms.internal.cast.zzb implements zzaf {
    public zzae() {
        super("com.google.android.gms.cast.framework.ICastStateListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IObjectWrapper iObjectWrapperZzb = zzb();
            parcel2.writeNoException();
            com.google.android.gms.internal.cast.zzc.zzf(parcel2, iObjectWrapperZzb);
        } else if (i == 2) {
            int i3 = parcel.readInt();
            com.google.android.gms.internal.cast.zzc.zzb(parcel);
            zzc(i3);
            parcel2.writeNoException();
        } else {
            if (i != 3) {
                return false;
            }
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
        }
        return true;
    }
}
