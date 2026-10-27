package com.android.car.settings.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.toolbar.ToolbarController;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class HomepageFragment extends SettingsFragment {
    private static final int REQUEST_CODE = 501;
    private MenuItem mSearchButton;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.homepage_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mSearchButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSearchButton = new MenuItem.Builder(getContext()).setToSearch().setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.home.-$$Lambda$HomepageFragment$3U8JaWAyiBHLt9UX2GpZiAisurQ
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$HomepageFragment(menuItem);
            }
        }).setUxRestrictions(8).build();
    }

    public /* synthetic */ void lambda$onCreate$0$HomepageFragment(MenuItem i) {
        onSearchButtonClicked();
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        int i;
        super.onActivityCreated(savedInstanceState);
        ToolbarController toolbar = getToolbar();
        if (toolbar != null) {
            if (!getContext().getResources().getBoolean(R.bool.config_is_quick_settings_root)) {
                toolbar.setState(Toolbar.State.HOME);
                if (getContext().getResources().getBoolean(R.bool.config_show_settings_root_exit_icon)) {
                    i = R.drawable.ic_launcher_settings;
                } else {
                    i = 0;
                }
                toolbar.setLogo(i);
                return;
            }
            toolbar.setState(Toolbar.State.SUBPAGE);
        }
    }

    private void onSearchButtonClicked() {
        Intent intent = new Intent("android.settings.APP_SEARCH_SETTINGS").setPackage(getSettingsIntelligencePkgName(getContext()));
        if (intent.resolveActivity(getContext().getPackageManager()) == null) {
            return;
        }
        startActivityForResult(intent, REQUEST_CODE);
    }

    private String getSettingsIntelligencePkgName(Context context) {
        return context.getString(R.string.config_settingsintelligence_package_name);
    }
}
