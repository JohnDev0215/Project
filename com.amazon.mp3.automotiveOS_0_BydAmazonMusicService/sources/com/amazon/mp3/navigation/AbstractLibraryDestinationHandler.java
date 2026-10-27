package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractLibraryDestinationHandler<T extends Destination> implements DestinationHandler<T> {
    protected abstract Uri getContentURI(String sourceId);

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final T destination) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, getContentURI(LastViewedScreenUtil.getLastViewedSource(context, LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource()));
        intentForContentUri.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
        intentForContentUri.setFlags(268435456);
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        intentForContentUri.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        context.startActivity(intentForContentUri);
    }
}
