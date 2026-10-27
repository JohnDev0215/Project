package com.amazon.mp3.library.util;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;

/* JADX INFO: loaded from: classes3.dex */
public class AddToPlaylistManager {
    private final Context mContext;
    private final AddToPlaylistListener mListener;

    public interface AddToPlaylistListener {
        void onError(ErrorReason errorReason);

        void onSuccess();
    }

    public enum ErrorReason {
        ITEM_ALREADY_IN_PLAYLIST,
        MAX_PLAYLIST_SIZE_REACHED,
        AAUP_PLAYLIST_TRACK_LIMIT,
        AAUP_RESTRICTION_LIMIT
    }

    public AddToPlaylistManager(Context context, AddToPlaylistListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    public void addToPlaylist(Uri playlistUri, Uri tracksUri) {
        boolean zEquals = "cirrus".equals(MediaProvider.getSource(playlistUri));
        boolean zIsPrimeAdditionalTrack = MediaProvider.Tracks.isPrimeAdditionalTrack(tracksUri);
        boolean zIsCatalogTrackInPlaylist = MediaProvider.Tracks.isCatalogTrackInPlaylist(tracksUri);
        boolean zIsPrimeAsinLuidTrack = MediaProvider.Tracks.isPrimeAsinLuidTrack(tracksUri);
        boolean zIsAllAsinTracksAlbum = MediaProvider.Albums.Tracks.isAllAsinTracksAlbum(this.mContext, tracksUri);
        boolean zIsAllLuidTracksAlbum = MediaProvider.Albums.Tracks.isAllLuidTracksAlbum(this.mContext, tracksUri);
        boolean z = zIsPrimeAdditionalTrack || zIsCatalogTrackInPlaylist || zIsPrimeAsinLuidTrack || zIsAllAsinTracksAlbum;
        if (zEquals && z && !zIsAllLuidTracksAlbum) {
            addtoPlaylistByAsin(playlistUri, tracksUri);
        } else {
            addToPlaylistByLuid(playlistUri, tracksUri);
        }
    }

    private void addToPlaylistByLuid(Uri playlistUri, Uri tracksUri) {
        new AddToPlaylistByLuidTask(this.mContext, playlistUri, tracksUri, this.mListener).execute(new Void[0]);
    }

    private void addtoPlaylistByAsin(Uri playlistUri, Uri tracksUri) {
        new AddToPlaylistByAsinTask(this.mContext, playlistUri, tracksUri, this.mListener).execute(new Void[0]);
    }
}
