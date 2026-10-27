package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.data.FireFlyServiceUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.AlbumLookup;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.TrackLookup;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.graphql.generated.TracksIdsQuery;
import com.amazon.music.router.AsyncDestinationHandler;
import com.amazon.music.storeservice.model.AlbumItem;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumDestinationHandler implements AsyncDestinationHandler<AlbumDestination> {
    private static final String TAG = "AlbumDestinationHandler";

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final AlbumDestination destination) {
        getAlbumDestinationWithAsinSubstitution(context, destination, null);
    }

    @Override // com.amazon.music.router.AsyncDestinationHandler
    public void navigateTo(final Context context, final AlbumDestination destination, final Runnable onNavigatedCallback) {
        getAlbumDestinationWithAsinSubstitution(context, destination, onNavigatedCallback);
    }

    private void getAlbumDestinationWithAsinSubstitution(final Context context, final AlbumDestination destination, final Runnable onNavigatedCallback) {
        final String trackAsin = destination.getTrackAsin();
        new AsyncTask<Void, Void, AlbumDestination>() { // from class: com.amazon.mp3.navigation.AlbumDestinationHandler.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public AlbumDestination doInBackground(Void... voids) {
                AlbumDestination albumDestinationWithTrackSubstitution;
                return (TextUtils.isEmpty(trackAsin) || (albumDestinationWithTrackSubstitution = AlbumDestinationHandler.this.getAlbumDestinationWithTrackSubstitution(context, destination)) == null) ? AlbumDestinationHandler.this.getAlbumDestinationWithAlbumSubstitution(context, destination, trackAsin) : albumDestinationWithTrackSubstitution;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(final AlbumDestination albumDestination) {
                AlbumDestinationHandler.this.navigateToAlbumDetails(context, albumDestination, destination);
                Runnable runnable = onNavigatedCallback;
                if (runnable != null) {
                    runnable.run();
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigateToAlbumDetails(final Context context, final AlbumDestination destination, final AlbumDestination originalDestination) {
        if (destination == null || destination.getCollectionAsin() == null) {
            handleAlbumUriError(context, originalDestination);
            return;
        }
        openAlbumDetail(context, destination);
        PrimeCollectionTask.createPlaybackTask(context, destination.getCollectionAsin(), destination.getTrackAsin(), PlaybackPageType.ALBUM_DETAIL, PrimeCollectionTask.Task.NONE, new QueryAlbumByAsin(context), new PrimeCollectionTask.OnUriReadyCallback() { // from class: com.amazon.mp3.navigation.AlbumDestinationHandler.2
            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onAlbumUriReady(final Uri uri) {
            }

            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onError(final Exception e) {
                Log.error(AlbumDestinationHandler.TAG, "Failed to get album uri. Showing error dialog.", e);
                AlbumDestinationHandler.this.handleAlbumUriError(context, originalDestination);
            }
        }).execute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAlbumUriError(final Context context, final AlbumDestination destination) {
        destination.setSuccess(false);
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            startIntent(context, new Intent(context, (Class<?>) NetworkErrorDialogActivity.class));
        } else {
            startIntent(context, PrimeActivityFactory.getHomeIntent(context));
            startIntent(context, new Intent(context, (Class<?>) InvalidDestinationErrorDialogActivity.class));
        }
    }

    private void startIntent(final Context context, final Intent intent) {
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlbumDestination getAlbumDestinationWithTrackSubstitution(final Context context, final AlbumDestination albumDestination) {
        if (Feature.is_lookup_deprecated.isEnabled()) {
            return getAlbumDestinationFromFirefly(albumDestination);
        }
        return getAlbumDestinationFromLookup(context, albumDestination);
    }

    private AlbumDestination getAlbumDestinationFromLookup(final Context context, final AlbumDestination albumDestination) {
        try {
            TrackLookup.Response responseLookupTrack = BrowseFactory.lookupTrack(context, albumDestination.getTrackAsin());
            if (responseLookupTrack == null || responseLookupTrack.tracks.isEmpty() || responseLookupTrack.tracks.get(0).getAlbum() == null) {
                return null;
            }
            TrackItem trackItem = responseLookupTrack.tracks.get(0);
            return updateAlbumDestinationWithAsinSubstitution(albumDestination, trackItem.getAlbum().getAsin(), trackItem.getAsin());
        } catch (BrowseException e) {
            Log.error(TAG, "Attempt to lookup track asin failed: " + albumDestination.getTrackAsin(), e);
            return null;
        }
    }

    private AlbumDestination getAlbumDestinationFromFirefly(final AlbumDestination albumDestination) {
        TracksIdsQuery.Track track;
        try {
            List<TracksIdsQuery.Track> list = FireFlyServiceUtil.INSTANCE.getTrackAndAlbumAsinsAsync(Collections.singletonList(albumDestination.getTrackAsin())).get();
            if (list == null || (track = list.get(0)) == null || track.getAlbum() == null) {
                return null;
            }
            return updateAlbumDestinationWithAsinSubstitution(albumDestination, track.getAlbum().getId(), track.getId());
        } catch (InterruptedException | ExecutionException e) {
            Log.error(TAG, "Attempt to look up track asin on Firefly failed: " + albumDestination.getTrackAsin(), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlbumDestination getAlbumDestinationWithAlbumSubstitution(final Context context, final AlbumDestination albumDestination, final String trackAsin) {
        AlbumItem albumItem;
        try {
            AlbumLookup.Response responseLookupAlbum = BrowseFactory.lookupAlbum(context, albumDestination.getCollectionAsin());
            if (responseLookupAlbum == null || (albumItem = responseLookupAlbum.album) == null) {
                return null;
            }
            return updateAlbumDestinationWithAsinSubstitution(albumDestination, albumItem.getAsin(), trackAsin);
        } catch (BrowseException e) {
            Log.error(TAG, "Attempt to lookup album asin failed: " + albumDestination.getCollectionAsin(), e);
            return null;
        }
    }

    private AlbumDestination updateAlbumDestinationWithAsinSubstitution(final AlbumDestination albumDestination, final String albumAsin, final String trackAsin) {
        return new AlbumDestination(albumAsin, trackAsin, albumDestination.getAction(), albumDestination.getRef(), albumDestination.getTag());
    }

    private void openAlbumDetail(final Context context, AlbumDestination destination) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.Albums.getContentUri("cirrus", 0L));
        if (intentForContentUri != null) {
            if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
                intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", destination.getCollectionAsin());
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", destination.getAction());
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN", destination.getTrackAsin());
            } else {
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
                intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", destination.getCollectionAsin());
                intentForContentUri.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", destination.getAction());
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN", destination.getTrackAsin());
            }
            intentForContentUri.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
            intentForContentUri.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
            intentForContentUri.addFlags(268435456);
            context.startActivity(intentForContentUri);
        }
    }
}
