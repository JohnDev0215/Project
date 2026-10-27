package com.android.car.developeroptions.support.actionbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.ObservableFragment;
import com.android.settingslib.core.lifecycle.ObservablePreferenceFragment;
import com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu;

/* JADX INFO: loaded from: classes.dex */
public class HelpMenuController implements LifecycleObserver, OnCreateOptionsMenu {
    private final Fragment mHost;

    public static void init(ObservablePreferenceFragment observablePreferenceFragment) {
        observablePreferenceFragment.getSettingsLifecycle().addObserver(new HelpMenuController(observablePreferenceFragment));
    }

    public static void init(ObservableFragment observableFragment) {
        observableFragment.getSettingsLifecycle().addObserver(new HelpMenuController(observableFragment));
    }

    private HelpMenuController(Fragment fragment) {
        this.mHost = fragment;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        int helpResource;
        Bundle arguments = this.mHost.getArguments();
        if (arguments != null && arguments.containsKey("help_uri_resource")) {
            helpResource = arguments.getInt("help_uri_resource");
        } else {
            LifecycleOwner lifecycleOwner = this.mHost;
            helpResource = lifecycleOwner instanceof HelpResourceProvider ? ((HelpResourceProvider) lifecycleOwner).getHelpResource() : 0;
        }
        String string = helpResource != 0 ? this.mHost.getContext().getString(helpResource) : null;
        FragmentActivity activity = this.mHost.getActivity();
        if (string == null || activity == null) {
            return;
        }
        HelpUtils.prepareHelpMenuItem(activity, menu, string, this.mHost.getClass().getName());
    }
}
