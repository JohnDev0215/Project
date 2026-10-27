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

    public AttrBydLoading(String attrName) {
        this.mAttrName = attrName;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        Drawable mipmapDrawable;
        if (view instanceof BydLoading) {
            BydLoading bydLoading = (BydLoading) view;
            if ("color".equals(this.attrValueTypeName)) {
                int color = SkinManager.getInstance().getColor(this.attrValueRefId);
                if ("byd_loading_textColor".equals(this.mAttrName)) {
                    bydLoading.setLoadingTextColor(color);
                    return;
                }
                return;
            }
            if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
                if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName)) {
                    mipmapDrawable = SkinManager.getInstance().getDrawable(this.attrValueRefId);
                } else {
                    mipmapDrawable = SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                }
                if ("byd_loading_background".equals(this.mAttrName)) {
                    bydLoading.setLoadingBackground(mipmapDrawable);
                }
            }
        }
    }
}
