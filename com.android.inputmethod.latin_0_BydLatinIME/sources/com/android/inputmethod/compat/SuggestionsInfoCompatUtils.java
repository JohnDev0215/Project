package com.android.inputmethod.compat;

import android.view.textservice.SuggestionsInfo;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public final class SuggestionsInfoCompatUtils {
    private static final Field FIELD_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS;
    private static final Integer OBJ_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS;
    private static final int RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS;

    static {
        Field field = CompatUtils.getField(SuggestionsInfo.class, "RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS");
        FIELD_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS = field;
        Integer num = (Integer) CompatUtils.getFieldValue(null, null, field);
        OBJ_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS = num;
        RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS = num != null ? num.intValue() : 0;
    }

    private SuggestionsInfoCompatUtils() {
    }

    public static int getValueOf_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS() {
        return RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS;
    }
}
