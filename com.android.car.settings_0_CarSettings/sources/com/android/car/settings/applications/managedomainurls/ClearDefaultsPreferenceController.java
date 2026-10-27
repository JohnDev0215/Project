package com.android.car.settings.applications.managedomainurls;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.usb.IUsbManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.settingslib.applications.AppUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ClearDefaultsPreferenceController extends AppLaunchSettingsBasePreferenceController<Preference> {
    private static final Logger LOG = new Logger((Class<?>) ClearDefaultsPreferenceController.class);
    private final PackageManager mPm;
    private final IUsbManager mUsbManager;

    public ClearDefaultsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        IBinder b = ServiceManager.getService("usb");
        this.mUsbManager = IUsbManager.Stub.asInterface(b);
        this.mPm = context.getPackageManager();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        boolean autoLaunchEnabled = AppUtils.hasPreferredActivities(this.mPm, getPackageName()) || isDefaultBrowser(getPackageName()) || hasUsbDefaults(this.mUsbManager, getPackageName());
        preference.setEnabled(autoLaunchEnabled);
        if (autoLaunchEnabled) {
            preference.setTitle(R.string.auto_launch_reset_text);
            preference.setSummary(R.string.auto_launch_enable_text);
        } else {
            preference.setTitle(R.string.auto_launch_disable_text);
            preference.setSummary((CharSequence) null);
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        if (this.mUsbManager != null) {
            int userId = getCurrentUserId();
            this.mPm.clearPackagePreferredActivities(getPackageName());
            if (isDefaultBrowser(getPackageName())) {
                this.mPm.setDefaultBrowserPackageNameAsUser(null, userId);
            }
            try {
                this.mUsbManager.clearDefaults(getPackageName(), userId);
            } catch (RemoteException e) {
                LOG.e("mUsbManager.clearDefaults", e);
            }
            refreshUi();
            return true;
        }
        return true;
    }

    private boolean isDefaultBrowser(String packageName) {
        String defaultBrowser = this.mPm.getDefaultBrowserPackageNameAsUser(getCurrentUserId());
        return packageName.equals(defaultBrowser);
    }

    private boolean hasUsbDefaults(IUsbManager usbManager, String packageName) {
        if (usbManager != null) {
            try {
                return usbManager.hasDefaults(packageName, getCurrentUserId());
            } catch (RemoteException e) {
                LOG.e("mUsbManager.hasDefaults", e);
                return false;
            }
        }
        return false;
    }
}
