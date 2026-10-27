package com.android.billingclient.api;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class SkuDetailsParams {
    private String zza;
    private List zzb;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
    public static class Builder {
        private String zza;
        private List zzb;

        private Builder() {
        }

        /* synthetic */ Builder(zzbu zzbuVar) {
        }

        @NonNull
        public SkuDetailsParams build() {
            String str = this.zza;
            if (str == null) {
                throw new IllegalArgumentException("SKU type must be set");
            }
            if (this.zzb == null) {
                throw new IllegalArgumentException("SKU list or SkuWithOffer list must be set");
            }
            SkuDetailsParams skuDetailsParams = new SkuDetailsParams();
            skuDetailsParams.zza = str;
            skuDetailsParams.zzb = this.zzb;
            return skuDetailsParams;
        }

        @NonNull
        public Builder setSkusList(@NonNull List<String> list) {
            this.zzb = new ArrayList(list);
            return this;
        }

        @NonNull
        public Builder setType(@NonNull String str) {
            this.zza = str;
            return this;
        }
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    @NonNull
    public String getSkuType() {
        return this.zza;
    }

    @NonNull
    public List<String> getSkusList() {
        return this.zzb;
    }
}
