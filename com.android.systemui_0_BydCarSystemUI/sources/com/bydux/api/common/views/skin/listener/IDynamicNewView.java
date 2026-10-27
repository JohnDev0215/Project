package com.bydux.api.common.views.skin.listener;

import android.view.View;
import com.bydux.api.common.views.skin.entity.DynamicAttr;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDynamicNewView {
    void dynamicAddSkinView(View view, String attrName, int attrResId);

    void dynamicAddSkinView(View view, List<DynamicAttr> attrsList);
}
