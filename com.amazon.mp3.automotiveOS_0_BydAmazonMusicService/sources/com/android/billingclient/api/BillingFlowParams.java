package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing.zzu;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public class BillingFlowParams {
    private boolean zza;
    private String zzb;
    private String zzc;
    private SubscriptionUpdateParams zzd;
    private zzu zze;
    private ArrayList zzf;
    private boolean zzg;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
    public static class Builder {
        private String zza;
        private String zzb;
        private List zzc;
        private ArrayList zzd;
        private boolean zze;
        private SubscriptionUpdateParams.Builder zzf;

        private Builder() {
            SubscriptionUpdateParams.Builder builderNewBuilder = SubscriptionUpdateParams.newBuilder();
            SubscriptionUpdateParams.Builder.zza(builderNewBuilder);
            this.zzf = builderNewBuilder;
        }

        @NonNull
        public BillingFlowParams build() {
            ArrayList arrayList = this.zzd;
            boolean z = true;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            List list = this.zzc;
            boolean z3 = (list == null || list.isEmpty()) ? false : true;
            if (!z2 && !z3) {
                throw new IllegalArgumentException("Details of the products must be provided.");
            }
            if (z2 && z3) {
                throw new IllegalArgumentException("Set SkuDetails or ProductDetailsParams, not both.");
            }
            zzaz zzazVar = null;
            if (!z2) {
                ProductDetailsParams productDetailsParams = (ProductDetailsParams) this.zzc.get(0);
                for (int i = 0; i < this.zzc.size(); i++) {
                    ProductDetailsParams productDetailsParams2 = (ProductDetailsParams) this.zzc.get(i);
                    if (productDetailsParams2 == null) {
                        throw new IllegalArgumentException("ProductDetailsParams cannot be null.");
                    }
                    if (i != 0) {
                        productDetailsParams2.zza();
                        throw null;
                    }
                }
                productDetailsParams.zza();
                throw null;
            }
            if (this.zzd.contains(null)) {
                throw new IllegalArgumentException("SKU cannot be null.");
            }
            if (this.zzd.size() > 1) {
                SkuDetails skuDetails = (SkuDetails) this.zzd.get(0);
                String type = skuDetails.getType();
                ArrayList arrayList2 = this.zzd;
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    SkuDetails skuDetails2 = (SkuDetails) arrayList2.get(i2);
                    if (!type.equals("play_pass_subs") && !skuDetails2.getType().equals("play_pass_subs") && !type.equals(skuDetails2.getType())) {
                        throw new IllegalArgumentException("SKUs should have the same type.");
                    }
                }
                String strZzd = skuDetails.zzd();
                ArrayList arrayList3 = this.zzd;
                int size2 = arrayList3.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    SkuDetails skuDetails3 = (SkuDetails) arrayList3.get(i3);
                    if (!type.equals("play_pass_subs") && !skuDetails3.getType().equals("play_pass_subs") && !strZzd.equals(skuDetails3.zzd())) {
                        throw new IllegalArgumentException("All SKUs must have the same package name.");
                    }
                }
            }
            BillingFlowParams billingFlowParams = new BillingFlowParams(zzazVar);
            if (!z2 || ((SkuDetails) this.zzd.get(0)).zzd().isEmpty()) {
                if (z3) {
                    ((ProductDetailsParams) this.zzc.get(0)).zza();
                    throw null;
                }
                z = false;
            }
            billingFlowParams.zza = z;
            billingFlowParams.zzb = this.zza;
            billingFlowParams.zzc = this.zzb;
            billingFlowParams.zzd = this.zzf.build();
            ArrayList arrayList4 = this.zzd;
            billingFlowParams.zzf = arrayList4 != null ? new ArrayList(arrayList4) : new ArrayList();
            billingFlowParams.zzg = this.zze;
            List list2 = this.zzc;
            billingFlowParams.zze = list2 != null ? zzu.zzj(list2) : zzu.zzk();
            return billingFlowParams;
        }

        @NonNull
        public Builder setObfuscatedAccountId(@NonNull String str) {
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setObfuscatedProfileId(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSkuDetails(@NonNull SkuDetails skuDetails) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(skuDetails);
            this.zzd = arrayList;
            return this;
        }

        /* synthetic */ Builder(zzau zzauVar) {
            SubscriptionUpdateParams.Builder builderNewBuilder = SubscriptionUpdateParams.newBuilder();
            SubscriptionUpdateParams.Builder.zza(builderNewBuilder);
            this.zzf = builderNewBuilder;
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
    public static final class ProductDetailsParams {
        @NonNull
        public final ProductDetails zza() {
            return null;
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
    public static class SubscriptionUpdateParams {
        private String zza;
        private String zzb;
        private int zzc = 0;

        /* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
        public static class Builder {
            private String zza;
            private String zzb;
            private boolean zzc;
            private int zzd = 0;

            private Builder() {
            }

            /* synthetic */ Builder(zzax zzaxVar) {
            }

            static /* synthetic */ Builder zza(Builder builder) {
                builder.zzc = true;
                return builder;
            }

            @NonNull
            public SubscriptionUpdateParams build() {
                zzay zzayVar = null;
                boolean z = (TextUtils.isEmpty(this.zza) && TextUtils.isEmpty(null)) ? false : true;
                boolean zIsEmpty = true ^ TextUtils.isEmpty(this.zzb);
                if (z && zIsEmpty) {
                    throw new IllegalArgumentException("Please provide Old SKU purchase information(token/id) or original external transaction id, not both.");
                }
                if (!this.zzc && !z && !zIsEmpty) {
                    throw new IllegalArgumentException("Old SKU purchase information(token/id) or original external transaction id must be provided.");
                }
                SubscriptionUpdateParams subscriptionUpdateParams = new SubscriptionUpdateParams(zzayVar);
                subscriptionUpdateParams.zza = this.zza;
                subscriptionUpdateParams.zzc = this.zzd;
                subscriptionUpdateParams.zzb = this.zzb;
                return subscriptionUpdateParams;
            }
        }

        private SubscriptionUpdateParams() {
        }

        /* synthetic */ SubscriptionUpdateParams(zzay zzayVar) {
        }

        @NonNull
        public static Builder newBuilder() {
            return new Builder(null);
        }

        final int zza() {
            return this.zzc;
        }

        final String zzc() {
            return this.zza;
        }

        final String zzd() {
            return this.zzb;
        }
    }

    private BillingFlowParams() {
    }

    /* synthetic */ BillingFlowParams(zzaz zzazVar) {
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    public final int zza() {
        return this.zzd.zza();
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final String zzc() {
        return this.zzc;
    }

    @Nullable
    public final String zzd() {
        return this.zzd.zzc();
    }

    @Nullable
    public final String zze() {
        return this.zzd.zzd();
    }

    @NonNull
    public final ArrayList zzf() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzf);
        return arrayList;
    }

    @NonNull
    public final List zzg() {
        return this.zze;
    }

    public final boolean zzo() {
        return this.zzg;
    }

    final boolean zzp() {
        return (this.zzb == null && this.zzc == null && this.zzd.zzd() == null && this.zzd.zza() == 0 && !this.zza && !this.zzg) ? false : true;
    }
}
