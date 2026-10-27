package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.wearable.Channel;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final /* synthetic */ class zzar implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zza = new zzar();

    private zzar() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((Channel.GetOutputStreamResult) result).getOutputStream();
    }
}
