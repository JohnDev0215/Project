package com.amazon.mp3.upsellweb;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.MarketplaceCategory;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.store.purchase.PurchaseUtil;
import com.amazon.mp3.util.UriBuilderExtensions;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.StandardWebTargetEndpoints;
import com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellSplashPageWebTargetBuilder extends AbstractTierDependentWebTargetBuilder {
    private final UriBuilderExtensions uriBuilderExtensions;

    UpsellSplashPageWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new StandardWebTargetEndpoints(), new DeviceAttributes(context), new UriBuilderExtensions());
    }

    UpsellSplashPageWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes, final UriBuilderExtensions uriBuilderExtensions) {
        super(accountInfo, endpoints, deviceAttributes);
        this.uriBuilderExtensions = uriBuilderExtensions;
        setUriPathForMarketplaceCategory();
    }

    private void setUriPathForMarketplaceCategory() {
        if (this.accountInfo.getMarketplaceCategory() == MarketplaceCategory.ROW_PRIME_ONLY) {
            this.uriBuilder.path("/music/prime/splash");
            addRedirectUrls();
        } else if (this.accountInfo.getMarketplaceCategory() == MarketplaceCategory.ROW) {
            this.uriBuilder.path("/unlimited/splash");
        } else {
            this.uriBuilder.path("/gp/dmusic/device/mp3/primeftu/introduction");
            addLegacyQueryParameters();
        }
    }

    private void addRedirectUrls() {
        Uri.Builder builderBuildUpon = Uri.parse(this.endpoints.getApexRowMusicWebEndpoint()).buildUpon();
        String string = builderBuildUpon.path("/gp/dmusic/device/mp3/primeftu/thankyou").appendQueryParameter("pageflowMetadata", getRedirectSuccessParam()).build().toString();
        String string2 = builderBuildUpon.path("/gp/dmusic/cloudplayer/web").build().toString();
        this.uriBuilder.appendQueryParameter("redirectURL", encodeUrl(string));
        this.uriBuilder.appendQueryParameter("cancelRedirectURL", encodeUrl(string2));
    }

    private String getRedirectSuccessParam() {
        String musicTerritory = this.accountInfo.getMusicTerritory();
        if (MusicTerritory.INDIA.equalsIgnoreCase(musicTerritory) || MusicTerritory.CANADA.equalsIgnoreCase(musicTerritory)) {
            return UpsellResultType.CONTINUE.toString();
        }
        return UpsellResultType.SUCCESS.toString();
    }

    @Deprecated
    private void addLegacyQueryParameters() {
        this.uriBuilder.appendQueryParameter("HF", "1");
        this.uriBuilder.appendQueryParameter("isPrime", this.accountInfo.getUserSubscription().isPrime() ? "1" : "0");
        this.uriBuilder.appendQueryParameter("deviceTypeID", this.deviceAttributes.getDeviceType());
        this.uriBuilder.appendQueryParameter("isUpgrade", this.accountInfo.isUpgradeCustomer() ? "1" : "0");
        this.uriBuilder.appendQueryParameter("hasStations", this.accountInfo.getUserSubscription().isInSubscriptionMarketplace() ? "1" : "0");
        this.uriBuilder.appendQueryParameter("subTag", getThirdPartyReferralTag());
    }

    private String getThirdPartyReferralTag() {
        String referrerPackageName = PurchaseUtil.getReferrerPackageName();
        if (TextUtils.isEmpty(referrerPackageName)) {
            return "";
        }
        try {
            return URLEncoder.encode(referrerPackageName, ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder
    public UpsellSplashPageWebTargetBuilder applyTier(final SubscriptionTier subscriptionTier) {
        MarketplaceCategory marketplaceCategory = this.accountInfo.getMarketplaceCategory();
        if (subscriptionTier == SubscriptionTier.PRIME) {
            applyPrimeTier(marketplaceCategory);
        } else if (subscriptionTier == SubscriptionTier.UNLIMITED_FAMILY) {
            applyFamilyTier(marketplaceCategory);
        }
        return this;
    }

    private void applyPrimeTier(final MarketplaceCategory marketplaceCategory) {
        String musicTerritory = this.accountInfo.getMusicTerritory();
        if (marketplaceCategory == MarketplaceCategory.CLASSIC) {
            this.uriBuilder = this.uriBuilderExtensions.removeQueryParameter(this.uriBuilder, "HF");
        } else if (AccountDetailUtil.isROWMarketplaceWithMusicTerritorySpecificMusicDomain(musicTerritory) && marketplaceCategory == MarketplaceCategory.ROW) {
            this.uriBuilder.path("/prime/splash");
        }
    }

    private void applyFamilyTier(final MarketplaceCategory marketplaceCategory) {
        if (marketplaceCategory == MarketplaceCategory.ROW_PRIME_ONLY) {
            return;
        }
        if (marketplaceCategory == MarketplaceCategory.ROW) {
            this.uriBuilder.path("/unlimited/family/splash");
        } else {
            this.uriBuilder.appendQueryParameter("family", "1");
        }
    }

    private String encodeUrl(final String url) {
        return Base64.encodeToString(url.getBytes(), 0).substring(0, r3.length() - 1);
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
