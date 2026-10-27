package com.android.inputmethod.latin.utils;

import android.text.InputType;
import android.view.inputmethod.EditorInfo;

/* JADX INFO: loaded from: classes2.dex */
public final class InputTypeUtils implements InputType {
    public static final int IME_ACTION_CUSTOM_LABEL = 256;
    private static final int NUMBER_PASSWORD_INPUT_TYPE = 18;
    private static final int[] SUPPRESSING_AUTO_SPACES_FIELD_VARIATION = {32, 128, 144, 224};
    private static final int TEXT_PASSWORD_INPUT_TYPE = 129;
    private static final int TEXT_VISIBLE_PASSWORD_INPUT_TYPE = 145;
    private static final int WEB_TEXT_EMAIL_ADDRESS_INPUT_TYPE = 209;
    private static final int WEB_TEXT_PASSWORD_INPUT_TYPE = 225;

    private static boolean isNumberPasswordInputType(final int inputType) {
        return inputType == 18;
    }

    private static boolean isTextPasswordInputType(final int inputType) {
        return inputType == 129;
    }

    public static boolean isVisiblePasswordInputType(final int inputType) {
        return (inputType & 4095) == 145;
    }

    private static boolean isWebEditTextInputType(final int inputType) {
        return inputType == 161;
    }

    private static boolean isWebEmailAddressInputType(final int inputType) {
        return inputType == 209;
    }

    private static boolean isWebEmailAddressVariation(int variation) {
        return variation == 208;
    }

    private static boolean isWebPasswordInputType(final int inputType) {
        return inputType == 225;
    }

    private InputTypeUtils() {
    }

    public static boolean isEmailVariation(final int variation) {
        return variation == 32 || isWebEmailAddressVariation(variation);
    }

    public static boolean isWebInputType(final int inputType) {
        int i = inputType & 4095;
        return isWebEditTextInputType(i) || isWebPasswordInputType(i) || isWebEmailAddressInputType(i);
    }

    public static boolean isPasswordInputType(final int inputType) {
        int i = inputType & 4095;
        return isTextPasswordInputType(i) || isWebPasswordInputType(i) || isNumberPasswordInputType(i);
    }

    public static boolean isAutoSpaceFriendlyType(final int inputType) {
        if (1 != (inputType & 15)) {
            return false;
        }
        int i = inputType & 4080;
        for (int i2 : SUPPRESSING_AUTO_SPACES_FIELD_VARIATION) {
            if (i == i2) {
                return false;
            }
        }
        return true;
    }

    public static int getImeOptionsActionIdFromEditorInfo(final EditorInfo editorInfo) {
        if ((editorInfo.imeOptions & 1073741824) != 0) {
            return 1;
        }
        if (editorInfo.actionLabel != null) {
            return 256;
        }
        if ((editorInfo.imeOptions & 255) == 6) {
            return 1;
        }
        return editorInfo.imeOptions & 255;
    }
}
