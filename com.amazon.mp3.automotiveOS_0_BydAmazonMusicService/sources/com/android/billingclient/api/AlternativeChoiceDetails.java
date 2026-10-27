package com.android.billingclient.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class AlternativeChoiceDetails {
    private final String zza;
    private final JSONObject zzb;
    private final List zzc;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
    public static class Product {
        private final String zza;
        private final String zzb;

        @Nullable
        private final String zzc;

        /* synthetic */ Product(JSONObject jSONObject, zzc zzcVar) {
            this.zza = jSONObject.optString("productId");
            this.zzb = jSONObject.optString("productType");
            String strOptString = jSONObject.optString("offerToken");
            this.zzc = true == strOptString.isEmpty() ? null : strOptString;
        }

        public final boolean equals(@Nullable Object obj) {
            String str;
            String offerToken;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Product)) {
                return false;
            }
            Product product = (Product) obj;
            return this.zza.equals(product.getId()) && this.zzb.equals(product.getType()) && ((str = this.zzc) == (offerToken = product.getOfferToken()) || (str != null && str.equals(offerToken)));
        }

        @NonNull
        public String getId() {
            return this.zza;
        }

        @Nullable
        public String getOfferToken() {
            return this.zzc;
        }

        @NonNull
        public String getType() {
            return this.zzb;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzc});
        }

        @NonNull
        public final String toString() {
            return String.format("{id: %s, type: %s, offer token: %s}", this.zza, this.zzb, this.zzc);
        }
    }

    AlternativeChoiceDetails(String str) throws JSONException {
        this.zza = str;
        JSONObject jSONObject = new JSONObject(str);
        this.zzb = jSONObject;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("products");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    arrayList.add(new Product(jSONObjectOptJSONObject, null));
                }
            }
        }
        this.zzc = arrayList;
    }
}
