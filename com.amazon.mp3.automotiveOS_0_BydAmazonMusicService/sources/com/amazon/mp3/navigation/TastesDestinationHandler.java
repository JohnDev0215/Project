package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.activity.ATCWebActivity;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.TastesDestination;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class TastesDestinationHandler implements DestinationHandler<TastesDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final TastesDestination tastesDestination) {
        context.startActivity(getIntent(context, tastesDestination));
    }

    public Intent getIntent(final Context context, final TastesDestination tastesDestination) {
        if (((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.artist_taste_collection)) {
            Intent intentStart = ATCWebActivity.start(context);
            intentStart.putExtra("com.amazon.mp3.activity.EXTRA_CUSTOMER_FLOW", ATCWebActivity.CustomerFlow.tastesDeeplink);
            intentStart.putExtra("com.amazon.mp3.activity.EXTRA_REF_MARKER", tastesDestination.getRef());
            intentStart.setFlags(268435456);
            return intentStart;
        }
        Intent homeIntent = PrimeActivityFactory.getHomeIntent(context);
        homeIntent.setFlags(268435456);
        new BrushFragmentNavigation.Builder("home").withRefMarker(tastesDestination.getRef()).withAssociateTag(tastesDestination.getTag()).updateIntent(homeIntent);
        return homeIntent;
    }
}
