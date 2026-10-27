package com.android.car.settings.applications;

import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AppListFragment extends SettingsFragment {
    private static final String KEY_SHOW_SYSTEM = "showSystem";
    private boolean mShowSystem;
    private MenuItem mSystemButton;

    protected abstract void onToggleShowSystemApps(boolean z);

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mShowSystem = savedInstanceState.getBoolean(KEY_SHOW_SYSTEM, false);
        }
        this.mSystemButton = new MenuItem.Builder(getContext()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.applications.-$$Lambda$AppListFragment$0Grt0Fe24GLoUTfD4u0o6F4EWbY
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$AppListFragment(menuItem);
            }
        }).setTitle(this.mShowSystem ? R.string.hide_system : R.string.show_system).build();
    }

    public /* synthetic */ void lambda$onCreate$0$AppListFragment(MenuItem i) {
        boolean z = !this.mShowSystem;
        this.mShowSystem = z;
        onToggleShowSystemApps(z);
        i.setTitle(this.mShowSystem ? R.string.hide_system : R.string.show_system);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mSystemButton);
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        onToggleShowSystemApps(this.mShowSystem);
    }

    protected final boolean shouldShowSystemApps() {
        return this.mShowSystem;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_SHOW_SYSTEM, this.mShowSystem);
    }
}
