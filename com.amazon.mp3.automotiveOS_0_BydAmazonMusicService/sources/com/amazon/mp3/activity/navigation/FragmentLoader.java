package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes3.dex */
abstract class FragmentLoader {
    protected final FragmentActivity mFragmentActivity;
    protected final FragmentController mFragmentController;
    protected final String mTag = getClass().getSimpleName();

    protected abstract boolean navigateFromIntent(final Intent intent);

    protected abstract boolean navigateFromMenu(final int menuId);

    public FragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        this.mFragmentActivity = fragmentActivity;
        this.mFragmentController = fragmentController;
    }

    protected FragmentActivity getActivity() {
        return this.mFragmentActivity;
    }
}
