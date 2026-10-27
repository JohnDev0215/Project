package com.amazon.mp3.prime;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.subscription.BrowseMode;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class Branding {
    private static final Map<Marketplace, BrandingInfo> SPECIAL_BRANDING_MARKETS;
    private static final String TAG = "Branding";

    private static final class BrandingInfo {
        private final int mBrandingSashId;
        private final String mBrandingString;

        public BrandingInfo(final String brandingString, final int brandingSashId) {
            this.mBrandingString = brandingString;
            this.mBrandingSashId = brandingSashId;
        }

        public String getBrandingString() {
            return this.mBrandingString;
        }

        public int getBrandingSashId() {
            return this.mBrandingSashId;
        }
    }

    static {
        HashMap map = new HashMap();
        SPECIAL_BRANDING_MARKETS = map;
        map.put(Marketplace.JAPAN, new BrandingInfo("プライム", R.drawable.prime_sash_jp));
    }

    public static String getPrimeBranding(final Marketplace market) {
        BrandingInfo brandingInfo = SPECIAL_BRANDING_MARKETS.get(market);
        return brandingInfo == null ? "Prime" : brandingInfo.getBrandingString();
    }

    public static boolean shouldShowPrimeBranding() {
        try {
            if (new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals("Prime")) {
                if (FeatureFlagsProvider.getFeatureFlagProvider().primeBadgingRequired()) {
                    return true;
                }
            }
            return false;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider is not available", e);
            return ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.prime_branding);
        }
    }

    public static String getPrimeMusicBranding(final Marketplace market) {
        return (Marketplace.JAPAN.equals(market) ? "Prime" : getPrimeBranding(market)) + " Music";
    }

    public static String getPrimeMusicBranding(final Context context) {
        return getPrimeMusicBranding(AccountDetailUtil.get(context).getHomeMarketPlace());
    }

    public static String getPrimeBranding(final Context context) {
        return getPrimeBranding(AccountDetailUtil.get(context).getHomeMarketPlace());
    }

    public static int getPrimeSashDrawableId(Marketplace market) {
        BrandingInfo brandingInfo = SPECIAL_BRANDING_MARKETS.get(market);
        return brandingInfo == null ? R.drawable.prime_sash : brandingInfo.getBrandingSashId();
    }

    public static int getPrimeSashDrawableId(Context context) {
        if (shouldShowPrimeBranding()) {
            return getPrimeSashDrawableId(AccountDetailUtil.get(context).getHomeMarketPlace());
        }
        return -1;
    }
}
