package com.amazon.mp3.curate.provider;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: MusicItemContentUri.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/curate/provider/MusicItemContentUri;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getUri", "Landroid/net/Uri;", "musicItem", "Lcom/amazon/music/curate/provider/MusicItem;", "isLocal", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MusicItemContentUri {
    private static final Logger LOG;
    private final Context context;

    public MusicItemContentUri(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    static {
        Logger logger = LoggerFactory.getLogger(MusicItemContentUri.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(MusicItemConte…i::class.java.simpleName)");
        LOG = logger;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.net.Uri getUri(com.amazon.music.curate.provider.MusicItem r5, boolean r6) {
        /*
            r4 = this;
            android.content.Context r0 = r4.context
            r1 = 0
            if (r0 == 0) goto L99
            if (r5 != 0) goto L9
            goto L99
        L9:
            com.amazon.music.curate.provider.EntityType r0 = r5.getEntityType()
            ExternalActionInterface.v1_0.EntityIdType r2 = r5.getEntityIdType()
            java.lang.String r5 = r5.getEntityId()
            if (r6 != 0) goto L1a
            java.lang.String r6 = "cirrus"
            goto L1c
        L1a:
            java.lang.String r6 = "cirrus-local"
        L1c:
            com.amazon.music.curate.provider.EntityType r3 = com.amazon.music.curate.provider.EntityType.CATALOG_PLAYLIST
            if (r0 != r3) goto L25
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.PrimePlaylists.getContentUri(r6, r5)
            goto L8a
        L25:
            com.amazon.music.curate.provider.EntityType r3 = com.amazon.music.curate.provider.EntityType.USER_PLAYLIST
            if (r0 != r3) goto L41
            android.content.Context r0 = r4.context
            boolean r0 = com.amazon.mp3.library.provider.MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(r0, r5)
            if (r0 == 0) goto L36
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.SharedUserPlaylists.getContentUri(r6, r5)
            goto L8a
        L36:
            android.content.Context r0 = r4.context
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.getContentUriFromLuid(r0, r6, r5)
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(r5)
            goto L8a
        L41:
            com.amazon.music.curate.provider.EntityType r3 = com.amazon.music.curate.provider.EntityType.ALBUM
            if (r0 != r3) goto L5a
            android.net.Uri r0 = com.amazon.mp3.library.provider.MediaProvider.Albums.getContentUri(r6)
            android.content.Context r2 = r4.context
            java.lang.Long r5 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.getAlbumOrArtistIdByAsin(r2, r0, r5)
            if (r5 == 0) goto L89
            long r2 = r5.longValue()
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.Albums.Tracks.getContentUri(r6, r2)
            goto L8a
        L5a:
            com.amazon.music.curate.provider.EntityType r3 = com.amazon.music.curate.provider.EntityType.ARTIST
            if (r0 != r3) goto L73
            android.net.Uri r0 = com.amazon.mp3.library.provider.MediaProvider.Artists.getContentUri(r6)
            android.content.Context r2 = r4.context
            java.lang.Long r5 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.getAlbumOrArtistIdByAsin(r2, r0, r5)
            if (r5 == 0) goto L89
            long r2 = r5.longValue()
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.Artists.Tracks.getContentUri(r6, r2)
            goto L8a
        L73:
            com.amazon.music.curate.provider.EntityType r6 = com.amazon.music.curate.provider.EntityType.STATION
            if (r0 != r6) goto L89
            ExternalActionInterface.v1_0.EntityIdType r6 = ExternalActionInterface.v1_0.EntityIdType.STATION_KEY
            if (r2 != r6) goto L80
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.Station.getContentUri(r5)
            goto L8a
        L80:
            ExternalActionInterface.v1_0.EntityIdType r6 = ExternalActionInterface.v1_0.EntityIdType.STATION_SEED_ID
            if (r2 != r6) goto L89
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.Station.getContentUriWithSeeds(r5)
            goto L8a
        L89:
            r5 = r1
        L8a:
            org.slf4j.Logger r6 = com.amazon.mp3.curate.provider.MusicItemContentUri.LOG
            if (r5 != 0) goto L8f
            goto L93
        L8f:
            java.lang.String r1 = r5.toString()
        L93:
            java.lang.String r0 = "collectionUri = {}"
            r6.debug(r0, r1)
            return r5
        L99:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.curate.provider.MusicItemContentUri.getUri(com.amazon.music.curate.provider.MusicItem, boolean):android.net.Uri");
    }
}
