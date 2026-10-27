package com.android.billingclient.api;

import android.text.TextUtils;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbw {
    private String zza;

    private zzbw() {
    }

    /* synthetic */ zzbw(zzbv zzbvVar) {
    }

    public final zzbw zza(String str) {
        this.zza = str;
        return this;
    }

    public final zzby zzb() {
        if (TextUtils.isEmpty(this.zza)) {
            throw new IllegalArgumentException("SKU must be set.");
        }
        return new zzby(this.zza, null, null, 0, null);
    }
}
