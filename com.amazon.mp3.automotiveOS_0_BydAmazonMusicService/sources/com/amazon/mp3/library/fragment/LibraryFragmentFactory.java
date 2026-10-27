package com.amazon.mp3.library.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.catalog.fragment.LibraryArtistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.fragment.SongListFragment;
import com.amazon.mp3.library.activity.AddToPlaylistTabActivity;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryFragmentFactory {
    public static Fragment getFragment(Activity host, Uri contentUri) {
        return getFragment(host, contentUri, null);
    }

    public static Fragment getFragment(Activity host, Uri contentUri, Uri playlistUri) {
        Fragment addDetailTracksToPlaylistFragment = null;
        if (contentUri == null) {
            return null;
        }
        if (host.getIntent() == null) {
            throw new IllegalArgumentException("Specified host Activity's intent is null");
        }
        String source = MediaProvider.getSource(contentUri);
        String type = DefaultUriMatcher.getType(contentUri);
        Intent intent = new Intent(host.getIntent());
        intent.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        intent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", source);
        if (MediaProvider.Playlists.isPlaylist(contentUri)) {
            if ("true".equals(contentUri.getQueryParameter("edit"))) {
                addDetailTracksToPlaylistFragment = new EditPlaylistFragment();
            } else if ("true".equals(contentUri.getQueryParameter("addTo"))) {
                intent = appendAddToPlaylistExtras(intent, contentUri, source);
                addDetailTracksToPlaylistFragment = new AddToPlaylistFragment();
            } else if (playlistUri == null) {
                addDetailTracksToPlaylistFragment = new PlaylistDetailFragment();
            } else {
                intent = appendAddToPlaylistExtras(intent, contentUri, playlistUri, source);
                addDetailTracksToPlaylistFragment = new AddPlaylistDetailToPlaylistFragment();
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.track")) {
            if (playlistUri == null) {
                if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled() && !(host instanceof AddToPlaylistTabActivity)) {
                    addDetailTracksToPlaylistFragment = new LibraryTracksFragment();
                } else {
                    addDetailTracksToPlaylistFragment = new SongListFragment();
                }
            } else {
                intent = appendAddToPlaylistExtras(intent, contentUri, playlistUri, source);
                addDetailTracksToPlaylistFragment = new AddTrackListToPlaylistFragment();
            }
        } else if (type.equals("vnd.android.cursor.item/vnd.amazonmp3.album")) {
            if (playlistUri == null) {
                addDetailTracksToPlaylistFragment = new AlbumDetailFragment();
            } else {
                intent = appendAddToPlaylistExtras(intent, contentUri, playlistUri, source);
                addDetailTracksToPlaylistFragment = new AddDetailTracksToPlaylistFragment();
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.album")) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled() && !(host instanceof AddToPlaylistTabActivity)) {
                addDetailTracksToPlaylistFragment = new LibraryAlbumsFragment();
            } else {
                addDetailTracksToPlaylistFragment = new AlbumListFragment();
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.artist")) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled() && !(host instanceof AddToPlaylistTabActivity)) {
                addDetailTracksToPlaylistFragment = new LibraryArtistsFragment();
            } else {
                addDetailTracksToPlaylistFragment = new ArtistListFragment();
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.playlists")) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled() && !(host instanceof AddToPlaylistTabActivity)) {
                addDetailTracksToPlaylistFragment = new LibraryPlaylistsFragment();
            } else {
                addDetailTracksToPlaylistFragment = new PlaylistsListFragment();
            }
        } else if (type.equals("vnd.android.cursor.dir/vnd.amazonmp3.genre")) {
            addDetailTracksToPlaylistFragment = new GenreListFragment();
        } else if (type.equals("vnd.android.cursor.item/vnd.amazonmp3.artist")) {
            if (playlistUri != null) {
                intent = appendAddToPlaylistExtras(intent, contentUri, playlistUri, source);
                addDetailTracksToPlaylistFragment = new AddDetailTracksToPlaylistFragment();
            }
        } else if (type.equals("vnd.android.cursor.item/vnd.amazonmp3.genre") && playlistUri != null) {
            intent = appendAddToPlaylistExtras(intent, contentUri, playlistUri, source);
            addDetailTracksToPlaylistFragment = new AddDetailTracksToPlaylistFragment();
        }
        if (addDetailTracksToPlaylistFragment != null) {
            addDetailTracksToPlaylistFragment.setArguments(intent.getExtras());
        }
        return addDetailTracksToPlaylistFragment;
    }

    private static Intent appendAddToPlaylistExtras(Intent i, Uri contentUri, Uri playlistUri, String sourceId) {
        boolean zEquals = "true".equals(contentUri.getQueryParameter("mutated"));
        i.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        i.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", sourceId);
        i.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", playlistUri);
        i.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_MUTATED", zEquals);
        i.addFlags(603979776);
        return i;
    }

    private static Intent appendAddToPlaylistExtras(Intent i, Uri contentUri, String sourceId) {
        Uri contentUri2 = MediaProvider.UdoPlaylists.getContentUri(MediaProvider.getSource(contentUri), PlaylistUtil.getPlaylistId(contentUri));
        boolean zEquals = "true".equals(contentUri.getQueryParameter("mutated"));
        i.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", MediaProvider.Tracks.getFilterContentUri(sourceId, ""));
        i.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", sourceId);
        i.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", contentUri2);
        i.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_MUTATED", zEquals);
        i.addFlags(603979776);
        return i;
    }
}
