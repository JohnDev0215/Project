package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.HomeDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class HomeDestinationHandler implements DestinationHandler<HomeDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final HomeDestination homeDestination) {
        context.startActivity(getIntent(context, homeDestination));
    }

    public Intent getIntent(final Context context, final HomeDestination homeDestination) {
        Intent homeIntent = PrimeActivityFactory.getHomeIntent(context);
        homeIntent.setFlags(268435456);
        new BrushFragmentNavigation.Builder("home").withAction(homeDestination.getAction()).withRefMarker(homeDestination.getRef()).withAssociateTag(homeDestination.getTag()).updateIntent(homeIntent);
        return homeIntent;
    }
}
