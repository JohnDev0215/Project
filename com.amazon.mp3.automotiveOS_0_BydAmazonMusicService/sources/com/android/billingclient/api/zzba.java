package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzfa;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzfh;
import com.google.android.gms.internal.play_billing.zzfj;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class zzba {
    public static zzfb zza(int i, int i2, BillingResult billingResult) {
        zzfa zzfaVarZzu = zzfb.zzu();
        zzfh zzfhVarZzu = zzfj.zzu();
        zzfhVarZzu.zzj(billingResult.getResponseCode());
        zzfhVarZzu.zzi(billingResult.getDebugMessage());
        zzfhVarZzu.zzk(i);
        zzfaVarZzu.zzi(zzfhVarZzu);
        zzfaVarZzu.zzj(i2);
        return (zzfb) zzfaVarZzu.zzc();
    }

    public static zzff zzb(int i) {
        zzfe zzfeVarZzu = zzff.zzu();
        zzfeVarZzu.zzi(i);
        return (zzff) zzfeVarZzu.zzc();
    }
}
