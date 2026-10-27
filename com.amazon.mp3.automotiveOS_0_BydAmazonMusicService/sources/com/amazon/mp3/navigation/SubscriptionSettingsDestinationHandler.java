package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.navigation.webtargetconverter.SubscriptionSettingsWebTargetConverter;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.SubscriptionSettingsDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class SubscriptionSettingsDestinationHandler implements DestinationHandler<SubscriptionSettingsDestination> {
    private final ActivityStacker activityStacker;
    private final SubscriptionSettingsWebTargetConverter subscriptionSettingsWebTargetConverter;

    public SubscriptionSettingsDestinationHandler(final SubscriptionSettingsWebTargetConverter subscriptionSettingsWebTargetConverter, final ActivityStacker activityStacker) {
        this.subscriptionSettingsWebTargetConverter = subscriptionSettingsWebTargetConverter;
        this.activityStacker = activityStacker;
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final SubscriptionSettingsDestination destination) {
        Intent intentChainIntents = this.activityStacker.chainIntents(PrimeActivityFactory.getHomeIntent(context), UnlimitedUpsellActivity.getStartIntent(context, this.subscriptionSettingsWebTargetConverter.convert(destination)));
        intentChainIntents.putExtra("com.amazon.mp3.navigate.to.nextActivityScreen", true);
        intentChainIntents.setFlags(268435456);
        context.startActivity(intentChainIntents);
    }
}
