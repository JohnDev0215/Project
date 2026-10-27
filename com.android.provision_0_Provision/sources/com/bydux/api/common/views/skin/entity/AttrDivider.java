package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ListView;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AttrDivider extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        Drawable mipmapDrawable;
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            if ("color".equals(this.attrValueTypeName)) {
                listView.setDivider(new ColorDrawable(SkinManager.getInstance().getColor(this.attrValueRefId)));
                return;
            }
            if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
                if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName)) {
                    mipmapDrawable = SkinManager.getInstance().getDrawable(this.attrValueRefId);
                } else {
                    mipmapDrawable = SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                }
                listView.setDivider(mipmapDrawable);
            }
        }
    }
}
