package com.bydux.api.common.views.skin.entity;

import android.view.View;
import android.widget.TextView;
import com.bydux.api.common.views.ctrl.BydButton;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes2.dex */
public class AttrTextColor extends SkinAttr {
    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if ((view instanceof TextView) || (view instanceof BydButton)) {
            TextView textView = (TextView) view;
            if ("color".equals(this.attrValueTypeName)) {
                textView.setTextColor(SkinManager.getInstance().convertToColorStateList(this.attrValueRefId));
            }
        }
    }
}
