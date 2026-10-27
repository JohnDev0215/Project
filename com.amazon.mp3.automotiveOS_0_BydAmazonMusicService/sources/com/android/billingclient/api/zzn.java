package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzbn;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzu;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
final class zzn extends BroadcastReceiver {
    final /* synthetic */ zzo zza;
    private final PurchasesUpdatedListener zzb;
    private final zzbf zzc;
    private final AlternativeBillingListener zzd;
    private boolean zze;
    private final zzbh zzf;

    /* synthetic */ zzn(zzo zzoVar, zzbf zzbfVar, zzbh zzbhVar, zzm zzmVar) {
        this.zza = zzoVar;
        this.zzb = null;
        this.zzd = null;
        this.zzc = null;
        this.zzf = zzbhVar;
    }

    static /* bridge */ /* synthetic */ zzbf zza(zzn zznVar) {
        zzbf zzbfVar = zznVar.zzc;
        return null;
    }

    private static final void zze(Bundle bundle, BillingResult billingResult, int i) {
        if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") == null) {
            zzba.zza(23, i, billingResult);
            return;
        }
        try {
            zzfb.zzw(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), zzbn.zza());
        } catch (Throwable unused) {
            zzb.zzj("BillingBroadcastManager", "Failed parsing Api failure.");
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            zzb.zzj("BillingBroadcastManager", "Bundle is null.");
            BillingResult billingResult = zzbc.zzj;
            zzba.zza(11, 1, billingResult);
            PurchasesUpdatedListener purchasesUpdatedListener = this.zzb;
            if (purchasesUpdatedListener != null) {
                purchasesUpdatedListener.onPurchasesUpdated(billingResult, null);
                return;
            }
            return;
        }
        BillingResult billingResultZzd = zzb.zzd(intent, "BillingBroadcastManager");
        String action = intent.getAction();
        String string = extras.getString("INTENT_SOURCE");
        int i = 2;
        if (string != "LAUNCH_BILLING_FLOW" && (string == null || !string.equals("LAUNCH_BILLING_FLOW"))) {
            i = 1;
        }
        if (action.equals("com.android.vending.billing.PURCHASES_UPDATED")) {
            if (extras.getBoolean("IS_FIRST_PARTY_PURCHASE", false) || this.zzb == null) {
                zzb.zzj("BillingBroadcastManager", "Received purchase and no valid listener registered.");
                zzba.zza(12, i, zzbc.zzj);
                return;
            }
            List<Purchase> listZzh = zzb.zzh(extras);
            if (billingResultZzd.getResponseCode() == 0) {
                zzba.zzb(i);
            } else {
                zze(extras, billingResultZzd, i);
            }
            this.zzb.onPurchasesUpdated(billingResultZzd, listZzh);
            return;
        }
        if (action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
            if (billingResultZzd.getResponseCode() != 0) {
                zze(extras, billingResultZzd, i);
                this.zzb.onPurchasesUpdated(billingResultZzd, zzu.zzk());
                return;
            }
            if (this.zzd == null) {
                zzb.zzj("BillingBroadcastManager", "AlternativeBillingListener is null.");
                BillingResult billingResult2 = zzbc.zzj;
                zzba.zza(15, i, billingResult2);
                this.zzb.onPurchasesUpdated(billingResult2, zzu.zzk());
                return;
            }
            String string2 = extras.getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
            if (string2 == null) {
                zzb.zzj("BillingBroadcastManager", "Couldn't find alternative billing user choice data in bundle.");
                BillingResult billingResult3 = zzbc.zzj;
                zzba.zza(16, i, billingResult3);
                this.zzb.onPurchasesUpdated(billingResult3, zzu.zzk());
                return;
            }
            try {
                AlternativeChoiceDetails alternativeChoiceDetails = new AlternativeChoiceDetails(string2);
                zzba.zzb(i);
                this.zzd.userSelectedAlternativeBilling(alternativeChoiceDetails);
            } catch (JSONException unused) {
                zzb.zzj("BillingBroadcastManager", String.format("Error when parsing invalid alternative choice data: [%s]", string2));
                BillingResult billingResult4 = zzbc.zzj;
                zzba.zza(17, i, billingResult4);
                this.zzb.onPurchasesUpdated(billingResult4, zzu.zzk());
            }
        }
    }

    public final void zzc(Context context, IntentFilter intentFilter) {
        if (this.zze) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(this.zza.zzb, intentFilter, 2);
        } else {
            context.registerReceiver(this.zza.zzb, intentFilter);
        }
        this.zze = true;
    }

    /* synthetic */ zzn(zzo zzoVar, PurchasesUpdatedListener purchasesUpdatedListener, AlternativeBillingListener alternativeBillingListener, zzbh zzbhVar, zzm zzmVar) {
        this.zza = zzoVar;
        this.zzb = purchasesUpdatedListener;
        this.zzf = zzbhVar;
        this.zzd = alternativeBillingListener;
        this.zzc = null;
    }
}
