package android.view.textclassifier;

import android.provider.DeviceConfig;
import com.android.internal.util.IndentingPrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public final class TextClassificationConstants {
    private static final String DEFAULT_TEXT_CLASSIFIER_SERVICE_PACKAGE_OVERRIDE = null;
    static final String GENERATE_LINKS_MAX_TEXT_LENGTH = "generate_links_max_text_length";
    private static final int GENERATE_LINKS_MAX_TEXT_LENGTH_DEFAULT = 100000;
    static final String LOCAL_TEXT_CLASSIFIER_ENABLED = "local_textclassifier_enabled";
    private static final boolean LOCAL_TEXT_CLASSIFIER_ENABLED_DEFAULT = true;
    private static final String MODEL_DARK_LAUNCH_ENABLED = "model_dark_launch_enabled";
    private static final boolean MODEL_DARK_LAUNCH_ENABLED_DEFAULT = false;
    private static final String SMART_LINKIFY_ENABLED = "smart_linkify_enabled";
    private static final boolean SMART_LINKIFY_ENABLED_DEFAULT = true;
    private static final String SMART_SELECTION_ENABLED = "smart_selection_enabled";
    private static final boolean SMART_SELECTION_ENABLED_DEFAULT = true;
    private static final String SMART_SELECT_ANIMATION_ENABLED = "smart_select_animation_enabled";
    private static final boolean SMART_SELECT_ANIMATION_ENABLED_DEFAULT = true;
    private static final String SMART_TEXT_SHARE_ENABLED = "smart_text_share_enabled";
    private static final boolean SMART_TEXT_SHARE_ENABLED_DEFAULT = true;
    static final String SYSTEM_TEXT_CLASSIFIER_ENABLED = "system_textclassifier_enabled";
    private static final boolean SYSTEM_TEXT_CLASSIFIER_ENABLED_DEFAULT = true;
    static final String TEXT_CLASSIFIER_SERVICE_PACKAGE_OVERRIDE = "textclassifier_service_package_override";

    public String getTextClassifierServicePackageOverride() {
        return DeviceConfig.getString(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, TEXT_CLASSIFIER_SERVICE_PACKAGE_OVERRIDE, DEFAULT_TEXT_CLASSIFIER_SERVICE_PACKAGE_OVERRIDE);
    }

    public boolean isLocalTextClassifierEnabled() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, LOCAL_TEXT_CLASSIFIER_ENABLED, true);
    }

    public boolean isSystemTextClassifierEnabled() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, SYSTEM_TEXT_CLASSIFIER_ENABLED, true);
    }

    public boolean isModelDarkLaunchEnabled() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, MODEL_DARK_LAUNCH_ENABLED, false);
    }

    public boolean isSmartSelectionEnabled() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, SMART_SELECTION_ENABLED, true);
    }

    public boolean isSmartTextShareEnabled() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, SMART_TEXT_SHARE_ENABLED, true);
    }

    public boolean isSmartLinkifyEnabled() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, SMART_LINKIFY_ENABLED, true);
    }

    public boolean isSmartSelectionAnimationEnabled() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, SMART_SELECT_ANIMATION_ENABLED, true);
    }

    public int getGenerateLinksMaxTextLength() {
        return DeviceConfig.getInt(DeviceConfig.NAMESPACE_TEXTCLASSIFIER, GENERATE_LINKS_MAX_TEXT_LENGTH, 100000);
    }

    void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("TextClassificationConstants:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.printPair(GENERATE_LINKS_MAX_TEXT_LENGTH, Integer.valueOf(getGenerateLinksMaxTextLength())).println();
        indentingPrintWriter.printPair(LOCAL_TEXT_CLASSIFIER_ENABLED, Boolean.valueOf(isLocalTextClassifierEnabled())).println();
        indentingPrintWriter.printPair(MODEL_DARK_LAUNCH_ENABLED, Boolean.valueOf(isModelDarkLaunchEnabled())).println();
        indentingPrintWriter.printPair(SMART_LINKIFY_ENABLED, Boolean.valueOf(isSmartLinkifyEnabled())).println();
        indentingPrintWriter.printPair(SMART_SELECT_ANIMATION_ENABLED, Boolean.valueOf(isSmartSelectionAnimationEnabled())).println();
        indentingPrintWriter.printPair(SMART_SELECTION_ENABLED, Boolean.valueOf(isSmartSelectionEnabled())).println();
        indentingPrintWriter.printPair(SMART_TEXT_SHARE_ENABLED, Boolean.valueOf(isSmartTextShareEnabled())).println();
        indentingPrintWriter.printPair(SYSTEM_TEXT_CLASSIFIER_ENABLED, Boolean.valueOf(isSystemTextClassifierEnabled())).println();
        indentingPrintWriter.printPair(TEXT_CLASSIFIER_SERVICE_PACKAGE_OVERRIDE, getTextClassifierServicePackageOverride()).println();
        indentingPrintWriter.decreaseIndent();
    }
}
