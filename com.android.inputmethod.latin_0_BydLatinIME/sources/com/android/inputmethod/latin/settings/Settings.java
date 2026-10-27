package com.android.inputmethod.latin.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.inputmethod.compat.BuildCompatUtils;
import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.InputAttributes;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.utils.AdditionalSubtypeUtils;
import com.android.inputmethod.latin.utils.ResourceUtils;
import com.android.inputmethod.latin.utils.RunInLocale;
import com.android.inputmethod.latin.utils.StatsUtils;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class Settings implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String DEFAULT_KEYPRESS_SOUND_VOLUME;
    private static final String DEFAULT_KEYPRESS_VIBRATION_DURATION;
    public static final boolean ENABLE_SHOW_LANGUAGE_SWITCH_KEY_SETTINGS;
    public static final String PREF_AUTO_CAP = "auto_cap";
    public static final String PREF_AUTO_CORRECTION = "pref_key_auto_correction";
    public static final String PREF_AUTO_CORRECTION_THRESHOLD_OBSOLETE = "auto_correction_threshold";
    public static final String PREF_BIGRAM_PREDICTIONS = "next_word_prediction";
    public static final String PREF_BLOCK_POTENTIALLY_OFFENSIVE = "pref_key_block_potentially_offensive";
    public static final String PREF_CONFIGURE_DICTIONARIES_KEY = "configure_dictionaries_key";
    private static final String PREF_CORPUS_HANDLES_FOR_PERSONALIZATION = "pref_corpus_handles_for_personalization";
    public static final String PREF_CUSTOM_INPUT_STYLES = "custom_input_styles";
    public static final String PREF_EDIT_PERSONAL_DICTIONARY = "edit_personal_dictionary";
    public static final String PREF_EMOJI_CATEGORY_LAST_TYPED_ID = "emoji_category_last_typed_id";
    public static final String PREF_EMOJI_RECENT_KEYS = "emoji_recent_keys";
    public static final String PREF_ENABLE_EMOJI_ALT_PHYSICAL_KEY = "pref_enable_emoji_alt_physical_key";
    public static final String PREF_ENABLE_METRICS_LOGGING = "pref_enable_metrics_logging";
    public static final String PREF_ENABLE_SPLIT_KEYBOARD = "pref_split_keyboard";
    public static final String PREF_GESTURE_FLOATING_PREVIEW_TEXT = "pref_gesture_floating_preview_text";
    public static final String PREF_GESTURE_INPUT = "gesture_input";
    public static final String PREF_GESTURE_PREVIEW_TRAIL = "pref_gesture_preview_trail";
    public static final String PREF_INCLUDE_OTHER_IMES_IN_LANGUAGE_SWITCH_LIST = "pref_include_other_imes_in_language_switch_list";
    public static final String PREF_KEYPRESS_SOUND_VOLUME = "pref_keypress_sound_volume";
    public static final String PREF_KEY_IS_INTERNAL = "pref_key_is_internal";
    public static final String PREF_KEY_LONGPRESS_TIMEOUT = "pref_key_longpress_timeout";
    public static final String PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY = "pref_key_preview_popup_dismiss_delay";
    public static final String PREF_KEY_USE_CONTACTS_DICT = "pref_key_use_contacts_dict";
    public static final String PREF_KEY_USE_DOUBLE_SPACE_PERIOD = "pref_key_use_double_space_period";
    public static final String PREF_KEY_USE_PERSONALIZED_DICTS = "pref_key_use_personalized_dicts";
    private static final String PREF_LAST_PERSONALIZATION_DICT_WIPED_TIME = "pref_last_used_personalization_dict_wiped_time";
    public static final String PREF_LAST_SHOWN_EMOJI_CATEGORY_ID = "last_shown_emoji_category_id";
    private static final String PREF_LAST_USED_PERSONALIZATION_TOKEN = "pref_last_used_personalization_token";
    public static final String PREF_POPUP_ON = "popup_on";
    public static final String PREF_SHOW_LANGUAGE_SWITCH_KEY = "pref_show_language_switch_key";
    public static final String PREF_SHOW_SETUP_WIZARD_ICON = "pref_show_setup_wizard_icon";
    public static final String PREF_SHOW_SUGGESTIONS = "show_suggestions";
    public static final String PREF_SHOW_SUGGESTIONS_SETTING_OBSOLETE = "show_suggestions_setting";
    public static final String PREF_SOUND_ON = "sound_on";
    private static final String PREF_SUPPRESS_LANGUAGE_SWITCH_KEY = "pref_suppress_language_switch_key";
    public static final String PREF_VIBRATE_ON = "vibrate_on";
    public static final String PREF_VIBRATION_DURATION_SETTINGS = "pref_vibration_duration_settings";
    public static final String PREF_VOICE_INPUT_KEY = "pref_voice_input_key";
    public static final String PREF_VOICE_MODE_OBSOLETE = "voice_mode";
    public static final String SCREEN_ACCOUNTS = "screen_accounts";
    public static final String SCREEN_DEBUG = "screen_debug";
    public static final String SCREEN_THEME = "screen_theme";
    public static final boolean SHOULD_SHOW_LXX_SUGGESTION_UI;
    private static final String TAG = "Settings";
    private static final float UNDEFINED_PREFERENCE_VALUE_FLOAT = -1.0f;
    private static final int UNDEFINED_PREFERENCE_VALUE_INT = -1;
    private static final Settings sInstance;
    private Context mContext;
    private SharedPreferences mPrefs;
    private Resources mRes;
    private SettingsValues mSettingsValues;
    private final ReentrantLock mSettingsValuesLock = new ReentrantLock();

    static {
        ENABLE_SHOW_LANGUAGE_SWITCH_KEY_SETTINGS = BuildCompatUtils.EFFECTIVE_SDK_INT <= 19;
        SHOULD_SHOW_LXX_SUGGESTION_UI = BuildCompatUtils.EFFECTIVE_SDK_INT >= 21;
        sInstance = new Settings();
        DEFAULT_KEYPRESS_SOUND_VOLUME = Float.toString(-1.0f);
        DEFAULT_KEYPRESS_VIBRATION_DURATION = Integer.toString(-1);
    }

    public static Settings getInstance() {
        return sInstance;
    }

    public static void init(final Context context) {
        sInstance.onCreate(context);
    }

    private Settings() {
    }

    private void onCreate(final Context context) {
        this.mContext = context;
        this.mRes = context.getResources();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefs = defaultSharedPreferences;
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        upgradeAutocorrectionSettings(this.mPrefs, this.mRes);
    }

    public void onDestroy() {
        this.mPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
        this.mSettingsValuesLock.lock();
        try {
            SettingsValues settingsValues = this.mSettingsValues;
            if (settingsValues == null) {
                Log.w(TAG, "onSharedPreferenceChanged called before loadSettings.");
            } else {
                loadSettings(this.mContext, settingsValues.mLocale, this.mSettingsValues.mInputAttributes);
                StatsUtils.onLoadSettings(this.mSettingsValues);
            }
        } finally {
            this.mSettingsValuesLock.unlock();
        }
    }

    public void loadSettings(final Context context, final Locale locale, @Nonnull final InputAttributes inputAttributes) {
        this.mSettingsValuesLock.lock();
        this.mContext = context;
        try {
            final SharedPreferences sharedPreferences = this.mPrefs;
            this.mSettingsValues = new RunInLocale<SettingsValues>() { // from class: com.android.inputmethod.latin.settings.Settings.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.android.inputmethod.latin.utils.RunInLocale
                public SettingsValues job(final Resources res) {
                    return new SettingsValues(context, sharedPreferences, res, inputAttributes);
                }
            }.runInLocale(this.mRes, locale);
        } finally {
            this.mSettingsValuesLock.unlock();
        }
    }

    public SettingsValues getCurrent() {
        return this.mSettingsValues;
    }

    public boolean isInternal() {
        return this.mSettingsValues.mIsInternal;
    }

    public static int readScreenMetrics(final Resources res) {
        return res.getInteger(R.integer.config_screen_metrics);
    }

    public static boolean readKeypressSoundEnabled(final SharedPreferences prefs, final Resources res) {
        return prefs.getBoolean(PREF_SOUND_ON, res.getBoolean(R.bool.config_default_sound_enabled));
    }

    public static boolean readVibrationEnabled(final SharedPreferences prefs, final Resources res) {
        return AudioAndHapticFeedbackManager.getInstance().hasVibrator() && prefs.getBoolean(PREF_VIBRATE_ON, res.getBoolean(R.bool.config_default_vibration_enabled));
    }

    public static boolean readAutoCorrectEnabled(final SharedPreferences prefs, final Resources res) {
        return prefs.getBoolean(PREF_AUTO_CORRECTION, true);
    }

    public static float readPlausibilityThreshold(final Resources res) {
        return Float.parseFloat(res.getString(R.string.plausibility_threshold));
    }

    public static boolean readBlockPotentiallyOffensive(final SharedPreferences prefs, final Resources res) {
        return prefs.getBoolean(PREF_BLOCK_POTENTIALLY_OFFENSIVE, res.getBoolean(R.bool.config_block_potentially_offensive));
    }

    public static boolean readFromBuildConfigIfGestureInputEnabled(final Resources res) {
        return res.getBoolean(R.bool.config_gesture_input_enabled_by_build_config);
    }

    public static boolean readGestureInputEnabled(final SharedPreferences prefs, final Resources res) {
        return readFromBuildConfigIfGestureInputEnabled(res) && prefs.getBoolean(PREF_GESTURE_INPUT, true);
    }

    public static boolean readFromBuildConfigIfToShowKeyPreviewPopupOption(final Resources res) {
        return res.getBoolean(R.bool.config_enable_show_key_preview_popup_option);
    }

    public static boolean readKeyPreviewPopupEnabled(final SharedPreferences prefs, final Resources res) {
        boolean z = res.getBoolean(R.bool.config_default_key_preview_popup);
        return !readFromBuildConfigIfToShowKeyPreviewPopupOption(res) ? z : prefs.getBoolean(PREF_POPUP_ON, z);
    }

    public static int readKeyPreviewPopupDismissDelay(final SharedPreferences prefs, final Resources res) {
        return Integer.parseInt(prefs.getString(PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY, Integer.toString(res.getInteger(R.integer.config_key_preview_linger_timeout))));
    }

    public static boolean readShowsLanguageSwitchKey(final SharedPreferences prefs) {
        if (prefs.contains(PREF_SUPPRESS_LANGUAGE_SWITCH_KEY)) {
            boolean z = prefs.getBoolean(PREF_SUPPRESS_LANGUAGE_SWITCH_KEY, false);
            SharedPreferences.Editor editorEdit = prefs.edit();
            editorEdit.remove(PREF_SUPPRESS_LANGUAGE_SWITCH_KEY);
            editorEdit.putBoolean(PREF_SHOW_LANGUAGE_SWITCH_KEY, !z);
            editorEdit.apply();
        }
        return prefs.getBoolean(PREF_SHOW_LANGUAGE_SWITCH_KEY, true);
    }

    public static String readPrefAdditionalSubtypes(final SharedPreferences prefs, final Resources res) {
        return prefs.getString(PREF_CUSTOM_INPUT_STYLES, AdditionalSubtypeUtils.createPrefSubtypes(res.getStringArray(R.array.predefined_subtypes)));
    }

    public static void writePrefAdditionalSubtypes(final SharedPreferences prefs, final String prefSubtypes) {
        prefs.edit().putString(PREF_CUSTOM_INPUT_STYLES, prefSubtypes).apply();
    }

    public static float readKeypressSoundVolume(final SharedPreferences prefs, final Resources res) {
        float f = prefs.getFloat(PREF_KEYPRESS_SOUND_VOLUME, -1.0f);
        return f != -1.0f ? f : readDefaultKeypressSoundVolume(res);
    }

    public static float readDefaultKeypressSoundVolume(final Resources res) {
        return Float.parseFloat(ResourceUtils.getDeviceOverrideValue(res, R.array.keypress_volumes, DEFAULT_KEYPRESS_SOUND_VOLUME));
    }

    public static int readKeyLongpressTimeout(final SharedPreferences prefs, final Resources res) {
        int i = prefs.getInt(PREF_KEY_LONGPRESS_TIMEOUT, -1);
        return i != -1 ? i : readDefaultKeyLongpressTimeout(res);
    }

    public static int readDefaultKeyLongpressTimeout(final Resources res) {
        return res.getInteger(R.integer.config_default_longpress_key_timeout);
    }

    public static int readKeypressVibrationDuration(final SharedPreferences prefs, final Resources res) {
        int i = prefs.getInt(PREF_VIBRATION_DURATION_SETTINGS, -1);
        return i != -1 ? i : readDefaultKeypressVibrationDuration(res);
    }

    public static int readDefaultKeypressVibrationDuration(final Resources res) {
        return Integer.parseInt(ResourceUtils.getDeviceOverrideValue(res, R.array.keypress_vibration_durations, DEFAULT_KEYPRESS_VIBRATION_DURATION));
    }

    public static float readKeyPreviewAnimationScale(final SharedPreferences prefs, final String prefKey, final float defaultValue) {
        float f = prefs.getFloat(prefKey, -1.0f);
        return f != -1.0f ? f : defaultValue;
    }

    public static int readKeyPreviewAnimationDuration(final SharedPreferences prefs, final String prefKey, final int defaultValue) {
        int i = prefs.getInt(prefKey, -1);
        return i != -1 ? i : defaultValue;
    }

    public static float readKeyboardHeight(final SharedPreferences prefs, final float defaultValue) {
        float f = prefs.getFloat(DebugSettings.PREF_KEYBOARD_HEIGHT_SCALE, -1.0f);
        return f != -1.0f ? f : defaultValue;
    }

    public static boolean readUseFullscreenMode(final Resources res) {
        return res.getBoolean(R.bool.config_use_fullscreen_mode);
    }

    public static boolean readShowSetupWizardIcon(final SharedPreferences prefs, final Context context) {
        if (prefs.contains(PREF_SHOW_SETUP_WIZARD_ICON)) {
            return prefs.getBoolean(PREF_SHOW_SETUP_WIZARD_ICON, false);
        }
        return !((context.getApplicationInfo().flags & 1) != 0);
    }

    public static boolean readHasHardwareKeyboard(final Configuration conf) {
        return (conf.keyboard == 1 || conf.hardKeyboardHidden == 2) ? false : true;
    }

    public static boolean isInternal(final SharedPreferences prefs) {
        return prefs.getBoolean(PREF_KEY_IS_INTERNAL, false);
    }

    public void writeLastUsedPersonalizationToken(byte[] token) {
        if (token == null) {
            this.mPrefs.edit().remove(PREF_LAST_USED_PERSONALIZATION_TOKEN).apply();
        } else {
            this.mPrefs.edit().putString(PREF_LAST_USED_PERSONALIZATION_TOKEN, StringUtils.byteArrayToHexString(token)).apply();
        }
    }

    public byte[] readLastUsedPersonalizationToken() {
        return StringUtils.hexStringToByteArray(this.mPrefs.getString(PREF_LAST_USED_PERSONALIZATION_TOKEN, null));
    }

    public void writeLastPersonalizationDictWipedTime(final long timestamp) {
        this.mPrefs.edit().putLong(PREF_LAST_PERSONALIZATION_DICT_WIPED_TIME, timestamp).apply();
    }

    public long readLastPersonalizationDictGeneratedTime() {
        return this.mPrefs.getLong(PREF_LAST_PERSONALIZATION_DICT_WIPED_TIME, 0L);
    }

    public void writeCorpusHandlesForPersonalization(final Set<String> corpusHandles) {
        this.mPrefs.edit().putStringSet(PREF_CORPUS_HANDLES_FOR_PERSONALIZATION, corpusHandles).apply();
    }

    public Set<String> readCorpusHandlesForPersonalization() {
        return this.mPrefs.getStringSet(PREF_CORPUS_HANDLES_FOR_PERSONALIZATION, Collections.emptySet());
    }

    public static void writeEmojiRecentKeys(final SharedPreferences prefs, String str) {
        prefs.edit().putString(PREF_EMOJI_RECENT_KEYS, str).apply();
    }

    public static String readEmojiRecentKeys(final SharedPreferences prefs) {
        return prefs.getString(PREF_EMOJI_RECENT_KEYS, "");
    }

    public static void writeLastTypedEmojiCategoryPageId(final SharedPreferences prefs, final int categoryId, final int categoryPageId) {
        prefs.edit().putInt(PREF_EMOJI_CATEGORY_LAST_TYPED_ID + categoryId, categoryPageId).apply();
    }

    public static int readLastTypedEmojiCategoryPageId(final SharedPreferences prefs, final int categoryId) {
        return prefs.getInt(PREF_EMOJI_CATEGORY_LAST_TYPED_ID + categoryId, 0);
    }

    public static void writeLastShownEmojiCategoryId(final SharedPreferences prefs, final int categoryId) {
        prefs.edit().putInt(PREF_LAST_SHOWN_EMOJI_CATEGORY_ID, categoryId).apply();
    }

    public static int readLastShownEmojiCategoryId(final SharedPreferences prefs, final int defValue) {
        return prefs.getInt(PREF_LAST_SHOWN_EMOJI_CATEGORY_ID, defValue);
    }

    private void upgradeAutocorrectionSettings(final SharedPreferences prefs, final Resources res) {
        String string = prefs.getString(PREF_AUTO_CORRECTION_THRESHOLD_OBSOLETE, null);
        if (string != null) {
            SharedPreferences.Editor editorEdit = prefs.edit();
            editorEdit.remove(PREF_AUTO_CORRECTION_THRESHOLD_OBSOLETE);
            if (string.equals(res.getString(R.string.auto_correction_threshold_mode_index_off))) {
                editorEdit.putBoolean(PREF_AUTO_CORRECTION, false);
            } else {
                editorEdit.putBoolean(PREF_AUTO_CORRECTION, true);
            }
            editorEdit.commit();
        }
    }
}
