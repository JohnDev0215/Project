package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.destination.SharedUserPlaylistDestination;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class SharedUserPlaylistDestinationHandler implements DestinationHandler<SharedUserPlaylistDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final SharedUserPlaylistDestination destination) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.SharedUserPlaylists.getContentUri("cirrus", destination.getPlaylistId()));
        Action action = destination.getAction();
        intentForContentUri.addFlags(268435456);
        intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_IS_USER_PLAYLIST", true);
        intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", action);
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        context.startActivity(intentForContentUri);
    }
}
