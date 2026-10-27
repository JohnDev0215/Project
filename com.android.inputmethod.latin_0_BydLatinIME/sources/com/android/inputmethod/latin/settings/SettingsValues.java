package com.android.inputmethod.latin.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import com.android.inputmethod.compat.AppWorkaroundsUtils;
import com.android.inputmethod.latin.InputAttributes;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.utils.AsyncResultHolder;
import com.android.inputmethod.latin.utils.ResourceUtils;
import com.android.inputmethod.latin.utils.TargetPackageInfoGetterTask;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SettingsValues {
    public static final float DEFAULT_SIZE_SCALE = 1.0f;
    private static final String FLOAT_MAX_VALUE_MARKER_STRING = "floatMaxValue";
    private static final String FLOAT_NEGATIVE_INFINITY_MARKER_STRING = "floatNegativeInfinity";
    private static final String SUGGESTIONS_VISIBILITY_HIDE_VALUE_OBSOLETE = "2";
    private static final String TAG = "SettingsValues";
    private static final int TIMEOUT_TO_GET_TARGET_PACKAGE = 5;

    @Nullable
    public final String mAccount;
    private final AsyncResultHolder<AppWorkaroundsUtils> mAppWorkarounds;
    public final boolean mAutoCap;
    private final boolean mAutoCorrectEnabled;
    public final boolean mAutoCorrectionEnabledPerUserSettings;
    public final float mAutoCorrectionThreshold;
    public final boolean mBigramPredictionEnabled;
    public final boolean mBlockPotentiallyOffensive;
    public final boolean mCloudSyncEnabled;
    public final int mDelayInMillisecondsToUpdateOldSuggestions;
    public final int mDisplayOrientation;
    public final long mDoubleSpacePeriodTimeout;
    public final boolean mEnableEmojiAltPhysicalKey;
    public final boolean mEnableMetricsLogging;
    public final boolean mGestureFloatingPreviewTextEnabled;
    public final boolean mGestureInputEnabled;
    public final boolean mGestureTrailEnabled;
    public final boolean mHasCustomKeyPreviewAnimationParams;
    public final boolean mHasHardwareKeyboard;
    public final boolean mHasKeyboardResize;
    public final boolean mIncludesOtherImesInLanguageSwitchList;

    @Nonnull
    public final InputAttributes mInputAttributes;
    public final boolean mIsInternal;
    public final boolean mIsShowAppIconSettingInPreferences;
    public final boolean mIsSplitKeyboardEnabled;
    public final int mKeyLongpressTimeout;
    public final int mKeyPreviewDismissDuration;
    public final float mKeyPreviewDismissEndXScale;
    public final float mKeyPreviewDismissEndYScale;
    public final int mKeyPreviewPopupDismissDelay;
    public final boolean mKeyPreviewPopupOn;
    public final int mKeyPreviewShowUpDuration;
    public final float mKeyPreviewShowUpStartXScale;
    public final float mKeyPreviewShowUpStartYScale;
    public final float mKeyboardHeightScale;
    public final float mKeypressSoundVolume;
    public final int mKeypressVibrationDuration;
    public final Locale mLocale;
    public final float mPlausibilityThreshold;
    public final int mScreenMetrics;
    public final boolean mShouldShowLxxSuggestionUi;
    public final boolean mShowAppIcon;
    public final boolean mShowsLanguageSwitchKey;
    public final boolean mShowsVoiceInputKey;
    public final boolean mSlidingKeyInputPreviewEnabled;
    public final boolean mSoundOn;
    public final SpacingAndPunctuations mSpacingAndPunctuations;
    private final boolean mSuggestionsEnabledPerUserSettings;
    public final boolean mUseContactsDict;
    public final boolean mUseDoubleSpacePeriod;
    public final boolean mUsePersonalizedDicts;
    public final boolean mVibrateOn;

    public SettingsValues(final Context context, final SharedPreferences prefs, final Resources res, @Nonnull final InputAttributes inputAttributes) {
        String string;
        this.mLocale = res.getConfiguration().locale;
        this.mDelayInMillisecondsToUpdateOldSuggestions = res.getInteger(R.integer.config_delay_in_milliseconds_to_update_old_suggestions);
        this.mSpacingAndPunctuations = new SpacingAndPunctuations(res);
        this.mInputAttributes = inputAttributes;
        this.mAutoCap = prefs.getBoolean(Settings.PREF_AUTO_CAP, true);
        this.mVibrateOn = Settings.readVibrationEnabled(prefs, res);
        this.mSoundOn = Settings.readKeypressSoundEnabled(prefs, res);
        this.mKeyPreviewPopupOn = Settings.readKeyPreviewPopupEnabled(prefs, res);
        this.mSlidingKeyInputPreviewEnabled = prefs.getBoolean(DebugSettings.PREF_SLIDING_KEY_INPUT_PREVIEW, true);
        this.mShowsVoiceInputKey = needsToShowVoiceInputKey(prefs, res) && inputAttributes.mShouldShowVoiceInputKey && Build.VERSION.SDK_INT >= 16;
        this.mIncludesOtherImesInLanguageSwitchList = Settings.ENABLE_SHOW_LANGUAGE_SWITCH_KEY_SETTINGS ? prefs.getBoolean(Settings.PREF_INCLUDE_OTHER_IMES_IN_LANGUAGE_SWITCH_LIST, false) : true;
        this.mShowsLanguageSwitchKey = Settings.ENABLE_SHOW_LANGUAGE_SWITCH_KEY_SETTINGS ? Settings.readShowsLanguageSwitchKey(prefs) : true;
        this.mUseContactsDict = prefs.getBoolean(Settings.PREF_KEY_USE_CONTACTS_DICT, false);
        this.mUsePersonalizedDicts = prefs.getBoolean(Settings.PREF_KEY_USE_PERSONALIZED_DICTS, false);
        this.mUseDoubleSpacePeriod = prefs.getBoolean(Settings.PREF_KEY_USE_DOUBLE_SPACE_PERIOD, true) && inputAttributes.mIsGeneralTextInput;
        this.mBlockPotentiallyOffensive = Settings.readBlockPotentiallyOffensive(prefs, res);
        boolean autoCorrectEnabled = Settings.readAutoCorrectEnabled(prefs, res);
        this.mAutoCorrectEnabled = autoCorrectEnabled;
        if (autoCorrectEnabled) {
            string = res.getString(R.string.auto_correction_threshold_mode_index_modest);
        } else {
            string = res.getString(R.string.auto_correction_threshold_mode_index_off);
        }
        this.mBigramPredictionEnabled = readBigramPredictionEnabled(prefs, res);
        this.mDoubleSpacePeriodTimeout = res.getInteger(R.integer.config_double_space_period_timeout);
        this.mHasHardwareKeyboard = Settings.readHasHardwareKeyboard(res.getConfiguration());
        this.mEnableMetricsLogging = prefs.getBoolean(Settings.PREF_ENABLE_METRICS_LOGGING, true);
        this.mIsSplitKeyboardEnabled = prefs.getBoolean(Settings.PREF_ENABLE_SPLIT_KEYBOARD, false);
        this.mScreenMetrics = Settings.readScreenMetrics(res);
        this.mShouldShowLxxSuggestionUi = Settings.SHOULD_SHOW_LXX_SUGGESTION_UI && prefs.getBoolean(DebugSettings.PREF_SHOULD_SHOW_LXX_SUGGESTION_UI, true);
        this.mKeyLongpressTimeout = Settings.readKeyLongpressTimeout(prefs, res);
        this.mKeypressVibrationDuration = Settings.readKeypressVibrationDuration(prefs, res);
        this.mKeypressSoundVolume = Settings.readKeypressSoundVolume(prefs, res);
        this.mKeyPreviewPopupDismissDelay = Settings.readKeyPreviewPopupDismissDelay(prefs, res);
        this.mEnableEmojiAltPhysicalKey = prefs.getBoolean(Settings.PREF_ENABLE_EMOJI_ALT_PHYSICAL_KEY, false);
        this.mShowAppIcon = Settings.readShowSetupWizardIcon(prefs, context);
        this.mIsShowAppIconSettingInPreferences = prefs.contains(Settings.PREF_SHOW_SETUP_WIZARD_ICON);
        this.mAutoCorrectionThreshold = readAutoCorrectionThreshold(res, string);
        this.mPlausibilityThreshold = Settings.readPlausibilityThreshold(res);
        this.mGestureInputEnabled = Settings.readGestureInputEnabled(prefs, res);
        this.mGestureTrailEnabled = prefs.getBoolean(Settings.PREF_GESTURE_PREVIEW_TRAIL, true);
        this.mCloudSyncEnabled = prefs.getBoolean(LocalSettingsConstants.PREF_ENABLE_CLOUD_SYNC, false);
        this.mAccount = prefs.getString(LocalSettingsConstants.PREF_ACCOUNT_NAME, null);
        this.mGestureFloatingPreviewTextEnabled = !inputAttributes.mDisableGestureFloatingPreviewText && prefs.getBoolean(Settings.PREF_GESTURE_FLOATING_PREVIEW_TEXT, true);
        this.mAutoCorrectionEnabledPerUserSettings = autoCorrectEnabled && !inputAttributes.mInputTypeNoAutoCorrect;
        this.mSuggestionsEnabledPerUserSettings = readSuggestionsEnabled(prefs);
        this.mIsInternal = Settings.isInternal(prefs);
        this.mHasCustomKeyPreviewAnimationParams = prefs.getBoolean(DebugSettings.PREF_HAS_CUSTOM_KEY_PREVIEW_ANIMATION_PARAMS, false);
        this.mHasKeyboardResize = prefs.getBoolean(DebugSettings.PREF_RESIZE_KEYBOARD, false);
        this.mKeyboardHeightScale = Settings.readKeyboardHeight(prefs, 1.0f);
        this.mKeyPreviewShowUpDuration = Settings.readKeyPreviewAnimationDuration(prefs, DebugSettings.PREF_KEY_PREVIEW_SHOW_UP_DURATION, res.getInteger(R.integer.config_key_preview_show_up_duration));
        this.mKeyPreviewDismissDuration = Settings.readKeyPreviewAnimationDuration(prefs, DebugSettings.PREF_KEY_PREVIEW_DISMISS_DURATION, res.getInteger(R.integer.config_key_preview_dismiss_duration));
        float floatFromFraction = ResourceUtils.getFloatFromFraction(res, R.fraction.config_key_preview_show_up_start_scale);
        float floatFromFraction2 = ResourceUtils.getFloatFromFraction(res, R.fraction.config_key_preview_dismiss_end_scale);
        this.mKeyPreviewShowUpStartXScale = Settings.readKeyPreviewAnimationScale(prefs, DebugSettings.PREF_KEY_PREVIEW_SHOW_UP_START_X_SCALE, floatFromFraction);
        this.mKeyPreviewShowUpStartYScale = Settings.readKeyPreviewAnimationScale(prefs, DebugSettings.PREF_KEY_PREVIEW_SHOW_UP_START_Y_SCALE, floatFromFraction);
        this.mKeyPreviewDismissEndXScale = Settings.readKeyPreviewAnimationScale(prefs, DebugSettings.PREF_KEY_PREVIEW_DISMISS_END_X_SCALE, floatFromFraction2);
        this.mKeyPreviewDismissEndYScale = Settings.readKeyPreviewAnimationScale(prefs, DebugSettings.PREF_KEY_PREVIEW_DISMISS_END_Y_SCALE, floatFromFraction2);
        this.mDisplayOrientation = res.getConfiguration().orientation;
        AsyncResultHolder<AppWorkaroundsUtils> asyncResultHolder = new AsyncResultHolder<>("AppWorkarounds");
        this.mAppWorkarounds = asyncResultHolder;
        PackageInfo cachedPackageInfo = TargetPackageInfoGetterTask.getCachedPackageInfo(inputAttributes.mTargetApplicationPackageName);
        if (cachedPackageInfo != null) {
            asyncResultHolder.set(new AppWorkaroundsUtils(cachedPackageInfo));
        } else {
            new TargetPackageInfoGetterTask(context, asyncResultHolder).execute(inputAttributes.mTargetApplicationPackageName);
        }
    }

    public boolean isMetricsLoggingEnabled() {
        return this.mEnableMetricsLogging;
    }

    public boolean isApplicationSpecifiedCompletionsOn() {
        return this.mInputAttributes.mApplicationSpecifiedCompletionOn;
    }

    public boolean needsToLookupSuggestions() {
        return this.mInputAttributes.mShouldShowSuggestions && (this.mAutoCorrectionEnabledPerUserSettings || isSuggestionsEnabledPerUserSettings());
    }

    public boolean isSuggestionsEnabledPerUserSettings() {
        return this.mSuggestionsEnabledPerUserSettings;
    }

    public boolean isPersonalizationEnabled() {
        return this.mUsePersonalizedDicts;
    }

    public boolean isWordSeparator(final int code) {
        return this.mSpacingAndPunctuations.isWordSeparator(code);
    }

    public boolean isWordConnector(final int code) {
        return this.mSpacingAndPunctuations.isWordConnector(code);
    }

    public boolean isWordCodePoint(final int code) {
        return Character.isLetter(code) || isWordConnector(code) || 8 == Character.getType(code);
    }

    public boolean isUsuallyPrecededBySpace(final int code) {
        return this.mSpacingAndPunctuations.isUsuallyPrecededBySpace(code);
    }

    public boolean isUsuallyFollowedBySpace(final int code) {
        return this.mSpacingAndPunctuations.isUsuallyFollowedBySpace(code);
    }

    public boolean shouldInsertSpacesAutomatically() {
        return this.mInputAttributes.mShouldInsertSpacesAutomatically;
    }

    public boolean isLanguageSwitchKeyEnabled() {
        if (!this.mShowsLanguageSwitchKey) {
            return false;
        }
        RichInputMethodManager richInputMethodManager = RichInputMethodManager.getInstance();
        if (this.mIncludesOtherImesInLanguageSwitchList) {
            return richInputMethodManager.hasMultipleEnabledIMEsOrSubtypes(false);
        }
        return richInputMethodManager.hasMultipleEnabledSubtypesInThisIme(false);
    }

    public boolean isSameInputType(final EditorInfo editorInfo) {
        return this.mInputAttributes.isSameInputType(editorInfo);
    }

    public boolean hasSameOrientation(final Configuration configuration) {
        return this.mDisplayOrientation == configuration.orientation;
    }

    public boolean isBeforeJellyBean() {
        AppWorkaroundsUtils appWorkaroundsUtils = this.mAppWorkarounds.get(null, 5L);
        if (appWorkaroundsUtils == null) {
            return false;
        }
        return appWorkaroundsUtils.isBeforeJellyBean();
    }

    public boolean isBrokenByRecorrection() {
        AppWorkaroundsUtils appWorkaroundsUtils = this.mAppWorkarounds.get(null, 5L);
        if (appWorkaroundsUtils == null) {
            return false;
        }
        return appWorkaroundsUtils.isBrokenByRecorrection();
    }

    private static boolean readSuggestionsEnabled(final SharedPreferences prefs) {
        if (prefs.contains(Settings.PREF_SHOW_SUGGESTIONS_SETTING_OBSOLETE)) {
            prefs.edit().remove(Settings.PREF_SHOW_SUGGESTIONS_SETTING_OBSOLETE).putBoolean(Settings.PREF_SHOW_SUGGESTIONS, !SUGGESTIONS_VISIBILITY_HIDE_VALUE_OBSOLETE.equals(prefs.getString(Settings.PREF_SHOW_SUGGESTIONS_SETTING_OBSOLETE, null))).apply();
        }
        return prefs.getBoolean(Settings.PREF_SHOW_SUGGESTIONS, true);
    }

    public static boolean readBigramPredictionEnabled(final SharedPreferences prefs, final Resources res) {
        return prefs.getBoolean(Settings.PREF_BIGRAM_PREDICTIONS, res.getBoolean(R.bool.config_default_next_word_prediction));
    }

    private static float readAutoCorrectionThreshold(final Resources res, final String currentAutoCorrectionSetting) {
        String[] stringArray = res.getStringArray(R.array.auto_correction_threshold_values);
        try {
            int i = Integer.parseInt(currentAutoCorrectionSetting);
            if (i < 0 || i >= stringArray.length) {
                return Float.MAX_VALUE;
            }
            String str = stringArray[i];
            if (FLOAT_MAX_VALUE_MARKER_STRING.equals(str)) {
                return Float.MAX_VALUE;
            }
            return FLOAT_NEGATIVE_INFINITY_MARKER_STRING.equals(str) ? Float.NEGATIVE_INFINITY : Float.parseFloat(str);
        } catch (NumberFormatException e) {
            Log.w(TAG, "Cannot load auto correction threshold setting. currentAutoCorrectionSetting: " + currentAutoCorrectionSetting + ", autoCorrectionThresholdValues: " + Arrays.toString(stringArray), e);
            return Float.MAX_VALUE;
        }
    }

    private static boolean needsToShowVoiceInputKey(final SharedPreferences prefs, final Resources res) {
        if (prefs.contains(Settings.PREF_VOICE_MODE_OBSOLETE)) {
            String string = res.getString(R.string.voice_mode_main);
            prefs.edit().putBoolean(Settings.PREF_VOICE_INPUT_KEY, string.equals(prefs.getString(Settings.PREF_VOICE_MODE_OBSOLETE, string))).remove(Settings.PREF_VOICE_MODE_OBSOLETE).apply();
        }
        return prefs.getBoolean(Settings.PREF_VOICE_INPUT_KEY, true);
    }

    public String dump() {
        StringBuilder sb = new StringBuilder("Current settings :");
        sb.append("\n   mSpacingAndPunctuations = ");
        sb.append("" + this.mSpacingAndPunctuations.dump());
        sb.append("\n   mDelayInMillisecondsToUpdateOldSuggestions = ");
        sb.append("" + this.mDelayInMillisecondsToUpdateOldSuggestions);
        sb.append("\n   mAutoCap = ");
        sb.append("" + this.mAutoCap);
        sb.append("\n   mVibrateOn = ");
        sb.append("" + this.mVibrateOn);
        sb.append("\n   mSoundOn = ");
        sb.append("" + this.mSoundOn);
        sb.append("\n   mKeyPreviewPopupOn = ");
        sb.append("" + this.mKeyPreviewPopupOn);
        sb.append("\n   mShowsVoiceInputKey = ");
        sb.append("" + this.mShowsVoiceInputKey);
        sb.append("\n   mIncludesOtherImesInLanguageSwitchList = ");
        sb.append("" + this.mIncludesOtherImesInLanguageSwitchList);
        sb.append("\n   mShowsLanguageSwitchKey = ");
        sb.append("" + this.mShowsLanguageSwitchKey);
        sb.append("\n   mUseContactsDict = ");
        sb.append("" + this.mUseContactsDict);
        sb.append("\n   mUsePersonalizedDicts = ");
        sb.append("" + this.mUsePersonalizedDicts);
        sb.append("\n   mUseDoubleSpacePeriod = ");
        sb.append("" + this.mUseDoubleSpacePeriod);
        sb.append("\n   mBlockPotentiallyOffensive = ");
        sb.append("" + this.mBlockPotentiallyOffensive);
        sb.append("\n   mBigramPredictionEnabled = ");
        sb.append("" + this.mBigramPredictionEnabled);
        sb.append("\n   mGestureInputEnabled = ");
        sb.append("" + this.mGestureInputEnabled);
        sb.append("\n   mGestureTrailEnabled = ");
        sb.append("" + this.mGestureTrailEnabled);
        sb.append("\n   mGestureFloatingPreviewTextEnabled = ");
        sb.append("" + this.mGestureFloatingPreviewTextEnabled);
        sb.append("\n   mSlidingKeyInputPreviewEnabled = ");
        sb.append("" + this.mSlidingKeyInputPreviewEnabled);
        sb.append("\n   mKeyLongpressTimeout = ");
        sb.append("" + this.mKeyLongpressTimeout);
        sb.append("\n   mLocale = ");
        sb.append("" + this.mLocale);
        sb.append("\n   mInputAttributes = ");
        sb.append("" + this.mInputAttributes);
        sb.append("\n   mKeypressVibrationDuration = ");
        sb.append("" + this.mKeypressVibrationDuration);
        sb.append("\n   mKeypressSoundVolume = ");
        sb.append("" + this.mKeypressSoundVolume);
        sb.append("\n   mKeyPreviewPopupDismissDelay = ");
        sb.append("" + this.mKeyPreviewPopupDismissDelay);
        sb.append("\n   mAutoCorrectEnabled = ");
        sb.append("" + this.mAutoCorrectEnabled);
        sb.append("\n   mAutoCorrectionThreshold = ");
        sb.append("" + this.mAutoCorrectionThreshold);
        sb.append("\n   mAutoCorrectionEnabledPerUserSettings = ");
        sb.append("" + this.mAutoCorrectionEnabledPerUserSettings);
        sb.append("\n   mSuggestionsEnabledPerUserSettings = ");
        sb.append("" + this.mSuggestionsEnabledPerUserSettings);
        sb.append("\n   mDisplayOrientation = ");
        sb.append("" + this.mDisplayOrientation);
        sb.append("\n   mAppWorkarounds = ");
        AppWorkaroundsUtils appWorkaroundsUtils = this.mAppWorkarounds.get(null, 0L);
        sb.append("" + (appWorkaroundsUtils == null ? "null" : appWorkaroundsUtils.toString()));
        sb.append("\n   mIsInternal = ");
        sb.append("" + this.mIsInternal);
        sb.append("\n   mKeyPreviewShowUpDuration = ");
        sb.append("" + this.mKeyPreviewShowUpDuration);
        sb.append("\n   mKeyPreviewDismissDuration = ");
        sb.append("" + this.mKeyPreviewDismissDuration);
        sb.append("\n   mKeyPreviewShowUpStartScaleX = ");
        sb.append("" + this.mKeyPreviewShowUpStartXScale);
        sb.append("\n   mKeyPreviewShowUpStartScaleY = ");
        sb.append("" + this.mKeyPreviewShowUpStartYScale);
        sb.append("\n   mKeyPreviewDismissEndScaleX = ");
        sb.append("" + this.mKeyPreviewDismissEndXScale);
        sb.append("\n   mKeyPreviewDismissEndScaleY = ");
        sb.append("" + this.mKeyPreviewDismissEndYScale);
        return sb.toString();
    }
}
