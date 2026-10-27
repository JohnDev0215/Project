package com.amazon.device.ads;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes2.dex */
class AmazonOOMAPAdapter implements AuthenticationPlatformAdapter {
    private static final String LOGTAG = "AmazonOOMAPAdapter";
    private Context context;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    AmazonOOMAPAdapter() {
    }

    @Override // com.amazon.device.ads.AuthenticationPlatformAdapter
    public AmazonOOMAPAdapter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override // com.amazon.device.ads.AuthenticationPlatformAdapter
    public String getAccountName() {
        String account;
        try {
            account = new MAPAccountManager(this.context).getAccount();
        } catch (Exception e) {
            this.logger.d("Exception caught when trying to retrieve the user's account: %s", e.getMessage());
            account = null;
        }
        if (account == null) {
            this.logger.e("No Amazon account found");
        }
        return account;
    }

    @Override // com.amazon.device.ads.AuthenticationPlatformAdapter
    public WebRequest makeAuthenticatedWebRequest(String str) {
        return new AmazonOOAuthenticatedWebRequest(new AuthenticationMethodFactory(MobileAdsInfoStore.getInstance().getApplicationContext(), str).newAuthenticationMethod(AuthenticationType.OAuth));
    }

    @Override // com.amazon.device.ads.AuthenticationPlatformAdapter
    public String getPreferredMarketplace() {
        String accountName = getAccountName();
        if (accountName == null) {
            return null;
        }
        try {
            Bundle bundle = CustomerAttributeStore.getInstance(MobileAdsInfoStore.getInstance().getApplicationContext()).getAttribute(accountName, CustomerAttributeKeys.KEY_PFM, null, EnumSet.noneOf(CustomerAttributeStore.GetAttributeOptions.class)).get();
            if (bundle != null) {
                return CustomerAttributeStore.getValueOrAttributeDefault(bundle);
            }
            return null;
        } catch (Exception e) {
            this.logger.d("Exception caught when trying to retrieve the user's preferred marketplace. Most issues can be remedied by upgrading to MAP R5. Exception message: %s", e.getMessage());
            return null;
        }
    }
}
