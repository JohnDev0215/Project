package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.AllAccessUserPlaylistDestination;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class AllAccessUserPlaylistDestinationHandler implements DestinationHandler<AllAccessUserPlaylistDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, AllAccessUserPlaylistDestination allAccessUserPlaylistDestination) {
        String playlistId = allAccessUserPlaylistDestination.getPlaylistId();
        if ("aaup".equals(playlistId)) {
            playlistId = GetOnDemandPlaylistsService.INSTANCE.get().getAllAccessUserPlaylistFromCache();
        }
        if (playlistId != null) {
            navigateToAllAccessUserPlaylist(context, allAccessUserPlaylistDestination, playlistId);
        } else {
            redirectToPlaylistsLandingPage(context);
        }
    }

    private void redirectToPlaylistsLandingPage(Context context) {
        Intent playlistsIntent = PrimeActivityFactory.getPlaylistsIntent(context);
        playlistsIntent.addFlags(32768);
        playlistsIntent.addFlags(268435456);
        new BrushFragmentNavigation.Builder("playlists").withTitle("").updateIntent(playlistsIntent);
        context.startActivity(playlistsIntent);
    }

    private void navigateToAllAccessUserPlaylist(Context context, AllAccessUserPlaylistDestination allAccessUserPlaylistDestination, final String playlistKey) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.PrimePlaylists.getContentUri("cirrus", playlistKey));
        Action action = allAccessUserPlaylistDestination.getAction();
        intentForContentUri.addFlags(268435456);
        intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", action);
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), allAccessUserPlaylistDestination.getRef());
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), allAccessUserPlaylistDestination.getTag());
        context.startActivity(intentForContentUri);
    }
}
