package com.amazon.mp3.playback.view.lyrics;

import android.widget.TextView;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.playback.view.lyrics.LyricsSelectionActionHandler;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsSelector.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0017\u0010\u0018J*\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002J*\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsSelector;", "", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler$EventType;", "eventType", "Lcom/amazon/mp3/lyrics/item/LyricsLine;", "lyricsLine", "Landroid/widget/TextView;", "lyricsTextView", "Lcom/amazon/mp3/playback/view/lyrics/LyricsListViewContainer;", "lyricsListViewContainer", "", "handleLyricsListView", "", "position", "eventHandler", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler;", "lyricsSelectionActionHandler", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler;", "Ljava/util/LinkedList;", "", "getSelectedLinesList", "()Ljava/util/LinkedList;", "selectedLinesList", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsSelector {
    private final LyricsSelectionActionHandler lyricsSelectionActionHandler = new LyricsSelectionActionHandler();

    public final LinkedList<String> getSelectedLinesList() {
        return this.lyricsSelectionActionHandler.getSelectedLinesList();
    }

    public final void eventHandler(LyricsLine lyricsLine, int position, TextView lyricsTextView, LyricsListViewContainer lyricsListViewContainer) {
        Intrinsics.checkNotNullParameter(lyricsLine, "lyricsLine");
        LyricsSelectionActionHandler.EventType eventTypeHandleEvent = this.lyricsSelectionActionHandler.handleEvent(lyricsLine, position);
        if (eventTypeHandleEvent == LyricsSelectionActionHandler.EventType.INVALID || lyricsTextView == null) {
            return;
        }
        handleLyricsListView(eventTypeHandleEvent, lyricsLine, lyricsTextView, lyricsListViewContainer);
    }

    private final void handleLyricsListView(LyricsSelectionActionHandler.EventType eventType, LyricsLine lyricsLine, TextView lyricsTextView, LyricsListViewContainer lyricsListViewContainer) {
        if (eventType == LyricsSelectionActionHandler.EventType.SELECTED) {
            lyricsLine.setLineSelected(true);
            if (lyricsListViewContainer == null) {
                return;
            }
            lyricsListViewContainer.setSelectedLineBackground(lyricsTextView);
            return;
        }
        if (eventType == LyricsSelectionActionHandler.EventType.UNSELECTED) {
            lyricsLine.setLineSelected(false);
            if (lyricsListViewContainer == null) {
                return;
            }
            lyricsListViewContainer.setUnSelectedLineBackground(lyricsTextView);
        }
    }
}
