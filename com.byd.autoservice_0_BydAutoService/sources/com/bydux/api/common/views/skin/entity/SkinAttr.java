package com.bydux.api.common.views.skin.entity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class SkinAttr {
    public static final String RES_TYPE_NAME_COLOR = "color";
    public static final String RES_TYPE_NAME_DRAWABLE = "drawable";
    public static final String RES_TYPE_NAME_MIPMAP = "mipmap";
    public String attrName;
    public int attrValueRefId;
    public String attrValueRefName;
    public String attrValueTypeName;

    public abstract void apply(View view);

    public String toString() {
        return "SkinAttr \n[\nattrName=" + this.attrName + ", \nattrValueRefId=" + this.attrValueRefId + ", \nattrValueRefName=" + this.attrValueRefName + ", \nattrValueTypeName=" + this.attrValueTypeName + "\n]";
    }
}
