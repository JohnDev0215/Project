package com.android.inputmethod.event;

import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.StringUtils;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class Event {
    public static final int EVENT_TYPE_CURSOR_MOVE = 7;
    public static final int EVENT_TYPE_GESTURE = 4;
    public static final int EVENT_TYPE_INPUT_KEYPRESS = 1;
    public static final int EVENT_TYPE_MODE_KEY = 3;
    public static final int EVENT_TYPE_NOT_HANDLED = 0;
    public static final int EVENT_TYPE_SOFTWARE_GENERATED_STRING = 6;
    public static final int EVENT_TYPE_SUGGESTION_PICKED = 5;
    public static final int EVENT_TYPE_TOGGLE = 2;
    private static final int FLAG_CONSUMED = 4;
    private static final int FLAG_DEAD = 1;
    private static final int FLAG_NONE = 0;
    private static final int FLAG_REPEAT = 2;
    public static final int NOT_A_CODE_POINT = -1;
    public static final int NOT_A_KEY_CODE = 0;
    public final int mCodePoint;
    private final int mEventType;
    private final int mFlags;
    public final int mKeyCode;
    public final Event mNextEvent;
    public final SuggestedWords.SuggestedWordInfo mSuggestedWordInfo;
    public final CharSequence mText;
    public final int mX;
    public final int mY;

    private Event(final int type, final CharSequence text, final int codePoint, final int keyCode, final int x, final int y, final SuggestedWords.SuggestedWordInfo suggestedWordInfo, final int flags, final Event next) {
        this.mEventType = type;
        this.mText = text;
        this.mCodePoint = codePoint;
        this.mKeyCode = keyCode;
        this.mX = x;
        this.mY = y;
        this.mSuggestedWordInfo = suggestedWordInfo;
        this.mFlags = flags;
        this.mNextEvent = next;
        if (5 == type) {
            if (suggestedWordInfo == null) {
                throw new RuntimeException("Wrong event: SUGGESTION_PICKED event must have a non-null SuggestedWordInfo");
            }
        } else if (suggestedWordInfo != null) {
            throw new RuntimeException("Wrong event: only SUGGESTION_PICKED events may have a non-null SuggestedWordInfo");
        }
    }

    @Nonnull
    public static Event createSoftwareKeypressEvent(final int codePoint, final int keyCode, final int x, final int y, final boolean isKeyRepeat) {
        return new Event(1, null, codePoint, keyCode, x, y, null, isKeyRepeat ? 2 : 0, null);
    }

    @Nonnull
    public static Event createHardwareKeypressEvent(final int codePoint, final int keyCode, final Event next, final boolean isKeyRepeat) {
        return new Event(1, null, codePoint, keyCode, -4, -4, null, isKeyRepeat ? 2 : 0, next);
    }

    @Nonnull
    public static Event createDeadEvent(final int codePoint, final int keyCode, final Event next) {
        return new Event(1, null, codePoint, keyCode, -4, -4, null, 1, next);
    }

    @Nonnull
    public static Event createEventForCodePointFromUnknownSource(final int codePoint) {
        return new Event(1, null, codePoint, 0, -1, -1, null, 0, null);
    }

    @Nonnull
    public static Event createEventForCodePointFromAlreadyTypedText(final int codePoint, final int x, final int y) {
        return new Event(1, null, codePoint, 0, x, y, null, 0, null);
    }

    @Nonnull
    public static Event createSuggestionPickedEvent(final SuggestedWords.SuggestedWordInfo suggestedWordInfo) {
        return new Event(5, suggestedWordInfo.mWord, -1, 0, -2, -2, suggestedWordInfo, 0, null);
    }

    @Nonnull
    public static Event createSoftwareTextEvent(final CharSequence text, final int keyCode) {
        return new Event(6, text, -1, keyCode, -1, -1, null, 0, null);
    }

    @Nonnull
    public static Event createPunctuationSuggestionPickedEvent(final SuggestedWords.SuggestedWordInfo suggestedWordInfo) {
        return new Event(5, suggestedWordInfo.mWord, suggestedWordInfo.mWord.charAt(0), 0, -2, -2, suggestedWordInfo, 0, null);
    }

    @Nonnull
    public static Event createCursorMovedEvent(final int moveAmount) {
        return new Event(7, null, -1, 0, moveAmount, -1, null, 0, null);
    }

    @Nonnull
    public static Event createConsumedEvent(final Event source) {
        return new Event(source.mEventType, source.mText, source.mCodePoint, source.mKeyCode, source.mX, source.mY, source.mSuggestedWordInfo, source.mFlags | 4, source.mNextEvent);
    }

    @Nonnull
    public static Event createNotHandledEvent() {
        return new Event(0, null, -1, 0, -1, -1, null, 0, null);
    }

    public boolean isFunctionalKeyEvent() {
        return -1 == this.mCodePoint;
    }

    public boolean isDead() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isKeyRepeat() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isConsumed() {
        return (this.mFlags & 4) != 0;
    }

    public boolean isGesture() {
        return 4 == this.mEventType;
    }

    public boolean isSuggestionStripPress() {
        return 5 == this.mEventType;
    }

    public boolean isHandled() {
        return this.mEventType != 0;
    }

    public CharSequence getTextToCommit() {
        if (isConsumed()) {
            return "";
        }
        switch (this.mEventType) {
            case 0:
            case 2:
            case 3:
            case 7:
                return "";
            case 1:
                return StringUtils.newSingleCodePointString(this.mCodePoint);
            case 4:
            case 5:
            case 6:
                return this.mText;
            default:
                throw new RuntimeException("Unknown event type: " + this.mEventType);
        }
    }
}
