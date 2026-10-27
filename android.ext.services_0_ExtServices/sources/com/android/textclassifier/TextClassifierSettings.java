package com.android.textclassifier;

import android.provider.DeviceConfig;
import com.android.textclassifier.utils.IndentingPrintWriter;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class TextClassifierSettings {
    static final String ENTITY_LIST_DEFAULT = "entity_list_default";
    static final String LANG_ID_CONTEXT_SETTINGS = "lang_id_context_settings";
    static final String LANG_ID_THRESHOLD_OVERRIDE = "lang_id_threshold_override";
    static final String SUGGEST_SELECTION_MAX_RANGE_LENGTH = "suggest_selection_max_range_length";
    static final String TEMPLATE_INTENT_FACTORY_ENABLED = "template_intent_factory_enabled";
    private static final ImmutableList<String> ENTITY_LIST_DEFAULT_VALUE = ImmutableList.of("address", "email", "phone", "url", "date", "datetime", "flight");
    private static final ImmutableList<String> CONVERSATION_ACTIONS_TYPES_DEFAULT_VALUES = ImmutableList.of("text_reply", "create_reminder", "call_phone", "open_url", "send_email", "send_sms", "track_flight", "view_calendar", "view_map", "add_contact", "copy");
    private static final float[] LANG_ID_CONTEXT_SETTINGS_DEFAULT = {20.0f, 1.0f, 0.4f};

    public int getSuggestSelectionMaxRangeLength() {
        return DeviceConfig.getInt("textclassifier", SUGGEST_SELECTION_MAX_RANGE_LENGTH, 10000);
    }

    public int getClassifyTextMaxRangeLength() {
        return DeviceConfig.getInt("textclassifier", "classify_text_max_range_length", 10000);
    }

    public int getGenerateLinksMaxTextLength() {
        return DeviceConfig.getInt("textclassifier", "generate_links_max_text_length", 100000);
    }

    public int getGenerateLinksLogSampleRate() {
        return DeviceConfig.getInt("textclassifier", "generate_links_log_sample_rate", 100);
    }

    public List<String> getEntityListDefault() {
        return getDeviceConfigStringList(ENTITY_LIST_DEFAULT, ENTITY_LIST_DEFAULT_VALUE);
    }

    public List<String> getEntityListNotEditable() {
        return getDeviceConfigStringList("entity_list_not_editable", ENTITY_LIST_DEFAULT_VALUE);
    }

    public List<String> getEntityListEditable() {
        return getDeviceConfigStringList("entity_list_editable", ENTITY_LIST_DEFAULT_VALUE);
    }

    public List<String> getInAppConversationActionTypes() {
        return getDeviceConfigStringList("in_app_conversation_action_types_default", CONVERSATION_ACTIONS_TYPES_DEFAULT_VALUES);
    }

    public List<String> getNotificationConversationActionTypes() {
        return getDeviceConfigStringList("notification_conversation_action_types_default", CONVERSATION_ACTIONS_TYPES_DEFAULT_VALUES);
    }

    public float getLangIdThresholdOverride() {
        return DeviceConfig.getFloat("textclassifier", LANG_ID_THRESHOLD_OVERRIDE, -1.0f);
    }

    public float getTranslateActionThreshold() {
        return DeviceConfig.getFloat("textclassifier", "translate_action_threshold", 0.5f);
    }

    public boolean isUserLanguageProfileEnabled() {
        return DeviceConfig.getBoolean("textclassifier", "user_language_profile_enabled", true);
    }

    public boolean isTemplateIntentFactoryEnabled() {
        return DeviceConfig.getBoolean("textclassifier", TEMPLATE_INTENT_FACTORY_ENABLED, true);
    }

    public boolean isTranslateInClassificationEnabled() {
        return DeviceConfig.getBoolean("textclassifier", "translate_in_classification_enabled", true);
    }

    public boolean isDetectLanguagesFromTextEnabled() {
        return DeviceConfig.getBoolean("textclassifier", "detect_languages_from_text_enabled", true);
    }

    public float[] getLangIdContextSettings() {
        return getDeviceConfigFloatArray(LANG_ID_CONTEXT_SETTINGS, LANG_ID_CONTEXT_SETTINGS_DEFAULT);
    }

    void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("TextClassifierSettings:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.printPair("classify_text_max_range_length", Integer.valueOf(getClassifyTextMaxRangeLength()));
        indentingPrintWriter.printPair("detect_language_from_text_enabled", Boolean.valueOf(isDetectLanguagesFromTextEnabled()));
        indentingPrintWriter.printPair(ENTITY_LIST_DEFAULT, getEntityListDefault());
        indentingPrintWriter.printPair("entity_list_editable", getEntityListEditable());
        indentingPrintWriter.printPair("entity_list_not_editable", getEntityListNotEditable());
        indentingPrintWriter.printPair("generate_links_log_sample_rate", Integer.valueOf(getGenerateLinksLogSampleRate()));
        indentingPrintWriter.printPair("generate_links_max_text_length", Integer.valueOf(getGenerateLinksMaxTextLength()));
        indentingPrintWriter.printPair("in_app_conversation_action_types_default", getInAppConversationActionTypes());
        indentingPrintWriter.printPair(LANG_ID_CONTEXT_SETTINGS, Arrays.toString(getLangIdContextSettings()));
        indentingPrintWriter.printPair(LANG_ID_THRESHOLD_OVERRIDE, Float.valueOf(getLangIdThresholdOverride()));
        indentingPrintWriter.printPair("translate_action_threshold", Float.valueOf(getTranslateActionThreshold()));
        indentingPrintWriter.printPair("notification_conversation_action_types_default", getNotificationConversationActionTypes());
        indentingPrintWriter.printPair(SUGGEST_SELECTION_MAX_RANGE_LENGTH, Integer.valueOf(getSuggestSelectionMaxRangeLength()));
        indentingPrintWriter.printPair("user_language_profile_enabled", Boolean.valueOf(isUserLanguageProfileEnabled()));
        indentingPrintWriter.printPair(TEMPLATE_INTENT_FACTORY_ENABLED, Boolean.valueOf(isTemplateIntentFactoryEnabled()));
        indentingPrintWriter.printPair("translate_in_classification_enabled", Boolean.valueOf(isTranslateInClassificationEnabled()));
        indentingPrintWriter.decreaseIndent();
    }

    private static List<String> getDeviceConfigStringList(String str, List<String> list) {
        return parse(DeviceConfig.getString("textclassifier", str, (String) null), list);
    }

    private static float[] getDeviceConfigFloatArray(String str, float[] fArr) {
        return parse(DeviceConfig.getString("textclassifier", str, (String) null), fArr);
    }

    private static List<String> parse(String str, List<String> list) {
        return str != null ? Collections.unmodifiableList(Arrays.asList(str.split(":"))) : list;
    }

    private static float[] parse(String str, float[] fArr) {
        if (str == null) {
            return fArr;
        }
        List<String> listSplitToList = Splitter.onPattern(":").splitToList(str);
        if (listSplitToList.size() != fArr.length) {
            return fArr;
        }
        float[] fArr2 = new float[listSplitToList.size()];
        for (int i = 0; i < listSplitToList.size(); i++) {
            try {
                fArr2[i] = Float.parseFloat(listSplitToList.get(i));
            } catch (NumberFormatException unused) {
                return fArr;
            }
        }
        return fArr2;
    }
}
