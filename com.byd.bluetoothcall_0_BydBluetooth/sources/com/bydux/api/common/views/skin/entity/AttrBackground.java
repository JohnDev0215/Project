package com.bydux.api.common.views.skin.entity;

import android.view.View;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AttrBackground extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if ("color".equals(this.attrValueTypeName)) {
            view.setBackgroundColor(SkinManager.getInstance().getColor(this.attrValueRefId));
        } else if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
            view.setBackground(AttrFactory.DRAWABLE.equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId));
        }
    }
}
