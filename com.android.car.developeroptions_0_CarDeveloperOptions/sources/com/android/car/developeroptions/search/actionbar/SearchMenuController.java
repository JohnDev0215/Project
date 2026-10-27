package com.android.car.developeroptions.search.actionbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu;

/* JADX INFO: loaded from: classes.dex */
public class SearchMenuController implements LifecycleObserver, OnCreateOptionsMenu {
    private final Fragment mHost;
    private final int mPageId;

    public static void init(InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        instrumentedPreferenceFragment.getSettingsLifecycle().addObserver(new SearchMenuController(instrumentedPreferenceFragment, instrumentedPreferenceFragment.getMetricsCategory()));
    }

    public static void init(InstrumentedFragment instrumentedFragment) {
        instrumentedFragment.getSettingsLifecycle().addObserver(new SearchMenuController(instrumentedFragment, instrumentedFragment.getMetricsCategory()));
    }

    private SearchMenuController(Fragment fragment, int i) {
        this.mHost = fragment;
        this.mPageId = i;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        final Context context = this.mHost.getContext();
        String string = context.getString(R.string.config_settingsintelligence_package_name);
        if (Utils.isDeviceProvisioned(this.mHost.getContext()) && Utils.isPackageEnabled(this.mHost.getContext(), string) && menu != null) {
            Bundle arguments = this.mHost.getArguments();
            if (arguments == null || arguments.getBoolean("need_search_icon_in_action_bar", true)) {
                MenuItem menuItemAdd = menu.add(0, 0, 0, R.string.search_menu);
                menuItemAdd.setIcon(R.drawable.ic_search_24dp);
                menuItemAdd.setShowAsAction(2);
                menuItemAdd.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.android.car.developeroptions.search.actionbar.-$$Lambda$SearchMenuController$uVgjAGHfVCE1ZX7jWwrWLNmQajw
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        return this.f$0.lambda$onCreateOptionsMenu$0$SearchMenuController(context, menuItem);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateOptionsMenu$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$onCreateOptionsMenu$0$SearchMenuController(Context context, MenuItem menuItem) {
        Intent intentBuildSearchIntent = FeatureFactory.getFactory(context).getSearchFeatureProvider().buildSearchIntent(context, this.mPageId);
        if (context.getPackageManager().queryIntentActivities(intentBuildSearchIntent, 65536).isEmpty()) {
            return true;
        }
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 226, new Pair[0]);
        this.mHost.startActivityForResult(intentBuildSearchIntent, 501);
        return true;
    }
}
