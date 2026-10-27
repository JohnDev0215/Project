package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.navigation.webtargetconverter.DigitalSubscriptionsWebTargetConverter;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.DigitalSubscriptionsDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class DigitalSubscriptionsDestinationHandler implements DestinationHandler<DigitalSubscriptionsDestination> {
    private final ActivityStacker activityStacker;
    private final DigitalSubscriptionsWebTargetConverter webTargetConverter;

    public DigitalSubscriptionsDestinationHandler(final DigitalSubscriptionsWebTargetConverter webTargetConverter, final ActivityStacker activityStacker) {
        this.webTargetConverter = webTargetConverter;
        this.activityStacker = activityStacker;
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final DigitalSubscriptionsDestination destination) {
        Intent startIntent = UnlimitedUpsellActivity.getStartIntent(context, this.webTargetConverter.convert(context, destination));
        Intent intentChainIntents = this.activityStacker.chainIntents(PrimeActivityFactory.getHomeIntent(context), startIntent);
        intentChainIntents.putExtra("com.amazon.mp3.navigate.to.nextActivityScreen", true);
        intentChainIntents.setFlags(268435456);
        context.startActivity(intentChainIntents);
    }
}
