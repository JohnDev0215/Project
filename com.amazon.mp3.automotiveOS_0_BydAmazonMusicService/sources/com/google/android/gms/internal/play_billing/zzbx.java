package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzbx;
import com.google.android.gms.internal.play_billing.zzcb;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
public class zzbx<MessageType extends zzcb<MessageType, BuilderType>, BuilderType extends zzbx<MessageType, BuilderType>> extends zzaj<MessageType, BuilderType> {
    protected zzcb zza;
    private final zzcb zzb;

    protected zzbx(MessageType messagetype) {
        this.zzb = messagetype;
        if (messagetype.zzs()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = messagetype.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzaj
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzbx clone() {
        zzbx zzbxVar = (zzbx) this.zzb.zzt(5, null, null);
        zzbxVar.zza = zze();
        return zzbxVar;
    }

    public final MessageType zzc() {
        MessageType messagetype = (MessageType) zze();
        if (messagetype.zzr()) {
            return messagetype;
        }
        throw new zzef(messagetype);
    }

    @Override // com.google.android.gms.internal.play_billing.zzde
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public MessageType zze() {
        if (!this.zza.zzs()) {
            return (MessageType) this.zza;
        }
        this.zza.zzm();
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdg
    public final /* bridge */ /* synthetic */ zzdf zzf() {
        throw null;
    }

    protected final void zzg() {
        if (this.zza.zzs()) {
            return;
        }
        zzh();
    }

    protected void zzh() {
        zzcb zzcbVarZzh = this.zzb.zzh();
        zzdn.zza().zzb(zzcbVarZzh.getClass()).zzg(zzcbVarZzh, this.zza);
        this.zza = zzcbVarZzh;
    }
}
