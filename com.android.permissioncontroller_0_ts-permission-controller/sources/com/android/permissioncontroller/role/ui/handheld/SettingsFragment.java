package com.android.permissioncontroller.role.ui.handheld;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.permissioncontroller.role.utils.UiUtils;
import com.android.settingslib.HelpUtils;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SettingsFragment extends PreferenceFragmentCompat {
    private FrameLayout mContentLayout;
    private TextView mEmptyText;
    private View mLoadingView;
    private LinearLayout mPreferenceLayout;

    protected abstract int getEmptyTextResource();

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContentLayout = (FrameLayout) inflater.inflate(R.layout.settings, container, false);
        LinearLayout linearLayout = (LinearLayout) super.onCreateView(inflater, container, savedInstanceState);
        this.mPreferenceLayout = linearLayout;
        this.mContentLayout.addView(linearLayout);
        return this.mContentLayout;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mLoadingView = this.mContentLayout.findViewById(R.id.loading);
        this.mEmptyText = (TextView) this.mContentLayout.findViewById(R.id.empty);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = requireActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.mEmptyText.setText(getEmptyTextResource());
        updateState();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Utils.prepareSearchMenuItem(menu, requireContext());
        int helpUriResource = getHelpUriResource();
        if (helpUriResource != 0) {
            HelpUtils.prepareHelpMenuItem(requireActivity(), menu, helpUriResource, getClass().getName());
        }
    }

    protected int getHelpUriResource() {
        return 0;
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            requireActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void updateState() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        boolean isLoading = preferenceScreen == null;
        UiUtils.setViewShown(this.mLoadingView, isLoading);
        boolean isEmpty = preferenceScreen != null && preferenceScreen.getPreferenceCount() == 0;
        UiUtils.setViewShown(this.mEmptyText, isEmpty);
    }
}
