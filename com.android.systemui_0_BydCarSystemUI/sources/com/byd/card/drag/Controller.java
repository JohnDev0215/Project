package com.byd.card.drag;

import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface Controller {
    void initData(List<ApplicationInfo> list);

    void onAppClick(ApplicationInfo app, View view);
}
