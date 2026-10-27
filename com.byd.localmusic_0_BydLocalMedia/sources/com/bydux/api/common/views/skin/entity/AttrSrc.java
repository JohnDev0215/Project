package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes5.dex */
public class AttrSrc extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        Drawable mipmapDrawable;
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
                if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName)) {
                    mipmapDrawable = SkinManager.getInstance().getDrawable(this.attrValueRefId);
                } else {
                    mipmapDrawable = SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                }
                imageView.setImageDrawable(mipmapDrawable);
            }
        }
    }
}
