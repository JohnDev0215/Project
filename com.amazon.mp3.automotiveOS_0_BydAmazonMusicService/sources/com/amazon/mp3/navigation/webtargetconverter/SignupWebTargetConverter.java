package com.amazon.mp3.navigation.webtargetconverter;

import com.amazon.mp3.navigation.DeeplinkTierToSubscriptionTierConverter;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilder;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.PrimeOfferPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilderFactory;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.AbstractTierDependentWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder;
import com.amazon.music.destination.DeeplinkTier;
import com.amazon.music.destination.SignupDestination;

/* JADX INFO: loaded from: classes3.dex */
public class SignupWebTargetConverter {
    private static final String TAG = "SignupWebTargetConverter";
    private DeeplinkTierToSubscriptionTierConverter deeplinkTierToSubscriptionTierConverter;
    private OfferPageWebTargetBuilderFactory offerPageWebTargetBuilderFactory;
    private PrimeOfferPageWebTargetBuilderFactory primeOfferPageWebTargetBuilderFactory;
    private SettingsPageWebTargetBuilderFactory settingsPageWebTargetBuilderFactory;

    public SignupWebTargetConverter(final SettingsPageWebTargetBuilderFactory settingsPageWebTargetBuilderFactory, final OfferPageWebTargetBuilderFactory offerPageWebTargetBuilderFactory, final PrimeOfferPageWebTargetBuilderFactory primeOfferPageWebTargetBuilderFactory, final DeeplinkTierToSubscriptionTierConverter deeplinkTierToSubscriptionTierConverter) {
        this.settingsPageWebTargetBuilderFactory = settingsPageWebTargetBuilderFactory;
        this.offerPageWebTargetBuilderFactory = offerPageWebTargetBuilderFactory;
        this.primeOfferPageWebTargetBuilderFactory = primeOfferPageWebTargetBuilderFactory;
        this.deeplinkTierToSubscriptionTierConverter = deeplinkTierToSubscriptionTierConverter;
    }

    public WebTarget convert(final SignupDestination destination) {
        WebTargetBuilder webTargetBuilderConvertSignupDeeplink;
        DeeplinkTier tier = destination.getTier();
        if (tier == DeeplinkTier.PRIME) {
            webTargetBuilderConvertSignupDeeplink = convertPrimeDeeplink(destination.isUserConfirmationRequired());
        } else {
            webTargetBuilderConvertSignupDeeplink = convertSignupDeeplink(tier);
        }
        webTargetBuilderConvertSignupDeeplink.withQueryParamsToForward(destination.getQueryParameters());
        return webTargetBuilderConvertSignupDeeplink.build();
    }

    private WebTargetBuilder convertPrimeDeeplink(boolean isUserConfirmationRequired) {
        if (isUserConfirmationRequired) {
            return this.primeOfferPageWebTargetBuilderFactory.newBuilder().withReportingParameters("dmusic_browse", "upbox");
        }
        return this.primeOfferPageWebTargetBuilderFactory.newBuilder().withReportingParameters("dmusic_browse", "");
    }

    private WebTargetBuilder convertSignupDeeplink(final DeeplinkTier deeplinkTier) {
        OfferPageWebTargetBuilder offerPageWebTargetBuilderWithListenRedirectDestination = this.offerPageWebTargetBuilderFactory.newBuilder().withListenRedirectDestination();
        applyTier(offerPageWebTargetBuilderWithListenRedirectDestination, deeplinkTier);
        return offerPageWebTargetBuilderWithListenRedirectDestination;
    }

    private void applyTier(final AbstractTierDependentWebTargetBuilder webTargetBuilder, final DeeplinkTier deeplinkTier) {
        try {
            webTargetBuilder.applyTier(this.deeplinkTierToSubscriptionTierConverter.convert(deeplinkTier));
        } catch (Exception e) {
            Log.error(TAG, "Signup Deeplink Destination contained an unrecognized tier. Unable to apply tier to the web target. %s", e.getMessage());
        }
    }
}
