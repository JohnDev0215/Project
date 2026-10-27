package com.android.inputmethod.compat;

import android.view.inputmethod.EditorInfo;
import java.lang.reflect.Field;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class EditorInfoCompatUtils {
    private static final Field FIELD_HINT_LOCALES;
    private static final Field FIELD_IME_FLAG_FORCE_ASCII;
    private static final Integer OBJ_IME_FLAG_FORCE_ASCII;

    static {
        Field field = CompatUtils.getField(EditorInfo.class, "IME_FLAG_FORCE_ASCII");
        FIELD_IME_FLAG_FORCE_ASCII = field;
        OBJ_IME_FLAG_FORCE_ASCII = (Integer) CompatUtils.getFieldValue(null, null, field);
        FIELD_HINT_LOCALES = CompatUtils.getField(EditorInfo.class, "hintLocales");
    }

    private EditorInfoCompatUtils() {
    }

    public static boolean hasFlagForceAscii(final int imeOptions) {
        Integer num = OBJ_IME_FLAG_FORCE_ASCII;
        return (num == null || (imeOptions & num.intValue()) == 0) ? false : true;
    }

    public static String imeActionName(final int imeOptions) {
        int i = imeOptions & 255;
        switch (i) {
            case 0:
                return "actionUnspecified";
            case 1:
                return "actionNone";
            case 2:
                return "actionGo";
            case 3:
                return "actionSearch";
            case 4:
                return "actionSend";
            case 5:
                return "actionNext";
            case 6:
                return "actionDone";
            case 7:
                return "actionPrevious";
            default:
                return "actionUnknown(" + i + ")";
        }
    }

    public static String imeOptionsName(final int imeOptions) {
        String strImeActionName = imeActionName(imeOptions);
        StringBuilder sb = new StringBuilder();
        if ((1073741824 & imeOptions) != 0) {
            sb.append("flagNoEnterAction|");
        }
        if ((134217728 & imeOptions) != 0) {
            sb.append("flagNavigateNext|");
        }
        if ((67108864 & imeOptions) != 0) {
            sb.append("flagNavigatePrevious|");
        }
        if (hasFlagForceAscii(imeOptions)) {
            sb.append("flagForceAscii|");
        }
        return strImeActionName != null ? ((Object) sb) + strImeActionName : sb.toString();
    }

    public static Locale getPrimaryHintLocale(final EditorInfo editorInfo) {
        Object fieldValue;
        if (editorInfo == null || (fieldValue = CompatUtils.getFieldValue(editorInfo, null, FIELD_HINT_LOCALES)) == null || LocaleListCompatUtils.isEmpty(fieldValue)) {
            return null;
        }
        return LocaleListCompatUtils.get(fieldValue, 0);
    }
}
