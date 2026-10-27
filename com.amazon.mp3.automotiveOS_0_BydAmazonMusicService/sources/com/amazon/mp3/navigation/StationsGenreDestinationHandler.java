package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.library.fragment.StationsFragment;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.StationsGenreDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class StationsGenreDestinationHandler implements DestinationHandler<StationsGenreDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final StationsGenreDestination stationsDestination) {
        Intent stationsIntent = PrimeActivityFactory.getStationsIntent(context);
        stationsIntent.setFlags(268435456);
        stationsIntent.putExtra(StationsFragment.ARGUMENT_REFINEMENT_SELECTION_KEY, stationsDestination.getGenre());
        stationsIntent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), stationsDestination.getRef());
        stationsIntent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), stationsDestination.getTag());
        context.startActivity(stationsIntent);
    }
}
