package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.NewReleasesDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class NewReleasesDestinationHandler implements DestinationHandler<NewReleasesDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final NewReleasesDestination newReleasesDestination) {
        context.startActivity(getIntentForNewReleasesPage(context, newReleasesDestination, newReleasesDestination.getGenre(), newReleasesDestination.getWidgetId()));
    }

    private boolean isBrowseId(final String refinementGenre) {
        return refinementGenre.split("-").length == 5;
    }

    private Intent getIntentForNewReleasesPage(final Context context, final NewReleasesDestination newReleasesDestination, String refinementGenre, String widgetId) {
        if (refinementGenre != null && isBrowseId(refinementGenre)) {
            refinementGenre = null;
        }
        Intent homeIntent = PrimeActivityFactory.getHomeIntent(context);
        homeIntent.setFlags(268435456);
        new BrushFragmentNavigation.Builder("new").withGenreId(refinementGenre).withWidgetId(widgetId).updateIntent(homeIntent);
        return homeIntent;
    }
}
