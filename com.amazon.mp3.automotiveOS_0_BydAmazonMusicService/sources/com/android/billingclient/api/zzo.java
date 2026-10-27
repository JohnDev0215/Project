package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
final class zzo {
    private final Context zza;
    private final zzn zzb;

    zzo(Context context, zzbf zzbfVar, zzbh zzbhVar) {
        this.zza = context;
        this.zzb = new zzn(this, null, zzbhVar, 0 == true ? 1 : 0);
    }

    @Nullable
    final zzbf zzb() {
        zzn.zza(this.zzb);
        return null;
    }

    @Nullable
    final PurchasesUpdatedListener zzc() {
        return this.zzb.zzb;
    }

    final void zze() {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        intentFilter.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.zzb.zzc(this.zza, intentFilter);
    }

    zzo(Context context, PurchasesUpdatedListener purchasesUpdatedListener, AlternativeBillingListener alternativeBillingListener, zzbh zzbhVar) {
        this.zza = context;
        this.zzb = new zzn(this, purchasesUpdatedListener, alternativeBillingListener, zzbhVar, null);
    }
}
