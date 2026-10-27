package com.android.inputmethod.keyboard;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import com.android.inputmethod.compat.InputMethodServiceCompatUtils;
import com.android.inputmethod.event.Event;
import com.android.inputmethod.keyboard.KeyboardLayoutSet;
import com.android.inputmethod.keyboard.emoji.EmojiPalettesView;
import com.android.inputmethod.keyboard.internal.KeyboardState;
import com.android.inputmethod.keyboard.internal.KeyboardTextsSet;
import com.android.inputmethod.latin.InputView;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.settings.SettingsValues;
import com.android.inputmethod.latin.utils.LanguageOnSpacebarUtils;
import com.android.inputmethod.latin.utils.ResourceUtils;
import com.byd.ime.TestLogger;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardSwitcher implements KeyboardState.SwitchActions {
    private static final String TAG = "KeyboardSwitcher";
    private static final KeyboardSwitcher sInstance = new KeyboardSwitcher();
    private InputView mCurrentInputView;
    private EmojiPalettesView mEmojiPalettesView;
    private boolean mIsHardwareAcceleratedDrawingEnabled;
    private KeyboardLayoutSet mKeyboardLayoutSet;
    private final KeyboardTextsSet mKeyboardTextsSet = new KeyboardTextsSet();
    private KeyboardTheme mKeyboardTheme;
    private MainKeyboardView mKeyboardView;
    private LatinIME mLatinIME;
    private View mMainKeyboardFrame;
    private RichInputMethodManager mRichImm;
    private KeyboardState mState;
    private Context mThemeContext;

    public static KeyboardSwitcher getInstance() {
        return sInstance;
    }

    private KeyboardSwitcher() {
    }

    public static void init(final LatinIME latinIme) {
        sInstance.initInternal(latinIme);
    }

    private void initInternal(final LatinIME latinIme) {
        this.mLatinIME = latinIme;
        this.mRichImm = RichInputMethodManager.getInstance();
        this.mState = new KeyboardState(this);
        this.mIsHardwareAcceleratedDrawingEnabled = InputMethodServiceCompatUtils.enableHardwareAcceleration(this.mLatinIME);
    }

    public void updateKeyboardTheme(Context displayContext) {
        boolean zUpdateKeyboardThemeAndContextThemeWrapper = updateKeyboardThemeAndContextThemeWrapper(displayContext, KeyboardTheme.getKeyboardTheme(displayContext));
        if (this.mKeyboardView == null || this.mLatinIME.shouldResetInputView) {
            TestLogger.logE("monkey bug 16495 do not show because InputMethodService onCreateInputView not call");
            this.mLatinIME.setInputView(onCreateInputView(displayContext, this.mIsHardwareAcceleratedDrawingEnabled));
            this.mLatinIME.shouldResetInputView = false;
            if (this.mCurrentInputView.getParent() instanceof FrameLayout) {
                ((FrameLayout) this.mCurrentInputView.getParent()).setVisibility(0);
            }
        }
        if (!zUpdateKeyboardThemeAndContextThemeWrapper || this.mKeyboardView == null) {
            return;
        }
        this.mLatinIME.setInputView(onCreateInputView(displayContext, this.mIsHardwareAcceleratedDrawingEnabled));
    }

    private boolean updateKeyboardThemeAndContextThemeWrapper(final Context context, final KeyboardTheme keyboardTheme) {
        if (this.mThemeContext != null && keyboardTheme.equals(this.mKeyboardTheme) && this.mThemeContext.getResources().equals(context.getResources())) {
            return false;
        }
        this.mKeyboardTheme = keyboardTheme;
        this.mThemeContext = new ContextThemeWrapper(context, keyboardTheme.mStyleId);
        KeyboardLayoutSet.onKeyboardThemeChanged();
        return true;
    }

    public void loadKeyboard(final EditorInfo editorInfo, final SettingsValues settingsValues, final int currentAutoCapsState, final int currentRecapitalizeState) {
        KeyboardLayoutSet.Builder builder = new KeyboardLayoutSet.Builder(this.mThemeContext, editorInfo);
        Resources resources = this.mThemeContext.getResources();
        builder.setKeyboardGeometry(ResourceUtils.getDefaultKeyboardWidth(resources), ResourceUtils.getKeyboardHeight(resources, settingsValues));
        builder.setSubtype(this.mRichImm.getCurrentSubtype());
        builder.setVoiceInputKeyEnabled(settingsValues.mShowsVoiceInputKey);
        builder.setLanguageSwitchKeyEnabled(this.mLatinIME.shouldShowLanguageSwitchKey());
        builder.setSplitLayoutEnabledByUser(settingsValues.mIsSplitKeyboardEnabled);
        this.mKeyboardLayoutSet = builder.build();
        try {
            this.mState.onLoadKeyboard(currentAutoCapsState, currentRecapitalizeState);
            this.mKeyboardTextsSet.setLocale(this.mRichImm.getCurrentSubtypeLocale(), this.mThemeContext);
        } catch (KeyboardLayoutSet.KeyboardLayoutSetException e) {
            Log.w(TAG, "loading keyboard failed: " + e.mKeyboardId, e.getCause());
        }
    }

    public void saveKeyboardState() {
        if (getKeyboard() != null || isShowingEmojiPalettes()) {
            this.mState.onSaveKeyboardState();
        }
    }

    public void onHideWindow() {
        MainKeyboardView mainKeyboardView = this.mKeyboardView;
        if (mainKeyboardView != null) {
            mainKeyboardView.onHideWindow();
        }
    }

    private void setKeyboard(@Nonnull final int keyboardId, @Nonnull final KeyboardSwitchState toggleState) {
        SettingsValues current = Settings.getInstance().getCurrent();
        setMainKeyboardFrame(current, toggleState);
        MainKeyboardView mainKeyboardView = this.mKeyboardView;
        Keyboard keyboard = mainKeyboardView.getKeyboard();
        Keyboard keyboard2 = this.mKeyboardLayoutSet.getKeyboard(keyboardId);
        mainKeyboardView.setKeyboard(keyboard2);
        this.mCurrentInputView.setKeyboardTopPadding(keyboard2.mTopPadding);
        mainKeyboardView.setKeyPreviewPopupEnabled(current.mKeyPreviewPopupOn, current.mKeyPreviewPopupDismissDelay);
        mainKeyboardView.setKeyPreviewAnimationParams(current.mHasCustomKeyPreviewAnimationParams, current.mKeyPreviewShowUpStartXScale, current.mKeyPreviewShowUpStartYScale, current.mKeyPreviewShowUpDuration, current.mKeyPreviewDismissEndXScale, current.mKeyPreviewDismissEndYScale, current.mKeyPreviewDismissDuration);
        mainKeyboardView.updateShortcutKey(this.mRichImm.isShortcutImeReady());
        mainKeyboardView.startDisplayLanguageOnSpacebar(keyboard == null || !keyboard2.mId.mSubtype.equals(keyboard.mId.mSubtype), LanguageOnSpacebarUtils.getLanguageOnSpacebarFormatType(keyboard2.mId.mSubtype), this.mRichImm.hasMultipleEnabledIMEsOrSubtypes(true));
    }

    public Keyboard getKeyboard() {
        MainKeyboardView mainKeyboardView = this.mKeyboardView;
        if (mainKeyboardView != null) {
            return mainKeyboardView.getKeyboard();
        }
        return null;
    }

    public void resetKeyboardStateToAlphabet(final int currentAutoCapsState, final int currentRecapitalizeState) {
        this.mState.onResetKeyboardStateToAlphabet(currentAutoCapsState, currentRecapitalizeState);
    }

    public void onPressKey(final int code, final boolean isSinglePointer, final int currentAutoCapsState, final int currentRecapitalizeState) {
        this.mState.onPressKey(code, isSinglePointer, currentAutoCapsState, currentRecapitalizeState);
    }

    public void onReleaseKey(final int code, final boolean withSliding, final int currentAutoCapsState, final int currentRecapitalizeState) {
        this.mState.onReleaseKey(code, withSliding, currentAutoCapsState, currentRecapitalizeState);
    }

    public void onFinishSlidingInput(final int currentAutoCapsState, final int currentRecapitalizeState) {
        this.mState.onFinishSlidingInput(currentAutoCapsState, currentRecapitalizeState);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setAlphabetKeyboard() {
        setKeyboard(0, KeyboardSwitchState.OTHER);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setAlphabetManualShiftedKeyboard() {
        setKeyboard(1, KeyboardSwitchState.OTHER);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setAlphabetAutomaticShiftedKeyboard() {
        setKeyboard(2, KeyboardSwitchState.OTHER);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setAlphabetShiftLockedKeyboard() {
        setKeyboard(3, KeyboardSwitchState.OTHER);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setAlphabetShiftLockShiftedKeyboard() {
        setKeyboard(4, KeyboardSwitchState.OTHER);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setSymbolsKeyboard() {
        setKeyboard(5, KeyboardSwitchState.OTHER);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setSymbolsShiftedKeyboard() {
        setKeyboard(6, KeyboardSwitchState.SYMBOLS_SHIFTED);
    }

    public boolean isImeSuppressedByHardwareKeyboard(@Nonnull final SettingsValues settingsValues, @Nonnull final KeyboardSwitchState toggleState) {
        return settingsValues.mHasHardwareKeyboard && toggleState == KeyboardSwitchState.HIDDEN;
    }

    private void setMainKeyboardFrame(@Nonnull final SettingsValues settingsValues, @Nonnull final KeyboardSwitchState toggleState) {
        int i = isImeSuppressedByHardwareKeyboard(settingsValues, toggleState) ? 8 : 0;
        this.mKeyboardView.setVisibility(i);
        this.mMainKeyboardFrame.setVisibility(i);
        this.mEmojiPalettesView.setVisibility(8);
        this.mEmojiPalettesView.stopEmojiPalettes();
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void setEmojiKeyboard() {
        Keyboard keyboard = this.mKeyboardLayoutSet.getKeyboard(0);
        this.mMainKeyboardFrame.setVisibility(8);
        this.mKeyboardView.setVisibility(8);
        this.mEmojiPalettesView.startEmojiPalettes(this.mKeyboardTextsSet.getText(KeyboardTextsSet.SWITCH_TO_ALPHA_KEY_LABEL), this.mKeyboardView.getKeyVisualAttribute(), keyboard.mIconsSet);
        this.mEmojiPalettesView.setVisibility(0);
    }

    public enum KeyboardSwitchState {
        HIDDEN(-1),
        SYMBOLS_SHIFTED(6),
        EMOJI(10),
        OTHER(-1);

        final int mKeyboardId;

        KeyboardSwitchState(int keyboardId) {
            this.mKeyboardId = keyboardId;
        }
    }

    public KeyboardSwitchState getKeyboardSwitchState() {
        MainKeyboardView mainKeyboardView;
        if (!isShowingEmojiPalettes() && (this.mKeyboardLayoutSet == null || (mainKeyboardView = this.mKeyboardView) == null || !mainKeyboardView.isShown())) {
            return KeyboardSwitchState.HIDDEN;
        }
        if (isShowingEmojiPalettes()) {
            return KeyboardSwitchState.EMOJI;
        }
        if (isShowingKeyboardId(6)) {
            return KeyboardSwitchState.SYMBOLS_SHIFTED;
        }
        return KeyboardSwitchState.OTHER;
    }

    public void onToggleKeyboard(@Nonnull final KeyboardSwitchState toggleState) {
        KeyboardSwitchState keyboardSwitchState = getKeyboardSwitchState();
        Log.w(TAG, "onToggleKeyboard() : Current = " + keyboardSwitchState + " : Toggle = " + toggleState);
        if (keyboardSwitchState == toggleState) {
            this.mLatinIME.stopShowingInputView();
            this.mLatinIME.hideWindow();
            setAlphabetKeyboard();
            return;
        }
        this.mLatinIME.startShowingInputView(true);
        if (toggleState == KeyboardSwitchState.EMOJI) {
            setEmojiKeyboard();
            return;
        }
        this.mEmojiPalettesView.stopEmojiPalettes();
        this.mEmojiPalettesView.setVisibility(8);
        this.mMainKeyboardFrame.setVisibility(0);
        this.mKeyboardView.setVisibility(0);
        setKeyboard(toggleState.mKeyboardId, toggleState);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void requestUpdatingShiftState(final int autoCapsFlags, final int recapitalizeMode) {
        this.mState.onUpdateShiftState(autoCapsFlags, recapitalizeMode);
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void startDoubleTapShiftKeyTimer() {
        MainKeyboardView mainKeyboardView = getMainKeyboardView();
        if (mainKeyboardView != null) {
            mainKeyboardView.startDoubleTapShiftKeyTimer();
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public void cancelDoubleTapShiftKeyTimer() {
        MainKeyboardView mainKeyboardView = getMainKeyboardView();
        if (mainKeyboardView != null) {
            mainKeyboardView.cancelDoubleTapShiftKeyTimer();
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.KeyboardState.SwitchActions
    public boolean isInDoubleTapShiftKeyTimeout() {
        MainKeyboardView mainKeyboardView = getMainKeyboardView();
        return mainKeyboardView != null && mainKeyboardView.isInDoubleTapShiftKeyTimeout();
    }

    public void onEvent(final Event event, final int currentAutoCapsState, final int currentRecapitalizeState) {
        this.mState.onEvent(event, currentAutoCapsState, currentRecapitalizeState);
    }

    public boolean isShowingKeyboardId(@Nonnull int... keyboardIds) {
        MainKeyboardView mainKeyboardView = this.mKeyboardView;
        if (mainKeyboardView != null && mainKeyboardView.isShown()) {
            int i = this.mKeyboardView.getKeyboard().mId.mElementId;
            for (int i2 : keyboardIds) {
                if (i == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isShowingEmojiPalettes() {
        EmojiPalettesView emojiPalettesView = this.mEmojiPalettesView;
        return emojiPalettesView != null && emojiPalettesView.isShown();
    }

    public boolean isShowingMoreKeysPanel() {
        if (isShowingEmojiPalettes()) {
            return false;
        }
        return this.mKeyboardView.isShowingMoreKeysPanel();
    }

    public View getVisibleKeyboardView() {
        if (isShowingEmojiPalettes()) {
            return this.mEmojiPalettesView;
        }
        return this.mKeyboardView;
    }

    public MainKeyboardView getMainKeyboardView() {
        return this.mKeyboardView;
    }

    public void deallocateMemory() {
        MainKeyboardView mainKeyboardView = this.mKeyboardView;
        if (mainKeyboardView != null) {
            mainKeyboardView.cancelAllOngoingEvents();
            this.mKeyboardView.deallocateMemory();
        }
        EmojiPalettesView emojiPalettesView = this.mEmojiPalettesView;
        if (emojiPalettesView != null) {
            emojiPalettesView.stopEmojiPalettes();
        }
    }

    public View onCreateInputView(Context displayContext, final boolean isHardwareAcceleratedDrawingEnabled) {
        MainKeyboardView mainKeyboardView = this.mKeyboardView;
        if (mainKeyboardView != null) {
            mainKeyboardView.closing();
        }
        updateKeyboardThemeAndContextThemeWrapper(displayContext, KeyboardTheme.getKeyboardTheme(displayContext));
        InputView inputView = (InputView) LayoutInflater.from(this.mThemeContext).inflate(R.layout.input_view, (ViewGroup) null);
        this.mCurrentInputView = inputView;
        this.mMainKeyboardFrame = inputView.findViewById(R.id.main_keyboard_frame);
        this.mEmojiPalettesView = (EmojiPalettesView) this.mCurrentInputView.findViewById(R.id.emoji_palettes_view);
        MainKeyboardView mainKeyboardView2 = (MainKeyboardView) this.mCurrentInputView.findViewById(R.id.keyboard_view);
        this.mKeyboardView = mainKeyboardView2;
        mainKeyboardView2.setHardwareAcceleratedDrawingEnabled(isHardwareAcceleratedDrawingEnabled);
        this.mKeyboardView.setKeyboardActionListener(this.mLatinIME);
        this.mEmojiPalettesView.setHardwareAcceleratedDrawingEnabled(isHardwareAcceleratedDrawingEnabled);
        this.mEmojiPalettesView.setKeyboardActionListener(this.mLatinIME);
        return this.mCurrentInputView;
    }

    public int getKeyboardShiftMode() {
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return 0;
        }
        int i = keyboard.mId.mElementId;
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return (i == 3 || i == 4) ? 3 : 0;
        }
        return 5;
    }

    public int getCurrentKeyboardScriptId() {
        KeyboardLayoutSet keyboardLayoutSet = this.mKeyboardLayoutSet;
        if (keyboardLayoutSet == null) {
            return -1;
        }
        return keyboardLayoutSet.getScriptId();
    }
}
