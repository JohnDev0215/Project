package com.android.inputmethod.latin.utils;

import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.DictionaryFacilitator;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.settings.SettingsValues;

/* JADX INFO: loaded from: classes2.dex */
public final class StatsUtils {
    public static void onAutoCorrection(final String typedWord, final String autoCorrectionWord, final boolean isBatchInput, final DictionaryFacilitator dictionaryFacilitator, final String prevWordsContext) {
    }

    public static void onBackspacePressed(int lengthToDelete) {
    }

    public static void onBackspaceSelectedText(int selectedTextLength) {
    }

    public static void onBackspaceWordDelete(int wordLength) {
    }

    public static void onCreate(final SettingsValues settingsValues, RichInputMethodManager richImm) {
    }

    public static void onCreateInputView() {
    }

    public static void onDecoderLaggy(final int operation, final long duration) {
    }

    public static void onDeleteMultiCharInput(int multiCharLength) {
    }

    public static void onDoubleSpacePeriod() {
    }

    public static void onFinishInputView() {
    }

    public static void onInputConnectionLaggy(final int operation, final long duration) {
    }

    public static void onInvalidWordIdentification(final String invalidWord) {
    }

    public static void onLoadSettings(SettingsValues settingsValues) {
    }

    public static void onPickSuggestionManually(final SuggestedWords suggestedWords, final SuggestedWords.SuggestedWordInfo suggestionInfo, final DictionaryFacilitator dictionaryFacilitator) {
    }

    public static void onRevertAutoCorrect() {
    }

    public static void onRevertDoubleSpacePeriod() {
    }

    public static void onRevertSwapPunctuation() {
    }

    public static void onSettingsActivity(final String entryPoint) {
    }

    public static void onStartInputView(int inputType, int displayOrientation, boolean restarting) {
    }

    public static void onSubtypeChanged(final InputMethodSubtype oldSubtype, final InputMethodSubtype newSubtype) {
    }

    public static void onWordCommitAutoCorrect(final String commitWord, final boolean isBatchMode) {
    }

    public static void onWordCommitSuggestionPickedManually(final String commitWord, final boolean isBatchMode) {
    }

    public static void onWordCommitUserTyped(final String commitWord, final boolean isBatchMode) {
    }

    private StatsUtils() {
    }
}
