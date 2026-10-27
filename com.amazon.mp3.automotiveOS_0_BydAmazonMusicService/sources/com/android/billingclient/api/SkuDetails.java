package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class SkuDetails {
    private final String zza;
    private final JSONObject zzb;

    public SkuDetails(@NonNull String str) throws JSONException {
        this.zza = str;
        JSONObject jSONObject = new JSONObject(str);
        this.zzb = jSONObject;
        if (TextUtils.isEmpty(jSONObject.optString("productId"))) {
            throw new IllegalArgumentException("SKU cannot be empty.");
        }
        if (TextUtils.isEmpty(jSONObject.optString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE))) {
            throw new IllegalArgumentException("SkuType cannot be empty.");
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SkuDetails) {
            return TextUtils.equals(this.zza, ((SkuDetails) obj).zza);
        }
        return false;
    }

    public int getIntroductoryPriceCycles() {
        return this.zzb.optInt("introductoryPriceCycles");
    }

    public long getPriceAmountMicros() {
        return this.zzb.optLong("price_amount_micros");
    }

    @NonNull
    public String getPriceCurrencyCode() {
        return this.zzb.optString("price_currency_code");
    }

    @NonNull
    public String getSku() {
        return this.zzb.optString("productId");
    }

    @NonNull
    public String getType() {
        return this.zzb.optString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    @NonNull
    public String toString() {
        return "SkuDetails: ".concat(String.valueOf(this.zza));
    }

    public int zza() {
        return this.zzb.optInt("offer_type");
    }

    @NonNull
    public String zzb() {
        return this.zzb.optString("offer_id");
    }

    @NonNull
    public String zzc() {
        String strOptString = this.zzb.optString("offerIdToken");
        return strOptString.isEmpty() ? this.zzb.optString("offer_id_token") : strOptString;
    }

    @NonNull
    public final String zzd() {
        return this.zzb.optString(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME);
    }

    @NonNull
    public String zze() {
        return this.zzb.optString("serializedDocid");
    }

    final String zzf() {
        return this.zzb.optString("skuDetailsToken");
    }
}
