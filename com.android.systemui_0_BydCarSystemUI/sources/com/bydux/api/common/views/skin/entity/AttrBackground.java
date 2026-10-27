package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes2.dex */
public class AttrBackground extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        Drawable mipmapDrawable;
        if ("color".equals(this.attrValueTypeName)) {
            view.setBackgroundColor(SkinManager.getInstance().getColor(this.attrValueRefId));
            return;
        }
        if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
            if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName)) {
                mipmapDrawable = SkinManager.getInstance().getDrawable(this.attrValueRefId);
            } else {
                mipmapDrawable = SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
            }
            view.setBackground(mipmapDrawable);
        }
    }
}
