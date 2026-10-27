package com.amazon.mp3.account;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.prime.upsell.PrimeUpsellSettingsUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.subscription.UserSubscription;

/* JADX INFO: loaded from: classes3.dex */
public class AccountInfo {
    private final Context context;

    public AccountInfo(final Context context) {
        this.context = context;
    }

    public MarketplaceCategory getMarketplaceCategory() {
        if (AccountDetailUtil.get(this.context).isRowPrimeOnlyMarketplace()) {
            return MarketplaceCategory.ROW_PRIME_ONLY;
        }
        if (AccountDetailUtil.get(this.context).isRowMarketplace()) {
            return MarketplaceCategory.ROW;
        }
        return MarketplaceCategory.CLASSIC;
    }

    public UserSubscription getUserSubscription() {
        return UserSubscriptionUtil.getUserSubscription();
    }

    public String getMusicTerritory() {
        return AccountDetailUtil.getMusicTerritoryOfResidence();
    }

    @Deprecated
    public boolean isUpgradeCustomer() {
        return PrimeUpsellSettingsUtil.isUpgradeCustomer(this.context);
    }
}
