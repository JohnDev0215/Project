package com.android.inputmethod.keyboard.internal;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardIconsSet {
    private static final int ATTR_UNDEFINED = 0;
    private static final String[] ICON_NAMES;
    public static final int ICON_UNDEFINED = 0;
    private static final Object[] NAMES_AND_ATTR_IDS;
    public static final String NAME_DELETE_KEY = "delete_key";
    public static final String NAME_DONE_KEY = "done_key";
    public static final String NAME_EMOJI_ACTION_KEY = "emoji_action_key";
    public static final String NAME_EMOJI_NORMAL_KEY = "emoji_normal_key";
    public static final String NAME_ENTER_KEY = "enter_key";
    public static final String NAME_GO_KEY = "go_key";
    public static final String NAME_LANGUAGE_SWITCH_KEY = "language_switch_key";
    public static final String NAME_NEXT_KEY = "next_key";
    public static final String NAME_PREVIOUS_KEY = "previous_key";
    public static final String NAME_SEARCH_KEY = "search_key";
    public static final String NAME_SEND_KEY = "send_key";
    public static final String NAME_SETTINGS_KEY = "settings_key";
    public static final String NAME_SHIFT_KEY = "shift_key";
    public static final String NAME_SHIFT_KEY_SHIFTED = "shift_key_shifted";
    public static final String NAME_SHORTCUT_KEY = "shortcut_key";
    public static final String NAME_SHORTCUT_KEY_DISABLED = "shortcut_key_disabled";
    public static final String NAME_SPACE_KEY = "space_key";
    public static final String NAME_SPACE_KEY_FOR_NUMBER_LAYOUT = "space_key_for_number_layout";
    public static final String NAME_TAB_KEY = "tab_key";
    private static final String NAME_UNDEFINED = "undefined";
    public static final String NAME_ZWJ_KEY = "zwj_key";
    public static final String NAME_ZWNJ_KEY = "zwnj_key";
    private static int NUM_ICONS = 0;
    public static final String PREFIX_ICON = "!icon/";
    private static final String TAG = "KeyboardIconsSet";
    private final int[] mIconResourceIds;
    private final Drawable[] mIcons;
    private static final SparseIntArray ATTR_ID_TO_ICON_ID = new SparseIntArray();
    private static final HashMap<String, Integer> sNameToIdsMap = new HashMap<>();

    public KeyboardIconsSet() {
        int i = NUM_ICONS;
        this.mIcons = new Drawable[i];
        this.mIconResourceIds = new int[i];
    }

    static {
        int i = 0;
        Object[] objArr = {NAME_UNDEFINED, 0, NAME_SHIFT_KEY, 14, NAME_DELETE_KEY, 1, NAME_SETTINGS_KEY, 13, NAME_SPACE_KEY, 18, NAME_ENTER_KEY, 5, NAME_GO_KEY, 6, NAME_SEARCH_KEY, 11, NAME_SEND_KEY, 12, NAME_NEXT_KEY, 9, NAME_DONE_KEY, 2, NAME_PREVIOUS_KEY, 10, NAME_TAB_KEY, 20, NAME_SHORTCUT_KEY, 16, NAME_SPACE_KEY_FOR_NUMBER_LAYOUT, 19, NAME_SHIFT_KEY_SHIFTED, 15, NAME_SHORTCUT_KEY_DISABLED, 17, NAME_LANGUAGE_SWITCH_KEY, 8, NAME_ZWNJ_KEY, 22, NAME_ZWJ_KEY, 21, NAME_EMOJI_ACTION_KEY, 3, NAME_EMOJI_NORMAL_KEY, 4};
        NAMES_AND_ATTR_IDS = objArr;
        int length = objArr.length / 2;
        NUM_ICONS = length;
        ICON_NAMES = new String[length];
        int i2 = 0;
        while (true) {
            Object[] objArr2 = NAMES_AND_ATTR_IDS;
            if (i >= objArr2.length) {
                return;
            }
            String str = (String) objArr2[i];
            Integer num = (Integer) objArr2[i + 1];
            if (num.intValue() != 0) {
                ATTR_ID_TO_ICON_ID.put(num.intValue(), i2);
            }
            sNameToIdsMap.put(str, Integer.valueOf(i2));
            ICON_NAMES[i2] = str;
            i2++;
            i += 2;
        }
    }

    public void loadIcons(final TypedArray keyboardAttrs) {
        int size = ATTR_ID_TO_ICON_ID.size();
        for (int i = 0; i < size; i++) {
            SparseIntArray sparseIntArray = ATTR_ID_TO_ICON_ID;
            int iKeyAt = sparseIntArray.keyAt(i);
            try {
                Drawable drawable = keyboardAttrs.getDrawable(iKeyAt);
                setDefaultBounds(drawable);
                Integer numValueOf = Integer.valueOf(sparseIntArray.get(iKeyAt));
                this.mIcons[numValueOf.intValue()] = drawable;
                this.mIconResourceIds[numValueOf.intValue()] = keyboardAttrs.getResourceId(iKeyAt, 0);
            } catch (Resources.NotFoundException unused) {
                Log.w(TAG, "Drawable resource for icon #" + keyboardAttrs.getResources().getResourceEntryName(iKeyAt) + " not found");
            }
        }
    }

    private static boolean isValidIconId(final int iconId) {
        return iconId >= 0 && iconId < ICON_NAMES.length;
    }

    @Nonnull
    public static String getIconName(final int iconId) {
        return isValidIconId(iconId) ? ICON_NAMES[iconId] : "unknown<" + iconId + ">";
    }

    public static int getIconId(final String name) {
        Integer num = sNameToIdsMap.get(name);
        if (num != null) {
            return num.intValue();
        }
        throw new RuntimeException("unknown icon name: " + name);
    }

    public int getIconResourceId(final String name) {
        int iconId = getIconId(name);
        if (isValidIconId(iconId)) {
            return this.mIconResourceIds[iconId];
        }
        throw new RuntimeException("unknown icon name: " + name);
    }

    @Nullable
    public Drawable getIconDrawable(final int iconId) {
        if (isValidIconId(iconId)) {
            return this.mIcons[iconId];
        }
        throw new RuntimeException("unknown icon id: " + getIconName(iconId));
    }

    private static void setDefaultBounds(final Drawable icon) {
        if (icon != null) {
            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        }
    }
}
