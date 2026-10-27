package com.android.permissioncontroller.auto;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.car.ui.core.CarUi;
import com.android.car.ui.preference.PreferenceFragment;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.ToolbarController;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public abstract class AutoSettingsFrameFragment extends PreferenceFragment {
    private CharSequence mActionLabel;
    private View.OnClickListener mActionOnClickListener;
    private boolean mIsLoading;
    private CharSequence mLabel;
    private ToolbarController mToolbar;

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        this.mToolbar = CarUi.getToolbar(requireActivity());
        updateHeaderLabel();
        updateLoading();
        updateAction();
        return rootView;
    }

    public void setHeaderLabel(CharSequence label) {
        this.mLabel = label;
        if (getPreferenceScreen() != null) {
            getPreferenceScreen().setTitle(this.mLabel);
        }
        updateHeaderLabel();
    }

    public CharSequence getHeaderLabel() {
        return this.mLabel;
    }

    private void updateHeaderLabel() {
        ToolbarController toolbarController = this.mToolbar;
        if (toolbarController != null) {
            toolbarController.setTitle(this.mLabel);
        }
    }

    public void setLoading(boolean isLoading) {
        this.mIsLoading = isLoading;
        updateLoading();
    }

    private void updateLoading() {
        ToolbarController toolbarController = this.mToolbar;
        if (toolbarController != null) {
            toolbarController.getProgressBar().setVisible(this.mIsLoading);
        }
    }

    public void setAction(CharSequence label, View.OnClickListener onClickListener) {
        this.mActionLabel = label;
        this.mActionOnClickListener = onClickListener;
        updateAction();
    }

    private void updateAction() {
        if (this.mToolbar == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.mActionLabel) && this.mActionOnClickListener != null) {
            this.mToolbar.setMenuItems(Collections.singletonList(MenuItem.builder(getContext()).setTitle(this.mActionLabel).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.permissioncontroller.auto.-$$Lambda$AutoSettingsFrameFragment$tW1kNYlRwx0YW3b4GGY_YY5kZsY
                @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
                public final void onClick(MenuItem menuItem) {
                    this.f$0.lambda$updateAction$0$AutoSettingsFrameFragment(menuItem);
                }
            }).build()));
        } else {
            this.mToolbar.setMenuItems((List<MenuItem>) null);
        }
    }

    public /* synthetic */ void lambda$updateAction$0$AutoSettingsFrameFragment(MenuItem i) {
        this.mActionOnClickListener.onClick(null);
    }
}
