package com.amazon.mp3.actionbar;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public interface ActionBarProvider {
    void removeHeaderView();

    void requestHomeButtonAsBack();

    void requestHomeButtonAsStoreClose();

    void restoreHomeButton();

    void setHeaderView(View headerView);

    void setHomeButtonVisibility(int visible);
}
