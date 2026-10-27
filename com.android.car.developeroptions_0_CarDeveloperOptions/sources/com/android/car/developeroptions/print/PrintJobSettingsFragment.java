package com.android.car.developeroptions.print;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintJob;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;

/* JADX INFO: loaded from: classes.dex */
public class PrintJobSettingsFragment extends DashboardFragment {
    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "PrintJobSettingsFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 78;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.print_job_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((PrintJobPreferenceController) use(PrintJobPreferenceController.class)).init(this);
        ((PrintJobMessagePreferenceController) use(PrintJobMessagePreferenceController.class)).init(this);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getListView().setEnabled(false);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        PrintJob printJob = ((PrintJobPreferenceController) use(PrintJobPreferenceController.class)).getPrintJob();
        if (printJob == null) {
            return;
        }
        if (!printJob.getInfo().isCancelling()) {
            menu.add(0, 1, 0, getString(R.string.print_cancel)).setShowAsAction(1);
        }
        if (printJob.isFailed()) {
            menu.add(0, 2, 0, getString(R.string.print_restart)).setShowAsAction(1);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        PrintJob printJob = ((PrintJobPreferenceController) use(PrintJobPreferenceController.class)).getPrintJob();
        if (printJob != null) {
            int itemId = menuItem.getItemId();
            if (itemId == 1) {
                printJob.cancel();
                finish();
                return true;
            }
            if (itemId == 2) {
                printJob.restart();
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
