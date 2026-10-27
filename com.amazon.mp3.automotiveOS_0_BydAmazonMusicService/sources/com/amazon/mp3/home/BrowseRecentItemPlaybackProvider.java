package com.amazon.mp3.home;

import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.recentlyplayed.RecentStationsUpgradeHandler;
import com.amazon.mp3.util.ContentAccessUtil;

/* JADX INFO: loaded from: classes3.dex */
public class BrowseRecentItemPlaybackProvider<T extends CatalogContent> implements ContentAccessUtil.CatalogActionListener<T>, RecentStationsUpgradeHandler {
    /* JADX WARN: Removed duplicated region for block: B:32:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.music.ui.model.ArtworkFrameModel$PlayState getPlaystate(final com.amazon.mp3.recentlyplayed.RecentlyPlayedItem r10) {
        /*
            android.net.Uri r0 = com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper.getCurrentUri()
            com.amazon.music.media.playback.ControlSource r1 = com.amazon.music.media.playback.ControlSource.APP_UI
            com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController r2 = com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider.getController(r1)
            com.amazon.music.media.playback.MediaItem r2 = r2.getCurrentMediaItem()
            r3 = 1
            if (r0 == 0) goto L83
            if (r2 == 0) goto L83
            android.net.Uri r4 = r10.getContentUri()
            java.lang.String r5 = "cirrus"
            r6 = 0
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.Tracks.getFilterContentUri(r5, r6)
            com.amazon.music.media.playback.MediaItemId$Type r7 = com.amazon.music.media.playback.MediaItemId.Type.ASIN
            com.amazon.music.media.playback.MediaItemId r8 = r2.getMediaItemId(r7)
            if (r8 == 0) goto L2f
            com.amazon.music.media.playback.MediaItemId r7 = r2.getMediaItemId(r7)
            java.lang.String r7 = r7.getId()
            goto L30
        L2f:
            r7 = r6
        L30:
            com.amazon.music.media.playback.MediaItemId$Type r8 = com.amazon.music.media.playback.MediaItemId.Type.LUID
            com.amazon.music.media.playback.MediaItemId r9 = r2.getMediaItemId(r8)
            if (r9 == 0) goto L40
            com.amazon.music.media.playback.MediaItemId r2 = r2.getMediaItemId(r8)
            java.lang.String r6 = r2.getId()
        L40:
            boolean r2 = com.amazon.mp3.library.provider.MediaProvider.PrimeBrowseTracksCollection.isCollection(r0)
            if (r2 == 0) goto L53
            if (r7 == 0) goto L83
            java.lang.String r10 = r10.getCollectionId()
            boolean r10 = r7.equals(r10)
            if (r10 == 0) goto L83
            goto L84
        L53:
            boolean r2 = r4.equals(r5)
            if (r2 == 0) goto L72
            if (r6 == 0) goto L65
            java.lang.String r0 = r10.getTrackLuid()
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L84
        L65:
            if (r7 == 0) goto L83
            java.lang.String r10 = r10.getTrackAsin()
            boolean r10 = r7.equals(r10)
            if (r10 == 0) goto L83
            goto L84
        L72:
            boolean r10 = r4.equals(r0)
            if (r10 != 0) goto L84
            android.net.Uri r10 = com.amazon.mp3.library.provider.MediaProvider.removeTracksSegment(r0)
            boolean r10 = r4.equals(r10)
            if (r10 == 0) goto L83
            goto L84
        L83:
            r3 = 0
        L84:
            if (r3 == 0) goto L9a
            com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController r10 = com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider.getController(r1)
            com.amazon.music.media.playback.PlayStatus r10 = r10.getPlayStatus()
            boolean r10 = r10.shouldBePlaying()
            if (r10 == 0) goto L97
            com.amazon.music.ui.model.ArtworkFrameModel$PlayState r10 = com.amazon.music.ui.model.ArtworkFrameModel$PlayState.PLAYING
            return r10
        L97:
            com.amazon.music.ui.model.ArtworkFrameModel$PlayState r10 = com.amazon.music.ui.model.ArtworkFrameModel$PlayState.PAUSED
            return r10
        L9a:
            com.amazon.music.ui.model.ArtworkFrameModel$PlayState r10 = com.amazon.music.ui.model.ArtworkFrameModel$PlayState.STOPPED
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.home.BrowseRecentItemPlaybackProvider.getPlaystate(com.amazon.mp3.recentlyplayed.RecentlyPlayedItem):com.amazon.music.ui.model.ArtworkFrameModel$PlayState");
    }
}
