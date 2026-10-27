package com.android.inputmethod.latin.inputlogic;

import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.SuggestionSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import com.android.inputmethod.compat.SuggestionSpanUtils;
import com.android.inputmethod.event.Event;
import com.android.inputmethod.event.InputTransaction;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.latin.Dictionary;
import com.android.inputmethod.latin.DictionaryFacilitator;
import com.android.inputmethod.latin.LastComposedWord;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.RichInputConnection;
import com.android.inputmethod.latin.Suggest;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.WordComposer;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.InputPointers;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.settings.SettingsValues;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import com.android.inputmethod.latin.settings.SpacingAndPunctuations;
import com.android.inputmethod.latin.suggestions.SuggestionStripViewAccessor;
import com.android.inputmethod.latin.utils.AsyncResultHolder;
import com.android.inputmethod.latin.utils.InputTypeUtils;
import com.android.inputmethod.latin.utils.RecapitalizeStatus;
import com.android.inputmethod.latin.utils.StatsUtils;
import com.android.inputmethod.latin.utils.TextRange;
import com.byd.ime.subtype.SubtypeManager;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class InputLogic {
    private static final String TAG = "InputLogic";
    public final RichInputConnection mConnection;
    private int mDeleteCount;
    private final DictionaryFacilitator mDictionaryFacilitator;
    private long mDoubleSpacePeriodCountdownStart;
    private String mEnteredText;
    private InputLogicHandler mInputLogicHandler;
    private boolean mIsAutoCorrectionIndicatorOn;
    private long mLastKeyTime;
    final LatinIME mLatinIME;
    private int mSpaceState;
    public final Suggest mSuggest;
    private final SuggestionStripViewAccessor mSuggestionStripViewAccessor;
    public SuggestedWords mSuggestedWords = SuggestedWords.getEmptyInstance();
    public LastComposedWord mLastComposedWord = LastComposedWord.NOT_A_COMPOSED_WORD;
    private final RecapitalizeStatus mRecapitalizeStatus = new RecapitalizeStatus();
    public final TreeSet<Long> mCurrentlyPressedHardwareKeys = new TreeSet<>();
    private String mWordBeingCorrectedByCursor = null;
    private int mAutoCommitSequenceNumber = 1;
    final WordComposer mWordComposer = new WordComposer();

    public InputLogic(final LatinIME latinIME, final SuggestionStripViewAccessor suggestionStripViewAccessor, final DictionaryFacilitator dictionaryFacilitator) {
        this.mInputLogicHandler = InputLogicHandler.NULL_HANDLER;
        this.mLatinIME = latinIME;
        this.mSuggestionStripViewAccessor = suggestionStripViewAccessor;
        this.mConnection = new RichInputConnection(latinIME);
        this.mInputLogicHandler = InputLogicHandler.NULL_HANDLER;
        this.mSuggest = new Suggest(dictionaryFacilitator);
        this.mDictionaryFacilitator = dictionaryFacilitator;
    }

    public void startInput(final String combiningSpec, final SettingsValues settingsValues) {
        this.mEnteredText = null;
        this.mWordBeingCorrectedByCursor = null;
        this.mConnection.onStartInput();
        if (!this.mWordComposer.getTypedWord().isEmpty()) {
            StatsUtils.onWordCommitUserTyped(this.mWordComposer.getTypedWord(), this.mWordComposer.isBatchMode());
        }
        this.mWordComposer.restartCombining(combiningSpec);
        resetComposingState(true);
        this.mDeleteCount = 0;
        this.mSpaceState = 0;
        this.mRecapitalizeStatus.disable();
        this.mCurrentlyPressedHardwareKeys.clear();
        this.mSuggestedWords = SuggestedWords.getEmptyInstance();
        this.mConnection.tryFixLyingCursorPosition();
        cancelDoubleSpacePeriodCountdown();
        InputLogicHandler inputLogicHandler = InputLogicHandler.NULL_HANDLER;
        InputLogicHandler inputLogicHandler2 = this.mInputLogicHandler;
        if (inputLogicHandler == inputLogicHandler2) {
            this.mInputLogicHandler = new InputLogicHandler(this.mLatinIME, this);
        } else {
            inputLogicHandler2.reset();
        }
        if (settingsValues.mShouldShowLxxSuggestionUi) {
            this.mConnection.requestCursorUpdates(true, true);
        }
    }

    public void onSubtypeChanged(final String combiningSpec, final SettingsValues settingsValues) {
        finishInput();
        startInput(combiningSpec, settingsValues);
    }

    public void onOrientationChange(final SettingsValues settingsValues) {
        if (this.mWordComposer.isComposingWord()) {
            this.mConnection.beginBatchEdit();
            commitTyped(settingsValues, "");
            this.mConnection.endBatchEdit();
        }
    }

    public void finishInput() {
        if (this.mWordComposer.isComposingWord()) {
            this.mConnection.finishComposingText();
            StatsUtils.onWordCommitUserTyped(this.mWordComposer.getTypedWord(), this.mWordComposer.isBatchMode());
        }
        resetComposingState(true);
        this.mInputLogicHandler.reset();
    }

    public void recycle() {
        InputLogicHandler inputLogicHandler = this.mInputLogicHandler;
        this.mInputLogicHandler = InputLogicHandler.NULL_HANDLER;
        inputLogicHandler.destroy();
        this.mDictionaryFacilitator.closeDictionaries();
    }

    public InputTransaction onTextInput(final SettingsValues settingsValues, final Event event, final int keyboardShiftMode, final LatinIME.UIHandler handler) {
        String string = event.getTextToCommit().toString();
        InputTransaction inputTransaction = new InputTransaction(settingsValues, event, SystemClock.uptimeMillis(), this.mSpaceState, getActualCapsMode(settingsValues, keyboardShiftMode));
        this.mConnection.beginBatchEdit();
        if (this.mWordComposer.isComposingWord()) {
            commitCurrentAutoCorrection(settingsValues, string, handler);
        } else {
            resetComposingState(true);
        }
        handler.postUpdateSuggestionStrip(1);
        String strPerformSpecificTldProcessingOnTextInput = performSpecificTldProcessingOnTextInput(string);
        if (4 == this.mSpaceState) {
            insertAutomaticSpaceIfOptionsAndTextAllow(settingsValues);
        }
        this.mConnection.commitText(strPerformSpecificTldProcessingOnTextInput, 1);
        StatsUtils.onWordCommitUserTyped(this.mEnteredText, this.mWordComposer.isBatchMode());
        this.mConnection.endBatchEdit();
        this.mSpaceState = 0;
        this.mEnteredText = strPerformSpecificTldProcessingOnTextInput;
        this.mWordBeingCorrectedByCursor = null;
        inputTransaction.setDidAffectContents();
        inputTransaction.requireShiftUpdate(1);
        return inputTransaction;
    }

    public InputTransaction onPickSuggestionManually(final SettingsValues settingsValues, final SuggestedWords.SuggestedWordInfo suggestionInfo, final int keyboardShiftState, final int currentKeyboardScriptId, final LatinIME.UIHandler handler) {
        SuggestedWords suggestedWords = this.mSuggestedWords;
        String str = suggestionInfo.mWord;
        if (str.length() == 1 && suggestedWords.isPunctuationSuggestions()) {
            StatsUtils.onPickSuggestionManually(this.mSuggestedWords, suggestionInfo, this.mDictionaryFacilitator);
            return onCodeInput(settingsValues, Event.createPunctuationSuggestionPickedEvent(suggestionInfo), keyboardShiftState, currentKeyboardScriptId, handler);
        }
        InputTransaction inputTransaction = new InputTransaction(settingsValues, Event.createSuggestionPickedEvent(suggestionInfo), SystemClock.uptimeMillis(), this.mSpaceState, keyboardShiftState);
        inputTransaction.setDidAffectContents();
        this.mConnection.beginBatchEdit();
        if (4 == this.mSpaceState && str.length() > 0 && !this.mWordComposer.isBatchMode()) {
            int iCodePointAt = Character.codePointAt(str, 0);
            if (!settingsValues.isWordSeparator(iCodePointAt) || settingsValues.isUsuallyPrecededBySpace(iCodePointAt)) {
                insertAutomaticSpaceIfOptionsAndTextAllow(settingsValues);
            }
        }
        if (suggestionInfo.isKindOf(6)) {
            this.mSuggestedWords = SuggestedWords.getEmptyInstance();
            this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
            inputTransaction.requireShiftUpdate(1);
            resetComposingState(true);
            this.mConnection.commitCompletion(suggestionInfo.mApplicationSpecifiedCompletionInfo);
            this.mConnection.endBatchEdit();
            return inputTransaction;
        }
        commitChosenWord(settingsValues, str, 1, "");
        this.mConnection.endBatchEdit();
        this.mLastComposedWord.deactivate();
        this.mSpaceState = 4;
        inputTransaction.requireShiftUpdate(1);
        handler.postUpdateSuggestionStrip(0);
        StatsUtils.onPickSuggestionManually(this.mSuggestedWords, suggestionInfo, this.mDictionaryFacilitator);
        StatsUtils.onWordCommitSuggestionPickedManually(suggestionInfo.mWord, this.mWordComposer.isBatchMode());
        return inputTransaction;
    }

    public boolean onUpdateSelection(final int oldSelStart, final int oldSelEnd, final int newSelStart, final int newSelEnd, final SettingsValues settingsValues) {
        if (this.mConnection.isBelatedExpectedUpdate(oldSelStart, newSelStart, oldSelEnd, newSelEnd)) {
            return false;
        }
        this.mSpaceState = 0;
        boolean z = (oldSelStart == newSelStart && oldSelEnd == newSelEnd && this.mWordComposer.isComposingWord()) ? false : true;
        boolean z2 = (oldSelStart == oldSelEnd && newSelStart == newSelEnd) ? false : true;
        int i = newSelStart - oldSelStart;
        if (z2 || !settingsValues.needsToLookupSuggestions() || (z && !this.mWordComposer.moveCursorByAndReturnIfInsideComposingWord(i))) {
            resetEntireInputState(newSelStart, newSelEnd, false);
            if (!TextUtils.isEmpty(this.mWordBeingCorrectedByCursor)) {
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                performAdditionToUserHistoryDictionary(settingsValues, this.mWordBeingCorrectedByCursor, NgramContext.EMPTY_PREV_WORDS_INFO);
            }
        } else {
            this.mConnection.resetCachesUponCursorMoveAndReturnSuccess(newSelStart, newSelEnd, false);
        }
        this.mRecapitalizeStatus.enable();
        this.mLatinIME.mHandler.postResumeSuggestions(true);
        this.mRecapitalizeStatus.stop();
        this.mWordBeingCorrectedByCursor = null;
        return true;
    }

    public InputTransaction onCodeInput(final SettingsValues settingsValues, @Nonnull final Event event, final int keyboardShiftMode, final int currentKeyboardScriptId, final LatinIME.UIHandler handler) {
        this.mWordBeingCorrectedByCursor = null;
        Event eventProcessEvent = this.mWordComposer.processEvent(event);
        InputTransaction inputTransaction = new InputTransaction(settingsValues, eventProcessEvent, SystemClock.uptimeMillis(), this.mSpaceState, getActualCapsMode(settingsValues, keyboardShiftMode));
        if (eventProcessEvent.mKeyCode != -5 || inputTransaction.mTimestamp > this.mLastKeyTime + 200) {
            this.mDeleteCount = 0;
        }
        this.mLastKeyTime = inputTransaction.mTimestamp;
        this.mConnection.beginBatchEdit();
        if (!this.mWordComposer.isComposingWord()) {
            this.mIsAutoCorrectionIndicatorOn = false;
        }
        if (eventProcessEvent.mCodePoint != 32) {
            cancelDoubleSpacePeriodCountdown();
        }
        for (Event event2 = eventProcessEvent; event2 != null; event2 = event2.mNextEvent) {
            if (event2.isConsumed()) {
                handleConsumedEvent(event2, inputTransaction);
            } else if (event2.isFunctionalKeyEvent()) {
                handleFunctionalEvent(event2, inputTransaction, currentKeyboardScriptId, handler);
            } else {
                handleNonFunctionalEvent(event2, inputTransaction, handler);
            }
        }
        if (!this.mConnection.hasSlowInputConnection() && !this.mWordComposer.isComposingWord() && (settingsValues.isWordCodePoint(eventProcessEvent.mCodePoint) || eventProcessEvent.mKeyCode == -5)) {
            this.mWordBeingCorrectedByCursor = getWordAtCursor(settingsValues, currentKeyboardScriptId);
        }
        if (!inputTransaction.didAutoCorrect() && eventProcessEvent.mKeyCode != -1 && eventProcessEvent.mKeyCode != -2 && eventProcessEvent.mKeyCode != -3) {
            this.mLastComposedWord.deactivate();
        }
        if (-5 != eventProcessEvent.mKeyCode) {
            this.mEnteredText = null;
        }
        this.mConnection.endBatchEdit();
        return inputTransaction;
    }

    public void onStartBatchInput(final SettingsValues settingsValues, final KeyboardSwitcher keyboardSwitcher, final LatinIME.UIHandler handler) {
        this.mWordBeingCorrectedByCursor = null;
        this.mInputLogicHandler.onStartBatchInput();
        handler.showGesturePreviewAndSuggestionStrip(SuggestedWords.getEmptyInstance(), false);
        handler.cancelUpdateSuggestionStrip();
        this.mAutoCommitSequenceNumber++;
        this.mConnection.beginBatchEdit();
        if (this.mWordComposer.isComposingWord()) {
            if (this.mWordComposer.isCursorFrontOrMiddleOfComposingWord()) {
                unlearnWord(this.mWordComposer.getTypedWord(), settingsValues, 1);
                resetEntireInputState(this.mConnection.getExpectedSelectionStart(), this.mConnection.getExpectedSelectionEnd(), true);
            } else if (this.mWordComposer.isSingleLetter()) {
                commitCurrentAutoCorrection(settingsValues, "", handler);
            } else {
                commitTyped(settingsValues, "");
            }
        }
        int codePointBeforeCursor = this.mConnection.getCodePointBeforeCursor();
        if (Character.isLetterOrDigit(codePointBeforeCursor) || settingsValues.isUsuallyFollowedBySpace(codePointBeforeCursor)) {
            boolean z = keyboardSwitcher.getKeyboardShiftMode() != getCurrentAutoCapsState(settingsValues);
            this.mSpaceState = 4;
            if (!z) {
                keyboardSwitcher.requestUpdatingShiftState(getCurrentAutoCapsState(settingsValues), getCurrentRecapitalizeState());
            }
        }
        this.mConnection.endBatchEdit();
        this.mWordComposer.setCapitalizedModeAtStartComposingTime(getActualCapsMode(settingsValues, keyboardSwitcher.getKeyboardShiftMode()));
    }

    public void onUpdateBatchInput(final InputPointers batchPointers) {
        this.mInputLogicHandler.onUpdateBatchInput(batchPointers, this.mAutoCommitSequenceNumber);
    }

    public void onEndBatchInput(final InputPointers batchPointers) {
        this.mInputLogicHandler.updateTailBatchInput(batchPointers, this.mAutoCommitSequenceNumber);
        this.mAutoCommitSequenceNumber++;
    }

    public void onCancelBatchInput(final LatinIME.UIHandler handler) {
        this.mInputLogicHandler.onCancelBatchInput();
        handler.showGesturePreviewAndSuggestionStrip(SuggestedWords.getEmptyInstance(), true);
    }

    public void setSuggestedWords(final SuggestedWords suggestedWords) {
        SuggestedWords.SuggestedWordInfo info;
        if (!suggestedWords.isEmpty()) {
            if (suggestedWords.mWillAutoCorrect) {
                info = suggestedWords.getInfo(1);
            } else {
                info = suggestedWords.mTypedWordInfo;
            }
            this.mWordComposer.setAutoCorrection(info);
        }
        this.mSuggestedWords = suggestedWords;
        boolean z = suggestedWords.mWillAutoCorrect;
        if (this.mIsAutoCorrectionIndicatorOn == z || !this.mWordComposer.isComposingWord()) {
            return;
        }
        this.mIsAutoCorrectionIndicatorOn = z;
        setComposingTextInternal(getTextWithUnderline(this.mWordComposer.getTypedWord()), 1);
    }

    private void handleConsumedEvent(final Event event, final InputTransaction inputTransaction) {
        CharSequence textToCommit = event.getTextToCommit();
        if (!TextUtils.isEmpty(textToCommit)) {
            this.mConnection.commitText(textToCommit, 1);
            inputTransaction.setDidAffectContents();
        }
        if (this.mWordComposer.isComposingWord()) {
            setComposingTextInternal(this.mWordComposer.getTypedWord(), 1);
            inputTransaction.setDidAffectContents();
            inputTransaction.setRequiresUpdateSuggestions();
        }
    }

    private void handleFunctionalEvent(final Event event, final InputTransaction inputTransaction, final int currentKeyboardScriptId, final LatinIME.UIHandler handler) {
        switch (event.mKeyCode) {
            case Constants.CODE_ALPHA_FROM_EMOJI /* -14 */:
            case Constants.CODE_SYMBOL_SHIFT /* -13 */:
            case Constants.CODE_EMOJI /* -11 */:
            case Constants.CODE_SHORTCUT /* -7 */:
            case -3:
            case -2:
                return;
            case Constants.CODE_SHIFT_ENTER /* -12 */:
                handleNonSpecialCharacterEvent(Event.createSoftwareKeypressEvent(10, event.mKeyCode, event.mX, event.mY, event.isKeyRepeat()), inputTransaction, handler);
                inputTransaction.setDidAffectContents();
                return;
            case Constants.CODE_LANGUAGE_SWITCH /* -10 */:
                handleLanguageSwitchKey();
                return;
            case -9:
                performEditorAction(7);
                return;
            case Constants.CODE_ACTION_NEXT /* -8 */:
                performEditorAction(5);
                return;
            case Constants.CODE_SETTINGS /* -6 */:
                onSettingsKeyPressed();
                return;
            case Constants.CODE_DELETE /* -5 */:
                handleBackspaceEvent(event, inputTransaction, currentKeyboardScriptId);
                inputTransaction.setDidAffectContents();
                return;
            case -4:
            default:
                throw new RuntimeException("Unknown key code : " + event.mKeyCode);
            case -1:
                performRecapitalization(inputTransaction.mSettingsValues);
                inputTransaction.requireShiftUpdate(1);
                if (this.mSuggestedWords.isPrediction()) {
                    inputTransaction.setRequiresUpdateSuggestions();
                    return;
                }
                return;
        }
    }

    private void handleNonFunctionalEvent(final Event event, final InputTransaction inputTransaction, final LatinIME.UIHandler handler) {
        inputTransaction.setDidAffectContents();
        if (event.mCodePoint == 10) {
            EditorInfo currentInputEditorInfo = getCurrentInputEditorInfo();
            int imeOptionsActionIdFromEditorInfo = InputTypeUtils.getImeOptionsActionIdFromEditorInfo(currentInputEditorInfo);
            if (imeOptionsActionIdFromEditorInfo == 1) {
                imeOptionsActionIdFromEditorInfo = 6;
            }
            if (256 == imeOptionsActionIdFromEditorInfo) {
                performEditorAction(currentInputEditorInfo.actionId);
                return;
            } else if (1 != imeOptionsActionIdFromEditorInfo) {
                performEditorAction(imeOptionsActionIdFromEditorInfo);
                return;
            } else {
                handleNonSpecialCharacterEvent(event, inputTransaction, handler);
                return;
            }
        }
        handleNonSpecialCharacterEvent(event, inputTransaction, handler);
    }

    private void handleNonSpecialCharacterEvent(final Event event, final InputTransaction inputTransaction, final LatinIME.UIHandler handler) {
        int i = event.mCodePoint;
        this.mSpaceState = 0;
        if (inputTransaction.mSettingsValues.isWordSeparator(i) || Character.getType(i) == 28) {
            handleSeparatorEvent(event, inputTransaction, handler);
            return;
        }
        if (4 == inputTransaction.mSpaceState) {
            if (this.mWordComposer.isCursorFrontOrMiddleOfComposingWord()) {
                unlearnWord(this.mWordComposer.getTypedWord(), inputTransaction.mSettingsValues, 1);
                resetEntireInputState(this.mConnection.getExpectedSelectionStart(), this.mConnection.getExpectedSelectionEnd(), true);
            } else {
                commitTyped(inputTransaction.mSettingsValues, "");
            }
        }
        handleNonSeparatorEvent(event, inputTransaction.mSettingsValues, inputTransaction);
    }

    private void handleNonSeparatorEvent(final Event event, final SettingsValues settingsValues, final InputTransaction inputTransaction) {
        int i = event.mCodePoint;
        boolean zIsComposingWord = this.mWordComposer.isComposingWord();
        if (4 == inputTransaction.mSpaceState && !settingsValues.isWordConnector(i)) {
            if (zIsComposingWord) {
                throw new RuntimeException("Should not be composing here");
            }
            insertAutomaticSpaceIfOptionsAndTextAllow(settingsValues);
        }
        if (this.mWordComposer.isCursorFrontOrMiddleOfComposingWord()) {
            unlearnWord(this.mWordComposer.getTypedWord(), inputTransaction.mSettingsValues, 1);
            resetEntireInputState(this.mConnection.getExpectedSelectionStart(), this.mConnection.getExpectedSelectionEnd(), true);
            zIsComposingWord = false;
        }
        if (!zIsComposingWord && settingsValues.isWordCodePoint(i) && settingsValues.needsToLookupSuggestions()) {
            zIsComposingWord = !settingsValues.mSpacingAndPunctuations.isWordConnector(i);
            resetComposingState(false);
        }
        if (zIsComposingWord) {
            this.mWordComposer.applyProcessedEvent(event);
            if (this.mWordComposer.isSingleLetter()) {
                this.mWordComposer.setCapitalizedModeAtStartComposingTime(inputTransaction.mShiftState);
            }
            setComposingTextInternal(getTextWithUnderline(this.mWordComposer.getTypedWord()), 1);
        } else if (tryStripSpaceAndReturnWhetherShouldSwapInstead(event, inputTransaction) && trySwapSwapperAndSpace(event, inputTransaction)) {
            this.mSpaceState = 3;
        } else {
            sendKeyCodePoint(settingsValues, i);
        }
        inputTransaction.setRequiresUpdateSuggestions();
    }

    private void handleSeparatorEvent(final Event event, final InputTransaction inputTransaction, final LatinIME.UIHandler handler) {
        int i = event.mCodePoint;
        SettingsValues settingsValues = inputTransaction.mSettingsValues;
        boolean zIsComposingWord = this.mWordComposer.isComposingWord();
        boolean zIsUsuallyPrecededBySpace = false;
        boolean z = 32 == i && !settingsValues.mSpacingAndPunctuations.mCurrentLanguageHasSpaces && zIsComposingWord;
        if (this.mWordComposer.isCursorFrontOrMiddleOfComposingWord()) {
            unlearnWord(this.mWordComposer.getTypedWord(), inputTransaction.mSettingsValues, 1);
            resetEntireInputState(this.mConnection.getExpectedSelectionStart(), this.mConnection.getExpectedSelectionEnd(), true);
        }
        if (this.mWordComposer.isComposingWord()) {
            if (settingsValues.mAutoCorrectionEnabledPerUserSettings) {
                commitCurrentAutoCorrection(settingsValues, z ? "" : StringUtils.newSingleCodePointString(i), handler);
                inputTransaction.setDidAutoCorrect();
            } else {
                commitTyped(settingsValues, StringUtils.newSingleCodePointString(i));
            }
        }
        boolean zTryStripSpaceAndReturnWhetherShouldSwapInstead = tryStripSpaceAndReturnWhetherShouldSwapInstead(event, inputTransaction);
        boolean z2 = 34 == i && this.mConnection.isInsideDoubleQuoteOrAfterDigit();
        if (4 == inputTransaction.mSpaceState) {
            if (34 == i) {
                zIsUsuallyPrecededBySpace = !z2;
            } else if (!settingsValues.mSpacingAndPunctuations.isClusteringSymbol(i) || !settingsValues.mSpacingAndPunctuations.isClusteringSymbol(this.mConnection.getCodePointBeforeCursor())) {
                zIsUsuallyPrecededBySpace = settingsValues.isUsuallyPrecededBySpace(i);
            }
        }
        if (zIsUsuallyPrecededBySpace) {
            insertAutomaticSpaceIfOptionsAndTextAllow(settingsValues);
        }
        if (tryPerformDoubleSpacePeriod(event, inputTransaction)) {
            this.mSpaceState = 1;
            inputTransaction.setRequiresUpdateSuggestions();
            StatsUtils.onDoubleSpacePeriod();
        } else if (zTryStripSpaceAndReturnWhetherShouldSwapInstead && trySwapSwapperAndSpace(event, inputTransaction)) {
            this.mSpaceState = 2;
            this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
        } else if (32 == i) {
            if (!this.mSuggestedWords.isPunctuationSuggestions()) {
                this.mSpaceState = 3;
            }
            startDoubleSpacePeriodCountdown(inputTransaction);
            if (zIsComposingWord || this.mSuggestedWords.isEmpty()) {
                inputTransaction.setRequiresUpdateSuggestions();
            }
            if (!z) {
                sendKeyCodePoint(settingsValues, i);
            }
        } else {
            if ((4 == inputTransaction.mSpaceState && settingsValues.isUsuallyFollowedBySpace(i)) || (34 == i && z2)) {
                this.mSpaceState = 4;
            }
            sendKeyCodePoint(settingsValues, i);
            this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
        }
        inputTransaction.requireShiftUpdate(1);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void handleBackspaceEvent(Event event, InputTransaction inputTransaction, int i) {
        boolean zUnlearnWordBeingDeleted;
        this.mSpaceState = 0;
        int i2 = 1;
        boolean z = true;
        this.mDeleteCount++;
        inputTransaction.requireShiftUpdate((!event.isKeyRepeat() || this.mConnection.getExpectedSelectionStart() <= 0) ? 1 : 2);
        if (this.mWordComposer.isCursorFrontOrMiddleOfComposingWord()) {
            unlearnWord(this.mWordComposer.getTypedWord(), inputTransaction.mSettingsValues, 1);
            resetEntireInputState(this.mConnection.getExpectedSelectionStart(), this.mConnection.getExpectedSelectionEnd(), true);
        }
        if (this.mWordComposer.isComposingWord()) {
            if (this.mWordComposer.isBatchMode()) {
                String typedWord = this.mWordComposer.getTypedWord();
                this.mWordComposer.reset();
                this.mWordComposer.setRejectedBatchModeSuggestion(typedWord);
                if (!TextUtils.isEmpty(typedWord)) {
                    unlearnWord(typedWord, inputTransaction.mSettingsValues, 2);
                }
                StatsUtils.onBackspaceWordDelete(typedWord.length());
            } else {
                this.mWordComposer.applyProcessedEvent(event);
                StatsUtils.onBackspacePressed(1);
            }
            if (this.mWordComposer.isComposingWord()) {
                setComposingTextInternal(getTextWithUnderline(this.mWordComposer.getTypedWord()), 1);
            } else {
                this.mConnection.commitText("", 1);
            }
            inputTransaction.setRequiresUpdateSuggestions();
            return;
        }
        if (this.mLastComposedWord.canRevertCommit()) {
            String str = this.mLastComposedWord.mTypedWord;
            revertCommit(inputTransaction, inputTransaction.mSettingsValues);
            StatsUtils.onRevertAutoCorrect();
            StatsUtils.onWordCommitUserTyped(str, this.mWordComposer.isBatchMode());
            if (inputTransaction.mSettingsValues.isSuggestionsEnabledPerUserSettings() && inputTransaction.mSettingsValues.mSpacingAndPunctuations.mCurrentLanguageHasSpaces && !this.mConnection.isCursorFollowedByWordCharacter(inputTransaction.mSettingsValues.mSpacingAndPunctuations)) {
                restartSuggestionsOnWordTouchedByCursor(inputTransaction.mSettingsValues, false, i);
                return;
            }
            return;
        }
        String str2 = this.mEnteredText;
        if (str2 != null && this.mConnection.sameAsTextBeforeCursor(str2)) {
            this.mConnection.deleteTextBeforeCursor(this.mEnteredText.length());
            StatsUtils.onDeleteMultiCharInput(this.mEnteredText.length());
            this.mEnteredText = null;
            return;
        }
        if (1 == inputTransaction.mSpaceState) {
            cancelDoubleSpacePeriodCountdown();
            if (this.mConnection.revertDoubleSpacePeriod(inputTransaction.mSettingsValues.mSpacingAndPunctuations)) {
                inputTransaction.setRequiresUpdateSuggestions();
                this.mWordComposer.setCapitalizedModeAtStartComposingTime(0);
                StatsUtils.onRevertDoubleSpacePeriod();
                return;
            }
        } else if (2 == inputTransaction.mSpaceState && this.mConnection.revertSwapPunctuation()) {
            StatsUtils.onRevertSwapPunctuation();
            return;
        }
        if (this.mConnection.hasSelection()) {
            CharSequence selectedText = this.mConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                z = false;
            } else {
                unlearnWord(selectedText.toString(), inputTransaction.mSettingsValues, 1);
            }
            int expectedSelectionEnd = this.mConnection.getExpectedSelectionEnd() - this.mConnection.getExpectedSelectionStart();
            RichInputConnection richInputConnection = this.mConnection;
            richInputConnection.setSelection(richInputConnection.getExpectedSelectionEnd(), this.mConnection.getExpectedSelectionEnd());
            this.mConnection.deleteTextBeforeCursor(expectedSelectionEnd);
            StatsUtils.onBackspaceSelectedText(expectedSelectionEnd);
        } else if (inputTransaction.mSettingsValues.isBeforeJellyBean() || inputTransaction.mSettingsValues.mInputAttributes.isTypeNull() || -1 == this.mConnection.getExpectedSelectionEnd()) {
            sendDownUpKeyEvent(67);
            if (this.mDeleteCount > 20) {
                zUnlearnWordBeingDeleted = unlearnWordBeingDeleted(inputTransaction.mSettingsValues, i) | false;
                sendDownUpKeyEvent(67);
                i2 = 2;
            } else {
                zUnlearnWordBeingDeleted = false;
            }
            StatsUtils.onBackspacePressed(i2);
            z = zUnlearnWordBeingDeleted;
        } else {
            int codePointBeforeCursor = this.mConnection.getCodePointBeforeCursor();
            if (codePointBeforeCursor == -1) {
                this.mConnection.deleteTextBeforeCursor(1);
                return;
            }
            int i3 = Character.isSupplementaryCodePoint(codePointBeforeCursor) ? 2 : 1;
            this.mConnection.deleteTextBeforeCursor(i3);
            if (this.mDeleteCount > 20) {
                boolean zUnlearnWordBeingDeleted2 = unlearnWordBeingDeleted(inputTransaction.mSettingsValues, i) | false;
                int codePointBeforeCursor2 = this.mConnection.getCodePointBeforeCursor();
                if (codePointBeforeCursor2 != -1) {
                    int i4 = Character.isSupplementaryCodePoint(codePointBeforeCursor2) ? 2 : 1;
                    this.mConnection.deleteTextBeforeCursor(i4);
                    i3 += i4;
                }
                z = zUnlearnWordBeingDeleted2;
            } else {
                z = false;
            }
            StatsUtils.onBackspacePressed(i3);
        }
        if (!z) {
            unlearnWordBeingDeleted(inputTransaction.mSettingsValues, i);
        }
        if (this.mConnection.hasSlowInputConnection()) {
            this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
        } else if (inputTransaction.mSettingsValues.isSuggestionsEnabledPerUserSettings() && inputTransaction.mSettingsValues.mSpacingAndPunctuations.mCurrentLanguageHasSpaces && !this.mConnection.isCursorFollowedByWordCharacter(inputTransaction.mSettingsValues.mSpacingAndPunctuations)) {
            restartSuggestionsOnWordTouchedByCursor(inputTransaction.mSettingsValues, false, i);
        }
    }

    String getWordAtCursor(final SettingsValues settingsValues, final int currentKeyboardScriptId) {
        TextRange wordRangeAtCursor;
        return (this.mConnection.hasSelection() || !settingsValues.isSuggestionsEnabledPerUserSettings() || !settingsValues.mSpacingAndPunctuations.mCurrentLanguageHasSpaces || (wordRangeAtCursor = this.mConnection.getWordRangeAtCursor(settingsValues.mSpacingAndPunctuations, currentKeyboardScriptId)) == null) ? "" : wordRangeAtCursor.mWord.toString();
    }

    boolean unlearnWordBeingDeleted(final SettingsValues settingsValues, final int currentKeyboardScriptId) {
        if (this.mConnection.hasSlowInputConnection()) {
            Log.w(TAG, "Skipping unlearning due to slow InputConnection.");
            return false;
        }
        if (!this.mConnection.isCursorFollowedByWordCharacter(settingsValues.mSpacingAndPunctuations)) {
            String wordAtCursor = getWordAtCursor(settingsValues, currentKeyboardScriptId);
            if (!TextUtils.isEmpty(wordAtCursor)) {
                unlearnWord(wordAtCursor, settingsValues, 1);
                return true;
            }
        }
        return false;
    }

    void unlearnWord(final String word, final SettingsValues settingsValues, final int eventType) {
        this.mDictionaryFacilitator.unlearnFromUserHistory(word, this.mConnection.getNgramContextFromNthPreviousWord(settingsValues.mSpacingAndPunctuations, 2), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), eventType);
    }

    private void handleLanguageSwitchKey() {
        SubtypeManager.getInstance().switchToNextInputSubtype();
    }

    private boolean trySwapSwapperAndSpace(final Event event, final InputTransaction inputTransaction) {
        if (32 != this.mConnection.getCodePointBeforeCursor()) {
            return false;
        }
        this.mConnection.deleteTextBeforeCursor(1);
        this.mConnection.commitText(((Object) event.getTextToCommit()) + " ", 1);
        inputTransaction.requireShiftUpdate(1);
        return true;
    }

    private boolean tryStripSpaceAndReturnWhetherShouldSwapInstead(final Event event, final InputTransaction inputTransaction) {
        int i = event.mCodePoint;
        boolean zIsSuggestionStripPress = event.isSuggestionStripPress();
        if (10 == i && 2 == inputTransaction.mSpaceState) {
            this.mConnection.removeTrailingSpace();
            return false;
        }
        if ((3 != inputTransaction.mSpaceState && 2 != inputTransaction.mSpaceState) || !zIsSuggestionStripPress || inputTransaction.mSettingsValues.isUsuallyPrecededBySpace(i)) {
            return false;
        }
        if (inputTransaction.mSettingsValues.isUsuallyFollowedBySpace(i)) {
            return true;
        }
        this.mConnection.removeTrailingSpace();
        return false;
    }

    public void startDoubleSpacePeriodCountdown(final InputTransaction inputTransaction) {
        this.mDoubleSpacePeriodCountdownStart = inputTransaction.mTimestamp;
    }

    public void cancelDoubleSpacePeriodCountdown() {
        this.mDoubleSpacePeriodCountdownStart = 0L;
    }

    public boolean isDoubleSpacePeriodCountdownActive(final InputTransaction inputTransaction) {
        return inputTransaction.mTimestamp - this.mDoubleSpacePeriodCountdownStart < inputTransaction.mSettingsValues.mDoubleSpacePeriodTimeout;
    }

    private boolean tryPerformDoubleSpacePeriod(final Event event, final InputTransaction inputTransaction) {
        CharSequence textBeforeCursor;
        int length;
        if (!inputTransaction.mSettingsValues.mUseDoubleSpacePeriod || 32 != event.mCodePoint || !isDoubleSpacePeriodCountdownActive(inputTransaction) || (textBeforeCursor = this.mConnection.getTextBeforeCursor(3, 0)) == null || (length = textBeforeCursor.length()) < 2 || textBeforeCursor.charAt(length - 1) != ' ') {
            return false;
        }
        if (canBeFollowedByDoubleSpacePeriod(Character.isSurrogatePair(textBeforeCursor.charAt(0), textBeforeCursor.charAt(1)) ? Character.codePointAt(textBeforeCursor, length - 3) : textBeforeCursor.charAt(length - 2))) {
            cancelDoubleSpacePeriodCountdown();
            this.mConnection.deleteTextBeforeCursor(1);
            this.mConnection.commitText(inputTransaction.mSettingsValues.mSpacingAndPunctuations.mSentenceSeparatorAndSpace, 1);
            inputTransaction.requireShiftUpdate(1);
            inputTransaction.setRequiresUpdateSuggestions();
            return true;
        }
        return false;
    }

    private static boolean canBeFollowedByDoubleSpacePeriod(final int codePoint) {
        return Character.isLetterOrDigit(codePoint) || codePoint == 39 || codePoint == 34 || codePoint == 41 || codePoint == 93 || codePoint == 125 || codePoint == 62 || codePoint == 43 || codePoint == 37 || Character.getType(codePoint) == 28;
    }

    private void performRecapitalization(final SettingsValues settingsValues) {
        int expectedSelectionStart;
        int expectedSelectionEnd;
        int expectedSelectionEnd2;
        if (this.mConnection.hasSelection() && this.mRecapitalizeStatus.mIsEnabled() && (expectedSelectionEnd2 = (expectedSelectionEnd = this.mConnection.getExpectedSelectionEnd()) - (expectedSelectionStart = this.mConnection.getExpectedSelectionStart())) <= 102400) {
            if (!this.mRecapitalizeStatus.isStarted() || !this.mRecapitalizeStatus.isSetAt(expectedSelectionStart, expectedSelectionEnd)) {
                CharSequence selectedText = this.mConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    return;
                }
                this.mRecapitalizeStatus.start(expectedSelectionStart, expectedSelectionEnd, selectedText.toString(), settingsValues.mLocale, settingsValues.mSpacingAndPunctuations.mSortedWordSeparators);
                this.mRecapitalizeStatus.trim();
            }
            this.mConnection.finishComposingText();
            this.mRecapitalizeStatus.rotate();
            this.mConnection.setSelection(expectedSelectionEnd, expectedSelectionEnd);
            this.mConnection.deleteTextBeforeCursor(expectedSelectionEnd2);
            this.mConnection.commitText(this.mRecapitalizeStatus.getRecapitalizedString(), 0);
            this.mConnection.setSelection(this.mRecapitalizeStatus.getNewCursorStart(), this.mRecapitalizeStatus.getNewCursorEnd());
        }
    }

    private void performAdditionToUserHistoryDictionary(final SettingsValues settingsValues, final String suggestion, @Nonnull final NgramContext ngramContext) {
        if (settingsValues.mAutoCorrectionEnabledPerUserSettings) {
            if (this.mConnection.hasSlowInputConnection()) {
                Log.w(TAG, "Skipping learning due to slow InputConnection.");
            } else {
                if (TextUtils.isEmpty(suggestion)) {
                    return;
                }
                this.mDictionaryFacilitator.addToUserHistory(suggestion, this.mWordComposer.wasAutoCapitalized() && !this.mWordComposer.isMostlyCaps(), ngramContext, (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), settingsValues.mBlockPotentiallyOffensive);
            }
        }
    }

    public void performUpdateSuggestionStripSync(final SettingsValues settingsValues, final int inputStyle) {
        if (!settingsValues.needsToLookupSuggestions()) {
            if (this.mWordComposer.isComposingWord()) {
                Log.w(TAG, "Called updateSuggestionsOrPredictions but suggestions were not requested!");
            }
            this.mSuggestionStripViewAccessor.showSuggestionStrip(SuggestedWords.getEmptyInstance());
        } else {
            if (!this.mWordComposer.isComposingWord() && !settingsValues.mBigramPredictionEnabled) {
                this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
                return;
            }
            final AsyncResultHolder asyncResultHolder = new AsyncResultHolder(Suggest.TAG);
            this.mInputLogicHandler.getSuggestedWords(inputStyle, -1, new Suggest.OnGetSuggestedWordsCallback() { // from class: com.android.inputmethod.latin.inputlogic.InputLogic.1
                @Override // com.android.inputmethod.latin.Suggest.OnGetSuggestedWordsCallback
                public void onGetSuggestedWords(final SuggestedWords suggestedWords) {
                    String typedWord = InputLogic.this.mWordComposer.getTypedWord();
                    SuggestedWords.SuggestedWordInfo suggestedWordInfo = new SuggestedWords.SuggestedWordInfo(typedWord, "", Integer.MAX_VALUE, 0, Dictionary.DICTIONARY_USER_TYPED, -1, -1);
                    if (suggestedWords.size() > 1 || typedWord.length() <= 1) {
                        asyncResultHolder.set(suggestedWords);
                    } else {
                        asyncResultHolder.set(InputLogic.retrieveOlderSuggestions(suggestedWordInfo, InputLogic.this.mSuggestedWords));
                    }
                }
            });
            SuggestedWords suggestedWords = (SuggestedWords) asyncResultHolder.get(null, 200L);
            if (suggestedWords != null) {
                this.mSuggestionStripViewAccessor.showSuggestionStrip(suggestedWords);
            }
        }
    }

    public void restartSuggestionsOnWordTouchedByCursor(final SettingsValues settingsValues, final boolean forStartInput, final int currentKeyboardScriptId) {
        int numberOfCharsInWordBeforeCursor;
        if (settingsValues.isBrokenByRecorrection() || !settingsValues.mSpacingAndPunctuations.mCurrentLanguageHasSpaces || !settingsValues.needsToLookupSuggestions() || this.mInputLogicHandler.isInBatchInput() || this.mConnection.hasSelection() || this.mConnection.getExpectedSelectionStart() < 0) {
            this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
            return;
        }
        int expectedSelectionStart = this.mConnection.getExpectedSelectionStart();
        int i = 0;
        if (!this.mConnection.isCursorTouchingWord(settingsValues.mSpacingAndPunctuations, true)) {
            this.mWordComposer.setCapitalizedModeAtStartComposingTime(0);
            this.mLatinIME.mHandler.postUpdateSuggestionStrip(5);
            return;
        }
        TextRange wordRangeAtCursor = this.mConnection.getWordRangeAtCursor(settingsValues.mSpacingAndPunctuations, currentKeyboardScriptId);
        if (wordRangeAtCursor == null) {
            return;
        }
        if (wordRangeAtCursor.length() <= 0) {
            this.mLatinIME.setNeutralSuggestionStrip();
            return;
        }
        if (!wordRangeAtCursor.mHasUrlSpans && (numberOfCharsInWordBeforeCursor = wordRangeAtCursor.getNumberOfCharsInWordBeforeCursor()) <= expectedSelectionStart) {
            ArrayList arrayList = new ArrayList();
            String string = wordRangeAtCursor.mWord.toString();
            SuggestedWords.SuggestedWordInfo suggestedWordInfo = new SuggestedWords.SuggestedWordInfo(string, "", 19, 0, Dictionary.DICTIONARY_USER_TYPED, -1, -1);
            arrayList.add(suggestedWordInfo);
            if (!isResumableWord(settingsValues, string)) {
                this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
                return;
            }
            SuggestionSpan[] suggestionSpansAtWord = wordRangeAtCursor.getSuggestionSpansAtWord();
            int length = suggestionSpansAtWord.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                String[] suggestions = suggestionSpansAtWord[i2].getSuggestions();
                int length2 = suggestions.length;
                for (int i4 = i; i4 < length2; i4++) {
                    String str = suggestions[i4];
                    i3++;
                    if (!TextUtils.equals(str, string)) {
                        arrayList.add(new SuggestedWords.SuggestedWordInfo(str, "", 18 - i3, 9, Dictionary.DICTIONARY_RESUMED, -1, -1));
                    }
                }
                i2++;
                i = 0;
            }
            int[] codePointArray = StringUtils.toCodePointArray(string);
            this.mWordComposer.setComposingWord(codePointArray, this.mLatinIME.getCoordinatesForCurrentKeyboard(codePointArray));
            this.mWordComposer.setCursorPositionWithinWord(string.codePointCount(0, numberOfCharsInWordBeforeCursor));
            if (forStartInput) {
                this.mConnection.maybeMoveTheCursorAroundAndRestoreToWorkaroundABug();
            }
            this.mConnection.setComposingRegion(expectedSelectionStart - numberOfCharsInWordBeforeCursor, expectedSelectionStart + wordRangeAtCursor.getNumberOfCharsInWordAfterCursor());
            if (arrayList.size() <= 1) {
                this.mInputLogicHandler.getSuggestedWords(0, -1, new Suggest.OnGetSuggestedWordsCallback() { // from class: com.android.inputmethod.latin.inputlogic.InputLogic.2
                    @Override // com.android.inputmethod.latin.Suggest.OnGetSuggestedWordsCallback
                    public void onGetSuggestedWords(final SuggestedWords suggestedWords) {
                        InputLogic.this.doShowSuggestionsAndClearAutoCorrectionIndicator(suggestedWords);
                    }
                });
            } else {
                doShowSuggestionsAndClearAutoCorrectionIndicator(new SuggestedWords(arrayList, null, suggestedWordInfo, false, false, false, 5, -1));
            }
        }
    }

    void doShowSuggestionsAndClearAutoCorrectionIndicator(final SuggestedWords suggestedWords) {
        this.mIsAutoCorrectionIndicatorOn = false;
        this.mLatinIME.mHandler.showSuggestionStrip(suggestedWords);
    }

    private void revertCommit(final InputTransaction inputTransaction, final SettingsValues settingsValues) {
        String str = this.mLastComposedWord.mTypedWord;
        if (str != null) {
            str.toString();
        }
        CharSequence charSequence = this.mLastComposedWord.mCommittedWord;
        String string = charSequence.toString();
        int length = charSequence.length();
        String str2 = this.mLastComposedWord.mSeparatorString;
        boolean zEquals = str2.equals(" ");
        this.mConnection.deleteTextBeforeCursor(length + str2.length());
        if (!TextUtils.isEmpty(charSequence)) {
            unlearnWord(string, inputTransaction.mSettingsValues, 3);
        }
        StringBuilder sbAppend = new StringBuilder().append((Object) str);
        if (zEquals) {
            str2 = "";
        }
        String string2 = sbAppend.append(str2).toString();
        SpannableString spannableString = new SpannableString(string2);
        if (charSequence instanceof SpannableString) {
            SpannableString spannableString2 = (SpannableString) charSequence;
            int i = 0;
            Object[] spans = spannableString2.getSpans(0, charSequence.length(), Object.class);
            int length2 = spannableString.length() - 1;
            ArrayList arrayList = new ArrayList();
            arrayList.add(string);
            int length3 = spans.length;
            int i2 = 0;
            while (i2 < length3) {
                Object obj = spans[i2];
                if (obj instanceof SuggestionSpan) {
                    String[] suggestions = ((SuggestionSpan) obj).getSuggestions();
                    int length4 = suggestions.length;
                    for (int i3 = i; i3 < length4; i3++) {
                        String str3 = suggestions[i3];
                        if (!str3.equals(string)) {
                            arrayList.add(str3);
                        }
                    }
                } else {
                    spannableString.setSpan(obj, 0, length2, spannableString2.getSpanFlags(obj));
                }
                i2++;
                i = 0;
            }
            spannableString.setSpan(new SuggestionSpan(this.mLatinIME, inputTransaction.mSettingsValues.mLocale, (String[]) arrayList.toArray(new String[arrayList.size()]), 0, null), 0, length2, 0);
        }
        if (inputTransaction.mSettingsValues.mSpacingAndPunctuations.mCurrentLanguageHasSpaces) {
            this.mConnection.commitText(spannableString, 1);
            if (zEquals) {
                this.mSpaceState = 4;
            }
        } else {
            int[] codePointArray = StringUtils.toCodePointArray(string2);
            this.mWordComposer.setComposingWord(codePointArray, this.mLatinIME.getCoordinatesForCurrentKeyboard(codePointArray));
            setComposingTextInternal(spannableString, 1);
        }
        this.mLastComposedWord = LastComposedWord.NOT_A_COMPOSED_WORD;
        inputTransaction.setRequiresUpdateSuggestions();
    }

    private int getActualCapsMode(final SettingsValues settingsValues, final int keyboardShiftMode) {
        if (keyboardShiftMode != 5) {
            return keyboardShiftMode;
        }
        int currentAutoCapsState = getCurrentAutoCapsState(settingsValues);
        if ((currentAutoCapsState & 4096) != 0) {
            return 7;
        }
        return currentAutoCapsState != 0 ? 5 : 0;
    }

    public int getCurrentAutoCapsState(final SettingsValues settingsValues) {
        EditorInfo currentInputEditorInfo;
        if (!settingsValues.mAutoCap || (currentInputEditorInfo = getCurrentInputEditorInfo()) == null) {
            return 0;
        }
        return this.mConnection.getCursorCapsMode(currentInputEditorInfo.inputType, settingsValues.mSpacingAndPunctuations, 4 == this.mSpaceState);
    }

    public int getCurrentRecapitalizeState() {
        if (this.mRecapitalizeStatus.isStarted() && this.mRecapitalizeStatus.isSetAt(this.mConnection.getExpectedSelectionStart(), this.mConnection.getExpectedSelectionEnd())) {
            return this.mRecapitalizeStatus.getCurrentMode();
        }
        return -1;
    }

    private EditorInfo getCurrentInputEditorInfo() {
        return this.mLatinIME.getCurrentInputEditorInfo();
    }

    public NgramContext getNgramContextFromNthPreviousWordForSuggestion(final SpacingAndPunctuations spacingAndPunctuations, final int nthPreviousWord) {
        if (spacingAndPunctuations.mCurrentLanguageHasSpaces) {
            return this.mConnection.getNgramContextFromNthPreviousWord(spacingAndPunctuations, nthPreviousWord);
        }
        if (LastComposedWord.NOT_A_COMPOSED_WORD == this.mLastComposedWord) {
            return NgramContext.BEGINNING_OF_SENTENCE;
        }
        return new NgramContext(new NgramContext.WordInfo(this.mLastComposedWord.mCommittedWord.toString()));
    }

    private static boolean isResumableWord(final SettingsValues settings, final String word) {
        int iCodePointAt = word.codePointAt(0);
        return (!settings.isWordCodePoint(iCodePointAt) || 39 == iCodePointAt || 45 == iCodePointAt) ? false : true;
    }

    private void performEditorAction(final int actionId) {
        this.mConnection.performEditorAction(actionId);
    }

    private String performSpecificTldProcessingOnTextInput(final String text) {
        if (text.length() <= 1 || text.charAt(0) != '.' || !Character.isLetter(text.charAt(1))) {
            return text;
        }
        this.mSpaceState = 0;
        return 46 == this.mConnection.getCodePointBeforeCursor() ? text.substring(1) : text;
    }

    private void onSettingsKeyPressed() {
        this.mLatinIME.displaySettingsDialog();
    }

    private void resetEntireInputState(final int newSelStart, final int newSelEnd, final boolean clearSuggestionStrip) {
        boolean zIsComposingWord = this.mWordComposer.isComposingWord();
        resetComposingState(true);
        if (clearSuggestionStrip) {
            this.mSuggestionStripViewAccessor.setNeutralSuggestionStrip();
        }
        this.mConnection.resetCachesUponCursorMoveAndReturnSuccess(newSelStart, newSelEnd, zIsComposingWord);
    }

    private void resetComposingState(final boolean alsoResetLastComposedWord) {
        this.mWordComposer.reset();
        if (alsoResetLastComposedWord) {
            this.mLastComposedWord = LastComposedWord.NOT_A_COMPOSED_WORD;
        }
    }

    static SuggestedWords retrieveOlderSuggestions(final SuggestedWords.SuggestedWordInfo typedWordInfo, final SuggestedWords previousSuggestedWords) {
        if (previousSuggestedWords.isPunctuationSuggestions()) {
            previousSuggestedWords = SuggestedWords.getEmptyInstance();
        }
        return new SuggestedWords(SuggestedWords.getTypedWordAndPreviousSuggestions(typedWordInfo, previousSuggestedWords), null, typedWordInfo, false, false, true, previousSuggestedWords.mInputStyle, -1);
    }

    @Nonnull
    private Locale getDictionaryFacilitatorLocale() {
        DictionaryFacilitator dictionaryFacilitator = this.mDictionaryFacilitator;
        return dictionaryFacilitator != null ? dictionaryFacilitator.getLocale() : Locale.ROOT;
    }

    private CharSequence getTextWithUnderline(final String text) {
        return this.mIsAutoCorrectionIndicatorOn ? SuggestionSpanUtils.getTextWithAutoCorrectionIndicatorUnderline(this.mLatinIME, text, getDictionaryFacilitatorLocale()) : text;
    }

    private void sendDownUpKeyEvent(final int keyCode) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.mConnection.sendKeyEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, keyCode, 0, 0, -1, 0, 6));
        this.mConnection.sendKeyEvent(new KeyEvent(SystemClock.uptimeMillis(), jUptimeMillis, 1, keyCode, 0, 0, -1, 0, 6));
    }

    private void sendKeyCodePoint(final SettingsValues settingsValues, final int codePoint) {
        if (codePoint >= 48 && codePoint <= 57) {
            sendDownUpKeyEvent((codePoint - 48) + 7);
        } else if (10 == codePoint && settingsValues.isBeforeJellyBean()) {
            sendDownUpKeyEvent(66);
        } else {
            this.mConnection.commitText(StringUtils.newSingleCodePointString(codePoint), 1);
        }
    }

    private void insertAutomaticSpaceIfOptionsAndTextAllow(final SettingsValues settingsValues) {
        if (settingsValues.shouldInsertSpacesAutomatically() && settingsValues.mSpacingAndPunctuations.mCurrentLanguageHasSpaces && !this.mConnection.textBeforeCursorLooksLikeURL()) {
            sendKeyCodePoint(settingsValues, 32);
        }
    }

    public void onUpdateTailBatchInputCompleted(final SettingsValues settingsValues, final SuggestedWords suggestedWords, final KeyboardSwitcher keyboardSwitcher) {
        String word = suggestedWords.isEmpty() ? null : suggestedWords.getWord(0);
        if (TextUtils.isEmpty(word)) {
            return;
        }
        this.mConnection.beginBatchEdit();
        if (4 == this.mSpaceState) {
            insertAutomaticSpaceIfOptionsAndTextAllow(settingsValues);
        }
        this.mWordComposer.setBatchInputWord(word);
        setComposingTextInternal(word, 1);
        this.mConnection.endBatchEdit();
        this.mSpaceState = 4;
        keyboardSwitcher.requestUpdatingShiftState(getCurrentAutoCapsState(settingsValues), getCurrentRecapitalizeState());
    }

    public void commitTyped(final SettingsValues settingsValues, final String separatorString) {
        if (this.mWordComposer.isComposingWord()) {
            String typedWord = this.mWordComposer.getTypedWord();
            if (typedWord.length() > 0) {
                boolean zIsBatchMode = this.mWordComposer.isBatchMode();
                commitChosenWord(settingsValues, typedWord, 0, separatorString);
                StatsUtils.onWordCommitUserTyped(typedWord, zIsBatchMode);
            }
        }
    }

    private void commitCurrentAutoCorrection(final SettingsValues settingsValues, final String separator, final LatinIME.UIHandler handler) {
        if (handler.hasPendingUpdateSuggestions()) {
            handler.cancelUpdateSuggestionStrip();
            performUpdateSuggestionStripSync(settingsValues, 1);
        }
        SuggestedWords.SuggestedWordInfo autoCorrectionOrNull = this.mWordComposer.getAutoCorrectionOrNull();
        String typedWord = this.mWordComposer.getTypedWord();
        String str = autoCorrectionOrNull != null ? autoCorrectionOrNull.mWord : typedWord;
        if (str != null) {
            if (TextUtils.isEmpty(typedWord)) {
                throw new RuntimeException("We have an auto-correction but the typed word is empty? Impossible! I must commit suicide.");
            }
            boolean zIsBatchMode = this.mWordComposer.isBatchMode();
            commitChosenWord(settingsValues, str, 2, separator);
            if (!typedWord.equals(str)) {
                this.mConnection.commitCorrection(new CorrectionInfo(this.mConnection.getExpectedSelectionEnd() - str.length(), typedWord, str));
                StatsUtils.onAutoCorrection(typedWord, str, zIsBatchMode, this.mDictionaryFacilitator, autoCorrectionOrNull != null ? autoCorrectionOrNull.mPrevWordsContext : "");
                StatsUtils.onWordCommitAutoCorrect(str, zIsBatchMode);
                return;
            }
            StatsUtils.onWordCommitUserTyped(str, zIsBatchMode);
        }
    }

    private void commitChosenWord(final SettingsValues settingsValues, final String chosenWord, final int commitType, final String separatorString) {
        getDictionaryFacilitatorLocale();
        NgramContext ngramContextFromNthPreviousWord = this.mConnection.getNgramContextFromNthPreviousWord(settingsValues.mSpacingAndPunctuations, this.mWordComposer.isComposingWord() ? 2 : 1);
        this.mConnection.commitText(chosenWord, 1);
        performAdditionToUserHistoryDictionary(settingsValues, chosenWord, ngramContextFromNthPreviousWord);
        this.mLastComposedWord = this.mWordComposer.commitWord(commitType, chosenWord, separatorString, ngramContextFromNthPreviousWord);
    }

    public boolean retryResetCachesAndReturnSuccess(final boolean tryResumeSuggestions, final int remainingTries, final LatinIME.UIHandler handler) {
        boolean z = this.mConnection.hasSelection() || !this.mConnection.isCursorPositionKnown();
        RichInputConnection richInputConnection = this.mConnection;
        if (!richInputConnection.resetCachesUponCursorMoveAndReturnSuccess(richInputConnection.getExpectedSelectionStart(), this.mConnection.getExpectedSelectionEnd(), z) && remainingTries > 0) {
            handler.postResetCaches(tryResumeSuggestions, remainingTries - 1);
            return false;
        }
        this.mConnection.tryFixLyingCursorPosition();
        if (tryResumeSuggestions) {
            handler.postResumeSuggestions(true);
        }
        return true;
    }

    public void getSuggestedWords(final SettingsValues settingsValues, final Keyboard keyboard, final int keyboardShiftMode, final int inputStyle, final int sequenceNumber, final Suggest.OnGetSuggestedWordsCallback callback) {
        this.mWordComposer.adviseCapitalizedModeBeforeFetchingSuggestions(getActualCapsMode(settingsValues, keyboardShiftMode));
        this.mSuggest.getSuggestedWords(this.mWordComposer, getNgramContextFromNthPreviousWordForSuggestion(settingsValues.mSpacingAndPunctuations, this.mWordComposer.isComposingWord() ? 2 : 1), keyboard, new SettingsValuesForSuggestion(settingsValues.mBlockPotentiallyOffensive), settingsValues.mAutoCorrectionEnabledPerUserSettings, inputStyle, sequenceNumber, callback);
    }

    private void setComposingTextInternal(final CharSequence newComposingText, final int newCursorPosition) {
        setComposingTextInternalWithBackgroundColor(newComposingText, newCursorPosition, 0, newComposingText.length());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void setComposingTextInternalWithBackgroundColor(final CharSequence newComposingText, final int newCursorPosition, final int backgroundColor, final int coloredTextLength) {
        if (backgroundColor != 0) {
            SpannableString spannableString = new SpannableString(newComposingText);
            spannableString.setSpan(new BackgroundColorSpan(backgroundColor), 0, Math.min(coloredTextLength, spannableString.length()), 289);
            newComposingText = spannableString;
        }
        this.mConnection.setComposingText(newComposingText, newCursorPosition);
    }

    public PrivateCommandPerformer getPrivateCommandPerformer() {
        return this.mConnection;
    }

    public int getComposingStart() {
        if (!this.mConnection.isCursorPositionKnown() || this.mConnection.hasSelection()) {
            return -1;
        }
        return this.mConnection.getExpectedSelectionStart() - this.mWordComposer.size();
    }

    public int getComposingLength() {
        return this.mWordComposer.size();
    }
}
