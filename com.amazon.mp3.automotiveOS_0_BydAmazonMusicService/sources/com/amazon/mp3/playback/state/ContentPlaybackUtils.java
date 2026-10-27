package com.amazon.mp3.playback.state;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.media.playback.PlayStatus;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ContentPlaybackUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b-\u0010.J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\b\u0010\b\u001a\u00020\u0002H\u0016J\b\u0010\t\u001a\u00020\u0002H\u0016J\b\u0010\n\u001a\u00020\u0002H\u0016J\b\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\f\u001a\u00020\u0002H\u0016J\b\u0010\r\u001a\u00020\u0002H\u0016J\b\u0010\u000e\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J2\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00020\u0015j\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0002`\u00162\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013H\u0016J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J$\u0010\u001e\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u001f\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020 J\u0010\u0010%\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0010H\u0016J\u0018\u0010(\u001a\u00020\u00022\b\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010\u001a\u001a\u00020\u0010R\u0017\u0010)\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,¨\u0006/"}, d2 = {"Lcom/amazon/mp3/playback/state/ContentPlaybackUtils;", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "Landroid/net/Uri;", "uri", "appendTracksIfNeeded", "inputUri", "removeTracksFromUriIfNeeded", "appendTracksToUriForDownloads", "getUserPlaylistUri", "getGenreUri", "getArtistUri", "getBarkerUri", "getButtonUri", "getLivestreamUri", "getPodcastShowUri", "getPodcastEpisodeUri", "", "asin", "getTrackUri", "", "asins", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getTrackUris", "stationId", "getStationUri", "playlistId", "getPlaylistUri", "artistName", "title", "getAlbumUri", "getAlbumUriWithoutTracks", "Lcom/amazon/music/media/playback/PlayStatus;", "playStatus", "", "convertToPlayState", "seedId", "getStationUriWithSeed", "Landroid/content/Context;", "context", "getUserPlaylistLocalUriWithoutTrack", "DEFAULT_URI", "Landroid/net/Uri;", "getDEFAULT_URI", "()Landroid/net/Uri;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ContentPlaybackUtils implements ContentPlaybackUtil {
    private static final Uri DEFAULT_URI;
    public static final ContentPlaybackUtils INSTANCE = new ContentPlaybackUtils();

    /* JADX INFO: compiled from: ContentPlaybackUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayStatus.values().length];
            iArr[PlayStatus.USER_PAUSED.ordinal()] = 1;
            iArr[PlayStatus.SYSTEM_PAUSED.ordinal()] = 2;
            iArr[PlayStatus.LOADING.ordinal()] = 3;
            iArr[PlayStatus.PREPARING.ordinal()] = 4;
            iArr[PlayStatus.SEEKING.ordinal()] = 5;
            iArr[PlayStatus.BUFFERING.ordinal()] = 6;
            iArr[PlayStatus.RENDERING.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ContentPlaybackUtils() {
    }

    static {
        Uri uri = Uri.parse("");
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"\")");
        DEFAULT_URI = uri;
    }

    public final Uri getDEFAULT_URI() {
        return DEFAULT_URI;
    }

    public Uri appendTracksIfNeeded(Uri uri) {
        String path = uri == null ? null : uri.getPath();
        return path == null ? uri : ((StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) "album", true) || StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) Environment.PLAYLIST_PATH, true) || StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) "artists", true)) && !StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) "tracks", true)) ? uri.buildUpon().appendEncodedPath("tracks").build() : uri;
    }

    public final Uri removeTracksFromUriIfNeeded(Uri inputUri) {
        if (inputUri == null) {
            return null;
        }
        Uri nonTrackCollectionUri = MediaProvider.Tracks.getNonTrackCollectionUri(inputUri);
        return nonTrackCollectionUri == null ? inputUri : nonTrackCollectionUri;
    }

    public final Uri appendTracksToUriForDownloads(Uri uri) {
        String path = uri == null ? null : uri.getPath();
        return path == null ? uri : ((StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) "album", true) || StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) Environment.PLAYLIST_PATH, true) || StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) "artists", true)) && !StringsKt__StringsKt.contains((CharSequence) path, (CharSequence) "tracks", true)) ? uri.buildUpon().appendEncodedPath("tracks").build() : uri;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getUserPlaylistUri() {
        return DEFAULT_URI;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getGenreUri() {
        return DEFAULT_URI;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getArtistUri() {
        return DEFAULT_URI;
    }

    public Uri getBarkerUri() {
        return DEFAULT_URI;
    }

    public Uri getButtonUri() {
        return DEFAULT_URI;
    }

    public Uri getLivestreamUri() {
        return DEFAULT_URI;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getPodcastShowUri() {
        return DEFAULT_URI;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getPodcastEpisodeUri() {
        return DEFAULT_URI;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getArtistUri(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Uri uri = Uri.parse(Intrinsics.stringPlus("artist/", asin));
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"artist/$asin\")");
        return uri;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getTrackUri(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Uri uriFindCollectionUriByAsin = new QuerySongByAsin(AmazonApplication.getContext()).findCollectionUriByAsin(asin);
        return uriFindCollectionUriByAsin == null ? DEFAULT_URI : uriFindCollectionUriByAsin;
    }

    public HashMap<String, Uri> getTrackUris(List<String> asins) {
        Intrinsics.checkNotNullParameter(asins, "asins");
        HashMap<String, Uri> mapFindCollectionUrisByAsin = new QuerySongByAsin(AmazonApplication.getContext()).findCollectionUrisByAsin(asins);
        Intrinsics.checkNotNullExpressionValue(mapFindCollectionUrisByAsin, "QuerySongByAsin(AmazonAp…llectionUrisByAsin(asins)");
        return mapFindCollectionUrisByAsin;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getStationUri(String stationId) {
        Intrinsics.checkNotNullParameter(stationId, "stationId");
        Uri contentUri = MediaProvider.Station.getContentUri(stationId);
        return contentUri == null ? DEFAULT_URI : contentUri;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getPlaylistUri(String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", playlistId);
        return contentUri == null ? DEFAULT_URI : contentUri;
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getAlbumUri(String artistName, String title, String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        if (artistName != null && title != null) {
            Uri contentUri = MediaProvider.Albums.Tracks.getContentUri("cirrus", IdGenerator.generateAlbumId(asin, title));
            Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(\n         …ource.ID_CIRRUS, albumId)");
            return contentUri;
        }
        return DEFAULT_URI;
    }

    public final Uri getAlbumUriWithoutTracks(String title, String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        if (title != null) {
            Uri contentUri = MediaProvider.Albums.getContentUri("cirrus", IdGenerator.generateAlbumId(asin, title));
            Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(\n         …ource.ID_CIRRUS, albumId)");
            return contentUri;
        }
        return DEFAULT_URI;
    }

    public final int convertToPlayState(PlayStatus playStatus) {
        Intrinsics.checkNotNullParameter(playStatus, "playStatus");
        switch (WhenMappings.$EnumSwitchMapping$0[playStatus.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
                return 2;
            default:
                return 0;
        }
    }

    @Override // com.amazon.music.media.auto.util.ContentPlaybackUtil
    public Uri getStationUriWithSeed(String seedId) {
        Intrinsics.checkNotNullParameter(seedId, "seedId");
        Uri contentUriWithSeeds = MediaProvider.Station.getContentUriWithSeeds(seedId);
        return contentUriWithSeeds == null ? DEFAULT_URI : contentUriWithSeeds;
    }

    public final Uri getUserPlaylistLocalUriWithoutTrack(Context context, String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Uri contentUriFromLuid = context == null ? null : MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", playlistId);
        return contentUriFromLuid == null ? DEFAULT_URI : contentUriFromLuid;
    }
}
