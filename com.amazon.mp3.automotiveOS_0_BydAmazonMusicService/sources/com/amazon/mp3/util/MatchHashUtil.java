package com.amazon.mp3.util;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class MatchHashUtil {
    public static int generateMatchHashForNotNormalizedMetadata(Context context, String title, String album, String artist, String albumArtist, int trackNumber, int discNumber) {
        return generateMatchHash(context, StringUtil.normalizeTrackName(title), StringUtil.normalizeAlbumName(album), StringUtil.normalizeArtistName(artist), StringUtil.normalizeArtistName(albumArtist), trackNumber, discNumber);
    }

    private static int generateMatchHash(Context context, String title, String album, String artist, String albumArtist, int trackNumber, int discNumber) {
        if (TextUtils.isEmpty(artist)) {
            artist = TextUtils.isEmpty(albumArtist) ? "Unknown Artist" : albumArtist;
        }
        if (album == null) {
            album = "";
        }
        return (title + '-' + album + '-' + trackNumber + '-' + discNumber + '-' + artist).toLowerCase(Locale.US).hashCode();
    }

    public static int generatePrimePlaylistTrackMatchHash(String sortTitle, String artist, String asin) {
        return (sortTitle + "-" + artist + "-" + asin).toLowerCase(Locale.US).hashCode();
    }

    public static int generatePrimePlaylistTrackMatchHash(CatalogPlaylistTrack track) {
        return generatePrimePlaylistTrackMatchHash(StringUtil.normalizeTrackName(track.getTitle()), StringUtil.normalizeArtistName(track.getArtistName()), track.getAsin());
    }
}
