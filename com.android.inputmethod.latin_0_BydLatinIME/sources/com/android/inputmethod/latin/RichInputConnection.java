package com.android.inputmethod.latin;

import android.inputmethodservice.InputMethodService;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import com.android.inputmethod.compat.InputConnectionCompatUtils;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.common.UnicodeSurrogate;
import com.android.inputmethod.latin.inputlogic.PrivateCommandPerformer;
import com.android.inputmethod.latin.settings.SpacingAndPunctuations;
import com.android.inputmethod.latin.utils.CapsModeUtils;
import com.android.inputmethod.latin.utils.DebugLogUtils;
import com.android.inputmethod.latin.utils.NgramContextUtils;
import com.android.inputmethod.latin.utils.ScriptUtils;
import com.android.inputmethod.latin.utils.SpannableStringUtils;
import com.android.inputmethod.latin.utils.StatsUtils;
import com.android.inputmethod.latin.utils.TextRange;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class RichInputConnection implements PrivateCommandPerformer {
    private static final boolean DBG = false;
    private static final boolean DEBUG_BATCH_NESTING = false;
    private static final boolean DEBUG_PREVIOUS_TEXT = false;
    private static final int INVALID_CURSOR_POSITION = -1;
    private static final int NUM_CHARS_TO_GET_AFTER_CURSOR = 40;
    private static final int NUM_CHARS_TO_GET_BEFORE_CURSOR = 40;
    private static final int OPERATION_GET_TEXT_AFTER_CURSOR = 1;
    private static final int OPERATION_GET_TEXT_BEFORE_CURSOR = 0;
    private static final int OPERATION_GET_WORD_RANGE_AT_CURSOR = 2;
    private static final int OPERATION_RELOAD_TEXT_CACHE = 3;
    private static final long SLOW_INPUT_CONNECTION_ON_FULL_RELOAD_MS = 1000;
    private static final long SLOW_INPUT_CONNECTION_ON_PARTIAL_RELOAD_MS = 200;
    private static final String TAG = "RichInputConnection";
    private final InputMethodService mParent;
    private static final String[] OPERATION_NAMES = {"GET_TEXT_BEFORE_CURSOR", "GET_TEXT_AFTER_CURSOR", "GET_WORD_RANGE_AT_CURSOR", "RELOAD_TEXT_CACHE"};
    private static final long SLOW_INPUTCONNECTION_PERSIST_MS = TimeUnit.MINUTES.toMillis(10);
    private int mExpectedSelStart = -1;
    private int mExpectedSelEnd = -1;
    private final StringBuilder mCommittedTextBeforeComposingText = new StringBuilder();
    private final StringBuilder mComposingText = new StringBuilder();
    private SpannableStringBuilder mTempObjectForCommitText = new SpannableStringBuilder();
    private long mLastSlowInputConnectionTime = -SLOW_INPUTCONNECTION_PERSIST_MS;
    private InputConnection mIC = null;
    private int mNestLevel = 0;

    public RichInputConnection(final InputMethodService parent) {
        this.mParent = parent;
    }

    public boolean isConnected() {
        return this.mIC != null;
    }

    public boolean hasSlowInputConnection() {
        return SystemClock.uptimeMillis() - this.mLastSlowInputConnectionTime <= SLOW_INPUTCONNECTION_PERSIST_MS;
    }

    public void onStartInput() {
        this.mLastSlowInputConnectionTime = -SLOW_INPUTCONNECTION_PERSIST_MS;
    }

    private void checkConsistencyForDebug() {
        ExtractedTextRequest extractedTextRequest = new ExtractedTextRequest();
        extractedTextRequest.hintMaxChars = 0;
        extractedTextRequest.hintMaxLines = 0;
        extractedTextRequest.token = 1;
        extractedTextRequest.flags = 0;
        ExtractedText extractedText = this.mIC.getExtractedText(extractedTextRequest, 0);
        CharSequence textBeforeCursor = getTextBeforeCursor(1024, 0);
        StringBuilder sbAppend = new StringBuilder(this.mCommittedTextBeforeComposingText).append((CharSequence) this.mComposingText);
        if (extractedText == null || textBeforeCursor == null) {
            return;
        }
        int iMin = Math.min(textBeforeCursor.length(), sbAppend.length());
        if (sbAppend.length() > iMin) {
            sbAppend.delete(0, sbAppend.length() - iMin);
        }
        String string = textBeforeCursor.length() <= iMin ? textBeforeCursor.toString() : textBeforeCursor.subSequence(textBeforeCursor.length() - iMin, textBeforeCursor.length()).toString();
        if (extractedText.selectionStart != this.mExpectedSelStart || !string.equals(sbAppend.toString())) {
            ((LatinIME) this.mParent).debugDumpStateAndCrashWithException("Expected selection start = " + this.mExpectedSelStart + "\nActual selection start = " + extractedText.selectionStart + "\nExpected text = " + sbAppend.length() + " " + ((Object) sbAppend) + "\nActual text = " + string.length() + " " + string);
        } else {
            Log.e(TAG, DebugLogUtils.getStackTrace(2));
            Log.e(TAG, "Exp <> Actual : " + this.mExpectedSelStart + " <> " + extractedText.selectionStart);
        }
    }

    public void beginBatchEdit() {
        int i = this.mNestLevel + 1;
        this.mNestLevel = i;
        if (i == 1) {
            this.mIC = this.mParent.getCurrentInputConnection();
            if (isConnected()) {
                this.mIC.beginBatchEdit();
                return;
            }
            return;
        }
        Log.e(TAG, "Nest level too deep : " + this.mNestLevel);
    }

    public void endBatchEdit() {
        if (this.mNestLevel <= 0) {
            Log.e(TAG, "Batch edit not in progress!");
        }
        int i = this.mNestLevel - 1;
        this.mNestLevel = i;
        if (i == 0 && isConnected()) {
            this.mIC.endBatchEdit();
        }
    }

    public boolean resetCachesUponCursorMoveAndReturnSuccess(final int newSelStart, final int newSelEnd, final boolean shouldFinishComposition) {
        this.mExpectedSelStart = newSelStart;
        this.mExpectedSelEnd = newSelEnd;
        this.mComposingText.setLength(0);
        if (!reloadTextCache()) {
            Log.d(TAG, "Will try to retrieve text later.");
            return false;
        }
        if (!isConnected() || !shouldFinishComposition) {
            return true;
        }
        this.mIC.finishComposingText();
        return true;
    }

    private boolean reloadTextCache() {
        this.mCommittedTextBeforeComposingText.setLength(0);
        this.mIC = this.mParent.getCurrentInputConnection();
        CharSequence textBeforeCursorAndDetectLaggyConnection = getTextBeforeCursorAndDetectLaggyConnection(3, SLOW_INPUT_CONNECTION_ON_FULL_RELOAD_MS, 1024, 0);
        if (textBeforeCursorAndDetectLaggyConnection == null) {
            this.mExpectedSelStart = -1;
            this.mExpectedSelEnd = -1;
            Log.e(TAG, "Unable to connect to the editor to retrieve text.");
            return false;
        }
        this.mCommittedTextBeforeComposingText.append(textBeforeCursorAndDetectLaggyConnection);
        return true;
    }

    private void checkBatchEdit() {
        if (this.mNestLevel != 1) {
            Log.e(TAG, "Batch edit level incorrect : " + this.mNestLevel);
            Log.e(TAG, DebugLogUtils.getStackTrace(4));
        }
    }

    public void finishComposingText() {
        this.mCommittedTextBeforeComposingText.append((CharSequence) this.mComposingText);
        this.mComposingText.setLength(0);
        if (isConnected()) {
            this.mIC.finishComposingText();
        }
    }

    public void commitText(final CharSequence text, final int newCursorPosition) {
        this.mCommittedTextBeforeComposingText.append(text);
        int length = this.mExpectedSelStart + (text.length() - this.mComposingText.length());
        this.mExpectedSelStart = length;
        this.mExpectedSelEnd = length;
        this.mComposingText.setLength(0);
        if (isConnected()) {
            this.mTempObjectForCommitText.clear();
            this.mTempObjectForCommitText.append(text);
            for (CharacterStyle characterStyle : (CharacterStyle[]) this.mTempObjectForCommitText.getSpans(0, text.length(), CharacterStyle.class)) {
                int spanStart = this.mTempObjectForCommitText.getSpanStart(characterStyle);
                int spanEnd = this.mTempObjectForCommitText.getSpanEnd(characterStyle);
                int spanFlags = this.mTempObjectForCommitText.getSpanFlags(characterStyle);
                if (spanEnd > 0 && spanEnd < this.mTempObjectForCommitText.length()) {
                    char cCharAt = this.mTempObjectForCommitText.charAt(spanEnd - 1);
                    char cCharAt2 = this.mTempObjectForCommitText.charAt(spanEnd);
                    if (UnicodeSurrogate.isLowSurrogate(cCharAt) && UnicodeSurrogate.isHighSurrogate(cCharAt2)) {
                        this.mTempObjectForCommitText.setSpan(characterStyle, spanStart, spanEnd + 1, spanFlags);
                    }
                }
            }
            this.mIC.commitText(this.mTempObjectForCommitText, newCursorPosition);
        }
    }

    @Nullable
    public CharSequence getSelectedText(final int flags) {
        if (isConnected()) {
            return this.mIC.getSelectedText(flags);
        }
        return null;
    }

    public boolean canDeleteCharacters() {
        return this.mExpectedSelStart > 0;
    }

    public int getCursorCapsMode(final int inputType, final SpacingAndPunctuations spacingAndPunctuations, final boolean hasSpaceBefore) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (!isConnected()) {
            return 0;
        }
        if (!TextUtils.isEmpty(this.mComposingText)) {
            return hasSpaceBefore ? inputType & 12288 : inputType & 4096;
        }
        if (TextUtils.isEmpty(this.mCommittedTextBeforeComposingText) && this.mExpectedSelStart != 0 && !reloadTextCache()) {
            Log.w(TAG, "Unable to connect to the editor. Setting caps mode without knowing text.");
        }
        return CapsModeUtils.getCapsMode(this.mCommittedTextBeforeComposingText.toString(), inputType, spacingAndPunctuations, hasSpaceBefore);
    }

    public int getCodePointBeforeCursor() {
        int length = this.mCommittedTextBeforeComposingText.length();
        if (length < 1) {
            return -1;
        }
        return Character.codePointBefore(this.mCommittedTextBeforeComposingText, length);
    }

    public CharSequence getTextBeforeCursor(final int n, final int flags) {
        int length = this.mCommittedTextBeforeComposingText.length() + this.mComposingText.length();
        int i = this.mExpectedSelStart;
        if (-1 != i && (length >= n || length >= i)) {
            StringBuilder sb = new StringBuilder(this.mCommittedTextBeforeComposingText);
            sb.append(this.mComposingText.toString());
            if (sb.length() > n) {
                sb.delete(0, sb.length() - n);
            }
            return sb;
        }
        return getTextBeforeCursorAndDetectLaggyConnection(0, SLOW_INPUT_CONNECTION_ON_PARTIAL_RELOAD_MS, n, flags);
    }

    private CharSequence getTextBeforeCursorAndDetectLaggyConnection(final int operation, final long timeout, final int n, final int flags) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (!isConnected()) {
            return null;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        CharSequence textBeforeCursor = this.mIC.getTextBeforeCursor(n, flags);
        detectLaggyConnection(operation, timeout, jUptimeMillis);
        return textBeforeCursor;
    }

    public CharSequence getTextAfterCursor(final int n, final int flags) {
        return getTextAfterCursorAndDetectLaggyConnection(1, SLOW_INPUT_CONNECTION_ON_PARTIAL_RELOAD_MS, n, flags);
    }

    private CharSequence getTextAfterCursorAndDetectLaggyConnection(final int operation, final long timeout, final int n, final int flags) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (!isConnected()) {
            return null;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        CharSequence textAfterCursor = this.mIC.getTextAfterCursor(n, flags);
        detectLaggyConnection(operation, timeout, jUptimeMillis);
        return textAfterCursor;
    }

    private void detectLaggyConnection(final int operation, final long timeout, final long startTime) {
        long jUptimeMillis = SystemClock.uptimeMillis() - startTime;
        if (jUptimeMillis >= timeout) {
            Log.w(TAG, "Slow InputConnection: " + OPERATION_NAMES[operation] + " took " + jUptimeMillis + " ms.");
            StatsUtils.onInputConnectionLaggy(operation, jUptimeMillis);
            this.mLastSlowInputConnectionTime = SystemClock.uptimeMillis();
        }
    }

    public void deleteTextBeforeCursor(final int beforeLength) {
        int length = this.mComposingText.length() - beforeLength;
        if (length >= 0) {
            this.mComposingText.setLength(length);
        } else {
            this.mComposingText.setLength(0);
            this.mCommittedTextBeforeComposingText.setLength(Math.max(this.mCommittedTextBeforeComposingText.length() + length, 0));
        }
        int i = this.mExpectedSelStart;
        if (i > beforeLength) {
            this.mExpectedSelStart = i - beforeLength;
            this.mExpectedSelEnd -= beforeLength;
        } else {
            this.mExpectedSelEnd -= i;
            this.mExpectedSelStart = 0;
        }
        if (isConnected()) {
            this.mIC.deleteSurroundingText(beforeLength, 0);
        }
    }

    public void performEditorAction(final int actionId) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (isConnected()) {
            this.mIC.performEditorAction(actionId);
        }
    }

    public void sendKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 0) {
                if (keyCode == 66) {
                    this.mCommittedTextBeforeComposingText.append("\n");
                    int i = this.mExpectedSelStart + 1;
                    this.mExpectedSelStart = i;
                    this.mExpectedSelEnd = i;
                } else if (keyCode == 67) {
                    if (this.mComposingText.length() == 0) {
                        if (this.mCommittedTextBeforeComposingText.length() > 0) {
                            this.mCommittedTextBeforeComposingText.delete(r0.length() - 1, this.mCommittedTextBeforeComposingText.length());
                        }
                    } else {
                        this.mComposingText.delete(r0.length() - 1, this.mComposingText.length());
                    }
                    int i2 = this.mExpectedSelStart;
                    if (i2 > 0 && i2 == this.mExpectedSelEnd) {
                        this.mExpectedSelStart = i2 - 1;
                    }
                    this.mExpectedSelEnd = this.mExpectedSelStart;
                } else {
                    String strNewSingleCodePointString = StringUtils.newSingleCodePointString(keyEvent.getUnicodeChar());
                    this.mCommittedTextBeforeComposingText.append(strNewSingleCodePointString);
                    int length = this.mExpectedSelStart + strNewSingleCodePointString.length();
                    this.mExpectedSelStart = length;
                    this.mExpectedSelEnd = length;
                }
            } else if (keyEvent.getCharacters() != null) {
                this.mCommittedTextBeforeComposingText.append(keyEvent.getCharacters());
                int length2 = this.mExpectedSelStart + keyEvent.getCharacters().length();
                this.mExpectedSelStart = length2;
                this.mExpectedSelEnd = length2;
            }
        }
        if (isConnected()) {
            this.mIC.sendKeyEvent(keyEvent);
        }
    }

    public void setComposingRegion(final int start, final int end) {
        CharSequence textBeforeCursor = getTextBeforeCursor((end - start) + 1024, 0);
        this.mCommittedTextBeforeComposingText.setLength(0);
        if (!TextUtils.isEmpty(textBeforeCursor)) {
            int iMax = Math.max(textBeforeCursor.length() - (this.mExpectedSelStart - start), 0);
            this.mComposingText.append(textBeforeCursor.subSequence(iMax, textBeforeCursor.length()));
            this.mCommittedTextBeforeComposingText.append(textBeforeCursor.subSequence(0, iMax));
        }
        if (isConnected()) {
            this.mIC.setComposingRegion(start, end);
        }
    }

    public void setComposingText(final CharSequence text, final int newCursorPosition) {
        int length = this.mExpectedSelStart + (text.length() - this.mComposingText.length());
        this.mExpectedSelStart = length;
        this.mExpectedSelEnd = length;
        this.mComposingText.setLength(0);
        this.mComposingText.append(text);
        if (isConnected()) {
            this.mIC.setComposingText(text, newCursorPosition);
        }
    }

    public boolean setSelection(final int start, final int end) {
        if (start < 0 || end < 0) {
            return false;
        }
        this.mExpectedSelStart = start;
        this.mExpectedSelEnd = end;
        if (!isConnected() || this.mIC.setSelection(start, end)) {
            return reloadTextCache();
        }
        return false;
    }

    public void commitCorrection(final CorrectionInfo correctionInfo) {
        if (isConnected()) {
            this.mIC.commitCorrection(correctionInfo);
        }
    }

    public void commitCompletion(final CompletionInfo completionInfo) {
        CharSequence text = completionInfo.getText();
        if (text == null) {
            text = "";
        }
        this.mCommittedTextBeforeComposingText.append(text);
        int length = this.mExpectedSelStart + (text.length() - this.mComposingText.length());
        this.mExpectedSelStart = length;
        this.mExpectedSelEnd = length;
        this.mComposingText.setLength(0);
        if (isConnected()) {
            this.mIC.commitCompletion(completionInfo);
        }
    }

    @Nonnull
    public NgramContext getNgramContextFromNthPreviousWord(final SpacingAndPunctuations spacingAndPunctuations, final int n) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (!isConnected()) {
            return NgramContext.EMPTY_PREV_WORDS_INFO;
        }
        return NgramContextUtils.getNgramContextFromNthPreviousWord(getTextBeforeCursor(40, 0), spacingAndPunctuations, n);
    }

    private static boolean isPartOfCompositionForScript(final int codePoint, final SpacingAndPunctuations spacingAndPunctuations, final int scriptId) {
        return spacingAndPunctuations.isWordConnector(codePoint) || (!spacingAndPunctuations.isWordSeparator(codePoint) && ScriptUtils.isLetterPartOfScript(codePoint, scriptId));
    }

    public TextRange getWordRangeAtCursor(final SpacingAndPunctuations spacingAndPunctuations, final int scriptId) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (!isConnected()) {
            return null;
        }
        CharSequence textBeforeCursorAndDetectLaggyConnection = getTextBeforeCursorAndDetectLaggyConnection(2, SLOW_INPUT_CONNECTION_ON_PARTIAL_RELOAD_MS, 40, 1);
        CharSequence textAfterCursorAndDetectLaggyConnection = getTextAfterCursorAndDetectLaggyConnection(2, SLOW_INPUT_CONNECTION_ON_PARTIAL_RELOAD_MS, 40, 1);
        if (textBeforeCursorAndDetectLaggyConnection == null || textAfterCursorAndDetectLaggyConnection == null) {
            return null;
        }
        int length = textBeforeCursorAndDetectLaggyConnection.length();
        while (length > 0) {
            int iCodePointBefore = Character.codePointBefore(textBeforeCursorAndDetectLaggyConnection, length);
            if (!isPartOfCompositionForScript(iCodePointBefore, spacingAndPunctuations, scriptId)) {
                break;
            }
            length--;
            if (Character.isSupplementaryCodePoint(iCodePointBefore)) {
                length--;
            }
        }
        int i = -1;
        while (true) {
            i++;
            if (i >= textAfterCursorAndDetectLaggyConnection.length()) {
                break;
            }
            int iCodePointAt = Character.codePointAt(textAfterCursorAndDetectLaggyConnection, i);
            if (!isPartOfCompositionForScript(iCodePointAt, spacingAndPunctuations, scriptId)) {
                break;
            }
            if (Character.isSupplementaryCodePoint(iCodePointAt)) {
                i++;
            }
        }
        return new TextRange(SpannableStringUtils.concatWithNonParagraphSuggestionSpansOnly(textBeforeCursorAndDetectLaggyConnection, textAfterCursorAndDetectLaggyConnection), length, textBeforeCursorAndDetectLaggyConnection.length() + i, textBeforeCursorAndDetectLaggyConnection.length(), SpannableStringUtils.hasUrlSpans(textBeforeCursorAndDetectLaggyConnection, length, textBeforeCursorAndDetectLaggyConnection.length()) || SpannableStringUtils.hasUrlSpans(textAfterCursorAndDetectLaggyConnection, 0, i));
    }

    public boolean isCursorTouchingWord(final SpacingAndPunctuations spacingAndPunctuations, boolean checkTextAfter) {
        if (checkTextAfter && isCursorFollowedByWordCharacter(spacingAndPunctuations)) {
            return true;
        }
        String string = this.mCommittedTextBeforeComposingText.toString();
        int length = string.length();
        int iCodePointBefore = length == 0 ? -1 : string.codePointBefore(length);
        if (spacingAndPunctuations.isWordConnector(iCodePointBefore)) {
            int iCharCount = length - Character.charCount(iCodePointBefore);
            iCodePointBefore = iCharCount == 0 ? -1 : string.codePointBefore(iCharCount);
        }
        return (-1 == iCodePointBefore || spacingAndPunctuations.isWordSeparator(iCodePointBefore) || spacingAndPunctuations.isWordConnector(iCodePointBefore)) ? false : true;
    }

    public boolean isCursorFollowedByWordCharacter(final SpacingAndPunctuations spacingAndPunctuations) {
        CharSequence textAfterCursor = getTextAfterCursor(1, 0);
        if (TextUtils.isEmpty(textAfterCursor)) {
            return false;
        }
        int iCodePointAt = Character.codePointAt(textAfterCursor, 0);
        return (spacingAndPunctuations.isWordSeparator(iCodePointAt) || spacingAndPunctuations.isWordConnector(iCodePointAt)) ? false : true;
    }

    public void removeTrailingSpace() {
        if (32 == getCodePointBeforeCursor()) {
            deleteTextBeforeCursor(1);
        }
    }

    public boolean sameAsTextBeforeCursor(final CharSequence text) {
        return TextUtils.equals(text, getTextBeforeCursor(text.length(), 0));
    }

    public boolean revertDoubleSpacePeriod(final SpacingAndPunctuations spacingAndPunctuations) {
        if (!TextUtils.equals(spacingAndPunctuations.mSentenceSeparatorAndSpace, getTextBeforeCursor(2, 0))) {
            Log.d(TAG, "Tried to revert double-space combo but we didn't find \"" + spacingAndPunctuations.mSentenceSeparatorAndSpace + "\" just before the cursor.");
            return false;
        }
        deleteTextBeforeCursor(2);
        commitText(" ", 1);
        return true;
    }

    public boolean revertSwapPunctuation() {
        CharSequence textBeforeCursor = getTextBeforeCursor(2, 0);
        if (TextUtils.isEmpty(textBeforeCursor) || ' ' != textBeforeCursor.charAt(1)) {
            Log.d(TAG, "Tried to revert a swap of punctuation but we didn't find a space just before the cursor.");
            return false;
        }
        deleteTextBeforeCursor(2);
        commitText(" " + ((Object) textBeforeCursor.subSequence(0, 1)), 1);
        return true;
    }

    public boolean isBelatedExpectedUpdate(final int oldSelStart, final int newSelStart, final int oldSelEnd, final int newSelEnd) {
        int i = this.mExpectedSelStart;
        if (i == newSelStart && this.mExpectedSelEnd == newSelEnd) {
            return true;
        }
        if (i == oldSelStart && this.mExpectedSelEnd == oldSelEnd && (oldSelStart != newSelStart || oldSelEnd != newSelEnd)) {
            return false;
        }
        return newSelStart == newSelEnd && (newSelStart - oldSelStart) * (i - newSelStart) >= 0 && (newSelEnd - oldSelEnd) * (this.mExpectedSelEnd - newSelEnd) >= 0;
    }

    public boolean textBeforeCursorLooksLikeURL() {
        return StringUtils.lastPartLooksLikeURL(this.mCommittedTextBeforeComposingText);
    }

    public boolean isInsideDoubleQuoteOrAfterDigit() {
        return StringUtils.isInsideDoubleQuoteOrAfterDigit(this.mCommittedTextBeforeComposingText);
    }

    public void tryFixLyingCursorPosition() {
        this.mIC = this.mParent.getCurrentInputConnection();
        CharSequence textBeforeCursor = getTextBeforeCursor(1024, 0);
        CharSequence selectedText = isConnected() ? this.mIC.getSelectedText(0) : null;
        if (textBeforeCursor == null || (!TextUtils.isEmpty(selectedText) && this.mExpectedSelEnd == this.mExpectedSelStart)) {
            this.mExpectedSelEnd = -1;
            this.mExpectedSelStart = -1;
            return;
        }
        int length = textBeforeCursor.length();
        if (length < 1024) {
            int i = this.mExpectedSelStart;
            if (length > i || i < 1024) {
                int i2 = this.mExpectedSelEnd;
                boolean z = i == i2;
                this.mExpectedSelStart = length;
                if (z || length > i2) {
                    this.mExpectedSelEnd = length;
                }
            }
        }
    }

    @Override // com.android.inputmethod.latin.inputlogic.PrivateCommandPerformer
    public boolean performPrivateCommand(final String action, final Bundle data) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (isConnected()) {
            return this.mIC.performPrivateCommand(action, data);
        }
        return false;
    }

    public int getExpectedSelectionStart() {
        return this.mExpectedSelStart;
    }

    public int getExpectedSelectionEnd() {
        return this.mExpectedSelEnd;
    }

    public boolean hasSelection() {
        return this.mExpectedSelEnd != this.mExpectedSelStart;
    }

    public boolean isCursorPositionKnown() {
        return -1 != this.mExpectedSelStart;
    }

    public void maybeMoveTheCursorAroundAndRestoreToWorkaroundABug() {
        if (Build.VERSION.SDK_INT < 16) {
            int i = this.mExpectedSelStart;
            if (i > 0) {
                this.mIC.setSelection(i - 1, i - 1);
            } else {
                this.mIC.setSelection(i + 1, i + 1);
            }
            this.mIC.setSelection(this.mExpectedSelStart, this.mExpectedSelEnd);
        }
    }

    public boolean requestCursorUpdates(final boolean enableMonitor, final boolean requestImmediateCallback) {
        this.mIC = this.mParent.getCurrentInputConnection();
        if (isConnected()) {
            return InputConnectionCompatUtils.requestCursorUpdates(this.mIC, enableMonitor, requestImmediateCallback);
        }
        return false;
    }
}
