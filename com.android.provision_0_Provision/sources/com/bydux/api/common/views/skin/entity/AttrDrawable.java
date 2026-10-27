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

    public AttrDrawable(String attrName) {
        this.mAttrName = attrName;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        Drawable mipmapDrawable;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
                if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName)) {
                    mipmapDrawable = SkinManager.getInstance().getDrawable(this.attrValueRefId);
                } else {
                    mipmapDrawable = SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                }
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if ("drawableLeft".equals(this.mAttrName)) {
                    BydLogHelper.logV("drawable left id=" + this.attrValueRefId);
                    textView.setCompoundDrawablesWithIntrinsicBounds(mipmapDrawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
                } else if ("drawableRight".equals(this.mAttrName)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], mipmapDrawable, compoundDrawables[3]);
                } else if ("drawableTop".equals(this.mAttrName)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], mipmapDrawable, compoundDrawables[2], compoundDrawables[3]);
                } else if ("drawableBottom".equals(this.mAttrName)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], mipmapDrawable);
                }
            }
        }
    }
}
