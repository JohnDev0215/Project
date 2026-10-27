package com.android.car.developeroptions.applications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.widget.LoadingViewController;

/* JADX INFO: loaded from: classes.dex */
public class RunningServices extends SettingsPreferenceFragment {
    private View mLoadingContainer;
    private LoadingViewController mLoadingViewController;
    private Menu mOptionsMenu;
    private final Runnable mRunningProcessesAvail = new Runnable() { // from class: com.android.car.developeroptions.applications.RunningServices.1
        @Override // java.lang.Runnable
        public void run() {
            RunningServices.this.mLoadingViewController.showContent(true);
        }
    };
    private RunningProcessesView mRunningProcessesView;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 404;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(R.string.runningservices_settings_title);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.manage_applications_running, (ViewGroup) null);
        RunningProcessesView runningProcessesView = (RunningProcessesView) viewInflate.findViewById(R.id.running_processes);
        this.mRunningProcessesView = runningProcessesView;
        runningProcessesView.doCreate();
        View viewFindViewById = viewInflate.findViewById(R.id.loading_container);
        this.mLoadingContainer = viewFindViewById;
        this.mLoadingViewController = new LoadingViewController(viewFindViewById, this.mRunningProcessesView);
        return viewInflate;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        this.mOptionsMenu = menu;
        menu.add(0, 1, 1, R.string.show_running_services).setShowAsAction(0);
        menu.add(0, 2, 2, R.string.show_background_processes).setShowAsAction(0);
        updateOptionsMenu();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mLoadingViewController.handleLoadingContainer(this.mRunningProcessesView.doResume(this, this.mRunningProcessesAvail), false);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mRunningProcessesView.doPause();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            this.mRunningProcessesView.mAdapter.setShowBackground(false);
        } else {
            if (itemId != 2) {
                return false;
            }
            this.mRunningProcessesView.mAdapter.setShowBackground(true);
        }
        updateOptionsMenu();
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        updateOptionsMenu();
    }

    private void updateOptionsMenu() {
        boolean showBackground = this.mRunningProcessesView.mAdapter.getShowBackground();
        this.mOptionsMenu.findItem(1).setVisible(showBackground);
        this.mOptionsMenu.findItem(2).setVisible(!showBackground);
    }
}
