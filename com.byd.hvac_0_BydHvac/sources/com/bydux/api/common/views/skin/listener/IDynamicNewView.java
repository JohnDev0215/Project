package com.bydux.api.common.views.skin.listener;

import android.view.View;
import com.bydux.api.common.views.skin.entity.DynamicAttr;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface IDynamicNewView {
    void dynamicAddSkinView(View view, String str, int i);

    void dynamicAddSkinView(View view, List<DynamicAttr> list);
}
