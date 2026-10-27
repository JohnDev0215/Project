package com.bydux.api.common.views.skin.entity;

import android.view.View;
import android.widget.ListView;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AttrListSelector extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            if ("color".equals(this.attrValueTypeName)) {
                listView.setSelector(SkinManager.getInstance().getColor(this.attrValueRefId));
            } else if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
                listView.setSelector(AttrFactory.DRAWABLE.equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId));
            }
        }
    }
}
