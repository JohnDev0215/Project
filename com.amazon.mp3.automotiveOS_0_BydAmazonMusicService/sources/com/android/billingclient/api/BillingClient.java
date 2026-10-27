package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class BillingClient {

    /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
    @AnyThread
    public static final class Builder {
        private volatile boolean zzb;
        private final Context zzd;
        private volatile PurchasesUpdatedListener zze;
        private volatile AlternativeBillingListener zzg;

        /* synthetic */ Builder(Context context, zzp zzpVar) {
            this.zzd = context;
        }

        @NonNull
        public BillingClient build() {
            if (this.zzd == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            if (this.zze == null) {
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if (!this.zzb) {
                throw new IllegalArgumentException("Support for pending purchases must be enabled. Enable this by calling 'enablePendingPurchases()' on BillingClientBuilder.");
            }
            if (this.zze != null || this.zzg == null) {
                return this.zze != null ? new BillingClientImpl(null, this.zzb, false, this.zzd, this.zze, this.zzg) : new BillingClientImpl(null, this.zzb, this.zzd, null);
            }
            throw new IllegalArgumentException("Please provide a valid listener for Google Play Billing purchases updates when enabling Alternative Billing.");
        }

        @NonNull
        public Builder enablePendingPurchases() {
            this.zzb = true;
            return this;
        }

        @NonNull
        public Builder setListener(@NonNull PurchasesUpdatedListener purchasesUpdatedListener) {
            this.zze = purchasesUpdatedListener;
            return this;
        }
    }

    @NonNull
    @AnyThread
    public static Builder newBuilder(@NonNull Context context) {
        return new Builder(context, null);
    }

    @AnyThread
    public abstract int getConnectionState();

    @NonNull
    @AnyThread
    public abstract BillingResult isFeatureSupported(@NonNull String str);

    @AnyThread
    public abstract boolean isReady();

    @NonNull
    @UiThread
    public abstract BillingResult launchBillingFlow(@NonNull Activity activity, @NonNull BillingFlowParams billingFlowParams);

    @AnyThread
    @Deprecated
    public abstract void queryPurchasesAsync(@NonNull String str, @NonNull PurchasesResponseListener purchasesResponseListener);

    @AnyThread
    @Deprecated
    public abstract void querySkuDetailsAsync(@NonNull SkuDetailsParams skuDetailsParams, @NonNull SkuDetailsResponseListener skuDetailsResponseListener);

    @AnyThread
    public abstract void startConnection(@NonNull BillingClientStateListener billingClientStateListener);
}
