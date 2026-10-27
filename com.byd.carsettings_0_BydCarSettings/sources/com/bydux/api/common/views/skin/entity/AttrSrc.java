package com.bydux.api.common.views.skin.entity;

import android.view.View;
import android.widget.ImageView;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes3.dex */
public class AttrSrc extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if ("drawable".equals(this.attrValueTypeName) || SkinAttr.RES_TYPE_NAME_MIPMAP.equals(this.attrValueTypeName)) {
                imageView.setImageDrawable("drawable".equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId));
            }
        }
    }
}
