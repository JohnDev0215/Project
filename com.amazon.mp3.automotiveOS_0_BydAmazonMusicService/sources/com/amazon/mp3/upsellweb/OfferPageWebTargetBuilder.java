package com.amazon.mp3.upsellweb;

import android.content.Context;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.MarketplaceCategory;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.StandardWebTargetEndpoints;
import com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints;

/* JADX INFO: loaded from: classes4.dex */
public class OfferPageWebTargetBuilder extends AbstractTierDependentWebTargetBuilder {
    OfferPageWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new StandardWebTargetEndpoints(), new DeviceAttributes(context));
    }

    OfferPageWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
        addPath();
        addRedirectParameters();
    }

    private void addPath() {
        if (this.accountInfo.getMarketplaceCategory() == MarketplaceCategory.ROW) {
            this.uriBuilder.path("/unlimited/signup");
        } else {
            this.uriBuilder.path("/gp/dmusic/full-catalog/signup");
        }
    }

    private void addRedirectParameters() {
        this.uriBuilder.appendQueryParameter("redirectURL", OfferPageRedirectPath.SUCCESS.getBase64EncodedType());
        this.uriBuilder.appendQueryParameter("notNowRedirectURL", OfferPageRedirectPath.NOT_NOW.getBase64EncodedType());
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder
    public OfferPageWebTargetBuilder applyTier(final SubscriptionTier tier) {
        if (tier == SubscriptionTier.UNLIMITED_FAMILY) {
            applyFamilyTier();
        }
        return this;
    }

    private void applyFamilyTier() {
        if (this.accountInfo.getMarketplaceCategory() == MarketplaceCategory.ROW) {
            this.uriBuilder.path("/unlimited/family/signup");
        } else {
            this.uriBuilder.appendQueryParameter("subscriptionOverride", "HAWKFIRE_FAMILY_MONTHLY");
        }
    }

    public OfferPageWebTargetBuilder withListenRedirectDestination() {
        this.uriBuilder.appendQueryParameter("listenRedirectURL", OfferPageRedirectPath.SUCCESS_LISTEN.getBase64EncodedType());
        return this;
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
