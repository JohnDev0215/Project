package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzok;
import com.google.android.gms.internal.cast.zzol;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzol<MessageType extends zzol<MessageType, BuilderType>, BuilderType extends zzok<MessageType, BuilderType>> implements zzrb {
    protected int zza = 0;

    int zzn() {
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzrb
    public final zzoy zzo() {
        try {
            int iZzq = zzq();
            zzoy zzoyVar = zzoy.zzb;
            byte[] bArr = new byte[iZzq];
            zzpg zzpgVarZzC = zzpg.zzC(bArr);
            zzB(zzpgVarZzC);
            zzpgVarZzC.zzD();
            return new zzov(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    void zzp(int i) {
        throw null;
    }
}
