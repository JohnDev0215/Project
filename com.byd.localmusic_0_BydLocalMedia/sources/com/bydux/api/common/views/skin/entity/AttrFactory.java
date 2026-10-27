package com.bydux.api.common.views.skin.entity;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes5.dex */
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

    public static SkinAttr get(SkinAttr skinAttr, String attrName, int attrValueRefId, String attrValueRefName, String typeName) {
        if (skinAttr == null) {
            return null;
        }
        skinAttr.attrName = attrName;
        skinAttr.attrValueRefId = attrValueRefId;
        skinAttr.attrValueRefName = attrValueRefName;
        skinAttr.attrValueTypeName = typeName;
        return skinAttr;
    }

    public static SkinAttr isSupportedAttr(String attrName) {
        if (TextUtils.isEmpty(attrName)) {
            return null;
        }
        if (BACKGROUND.equals(attrName)) {
            return new AttrBackground();
        }
        if (TEXT_COLOR.equals(attrName)) {
            return new AttrTextColor();
        }
        if (LIST_SELECTOR.equals(attrName)) {
            return new AttrListSelector();
        }
        if (DIVIDER.equals(attrName)) {
            return new AttrDivider();
        }
        if (SRC.equals(attrName)) {
            return new AttrSrc();
        }
        if (attrName.startsWith(DRAWABLE)) {
            return new AttrDrawable(attrName);
        }
        if (attrName.startsWith(TIN)) {
            return new AttrTin();
        }
        if (attrName.startsWith(BYDLOADING)) {
            return new AttrBydLoading(attrName);
        }
        if (attrName.startsWith(BYDCLOCK)) {
            return new AttrBydClock(attrName);
        }
        if (attrName.startsWith(BYDDGCLOCK)) {
            return new AttrBydDigitalClock(attrName);
        }
        return null;
    }
}
