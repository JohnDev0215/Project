package com.amazon.mp3.external.ads;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.amazon.device.ads.AmazonOOAdRegistration;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.service.metrics.CampaignMetrics;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonAds {
    private static final String TAG = "AmazonAds";
    private static boolean sHasRegisteredWithSIS;
    private final Context mContext;

    public AmazonAds(final Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void identifyCustomer() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.external.ads.AmazonAds.1
                @Override // java.lang.Runnable
                public void run() {
                    AmazonAds.this.identifyCustomer();
                }
            });
        } else {
            registerWithSIS(this.mContext);
            identifyCustomerWithDMS();
        }
    }

    private void identifyCustomerWithDMS() {
        if (hasRegisteredWithSIS()) {
            if (AccountCredentialUtil.get(this.mContext).isSignedIn()) {
                AmazonOOAdRegistration.identifyAmazonUserUsingMAP();
                Log.verbose(TAG, "Identification successful");
                return;
            }
            return;
        }
        Log.warning(TAG, "Failed to identify. The device is not registered with SIS.");
    }

    private static synchronized void registerWithSIS(final Context context) {
        if (hasRegisteredWithSIS()) {
            Log.verbose(TAG, "Already registered with SIS");
        } else {
            String str = TAG;
            Log.verbose(str, "Registering device with SIS");
            Log.debug(str, "Application name: %s / id: %s", "cloudplayer", "3e27ca024d104cc9b4644a0dd64fc5e9");
            AmazonOOAdRegistration.setAppName("cloudplayer");
            AmazonOOAdRegistration.setAppKey("3e27ca024d104cc9b4644a0dd64fc5e9");
            AmazonOOAdRegistration.setAppDefinedMarketplace(AccountDetailUtil.getMusicTerritoryOfResidence());
            String rawReferrer = CampaignMetrics.loadCampaignReferrer(context).getRawReferrer();
            if (!TextUtils.isEmpty(rawReferrer)) {
                AmazonOOAdRegistration.setReferrer(context, rawReferrer);
            }
            AmazonOOAdRegistration.registerApp(context);
            setRegisteredWithSIS(true);
        }
    }

    private static synchronized boolean hasRegisteredWithSIS() {
        return sHasRegisteredWithSIS;
    }

    private static synchronized void setRegisteredWithSIS(final boolean hasRegistered) {
        sHasRegisteredWithSIS = hasRegistered;
    }
}
