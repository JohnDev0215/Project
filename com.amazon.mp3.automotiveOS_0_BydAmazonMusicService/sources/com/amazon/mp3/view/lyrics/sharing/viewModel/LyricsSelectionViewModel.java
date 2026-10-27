package com.amazon.mp3.view.lyrics.sharing.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.stage.util.StageLyricsUtil;
import com.amazon.music.media.playback.MediaItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: LyricsSelectionViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b'\u0010(J\"\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\"\u0010\t\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0002J\u001a\u0010\f\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u001a\u0010\r\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R*\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R$\u0010\u000e\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00048\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b\u000e\u0010#\"\u0004\b$\u0010%R$\u0010\u000f\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00048\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b\u000f\u0010#\"\u0004\b&\u0010%¨\u0006*"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsSelectionViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "newLyrics", "", "position", "currentSelectedLineCount", "", "checkEligibilityAndUnselectLine", "checkEligibilityAndSelectLine", "lyrics", "countTotalSelectedCharacter", "selectLine", "unselectLine", "startIndexOfLyricsSelected", "endIndexOfLyricsSelected", "updateCounter", "setTheLatestLyrics", "onLyricsLineClicked", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "lyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/lifecycle/MutableLiveData;", "getLyrics", "()Landroidx/lifecycle/MutableLiveData;", "setLyrics", "(Landroidx/lifecycle/MutableLiveData;)V", "selectedLinesCounter", "getSelectedLinesCounter", "setSelectedLinesCounter", "Lcom/amazon/music/media/playback/MediaItem;", "currentMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "value", "I", "setStartIndexOfLyricsSelected", "(I)V", "setEndIndexOfLyricsSelected", "<init>", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsSelectionViewModel extends ViewModel {
    private MediaItem currentMediaItem;
    private int endIndexOfLyricsSelected;
    private MutableLiveData<Lyrics> lyrics;
    private final LyricsSharingDataModel lyricsSharingDataModel;
    private MutableLiveData<Integer> selectedLinesCounter;
    private int startIndexOfLyricsSelected;

    public LyricsSelectionViewModel(LyricsSharingDataModel lyricsSharingDataModel) {
        Intrinsics.checkNotNullParameter(lyricsSharingDataModel, "lyricsSharingDataModel");
        this.lyricsSharingDataModel = lyricsSharingDataModel;
        this.lyrics = new MutableLiveData<>(null);
        this.selectedLinesCounter = new MutableLiveData<>(0);
        this.startIndexOfLyricsSelected = lyricsSharingDataModel.getStartIndexOfLyricsSelected();
        this.endIndexOfLyricsSelected = lyricsSharingDataModel.getEndIndexOfLyricsSelected();
        this.currentMediaItem = lyricsSharingDataModel.getMediaItem();
    }

    public final MutableLiveData<Lyrics> getLyrics() {
        return this.lyrics;
    }

    public final MutableLiveData<Integer> getSelectedLinesCounter() {
        return this.selectedLinesCounter;
    }

    private final void setStartIndexOfLyricsSelected(int i) {
        this.lyricsSharingDataModel.setStartIndexOfLyricsSelected(i);
        this.startIndexOfLyricsSelected = i;
    }

    private final void setEndIndexOfLyricsSelected(int i) {
        this.lyricsSharingDataModel.setEndIndexOfLyricsSelected(i);
        this.endIndexOfLyricsSelected = i;
    }

    public final void setTheLatestLyrics() {
        this.lyrics.setValue(this.lyricsSharingDataModel.getLyrics());
        updateCounter(this.startIndexOfLyricsSelected, this.endIndexOfLyricsSelected);
    }

    public final void onLyricsLineClicked(int position) {
        int i;
        Lyrics lyrics = this.lyricsSharingDataModel.getLyrics();
        int i2 = this.startIndexOfLyricsSelected;
        int i3 = (i2 == -1 || (i = this.endIndexOfLyricsSelected) == -1) ? 0 : (i - i2) + 1;
        if (position <= this.endIndexOfLyricsSelected && i2 <= position) {
            checkEligibilityAndUnselectLine(lyrics, position, i3);
        } else {
            checkEligibilityAndSelectLine(lyrics, position, i3);
        }
        updateCounter(this.startIndexOfLyricsSelected, this.endIndexOfLyricsSelected);
        this.lyrics.setValue(lyrics);
    }

    private final void checkEligibilityAndUnselectLine(Lyrics newLyrics, int position, int currentSelectedLineCount) {
        if (currentSelectedLineCount == 1 && position == this.startIndexOfLyricsSelected) {
            unselectLine(newLyrics, position);
            setStartIndexOfLyricsSelected(-1);
            setEndIndexOfLyricsSelected(-1);
            return;
        }
        int i = this.startIndexOfLyricsSelected;
        int i2 = i + 1;
        int i3 = this.endIndexOfLyricsSelected;
        if (position < i3 && i2 <= position) {
            StageLyricsUtil.INSTANCE.resetLyricsSelection(newLyrics, i, i3);
            selectLine(newLyrics, position);
            setStartIndexOfLyricsSelected(position);
            setEndIndexOfLyricsSelected(position);
            return;
        }
        if (position == i) {
            setStartIndexOfLyricsSelected(position + 1);
            unselectLine(newLyrics, position);
        } else if (position == i3) {
            setEndIndexOfLyricsSelected(position - 1);
            unselectLine(newLyrics, position);
        }
    }

    private final void checkEligibilityAndSelectLine(Lyrics newLyrics, int position, int currentSelectedLineCount) {
        int iCoerceAtLeast = RangesKt___RangesKt.coerceAtLeast(3 - currentSelectedLineCount, 1);
        int i = this.startIndexOfLyricsSelected;
        int i2 = i - iCoerceAtLeast;
        int i3 = this.endIndexOfLyricsSelected;
        if (!(position <= iCoerceAtLeast + i3 && i2 <= position) || currentSelectedLineCount == 0) {
            StageLyricsUtil.INSTANCE.resetLyricsSelection(newLyrics, i, i3);
            selectLine(newLyrics, position);
            setStartIndexOfLyricsSelected(position);
            setEndIndexOfLyricsSelected(position);
            return;
        }
        if (currentSelectedLineCount == 3) {
            if (position == i - 1) {
                unselectLine(newLyrics, i3);
                setEndIndexOfLyricsSelected(this.endIndexOfLyricsSelected - 1);
            } else if (position == i3 + 1) {
                unselectLine(newLyrics, i);
                setStartIndexOfLyricsSelected(this.startIndexOfLyricsSelected + 1);
            }
        }
        boolean z = Math.abs(position - this.startIndexOfLyricsSelected) == 1 || Math.abs(position - this.endIndexOfLyricsSelected) == 1;
        setStartIndexOfLyricsSelected(RangesKt___RangesKt.coerceAtMost(this.startIndexOfLyricsSelected, position));
        setEndIndexOfLyricsSelected(RangesKt___RangesKt.coerceAtLeast(this.endIndexOfLyricsSelected, position));
        int i4 = this.endIndexOfLyricsSelected;
        if (position == i4) {
            int i5 = this.startIndexOfLyricsSelected;
            if (i5 > i4) {
                return;
            }
            while (true) {
                int i6 = i5 + 1;
                if (countTotalSelectedCharacter(newLyrics) <= 150) {
                    selectLine(newLyrics, i5);
                } else {
                    if (!z) {
                        StageLyricsUtil.INSTANCE.resetLyricsSelection(newLyrics, this.startIndexOfLyricsSelected, this.endIndexOfLyricsSelected);
                        selectLine(newLyrics, position);
                        setStartIndexOfLyricsSelected(position);
                        setEndIndexOfLyricsSelected(position);
                        return;
                    }
                    unselectLine(newLyrics, i5);
                    setStartIndexOfLyricsSelected(this.startIndexOfLyricsSelected + 1);
                }
                if (i5 == i4) {
                    return;
                } else {
                    i5 = i6;
                }
            }
        } else {
            int i7 = this.startIndexOfLyricsSelected;
            if (i7 > i4) {
                return;
            }
            while (true) {
                int i8 = i4 - 1;
                if (countTotalSelectedCharacter(newLyrics) <= 150) {
                    selectLine(newLyrics, i4);
                } else {
                    if (!z) {
                        StageLyricsUtil.INSTANCE.resetLyricsSelection(newLyrics, this.startIndexOfLyricsSelected, this.endIndexOfLyricsSelected);
                        selectLine(newLyrics, position);
                        setStartIndexOfLyricsSelected(position);
                        setEndIndexOfLyricsSelected(position);
                        return;
                    }
                    unselectLine(newLyrics, i4);
                    setEndIndexOfLyricsSelected(this.endIndexOfLyricsSelected - 1);
                }
                if (i4 == i7) {
                    return;
                } else {
                    i4 = i8;
                }
            }
        }
    }

    private final int countTotalSelectedCharacter(Lyrics lyrics) {
        int i;
        List<LyricsLine> lines;
        LyricsLine lyricsLine;
        String line;
        int i2 = this.startIndexOfLyricsSelected;
        if (i2 == -1 || (i = this.endIndexOfLyricsSelected) == -1 || i2 > i) {
            return 0;
        }
        int length = 0;
        while (true) {
            int i3 = i2 + 1;
            length += (lyrics == null || (lines = lyrics.getLines()) == null || (lyricsLine = lines.get(i2)) == null || (line = lyricsLine.getLine()) == null) ? 0 : line.length();
            if (i2 == i) {
                return length;
            }
            i2 = i3;
        }
    }

    private final void selectLine(Lyrics lyrics, int position) {
        List<LyricsLine> lines;
        LyricsLine lyricsLine = null;
        if (lyrics != null && (lines = lyrics.getLines()) != null) {
            lyricsLine = lines.get(position);
        }
        if (lyricsLine == null) {
            return;
        }
        lyricsLine.setLineSelected(true);
    }

    private final void unselectLine(Lyrics lyrics, int position) {
        List<LyricsLine> lines;
        LyricsLine lyricsLine = null;
        if (lyrics != null && (lines = lyrics.getLines()) != null) {
            lyricsLine = lines.get(position);
        }
        if (lyricsLine == null) {
            return;
        }
        lyricsLine.setLineSelected(false);
    }

    private final void updateCounter(int startIndexOfLyricsSelected, int endIndexOfLyricsSelected) {
        if (startIndexOfLyricsSelected == -1 || endIndexOfLyricsSelected == -1) {
            this.selectedLinesCounter.setValue(0);
        } else {
            this.selectedLinesCounter.setValue(Integer.valueOf((endIndexOfLyricsSelected - startIndexOfLyricsSelected) + 1));
        }
    }
}
