package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.LiveStreamsDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class LiveStreamsDestinationHandler implements DestinationHandler<LiveStreamsDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final LiveStreamsDestination liveStreamsDestination) {
        context.startActivity(getIntent(context, liveStreamsDestination));
    }

    public Intent getIntent(final Context context, final LiveStreamsDestination liveStreamsDestination) {
        Intent homeIntent = PrimeActivityFactory.getHomeIntent(context);
        homeIntent.setFlags(268435456);
        new BrushFragmentNavigation.Builder("live-streams").withRefMarker(liveStreamsDestination.getRef()).withAssociateTag(liveStreamsDestination.getTag()).updateIntent(homeIntent);
        return homeIntent;
    }
}
