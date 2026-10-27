package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzpq;
import com.google.android.gms.internal.cast.zzpt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class zzpq<MessageType extends zzpt<MessageType, BuilderType>, BuilderType extends zzpq<MessageType, BuilderType>> extends zzok<MessageType, BuilderType> {
    protected zzpt zza;
    protected boolean zzb = false;
    private final zzpt zzc;

    protected zzpq(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzpt) messagetype.zzb(4, null, null);
    }

    private static final void zza(zzpt zzptVar, zzpt zzptVar2) {
        zzrj.zza().zzb(zzptVar.getClass()).zzd(zzptVar, zzptVar2);
    }

    @Override // com.google.android.gms.internal.cast.zzok
    protected final /* synthetic */ zzok zzl(zzol zzolVar) {
        zzo((zzpt) zzolVar);
        return this;
    }

    @Override // com.google.android.gms.internal.cast.zzok
    /* JADX INFO: renamed from: zzn, reason: merged with bridge method [inline-methods] */
    public final zzpq clone() {
        zzpq zzpqVar = (zzpq) this.zzc.zzb(5, null, null);
        zzpqVar.zzo(zzr());
        return zzpqVar;
    }

    public final zzpq zzo(zzpt zzptVar) {
        if (this.zzb) {
            zzt();
            this.zzb = false;
        }
        zza(this.zza, zzptVar);
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r3 != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final MessageType zzp() {
        /*
            r5 = this;
            com.google.android.gms.internal.cast.zzpt r0 = r5.zzr()
            r1 = 1
            r2 = 0
            java.lang.Object r3 = r0.zzb(r1, r2, r2)
            java.lang.Byte r3 = (java.lang.Byte) r3
            byte r3 = r3.byteValue()
            if (r3 != r1) goto L13
            goto L30
        L13:
            if (r3 == 0) goto L31
            com.google.android.gms.internal.cast.zzrj r3 = com.google.android.gms.internal.cast.zzrj.zza()
            java.lang.Class r4 = r0.getClass()
            com.google.android.gms.internal.cast.zzrm r3 = r3.zzb(r4)
            boolean r3 = r3.zzf(r0)
            if (r1 == r3) goto L29
            r1 = r2
            goto L2a
        L29:
            r1 = r0
        L2a:
            r4 = 2
            r0.zzb(r4, r1, r2)
            if (r3 == 0) goto L31
        L30:
            return r0
        L31:
            com.google.android.gms.internal.cast.zzsc r1 = new com.google.android.gms.internal.cast.zzsc
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzpq.zzp():com.google.android.gms.internal.cast.zzpt");
    }

    @Override // com.google.android.gms.internal.cast.zzra
    /* JADX INFO: renamed from: zzq, reason: merged with bridge method [inline-methods] */
    public MessageType zzr() {
        if (this.zzb) {
            return (MessageType) this.zza;
        }
        zzpt zzptVar = this.zza;
        zzrj.zza().zzb(zzptVar.getClass()).zzc(zzptVar);
        this.zzb = true;
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.cast.zzrc
    public final /* synthetic */ zzrb zzs() {
        return this.zzc;
    }

    protected void zzt() {
        zzpt zzptVar = (zzpt) this.zza.zzb(4, null, null);
        zza(zzptVar, this.zza);
        this.zza = zzptVar;
    }
}
