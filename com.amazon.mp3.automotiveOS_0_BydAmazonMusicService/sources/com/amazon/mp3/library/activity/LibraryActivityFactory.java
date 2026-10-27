package com.amazon.mp3.library.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.fragment.AlbumListFragment;
import com.amazon.mp3.library.fragment.ArtistDetailsFragment;
import com.amazon.mp3.library.fragment.ArtistListFragment;
import com.amazon.mp3.library.fragment.GenreDetailFragment;
import com.amazon.mp3.library.fragment.GenreListFragment;
import com.amazon.mp3.library.fragment.PlaylistDetailFragment;
import com.amazon.mp3.library.fragment.PlaylistsListFragment;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.fragment.TrackListFragment;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.visualon.OSMPUtils.voOSType;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryActivityFactory {
    private LibraryActivityFactory() {
    }

    public static Intent getIntentForContentUri(Context context, Uri contentUri) {
        return getIntentForContentUri(context, contentUri, null);
    }

    public static Intent getIntentForContentUri(Context context, Uri contentUri, Uri playlistUri) {
        if (contentUri == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        if (playlistUri != null) {
            intent.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", playlistUri);
            intent.setClass(context, AddToPlaylistActivity.class);
            return intent;
        }
        Context applicationContext = context.getApplicationContext();
        String type = DefaultUriMatcher.getType(contentUri);
        if (MediaProvider.Playlists.isPlaylist(contentUri)) {
            if (MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(contentUri)) {
                intent.setClass(applicationContext, MusicHomeActivity.class);
                intent.putExtra("com.amazon.mp3.fragment.extra", PrimePlaylistDetailFragment.class.getSimpleName());
            } else if ("true".equals(contentUri.getQueryParameter("addTo"))) {
                intent.setClass(applicationContext, AddToPlaylistTabActivity.class);
            } else if ("true".equals(contentUri.getQueryParameter("edit"))) {
                intent.setClass(applicationContext, AddToPlaylistActivity.class);
            } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.playlists")) {
                intent.setClass(applicationContext, MusicHomeActivity.class);
                intent.putExtra("com.amazon.mp3.fragment.extra", PlaylistsListFragment.class.getSimpleName());
            } else {
                intent.setClass(applicationContext, MusicHomeActivity.class);
                intent.putExtra("com.amazon.mp3.fragment.extra", PlaylistDetailFragment.class.getSimpleName());
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.track")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", TrackListFragment.class.getSimpleName());
        } else if (type.equals("vnd.android.cursor.item/vnd.amazonmp3.genre")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", GenreDetailFragment.class.getSimpleName());
            intent.setFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.genre")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", GenreListFragment.class.getSimpleName());
        } else if (type.equals("vnd.android.cursor.item/vnd.amazonmp3.artist")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            if (LibraryArtistDetailFragment.shouldDisplayBauhausLibraryPage(applicationContext, contentUri)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", LibraryArtistDetailFragment.class.getSimpleName());
            } else {
                intent.putExtra("com.amazon.mp3.fragment.extra", ArtistDetailsFragment.class.getSimpleName());
                intent.setFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
                intent.setFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
            }
        } else if (type.equals("vnd.android.cursor.item/vnd.amazonmp3.primeartist")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            if (LibraryArtistDetailFragment.shouldDisplayBauhausLibraryPage(applicationContext, contentUri)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", LibraryArtistDetailFragment.class.getSimpleName());
            } else {
                new BrushFragmentNavigation.Builder("artist").updateIntent(intent);
                intent.setFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.album")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                intent.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
                intent.putExtra("com.amazon.mp3.fragment.extra", LibraryAlbumsFragment.class.getSimpleName());
            } else {
                intent.putExtra("com.amazon.mp3.fragment.extra", AlbumListFragment.class.getSimpleName());
            }
        } else if (type.equals("vnd.android.cursor.item/vnd.amazonmp3.album")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
                intent.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
                intent.putExtra("com.amazon.mp3.fragment.extra", AlbumDetailFragment.class.getSimpleName());
            } else {
                intent.putExtra("com.amazon.mp3.fragment.extra", com.amazon.mp3.library.fragment.AlbumDetailFragment.class.getSimpleName());
                intent.putExtra("com.amazon.mp3.library.EXTRA_CONTEXT_MENU_FLAGS", 1);
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.artist")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", ArtistListFragment.class.getSimpleName());
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.playlists")) {
            intent.setClass(applicationContext, MusicHomeActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", PlaylistsListFragment.class.getSimpleName());
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.nowplaying") || type.equals("vnd.android.cursor.item/vnd.amazonmp3.nowplaying")) {
            intent.setFlags(536870912);
            intent.setClass(applicationContext, NowPlayingFragmentActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", NowPlayingFragment.class.getSimpleName());
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid contentUri type %s passed to AdapterFactory", type));
        }
        return intent;
    }

    public static Intent getArtistDetailsIntent(Context context, String artistName, String artistAsin) {
        Intent intentCreateIntent = new BrushFragmentNavigation.Builder("artist").withTitle(artistName).withAsin(artistAsin).createIntent(context);
        intentCreateIntent.setClass(context, MusicHomeActivity.class);
        return intentCreateIntent;
    }

    public static Intent getArtistDetailsIntent(Context context, String artistName, String artistAsin, Uri artistUri, String source) {
        if (!(DefaultUriMatcher.match(artistUri) != -1)) {
            artistUri = MediaProvider.Artists.getContentUri(source, IdGenerator.generateArtistId(artistName));
        }
        if (LibraryArtistDetailFragment.shouldDisplayBauhausLibraryPage(context, artistUri)) {
            return getLibraryArtistDetailsIntent(context, artistUri, artistAsin);
        }
        return getArtistDetailsIntent(context, artistName, artistAsin);
    }

    public static Intent getLibraryArtistDetailsIntent(Context context, Uri artistUri, String artistAsin) {
        Intent intentForContentUri = getIntentForContentUri(context, artistUri);
        if (AmazonApplication.getCapabilities().isBauhausLibraryArtistDPEnabled()) {
            intentForContentUri.putExtra("ARTIST_ASIN", artistAsin);
            intentForContentUri.putExtra("SELECTED_FILTER", RefinementFilterType.LIBRARY.name());
        }
        return intentForContentUri;
    }

    public static Intent getLibraryArtistDetailsIntent(Context context, long artistId, String artistAsin) {
        Intent intentForContentUri = getIntentForContentUri(context, MediaProvider.Artists.getContentUri("cirrus", artistId));
        if (AmazonApplication.getCapabilities().isBauhausLibraryArtistDPEnabled()) {
            intentForContentUri.putExtra("ARTIST_ASIN", artistAsin);
        }
        return intentForContentUri;
    }

    public static Intent getLibraryHomeStartIntent(Context context) {
        return MusicHomeActivity.getStartIntent(context);
    }

    public static Intent getLibraryHomeStartIntent(Context context, String sourceId) {
        Intent startIntent = MusicHomeActivity.getStartIntent(context, LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(sourceId));
        if (LastViewedScreenUtil.getLastViewedScreen(context) != LastViewedScreenUtil.LastViewedScreen.LIBRARY) {
            startIntent.putExtra("com.amazon.mp3.fragment.extra", MusicHomeActivity.getMyLibraryHomeFragmentName());
        }
        return startIntent;
    }
}
