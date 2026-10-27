package com.android.inputmethod.keyboard.internal;

import android.text.TextUtils;
import com.android.inputmethod.latin.common.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class CodesArrayParser {
    private static final int BASE_HEX = 16;
    private static final char COMMA = ',';
    private static final String COMMA_REGEX = StringUtils.newSingleCodePointString(44);
    private static final String VERTICAL_BAR_REGEX = "\\|";

    private CodesArrayParser() {
    }

    private static String getLabelSpec(final String codesArraySpec) {
        String[] strArrSplit = codesArraySpec.split(VERTICAL_BAR_REGEX, -1);
        return strArrSplit.length <= 1 ? codesArraySpec : strArrSplit[0];
    }

    public static String parseLabel(final String codesArraySpec) {
        String labelSpec = getLabelSpec(codesArraySpec);
        StringBuilder sb = new StringBuilder();
        for (String str : labelSpec.split(COMMA_REGEX)) {
            sb.appendCodePoint(Integer.parseInt(str, 16));
        }
        return sb.toString();
    }

    private static String getCodeSpec(final String codesArraySpec) {
        String[] strArrSplit = codesArraySpec.split(VERTICAL_BAR_REGEX, -1);
        return strArrSplit.length <= 1 ? codesArraySpec : TextUtils.isEmpty(strArrSplit[1]) ? strArrSplit[0] : strArrSplit[1];
    }

    public static int getMinSupportSdkVersion(final String codesArraySpec) {
        String[] strArrSplit = codesArraySpec.split(VERTICAL_BAR_REGEX, -1);
        if (strArrSplit.length <= 2) {
            return 0;
        }
        try {
            return Integer.parseInt(strArrSplit[2]);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static int parseCode(final String codesArraySpec) {
        String codeSpec = getCodeSpec(codesArraySpec);
        if (codeSpec.indexOf(44) < 0) {
            return Integer.parseInt(codeSpec, 16);
        }
        return -4;
    }

    public static String parseOutputText(final String codesArraySpec) {
        String codeSpec = getCodeSpec(codesArraySpec);
        if (codeSpec.indexOf(44) < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : codeSpec.split(COMMA_REGEX)) {
            sb.appendCodePoint(Integer.parseInt(str, 16));
        }
        return sb.toString();
    }
}
