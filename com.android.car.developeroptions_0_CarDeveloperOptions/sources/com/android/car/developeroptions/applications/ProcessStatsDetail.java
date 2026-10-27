package com.android.car.developeroptions.applications;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.ArrayMap;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.CancellablePreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.SummaryPreference;
import com.android.car.developeroptions.widget.EntityHeaderController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ProcessStatsDetail extends SettingsPreferenceFragment {
    static final Comparator<ProcStatsEntry> sEntryCompare = new Comparator<ProcStatsEntry>() { // from class: com.android.car.developeroptions.applications.ProcessStatsDetail.2
        @Override // java.util.Comparator
        public int compare(ProcStatsEntry procStatsEntry, ProcStatsEntry procStatsEntry2) {
            double d = procStatsEntry.mRunWeight;
            double d2 = procStatsEntry2.mRunWeight;
            if (d < d2) {
                return 1;
            }
            return d > d2 ? -1 : 0;
        }
    };
    private ProcStatsPackageEntry mApp;
    private DevicePolicyManager mDpm;
    private MenuItem mForceStop;
    private double mMaxMemoryUsage;
    private PackageManager mPm;
    private PreferenceCategory mProcGroup;
    private final ArrayMap<ComponentName, CancellablePreference> mServiceMap = new ArrayMap<>();
    private double mTotalScale;
    private long mTotalTime;
    private double mWeightToRam;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 21;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPm = getActivity().getPackageManager();
        this.mDpm = (DevicePolicyManager) getActivity().getSystemService("device_policy");
        Bundle arguments = getArguments();
        ProcStatsPackageEntry procStatsPackageEntry = (ProcStatsPackageEntry) arguments.getParcelable("package_entry");
        this.mApp = procStatsPackageEntry;
        procStatsPackageEntry.retrieveUiData(getActivity(), this.mPm);
        this.mWeightToRam = arguments.getDouble("weight_to_ram");
        this.mTotalTime = arguments.getLong("total_time");
        this.mMaxMemoryUsage = arguments.getDouble("max_memory_usage");
        this.mTotalScale = arguments.getDouble("total_scale");
        long j = this.mTotalTime / 100;
        this.mServiceMap.clear();
        createDetails();
        setHasOptionsMenu(true);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Drawable colorDrawable;
        super.onViewCreated(view, bundle);
        if (this.mApp.mUiTargetApp == null) {
            finish();
            return;
        }
        FragmentActivity activity = getActivity();
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this, null);
        entityHeaderControllerNewInstance.setRecyclerView(getListView(), getSettingsLifecycle());
        if (this.mApp.mUiTargetApp != null) {
            colorDrawable = IconDrawableFactory.newInstance(activity).getBadgedIcon(this.mApp.mUiTargetApp);
        } else {
            colorDrawable = new ColorDrawable(0);
        }
        entityHeaderControllerNewInstance.setIcon(colorDrawable);
        entityHeaderControllerNewInstance.setLabel(this.mApp.mUiLabel);
        entityHeaderControllerNewInstance.setPackageName(this.mApp.mPackage);
        ApplicationInfo applicationInfo = this.mApp.mUiTargetApp;
        entityHeaderControllerNewInstance.setUid(applicationInfo != null ? applicationInfo.uid : -10000);
        entityHeaderControllerNewInstance.setHasAppInfoLink(true);
        entityHeaderControllerNewInstance.setButtonActions(0, 0);
        getPreferenceScreen().addPreference(entityHeaderControllerNewInstance.done(activity, getPrefContext()));
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        checkForceStop();
        updateRunningServices();
    }

    private void updateRunningServices() {
        final ComponentName componentName;
        CancellablePreference cancellablePreference;
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) getActivity().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        int size = this.mServiceMap.size();
        for (int i = 0; i < size; i++) {
            this.mServiceMap.valueAt(i).setCancellable(false);
        }
        int size2 = runningServices.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ActivityManager.RunningServiceInfo runningServiceInfo = runningServices.get(i2);
            if ((runningServiceInfo.started || runningServiceInfo.clientLabel != 0) && (runningServiceInfo.flags & 8) == 0 && (cancellablePreference = this.mServiceMap.get((componentName = runningServiceInfo.service))) != null) {
                cancellablePreference.setOnCancelListener(new CancellablePreference.OnCancelListener() { // from class: com.android.car.developeroptions.applications.ProcessStatsDetail.1
                    @Override // com.android.car.developeroptions.CancellablePreference.OnCancelListener
                    public void onCancel(CancellablePreference cancellablePreference2) {
                        ProcessStatsDetail.this.stopService(componentName.getPackageName(), componentName.getClassName());
                    }
                });
                cancellablePreference.setCancellable(true);
            }
        }
    }

    private void createDetails() {
        addPreferencesFromResource(R.xml.app_memory_settings);
        this.mProcGroup = (PreferenceCategory) findPreference("processes");
        fillProcessesSection();
        SummaryPreference summaryPreference = (SummaryPreference) findPreference("status_header");
        ProcStatsPackageEntry procStatsPackageEntry = this.mApp;
        double d = ((procStatsPackageEntry.mRunWeight > procStatsPackageEntry.mBgWeight ? 1 : (procStatsPackageEntry.mRunWeight == procStatsPackageEntry.mBgWeight ? 0 : -1)) > 0 ? this.mApp.mRunWeight : this.mApp.mBgWeight) * this.mWeightToRam;
        float f = (float) (d / this.mMaxMemoryUsage);
        FragmentActivity activity = getActivity();
        summaryPreference.setRatios(f, 0.0f, 1.0f - f);
        Formatter.BytesResult bytes = Formatter.formatBytes(activity.getResources(), (long) d, 1);
        summaryPreference.setAmount(bytes.value);
        summaryPreference.setUnits(bytes.units);
        ProcStatsPackageEntry procStatsPackageEntry2 = this.mApp;
        findPreference("frequency").setSummary(ProcStatsPackageEntry.getFrequency(Math.max(procStatsPackageEntry2.mRunDuration, procStatsPackageEntry2.mBgDuration) / this.mTotalTime, getActivity()));
        ProcStatsPackageEntry procStatsPackageEntry3 = this.mApp;
        findPreference("max_usage").setSummary(Formatter.formatShortFileSize(getContext(), (long) (Math.max(procStatsPackageEntry3.mMaxBgMem, procStatsPackageEntry3.mMaxRunMem) * this.mTotalScale * 1024.0d)));
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        this.mForceStop = menu.add(0, 1, 0, R.string.force_stop);
        checkForceStop();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1) {
            return false;
        }
        killProcesses();
        return true;
    }

    private void fillProcessesSection() {
        this.mProcGroup.removeAll();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mApp.mEntries.size(); i++) {
            ProcStatsEntry procStatsEntry = this.mApp.mEntries.get(i);
            if (procStatsEntry.mPackage.equals("os")) {
                procStatsEntry.mLabel = procStatsEntry.mName;
            } else {
                procStatsEntry.mLabel = getProcessName(this.mApp.mUiLabel, procStatsEntry);
            }
            arrayList.add(procStatsEntry);
        }
        Collections.sort(arrayList, sEntryCompare);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ProcStatsEntry procStatsEntry2 = (ProcStatsEntry) arrayList.get(i2);
            Preference preference = new Preference(getPrefContext());
            preference.setTitle(procStatsEntry2.mLabel);
            preference.setSelectable(false);
            long jMax = Math.max(procStatsEntry2.mRunDuration, procStatsEntry2.mBgDuration);
            double d = procStatsEntry2.mRunWeight;
            double d2 = this.mWeightToRam;
            preference.setSummary(getString(R.string.memory_use_running_format, Formatter.formatShortFileSize(getActivity(), Math.max((long) (d * d2), (long) (procStatsEntry2.mBgWeight * d2))), ProcStatsPackageEntry.getFrequency(jMax / this.mTotalTime, getActivity())));
            this.mProcGroup.addPreference(preference);
        }
        if (this.mProcGroup.getPreferenceCount() < 2) {
            getPreferenceScreen().removePreference(this.mProcGroup);
        }
    }

    private static String capitalize(String str) {
        char cCharAt = str.charAt(0);
        if (!Character.isLowerCase(cCharAt)) {
            return str;
        }
        return Character.toUpperCase(cCharAt) + str.substring(1);
    }

    private static String getProcessName(String str, ProcStatsEntry procStatsEntry) {
        String str2 = procStatsEntry.mName;
        if (str2.contains(":")) {
            return capitalize(str2.substring(str2.lastIndexOf(58) + 1));
        }
        if (!str2.startsWith(procStatsEntry.mPackage)) {
            return str2;
        }
        if (str2.length() == procStatsEntry.mPackage.length()) {
            return str;
        }
        int length = procStatsEntry.mPackage.length();
        if (str2.charAt(length) == '.') {
            length++;
        }
        return capitalize(str2.substring(length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopService(String str, String str2) {
        try {
            if ((getActivity().getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0) {
                showStopServiceDialog(str, str2);
            } else {
                doStopService(str, str2);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ProcessStatsDetail", "Can't find app " + str, e);
        }
    }

    private void showStopServiceDialog(final String str, final String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.runningservicedetails_stop_dlg_title);
        builder.setMessage(R.string.runningservicedetails_stop_dlg_text);
        builder.setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.applications.ProcessStatsDetail.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ProcessStatsDetail.this.doStopService(str, str2);
            }
        });
        builder.setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null);
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStopService(String str, String str2) {
        getActivity().stopService(new Intent().setClassName(str, str2));
        updateRunningServices();
    }

    private void killProcesses() {
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService("activity");
        for (int i = 0; i < this.mApp.mEntries.size(); i++) {
            ProcStatsEntry procStatsEntry = this.mApp.mEntries.get(i);
            for (int i2 = 0; i2 < procStatsEntry.mPackages.size(); i2++) {
                activityManager.forceStopPackage(procStatsEntry.mPackages.get(i2));
            }
        }
    }

    private void checkForceStop() {
        if (this.mForceStop == null) {
            return;
        }
        if (this.mApp.mEntries.get(0).mUid < 10000) {
            this.mForceStop.setVisible(false);
            return;
        }
        boolean z = false;
        for (int i = 0; i < this.mApp.mEntries.size(); i++) {
            ProcStatsEntry procStatsEntry = this.mApp.mEntries.get(i);
            for (int i2 = 0; i2 < procStatsEntry.mPackages.size(); i2++) {
                String str = procStatsEntry.mPackages.get(i2);
                if (this.mDpm.packageHasActiveAdmins(str)) {
                    this.mForceStop.setEnabled(false);
                    return;
                } else {
                    try {
                        if ((this.mPm.getApplicationInfo(str, 0).flags & 2097152) == 0) {
                            z = true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
            }
        }
        if (z) {
            this.mForceStop.setVisible(true);
        }
    }
}
