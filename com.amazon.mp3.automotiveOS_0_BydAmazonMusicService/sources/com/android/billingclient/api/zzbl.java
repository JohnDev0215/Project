package com.android.billingclient.api;

import android.os.Bundle;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing.zzb;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbl {
    static BillingResult zza(Bundle bundle, String str, String str2) {
        BillingResult billingResult = zzbc.zzj;
        if (bundle == null) {
            zzb.zzj("BillingClient", String.format("%s got null owned items list", str2));
            return billingResult;
        }
        int iZzb = zzb.zzb(bundle, "BillingClient");
        String strZzf = zzb.zzf(bundle, "BillingClient");
        BillingResult.Builder builderNewBuilder = BillingResult.newBuilder();
        builderNewBuilder.setResponseCode(iZzb);
        builderNewBuilder.setDebugMessage(strZzf);
        BillingResult billingResultBuild = builderNewBuilder.build();
        if (iZzb != 0) {
            zzb.zzj("BillingClient", String.format("%s failed. Response code: %s", str2, Integer.valueOf(iZzb)));
            return billingResultBuild;
        }
        if (!bundle.containsKey("INAPP_PURCHASE_ITEM_LIST") || !bundle.containsKey("INAPP_PURCHASE_DATA_LIST") || !bundle.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
            zzb.zzj("BillingClient", String.format("Bundle returned from %s doesn't contain required fields.", str2));
            return billingResult;
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList<String> stringArrayList3 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        if (stringArrayList == null) {
            zzb.zzj("BillingClient", String.format("Bundle returned from %s contains null SKUs list.", str2));
            return billingResult;
        }
        if (stringArrayList2 == null) {
            zzb.zzj("BillingClient", String.format("Bundle returned from %s contains null purchases list.", str2));
            return billingResult;
        }
        if (stringArrayList3 != null) {
            return zzbc.zzl;
        }
        zzb.zzj("BillingClient", String.format("Bundle returned from %s contains null signatures list.", str2));
        return billingResult;
    }
}
