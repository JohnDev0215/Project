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
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class SettingsPageWebTargetBuilder extends AbstractTierDependentWebTargetBuilder {
    private SettingsPageWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new StandardWebTargetEndpoints(), new DeviceAttributes(context));
    }

    SettingsPageWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
    }

    static SettingsPageWebTargetBuilder withDefaultOptions(final Context context) {
        return new SettingsPageWebTargetBuilder(context).withDefaultOptions();
    }

    static SettingsPageWebTargetBuilder forPlanComparisonPage(final Context context) {
        return new SettingsPageWebTargetBuilder(context).forPlanComparisonPage();
    }

    private SettingsPageWebTargetBuilder forPlanComparisonPage() {
        return withSettingsPagePath().withPlanComparisonView().withDefaultRedirectParameters().withDefaultLanguageQueryParameters();
    }

    private SettingsPageWebTargetBuilder withDefaultOptions() {
        return withSettingsPagePath().withDefaultRedirectParameters().withDefaultLanguageQueryParameters();
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder
    public SettingsPageWebTargetBuilder applyTier(final SubscriptionTier tier) {
        if (tier == SubscriptionTier.UNLIMITED_FAMILY) {
            applyFamilyTier();
        }
        return this;
    }

    private void applyFamilyTier() {
        this.uriBuilder.appendQueryParameter("fullCatalogUpgrade", "HAWKFIRE_FAMILY_MONTHLY");
    }

    private SettingsPageWebTargetBuilder withPlanComparisonView() {
        this.uriBuilder.appendPath("planComparison");
        return this;
    }

    private SettingsPageWebTargetBuilder withDefaultRedirectParameters() {
        this.uriBuilder.appendQueryParameter("redirectURL", OfferPageRedirectPath.SUCCESS.getBase64EncodedType());
        this.uriBuilder.appendQueryParameter("notNowRedirectURL", OfferPageRedirectPath.NOT_NOW.getBase64EncodedType());
        return this;
    }

    private SettingsPageWebTargetBuilder withDefaultLanguageQueryParameters() {
        this.uriBuilder.appendQueryParameter("language", Locale.getDefault().toString());
        return this;
    }

    private SettingsPageWebTargetBuilder withSettingsPagePath() {
        MarketplaceCategory marketplaceCategory = this.accountInfo.getMarketplaceCategory();
        if (marketplaceCategory == MarketplaceCategory.ROW_PRIME_ONLY) {
            this.uriBuilder.path("music/settings");
        } else if (marketplaceCategory == MarketplaceCategory.ROW) {
            this.uriBuilder.path("settings");
        } else {
            this.uriBuilder.path("gp/dmusic/player/settings");
        }
        return this;
    }

    public SettingsPageWebTargetBuilder withEditPaymentView() {
        this.uriBuilder.appendPath("editpayment");
        this.uriBuilder.appendQueryParameter("ref_", "dm_fcsub_update_your_pymt_method");
        this.uriBuilder.appendQueryParameter("subsType", "fullCatalog");
        return this;
    }

    public SettingsPageWebTargetBuilder withConfirmRenewalView() {
        this.uriBuilder.appendQueryParameter("fullCatalogContinue", "1");
        return this;
    }

    public SettingsPageWebTargetBuilder withAutoRenewalView() {
        this.uriBuilder.appendQueryParameter("autoRenewalOn", "1");
        return this;
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
