package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzaj;
import com.google.android.gms.internal.play_billing.zzak;
import java.io.IOException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzak<MessageType extends zzak<MessageType, BuilderType>, BuilderType extends zzaj<MessageType, BuilderType>> implements zzdf {
    protected int zza = 0;

    int zza(zzdp zzdpVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final zzba zzb() {
        try {
            int iZzd = zzd();
            zzba zzbaVar = zzba.zzb;
            byte[] bArr = new byte[iZzd];
            zzbi zzbiVarZzz = zzbi.zzz(bArr, 0, iZzd);
            zzq(zzbiVarZzz);
            zzbiVarZzz.zzA();
            return new zzax(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }
}
