package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.bydux.api.common.views.skin.loader.SkinManager;
import defpackage.y7;

/* JADX INFO: loaded from: classes.dex */
public class AttrTin extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if (SkinAttr.RES_TYPE_NAME_COLOR.equals(this.attrValueTypeName)) {
                Drawable drawableR = y7.r(imageView.getDrawable());
                y7.n(drawableR, SkinManager.getInstance().getColor(this.attrValueRefId));
                imageView.setImageDrawable(drawableR);
            }
        }
    }
}
