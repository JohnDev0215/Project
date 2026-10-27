package com.android.inputmethod.latin;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.inputmethodservice.InputMethodService;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.PrintWriterPrinter;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodSubtype;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.accessibility.AccessibilityUtils;
import com.android.inputmethod.compat.BuildCompatUtils;
import com.android.inputmethod.compat.EditorInfoCompatUtils;
import com.android.inputmethod.compat.InputMethodServiceCompatUtils;
import com.android.inputmethod.compat.ViewOutlineProviderCompatUtils;
import com.android.inputmethod.dictionarypack.DictionaryPackConstants;
import com.android.inputmethod.event.Event;
import com.android.inputmethod.event.HardwareEventDecoder;
import com.android.inputmethod.event.HardwareKeyboardEventDecoder;
import com.android.inputmethod.event.InputTransaction;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardActionListener;
import com.android.inputmethod.keyboard.KeyboardId;
import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.latin.DictionaryFacilitator;
import com.android.inputmethod.latin.Suggest;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.CoordinateUtils;
import com.android.inputmethod.latin.common.InputPointers;
import com.android.inputmethod.latin.define.DebugFlags;
import com.android.inputmethod.latin.inputlogic.InputLogic;
import com.android.inputmethod.latin.permissions.PermissionsManager;
import com.android.inputmethod.latin.personalization.PersonalizationHelper;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.settings.SettingsActivity;
import com.android.inputmethod.latin.settings.SettingsValues;
import com.android.inputmethod.latin.suggestions.SuggestionStripView;
import com.android.inputmethod.latin.suggestions.SuggestionStripViewAccessor;
import com.android.inputmethod.latin.touchinputconsumer.GestureConsumer;
import com.android.inputmethod.latin.utils.ApplicationUtils;
import com.android.inputmethod.latin.utils.DialogUtils;
import com.android.inputmethod.latin.utils.ImportantNoticeUtils;
import com.android.inputmethod.latin.utils.JniUtils;
import com.android.inputmethod.latin.utils.LeakGuardHandlerWrapper;
import com.android.inputmethod.latin.utils.StatsUtils;
import com.android.inputmethod.latin.utils.StatsUtilsManager;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import com.android.inputmethod.latin.utils.ViewLayoutUtils;
import com.byd.ime.BydThemeHelper;
import com.byd.ime.subtype.SubtypeManager;
import com.byd.ime.subtype.show.MultiInputMethodSubtypePopupWindow;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LatinIME extends InputMethodService implements KeyboardActionListener, SuggestionStripView.Listener, SuggestionStripViewAccessor, DictionaryFacilitator.DictionaryInitializationListener, PermissionsManager.PermissionsResultCallback {
    static final String ACTION_HIDE_SOFT_INPUT = "com.android.inputmethod.latin.HIDE_SOFT_INPUT";
    private static final int PENDING_IMS_CALLBACK_DURATION_MILLIS = 800;
    private static final int PERIOD_FOR_AUDIO_AND_HAPTIC_FEEDBACK_IN_KEY_REPEAT = 2;
    static final String PERMISSION_HIDE_SOFT_INPUT = "com.android.inputmethod.latin.HIDE_SOFT_INPUT";
    private static final String SCHEME_PACKAGE = "package";
    static final String TAG = "LatinIME";
    private static final boolean TRACE = false;
    private BydThemeHelper bydThemeHelper;
    private int mCurDisplayId;
    private final BroadcastReceiver mDictionaryDumpBroadcastReceiver;
    private final DictionaryFacilitator mDictionaryFacilitator;
    private final BroadcastReceiver mDictionaryPackInstallReceiver;

    @Nullable
    private Context mDisplayContext;
    private EmojiAltPhysicalKeyDetector mEmojiAltPhysicalKeyDetector;
    private GestureConsumer mGestureConsumer;
    public final UIHandler mHandler;
    final SparseArray<HardwareEventDecoder> mHardwareEventDecoders;
    final HideSoftInputReceiver mHideSoftInputReceiver;
    final InputLogic mInputLogic;
    private View mInputView;
    private ViewOutlineProviderCompatUtils.InsetsUpdater mInsetsUpdater;
    private boolean mIsExecutingStartShowingInputView;
    private final boolean mIsHardwareAcceleratedDrawingEnabled;
    final KeyboardSwitcher mKeyboardSwitcher;
    private MultiInputMethodSubtypePopupWindow mMultiInputMethodSubtypePopupWindow;
    private AlertDialog mOptionsDialog;
    private RichInputMethodManager mRichImm;
    private final BroadcastReceiver mRingerModeChangeReceiver;
    final Settings mSettings;
    private StatsUtilsManager mStatsUtilsManager;
    private final SubtypeState mSubtypeState;
    private SuggestionStripView mSuggestionStripView;
    public boolean shouldResetInputView;
    static final long DELAY_WAIT_FOR_DICTIONARY_LOAD_MILLIS = TimeUnit.SECONDS.toMillis(2);
    static final long DELAY_DEALLOCATE_MEMORY_MILLIS = TimeUnit.SECONDS.toMillis(10);

    SuggestedWords getSuggestedWordsForTest() {
        return null;
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onCancelInput() {
    }

    @Override // android.inputmethodservice.InputMethodService
    public void setCandidatesView(final View view) {
    }

    static {
        JniUtils.loadNativeLibrary();
    }

    static final class HideSoftInputReceiver extends BroadcastReceiver {
        private final InputMethodService mIms;

        public HideSoftInputReceiver(InputMethodService ims) {
            this.mIms = ims;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.android.inputmethod.latin.HIDE_SOFT_INPUT".equals(intent.getAction())) {
                this.mIms.requestHideSelf(0);
            } else {
                Log.e(LatinIME.TAG, "Unexpected intent " + intent);
            }
        }
    }

    public static final class UIHandler extends LeakGuardHandlerWrapper<LatinIME> {
        private static final int ARG1_DISMISS_GESTURE_FLOATING_PREVIEW_TEXT = 1;
        private static final int ARG1_NOT_GESTURE_INPUT = 0;
        private static final int ARG1_SHOW_GESTURE_FLOATING_PREVIEW_TEXT = 2;
        private static final int ARG1_TRUE = 1;
        private static final int ARG2_UNUSED = 0;
        private static final int MSG_DEALLOCATE_MEMORY = 9;
        private static final int MSG_LAST = 11;
        private static final int MSG_PENDING_IMS_CALLBACK = 1;
        private static final int MSG_REOPEN_DICTIONARIES = 5;
        private static final int MSG_RESET_CACHES = 7;
        private static final int MSG_RESUME_SUGGESTIONS = 4;
        private static final int MSG_RESUME_SUGGESTIONS_FOR_START_INPUT = 10;
        private static final int MSG_SHOW_GESTURE_PREVIEW_AND_SUGGESTION_STRIP = 3;
        private static final int MSG_SWITCH_LANGUAGE_AUTOMATICALLY = 11;
        private static final int MSG_UPDATE_SHIFT_STATE = 0;
        private static final int MSG_UPDATE_SUGGESTION_STRIP = 2;
        private static final int MSG_UPDATE_TAIL_BATCH_INPUT_COMPLETED = 6;
        private static final int MSG_WAIT_FOR_DICTIONARY_LOAD = 8;
        private EditorInfo mAppliedEditorInfo;
        private int mDelayInMillisecondsToUpdateShiftState;
        private int mDelayInMillisecondsToUpdateSuggestions;
        private boolean mHasPendingFinishInput;
        private boolean mHasPendingFinishInputView;
        private boolean mHasPendingStartInput;
        private boolean mIsOrientationChanging;
        private boolean mPendingSuccessiveImsCallback;

        public UIHandler(@Nonnull final LatinIME ownerInstance) {
            super(ownerInstance);
        }

        public void onCreate() {
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance == null) {
                return;
            }
            Resources resources = ownerInstance.getResources();
            this.mDelayInMillisecondsToUpdateSuggestions = resources.getInteger(R.integer.config_delay_in_milliseconds_to_update_suggestions);
            this.mDelayInMillisecondsToUpdateShiftState = resources.getInteger(R.integer.config_delay_in_milliseconds_to_update_shift_state);
        }

        @Override // android.os.Handler
        public void handleMessage(final Message msg) {
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance == null) {
                return;
            }
            KeyboardSwitcher keyboardSwitcher = ownerInstance.mKeyboardSwitcher;
            int i = msg.what;
            if (i != 0) {
                switch (i) {
                    case 2:
                        cancelUpdateSuggestionStrip();
                        ownerInstance.mInputLogic.performUpdateSuggestionStripSync(ownerInstance.mSettings.getCurrent(), msg.arg1);
                        break;
                    case 3:
                        if (msg.arg1 == 0) {
                            ownerInstance.showSuggestionStrip((SuggestedWords) msg.obj);
                        } else {
                            ownerInstance.showGesturePreviewAndSuggestionStrip((SuggestedWords) msg.obj, msg.arg1 == 1);
                        }
                        break;
                    case 4:
                        ownerInstance.mInputLogic.restartSuggestionsOnWordTouchedByCursor(ownerInstance.mSettings.getCurrent(), false, ownerInstance.mKeyboardSwitcher.getCurrentKeyboardScriptId());
                        break;
                    case 5:
                        postWaitForDictionaryLoad();
                        ownerInstance.resetDictionaryFacilitatorIfNecessary();
                        break;
                    case 6:
                        SuggestedWords suggestedWords = (SuggestedWords) msg.obj;
                        ownerInstance.mInputLogic.onUpdateTailBatchInputCompleted(ownerInstance.mSettings.getCurrent(), suggestedWords, ownerInstance.mKeyboardSwitcher);
                        ownerInstance.onTailBatchInputResultShown(suggestedWords);
                        break;
                    case 7:
                        SettingsValues current = ownerInstance.mSettings.getCurrent();
                        if (ownerInstance.mInputLogic.retryResetCachesAndReturnSuccess(msg.arg1 == 1, msg.arg2, this)) {
                            ownerInstance.mKeyboardSwitcher.loadKeyboard(ownerInstance.getCurrentInputEditorInfo(), current, ownerInstance.getCurrentAutoCapsState(), ownerInstance.getCurrentRecapitalizeState());
                        }
                        break;
                    case 8:
                        Log.i(LatinIME.TAG, "Timeout waiting for dictionary load");
                        break;
                    case 9:
                        ownerInstance.deallocateMemory();
                        break;
                    case 10:
                        ownerInstance.mInputLogic.restartSuggestionsOnWordTouchedByCursor(ownerInstance.mSettings.getCurrent(), true, ownerInstance.mKeyboardSwitcher.getCurrentKeyboardScriptId());
                        break;
                    case 11:
                        ownerInstance.switchLanguage((InputMethodSubtype) msg.obj);
                        break;
                }
                return;
            }
            keyboardSwitcher.requestUpdatingShiftState(ownerInstance.getCurrentAutoCapsState(), ownerInstance.getCurrentRecapitalizeState());
        }

        public void postUpdateSuggestionStrip(final int inputStyle) {
            sendMessageDelayed(obtainMessage(2, inputStyle, 0), this.mDelayInMillisecondsToUpdateSuggestions);
        }

        public void postReopenDictionaries() {
            sendMessage(obtainMessage(5));
        }

        private void postResumeSuggestionsInternal(final boolean shouldDelay, final boolean forStartInput) {
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance != null && ownerInstance.mSettings.getCurrent().isSuggestionsEnabledPerUserSettings()) {
                removeMessages(4);
                removeMessages(10);
                int i = forStartInput ? 10 : 4;
                if (shouldDelay) {
                    sendMessageDelayed(obtainMessage(i), this.mDelayInMillisecondsToUpdateSuggestions);
                } else {
                    sendMessage(obtainMessage(i));
                }
            }
        }

        public void postResumeSuggestions(final boolean shouldDelay) {
            postResumeSuggestionsInternal(shouldDelay, false);
        }

        public void postResumeSuggestionsForStartInput(final boolean shouldDelay) {
            postResumeSuggestionsInternal(shouldDelay, true);
        }

        public void postResetCaches(boolean z, int i) {
            removeMessages(7);
            sendMessage(obtainMessage(7, z ? 1 : 0, i, null));
        }

        public void postWaitForDictionaryLoad() {
            sendMessageDelayed(obtainMessage(8), LatinIME.DELAY_WAIT_FOR_DICTIONARY_LOAD_MILLIS);
        }

        public void cancelWaitForDictionaryLoad() {
            removeMessages(8);
        }

        public boolean hasPendingWaitForDictionaryLoad() {
            return hasMessages(8);
        }

        public void cancelUpdateSuggestionStrip() {
            removeMessages(2);
        }

        public boolean hasPendingUpdateSuggestions() {
            return hasMessages(2);
        }

        public boolean hasPendingReopenDictionaries() {
            return hasMessages(5);
        }

        public void postUpdateShiftState() {
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0), this.mDelayInMillisecondsToUpdateShiftState);
        }

        public void postDeallocateMemory() {
            sendMessageDelayed(obtainMessage(9), LatinIME.DELAY_DEALLOCATE_MEMORY_MILLIS);
        }

        public void cancelDeallocateMemory() {
            removeMessages(9);
        }

        public boolean hasPendingDeallocateMemory() {
            return hasMessages(9);
        }

        public void removeAllMessages() {
            for (int i = 0; i <= 11; i++) {
                removeMessages(i);
            }
        }

        public void showGesturePreviewAndSuggestionStrip(final SuggestedWords suggestedWords, final boolean dismissGestureFloatingPreviewText) {
            removeMessages(3);
            obtainMessage(3, dismissGestureFloatingPreviewText ? 1 : 2, 0, suggestedWords).sendToTarget();
        }

        public void showSuggestionStrip(final SuggestedWords suggestedWords) {
            removeMessages(3);
            obtainMessage(3, 0, 0, suggestedWords).sendToTarget();
        }

        public void showTailBatchInputResult(final SuggestedWords suggestedWords) {
            obtainMessage(6, suggestedWords).sendToTarget();
        }

        public void postSwitchLanguage(final InputMethodSubtype subtype) {
            obtainMessage(11, subtype).sendToTarget();
        }

        public void startOrientationChanging() {
            removeMessages(1);
            resetPendingImsCallback();
            this.mIsOrientationChanging = true;
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance != null && ownerInstance.isInputViewShown()) {
                ownerInstance.mKeyboardSwitcher.saveKeyboardState();
            }
        }

        private void resetPendingImsCallback() {
            this.mHasPendingFinishInputView = false;
            this.mHasPendingFinishInput = false;
            this.mHasPendingStartInput = false;
        }

        private void executePendingImsCallback(final LatinIME latinIme, final EditorInfo editorInfo, boolean restarting) {
            if (this.mHasPendingFinishInputView) {
                latinIme.onFinishInputViewInternal(this.mHasPendingFinishInput);
            }
            if (this.mHasPendingFinishInput) {
                latinIme.onFinishInputInternal();
            }
            if (this.mHasPendingStartInput) {
                latinIme.onStartInputInternal(editorInfo, restarting);
            }
            resetPendingImsCallback();
        }

        public void onStartInput(final EditorInfo editorInfo, final boolean restarting) {
            if (hasMessages(1)) {
                this.mHasPendingStartInput = true;
                return;
            }
            if (this.mIsOrientationChanging && restarting) {
                this.mIsOrientationChanging = false;
                this.mPendingSuccessiveImsCallback = true;
            }
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance != null) {
                executePendingImsCallback(ownerInstance, editorInfo, restarting);
                ownerInstance.onStartInputInternal(editorInfo, restarting);
            }
        }

        public void onStartInputView(final EditorInfo editorInfo, final boolean restarting) {
            if (hasMessages(1) && KeyboardId.equivalentEditorInfoForKeyboard(editorInfo, this.mAppliedEditorInfo)) {
                resetPendingImsCallback();
                return;
            }
            if (this.mPendingSuccessiveImsCallback) {
                this.mPendingSuccessiveImsCallback = false;
                resetPendingImsCallback();
                sendMessageDelayed(obtainMessage(1), 800L);
            }
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance != null) {
                executePendingImsCallback(ownerInstance, editorInfo, restarting);
                ownerInstance.onStartInputViewInternal(editorInfo, restarting);
                this.mAppliedEditorInfo = editorInfo;
            }
            cancelDeallocateMemory();
        }

        public void onFinishInputView(final boolean finishingInput) {
            if (hasMessages(1)) {
                this.mHasPendingFinishInputView = true;
                return;
            }
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance != null) {
                ownerInstance.onFinishInputViewInternal(finishingInput);
                this.mAppliedEditorInfo = null;
            }
            if (hasPendingDeallocateMemory()) {
                return;
            }
            postDeallocateMemory();
        }

        public void onFinishInput() {
            if (hasMessages(1)) {
                this.mHasPendingFinishInput = true;
                return;
            }
            LatinIME ownerInstance = getOwnerInstance();
            if (ownerInstance != null) {
                executePendingImsCallback(ownerInstance, null, false);
                ownerInstance.onFinishInputInternal();
            }
        }
    }

    static final class SubtypeState {
        private boolean mCurrentSubtypeHasBeenUsed;
        private InputMethodSubtype mLastActiveSubtype;

        SubtypeState() {
        }

        public void setCurrentSubtypeHasBeenUsed() {
            this.mCurrentSubtypeHasBeenUsed = true;
        }

        public void switchSubtype(final IBinder token, final RichInputMethodManager richImm) {
            InputMethodSubtype currentInputMethodSubtype = richImm.getInputMethodManager().getCurrentInputMethodSubtype();
            InputMethodSubtype inputMethodSubtype = this.mLastActiveSubtype;
            boolean z = this.mCurrentSubtypeHasBeenUsed;
            if (z) {
                this.mLastActiveSubtype = currentInputMethodSubtype;
                this.mCurrentSubtypeHasBeenUsed = false;
            }
            if (z && richImm.checkIfSubtypeBelongsToThisImeAndEnabled(inputMethodSubtype) && !currentInputMethodSubtype.equals(inputMethodSubtype)) {
                richImm.setInputMethodAndSubtype(token, inputMethodSubtype);
            } else {
                richImm.switchToNextInputMethod(token, true);
            }
        }
    }

    public LatinIME() {
        DictionaryFacilitator dictionaryFacilitator = DictionaryFacilitatorProvider.getDictionaryFacilitator(false);
        this.mDictionaryFacilitator = dictionaryFacilitator;
        this.mInputLogic = new InputLogic(this, this, dictionaryFacilitator);
        this.mHardwareEventDecoders = new SparseArray<>(1);
        this.mSubtypeState = new SubtypeState();
        this.mCurDisplayId = -1;
        this.mDictionaryPackInstallReceiver = new DictionaryPackInstallBroadcastReceiver(this);
        this.mDictionaryDumpBroadcastReceiver = new DictionaryDumpBroadcastReceiver(this);
        this.mHideSoftInputReceiver = new HideSoftInputReceiver(this);
        this.mGestureConsumer = GestureConsumer.NULL_GESTURE_CONSUMER;
        this.mHandler = new UIHandler(this);
        this.mRingerModeChangeReceiver = new BroadcastReceiver() { // from class: com.android.inputmethod.latin.LatinIME.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(final Context context, final Intent intent) {
                if (intent.getAction().equals("android.media.RINGER_MODE_CHANGED")) {
                    AudioAndHapticFeedbackManager.getInstance().onRingerModeChanged();
                }
            }
        };
        this.mSettings = Settings.getInstance();
        this.mKeyboardSwitcher = KeyboardSwitcher.getInstance();
        this.mStatsUtilsManager = StatsUtilsManager.getInstance();
        boolean zEnableHardwareAcceleration = InputMethodServiceCompatUtils.enableHardwareAcceleration(this);
        this.mIsHardwareAcceleratedDrawingEnabled = zEnableHardwareAcceleration;
        Log.i(TAG, "Hardware accelerated drawing: " + zEnableHardwareAcceleration);
        SubtypeManager.getInstance().setLatinIME(this);
    }

    @Override // android.inputmethodservice.InputMethodService, android.app.Service
    public void onCreate() {
        Settings.init(this);
        DebugFlags.init(PreferenceManager.getDefaultSharedPreferences(this));
        RichInputMethodManager.init(this);
        this.mRichImm = RichInputMethodManager.getInstance();
        AudioAndHapticFeedbackManager.init(this);
        AccessibilityUtils.init(this);
        this.mStatsUtilsManager.onCreate(this, this.mDictionaryFacilitator);
        WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
        this.mDisplayContext = createDisplayContext(windowManager.getDefaultDisplay());
        this.mCurDisplayId = windowManager.getDefaultDisplay().getDisplayId();
        KeyboardSwitcher.init(this);
        super.onCreate();
        this.mHandler.onCreate();
        loadSettings();
        resetDictionaryFacilitatorIfNecessary();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.RINGER_MODE_CHANGED");
        registerReceiver(this.mRingerModeChangeReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter2.addDataScheme(SCHEME_PACKAGE);
        registerReceiver(this.mDictionaryPackInstallReceiver, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(DictionaryPackConstants.NEW_DICTIONARY_INTENT_ACTION);
        registerReceiver(this.mDictionaryPackInstallReceiver, intentFilter3);
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction(DictionaryDumpBroadcastReceiver.DICTIONARY_DUMP_INTENT_ACTION);
        registerReceiver(this.mDictionaryDumpBroadcastReceiver, intentFilter4);
        IntentFilter intentFilter5 = new IntentFilter();
        intentFilter5.addAction("com.android.inputmethod.latin.HIDE_SOFT_INPUT");
        registerReceiver(this.mHideSoftInputReceiver, intentFilter5, "com.android.inputmethod.latin.HIDE_SOFT_INPUT", null);
        StatsUtils.onCreate(this.mSettings.getCurrent(), this.mRichImm);
        BydThemeHelper bydThemeHelper = new BydThemeHelper(PreferenceManager.getDefaultSharedPreferences(this));
        this.bydThemeHelper = bydThemeHelper;
        bydThemeHelper.updateThemeByNightMode(this);
        SubtypeManager.getInstance().onCreate();
    }

    void loadSettings() {
        Locale currentSubtypeLocale = this.mRichImm.getCurrentSubtypeLocale();
        this.mSettings.loadSettings(this, currentSubtypeLocale, new InputAttributes(getCurrentInputEditorInfo(), isFullscreenMode(), getPackageName()));
        SettingsValues current = this.mSettings.getCurrent();
        AudioAndHapticFeedbackManager.getInstance().onSettingsChanged(current);
        if (!this.mHandler.hasPendingReopenDictionaries()) {
            resetDictionaryFacilitator(currentSubtypeLocale);
        }
        refreshPersonalizationDictionarySession(current);
        resetDictionaryFacilitatorIfNecessary();
        this.mStatsUtilsManager.onLoadSettings(this, current);
    }

    private void refreshPersonalizationDictionarySession(final SettingsValues currentSettingsValues) {
        if (currentSettingsValues.mUsePersonalizedDicts) {
            return;
        }
        PersonalizationHelper.removeAllUserHistoryDictionaries(this);
        this.mDictionaryFacilitator.clearUserHistoryDictionary(this);
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator.DictionaryInitializationListener
    public void onUpdateMainDictionaryAvailability(final boolean isMainDictionaryAvailable) {
        MainKeyboardView mainKeyboardView = this.mKeyboardSwitcher.getMainKeyboardView();
        if (mainKeyboardView != null) {
            mainKeyboardView.setMainDictionaryAvailability(isMainDictionaryAvailable);
        }
        if (this.mHandler.hasPendingWaitForDictionaryLoad()) {
            this.mHandler.cancelWaitForDictionaryLoad();
            this.mHandler.postResumeSuggestions(false);
        }
    }

    void resetDictionaryFacilitatorIfNecessary() {
        Locale currentSubtypeLocale = this.mRichImm.getCurrentSubtypeLocale();
        if (currentSubtypeLocale == null) {
            Log.e(TAG, "System is reporting no current subtype.");
            currentSubtypeLocale = getResources().getConfiguration().locale;
        }
        if (this.mDictionaryFacilitator.isForLocale(currentSubtypeLocale) && this.mDictionaryFacilitator.isForAccount(this.mSettings.getCurrent().mAccount)) {
            return;
        }
        resetDictionaryFacilitator(currentSubtypeLocale);
    }

    private void resetDictionaryFacilitator(final Locale locale) {
        SettingsValues current = this.mSettings.getCurrent();
        this.mDictionaryFacilitator.resetDictionaries(this, locale, current.mUseContactsDict, current.mUsePersonalizedDicts, false, current.mAccount, "", this);
        if (current.mAutoCorrectionEnabledPerUserSettings) {
            this.mInputLogic.mSuggest.setAutoCorrectionThreshold(current.mAutoCorrectionThreshold);
        }
        this.mInputLogic.mSuggest.setPlausibilityThreshold(current.mPlausibilityThreshold);
    }

    void resetSuggestMainDict() {
        SettingsValues current = this.mSettings.getCurrent();
        DictionaryFacilitator dictionaryFacilitator = this.mDictionaryFacilitator;
        dictionaryFacilitator.resetDictionaries(this, dictionaryFacilitator.getLocale(), current.mUseContactsDict, current.mUsePersonalizedDicts, true, current.mAccount, "", this);
    }

    @Override // android.inputmethodservice.InputMethodService, android.inputmethodservice.AbstractInputMethodService, android.app.Service
    public void onDestroy() {
        this.mDictionaryFacilitator.closeDictionaries();
        this.mSettings.onDestroy();
        unregisterReceiver(this.mHideSoftInputReceiver);
        unregisterReceiver(this.mRingerModeChangeReceiver);
        unregisterReceiver(this.mDictionaryPackInstallReceiver);
        unregisterReceiver(this.mDictionaryDumpBroadcastReceiver);
        this.mStatsUtilsManager.onDestroy(this);
        super.onDestroy();
    }

    public void recycle() {
        unregisterReceiver(this.mDictionaryPackInstallReceiver);
        unregisterReceiver(this.mDictionaryDumpBroadcastReceiver);
        unregisterReceiver(this.mRingerModeChangeReceiver);
        this.mInputLogic.recycle();
    }

    private boolean isImeSuppressedByHardwareKeyboard() {
        KeyboardSwitcher keyboardSwitcher = KeyboardSwitcher.getInstance();
        return !onEvaluateInputViewShown() && keyboardSwitcher.isImeSuppressedByHardwareKeyboard(this.mSettings.getCurrent(), keyboardSwitcher.getKeyboardSwitchState());
    }

    @Override // android.inputmethodservice.InputMethodService, android.inputmethodservice.AbstractInputMethodService, android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration conf) {
        SettingsValues current = this.mSettings.getCurrent();
        if (current.mDisplayOrientation != conf.orientation) {
            this.mHandler.startOrientationChanging();
            this.mInputLogic.onOrientationChange(this.mSettings.getCurrent());
        }
        if (current.mHasHardwareKeyboard != Settings.readHasHardwareKeyboard(conf)) {
            loadSettings();
            this.mSettings.getCurrent();
            if (isImeSuppressedByHardwareKeyboard()) {
                cleanupInternalStateForFinishInput();
            }
        }
        this.bydThemeHelper.updateThemeByNightMode(this);
        this.shouldResetInputView = true;
        SubtypeManager.getInstance().onConfigurationChanged(conf);
        MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow = this.mMultiInputMethodSubtypePopupWindow;
        if (multiInputMethodSubtypePopupWindow != null) {
            multiInputMethodSubtypePopupWindow.dismiss();
        }
        if (isShowingOptionDialog()) {
            this.mOptionsDialog.dismiss();
            this.mOptionsDialog = null;
        }
        super.onConfigurationChanged(conf);
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onInitializeInterface() {
        WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
        int displayId = windowManager.getDefaultDisplay().getDisplayId();
        if (this.mCurDisplayId != displayId) {
            this.mCurDisplayId = displayId;
            Context contextCreateDisplayContext = createDisplayContext(windowManager.getDefaultDisplay());
            this.mDisplayContext = contextCreateDisplayContext;
            this.mKeyboardSwitcher.updateKeyboardTheme(contextCreateDisplayContext);
        }
    }

    @Override // android.inputmethodservice.InputMethodService
    public View onCreateInputView() {
        StatsUtils.onCreateInputView();
        return this.mKeyboardSwitcher.onCreateInputView(this.mDisplayContext, this.mIsHardwareAcceleratedDrawingEnabled);
    }

    @Override // android.inputmethodservice.InputMethodService
    public void setInputView(final View view) {
        super.setInputView(view);
        this.mInputView = view;
        this.mInsetsUpdater = ViewOutlineProviderCompatUtils.setInsetsOutlineProvider(view);
        updateSoftInputWindowLayoutParameters();
        this.mSuggestionStripView = (SuggestionStripView) view.findViewById(R.id.suggestion_strip_view);
        if (hasSuggestionStripView()) {
            this.mSuggestionStripView.setListener(this, view);
        }
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onStartInput(final EditorInfo editorInfo, final boolean restarting) {
        this.mHandler.onStartInput(editorInfo, restarting);
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onStartInputView(final EditorInfo editorInfo, final boolean restarting) {
        this.mHandler.onStartInputView(editorInfo, restarting);
        this.mStatsUtilsManager.onStartInputView();
        SubtypeManager.getInstance().switchToCurrentSubtype();
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onFinishInputView(final boolean finishingInput) {
        StatsUtils.onFinishInputView();
        this.mHandler.onFinishInputView(finishingInput);
        this.mStatsUtilsManager.onFinishInputView();
        this.mGestureConsumer = GestureConsumer.NULL_GESTURE_CONSUMER;
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onFinishInput() {
        this.mHandler.onFinishInput();
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onCurrentInputMethodSubtypeChanged(final InputMethodSubtype subtype) {
        StatsUtils.onSubtypeChanged(this.mRichImm.getCurrentSubtype().getRawSubtype(), subtype);
        this.mRichImm.onSubtypeChanged(subtype);
        this.mInputLogic.onSubtypeChanged(SubtypeLocaleUtils.getCombiningRulesExtraValue(subtype), this.mSettings.getCurrent());
        loadKeyboard();
    }

    void onStartInputInternal(final EditorInfo editorInfo, final boolean restarting) {
        InputMethodSubtype inputMethodSubtypeFindSubtypeByLocale;
        super.onStartInput(editorInfo, restarting);
        Locale primaryHintLocale = EditorInfoCompatUtils.getPrimaryHintLocale(editorInfo);
        if (primaryHintLocale == null || (inputMethodSubtypeFindSubtypeByLocale = this.mRichImm.findSubtypeByLocale(primaryHintLocale)) == null || inputMethodSubtypeFindSubtypeByLocale.equals(this.mRichImm.getCurrentSubtype().getRawSubtype())) {
            return;
        }
        this.mHandler.postSwitchLanguage(inputMethodSubtypeFindSubtypeByLocale);
    }

    void onStartInputViewInternal(final EditorInfo editorInfo, final boolean restarting) {
        super.onStartInputView(editorInfo, restarting);
        this.mDictionaryFacilitator.onStartInput();
        this.mGestureConsumer = GestureConsumer.NULL_GESTURE_CONSUMER;
        this.mRichImm.refreshSubtypeCaches();
        KeyboardSwitcher keyboardSwitcher = this.mKeyboardSwitcher;
        keyboardSwitcher.updateKeyboardTheme(this.mDisplayContext);
        MainKeyboardView mainKeyboardView = keyboardSwitcher.getMainKeyboardView();
        SettingsValues current = this.mSettings.getCurrent();
        if (editorInfo == null) {
            Log.e(TAG, "Null EditorInfo in onStartInputView()");
            return;
        }
        String str = TAG;
        Log.i(str, "Starting input. Cursor position = " + editorInfo.initialSelStart + "," + editorInfo.initialSelEnd);
        if (InputAttributes.inPrivateImeOptions(null, Constants.ImeOption.NO_MICROPHONE_COMPAT, editorInfo)) {
            Log.w(str, "Deprecated private IME option specified: " + editorInfo.privateImeOptions);
            Log.w(str, "Use " + getPackageName() + "." + Constants.ImeOption.NO_MICROPHONE + " instead");
        }
        if (InputAttributes.inPrivateImeOptions(getPackageName(), Constants.ImeOption.FORCE_ASCII, editorInfo)) {
            Log.w(str, "Deprecated private IME option specified: " + editorInfo.privateImeOptions);
            Log.w(str, "Use EditorInfo.IME_FLAG_FORCE_ASCII flag instead");
        }
        if (mainKeyboardView == null) {
            return;
        }
        this.mGestureConsumer = GestureConsumer.newInstance(editorInfo, this.mInputLogic.getPrivateCommandPerformer(), this.mRichImm.getCurrentSubtypeLocale(), keyboardSwitcher.getKeyboard());
        AccessibilityUtils accessibilityUtils = AccessibilityUtils.getInstance();
        if (accessibilityUtils.isTouchExplorationEnabled()) {
            accessibilityUtils.onStartInputViewInternal(mainKeyboardView, editorInfo, restarting);
        }
        boolean z = true;
        boolean z2 = !restarting || (current.isSameInputType(editorInfo) ^ true);
        StatsUtils.onStartInputView(editorInfo.inputType, Settings.getInstance().getCurrent().mDisplayOrientation, !z2);
        updateFullscreenMode();
        Suggest suggest = this.mInputLogic.mSuggest;
        if (isImeSuppressedByHardwareKeyboard()) {
            z = false;
        } else {
            this.mInputLogic.startInput(this.mRichImm.getCombiningRulesExtraValueOfCurrentSubtype(), current);
            resetDictionaryFacilitatorIfNecessary();
            if (!this.mInputLogic.mConnection.resetCachesUponCursorMoveAndReturnSuccess(editorInfo.initialSelStart, editorInfo.initialSelEnd, false)) {
                this.mHandler.postResetCaches(z2, 5);
            } else {
                this.mInputLogic.mConnection.tryFixLyingCursorPosition();
                this.mHandler.postResumeSuggestionsForStartInput(true);
                z = false;
            }
        }
        if (z2 || !current.hasSameOrientation(getResources().getConfiguration())) {
            loadSettings();
        }
        if (z2) {
            mainKeyboardView.closing();
            current = this.mSettings.getCurrent();
            if (current.mAutoCorrectionEnabledPerUserSettings) {
                suggest.setAutoCorrectionThreshold(current.mAutoCorrectionThreshold);
            }
            suggest.setPlausibilityThreshold(current.mPlausibilityThreshold);
            keyboardSwitcher.loadKeyboard(editorInfo, current, getCurrentAutoCapsState(), getCurrentRecapitalizeState());
            if (z) {
                keyboardSwitcher.saveKeyboardState();
            }
        } else if (restarting) {
            keyboardSwitcher.resetKeyboardStateToAlphabet(getCurrentAutoCapsState(), getCurrentRecapitalizeState());
            keyboardSwitcher.requestUpdatingShiftState(getCurrentAutoCapsState(), getCurrentRecapitalizeState());
        }
        setNeutralSuggestionStrip();
        this.mHandler.cancelUpdateSuggestionStrip();
        mainKeyboardView.setMainDictionaryAvailability(this.mDictionaryFacilitator.hasAtLeastOneInitializedMainDictionary());
        mainKeyboardView.setKeyPreviewPopupEnabled(current.mKeyPreviewPopupOn, current.mKeyPreviewPopupDismissDelay);
        mainKeyboardView.setSlidingKeyInputPreviewEnabled(current.mSlidingKeyInputPreviewEnabled);
        mainKeyboardView.setGestureHandlingEnabledByUser(current.mGestureInputEnabled, current.mGestureTrailEnabled, current.mGestureFloatingPreviewTextEnabled);
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onWindowShown() {
        super.onWindowShown();
        setNavigationBarVisibility(isInputViewShown());
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onWindowHidden() {
        super.onWindowHidden();
        MainKeyboardView mainKeyboardView = this.mKeyboardSwitcher.getMainKeyboardView();
        if (mainKeyboardView != null) {
            mainKeyboardView.closing();
        }
        setNavigationBarVisibility(false);
        MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow = this.mMultiInputMethodSubtypePopupWindow;
        if (multiInputMethodSubtypePopupWindow != null) {
            multiInputMethodSubtypePopupWindow.dismiss();
        }
    }

    void onFinishInputInternal() {
        super.onFinishInput();
        this.mDictionaryFacilitator.onFinishInput(this);
        MainKeyboardView mainKeyboardView = this.mKeyboardSwitcher.getMainKeyboardView();
        if (mainKeyboardView != null) {
            mainKeyboardView.closing();
        }
    }

    void onFinishInputViewInternal(final boolean finishingInput) {
        super.onFinishInputView(finishingInput);
        cleanupInternalStateForFinishInput();
    }

    private void cleanupInternalStateForFinishInput() {
        this.mHandler.cancelUpdateSuggestionStrip();
        this.mInputLogic.finishInput();
    }

    protected void deallocateMemory() {
        this.mKeyboardSwitcher.deallocateMemory();
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onUpdateSelection(final int oldSelStart, final int oldSelEnd, final int newSelStart, final int newSelEnd, final int composingSpanStart, final int composingSpanEnd) {
        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd, composingSpanStart, composingSpanEnd);
        SettingsValues current = this.mSettings.getCurrent();
        if (isInputViewShown() && this.mInputLogic.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd, current)) {
            this.mKeyboardSwitcher.requestUpdatingShiftState(getCurrentAutoCapsState(), getCurrentRecapitalizeState());
        }
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onExtractedTextClicked() {
        if (this.mSettings.getCurrent().needsToLookupSuggestions()) {
            return;
        }
        super.onExtractedTextClicked();
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onExtractedCursorMovement(final int dx, final int dy) {
        if (this.mSettings.getCurrent().needsToLookupSuggestions()) {
            return;
        }
        super.onExtractedCursorMovement(dx, dy);
    }

    @Override // android.inputmethodservice.InputMethodService
    public void hideWindow() {
        this.mKeyboardSwitcher.onHideWindow();
        if (isShowingOptionDialog()) {
            this.mOptionsDialog.dismiss();
            this.mOptionsDialog = null;
        }
        super.hideWindow();
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onDisplayCompletions(final CompletionInfo[] applicationSpecifiedCompletions) {
        if (this.mSettings.getCurrent().isApplicationSpecifiedCompletionsOn()) {
            this.mHandler.cancelUpdateSuggestionStrip();
            if (applicationSpecifiedCompletions == null) {
                setNeutralSuggestionStrip();
            } else {
                setSuggestedWords(new SuggestedWords(SuggestedWords.getFromApplicationSpecifiedCompletions(applicationSpecifiedCompletions), null, null, false, false, false, 4, -1));
            }
        }
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onComputeInsets(final InputMethodService.Insets outInsets) {
        super.onComputeInsets(outInsets);
        if (this.mInputView == null) {
            return;
        }
        this.mSettings.getCurrent();
        View visibleKeyboardView = this.mKeyboardSwitcher.getVisibleKeyboardView();
        if (visibleKeyboardView == null || !hasSuggestionStripView()) {
            return;
        }
        int height = this.mInputView.getHeight();
        if (isImeSuppressedByHardwareKeyboard() && !visibleKeyboardView.isShown()) {
            outInsets.contentTopInsets = height;
            outInsets.visibleTopInsets = height;
            this.mInsetsUpdater.setInsets(outInsets);
            return;
        }
        int height2 = (height - visibleKeyboardView.getHeight()) - ((this.mKeyboardSwitcher.isShowingEmojiPalettes() || this.mSuggestionStripView.getVisibility() != 0) ? 0 : this.mSuggestionStripView.getHeight());
        this.mSuggestionStripView.setMoreSuggestionsHeight(height2);
        if (visibleKeyboardView.isShown()) {
            int i = this.mKeyboardSwitcher.isShowingMoreKeysPanel() ? 0 : height2;
            int width = visibleKeyboardView.getWidth();
            outInsets.touchableInsets = 3;
            outInsets.touchableRegion.set(0, i, width, height);
        }
        outInsets.contentTopInsets = height2;
        outInsets.visibleTopInsets = height2;
        this.mInsetsUpdater.setInsets(outInsets);
    }

    public void startShowingInputView(final boolean needsToLoadKeyboard) {
        this.mIsExecutingStartShowingInputView = true;
        showWindow(true);
        this.mIsExecutingStartShowingInputView = false;
        if (needsToLoadKeyboard) {
            loadKeyboard();
        }
    }

    public void stopShowingInputView() {
        showWindow(false);
    }

    @Override // android.inputmethodservice.InputMethodService
    public boolean onShowInputRequested(final int flags, final boolean configChange) {
        if (isImeSuppressedByHardwareKeyboard()) {
            return true;
        }
        return super.onShowInputRequested(flags, configChange);
    }

    @Override // android.inputmethodservice.InputMethodService
    public boolean onEvaluateInputViewShown() {
        if (this.mIsExecutingStartShowingInputView) {
            return true;
        }
        return super.onEvaluateInputViewShown();
    }

    @Override // android.inputmethodservice.InputMethodService
    public boolean onEvaluateFullscreenMode() {
        this.mSettings.getCurrent();
        if (isImeSuppressedByHardwareKeyboard()) {
            return false;
        }
        boolean useFullscreenMode = Settings.readUseFullscreenMode(getResources());
        if (!super.onEvaluateFullscreenMode() || !useFullscreenMode) {
            return false;
        }
        EditorInfo currentInputEditorInfo = getCurrentInputEditorInfo();
        return currentInputEditorInfo == null || (currentInputEditorInfo.imeOptions & SuggestedWords.SuggestedWordInfo.KIND_FLAG_APPROPRIATE_FOR_AUTO_CORRECTION) == 0;
    }

    @Override // android.inputmethodservice.InputMethodService
    public void updateFullscreenMode() {
        super.updateFullscreenMode();
        updateSoftInputWindowLayoutParameters();
    }

    private void updateSoftInputWindowLayoutParameters() {
        Window window = getWindow().getWindow();
        ViewLayoutUtils.updateLayoutHeightOf(window, -1);
        if (this.mInputView != null) {
            int i = isFullscreenMode() ? -2 : -1;
            View viewFindViewById = window.findViewById(android.R.id.inputArea);
            ViewLayoutUtils.updateLayoutHeightOf(viewFindViewById, i);
            ViewLayoutUtils.updateLayoutGravityOf(viewFindViewById, 80);
            ViewLayoutUtils.updateLayoutHeightOf(this.mInputView, i);
        }
    }

    int getCurrentAutoCapsState() {
        return this.mInputLogic.getCurrentAutoCapsState(this.mSettings.getCurrent());
    }

    int getCurrentRecapitalizeState() {
        return this.mInputLogic.getCurrentRecapitalizeState();
    }

    public int[] getCoordinatesForCurrentKeyboard(final int[] codePoints) {
        Keyboard keyboard = this.mKeyboardSwitcher.getKeyboard();
        if (keyboard == null) {
            return CoordinateUtils.newCoordinateArray(codePoints.length, -1, -1);
        }
        return keyboard.getCoordinates(codePoints);
    }

    @Override // com.android.inputmethod.latin.suggestions.SuggestionStripView.Listener
    public void showImportantNoticeContents() {
        PermissionsManager.get(this).requestPermissions(this, null, "android.permission.READ_CONTACTS");
    }

    @Override // com.android.inputmethod.latin.permissions.PermissionsManager.PermissionsResultCallback
    public void onRequestPermissionsResult(boolean allGranted) {
        ImportantNoticeUtils.updateContactsNoticeShown(this);
        setNeutralSuggestionStrip();
    }

    public void displaySettingsDialog() {
        if (isShowingOptionDialog()) {
            return;
        }
        showSubtypeSelectorAndSettings();
    }

    private void showMultiInputMethodSubtypeWindow() {
        MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow = this.mMultiInputMethodSubtypePopupWindow;
        if (multiInputMethodSubtypePopupWindow != null) {
            multiInputMethodSubtypePopupWindow.dismiss();
        }
        MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow2 = new MultiInputMethodSubtypePopupWindow(this);
        this.mMultiInputMethodSubtypePopupWindow = multiInputMethodSubtypePopupWindow2;
        multiInputMethodSubtypePopupWindow2.showInEndBottom(this.mKeyboardSwitcher.getMainKeyboardView());
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public boolean onCustomRequest(final int requestCode) {
        if (isShowingOptionDialog() || requestCode != 1) {
            return false;
        }
        showMultiInputMethodSubtypeWindow();
        return true;
    }

    private boolean isShowingOptionDialog() {
        AlertDialog alertDialog = this.mOptionsDialog;
        return alertDialog != null && alertDialog.isShowing();
    }

    public void switchLanguage(final InputMethodSubtype subtype) {
        this.mRichImm.setInputMethodAndSubtype(getWindow().getWindow().getAttributes().token, subtype);
    }

    public void switchToNextSubtype() {
        IBinder iBinder = getWindow().getWindow().getAttributes().token;
        if (shouldSwitchToOtherInputMethods()) {
            this.mRichImm.switchToNextInputMethod(iBinder, false);
        } else {
            this.mSubtypeState.switchSubtype(iBinder, this.mRichImm);
        }
    }

    private int getCodePointForKeyboard(final int codePoint) {
        if (-1 != codePoint) {
            return codePoint;
        }
        Keyboard keyboard = this.mKeyboardSwitcher.getKeyboard();
        if (keyboard == null || !keyboard.mId.isAlphabetKeyboard()) {
            return -13;
        }
        return codePoint;
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onCodeInput(final int codePoint, final int x, final int y, final boolean isKeyRepeat) {
        MainKeyboardView mainKeyboardView = this.mKeyboardSwitcher.getMainKeyboardView();
        onEvent(createSoftwareKeypressEvent(getCodePointForKeyboard(codePoint), mainKeyboardView.getKeyX(x), mainKeyboardView.getKeyY(y), isKeyRepeat));
    }

    public void onEvent(@Nonnull final Event event) {
        if (-7 == event.mKeyCode) {
            this.mRichImm.switchToShortcutIme(this);
        }
        updateStateAfterInputTransaction(this.mInputLogic.onCodeInput(this.mSettings.getCurrent(), event, this.mKeyboardSwitcher.getKeyboardShiftMode(), this.mKeyboardSwitcher.getCurrentKeyboardScriptId(), this.mHandler));
        this.mKeyboardSwitcher.onEvent(event, getCurrentAutoCapsState(), getCurrentRecapitalizeState());
    }

    @Nonnull
    public static Event createSoftwareKeypressEvent(final int keyCodeOrCodePoint, final int keyX, final int keyY, final boolean isKeyRepeat) {
        int i;
        if (keyCodeOrCodePoint <= 0) {
            i = keyCodeOrCodePoint;
            keyCodeOrCodePoint = -1;
        } else {
            i = 0;
        }
        return Event.createSoftwareKeypressEvent(keyCodeOrCodePoint, i, keyX, keyY, isKeyRepeat);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onTextInput(final String rawText) {
        Event eventCreateSoftwareTextEvent = Event.createSoftwareTextEvent(rawText, -4);
        updateStateAfterInputTransaction(this.mInputLogic.onTextInput(this.mSettings.getCurrent(), eventCreateSoftwareTextEvent, this.mKeyboardSwitcher.getKeyboardShiftMode(), this.mHandler));
        this.mKeyboardSwitcher.onEvent(eventCreateSoftwareTextEvent, getCurrentAutoCapsState(), getCurrentRecapitalizeState());
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onStartBatchInput() {
        this.mInputLogic.onStartBatchInput(this.mSettings.getCurrent(), this.mKeyboardSwitcher, this.mHandler);
        this.mGestureConsumer.onGestureStarted(this.mRichImm.getCurrentSubtypeLocale(), this.mKeyboardSwitcher.getKeyboard());
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onUpdateBatchInput(final InputPointers batchPointers) {
        this.mInputLogic.onUpdateBatchInput(batchPointers);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onEndBatchInput(final InputPointers batchPointers) {
        this.mInputLogic.onEndBatchInput(batchPointers);
        this.mGestureConsumer.onGestureCompleted(batchPointers);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onCancelBatchInput() {
        this.mInputLogic.onCancelBatchInput(this.mHandler);
        this.mGestureConsumer.onGestureCanceled();
    }

    public void onTailBatchInputResultShown(final SuggestedWords suggestedWords) {
        this.mGestureConsumer.onImeSuggestionsProcessed(suggestedWords, this.mInputLogic.getComposingStart(), this.mInputLogic.getComposingLength(), this.mDictionaryFacilitator);
    }

    void showGesturePreviewAndSuggestionStrip(@Nonnull final SuggestedWords suggestedWords, final boolean dismissGestureFloatingPreviewText) {
        showSuggestionStrip(suggestedWords);
        this.mKeyboardSwitcher.getMainKeyboardView().showGestureFloatingPreviewText(suggestedWords, dismissGestureFloatingPreviewText);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onFinishSlidingInput() {
        this.mKeyboardSwitcher.onFinishSlidingInput(getCurrentAutoCapsState(), getCurrentRecapitalizeState());
    }

    public boolean hasSuggestionStripView() {
        return this.mSuggestionStripView != null;
    }

    private void setSuggestedWords(final SuggestedWords suggestedWords) {
        SettingsValues current = this.mSettings.getCurrent();
        this.mInputLogic.setSuggestedWords(suggestedWords);
        if (hasSuggestionStripView() && onEvaluateInputViewShown()) {
            boolean zShouldShowImportantNotice = ImportantNoticeUtils.shouldShowImportantNotice(this, current);
            boolean z = true;
            boolean z2 = (zShouldShowImportantNotice || current.mShowsVoiceInputKey || (current.mInputAttributes.mShouldShowSuggestions && current.isSuggestionsEnabledPerUserSettings()) || current.isApplicationSpecifiedCompletionsOn()) && !current.mInputAttributes.mIsPasswordField;
            this.mSuggestionStripView.updateVisibility(z2, isFullscreenMode());
            if (z2) {
                boolean z3 = suggestedWords.isEmpty() || suggestedWords.isPunctuationSuggestions() || (current.isApplicationSpecifiedCompletionsOn() && suggestedWords.isEmpty());
                boolean z4 = suggestedWords.mInputStyle == 7;
                if (!z3 && !z4) {
                    z = false;
                }
                if (zShouldShowImportantNotice && z && this.mSuggestionStripView.maybeShowImportantNoticeTitle()) {
                    return;
                }
                if (current.isSuggestionsEnabledPerUserSettings() || current.isApplicationSpecifiedCompletionsOn() || z3) {
                    this.mSuggestionStripView.setSuggestions(suggestedWords, this.mRichImm.getCurrentSubtype().isRtlSubtype());
                }
            }
        }
    }

    public void getSuggestedWords(final int inputStyle, final int sequenceNumber, final Suggest.OnGetSuggestedWordsCallback callback) {
        Keyboard keyboard = this.mKeyboardSwitcher.getKeyboard();
        if (keyboard == null) {
            callback.onGetSuggestedWords(SuggestedWords.getEmptyInstance());
        } else {
            this.mInputLogic.getSuggestedWords(this.mSettings.getCurrent(), keyboard, this.mKeyboardSwitcher.getKeyboardShiftMode(), inputStyle, sequenceNumber, callback);
        }
    }

    @Override // com.android.inputmethod.latin.suggestions.SuggestionStripViewAccessor
    public void showSuggestionStrip(final SuggestedWords suggestedWords) {
        if (suggestedWords.isEmpty()) {
            setNeutralSuggestionStrip();
        } else {
            setSuggestedWords(suggestedWords);
        }
        AccessibilityUtils.getInstance().setAutoCorrection(suggestedWords);
    }

    @Override // com.android.inputmethod.latin.suggestions.SuggestionStripView.Listener
    public void pickSuggestionManually(final SuggestedWords.SuggestedWordInfo suggestionInfo) {
        updateStateAfterInputTransaction(this.mInputLogic.onPickSuggestionManually(this.mSettings.getCurrent(), suggestionInfo, this.mKeyboardSwitcher.getKeyboardShiftMode(), this.mKeyboardSwitcher.getCurrentKeyboardScriptId(), this.mHandler));
    }

    @Override // com.android.inputmethod.latin.suggestions.SuggestionStripView.Listener
    public void onHideIME() {
        requestHideSelf(0);
    }

    @Override // com.android.inputmethod.latin.suggestions.SuggestionStripViewAccessor
    public void setNeutralSuggestionStrip() {
        SuggestedWords emptyInstance;
        SettingsValues current = this.mSettings.getCurrent();
        if (current.mBigramPredictionEnabled) {
            emptyInstance = SuggestedWords.getEmptyInstance();
        } else {
            emptyInstance = current.mSpacingAndPunctuations.mSuggestPuncList;
        }
        setSuggestedWords(emptyInstance);
    }

    void loadKeyboard() {
        this.mHandler.postReopenDictionaries();
        loadSettings();
        if (this.mKeyboardSwitcher.getMainKeyboardView() != null) {
            this.mKeyboardSwitcher.loadKeyboard(getCurrentInputEditorInfo(), this.mSettings.getCurrent(), getCurrentAutoCapsState(), getCurrentRecapitalizeState());
        }
    }

    private void updateStateAfterInputTransaction(final InputTransaction inputTransaction) {
        int requiredShiftUpdate = inputTransaction.getRequiredShiftUpdate();
        int i = 1;
        if (requiredShiftUpdate == 1) {
            this.mKeyboardSwitcher.requestUpdatingShiftState(getCurrentAutoCapsState(), getCurrentRecapitalizeState());
        } else if (requiredShiftUpdate == 2) {
            this.mHandler.postUpdateShiftState();
        }
        if (inputTransaction.requiresUpdateSuggestions()) {
            if (inputTransaction.mEvent.isSuggestionStripPress()) {
                i = 0;
            } else if (inputTransaction.mEvent.isGesture()) {
                i = 3;
            }
            this.mHandler.postUpdateSuggestionStrip(i);
        }
        if (inputTransaction.didAffectContents()) {
            this.mSubtypeState.setCurrentSubtypeHasBeenUsed();
        }
    }

    private void hapticAndAudioFeedback(final int code, final int repeatCount) {
        MainKeyboardView mainKeyboardView = this.mKeyboardSwitcher.getMainKeyboardView();
        if (mainKeyboardView == null || !mainKeyboardView.isInDraggingFinger()) {
            if (repeatCount <= 0 || ((code != -5 || this.mInputLogic.mConnection.canDeleteCharacters()) && repeatCount % 2 != 0)) {
                AudioAndHapticFeedbackManager audioAndHapticFeedbackManager = AudioAndHapticFeedbackManager.getInstance();
                if (repeatCount == 0) {
                    audioAndHapticFeedbackManager.performHapticFeedback(mainKeyboardView);
                }
                audioAndHapticFeedbackManager.performAudioFeedback(code);
            }
        }
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onPressKey(final int primaryCode, final int repeatCount, final boolean isSinglePointer) {
        this.mKeyboardSwitcher.onPressKey(primaryCode, isSinglePointer, getCurrentAutoCapsState(), getCurrentRecapitalizeState());
        hapticAndAudioFeedback(primaryCode, repeatCount);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardActionListener
    public void onReleaseKey(final int primaryCode, final boolean withSliding) {
        this.mKeyboardSwitcher.onReleaseKey(primaryCode, withSliding, getCurrentAutoCapsState(), getCurrentRecapitalizeState());
    }

    private HardwareEventDecoder getHardwareKeyEventDecoder(final int deviceId) {
        HardwareEventDecoder hardwareEventDecoder = this.mHardwareEventDecoders.get(deviceId);
        if (hardwareEventDecoder != null) {
            return hardwareEventDecoder;
        }
        HardwareKeyboardEventDecoder hardwareKeyboardEventDecoder = new HardwareKeyboardEventDecoder(deviceId);
        this.mHardwareEventDecoders.put(deviceId, hardwareKeyboardEventDecoder);
        return hardwareKeyboardEventDecoder;
    }

    @Override // android.inputmethodservice.InputMethodService, android.view.KeyEvent.Callback
    public boolean onKeyDown(final int keyCode, final KeyEvent keyEvent) {
        if (this.mEmojiAltPhysicalKeyDetector == null) {
            this.mEmojiAltPhysicalKeyDetector = new EmojiAltPhysicalKeyDetector(getApplicationContext().getResources());
        }
        this.mEmojiAltPhysicalKeyDetector.onKeyDown(keyEvent);
        return super.onKeyDown(keyCode, keyEvent);
    }

    @Override // android.inputmethodservice.InputMethodService, android.view.KeyEvent.Callback
    public boolean onKeyUp(final int keyCode, final KeyEvent keyEvent) {
        if (this.mEmojiAltPhysicalKeyDetector == null) {
            this.mEmojiAltPhysicalKeyDetector = new EmojiAltPhysicalKeyDetector(getApplicationContext().getResources());
        }
        this.mEmojiAltPhysicalKeyDetector.onKeyUp(keyEvent);
        return super.onKeyUp(keyCode, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startActivityOnTheSameDisplay(Intent intent) {
        startActivity(intent, ActivityOptions.makeBasic().setLaunchDisplayId(((WindowManager) getSystemService("window")).getDefaultDisplay().getDisplayId()).toBundle());
    }

    void launchSettings(final String extraEntryValue) {
        this.mInputLogic.commitTyped(this.mSettings.getCurrent(), "");
        requestHideSelf(0);
        MainKeyboardView mainKeyboardView = this.mKeyboardSwitcher.getMainKeyboardView();
        if (mainKeyboardView != null) {
            mainKeyboardView.closing();
        }
        Intent intent = new Intent();
        intent.setClass(this, SettingsActivity.class);
        intent.setFlags(337641472);
        intent.putExtra(SettingsActivity.EXTRA_SHOW_HOME_AS_UP, false);
        intent.putExtra(SettingsActivity.EXTRA_ENTRY_KEY, extraEntryValue);
        startActivityOnTheSameDisplay(intent);
    }

    private void showSubtypeSelectorAndSettings() {
        String string = getString(R.string.english_ime_input_options);
        final String string2 = getString(R.string.language_selection_title);
        CharSequence[] charSequenceArr = {string2, getString(ApplicationUtils.getActivityTitleResId(this, SettingsActivity.class))};
        final String inputMethodIdOfThisIme = this.mRichImm.getInputMethodIdOfThisIme();
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.inputmethod.latin.LatinIME.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface di, int position) {
                di.dismiss();
                if (position == 0) {
                    LatinIME.this.launchSettings(SettingsActivity.EXTRA_ENTRY_VALUE_LONG_PRESS_COMMA);
                } else {
                    if (position != 1) {
                        return;
                    }
                    LatinIME.this.launchSettings(SettingsActivity.EXTRA_ENTRY_VALUE_LONG_PRESS_COMMA);
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogUtils.getPlatformDialogThemeContext(this));
        builder.setItems(charSequenceArr, onClickListener).setTitle(string);
        builder.setItems(new CharSequence[]{charSequenceArr[1]}, onClickListener);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.setCanceledOnTouchOutside(true);
        showOptionDialog(alertDialogCreate);
    }

    public void showOptionDialog(final AlertDialog dialog) {
        IBinder windowToken = this.mKeyboardSwitcher.getMainKeyboardView().getWindowToken();
        if (windowToken == null) {
            return;
        }
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.token = windowToken;
        attributes.type = 1003;
        window.setAttributes(attributes);
        window.addFlags(131072);
        this.mOptionsDialog = dialog;
        dialog.show();
    }

    void waitForLoadingDictionaries(final long timeout, final TimeUnit unit) throws InterruptedException {
        this.mDictionaryFacilitator.waitForLoadingDictionariesForTesting(timeout, unit);
    }

    void replaceDictionariesForTest(final Locale locale) {
        SettingsValues current = this.mSettings.getCurrent();
        this.mDictionaryFacilitator.resetDictionaries(this, locale, current.mUseContactsDict, current.mUsePersonalizedDicts, false, current.mAccount, "", this);
    }

    void clearPersonalizedDictionariesForTest() {
        this.mDictionaryFacilitator.clearUserHistoryDictionary(this);
    }

    List<InputMethodSubtype> getEnabledSubtypesForTest() {
        RichInputMethodManager richInputMethodManager = this.mRichImm;
        return richInputMethodManager != null ? richInputMethodManager.getMyEnabledInputMethodSubtypeList(true) : new ArrayList();
    }

    public void dumpDictionaryForDebug(final String dictName) {
        if (!this.mDictionaryFacilitator.isActive()) {
            resetDictionaryFacilitatorIfNecessary();
        }
        this.mDictionaryFacilitator.dumpDictionaryForDebug(dictName);
    }

    public void debugDumpStateAndCrashWithException(final String context) {
        SettingsValues current = this.mSettings.getCurrent();
        StringBuilder sb = new StringBuilder(current.toString());
        sb.append("\nAttributes : ").append(current.mInputAttributes).append("\nContext : ").append(context);
        throw new RuntimeException(sb.toString());
    }

    @Override // android.inputmethodservice.InputMethodService, android.inputmethodservice.AbstractInputMethodService, android.app.Service
    protected void dump(final FileDescriptor fd, final PrintWriter fout, final String[] args) {
        super.dump(fd, fout, args);
        PrintWriterPrinter printWriterPrinter = new PrintWriterPrinter(fout);
        printWriterPrinter.println("LatinIME state :");
        printWriterPrinter.println("  VersionCode = " + ApplicationUtils.getVersionCode(this));
        printWriterPrinter.println("  VersionName = " + ApplicationUtils.getVersionName(this));
        Keyboard keyboard = this.mKeyboardSwitcher.getKeyboard();
        printWriterPrinter.println("  Keyboard mode = " + (keyboard != null ? keyboard.mId.mMode : -1));
        printWriterPrinter.println(this.mSettings.getCurrent().dump());
        printWriterPrinter.println(this.mDictionaryFacilitator.dump(this));
    }

    public boolean shouldSwitchToOtherInputMethods() {
        boolean z = this.mSettings.getCurrent().mIncludesOtherImesInLanguageSwitchList;
        IBinder iBinder = getWindow().getWindow().getAttributes().token;
        return iBinder == null ? z : this.mRichImm.shouldOfferSwitchingToNextInputMethod(iBinder, z);
    }

    public boolean shouldShowLanguageSwitchKey() {
        boolean zIsLanguageSwitchKeyEnabled = this.mSettings.getCurrent().isLanguageSwitchKeyEnabled();
        IBinder iBinder = getWindow().getWindow().getAttributes().token;
        return iBinder == null ? zIsLanguageSwitchKeyEnabled : this.mRichImm.shouldOfferSwitchingToNextInputMethod(iBinder, zIsLanguageSwitchKeyEnabled);
    }

    private void setNavigationBarVisibility(final boolean visible) {
        if (BuildCompatUtils.EFFECTIVE_SDK_INT > 23) {
            getWindow().getWindow().setNavigationBarColor(visible ? ViewCompat.MEASURED_STATE_MASK : 0);
        }
    }
}
