package com.android.car.settings.system;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.INotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ResetAppPrefFragment extends SettingsFragment {
    private static final Logger LOG = new Logger((Class<?>) ResetAppPrefFragment.class);
    private MenuItem mResetButton;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.reset_app_pref_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mResetButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mResetButton = new MenuItem.Builder(getContext()).setTitle(R.string.reset_app_pref_button_text).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.system.-$$Lambda$ResetAppPrefFragment$zzveKIQi7--b_8VSx_hJcIlwA3U
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$ResetAppPrefFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$ResetAppPrefFragment(MenuItem i) {
        resetAppPreferences();
    }

    private void resetAppPreferences() {
        new ResetTask(requireContext().getApplicationContext()).execute(new Void[0]);
    }

    private static class ResetTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<Context> mContext;

        ResetTask(Context context) {
            this.mContext = new WeakReference<>(context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... unused) {
            IBinder packageManagerServiceBinder;
            Context context = this.mContext.get();
            if (context == null) {
                ResetAppPrefFragment.LOG.w("Unable to reset app preferences. Null context");
                return null;
            }
            PackageManager packageManager = context.getPackageManager();
            IBinder notificationManagerServiceBinder = ServiceManager.getService("notification");
            if (notificationManagerServiceBinder == null) {
                ResetAppPrefFragment.LOG.w("Unable to reset app preferences. Null notification manager service");
                return null;
            }
            INotificationManager notificationManagerService = INotificationManager.Stub.asInterface(notificationManagerServiceBinder);
            List<ApplicationInfo> apps = packageManager.getInstalledApplications(512);
            for (ApplicationInfo app : apps) {
                try {
                    notificationManagerService.setNotificationsEnabledForPackage(app.packageName, app.uid, true);
                } catch (RemoteException e) {
                    ResetAppPrefFragment.LOG.w("Unable to reset notification preferences for app: " + app.name, e);
                }
                if (!app.enabled && packageManager.getApplicationEnabledSetting(app.packageName) == 3) {
                    packageManager.setApplicationEnabledSetting(app.packageName, 0, 1);
                }
            }
            try {
                packageManagerServiceBinder = ServiceManager.getService("package");
            } catch (RemoteException e2) {
                ResetAppPrefFragment.LOG.w("Unable to reset app preferences", e2);
            }
            if (packageManagerServiceBinder == null) {
                ResetAppPrefFragment.LOG.w("Unable to reset app preferences. Null package manager service");
                return null;
            }
            IPackageManager.Stub.asInterface(packageManagerServiceBinder).resetApplicationPreferences(ActivityManager.getCurrentUser());
            ((AppOpsManager) context.getSystemService("appops")).resetAllModes();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            this.mContext.get();
        }
    }
}
