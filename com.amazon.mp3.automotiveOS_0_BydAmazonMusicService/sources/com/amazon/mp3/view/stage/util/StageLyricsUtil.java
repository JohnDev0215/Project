package com.amazon.mp3.view.stage.util;

import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.playback.fragment.NPSViews;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: StageLyricsUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/view/stage/util/StageLyricsUtil;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageLyricsUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: StageLyricsUtil.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\tJ \u0010\u0012\u001a\u00020\u00132\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/view/stage/util/StageLyricsUtil$Companion;", "", "()V", "LYRICS_LINE_EQUALIZER_VALUE", "", "findCurrentPlayingLyricsLines", "Lkotlin/Pair;", "", "lyrics", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "findLyricsIndexForCurrentTime", "currentTime", "", "getLyricsPreviewViewType", "Lcom/amazon/mp3/playback/fragment/NPSViews;", "isLyricsInAutomatedPreview", "", "getTimeToTransitionFromAlbumArtToLyrics", "resetLyricsSelection", "", "startIndexOfLyricsSelected", "endIndexOfLyricsSelected", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getTimeToTransitionFromAlbumArtToLyrics(Lyrics lyrics) {
            if (lyrics != null && Intrinsics.areEqual(lyrics.getLines().get(0).getLine(), "...")) {
                return Math.max(lyrics.getLines().get(0).getEndTime(), 3000L);
            }
            return 3000L;
        }

        public final Pair<Integer, Integer> findCurrentPlayingLyricsLines(Lyrics lyrics) {
            int i;
            Intrinsics.checkNotNullParameter(lyrics, "lyrics");
            int iFindLyricsIndexForCurrentTime = findLyricsIndexForCurrentTime(lyrics, PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).getPositionMillis());
            if (lyrics.getLines().size() >= 2 && iFindLyricsIndexForCurrentTime != lyrics.getLines().size() - 1) {
                i = iFindLyricsIndexForCurrentTime + 1;
            } else if (lyrics.getLines().size() >= 2) {
                iFindLyricsIndexForCurrentTime--;
                i = iFindLyricsIndexForCurrentTime;
            } else {
                i = iFindLyricsIndexForCurrentTime;
            }
            return new Pair<>(Integer.valueOf(iFindLyricsIndexForCurrentTime), Integer.valueOf(i));
        }

        private final int findLyricsIndexForCurrentTime(Lyrics lyrics, long currentTime) {
            List<LyricsLine> lines = lyrics.getLines();
            Intrinsics.checkNotNullExpressionValue(lines, "lyrics.lines");
            int i = 0;
            for (Object obj : lines) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                LyricsLine lyricsLine = (LyricsLine) obj;
                if (lyricsLine.getEndTime() >= currentTime) {
                    String line = lyricsLine.getLine();
                    Intrinsics.checkNotNullExpressionValue(line, "lyricsLine.line");
                    Object[] array = StringsKt__StringsKt.split$default((CharSequence) line, new String[]{" "}, false, 0, 6, (Object) null).toArray(new String[0]);
                    if (array == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    }
                    if (array.length != 1) {
                        return i;
                    }
                }
                i = i2;
            }
            return lyrics.getLines().size() - 1;
        }

        public final void resetLyricsSelection(Lyrics lyrics, int startIndexOfLyricsSelected, int endIndexOfLyricsSelected) {
            List<LyricsLine> lines;
            if (startIndexOfLyricsSelected == -1 || endIndexOfLyricsSelected == -1 || startIndexOfLyricsSelected > endIndexOfLyricsSelected) {
                return;
            }
            while (true) {
                int i = startIndexOfLyricsSelected + 1;
                LyricsLine lyricsLine = null;
                if (lyrics != null && (lines = lyrics.getLines()) != null) {
                    lyricsLine = lines.get(startIndexOfLyricsSelected);
                }
                if (lyricsLine != null) {
                    lyricsLine.setLineSelected(false);
                }
                if (startIndexOfLyricsSelected == endIndexOfLyricsSelected) {
                    return;
                } else {
                    startIndexOfLyricsSelected = i;
                }
            }
        }

        public final NPSViews getLyricsPreviewViewType(boolean isLyricsInAutomatedPreview) {
            if (isLyricsInAutomatedPreview) {
                return NPSViews.LYRICS_AUTOMATED_PREVIEW;
            }
            return NPSViews.LYRICS_PREVIEW;
        }
    }
}
