package com.android.inputmethod.latin;

import android.util.Log;
import android.view.inputmethod.EditorInfo;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.utils.InputTypeUtils;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class InputAttributes {
    private final String TAG;
    public final boolean mApplicationSpecifiedCompletionOn;
    public final boolean mDisableGestureFloatingPreviewText;
    private final EditorInfo mEditorInfo;
    private final int mInputType;
    public final boolean mInputTypeNoAutoCorrect;
    public final boolean mIsGeneralTextInput;
    public final boolean mIsPasswordField;
    private final String mPackageNameForPrivateImeOptions;
    public final boolean mShouldInsertSpacesAutomatically;
    public final boolean mShouldShowSuggestions;
    public final boolean mShouldShowVoiceInputKey;
    public final String mTargetApplicationPackageName;

    public InputAttributes(final EditorInfo editorInfo, final boolean isFullscreenMode, final String packageNameForPrivateImeOptions) {
        String simpleName = InputAttributes.class.getSimpleName();
        this.TAG = simpleName;
        this.mEditorInfo = editorInfo;
        this.mPackageNameForPrivateImeOptions = packageNameForPrivateImeOptions;
        this.mTargetApplicationPackageName = editorInfo != null ? editorInfo.packageName : null;
        boolean z = false;
        int i = editorInfo != null ? editorInfo.inputType : 0;
        int i2 = i & 15;
        this.mInputType = i;
        boolean z2 = InputTypeUtils.isPasswordInputType(i) || InputTypeUtils.isVisiblePasswordInputType(i);
        this.mIsPasswordField = z2;
        if (i2 != 1) {
            if (editorInfo == null) {
                Log.w(simpleName, "No editor info for this field. Bug?");
            } else if (i == 0) {
                Log.i(simpleName, "InputType.TYPE_NULL is specified");
            } else if (i2 == 0) {
                Log.w(simpleName, String.format("Unexpected input class: inputType=0x%08x imeOptions=0x%08x", Integer.valueOf(i), Integer.valueOf(editorInfo.imeOptions)));
            }
            this.mShouldShowSuggestions = false;
            this.mInputTypeNoAutoCorrect = false;
            this.mApplicationSpecifiedCompletionOn = false;
            this.mShouldInsertSpacesAutomatically = false;
            this.mShouldShowVoiceInputKey = false;
            this.mDisableGestureFloatingPreviewText = false;
            this.mIsGeneralTextInput = false;
            return;
        }
        int i3 = i & 4080;
        boolean z3 = (524288 & i) != 0;
        boolean z4 = (131072 & i) != 0;
        boolean z5 = (32768 & i) != 0;
        boolean z6 = (65536 & i) != 0;
        this.mShouldShowSuggestions = !(z2 || InputTypeUtils.isEmailVariation(i3) || 16 == i3 || 176 == i3 || z3 || z6);
        this.mShouldInsertSpacesAutomatically = InputTypeUtils.isAutoSpaceFriendlyType(i);
        this.mShouldShowVoiceInputKey = !(z2 || InputTypeUtils.isEmailVariation(i3) || 16 == i3 || hasNoMicrophoneKeyOption());
        this.mDisableGestureFloatingPreviewText = inPrivateImeOptions(packageNameForPrivateImeOptions, Constants.ImeOption.NO_FLOATING_GESTURE_PREVIEW, editorInfo);
        this.mInputTypeNoAutoCorrect = (i3 == 160 && !z5) || z3 || !(z5 || z4);
        this.mApplicationSpecifiedCompletionOn = z6 && isFullscreenMode;
        if (32 != i3 && 128 != i3 && 192 != i3 && 16 != i3 && 144 != i3 && 208 != i3 && 224 != i3) {
            z = true;
        }
        this.mIsGeneralTextInput = z;
    }

    public boolean isTypeNull() {
        return this.mInputType == 0;
    }

    public boolean isSameInputType(final EditorInfo editorInfo) {
        return editorInfo.inputType == this.mInputType;
    }

    private boolean hasNoMicrophoneKeyOption() {
        return inPrivateImeOptions(this.mPackageNameForPrivateImeOptions, Constants.ImeOption.NO_MICROPHONE, this.mEditorInfo) || inPrivateImeOptions(null, Constants.ImeOption.NO_MICROPHONE_COMPAT, this.mEditorInfo);
    }

    private void dumpFlags(final int inputType) {
        int i = inputType & 15;
        String inputClassString = toInputClassString(i);
        String variationString = toVariationString(i, inputType & 32);
        String flagsString = toFlagsString(inputType & 16773120);
        Log.i(this.TAG, "Input class: " + inputClassString);
        Log.i(this.TAG, "Variation: " + variationString);
        Log.i(this.TAG, "Flags: " + flagsString);
    }

    private static String toInputClassString(final int inputClass) {
        return inputClass != 1 ? inputClass != 2 ? inputClass != 3 ? inputClass != 4 ? String.format("unknownInputClass<0x%08x>", Integer.valueOf(inputClass)) : "TYPE_CLASS_DATETIME" : "TYPE_CLASS_PHONE" : "TYPE_CLASS_NUMBER" : "TYPE_CLASS_TEXT";
    }

    private static String toVariationString(final int inputClass, final int variation) {
        if (inputClass == 1) {
            return toTextVariationString(variation);
        }
        if (inputClass != 2) {
            return inputClass != 4 ? "" : toDatetimeVariationString(variation);
        }
        return toNumberVariationString(variation);
    }

    private static String toTextVariationString(final int variation) {
        switch (variation) {
            case 0:
                return "TYPE_TEXT_VARIATION_NORMAL";
            case 16:
                return "TYPE_TEXT_VARIATION_URI";
            case 32:
                return " TYPE_TEXT_VARIATION_EMAIL_ADDRESS";
            case 48:
                return "TYPE_TEXT_VARIATION_EMAIL_SUBJECT";
            case 64:
                return "TYPE_TEXT_VARIATION_SHORT_MESSAGE";
            case 80:
                return "TYPE_TEXT_VARIATION_LONG_MESSAGE";
            case 96:
                return "TYPE_TEXT_VARIATION_PERSON_NAME";
            case 112:
                return "TYPE_TEXT_VARIATION_POSTAL_ADDRESS";
            case 128:
                return "TYPE_TEXT_VARIATION_PASSWORD";
            case 144:
                return "TYPE_TEXT_VARIATION_VISIBLE_PASSWORD";
            case 160:
                return "TYPE_TEXT_VARIATION_WEB_EDIT_TEXT";
            case 176:
                return "TYPE_TEXT_VARIATION_FILTER";
            case 192:
                return "TYPE_TEXT_VARIATION_PHONETIC";
            case 208:
                return "TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS";
            case 224:
                return "TYPE_TEXT_VARIATION_WEB_PASSWORD";
            default:
                return String.format("unknownVariation<0x%08x>", Integer.valueOf(variation));
        }
    }

    private static String toNumberVariationString(final int variation) {
        return variation != 0 ? variation != 16 ? String.format("unknownVariation<0x%08x>", Integer.valueOf(variation)) : "TYPE_NUMBER_VARIATION_PASSWORD" : "TYPE_NUMBER_VARIATION_NORMAL";
    }

    private static String toDatetimeVariationString(final int variation) {
        return variation != 0 ? variation != 16 ? variation != 32 ? String.format("unknownVariation<0x%08x>", Integer.valueOf(variation)) : "TYPE_DATETIME_VARIATION_TIME" : "TYPE_DATETIME_VARIATION_DATE" : "TYPE_DATETIME_VARIATION_NORMAL";
    }

    private static String toFlagsString(final int flags) {
        ArrayList arrayList = new ArrayList();
        if ((524288 & flags) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_NO_SUGGESTIONS");
        }
        if ((131072 & flags) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_MULTI_LINE");
        }
        if ((262144 & flags) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_IME_MULTI_LINE");
        }
        if ((flags & 8192) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_CAP_WORDS");
        }
        if ((flags & 16384) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_CAP_SENTENCES");
        }
        if ((flags & 4096) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_CAP_CHARACTERS");
        }
        if ((32768 & flags) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_AUTO_CORRECT");
        }
        if ((flags & 65536) != 0) {
            arrayList.add("TYPE_TEXT_FLAG_AUTO_COMPLETE");
        }
        return arrayList.isEmpty() ? "" : Arrays.toString(arrayList.toArray());
    }

    public String toString() {
        Object[] objArr = new Object[8];
        objArr[0] = getClass().getSimpleName();
        objArr[1] = Integer.valueOf(this.mInputType);
        objArr[2] = this.mInputTypeNoAutoCorrect ? " noAutoCorrect" : "";
        objArr[3] = this.mIsPasswordField ? " password" : "";
        objArr[4] = this.mShouldShowSuggestions ? " shouldShowSuggestions" : "";
        objArr[5] = this.mApplicationSpecifiedCompletionOn ? " appSpecified" : "";
        objArr[6] = this.mShouldInsertSpacesAutomatically ? " insertSpaces" : "";
        objArr[7] = this.mTargetApplicationPackageName;
        return String.format("%s: inputType=0x%08x%s%s%s%s%s targetApp=%s\n", objArr);
    }

    public static boolean inPrivateImeOptions(final String packageName, final String key, final EditorInfo editorInfo) {
        if (editorInfo == null) {
            return false;
        }
        if (packageName != null) {
            key = packageName + "." + key;
        }
        return StringUtils.containsInCommaSplittableText(key, editorInfo.privateImeOptions);
    }
}
