package com.amazon.music.media.auto.util;

import android.net.Uri;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContentPlaybackUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0002H&J\b\u0010\u0006\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0002H&J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\bH&J$\u0010\u0011\u001a\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\bH&¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "", "Landroid/net/Uri;", "getUserPlaylistUri", "getGenreUri", "getArtistUri", "getPodcastShowUri", "getPodcastEpisodeUri", "", "asin", "getTrackUri", "stationId", "getStationUri", "playlistId", "getPlaylistUri", "artistName", "title", "getAlbumUri", "seedId", "getStationUriWithSeed", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public interface ContentPlaybackUtil {
    Uri getAlbumUri(String artistName, String title, String asin);

    Uri getArtistUri();

    Uri getArtistUri(String asin);

    Uri getGenreUri();

    Uri getPlaylistUri(String playlistId);

    Uri getPodcastEpisodeUri();

    Uri getPodcastShowUri();

    Uri getStationUri(String stationId);

    Uri getStationUriWithSeed(String seedId);

    Uri getTrackUri(String asin);

    Uri getUserPlaylistUri();
}
