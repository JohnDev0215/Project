package com.amazon.mp3.playback.view.lyrics;

import com.amazon.mp3.lyrics.item.LyricsLine;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsSelectionActionHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\u0018\u0000 '2\u00020\u0001:\u0003'()B\u0007¢\u0006\u0004\b%\u0010&J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0002J\u0016\u0010\u0011\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0012\u001a\u00020\fJ\u0006\u0010\u0013\u001a\u00020\fR0\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0016\u0010!\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006*"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler;", "", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler$EventType;", "retrieveCurrentEventType", "", "position", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler$ListActionType;", "retrieveCurrentActionState", "Lcom/amazon/mp3/lyrics/item/LyricsLine;", "lyricsLine", "", "updateListAndPosition", "", "isAddAction", "isRemoveAction", "isValidAction", "isListFull", "handleEvent", "isLineUnselected", "isLineSelected", "Ljava/util/LinkedList;", "", "<set-?>", "selectedLinesList", "Ljava/util/LinkedList;", "getSelectedLinesList", "()Ljava/util/LinkedList;", "startPosition", "I", "getStartPosition", "()I", "endPosition", "getEndPosition", "listAction", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler$ListActionType;", "eventType", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler$EventType;", "<init>", "()V", "Companion", "EventType", "ListActionType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsSelectionActionHandler {
    private LinkedList<String> selectedLinesList = new LinkedList<>();
    private int startPosition = -1;
    private int endPosition = -1;
    private ListActionType listAction = ListActionType.NULL_TYPE;
    private EventType eventType = EventType.INVALID;

    /* JADX INFO: compiled from: LyricsSelectionActionHandler.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler$EventType;", "", "(Ljava/lang/String;I)V", "SELECTED", "UNSELECTED", "INVALID", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum EventType {
        SELECTED,
        UNSELECTED,
        INVALID
    }

    /* JADX INFO: compiled from: LyricsSelectionActionHandler.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsSelectionActionHandler$ListActionType;", "", "(Ljava/lang/String;I)V", "ADD_EMPTY", "ADD_TOP", "ADD_BOTTOM", "REMOVE_TOP", "REMOVE_LAST", "NULL_TYPE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ListActionType {
        ADD_EMPTY,
        ADD_TOP,
        ADD_BOTTOM,
        REMOVE_TOP,
        REMOVE_LAST,
        NULL_TYPE
    }

    /* JADX INFO: compiled from: LyricsSelectionActionHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ListActionType.values().length];
            iArr[ListActionType.ADD_EMPTY.ordinal()] = 1;
            iArr[ListActionType.ADD_TOP.ordinal()] = 2;
            iArr[ListActionType.ADD_BOTTOM.ordinal()] = 3;
            iArr[ListActionType.REMOVE_TOP.ordinal()] = 4;
            iArr[ListActionType.REMOVE_LAST.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final LinkedList<String> getSelectedLinesList() {
        return this.selectedLinesList;
    }

    public final int getStartPosition() {
        return this.startPosition;
    }

    public final int getEndPosition() {
        return this.endPosition;
    }

    public final EventType handleEvent(LyricsLine lyricsLine, int position) {
        Intrinsics.checkNotNullParameter(lyricsLine, "lyricsLine");
        this.listAction = retrieveCurrentActionState(position);
        this.eventType = retrieveCurrentEventType();
        if (isValidAction()) {
            updateListAndPosition(lyricsLine, position);
        }
        return this.eventType;
    }

    private final EventType retrieveCurrentEventType() {
        return isLineSelected() ? EventType.SELECTED : isLineUnselected() ? EventType.UNSELECTED : EventType.INVALID;
    }

    private final ListActionType retrieveCurrentActionState(int position) {
        if (this.selectedLinesList.size() == 0) {
            return ListActionType.ADD_EMPTY;
        }
        int i = position - 1;
        int i2 = this.endPosition;
        if (i == i2) {
            return ListActionType.ADD_BOTTOM;
        }
        int i3 = position + 1;
        int i4 = this.startPosition;
        if (i3 == i4) {
            return ListActionType.ADD_TOP;
        }
        if (position == i4) {
            return ListActionType.REMOVE_TOP;
        }
        if (position == i2) {
            return ListActionType.REMOVE_LAST;
        }
        return ListActionType.NULL_TYPE;
    }

    private final void updateListAndPosition(LyricsLine lyricsLine, int position) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.listAction.ordinal()];
        if (i == 1) {
            this.endPosition = position;
            this.startPosition = position;
            this.selectedLinesList.addFirst(lyricsLine.getLine());
            return;
        }
        if (i == 2) {
            this.startPosition = position;
            this.selectedLinesList.addFirst(lyricsLine.getLine());
            return;
        }
        if (i == 3) {
            this.endPosition = position;
            this.selectedLinesList.addLast(lyricsLine.getLine());
        } else if (i == 4) {
            this.startPosition = position + 1;
            this.selectedLinesList.removeFirst();
        } else {
            if (i != 5) {
                return;
            }
            this.endPosition = position - 1;
            this.selectedLinesList.removeLast();
        }
    }

    public final boolean isLineUnselected() {
        return isRemoveAction();
    }

    public final boolean isLineSelected() {
        return !isListFull() && isAddAction();
    }

    private final boolean isAddAction() {
        ListActionType listActionType = this.listAction;
        return listActionType == ListActionType.ADD_BOTTOM || listActionType == ListActionType.ADD_EMPTY || listActionType == ListActionType.ADD_TOP;
    }

    private final boolean isRemoveAction() {
        ListActionType listActionType = this.listAction;
        return listActionType == ListActionType.REMOVE_LAST || listActionType == ListActionType.REMOVE_TOP;
    }

    private final boolean isValidAction() {
        return isLineUnselected() || isLineSelected();
    }

    private final boolean isListFull() {
        return this.selectedLinesList.size() >= 3;
    }
}
