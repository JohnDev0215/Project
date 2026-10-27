package com.android.car.developeroptions.development.qstile;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.statusbar.IStatusBarService;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class DevelopmentTilePreferenceController extends BasePreferenceController {
    private static final String TAG = "DevTilePrefController";
    private final OnChangeHandler mOnChangeHandler;
    private final PackageManager mPackageManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public DevelopmentTilePreferenceController(Context context, String str) {
        super(context, str);
        this.mOnChangeHandler = new OnChangeHandler(context);
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Context context = preferenceScreen.getContext();
        Iterator<ResolveInfo> it = this.mPackageManager.queryIntentServices(new Intent("android.service.quicksettings.action.QS_TILE").setPackage(context.getPackageName()), 512).iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            int componentEnabledSetting = this.mPackageManager.getComponentEnabledSetting(new ComponentName(serviceInfo.packageName, serviceInfo.name));
            boolean z = true;
            if (componentEnabledSetting != 1 && (componentEnabledSetting != 0 || !serviceInfo.enabled)) {
                z = false;
            }
            SwitchPreference switchPreference = new SwitchPreference(context);
            switchPreference.setTitle(serviceInfo.loadLabel(this.mPackageManager));
            switchPreference.setIcon(serviceInfo.icon);
            switchPreference.setKey(serviceInfo.name);
            switchPreference.setChecked(z);
            switchPreference.setOnPreferenceChangeListener(this.mOnChangeHandler);
            preferenceScreen.addPreference(switchPreference);
        }
    }

    static class OnChangeHandler implements Preference.OnPreferenceChangeListener {
        private final Context mContext;
        private final PackageManager mPackageManager;
        private IStatusBarService mStatusBarService = IStatusBarService.Stub.asInterface(ServiceManager.checkService("statusbar"));

        public OnChangeHandler(Context context) {
            this.mContext = context;
            this.mPackageManager = context.getPackageManager();
        }

        @Override // androidx.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object obj) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            ComponentName componentName = new ComponentName(this.mContext.getPackageName(), preference.getKey());
            this.mPackageManager.setComponentEnabledSetting(componentName, zBooleanValue ? 1 : 2, 1);
            try {
                if (this.mStatusBarService != null) {
                    if (zBooleanValue) {
                        this.mStatusBarService.addTile(componentName);
                    } else {
                        this.mStatusBarService.remTile(componentName);
                    }
                }
            } catch (RemoteException e) {
                Log.e(DevelopmentTilePreferenceController.TAG, "Failed to modify QS tile for component " + componentName.toString(), e);
            }
            return true;
        }
    }
}
