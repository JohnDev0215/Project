package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AttrDrawable extends SkinAttr {
    private String mAttrName;
    private final String DRAWABLE_LEFT = "drawableLeft";
    private final String DRAWABLE_RIGHT = "drawableRight";
    private final String DRAWABLE_TOP = "drawableTop";
    private final String DRAWABLE_BOTTOM = "drawableBottom";

    public AttrDrawable(String str) {
        this.mAttrName = str;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if ("drawable".equals(this.attrValueTypeName) || SkinAttr.RES_TYPE_NAME_MIPMAP.equals(this.attrValueTypeName)) {
                Drawable drawable = "drawable".equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if ("drawableLeft".equals(this.mAttrName)) {
                    BydLogHelper.logV("drawable left id=" + this.attrValueRefId);
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
                    return;
                }
                if ("drawableRight".equals(this.mAttrName)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], drawable, compoundDrawables[3]);
                } else if ("drawableTop".equals(this.mAttrName)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], drawable, compoundDrawables[2], compoundDrawables[3]);
                } else if ("drawableBottom".equals(this.mAttrName)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], drawable);
                }
            }
        }
    }
}
