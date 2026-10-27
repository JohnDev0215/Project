package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.event.Event;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.utils.CapsModeUtils;
import com.android.inputmethod.latin.utils.RecapitalizeStatus;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardState {
    private static final int AUTOMATIC_SHIFT = 2;
    private static final boolean DEBUG_EVENT = false;
    private static final boolean DEBUG_INTERNAL_ACTION = false;
    private static final int MANUAL_SHIFT = 1;
    private static final int SHIFT_LOCK_SHIFTED = 3;
    private static final int SWITCH_STATE_ALPHA = 0;
    private static final int SWITCH_STATE_MOMENTARY_ALPHA_AND_SYMBOL = 3;
    private static final int SWITCH_STATE_MOMENTARY_ALPHA_SHIFT = 5;
    private static final int SWITCH_STATE_MOMENTARY_SYMBOL_AND_MORE = 4;
    private static final int SWITCH_STATE_SYMBOL = 2;
    private static final int SWITCH_STATE_SYMBOL_BEGIN = 1;
    private static final String TAG = "KeyboardState";
    private static final int UNSHIFT = 0;
    private boolean mIsAlphabetMode;
    private boolean mIsEmojiMode;
    private boolean mIsInAlphabetUnshiftedFromShifted;
    private boolean mIsInDoubleTapShiftKey;
    private boolean mIsSymbolShifted;
    private boolean mPrevMainKeyboardWasShiftLocked;
    private boolean mPrevSymbolsKeyboardWasShifted;
    private final SwitchActions mSwitchActions;
    private ShiftKeyState mShiftKeyState = new ShiftKeyState("Shift");
    private ModifierKeyState mSymbolKeyState = new ModifierKeyState("Symbol");
    private int mSwitchState = 0;
    private AlphabetShiftState mAlphabetShiftState = new AlphabetShiftState();
    private final SavedKeyboardState mSavedKeyboardState = new SavedKeyboardState();
    private int mRecapitalizeMode = -1;

    public interface SwitchActions {
        public static final boolean DEBUG_ACTION = false;
        public static final boolean DEBUG_TIMER_ACTION = false;

        void cancelDoubleTapShiftKeyTimer();

        boolean isInDoubleTapShiftKeyTimeout();

        void requestUpdatingShiftState(final int autoCapsFlags, final int recapitalizeMode);

        void setAlphabetAutomaticShiftedKeyboard();

        void setAlphabetKeyboard();

        void setAlphabetManualShiftedKeyboard();

        void setAlphabetShiftLockShiftedKeyboard();

        void setAlphabetShiftLockedKeyboard();

        void setEmojiKeyboard();

        void setSymbolsKeyboard();

        void setSymbolsShiftedKeyboard();

        void startDoubleTapShiftKeyTimer();
    }

    private static boolean isSpaceOrEnter(final int c) {
        return c == 32 || c == 10;
    }

    static String shiftModeToString(final int shiftMode) {
        if (shiftMode == 0) {
            return "UNSHIFT";
        }
        if (shiftMode == 1) {
            return "MANUAL";
        }
        if (shiftMode != 2) {
            return null;
        }
        return "AUTOMATIC";
    }

    private static String switchStateToString(final int switchState) {
        if (switchState == 0) {
            return "ALPHA";
        }
        if (switchState == 1) {
            return "SYMBOL-BEGIN";
        }
        if (switchState == 2) {
            return "SYMBOL";
        }
        if (switchState == 3) {
            return "MOMENTARY-ALPHA-SYMBOL";
        }
        if (switchState == 4) {
            return "MOMENTARY-SYMBOL-MORE";
        }
        if (switchState != 5) {
            return null;
        }
        return "MOMENTARY-ALPHA_SHIFT";
    }

    static final class SavedKeyboardState {
        public boolean mIsAlphabetMode;
        public boolean mIsAlphabetShiftLocked;
        public boolean mIsEmojiMode;
        public boolean mIsValid;
        public int mShiftMode;

        SavedKeyboardState() {
        }

        public String toString() {
            return !this.mIsValid ? "INVALID" : this.mIsAlphabetMode ? this.mIsAlphabetShiftLocked ? "ALPHABET_SHIFT_LOCKED" : "ALPHABET_" + KeyboardState.shiftModeToString(this.mShiftMode) : this.mIsEmojiMode ? "EMOJI" : "SYMBOLS_" + KeyboardState.shiftModeToString(this.mShiftMode);
        }
    }

    public KeyboardState(final SwitchActions switchActions) {
        this.mSwitchActions = switchActions;
    }

    public void onLoadKeyboard(final int autoCapsFlags, final int recapitalizeMode) {
        this.mAlphabetShiftState.setShiftLocked(false);
        this.mPrevMainKeyboardWasShiftLocked = false;
        this.mPrevSymbolsKeyboardWasShifted = false;
        this.mShiftKeyState.onRelease();
        this.mSymbolKeyState.onRelease();
        if (this.mSavedKeyboardState.mIsValid) {
            onRestoreKeyboardState(autoCapsFlags, recapitalizeMode);
            this.mSavedKeyboardState.mIsValid = false;
        } else {
            setAlphabetKeyboard(autoCapsFlags, recapitalizeMode);
        }
    }

    public void onSaveKeyboardState() {
        int i;
        SavedKeyboardState savedKeyboardState = this.mSavedKeyboardState;
        savedKeyboardState.mIsAlphabetMode = this.mIsAlphabetMode;
        savedKeyboardState.mIsEmojiMode = this.mIsEmojiMode;
        if (this.mIsAlphabetMode) {
            savedKeyboardState.mIsAlphabetShiftLocked = this.mAlphabetShiftState.isShiftLocked();
            if (this.mAlphabetShiftState.isAutomaticShifted()) {
                i = 2;
            } else {
                i = this.mAlphabetShiftState.isShiftedOrShiftLocked() ? 1 : 0;
            }
            savedKeyboardState.mShiftMode = i;
        } else {
            savedKeyboardState.mIsAlphabetShiftLocked = this.mPrevMainKeyboardWasShiftLocked;
            savedKeyboardState.mShiftMode = this.mIsSymbolShifted ? 1 : 0;
        }
        savedKeyboardState.mIsValid = true;
    }

    private void onRestoreKeyboardState(final int autoCapsFlags, final int recapitalizeMode) {
        SavedKeyboardState savedKeyboardState = this.mSavedKeyboardState;
        this.mPrevMainKeyboardWasShiftLocked = savedKeyboardState.mIsAlphabetShiftLocked;
        if (savedKeyboardState.mIsAlphabetMode) {
            setAlphabetKeyboard(autoCapsFlags, recapitalizeMode);
            setShiftLocked(savedKeyboardState.mIsAlphabetShiftLocked);
            if (savedKeyboardState.mIsAlphabetShiftLocked) {
                return;
            }
            setShifted(savedKeyboardState.mShiftMode);
            return;
        }
        if (savedKeyboardState.mIsEmojiMode) {
            setEmojiKeyboard();
        } else if (savedKeyboardState.mShiftMode == 1) {
            setSymbolsShiftedKeyboard();
        } else {
            setSymbolsKeyboard();
        }
    }

    private void setShifted(final int shiftMode) {
        int i;
        if (this.mIsAlphabetMode) {
            if (this.mAlphabetShiftState.isAutomaticShifted()) {
                i = 2;
            } else {
                i = this.mAlphabetShiftState.isManualShifted() ? 1 : 0;
            }
            if (shiftMode == 0) {
                this.mAlphabetShiftState.setShifted(false);
                if (shiftMode != i) {
                    this.mSwitchActions.setAlphabetKeyboard();
                    return;
                }
                return;
            }
            if (shiftMode == 1) {
                this.mAlphabetShiftState.setShifted(true);
                if (shiftMode != i) {
                    this.mSwitchActions.setAlphabetManualShiftedKeyboard();
                    return;
                }
                return;
            }
            if (shiftMode != 2) {
                if (shiftMode != 3) {
                    return;
                }
                this.mAlphabetShiftState.setShifted(true);
                this.mSwitchActions.setAlphabetShiftLockShiftedKeyboard();
                return;
            }
            this.mAlphabetShiftState.setAutomaticShifted();
            if (shiftMode != i) {
                this.mSwitchActions.setAlphabetAutomaticShiftedKeyboard();
            }
        }
    }

    private void setShiftLocked(final boolean shiftLocked) {
        if (this.mIsAlphabetMode) {
            if (shiftLocked && (!this.mAlphabetShiftState.isShiftLocked() || this.mAlphabetShiftState.isShiftLockShifted())) {
                this.mSwitchActions.setAlphabetShiftLockedKeyboard();
            }
            if (!shiftLocked && this.mAlphabetShiftState.isShiftLocked()) {
                this.mSwitchActions.setAlphabetKeyboard();
            }
            this.mAlphabetShiftState.setShiftLocked(shiftLocked);
        }
    }

    private void toggleAlphabetAndSymbols(final int autoCapsFlags, final int recapitalizeMode) {
        if (this.mIsAlphabetMode) {
            this.mPrevMainKeyboardWasShiftLocked = this.mAlphabetShiftState.isShiftLocked();
            if (this.mPrevSymbolsKeyboardWasShifted) {
                setSymbolsShiftedKeyboard();
            } else {
                setSymbolsKeyboard();
            }
            this.mPrevSymbolsKeyboardWasShifted = false;
            return;
        }
        this.mPrevSymbolsKeyboardWasShifted = this.mIsSymbolShifted;
        setAlphabetKeyboard(autoCapsFlags, recapitalizeMode);
        if (this.mPrevMainKeyboardWasShiftLocked) {
            setShiftLocked(true);
        }
        this.mPrevMainKeyboardWasShiftLocked = false;
    }

    private void resetKeyboardStateToAlphabet(final int autoCapsFlags, final int recapitalizeMode) {
        if (this.mIsAlphabetMode) {
            return;
        }
        this.mPrevSymbolsKeyboardWasShifted = this.mIsSymbolShifted;
        setAlphabetKeyboard(autoCapsFlags, recapitalizeMode);
        if (this.mPrevMainKeyboardWasShiftLocked) {
            setShiftLocked(true);
        }
        this.mPrevMainKeyboardWasShiftLocked = false;
    }

    private void toggleShiftInSymbols() {
        if (this.mIsSymbolShifted) {
            setSymbolsKeyboard();
        } else {
            setSymbolsShiftedKeyboard();
        }
    }

    private void setAlphabetKeyboard(final int autoCapsFlags, final int recapitalizeMode) {
        this.mSwitchActions.setAlphabetKeyboard();
        this.mIsAlphabetMode = true;
        this.mIsEmojiMode = false;
        this.mIsSymbolShifted = false;
        this.mRecapitalizeMode = -1;
        this.mSwitchState = 0;
        this.mSwitchActions.requestUpdatingShiftState(autoCapsFlags, recapitalizeMode);
    }

    private void setSymbolsKeyboard() {
        this.mSwitchActions.setSymbolsKeyboard();
        this.mIsAlphabetMode = false;
        this.mIsSymbolShifted = false;
        this.mRecapitalizeMode = -1;
        this.mAlphabetShiftState.setShiftLocked(false);
        this.mSwitchState = 1;
    }

    private void setSymbolsShiftedKeyboard() {
        this.mSwitchActions.setSymbolsShiftedKeyboard();
        this.mIsAlphabetMode = false;
        this.mIsSymbolShifted = true;
        this.mRecapitalizeMode = -1;
        this.mAlphabetShiftState.setShiftLocked(false);
        this.mSwitchState = 1;
    }

    private void setEmojiKeyboard() {
        this.mIsAlphabetMode = false;
        this.mIsEmojiMode = true;
        this.mRecapitalizeMode = -1;
        this.mPrevMainKeyboardWasShiftLocked = this.mAlphabetShiftState.isShiftLocked();
        this.mAlphabetShiftState.setShiftLocked(false);
        this.mSwitchActions.setEmojiKeyboard();
    }

    public void onPressKey(final int code, final boolean isSinglePointer, final int autoCapsFlags, final int recapitalizeMode) {
        if (code != -1) {
            this.mSwitchActions.cancelDoubleTapShiftKeyTimer();
        }
        if (code == -1) {
            onPressShift();
            return;
        }
        if (code == -2) {
            return;
        }
        if (code == -3) {
            onPressSymbol(autoCapsFlags, recapitalizeMode);
            return;
        }
        this.mShiftKeyState.onOtherKeyPressed();
        this.mSymbolKeyState.onOtherKeyPressed();
        if (isSinglePointer || !this.mIsAlphabetMode || autoCapsFlags == 4096) {
            return;
        }
        if (this.mAlphabetShiftState.isAutomaticShifted() || (this.mAlphabetShiftState.isManualShifted() && this.mShiftKeyState.isReleasing())) {
            this.mSwitchActions.setAlphabetKeyboard();
        }
    }

    public void onReleaseKey(final int code, final boolean withSliding, final int autoCapsFlags, final int recapitalizeMode) {
        if (code == -1) {
            onReleaseShift(withSliding, autoCapsFlags, recapitalizeMode);
        } else if (code == -2) {
            setShiftLocked(!this.mAlphabetShiftState.isShiftLocked());
        } else if (code == -3) {
            onReleaseSymbol(withSliding, autoCapsFlags, recapitalizeMode);
        }
    }

    private void onPressSymbol(final int autoCapsFlags, final int recapitalizeMode) {
        toggleAlphabetAndSymbols(autoCapsFlags, recapitalizeMode);
        this.mSymbolKeyState.onPress();
        this.mSwitchState = 3;
    }

    private void onReleaseSymbol(final boolean withSliding, final int autoCapsFlags, final int recapitalizeMode) {
        if (this.mSymbolKeyState.isChording()) {
            toggleAlphabetAndSymbols(autoCapsFlags, recapitalizeMode);
        } else if (!withSliding) {
            this.mPrevSymbolsKeyboardWasShifted = false;
        }
        this.mSymbolKeyState.onRelease();
    }

    public void onUpdateShiftState(final int autoCapsFlags, final int recapitalizeMode) {
        this.mRecapitalizeMode = recapitalizeMode;
        updateAlphabetShiftState(autoCapsFlags, recapitalizeMode);
    }

    public void onResetKeyboardStateToAlphabet(final int autoCapsFlags, final int recapitalizeMode) {
        resetKeyboardStateToAlphabet(autoCapsFlags, recapitalizeMode);
    }

    private void updateShiftStateForRecapitalize(final int recapitalizeMode) {
        if (recapitalizeMode == 2) {
            setShifted(2);
        } else if (recapitalizeMode == 3) {
            setShifted(3);
        } else {
            setShifted(0);
        }
    }

    private void updateAlphabetShiftState(int i, int i2) {
        if (this.mIsAlphabetMode) {
            if (-1 != i2) {
                updateShiftStateForRecapitalize(i2);
                return;
            }
            if (!this.mShiftKeyState.isReleasing() || this.mAlphabetShiftState.isShiftLocked() || this.mShiftKeyState.isIgnoring()) {
                return;
            }
            if (!this.mShiftKeyState.isReleasing() || i == 0) {
                setShifted(this.mShiftKeyState.isChording() ? 1 : 0);
            } else {
                setShifted(2);
            }
        }
    }

    private void onPressShift() {
        if (-1 != this.mRecapitalizeMode) {
            return;
        }
        if (this.mIsAlphabetMode) {
            boolean zIsInDoubleTapShiftKeyTimeout = this.mSwitchActions.isInDoubleTapShiftKeyTimeout();
            this.mIsInDoubleTapShiftKey = zIsInDoubleTapShiftKeyTimeout;
            if (!zIsInDoubleTapShiftKeyTimeout) {
                this.mSwitchActions.startDoubleTapShiftKeyTimer();
            }
            if (this.mIsInDoubleTapShiftKey) {
                if (this.mAlphabetShiftState.isManualShifted() || this.mIsInAlphabetUnshiftedFromShifted) {
                    setShiftLocked(true);
                    return;
                }
                return;
            }
            if (this.mAlphabetShiftState.isShiftLocked()) {
                setShifted(3);
                this.mShiftKeyState.onPress();
                return;
            } else if (this.mAlphabetShiftState.isAutomaticShifted()) {
                setShifted(1);
                this.mShiftKeyState.onPress();
                return;
            } else if (this.mAlphabetShiftState.isShiftedOrShiftLocked()) {
                this.mShiftKeyState.onPressOnShifted();
                return;
            } else {
                setShifted(1);
                this.mShiftKeyState.onPress();
                return;
            }
        }
        toggleShiftInSymbols();
        this.mSwitchState = 4;
        this.mShiftKeyState.onPress();
    }

    private void onReleaseShift(final boolean withSliding, final int autoCapsFlags, final int recapitalizeMode) {
        int i = this.mRecapitalizeMode;
        if (-1 != i) {
            updateShiftStateForRecapitalize(i);
        } else if (this.mIsAlphabetMode) {
            boolean zIsShiftLocked = this.mAlphabetShiftState.isShiftLocked();
            this.mIsInAlphabetUnshiftedFromShifted = false;
            if (this.mIsInDoubleTapShiftKey) {
                this.mIsInDoubleTapShiftKey = false;
            } else {
                if (this.mShiftKeyState.isChording()) {
                    if (this.mAlphabetShiftState.isShiftLockShifted()) {
                        setShiftLocked(true);
                    } else {
                        setShifted(0);
                    }
                    this.mShiftKeyState.onRelease();
                    this.mSwitchActions.requestUpdatingShiftState(autoCapsFlags, recapitalizeMode);
                    return;
                }
                if (this.mAlphabetShiftState.isShiftLockShifted() && withSliding) {
                    setShiftLocked(true);
                } else if (this.mAlphabetShiftState.isManualShifted() && withSliding) {
                    this.mSwitchState = 5;
                } else if (!zIsShiftLocked || this.mAlphabetShiftState.isShiftLockShifted() || ((!this.mShiftKeyState.isPressing() && !this.mShiftKeyState.isPressingOnShifted()) || withSliding)) {
                    if (zIsShiftLocked && !this.mShiftKeyState.isIgnoring() && !withSliding) {
                        setShiftLocked(false);
                    } else if (this.mAlphabetShiftState.isShiftedOrShiftLocked() && this.mShiftKeyState.isPressingOnShifted() && !withSliding) {
                        setShifted(0);
                        this.mIsInAlphabetUnshiftedFromShifted = true;
                    } else if (this.mAlphabetShiftState.isManualShiftedFromAutomaticShifted() && this.mShiftKeyState.isPressing() && !withSliding) {
                        setShifted(0);
                        this.mIsInAlphabetUnshiftedFromShifted = true;
                    }
                }
            }
        } else if (this.mShiftKeyState.isChording()) {
            toggleShiftInSymbols();
        }
        this.mShiftKeyState.onRelease();
    }

    public void onFinishSlidingInput(final int autoCapsFlags, final int recapitalizeMode) {
        int i = this.mSwitchState;
        if (i == 3) {
            toggleAlphabetAndSymbols(autoCapsFlags, recapitalizeMode);
        } else if (i == 4) {
            toggleShiftInSymbols();
        } else {
            if (i != 5) {
                return;
            }
            setAlphabetKeyboard(autoCapsFlags, recapitalizeMode);
        }
    }

    public void onEvent(final Event event, final int autoCapsFlags, final int recapitalizeMode) {
        int i = event.isFunctionalKeyEvent() ? event.mKeyCode : event.mCodePoint;
        int i2 = this.mSwitchState;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 == 4 && i == -1) {
                        this.mSwitchState = 1;
                    }
                } else if (i == -3) {
                    if (this.mIsAlphabetMode) {
                        this.mSwitchState = 0;
                    } else {
                        this.mSwitchState = 1;
                    }
                }
            } else if (isSpaceOrEnter(i)) {
                toggleAlphabetAndSymbols(autoCapsFlags, recapitalizeMode);
                this.mPrevSymbolsKeyboardWasShifted = false;
            }
        } else if (!this.mIsEmojiMode && !isSpaceOrEnter(i) && (Constants.isLetterCode(i) || i == -4)) {
            this.mSwitchState = 2;
        }
        if (Constants.isLetterCode(i)) {
            updateAlphabetShiftState(autoCapsFlags, recapitalizeMode);
        } else if (i == -11) {
            setEmojiKeyboard();
        } else if (i == -14) {
            setAlphabetKeyboard(autoCapsFlags, recapitalizeMode);
        }
    }

    public String toString() {
        String string;
        StringBuilder sbAppend = new StringBuilder().append("[keyboard=");
        if (this.mIsAlphabetMode) {
            string = this.mAlphabetShiftState.toString();
        } else {
            string = this.mIsSymbolShifted ? "SYMBOLS_SHIFTED" : "SYMBOLS";
        }
        return sbAppend.append(string).append(" shift=").append(this.mShiftKeyState).append(" symbol=").append(this.mSymbolKeyState).append(" switch=").append(switchStateToString(this.mSwitchState)).append("]").toString();
    }

    private String stateToString(final int autoCapsFlags, final int recapitalizeMode) {
        return this + " autoCapsFlags=" + CapsModeUtils.flagsToString(autoCapsFlags) + " recapitalizeMode=" + RecapitalizeStatus.modeToString(recapitalizeMode);
    }
}
