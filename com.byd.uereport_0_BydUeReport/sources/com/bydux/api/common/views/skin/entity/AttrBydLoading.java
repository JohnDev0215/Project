package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydLoading;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes2.dex */
public class AttrBydLoading extends SkinAttr {
    private String mAttrName;
    private final String LOADING_TEXT_COLOR = "byd_loading_textColor";
    private final String LOADING_BACKGROUND = "byd_loading_background";

    public AttrBydLoading(String str) {
        this.mAttrName = str;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof BydLoading) {
            BydLoading bydLoading = (BydLoading) view;
            if (SkinAttr.RES_TYPE_NAME_COLOR.equals(this.attrValueTypeName)) {
                int color = SkinManager.getInstance().getColor(this.attrValueRefId);
                if ("byd_loading_textColor".equals(this.mAttrName)) {
                    bydLoading.setLoadingTextColor(color);
                    return;
                }
                return;
            }
            if ("drawable".equals(this.attrValueTypeName) || SkinAttr.RES_TYPE_NAME_MIPMAP.equals(this.attrValueTypeName)) {
                Drawable drawable = "drawable".equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                if ("byd_loading_background".equals(this.mAttrName)) {
                    bydLoading.setLoadingBackground(drawable);
                }
            }
        }
    }
}
