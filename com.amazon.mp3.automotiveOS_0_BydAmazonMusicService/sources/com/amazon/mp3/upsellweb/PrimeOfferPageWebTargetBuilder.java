package com.amazon.mp3.upsellweb;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.MarketplaceCategory;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.RowSpecialCaseWebTargetEndpoints;
import com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeOfferPageWebTargetBuilder extends BaseWebTargetBuilder {
    private String campaignId;
    private String subpage;

    PrimeOfferPageWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new RowSpecialCaseWebTargetEndpoints(), new DeviceAttributes(context));
    }

    PrimeOfferPageWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
        addPath(accountInfo);
        addRedirectParameters(accountInfo.getMarketplaceCategory(), accountInfo.getMusicTerritory(), endpoints.getApexRowMusicWebEndpoint());
    }

    private void addPath(final AccountInfo accountInfo) {
        if (accountInfo.getMarketplaceCategory() == MarketplaceCategory.ROW_PRIME_ONLY || AccountDetailUtil.isROWMarketplaceWithMusicTerritorySpecificMusicDomain(accountInfo.getMusicTerritory())) {
            this.uriBuilder.path("/prime/signup");
        } else {
            this.uriBuilder.path("/gp/dmusic/redirect/primeupsell");
        }
    }

    private void addRedirectParameters(final MarketplaceCategory marketplaceCategory, final String musicTerritory, final String rowMusicEndpoint) {
        this.uriBuilder.appendQueryParameter("redirectURL", encode(getSuccessRedirect(marketplaceCategory, musicTerritory, rowMusicEndpoint)));
        this.uriBuilder.appendQueryParameter("cancelRedirectURL", encode(getThankYouRedirect(marketplaceCategory, rowMusicEndpoint)));
        this.uriBuilder.appendQueryParameter("cancelRedirectQueryParams", encode("cancelledSignUp=1"));
    }

    private String encode(final String inputString) {
        return Base64.encodeToString(inputString.getBytes(), 0).substring(0, r3.length() - 1);
    }

    private String getSuccessRedirect(final MarketplaceCategory marketplaceCategory, final String musicTerritory, final String rowMusicEndpoint) {
        if (marketplaceCategory == MarketplaceCategory.ROW_PRIME_ONLY) {
            Uri.Builder builderBuildUpon = Uri.parse(rowMusicEndpoint).buildUpon();
            addSuccessRedirectPathForMusicTerritory(musicTerritory, builderBuildUpon);
            return builderBuildUpon.build().toString();
        }
        return new Uri.Builder().path("/gp/dmusic/device/mp3/primeftu/thankyou").appendQueryParameter("pageflowMetadata", "subscribeResult=SUCCESS").build().toString();
    }

    private Uri.Builder addSuccessRedirectPathForMusicTerritory(final String musicTerritory, final Uri.Builder uriBuilder) {
        if (MusicTerritory.INDIA.value.equals(musicTerritory)) {
            return uriBuilder.path("/home");
        }
        return uriBuilder.path("/gp/dmusic/device/mp3/primeftu/thankyou").appendQueryParameter("pageflowMetadata", "subscribeResult=SUCCESS");
    }

    private String getThankYouRedirect(final MarketplaceCategory marketplaceCategory, final String rowMusicEndpoint) {
        if (marketplaceCategory == MarketplaceCategory.ROW_PRIME_ONLY) {
            return Uri.parse(rowMusicEndpoint).buildUpon().path("/gp/dmusic/cloudplayer/web").build().toString();
        }
        return new Uri.Builder().path("/gp/dmusic/device/mp3/primeftu/thankyou").build().toString();
    }

    public PrimeOfferPageWebTargetBuilder withReportingParameters(final String campaignId, final String subpage) {
        this.campaignId = campaignId;
        this.subpage = subpage;
        return this;
    }

    private String getCampaignId() {
        if (TextUtils.isEmpty(this.campaignId)) {
            this.campaignId = "dmusic_library";
        }
        return this.campaignId;
    }

    private String getRefMarker() {
        if (TextUtils.isEmpty(this.subpage)) {
            this.subpage = "xx";
        }
        return String.format(Locale.US, "dm_and_prbrw_%1$s_xx_rpsu_%2$s_xx_xx", this.subpage, this.deviceAttributes.getDeviceType());
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        this.uriBuilder.appendQueryParameter("ref", getRefMarker());
        this.uriBuilder.appendQueryParameter("primeCampaignId", getCampaignId());
        return super.build();
    }
}
