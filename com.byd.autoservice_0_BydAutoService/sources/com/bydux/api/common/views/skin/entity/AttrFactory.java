package com.bydux.api.common.views.skin.entity;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class AttrFactory {
    public static final String BACKGROUND = "background";
    public static final String BYDCLOCK = "byd_clock_";
    public static final String BYDDGCLOCK = "byd_dgclock_";
    public static final String BYDLOADING = "byd_loading_";
    public static final String DIVIDER = "divider";
    public static final String DRAWABLE = "drawable";
    public static final String LIST_SELECTOR = "listSelector";
    public static final String SRC = "src";
    public static final String TEXT_COLOR = "textColor";
    public static final String TIN = "tin";

    public static SkinAttr get(SkinAttr skinAttr, String str, int i3, String str2, String str3) {
        if (skinAttr == null) {
            return null;
        }
        skinAttr.attrName = str;
        skinAttr.attrValueRefId = i3;
        skinAttr.attrValueRefName = str2;
        skinAttr.attrValueTypeName = str3;
        return skinAttr;
    }

    public static SkinAttr isSupportedAttr(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (BACKGROUND.equals(str)) {
            return new AttrBackground();
        }
        if (TEXT_COLOR.equals(str)) {
            return new AttrTextColor();
        }
        if (LIST_SELECTOR.equals(str)) {
            return new AttrListSelector();
        }
        if (DIVIDER.equals(str)) {
            return new AttrDivider();
        }
        if (SRC.equals(str)) {
            return new AttrSrc();
        }
        if (str.startsWith("drawable")) {
            return new AttrDrawable(str);
        }
        if (str.startsWith(TIN)) {
            return new AttrTin();
        }
        if (str.startsWith(BYDLOADING)) {
            return new AttrBydLoading(str);
        }
        if (str.startsWith(BYDCLOCK)) {
            return new AttrBydClock(str);
        }
        if (str.startsWith(BYDDGCLOCK)) {
            return new AttrBydDigitalClock(str);
        }
        return null;
    }
}
