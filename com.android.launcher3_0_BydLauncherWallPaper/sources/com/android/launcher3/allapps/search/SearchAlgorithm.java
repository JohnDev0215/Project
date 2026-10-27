package com.android.launcher3.allapps.search;

import com.android.launcher3.allapps.search.AllAppsSearchBarController;

/* JADX INFO: loaded from: classes4.dex */
public interface SearchAlgorithm {
    void cancel(boolean interruptActiveRequests);

    void doSearch(String query, AllAppsSearchBarController.Callbacks callback);
}
