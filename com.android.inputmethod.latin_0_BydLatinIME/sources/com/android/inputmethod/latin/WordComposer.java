package com.android.inputmethod.latin;

import com.android.inputmethod.event.CombinerChain;
import com.android.inputmethod.event.Event;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.common.CoordinateUtils;
import com.android.inputmethod.latin.common.InputPointers;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class WordComposer {
    public static final int CAPS_MODE_AUTO_SHIFTED = 5;
    public static final int CAPS_MODE_AUTO_SHIFT_LOCKED = 7;
    public static final int CAPS_MODE_MANUAL_SHIFTED = 1;
    public static final int CAPS_MODE_MANUAL_SHIFT_LOCKED = 3;
    public static final int CAPS_MODE_OFF = 0;
    private static final boolean DBG = false;
    private static final int MAX_WORD_LENGTH = 48;
    private int mCapitalizedMode;
    private int mCapsCount;
    private int mCodePointSize;
    private String mCombiningSpec;
    private int mDigitsCount;
    private boolean mIsOnlyFirstCharCapitalized;
    private CharSequence mTypedWordCache;
    private final InputPointers mInputPointers = new InputPointers(48);
    private CombinerChain mCombinerChain = new CombinerChain("");
    private final ArrayList<Event> mEvents = new ArrayList<>();
    private SuggestedWords.SuggestedWordInfo mAutoCorrection = null;
    private boolean mIsResumed = false;
    private boolean mIsBatchMode = false;
    private int mCursorPositionWithinWord = 0;
    private String mRejectedBatchModeSuggestion = null;

    public WordComposer() {
        refreshTypedWordCache();
    }

    public ComposedData getComposedDataSnapshot() {
        return new ComposedData(getInputPointers(), isBatchMode(), this.mTypedWordCache.toString());
    }

    public void restartCombining(final String combiningSpec) {
        if (combiningSpec == null) {
            combiningSpec = "";
        }
        if (combiningSpec.equals(this.mCombiningSpec)) {
            return;
        }
        this.mCombinerChain = new CombinerChain(this.mCombinerChain.getComposingWordWithCombiningFeedback().toString());
        this.mCombiningSpec = combiningSpec;
    }

    public void reset() {
        this.mCombinerChain.reset();
        this.mEvents.clear();
        this.mAutoCorrection = null;
        this.mCapsCount = 0;
        this.mDigitsCount = 0;
        this.mIsOnlyFirstCharCapitalized = false;
        this.mIsResumed = false;
        this.mIsBatchMode = false;
        this.mCursorPositionWithinWord = 0;
        this.mRejectedBatchModeSuggestion = null;
        refreshTypedWordCache();
    }

    private final void refreshTypedWordCache() {
        CharSequence composingWordWithCombiningFeedback = this.mCombinerChain.getComposingWordWithCombiningFeedback();
        this.mTypedWordCache = composingWordWithCombiningFeedback;
        this.mCodePointSize = Character.codePointCount(composingWordWithCombiningFeedback, 0, composingWordWithCombiningFeedback.length());
    }

    public int size() {
        return this.mCodePointSize;
    }

    public boolean isSingleLetter() {
        return size() == 1;
    }

    public final boolean isComposingWord() {
        return size() > 0;
    }

    public InputPointers getInputPointers() {
        return this.mInputPointers;
    }

    @Nonnull
    public Event processEvent(@Nonnull final Event event) {
        Event eventProcessEvent = this.mCombinerChain.processEvent(this.mEvents, event);
        refreshTypedWordCache();
        this.mEvents.add(event);
        return eventProcessEvent;
    }

    public void applyProcessedEvent(final Event event) {
        this.mCombinerChain.applyProcessedEvent(event);
        int i = event.mCodePoint;
        int i2 = event.mX;
        int i3 = event.mY;
        int size = size();
        refreshTypedWordCache();
        int i4 = this.mCodePointSize;
        this.mCursorPositionWithinWord = i4;
        boolean z = false;
        if (i4 == 0) {
            this.mIsOnlyFirstCharCapitalized = false;
        }
        if (-5 != event.mKeyCode) {
            if (size < 48 && !this.mIsBatchMode) {
                this.mInputPointers.addPointerAt(size, i2, i3, 0, 0);
            }
            if (size == 0) {
                this.mIsOnlyFirstCharCapitalized = Character.isUpperCase(i);
            } else {
                if (this.mIsOnlyFirstCharCapitalized && !Character.isUpperCase(i)) {
                    z = true;
                }
                this.mIsOnlyFirstCharCapitalized = z;
            }
            if (Character.isUpperCase(i)) {
                this.mCapsCount++;
            }
            if (Character.isDigit(i)) {
                this.mDigitsCount++;
            }
        }
        this.mAutoCorrection = null;
    }

    public void setCursorPositionWithinWord(final int posWithinWord) {
        this.mCursorPositionWithinWord = posWithinWord;
    }

    public boolean isCursorFrontOrMiddleOfComposingWord() {
        return this.mCursorPositionWithinWord != this.mCodePointSize;
    }

    public boolean moveCursorByAndReturnIfInsideComposingWord(final int expectedMoveAmount) {
        int i = this.mCursorPositionWithinWord;
        int[] codePointArray = StringUtils.toCodePointArray(this.mTypedWordCache);
        int iCharCount = 0;
        if (expectedMoveAmount >= 0) {
            while (iCharCount < expectedMoveAmount && i < codePointArray.length) {
                iCharCount += Character.charCount(codePointArray[i]);
                i++;
            }
        } else {
            while (iCharCount > expectedMoveAmount && i > 0) {
                i--;
                iCharCount -= Character.charCount(codePointArray[i]);
            }
        }
        if (iCharCount != expectedMoveAmount) {
            return false;
        }
        this.mCursorPositionWithinWord = i;
        CombinerChain combinerChain = this.mCombinerChain;
        combinerChain.applyProcessedEvent(combinerChain.processEvent(this.mEvents, Event.createCursorMovedEvent(i)));
        return true;
    }

    public void setBatchInputPointers(final InputPointers batchPointers) {
        this.mInputPointers.set(batchPointers);
        this.mIsBatchMode = true;
    }

    public void setBatchInputWord(final String word) {
        reset();
        this.mIsBatchMode = true;
        int length = word.length();
        int iOffsetByCodePoints = 0;
        while (iOffsetByCodePoints < length) {
            applyProcessedEvent(processEvent(Event.createEventForCodePointFromUnknownSource(Character.codePointAt(word, iOffsetByCodePoints))));
            iOffsetByCodePoints = Character.offsetByCodePoints(word, iOffsetByCodePoints, 1);
        }
    }

    public void setComposingWord(final int[] codePoints, final int[] coordinates) {
        reset();
        int length = codePoints.length;
        for (int i = 0; i < length; i++) {
            applyProcessedEvent(processEvent(Event.createEventForCodePointFromAlreadyTypedText(codePoints[i], CoordinateUtils.xFromArray(coordinates, i), CoordinateUtils.yFromArray(coordinates, i))));
        }
        this.mIsResumed = true;
    }

    public String getTypedWord() {
        return this.mTypedWordCache.toString();
    }

    public boolean isOrWillBeOnlyFirstCharCapitalized() {
        return isComposingWord() ? this.mIsOnlyFirstCharCapitalized : this.mCapitalizedMode != 0;
    }

    public boolean isAllUpperCase() {
        if (size() > 1) {
            return this.mCapsCount == size();
        }
        int i = this.mCapitalizedMode;
        return i == 7 || i == 3;
    }

    public boolean wasShiftedNoLock() {
        int i = this.mCapitalizedMode;
        return i == 5 || i == 1;
    }

    public boolean isMostlyCaps() {
        return this.mCapsCount > 1;
    }

    public boolean hasDigits() {
        return this.mDigitsCount > 0;
    }

    public void setCapitalizedModeAtStartComposingTime(final int mode) {
        this.mCapitalizedMode = mode;
    }

    public void adviseCapitalizedModeBeforeFetchingSuggestions(final int mode) {
        if (isComposingWord()) {
            return;
        }
        this.mCapitalizedMode = mode;
    }

    public boolean wasAutoCapitalized() {
        int i = this.mCapitalizedMode;
        return i == 7 || i == 5;
    }

    public void setAutoCorrection(final SuggestedWords.SuggestedWordInfo autoCorrection) {
        this.mAutoCorrection = autoCorrection;
    }

    public SuggestedWords.SuggestedWordInfo getAutoCorrectionOrNull() {
        return this.mAutoCorrection;
    }

    public boolean isResumed() {
        return this.mIsResumed;
    }

    public LastComposedWord commitWord(final int type, final CharSequence committedWord, final String separatorString, final NgramContext ngramContext) {
        LastComposedWord lastComposedWord = new LastComposedWord(this.mEvents, this.mInputPointers, this.mTypedWordCache.toString(), committedWord, separatorString, ngramContext, this.mCapitalizedMode);
        this.mInputPointers.reset();
        if (type != 2 && type != 1) {
            lastComposedWord.deactivate();
        }
        this.mCapsCount = 0;
        this.mDigitsCount = 0;
        this.mIsBatchMode = false;
        this.mCombinerChain.reset();
        this.mEvents.clear();
        this.mCodePointSize = 0;
        this.mIsOnlyFirstCharCapitalized = false;
        this.mCapitalizedMode = 0;
        refreshTypedWordCache();
        this.mAutoCorrection = null;
        this.mCursorPositionWithinWord = 0;
        this.mIsResumed = false;
        this.mRejectedBatchModeSuggestion = null;
        return lastComposedWord;
    }

    public void resumeSuggestionOnLastComposedWord(final LastComposedWord lastComposedWord) {
        this.mEvents.clear();
        Collections.copy(this.mEvents, lastComposedWord.mEvents);
        this.mInputPointers.set(lastComposedWord.mInputPointers);
        this.mCombinerChain.reset();
        refreshTypedWordCache();
        this.mCapitalizedMode = lastComposedWord.mCapitalizedMode;
        this.mAutoCorrection = null;
        this.mCursorPositionWithinWord = this.mCodePointSize;
        this.mRejectedBatchModeSuggestion = null;
        this.mIsResumed = true;
    }

    public boolean isBatchMode() {
        return this.mIsBatchMode;
    }

    public void setRejectedBatchModeSuggestion(final String rejectedSuggestion) {
        this.mRejectedBatchModeSuggestion = rejectedSuggestion;
    }

    public String getRejectedBatchModeSuggestion() {
        return this.mRejectedBatchModeSuggestion;
    }

    void addInputPointerForTest(int index, int keyX, int keyY) {
        this.mInputPointers.addPointerAt(index, keyX, keyY, 0, 0);
    }

    void setTypedWordCacheForTests(String typedWordCacheForTests) {
        this.mTypedWordCache = typedWordCacheForTests;
    }
}
