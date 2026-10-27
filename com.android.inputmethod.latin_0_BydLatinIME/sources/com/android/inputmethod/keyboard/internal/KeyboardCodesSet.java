package com.android.inputmethod.keyboard.internal;

import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardCodesSet {
    public static final String PREFIX_CODE = "!code/";
    private static final HashMap<String, Integer> sNameToIdMap = new HashMap<>();
    private static final String[] ID_TO_NAME = {"key_tab", "key_enter", "key_space", "key_shift", "key_capslock", "key_switch_alpha_symbol", "key_output_text", "key_delete", "key_settings", "key_shortcut", "key_action_next", "key_action_previous", "key_shift_enter", "key_language_switch", "key_emoji", "key_alpha_from_emoji", "key_unspecified"};
    private static final int[] DEFAULT = {9, 10, 32, -1, -2, -3, -4, -5, -6, -7, -8, -9, -12, -10, -11, -14, -15};

    static {
        int i = 0;
        while (true) {
            String[] strArr = ID_TO_NAME;
            if (i >= strArr.length) {
                return;
            }
            sNameToIdMap.put(strArr[i], Integer.valueOf(i));
            i++;
        }
    }

    private KeyboardCodesSet() {
    }

    public static int getCode(final String name) {
        Integer num = sNameToIdMap.get(name);
        if (num == null) {
            throw new RuntimeException("Unknown key code: " + name);
        }
        return DEFAULT[num.intValue()];
    }
}
