package com.amazon.music.explore.providers;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public interface ExplicitFilterProvider {

    public interface ExplicitFilterDialogCallback {
        void onDisabled();

        void onEnabled();
    }

    boolean isExplicitContentFiltered();

    void openBlockingExplicitFilterDialog(Context context, ExplicitFilterDialogCallback explicitFilterDialogCallback);

    void openBlockingExplicitFilterDialog(Context context, ExplicitFilterDialogCallback explicitFilterDialogCallback, String str, String str2);
}
