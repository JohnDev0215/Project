package com.byd.card.bean;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public interface AppOrShortcutItem {
    Drawable getItemDrawable();

    String getItemIdentify();

    String getItemName();

    long getRecentTime();

    boolean isForbidden();

    boolean isItemChecked();

    void setForbidden(boolean forbidden);

    void setItemChecked(boolean checked);
}
