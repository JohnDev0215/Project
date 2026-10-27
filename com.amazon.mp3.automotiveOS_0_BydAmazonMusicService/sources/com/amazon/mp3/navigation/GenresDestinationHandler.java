package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.GenresDestination;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.router.DestinationHandler;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class GenresDestinationHandler implements DestinationHandler<GenresDestination> {
    private static final String TAG = "GenresDestinationHandler";

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final GenresDestination genresDestination) {
        context.startActivity(getIntentForGenresDetails(context, genresDestination, genresDestination.getCollectionAsin()));
    }

    private boolean shouldRedirectToPlaylistLandingPage(final String genresAsin) {
        return isGenreIdMatch(genresAsin) && isRerouteToPlaylistLandingEnabled();
    }

    private Intent getIntentForGenresDetails(final Context context, final GenresDestination genresDestination, final String genresAsin) {
        Intent homeIntent = PrimeActivityFactory.getHomeIntent(context);
        homeIntent.setFlags(268435456);
        boolean zShouldRedirectToPlaylistLandingPage = shouldRedirectToPlaylistLandingPage(genresAsin);
        BrushFragmentNavigation.Builder builderWithTitle = new BrushFragmentNavigation.Builder(zShouldRedirectToPlaylistLandingPage ? "playlists" : ParserUtil.GENRE_SEGMENT_NAME).withTitle("");
        if (zShouldRedirectToPlaylistLandingPage) {
            genresAsin = "";
        }
        builderWithTitle.withGenreId(genresAsin).withAction(genresDestination.getAction()).withRefMarker(genresDestination.getRef()).withAssociateTag(genresDestination.getTag()).updateIntent(homeIntent);
        return homeIntent;
    }

    private boolean isRerouteToPlaylistLandingEnabled() {
        return FMPMFeatureGating.REROUTE_TO_PLAYLIST_LANDING.isEnabled();
    }

    private boolean isGenreIdMatch(final String genresAsin) {
        Configuration configuration = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        if (configuration != null) {
            try {
                if (StringUtils.equals(configuration.string("on_demand_genre_id"), genresAsin)) {
                    return true;
                }
                if (StringUtils.equals(configuration.string("on_demand_genre_id_alias"), genresAsin)) {
                    return true;
                }
            } catch (Exception e) {
                Log.error(TAG, String.format("Exception while retrieving value from Arcus %s", e.getMessage()));
                return false;
            }
        }
        return false;
    }
}
