package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.ArtistDestination;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistDestinationHandler implements DestinationHandler<ArtistDestination> {
    private static final String TAG = "ArtistDestinationHandler";

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final ArtistDestination artistDestination) {
        final String collectionAsin = artistDestination.getCollectionAsin();
        Runnable runnable = new Runnable() { // from class: com.amazon.mp3.navigation.ArtistDestinationHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$navigateTo$0(context, artistDestination, collectionAsin);
            }
        };
        if (LibraryArtistDetailFragment.shouldDisplayBauhausLibraryPage(context, "")) {
            context.startActivity(getIntentForLibraryArtistDetails(context, artistDestination.getId(), collectionAsin));
            return;
        }
        if (DMSFNavigationProvider.openArtistDetailPage(collectionAsin, artistDestination.getId() == 0 ? null : Long.valueOf(artistDestination.getId()), artistDestination.getStoryId(), runnable)) {
            return;
        }
        context.startActivity(getIntentForCatalogArtistDetails(context, artistDestination, collectionAsin));
    }

    private Intent getIntentForLibraryArtistDetails(final Context context, final long id, final String asin) {
        return LibraryActivityFactory.getLibraryArtistDetailsIntent(context, id, asin);
    }

    private Intent getIntentForCatalogArtistDetails(final Context context, final ArtistDestination artistDestination, final String artistAsin) {
        Intent brushDetailIntent = PrimeActivityFactory.getBrushDetailIntent(context);
        brushDetailIntent.setFlags(268435456);
        new BrushFragmentNavigation.Builder("artist").withTitle("").withAsin(artistAsin).withAction(artistDestination.getAction()).withRefMarker(artistDestination.getRef()).withAssociateTag(artistDestination.getTag()).updateIntent(brushDetailIntent);
        return brushDetailIntent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: afterArtistDetailPageNavigation, reason: merged with bridge method [inline-methods] */
    public void lambda$navigateTo$0(final Context context, final ArtistDestination artistDestination, final String artistAsin) {
        Action action = artistDestination.getAction();
        String experienceId = artistDestination.getExperienceId();
        if (action == Action.PLAY_SPOTLIGHT) {
            handleSpotlightPlayback(context, artistAsin);
        } else if (experienceId != null) {
            handleMusicExperiencePlayback(context, artistAsin, experienceId);
        }
    }

    private void handleMusicExperiencePlayback(final Context context, final String artistAsin, final String experienceId) {
        Log.info(TAG, "Initiating Music Experience playback from Deeplink for artist asin %s experience id %s", artistAsin, experienceId);
        Intent intent = new Intent(context, (Class<?>) NowPlayingFragmentActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("com.amazon.mp3.fragment.extra", NowPlayingFragment.class.getSimpleName());
        intent.putExtra("artistAsin", artistAsin);
        intent.putExtra("experienceId", experienceId);
        intent.putExtra(PlaybackFragment.class.getSimpleName(), true);
        context.startActivity(intent);
    }

    private void handleSpotlightPlayback(final Context context, final String artistAsin) {
        Log.info(TAG, "Initiating Spotlight playback from Deeplink for artist asin %s", artistAsin);
        Intent intent = new Intent(context, (Class<?>) NowPlayingFragmentActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("com.amazon.mp3.fragment.extra", NowPlayingFragment.class.getSimpleName());
        intent.putExtra("artistAsin", artistAsin);
        intent.putExtra(PlaybackFragment.class.getSimpleName(), true);
        context.startActivity(intent);
    }
}
