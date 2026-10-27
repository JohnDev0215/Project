package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.upsellweb.ManageFamilyPageWebTargetBuilder;
import com.amazon.music.destination.FamilyInviteDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class FamilyInviteDestinationHandler implements DestinationHandler<FamilyInviteDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final FamilyInviteDestination familyInviteDestination) {
        Intent startIntent = UnlimitedUpsellActivity.getStartIntent(context, new ManageFamilyPageWebTargetBuilder(context).withQueryParamsToForward(familyInviteDestination.getQueryParameters()).build());
        Intent intentChainIntents = new ActivityStacker().chainIntents(PrimeActivityFactory.getHomeIntent(context), startIntent);
        startIntent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), familyInviteDestination.getRef());
        intentChainIntents.putExtra("com.amazon.mp3.navigate.to.nextActivityScreen", true);
        intentChainIntents.setFlags(268435456);
        context.startActivity(intentChainIntents);
    }
}
