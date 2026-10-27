package com.amazon.mp3.download.helper;

import com.amazon.digitalmusicplayback.PlayerAudioQuality;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadStateEvaluator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0013\u001a\u00020\nR\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/download/helper/RedownloadStateEvaluator;", "", "playerAudioQuality", "Lcom/amazon/digitalmusicplayback/PlayerAudioQuality;", "currentDownloadQualityPreference", "Lcom/amazon/mp3/download/DownloadQualityPreferences;", "contentEncodings", "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "isSpatialModeSettingEnabled", "", "(Lcom/amazon/digitalmusicplayback/PlayerAudioQuality;Lcom/amazon/mp3/download/DownloadQualityPreferences;Ljava/util/List;Z)V", "doesMatchBestAvailableQuality", "qualityMap", "Lcom/amazon/mp3/playback/PlaybackAudioQualityToAudioQuality;", "downloadedAudioQuality", "Lcom/amazon/music/media/playback/PlaybackAudioQuality;", "doesMatchCurrentPreference", "doesMatchHDQuality", "shouldRedownload", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RedownloadStateEvaluator {
    private final List<ContentEncoding> contentEncodings;
    private final DownloadQualityPreferences currentDownloadQualityPreference;
    private final boolean isSpatialModeSettingEnabled;
    private final PlayerAudioQuality playerAudioQuality;

    /* JADX INFO: compiled from: RedownloadStateEvaluator.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DownloadQualityPreferences.values().length];
            iArr[DownloadQualityPreferences.LOW.ordinal()] = 1;
            iArr[DownloadQualityPreferences.HIGH.ordinal()] = 2;
            iArr[DownloadQualityPreferences.HD.ordinal()] = 3;
            iArr[DownloadQualityPreferences.AUTO.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ContentEncoding.values().length];
            iArr2[ContentEncoding.HD.ordinal()] = 1;
            iArr2[ContentEncoding.UHD.ordinal()] = 2;
            iArr2[ContentEncoding.IMMERSIVE.ordinal()] = 3;
            iArr2[ContentEncoding.DOLBY_ATMOS.ordinal()] = 4;
            iArr2[ContentEncoding.SONY_360.ordinal()] = 5;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RedownloadStateEvaluator(PlayerAudioQuality playerAudioQuality, DownloadQualityPreferences currentDownloadQualityPreference, List<? extends ContentEncoding> list, boolean z) {
        Intrinsics.checkNotNullParameter(playerAudioQuality, "playerAudioQuality");
        Intrinsics.checkNotNullParameter(currentDownloadQualityPreference, "currentDownloadQualityPreference");
        this.playerAudioQuality = playerAudioQuality;
        this.currentDownloadQualityPreference = currentDownloadQualityPreference;
        this.contentEncodings = list;
        this.isSpatialModeSettingEnabled = z;
    }

    public final boolean shouldRedownload() {
        if (this.playerAudioQuality == PlayerAudioQuality.UNKNOWN) {
            return true;
        }
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        PlaybackAudioQuality downloadedAudioQuality = PlaybackAudioQuality.fromPlayerAudioQuality(this.playerAudioQuality);
        Intrinsics.checkNotNullExpressionValue(downloadedAudioQuality, "downloadedAudioQuality");
        return !doesMatchCurrentPreference(playbackAudioQualityToAudioQuality, downloadedAudioQuality);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean doesMatchCurrentPreference(com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality r5, com.amazon.music.media.playback.PlaybackAudioQuality r6) {
        /*
            r4 = this;
            boolean r0 = r4.isSpatialModeSettingEnabled
            r1 = 1
            if (r0 == 0) goto L36
            java.util.List<com.amazon.music.contentEncoding.ContentEncoding> r0 = r4.contentEncodings
            r2 = 0
            if (r0 != 0) goto Lc
        La:
            r0 = r2
            goto L15
        Lc:
            com.amazon.music.contentEncoding.ContentEncoding r3 = com.amazon.music.contentEncoding.ContentEncoding.DOLBY_ATMOS
            boolean r0 = r0.contains(r3)
            if (r0 != r1) goto La
            r0 = r1
        L15:
            if (r0 != 0) goto L2e
            java.util.List<com.amazon.music.contentEncoding.ContentEncoding> r0 = r4.contentEncodings
            if (r0 != 0) goto L1d
        L1b:
            r0 = r2
            goto L26
        L1d:
            com.amazon.music.contentEncoding.ContentEncoding r3 = com.amazon.music.contentEncoding.ContentEncoding.SONY_360
            boolean r0 = r0.contains(r3)
            if (r0 != r1) goto L1b
            r0 = r1
        L26:
            if (r0 != 0) goto L2e
            boolean r0 = r5.isAtmosOr360(r6)
            if (r0 == 0) goto L2f
        L2e:
            r2 = r1
        L2f:
            if (r2 == 0) goto L36
            boolean r5 = r5.isAtmosOr360(r6)
            return r5
        L36:
            com.amazon.mp3.download.DownloadQualityPreferences r0 = r4.currentDownloadQualityPreference
            int[] r2 = com.amazon.mp3.download.helper.RedownloadStateEvaluator.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r2[r0]
            if (r0 == r1) goto L60
            r1 = 2
            if (r0 == r1) goto L5b
            r1 = 3
            if (r0 == r1) goto L56
            r1 = 4
            if (r0 != r1) goto L50
            boolean r5 = r4.doesMatchBestAvailableQuality(r5, r6)
            goto L64
        L50:
            kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
            r5.<init>()
            throw r5
        L56:
            boolean r5 = r4.doesMatchHDQuality(r5, r6)
            goto L64
        L5b:
            boolean r5 = r5.isHigh(r6)
            goto L64
        L60:
            boolean r5 = r5.isLow(r6)
        L64:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.download.helper.RedownloadStateEvaluator.doesMatchCurrentPreference(com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality, com.amazon.music.media.playback.PlaybackAudioQuality):boolean");
    }

    private final boolean doesMatchHDQuality(PlaybackAudioQualityToAudioQuality qualityMap, PlaybackAudioQuality downloadedAudioQuality) {
        List<ContentEncoding> list = this.contentEncodings;
        if (list != null && list.contains(ContentEncoding.HD)) {
            return qualityMap.isHD(downloadedAudioQuality);
        }
        return false;
    }

    private final boolean doesMatchBestAvailableQuality(PlaybackAudioQualityToAudioQuality qualityMap, PlaybackAudioQuality downloadedAudioQuality) {
        List listSortedWith;
        List<ContentEncoding> list = this.contentEncodings;
        ContentEncoding contentEncoding = null;
        if (list != null && (listSortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.amazon.mp3.download.helper.RedownloadStateEvaluator$doesMatchBestAvailableQuality$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((ContentEncoding) t).ordinal()), Integer.valueOf(((ContentEncoding) t2).ordinal()));
            }
        })) != null) {
            contentEncoding = (ContentEncoding) CollectionsKt___CollectionsKt.lastOrNull(listSortedWith);
        }
        if (contentEncoding == null) {
            contentEncoding = ContentEncoding.UNKNOWN;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[contentEncoding.ordinal()];
        if (i == 1) {
            return qualityMap.isHD(downloadedAudioQuality);
        }
        if (i == 2) {
            return qualityMap.isUHD(downloadedAudioQuality);
        }
        if (i == 3) {
            return qualityMap.isImmersive(downloadedAudioQuality);
        }
        if (i == 4 || i == 5) {
            return qualityMap.isAtmosOr360(downloadedAudioQuality);
        }
        return true;
    }
}
