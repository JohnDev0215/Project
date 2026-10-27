package com.android.billingclient.api;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.play_billing.zzb;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class BillingResult {
    private int zza;
    private String zzb;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
    public static class Builder {
        private int zza;
        private String zzb = "";

        private Builder() {
        }

        /* synthetic */ Builder(zzbb zzbbVar) {
        }

        @NonNull
        public BillingResult build() {
            BillingResult billingResult = new BillingResult();
            billingResult.zza = this.zza;
            billingResult.zzb = this.zzb;
            return billingResult;
        }

        @NonNull
        public Builder setDebugMessage(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setResponseCode(int i) {
            this.zza = i;
            return this;
        }
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    @NonNull
    public String getDebugMessage() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }

    @NonNull
    public String toString() {
        return "Response Code: " + zzb.zzg(this.zza) + ", Debug Message: " + this.zzb;
    }
}
