package com.android.car.developeroptions.applications.manageapplications;

import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public interface FileViewHolderController {
    void onClick(Fragment fragment);

    void queryStats();

    void setupView(ApplicationViewHolder applicationViewHolder);

    boolean shouldShow();
}
