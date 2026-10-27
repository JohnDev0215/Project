package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzu;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
final class zzai implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ PurchasesResponseListener zzb;
    final /* synthetic */ BillingClientImpl zzc;

    zzai(BillingClientImpl billingClientImpl, String str, PurchasesResponseListener purchasesResponseListener) {
        this.zzc = billingClientImpl;
        this.zza = str;
        this.zzb = purchasesResponseListener;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzbk zzbkVarZzi = BillingClientImpl.zzi(this.zzc, this.zza);
        if (zzbkVarZzi.zzb() != null) {
            this.zzb.onQueryPurchasesResponse(zzbkVarZzi.zza(), zzbkVarZzi.zzb());
            return null;
        }
        this.zzb.onQueryPurchasesResponse(zzbkVarZzi.zza(), zzu.zzk());
        return null;
    }
}
