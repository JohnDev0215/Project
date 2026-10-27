package com.android.car.settings.common;

import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes5.dex */
public interface FragmentHost {
    void goBack();

    void launchFragment(Fragment fragment);

    void showBlockingMessage();
}
