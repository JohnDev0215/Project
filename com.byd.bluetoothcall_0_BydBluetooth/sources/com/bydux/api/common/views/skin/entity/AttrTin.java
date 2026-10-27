package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.core.graphics.drawable.a;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AttrTin extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if ("color".equals(this.attrValueTypeName)) {
                Drawable drawableR = a.r(imageView.getDrawable());
                a.n(drawableR, SkinManager.getInstance().getColor(this.attrValueRefId));
                imageView.setImageDrawable(drawableR);
            }
        }
    }
}
