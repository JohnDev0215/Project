package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzok;
import com.google.android.gms.internal.cast.zzol;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzok<MessageType extends zzol<MessageType, BuilderType>, BuilderType extends zzok<MessageType, BuilderType>> implements zzra {
    @Override // 
    /* JADX INFO: renamed from: zzk, reason: merged with bridge method [inline-methods] */
    public abstract zzok clone();

    protected abstract zzok zzl(zzol zzolVar);

    @Override // com.google.android.gms.internal.cast.zzra
    public final /* bridge */ /* synthetic */ zzra zzm(zzrb zzrbVar) {
        if (zzs().getClass().isInstance(zzrbVar)) {
            return zzl((zzol) zzrbVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
