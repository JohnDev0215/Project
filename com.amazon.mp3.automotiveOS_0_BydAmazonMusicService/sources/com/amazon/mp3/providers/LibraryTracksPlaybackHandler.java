package com.amazon.mp3.providers;

import android.content.Context;
import android.net.Uri;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playback.util.TrackInitiatedPlaybackHelper;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryTracksPlaybackHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b7\u00108J\u0087\u0001\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJo\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0018\u0010!\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\rH\u0002J¥\u0001\u0010*\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010$\u001a\u00020\r2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u001a\u0010(\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050'0&2\b\u0010)\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u000f¢\u0006\u0004\b*\u0010+J\u001a\u0010,\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\r2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005J\u0085\u0001\u0010*\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010$\u001a\u00020\r2\u0006\u0010)\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u000f¢\u0006\u0004\b*\u0010-R\u001c\u0010/\u001a\n .*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106¨\u00069"}, d2 = {"Lcom/amazon/mp3/providers/LibraryTracksPlaybackHandler;", "", "Landroid/content/Context;", "context", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "musicTracks", "track", "", "selection", "", "selectionArgs", "sortOrder", "", "position", "", "shuffleAll", "Landroid/net/Uri;", "tracksUri", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "metricInformation", "Lcom/amazon/music/media/playback/ControlSource;", "controlSource", "isUserInitiated", "", "startCloudQueuePlayback", "(Landroid/content/Context;Ljava/util/List;Lcom/amazon/mp3/library/item/MusicTrack;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;IZLandroid/net/Uri;Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;Lcom/amazon/music/media/playback/ControlSource;Z)V", "listViewPosition", "contentUri", "shuffle", "startPlayback", "(Landroid/content/Context;ILandroid/net/Uri;ZLcom/amazon/mp3/library/item/MusicTrack;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;Lcom/amazon/music/media/playback/ControlSource;)V", "selectedTrack", "findIndexInCurrentQueue", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "trackCount", "trackList", "Lkotlin/Function1;", "Lrx/Observable;", "getTrack", "startPosition", "initiatePlayback", "(Landroid/content/Context;Lcom/amazon/music/views/library/metadata/ContentMetadata;ILjava/util/List;Lkotlin/jvm/functions/Function1;Ljava/lang/Integer;ZLandroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/media/playback/ControlSource;Z)V", "shouldAllowCloudQueuePlayback", "(Landroid/content/Context;Lcom/amazon/mp3/library/item/MusicTrack;Ljava/util/List;IIZLandroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/media/playback/ControlSource;Z)V", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/music/media/playback/PlaybackController;", "mPlaybackController", "Lcom/amazon/music/media/playback/PlaybackController;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryTracksPlaybackHandler {
    public static final LibraryTracksPlaybackHandler INSTANCE = new LibraryTracksPlaybackHandler();
    private static final String TAG = LibraryTracksPlaybackHandler.class.getSimpleName();
    private static final PlaybackController mPlaybackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
    private static final PlaybackPageType playbackPageType = PlaybackPageType.SONGS_LIST;

    private LibraryTracksPlaybackHandler() {
    }

    public static /* synthetic */ void initiatePlayback$default(LibraryTracksPlaybackHandler libraryTracksPlaybackHandler, Context context, ContentMetadata contentMetadata, int i, List list, Function1 function1, Integer num, boolean z, Uri uri, String str, String[] strArr, String str2, ControlSource controlSource, boolean z2, int i2, Object obj) {
        ControlSource controlSource2;
        if ((i2 & 2048) != 0) {
            ControlSource APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
            controlSource2 = APP_UI;
        } else {
            controlSource2 = controlSource;
        }
        libraryTracksPlaybackHandler.initiatePlayback(context, contentMetadata, i, list, function1, num, z, uri, str, strArr, str2, controlSource2, (i2 & 4096) != 0 ? true : z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void initiatePlayback(final android.content.Context r15, com.amazon.music.views.library.metadata.ContentMetadata r16, final int r17, final java.util.List<? extends com.amazon.mp3.library.item.MusicTrack> r18, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends rx.Observable<com.amazon.mp3.library.item.MusicTrack>> r19, java.lang.Integer r20, final boolean r21, final android.net.Uri r22, final java.lang.String r23, final java.lang.String[] r24, final java.lang.String r25, final com.amazon.music.media.playback.ControlSource r26, final boolean r27) {
        /*
            r14 = this;
            r1 = r15
            r0 = r16
            r4 = r17
            r2 = r19
            java.lang.String r3 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r3)
            java.lang.String r3 = "trackList"
            r5 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r3)
            java.lang.String r3 = "getTrack"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            java.lang.String r3 = "tracksUri"
            r7 = r22
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r3)
            java.lang.String r3 = "controlSource"
            r11 = r26
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r3)
            r3 = 0
            if (r20 == 0) goto L31
            int r3 = r20.intValue()
        L2f:
            r6 = r3
            goto L5a
        L31:
            boolean r6 = r0 instanceof com.amazon.music.views.library.metadata.TrackMetadata
            if (r6 == 0) goto L4a
            r6 = r0
            com.amazon.music.views.library.metadata.TrackMetadata r6 = (com.amazon.music.views.library.metadata.TrackMetadata) r6
            java.lang.Integer r8 = r6.getTrackPosition()
            if (r8 == 0) goto L4a
            java.lang.Integer r6 = r6.getTrackPosition()
            if (r6 != 0) goto L45
            goto L2f
        L45:
            int r3 = r6.intValue()
            goto L2f
        L4a:
            if (r21 == 0) goto L2f
            if (r4 <= 0) goto L2f
            kotlin.ranges.IntRange r6 = new kotlin.ranges.IntRange
            r6.<init>(r3, r4)
            kotlin.random.Random$Default r3 = kotlin.random.Random.INSTANCE
            int r3 = kotlin.ranges.RangesKt.random(r6, r3)
            goto L2f
        L5a:
            if (r0 != 0) goto L5d
            goto L75
        L5d:
            com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory r3 = new com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory
            r3.<init>(r15)
            android.net.Uri r0 = r16.getUri()
            com.amazon.mp3.library.item.AbstractItem r0 = r3.getItem(r0)
            boolean r3 = r0 instanceof com.amazon.mp3.library.item.MusicTrack
            if (r3 == 0) goto L71
            com.amazon.mp3.library.item.MusicTrack r0 = (com.amazon.mp3.library.item.MusicTrack) r0
            goto L72
        L71:
            r0 = 0
        L72:
            r3 = r0
            if (r3 != 0) goto L9e
        L75:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            java.lang.Object r0 = r2.invoke(r0)
            r12 = r0
            rx.Observable r12 = (rx.Observable) r12
            com.amazon.mp3.providers.LibraryTracksPlaybackHandler$$ExternalSyntheticLambda0 r13 = new com.amazon.mp3.providers.LibraryTracksPlaybackHandler$$ExternalSyntheticLambda0
            r0 = r13
            r1 = r15
            r2 = r18
            r3 = r17
            r4 = r6
            r5 = r21
            r6 = r22
            r7 = r23
            r8 = r24
            r9 = r25
            r10 = r26
            r11 = r27
            r0.<init>()
            r12.subscribe(r13)
            return
        L9e:
            com.amazon.mp3.providers.LibraryTracksPlaybackHandler r0 = com.amazon.mp3.providers.LibraryTracksPlaybackHandler.INSTANCE
            r1 = r15
            r2 = r3
            r3 = r18
            r4 = r17
            r5 = r6
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
            r12 = r27
            r0.initiatePlayback(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.providers.LibraryTracksPlaybackHandler.initiatePlayback(android.content.Context, com.amazon.music.views.library.metadata.ContentMetadata, int, java.util.List, kotlin.jvm.functions.Function1, java.lang.Integer, boolean, android.net.Uri, java.lang.String, java.lang.String[], java.lang.String, com.amazon.music.media.playback.ControlSource, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initiatePlayback$lambda-3, reason: not valid java name */
    public static final void m1606initiatePlayback$lambda3(Context context, List trackList, int i, int i2, boolean z, Uri tracksUri, String str, String[] strArr, String str2, ControlSource controlSource, boolean z2, MusicTrack musicTrack) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(trackList, "$trackList");
        Intrinsics.checkNotNullParameter(tracksUri, "$tracksUri");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        if (musicTrack == null) {
            return;
        }
        INSTANCE.initiatePlayback(context, musicTrack, trackList, i, i2, z, tracksUri, str, strArr, str2, controlSource, z2);
    }

    public static /* synthetic */ boolean shouldAllowCloudQueuePlayback$default(LibraryTracksPlaybackHandler libraryTracksPlaybackHandler, int i, MusicTrack musicTrack, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            musicTrack = null;
        }
        return libraryTracksPlaybackHandler.shouldAllowCloudQueuePlayback(i, musicTrack);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean shouldAllowCloudQueuePlayback(int trackCount, MusicTrack track) {
        byte b = track != null && new EligibilityUtil(null, 1, 0 == true ? 1 : 0).getPlayMode(track) == PlayModeEnum.SIMILAR;
        boolean z = trackCount >= 1000;
        Log.debug(TAG, "isVeryLargeTrackCount: " + z + ", track count: " + trackCount + " vs. maximum cloud queue track limit 1000");
        return (!z || b == true) && AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled() && Feature.cloud_queue_content.isEnabled();
    }

    public static /* synthetic */ void initiatePlayback$default(LibraryTracksPlaybackHandler libraryTracksPlaybackHandler, Context context, MusicTrack musicTrack, List list, int i, int i2, boolean z, Uri uri, String str, String[] strArr, String str2, ControlSource controlSource, boolean z2, int i3, Object obj) {
        ControlSource controlSource2;
        if ((i3 & 1024) != 0) {
            ControlSource APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
            controlSource2 = APP_UI;
        } else {
            controlSource2 = controlSource;
        }
        libraryTracksPlaybackHandler.initiatePlayback(context, musicTrack, list, i, i2, z, uri, str, strArr, str2, controlSource2, (i3 & 2048) != 0 ? true : z2);
    }

    public final void initiatePlayback(Context context, MusicTrack track, List<? extends MusicTrack> trackList, int trackCount, int startPosition, boolean shuffleAll, Uri tracksUri, String selection, String[] selectionArgs, String sortOrder, ControlSource controlSource, boolean isUserInitiated) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        Intrinsics.checkNotNullParameter(tracksUri, "tracksUri");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        if (CirrusMediaSource.matchCloud(tracksUri) && DeluxeContentUtil.isDeluxeContent(track.getExtension())) {
            return;
        }
        PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, playbackPageType);
        if (shouldAllowCloudQueuePlayback(trackCount, track)) {
            startCloudQueuePlayback(context, trackList, track, selection, selectionArgs, sortOrder, startPosition, shuffleAll, tracksUri, playbackMetricInformation, controlSource, isUserInitiated);
        } else {
            startPlayback(context, startPosition, tracksUri, shuffleAll, track, selection, selectionArgs, sortOrder, playbackMetricInformation, controlSource);
        }
    }

    private final void startCloudQueuePlayback(final Context context, List<? extends MusicTrack> musicTracks, final MusicTrack track, final String selection, final String[] selectionArgs, final String sortOrder, final int position, final boolean shuffleAll, final Uri tracksUri, final PlaybackMetricInformation metricInformation, ControlSource controlSource, boolean isUserInitiated) {
        Log.debug(TAG, "start cloud queue playback");
        List tracksForPlayback$default = TrackInitiatedPlaybackHelper.getTracksForPlayback$default(musicTracks, track, new EligibilityUtil(null, 1, 0 == true ? 1 : 0), null, 8, null);
        int iFindTrackPositionInList = TrackInitiatedPlaybackHelper.findTrackPositionInList(tracksForPlayback$default, track);
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        PlaybackPageType pageType = metricInformation.getPageType();
        Intrinsics.checkNotNullExpressionValue(pageType, "metricInformation.pageType");
        StartPlaybackUtil.startMusicTracksPlayback(context, tracksUri, "", tracksForPlayback$default, (14336 & 16) != 0 ? 0 : iFindTrackPositionInList, (14336 & 32) != 0 ? false : shuffleAll, (14336 & 64) != 0 ? true : true, pageType, (14336 & 256) != 0 ? false : false, controlSource, (14336 & 1024) != 0 ? null : null, (14336 & 2048) != 0 ? false : false, (14336 & 4096) != 0 ? null : null, (14336 & 8192) != 0 ? null : null, isUserInitiated, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.providers.LibraryTracksPlaybackHandler.startCloudQueuePlayback.1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                LibraryTracksPlaybackHandler.startPlayback$default(LibraryTracksPlaybackHandler.INSTANCE, context, position, tracksUri, shuffleAll, track, selection, selectionArgs, sortOrder, metricInformation, null, 512, null);
            }
        });
    }

    static /* synthetic */ void startPlayback$default(LibraryTracksPlaybackHandler libraryTracksPlaybackHandler, Context context, int i, Uri uri, boolean z, MusicTrack musicTrack, String str, String[] strArr, String str2, PlaybackMetricInformation playbackMetricInformation, ControlSource controlSource, int i2, Object obj) {
        ControlSource controlSource2;
        if ((i2 & 512) != 0) {
            ControlSource APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
            controlSource2 = APP_UI;
        } else {
            controlSource2 = controlSource;
        }
        libraryTracksPlaybackHandler.startPlayback(context, i, uri, z, musicTrack, str, strArr, str2, playbackMetricInformation, controlSource2);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void startPlayback(android.content.Context r18, int r19, android.net.Uri r20, boolean r21, com.amazon.mp3.library.item.MusicTrack r22, java.lang.String r23, java.lang.String[] r24, java.lang.String r25, com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation r26, com.amazon.music.media.playback.ControlSource r27) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.providers.LibraryTracksPlaybackHandler.startPlayback(android.content.Context, int, android.net.Uri, boolean, com.amazon.mp3.library.item.MusicTrack, java.lang.String, java.lang.String[], java.lang.String, com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation, com.amazon.music.media.playback.ControlSource):void");
    }

    private final int findIndexInCurrentQueue(MusicTrack selectedTrack, int listViewPosition) {
        String title = selectedTrack.getTitle();
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        if (playbackProvider == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.media.playback.SequencerPlaybackProvider");
        }
        Sequencer sequencer = ((SequencerPlaybackProvider) playbackProvider).getSequencer();
        if (sequencer != null) {
            int i = 0;
            int numMediaItems = sequencer.getNumMediaItems();
            while (i < numMediaItems) {
                int i2 = i + 1;
                if (sequencer.getMediaItem(i) != null) {
                    String name = sequencer.getMediaItem(i).getName();
                    Intrinsics.checkNotNullExpressionValue(name, "sequencer.getMediaItem(i).name");
                    if (Intrinsics.areEqual(name, title)) {
                        return i;
                    }
                }
                i = i2;
            }
        }
        return listViewPosition;
    }
}
