package com.android.billingclient.api;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class AccountIdentifiers {

    @Nullable
    private final String zza;

    @Nullable
    private final String zzb;

    AccountIdentifiers(@Nullable String str, @Nullable String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Nullable
    public String getObfuscatedProfileId() {
        return this.zzb;
    }
}
