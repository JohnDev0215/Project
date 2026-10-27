package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zze;
import com.google.android.gms.internal.play_billing.zzfl;
import com.google.android.gms.internal.play_billing.zzfm;
import com.google.android.gms.internal.play_billing.zzu;
import com.google.android.gms.internal.play_billing.zzz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
class BillingClientImpl extends BillingClient {
    private volatile int zza;
    private final String zzb;
    private final Handler zzc;
    private volatile zzo zzd;
    private Context zze;
    private volatile zze zzf;
    private volatile zzap zzg;
    private boolean zzh;
    private boolean zzi;
    private int zzj;
    private boolean zzk;
    private boolean zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private boolean zzx;
    private ExecutorService zzy;
    private zzbh zzz;

    private void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, boolean z, boolean z2, @Nullable AlternativeBillingListener alternativeBillingListener, String str) {
        this.zze = context.getApplicationContext();
        zzfl zzflVarZzu = zzfm.zzu();
        zzflVarZzu.zzj(str);
        zzflVarZzu.zzi(this.zze.getPackageName());
        this.zzz = new zzbh();
        if (purchasesUpdatedListener == null) {
            zzb.zzj("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.zzd = new zzo(this.zze, purchasesUpdatedListener, alternativeBillingListener, this.zzz);
        this.zzv = z;
        this.zzw = z2;
        this.zzx = alternativeBillingListener != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler zzH() {
        return Looper.myLooper() == null ? this.zzc : new Handler(Looper.myLooper());
    }

    private final BillingResult zzI(final BillingResult billingResult) {
        if (Thread.interrupted()) {
            return billingResult;
        }
        this.zzc.post(new Runnable() { // from class: com.android.billingclient.api.zzag
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzG(billingResult);
            }
        });
        return billingResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BillingResult zzJ() {
        return (this.zza == 0 || this.zza == 3) ? zzbc.zzm : zzbc.zzj;
    }

    @SuppressLint({"PrivateApi"})
    private static String zzK() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        } catch (Exception unused) {
            return "5.2.1";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public final Future zzL(Callable callable, long j, @Nullable final Runnable runnable, Handler handler) {
        if (this.zzy == null) {
            this.zzy = Executors.newFixedThreadPool(zzb.zza, new zzal(this));
        }
        try {
            final Future futureSubmit = this.zzy.submit(callable);
            handler.postDelayed(new Runnable() { // from class: com.android.billingclient.api.zzaf
                @Override // java.lang.Runnable
                public final void run() {
                    Future future = futureSubmit;
                    Runnable runnable2 = runnable;
                    if (future.isDone() || future.isCancelled()) {
                        return;
                    }
                    future.cancel(true);
                    zzb.zzj("BillingClient", "Async task is taking too long, cancel it!");
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }, (long) (j * 0.95d));
            return futureSubmit;
        } catch (Exception e) {
            zzb.zzk("BillingClient", "Async task throws exception!", e);
            return null;
        }
    }

    private final void zzO(String str, final PurchasesResponseListener purchasesResponseListener) {
        if (!isReady()) {
            purchasesResponseListener.onQueryPurchasesResponse(zzbc.zzm, zzu.zzk());
            return;
        }
        if (TextUtils.isEmpty(str)) {
            zzb.zzj("BillingClient", "Please provide a valid product type.");
            purchasesResponseListener.onQueryPurchasesResponse(zzbc.zzg, zzu.zzk());
        } else if (zzL(new zzai(this, str, purchasesResponseListener), 30000L, new Runnable() { // from class: com.android.billingclient.api.zzad
            @Override // java.lang.Runnable
            public final void run() {
                purchasesResponseListener.onQueryPurchasesResponse(zzbc.zzn, zzu.zzk());
            }
        }, zzH()) == null) {
            purchasesResponseListener.onQueryPurchasesResponse(zzJ(), zzu.zzk());
        }
    }

    private final boolean zzP() {
        return this.zzu && this.zzw;
    }

    static /* bridge */ /* synthetic */ zzbk zzi(BillingClientImpl billingClientImpl, String str) {
        zzb.zzi("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        Bundle bundleZzc = zzb.zzc(billingClientImpl.zzm, billingClientImpl.zzu, billingClientImpl.zzv, billingClientImpl.zzw, billingClientImpl.zzb);
        String string = null;
        do {
            try {
                Bundle bundleZzj = billingClientImpl.zzm ? billingClientImpl.zzf.zzj(true != billingClientImpl.zzu ? 9 : 19, billingClientImpl.zze.getPackageName(), str, string, bundleZzc) : billingClientImpl.zzf.zzi(3, billingClientImpl.zze.getPackageName(), str, string);
                BillingResult billingResultZza = zzbl.zza(bundleZzj, "BillingClient", "getPurchase()");
                if (billingResultZza != zzbc.zzl) {
                    return new zzbk(billingResultZza, null);
                }
                ArrayList<String> stringArrayList = bundleZzj.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundleZzj.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundleZzj.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                for (int i = 0; i < stringArrayList2.size(); i++) {
                    String str2 = stringArrayList2.get(i);
                    String str3 = stringArrayList3.get(i);
                    zzb.zzi("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList.get(i))));
                    try {
                        Purchase purchase = new Purchase(str2, str3);
                        if (TextUtils.isEmpty(purchase.getPurchaseToken())) {
                            zzb.zzj("BillingClient", "BUG: empty/null token!");
                        }
                        arrayList.add(purchase);
                    } catch (JSONException e) {
                        zzb.zzk("BillingClient", "Got an exception trying to decode the purchase!", e);
                        return new zzbk(zzbc.zzj, null);
                    }
                }
                string = bundleZzj.getString("INAPP_CONTINUATION_TOKEN");
                zzb.zzi("BillingClient", "Continuation token: ".concat(String.valueOf(string)));
            } catch (Exception e2) {
                zzb.zzk("BillingClient", "Got exception trying to get purchasesm try to reconnect", e2);
                return new zzbk(zzbc.zzm, null);
            }
        } while (!TextUtils.isEmpty(string));
        return new zzbk(zzbc.zzl, arrayList);
    }

    @Override // com.android.billingclient.api.BillingClient
    public final int getConnectionState() {
        return this.zza;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0084  */
    @Override // com.android.billingclient.api.BillingClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.android.billingclient.api.BillingResult isFeatureSupported(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.isFeatureSupported(java.lang.String):com.android.billingclient.api.BillingResult");
    }

    @Override // com.android.billingclient.api.BillingClient
    public final boolean isReady() {
        return (this.zza != 2 || this.zzf == null || this.zzg == null) ? false : true;
    }

    @Override // com.android.billingclient.api.BillingClient
    public final BillingResult launchBillingFlow(Activity activity, final BillingFlowParams billingFlowParams) {
        String str;
        String str2;
        Future futureZzL;
        int i;
        boolean z;
        String str3;
        SkuDetails skuDetails;
        BillingFlowParams.ProductDetailsParams productDetailsParams;
        String str4;
        String str5;
        boolean z2;
        String str6;
        boolean z3;
        String str7;
        int i2;
        final int i3;
        BillingFlowParams.ProductDetailsParams productDetailsParams2;
        final BillingClientImpl billingClientImpl = this;
        if (!isReady()) {
            BillingResult billingResult = zzbc.zzm;
            zzba.zza(2, 2, billingResult);
            billingClientImpl.zzI(billingResult);
            return billingResult;
        }
        ArrayList<SkuDetails> arrayListZzf = billingFlowParams.zzf();
        List listZzg = billingFlowParams.zzg();
        SkuDetails skuDetails2 = (SkuDetails) zzz.zza(arrayListZzf, null);
        BillingFlowParams.ProductDetailsParams productDetailsParams3 = (BillingFlowParams.ProductDetailsParams) zzz.zza(listZzg, null);
        if (skuDetails2 == null) {
            productDetailsParams3.zza();
            throw null;
        }
        final String sku = skuDetails2.getSku();
        final String type = skuDetails2.getType();
        if (type.equals("subs") && !billingClientImpl.zzh) {
            zzb.zzj("BillingClient", "Current client doesn't support subscriptions.");
            BillingResult billingResult2 = zzbc.zzo;
            zzba.zza(9, 2, billingResult2);
            billingClientImpl.zzI(billingResult2);
            return billingResult2;
        }
        if (billingFlowParams.zzp() && !billingClientImpl.zzk) {
            zzb.zzj("BillingClient", "Current client doesn't support extra params for buy intent.");
            BillingResult billingResult3 = zzbc.zzh;
            zzba.zza(18, 2, billingResult3);
            billingClientImpl.zzI(billingResult3);
            return billingResult3;
        }
        if (arrayListZzf.size() > 1 && !billingClientImpl.zzr) {
            zzb.zzj("BillingClient", "Current client doesn't support multi-item purchases.");
            BillingResult billingResult4 = zzbc.zzt;
            zzba.zza(19, 2, billingResult4);
            billingClientImpl.zzI(billingResult4);
            return billingResult4;
        }
        if (!listZzg.isEmpty() && !billingClientImpl.zzs) {
            zzb.zzj("BillingClient", "Current client doesn't support purchases with ProductDetails.");
            BillingResult billingResult5 = zzbc.zzv;
            zzba.zza(20, 2, billingResult5);
            billingClientImpl.zzI(billingResult5);
            return billingResult5;
        }
        if (billingClientImpl.zzk) {
            boolean z4 = billingClientImpl.zzm;
            boolean z5 = billingClientImpl.zzu;
            boolean z6 = billingClientImpl.zzv;
            boolean z7 = billingClientImpl.zzw;
            boolean z8 = billingClientImpl.zzx;
            str = "BUY_INTENT";
            String str8 = billingClientImpl.zzb;
            final Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str8);
            if (billingFlowParams.zza() != 0) {
                bundle.putInt("prorationMode", billingFlowParams.zza());
            }
            if (!TextUtils.isEmpty(billingFlowParams.zzb())) {
                bundle.putString("accountId", billingFlowParams.zzb());
            }
            if (!TextUtils.isEmpty(billingFlowParams.zzc())) {
                bundle.putString("obfuscatedProfileId", billingFlowParams.zzc());
            }
            if (billingFlowParams.zzo()) {
                bundle.putBoolean("isOfferPersonalizedByDeveloper", true);
            }
            if (!TextUtils.isEmpty(null)) {
                bundle.putStringArrayList("skusToReplace", new ArrayList<>(Arrays.asList(null)));
            }
            if (!TextUtils.isEmpty(billingFlowParams.zzd())) {
                bundle.putString("oldSkuPurchaseToken", billingFlowParams.zzd());
            }
            String str9 = null;
            if (!TextUtils.isEmpty(null)) {
                bundle.putString("oldSkuPurchaseId", null);
            }
            if (!TextUtils.isEmpty(billingFlowParams.zze())) {
                bundle.putString("originalExternalTransactionId", billingFlowParams.zze());
                str9 = null;
            }
            if (!TextUtils.isEmpty(str9)) {
                bundle.putString("paymentsPurchaseParams", str9);
            }
            if (z4 && z6) {
                z = true;
                bundle.putBoolean("enablePendingPurchases", true);
            } else {
                z = true;
            }
            if (z5 && z7) {
                bundle.putBoolean("enablePendingPurchaseForSubscriptions", z);
            }
            if (z8) {
                bundle.putBoolean("enableAlternativeBilling", z);
            }
            if (arrayListZzf.isEmpty()) {
                str3 = "proxyPackageVersion";
                skuDetails = skuDetails2;
                productDetailsParams = productDetailsParams3;
                str4 = sku;
                str5 = "BillingClient";
                z2 = true;
                ArrayList<String> arrayList = new ArrayList<>(listZzg.size() - 1);
                ArrayList<String> arrayList2 = new ArrayList<>(listZzg.size() - 1);
                ArrayList<String> arrayList3 = new ArrayList<>();
                ArrayList<String> arrayList4 = new ArrayList<>();
                ArrayList<String> arrayList5 = new ArrayList<>();
                if (listZzg.size() > 0) {
                    ((BillingFlowParams.ProductDetailsParams) listZzg.get(0)).zza();
                    throw null;
                }
                bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                if (!arrayList3.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList3);
                }
                if (!arrayList5.isEmpty()) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
                }
                if (!arrayList.isEmpty()) {
                    bundle.putStringArrayList("additionalSkus", arrayList);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList2);
                }
            } else {
                ArrayList<String> arrayList6 = new ArrayList<>();
                ArrayList<String> arrayList7 = new ArrayList<>();
                str4 = sku;
                ArrayList<String> arrayList8 = new ArrayList<>();
                str3 = "proxyPackageVersion";
                ArrayList<Integer> arrayList9 = new ArrayList<>();
                str5 = "BillingClient";
                ArrayList<String> arrayList10 = new ArrayList<>();
                boolean z9 = false;
                boolean z10 = false;
                boolean z11 = false;
                boolean z12 = false;
                for (SkuDetails skuDetails3 : arrayListZzf) {
                    if (skuDetails3.zzf().isEmpty()) {
                        productDetailsParams2 = productDetailsParams3;
                    } else {
                        productDetailsParams2 = productDetailsParams3;
                        arrayList6.add(skuDetails3.zzf());
                    }
                    String strZzc = skuDetails3.zzc();
                    SkuDetails skuDetails4 = skuDetails2;
                    String strZzb = skuDetails3.zzb();
                    int iZza = skuDetails3.zza();
                    String strZze = skuDetails3.zze();
                    arrayList7.add(strZzc);
                    z9 |= !TextUtils.isEmpty(strZzc);
                    arrayList8.add(strZzb);
                    z10 |= !TextUtils.isEmpty(strZzb);
                    arrayList9.add(Integer.valueOf(iZza));
                    z11 |= iZza != 0;
                    z12 |= !TextUtils.isEmpty(strZze);
                    arrayList10.add(strZze);
                    productDetailsParams3 = productDetailsParams2;
                    skuDetails2 = skuDetails4;
                }
                skuDetails = skuDetails2;
                productDetailsParams = productDetailsParams3;
                if (!arrayList6.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList6);
                }
                if (z9) {
                    bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList7);
                }
                if (z10) {
                    bundle.putStringArrayList("SKU_OFFER_ID_LIST", arrayList8);
                }
                if (z11) {
                    bundle.putIntegerArrayList("SKU_OFFER_TYPE_LIST", arrayList9);
                }
                if (z12) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList10);
                }
                z2 = true;
                if (arrayListZzf.size() > 1) {
                    ArrayList<String> arrayList11 = new ArrayList<>(arrayListZzf.size() - 1);
                    ArrayList<String> arrayList12 = new ArrayList<>(arrayListZzf.size() - 1);
                    for (int i4 = 1; i4 < arrayListZzf.size(); i4++) {
                        arrayList11.add(((SkuDetails) arrayListZzf.get(i4)).getSku());
                        arrayList12.add(((SkuDetails) arrayListZzf.get(i4)).getType());
                    }
                    bundle.putStringArrayList("additionalSkus", arrayList11);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList12);
                }
            }
            billingClientImpl = this;
            if (bundle.containsKey("SKU_OFFER_ID_TOKEN_LIST") && !billingClientImpl.zzp) {
                BillingResult billingResult6 = zzbc.zzu;
                zzba.zza(21, 2, billingResult6);
                billingClientImpl.zzI(billingResult6);
                return billingResult6;
            }
            if (skuDetails == null || TextUtils.isEmpty(skuDetails.zzd())) {
                if (productDetailsParams != null) {
                    productDetailsParams.zza();
                    throw null;
                }
                str6 = null;
                z3 = false;
            } else {
                bundle.putString("skuPackageName", skuDetails.zzd());
                z3 = z2;
                str6 = null;
            }
            if (!TextUtils.isEmpty(str6)) {
                bundle.putString("accountName", str6);
            }
            Intent intent = activity.getIntent();
            if (intent == null) {
                str2 = str5;
                zzb.zzj(str2, "Activity's intent is null.");
            } else {
                str2 = str5;
                if (!TextUtils.isEmpty(intent.getStringExtra("PROXY_PACKAGE"))) {
                    String stringExtra = intent.getStringExtra("PROXY_PACKAGE");
                    bundle.putString("proxyPackage", stringExtra);
                    try {
                        str7 = str3;
                    } catch (PackageManager.NameNotFoundException unused) {
                        str7 = str3;
                    }
                    try {
                        bundle.putString(str7, billingClientImpl.zze.getPackageManager().getPackageInfo(stringExtra, 0).versionName);
                    } catch (PackageManager.NameNotFoundException unused2) {
                        bundle.putString(str7, "package not found");
                    }
                }
            }
            if (billingClientImpl.zzs && !listZzg.isEmpty()) {
                i2 = 17;
            } else if (billingClientImpl.zzq && z3) {
                i2 = 15;
            } else if (billingClientImpl.zzm) {
                i3 = 9;
                final String str10 = str4;
                futureZzL = zzL(new Callable() { // from class: com.android.billingclient.api.zzab
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return this.zza.zzc(i3, str10, type, billingFlowParams, bundle);
                    }
                }, CoroutineLiveDataKt.DEFAULT_TIMEOUT, null, billingClientImpl.zzc);
            } else {
                i2 = 6;
            }
            i3 = i2;
            final String str102 = str4;
            futureZzL = zzL(new Callable() { // from class: com.android.billingclient.api.zzab
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.zza.zzc(i3, str102, type, billingFlowParams, bundle);
                }
            }, CoroutineLiveDataKt.DEFAULT_TIMEOUT, null, billingClientImpl.zzc);
        } else {
            str = "BUY_INTENT";
            str2 = "BillingClient";
            futureZzL = zzL(new Callable() { // from class: com.android.billingclient.api.zzac
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.zza.zzd(sku, type);
                }
            }, CoroutineLiveDataKt.DEFAULT_TIMEOUT, null, billingClientImpl.zzc);
        }
        try {
            try {
                Bundle bundle2 = (Bundle) futureZzL.get(CoroutineLiveDataKt.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
                int iZzb = zzb.zzb(bundle2, str2);
                String strZzf = zzb.zzf(bundle2, str2);
                if (iZzb == 0) {
                    Intent intent2 = new Intent(activity, (Class<?>) ProxyBillingActivity.class);
                    String str11 = str;
                    intent2.putExtra(str11, (PendingIntent) bundle2.getParcelable(str11));
                    activity.startActivity(intent2);
                    return zzbc.zzl;
                }
                zzb.zzj(str2, "Unable to buy item, Error response code: " + iZzb);
                BillingResult.Builder builderNewBuilder = BillingResult.newBuilder();
                builderNewBuilder.setResponseCode(iZzb);
                builderNewBuilder.setDebugMessage(strZzf);
                BillingResult billingResultBuild = builderNewBuilder.build();
                i = 2;
                try {
                    zzba.zza(3, 2, billingResultBuild);
                    billingClientImpl.zzI(billingResultBuild);
                    return billingResultBuild;
                } catch (CancellationException e) {
                    e = e;
                    zzb.zzk(str2, "Time out while launching billing flow. Try to reconnect", e);
                    BillingResult billingResult7 = zzbc.zzn;
                    zzba.zza(4, i, billingResult7);
                    billingClientImpl.zzI(billingResult7);
                    return billingResult7;
                } catch (TimeoutException e2) {
                    e = e2;
                    zzb.zzk(str2, "Time out while launching billing flow. Try to reconnect", e);
                    BillingResult billingResult72 = zzbc.zzn;
                    zzba.zza(4, i, billingResult72);
                    billingClientImpl.zzI(billingResult72);
                    return billingResult72;
                }
            } catch (CancellationException | TimeoutException e3) {
                e = e3;
                i = 2;
            }
        } catch (Exception e4) {
            zzb.zzk(str2, "Exception while launching billing flow. Try to reconnect", e4);
            BillingResult billingResult8 = zzbc.zzm;
            zzba.zza(5, 2, billingResult8);
            billingClientImpl.zzI(billingResult8);
            return billingResult8;
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void querySkuDetailsAsync(SkuDetailsParams skuDetailsParams, final SkuDetailsResponseListener skuDetailsResponseListener) {
        if (!isReady()) {
            skuDetailsResponseListener.onSkuDetailsResponse(zzbc.zzm, null);
            return;
        }
        final String skuType = skuDetailsParams.getSkuType();
        List<String> skusList = skuDetailsParams.getSkusList();
        if (TextUtils.isEmpty(skuType)) {
            zzb.zzj("BillingClient", "Please fix the input params. SKU type can't be empty.");
            skuDetailsResponseListener.onSkuDetailsResponse(zzbc.zzf, null);
            return;
        }
        if (skusList == null) {
            zzb.zzj("BillingClient", "Please fix the input params. The list of SKUs can't be empty - set SKU list or SkuWithOffer list.");
            skuDetailsResponseListener.onSkuDetailsResponse(zzbc.zze, null);
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (String str : skusList) {
            zzbw zzbwVar = new zzbw(null);
            zzbwVar.zza(str);
            arrayList.add(zzbwVar.zzb());
        }
        final String str2 = null;
        if (zzL(new Callable(skuType, arrayList, str2, skuDetailsResponseListener) { // from class: com.android.billingclient.api.zzq
            public final /* synthetic */ String zzb;
            public final /* synthetic */ List zzc;
            public final /* synthetic */ SkuDetailsResponseListener zzd;

            {
                this.zzd = skuDetailsResponseListener;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzn(this.zzb, this.zzc, null, this.zzd);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzy
            @Override // java.lang.Runnable
            public final void run() {
                skuDetailsResponseListener.onSkuDetailsResponse(zzbc.zzn, null);
            }
        }, zzH()) == null) {
            skuDetailsResponseListener.onSkuDetailsResponse(zzJ(), null);
        }
    }

    final /* synthetic */ void zzG(BillingResult billingResult) {
        if (this.zzd.zzc() != null) {
            this.zzd.zzc().onPurchasesUpdated(billingResult, null);
        } else {
            this.zzd.zzb();
            zzb.zzj("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    final /* synthetic */ Bundle zzc(int i, String str, String str2, BillingFlowParams billingFlowParams, Bundle bundle) throws Exception {
        return this.zzf.zzg(i, this.zze.getPackageName(), str, str2, null, bundle);
    }

    final /* synthetic */ Bundle zzd(String str, String str2) throws Exception {
        return this.zzf.zzf(3, this.zze.getPackageName(), str, str2, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0199, code lost:
    
        r4 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object zzn(java.lang.String r24, java.util.List r25, java.lang.String r26, com.android.billingclient.api.SkuDetailsResponseListener r27) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.zzn(java.lang.String, java.util.List, java.lang.String, com.android.billingclient.api.SkuDetailsResponseListener):java.lang.Object");
    }

    @Override // com.android.billingclient.api.BillingClient
    public void queryPurchasesAsync(String str, PurchasesResponseListener purchasesResponseListener) {
        zzO(str, purchasesResponseListener);
    }

    @AnyThread
    private BillingClientImpl(Context context, boolean z, boolean z2, PurchasesUpdatedListener purchasesUpdatedListener, String str, String str2, @Nullable AlternativeBillingListener alternativeBillingListener) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzj = 0;
        this.zzb = str;
        initialize(context, purchasesUpdatedListener, z, z2, alternativeBillingListener, str);
    }

    @AnyThread
    BillingClientImpl(@Nullable String str, boolean z, Context context, zzbf zzbfVar) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzj = 0;
        this.zzb = zzK();
        this.zze = context.getApplicationContext();
        zzfl zzflVarZzu = zzfm.zzu();
        zzflVarZzu.zzj(zzK());
        zzflVarZzu.zzi(this.zze.getPackageName());
        this.zzz = new zzbh();
        zzb.zzj("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.zzd = new zzo(this.zze, null, this.zzz);
        this.zzv = z;
    }

    @AnyThread
    BillingClientImpl(@Nullable String str, boolean z, boolean z2, Context context, PurchasesUpdatedListener purchasesUpdatedListener, @Nullable AlternativeBillingListener alternativeBillingListener) {
        this(context, z, false, purchasesUpdatedListener, zzK(), null, alternativeBillingListener);
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void startConnection(BillingClientStateListener billingClientStateListener) {
        ServiceInfo serviceInfo;
        if (isReady()) {
            zzb.zzi("BillingClient", "Service connection is valid. No need to re-initialize.");
            billingClientStateListener.onBillingSetupFinished(zzbc.zzl);
            return;
        }
        if (this.zza == 1) {
            zzb.zzj("BillingClient", "Client is already in the process of connecting to billing service.");
            billingClientStateListener.onBillingSetupFinished(zzbc.zzd);
            return;
        }
        if (this.zza == 3) {
            zzb.zzj("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            billingClientStateListener.onBillingSetupFinished(zzbc.zzm);
            return;
        }
        this.zza = 1;
        this.zzd.zze();
        zzb.zzi("BillingClient", "Starting in-app billing setup.");
        this.zzg = new zzap(this, billingClientStateListener, null);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List<ResolveInfo> listQueryIntentServices = this.zze.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices != null && !listQueryIntentServices.isEmpty() && (serviceInfo = listQueryIntentServices.get(0).serviceInfo) != null) {
            String str = serviceInfo.packageName;
            String str2 = serviceInfo.name;
            if (!"com.android.vending".equals(str) || str2 == null) {
                zzb.zzj("BillingClient", "The device doesn't have valid Play Store.");
            } else {
                ComponentName componentName = new ComponentName(str, str2);
                Intent intent2 = new Intent(intent);
                intent2.setComponent(componentName);
                intent2.putExtra("playBillingLibraryVersion", this.zzb);
                if (this.zze.bindService(intent2, this.zzg, 1)) {
                    zzb.zzi("BillingClient", "Service was bonded successfully.");
                    return;
                }
                zzb.zzj("BillingClient", "Connection to Billing service is blocked.");
            }
        }
        this.zza = 0;
        zzb.zzi("BillingClient", "Billing service unavailable on device.");
        billingClientStateListener.onBillingSetupFinished(zzbc.zzc);
    }
}
