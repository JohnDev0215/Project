package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AttrDivider extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            if (SkinAttr.RES_TYPE_NAME_COLOR.equals(this.attrValueTypeName)) {
                listView.setDivider(new ColorDrawable(SkinManager.getInstance().getColor(this.attrValueRefId)));
            } else if ("drawable".equals(this.attrValueTypeName) || SkinAttr.RES_TYPE_NAME_MIPMAP.equals(this.attrValueTypeName)) {
                listView.setDivider("drawable".equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId));
            }
        }
    }
}
