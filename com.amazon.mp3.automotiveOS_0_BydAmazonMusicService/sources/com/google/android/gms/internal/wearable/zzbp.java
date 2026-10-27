package com.google.android.gms.internal.wearable;

import com.google.android.gms.internal.wearable.zzbp;
import com.google.android.gms.internal.wearable.zzbs;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class zzbp<MessageType extends zzbs<MessageType, BuilderType>, BuilderType extends zzbp<MessageType, BuilderType>> extends zzae<MessageType, BuilderType> {
    protected MessageType zza;
    protected boolean zzb = false;
    private final MessageType zzc;

    protected zzbp(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (MessageType) messagetype.zzG(4, null, null);
    }

    private static final void zza(MessageType messagetype, MessageType messagetype2) {
        zzdf.zza().zzb(messagetype.getClass()).zzd(messagetype, messagetype2);
    }

    @Override // com.google.android.gms.internal.wearable.zzcy
    public final /* bridge */ /* synthetic */ zzcx zzac() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.wearable.zzae
    protected final /* bridge */ /* synthetic */ zzae zzp(zzaf zzafVar) {
        zzv((zzbs) zzafVar);
        return this;
    }

    protected void zzr() {
        MessageType messagetype = (MessageType) this.zza.zzG(4, null, null);
        zza(messagetype, this.zza);
        this.zza = messagetype;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.wearable.zzae
    /* JADX INFO: renamed from: zzs, reason: merged with bridge method [inline-methods] */
    public final BuilderType clone() {
        BuilderType buildertype = (BuilderType) this.zzc.zzG(5, null, null);
        buildertype.zzv(zzw());
        return buildertype;
    }

    @Override // com.google.android.gms.internal.wearable.zzcw
    /* JADX INFO: renamed from: zzt, reason: merged with bridge method [inline-methods] */
    public MessageType zzw() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzdf.zza().zzb(messagetype.getClass()).zzi(messagetype);
        this.zzb = true;
        return this.zza;
    }

    public final MessageType zzu() {
        MessageType messagetype = (MessageType) zzw();
        if (messagetype.zzN()) {
            return messagetype;
        }
        throw new zzdv(messagetype);
    }

    public final BuilderType zzv(MessageType messagetype) {
        if (this.zzb) {
            zzr();
            this.zzb = false;
        }
        zza(this.zza, messagetype);
        return this;
    }
}
