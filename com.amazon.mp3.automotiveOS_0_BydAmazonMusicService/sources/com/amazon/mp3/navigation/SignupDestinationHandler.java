package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.navigation.dialogtargetconverter.SignupDestinationDialogTargetConverter;
import com.amazon.mp3.navigation.webtargetconverter.SignupWebTargetConverter;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.prime.upsell.PrimeUpsellActivity;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.destination.DeeplinkTier;
import com.amazon.music.destination.SignupDestination;
import com.amazon.music.inappmessaging.external.model.DynamicMessageContextualEventBuilder;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class SignupDestinationHandler implements DestinationHandler<SignupDestination> {
    private final ActivityStacker activityStacker;
    private final SignupDestinationDialogTargetConverter signupDestinationDialogTargetConverter;
    private final SignupWebTargetConverter signupWebTargetConverter;

    public SignupDestinationHandler(final SignupWebTargetConverter signupWebTargetConverter, final SignupDestinationDialogTargetConverter signupDestinationDialogTargetConverter, final ActivityStacker activityStacker) {
        this.signupWebTargetConverter = signupWebTargetConverter;
        this.signupDestinationDialogTargetConverter = signupDestinationDialogTargetConverter;
        this.activityStacker = activityStacker;
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final SignupDestination signupDestination) {
        if (signupDestination.isUserConfirmationRequired() && DynamicMessagingManager.isDynamicMessagingSupported() && signupDestination.getTier() != DeeplinkTier.PRIME) {
            UpsellReason upsellReason = UpsellReason.DEEPLINK_SIGNUP;
            UpsellInformation upsellInformation = UpsellUtil.getUpsellInformation("DEFAULT_ASIN", upsellReason, UpsellSourceEntity.UNKNOWN_ENTITY);
            UpsellUtil.emitEligibilityCheckRequestedMetric(upsellInformation);
            UpsellManager.getInstance().showDynamicMessageOneClick(AndroidUtils.getCurrentActivity(), DynamicMessageContextualEventBuilder.builder(Trigger.INELIGIBLE_CONTENT_REQUEST).withArtistName(upsellInformation.getArtistName()).withItemName(upsellInformation.getSeedName()).build(), new DynamicMessageMetricsAttributes(null, upsellReason.getValue()));
            return;
        }
        Intent deeplinkIntent = getDeeplinkIntent(signupDestination, context);
        boolean z = DeeplinkHandlerActivity.isAppOpenedFromDeeplink() && FTUSettingsUtil.shouldSuppressHomeForFTUDeeplink();
        Intent intentChainIntents = this.activityStacker.chainIntents(PrimeActivityFactory.getHomeIntent(context), deeplinkIntent);
        intentChainIntents.putExtra("com.amazon.mp3.navigate.to.nextActivityScreen", true);
        if (z) {
            intentChainIntents.putExtra("com.amazon.mp3.navigate.to.suppressHomeActivity", true);
        }
        intentChainIntents.setFlags(268435456);
        context.startActivity(intentChainIntents);
    }

    private Intent getDeeplinkIntent(final SignupDestination signupDestination, final Context context) {
        WebTarget webTargetConvert = this.signupWebTargetConverter.convert(signupDestination);
        if (signupDestination.getTier() == DeeplinkTier.PRIME) {
            return getPrimeDeeplinkIntent(signupDestination.isUserConfirmationRequired(), context, webTargetConvert);
        }
        return getUnlimitedDeeplinkIntent(signupDestination, context, webTargetConvert);
    }

    private Intent getPrimeDeeplinkIntent(final boolean isUserConfirmationRequired, final Context context, final WebTarget webTarget) {
        if (isUserConfirmationRequired) {
            return PrimeUpsellActivity.getDialogBeforeWebIntent(context, webTarget);
        }
        return PrimeUpsellActivity.getWebIntent(context, webTarget);
    }

    private Intent getUnlimitedDeeplinkIntent(final SignupDestination signupDestination, final Context context, final WebTarget webTarget) {
        if (signupDestination.isUserConfirmationRequired()) {
            return UnlimitedUpsellActivity.getStartIntent(context, this.signupDestinationDialogTargetConverter.convert(signupDestination, webTarget));
        }
        return UnlimitedUpsellActivity.getStartIntent(context, webTarget);
    }
}
