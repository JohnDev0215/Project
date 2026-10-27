package com.android.car.developeroptions.applications.specialaccess.deviceadmin;

import android.app.AppGlobals;
import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.widget.FooterPreference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class DeviceAdminListPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final IntentFilter FILTER;
    private static final String KEY_DEVICE_ADMIN_FOOTER = "device_admin_footer";
    private static final String TAG = "DeviceAdminListPrefCtrl";
    private final ArrayList<DeviceAdminListItem> mAdmins;
    private final BroadcastReceiver mBroadcastReceiver;
    private final DevicePolicyManager mDPM;
    private FooterPreference mFooterPreference;
    private final IPackageManager mIPackageManager;
    private final PackageManager mPackageManager;
    private PreferenceGroup mPreferenceGroup;
    private final SparseArray<ComponentName> mProfileOwnerComponents;
    private final UserManager mUm;

    static /* synthetic */ boolean lambda$bindPreference$1(Preference preference, Object obj) {
        return false;
    }

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

    static {
        IntentFilter intentFilter = new IntentFilter();
        FILTER = intentFilter;
        intentFilter.addAction("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED");
    }

    public DeviceAdminListPreferenceController(Context context, String str) {
        super(context, str);
        this.mAdmins = new ArrayList<>();
        this.mProfileOwnerComponents = new SparseArray<>();
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.applications.specialaccess.deviceadmin.DeviceAdminListPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (TextUtils.equals("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED", intent.getAction())) {
                    DeviceAdminListPreferenceController.this.updateList();
                }
            }
        };
        this.mDPM = (DevicePolicyManager) context.getSystemService("device_policy");
        this.mUm = (UserManager) context.getSystemService("user");
        this.mPackageManager = this.mContext.getPackageManager();
        this.mIPackageManager = AppGlobals.getPackageManager();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreferenceGroup = preferenceGroup;
        this.mFooterPreference = (FooterPreference) preferenceGroup.findPreference(KEY_DEVICE_ADMIN_FOOTER);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, UserHandle.ALL, FILTER, null, null);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mProfileOwnerComponents.clear();
        List<UserHandle> userProfiles = this.mUm.getUserProfiles();
        int size = userProfiles.size();
        for (int i = 0; i < size; i++) {
            int identifier = userProfiles.get(i).getIdentifier();
            this.mProfileOwnerComponents.put(identifier, this.mDPM.getProfileOwnerAsUser(identifier));
        }
        updateList();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
    }

    void updateList() {
        refreshData();
        refreshUI();
    }

    private void refreshData() {
        this.mAdmins.clear();
        Iterator<UserHandle> it = this.mUm.getUserProfiles().iterator();
        while (it.hasNext()) {
            updateAvailableAdminsForProfile(it.next().getIdentifier());
        }
        Collections.sort(this.mAdmins);
    }

    private void refreshUI() {
        if (this.mPreferenceGroup == null) {
            return;
        }
        FooterPreference footerPreference = this.mFooterPreference;
        if (footerPreference != null) {
            footerPreference.setVisible(this.mAdmins.isEmpty());
        }
        ArrayMap arrayMap = new ArrayMap();
        Context context = this.mPreferenceGroup.getContext();
        int preferenceCount = this.mPreferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = this.mPreferenceGroup.getPreference(i);
            if (preference instanceof SwitchPreference) {
                SwitchPreference switchPreference = (SwitchPreference) preference;
                arrayMap.put(switchPreference.getKey(), switchPreference);
            }
        }
        for (DeviceAdminListItem deviceAdminListItem : this.mAdmins) {
            SwitchPreference switchPreference2 = (SwitchPreference) arrayMap.remove(deviceAdminListItem.getKey());
            if (switchPreference2 == null) {
                switchPreference2 = new SwitchPreference(context);
                this.mPreferenceGroup.addPreference(switchPreference2);
            }
            bindPreference(deviceAdminListItem, switchPreference2);
        }
        Iterator it = arrayMap.values().iterator();
        while (it.hasNext()) {
            this.mPreferenceGroup.removePreference((SwitchPreference) it.next());
        }
    }

    private void bindPreference(final DeviceAdminListItem deviceAdminListItem, SwitchPreference switchPreference) {
        switchPreference.setKey(deviceAdminListItem.getKey());
        switchPreference.setTitle(deviceAdminListItem.getName());
        switchPreference.setIcon(deviceAdminListItem.getIcon());
        switchPreference.setChecked(deviceAdminListItem.isActive());
        switchPreference.setSummary(deviceAdminListItem.getDescription());
        switchPreference.setEnabled(deviceAdminListItem.isEnabled());
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.applications.specialaccess.deviceadmin.-$$Lambda$DeviceAdminListPreferenceController$a1s8u5bWNCP2S6YOf6bl0MILlek
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$bindPreference$0$DeviceAdminListPreferenceController(deviceAdminListItem, preference);
            }
        });
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.applications.specialaccess.deviceadmin.-$$Lambda$DeviceAdminListPreferenceController$DH5uwQtdm9qEQuKrB1eO8bsCt34
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return DeviceAdminListPreferenceController.lambda$bindPreference$1(preference, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$bindPreference$0$DeviceAdminListPreferenceController(DeviceAdminListItem deviceAdminListItem, Preference preference) {
        UserHandle user = deviceAdminListItem.getUser();
        Context context = this.mContext;
        context.startActivityAsUser(deviceAdminListItem.getLaunchIntent(context), user);
        return true;
    }

    private void updateAvailableAdminsForProfile(int i) {
        List activeAdminsAsUser = this.mDPM.getActiveAdminsAsUser(i);
        addActiveAdminsForProfile(activeAdminsAsUser, i);
        addDeviceAdminBroadcastReceiversForProfile(activeAdminsAsUser, i);
    }

    private void addActiveAdminsForProfile(List<ComponentName> list, int i) {
        if (list == null) {
            return;
        }
        for (ComponentName componentName : list) {
            try {
                DeviceAdminInfo deviceAdminInfoCreateDeviceAdminInfo = createDeviceAdminInfo(this.mContext, this.mIPackageManager.getReceiverInfo(componentName, 819328, i));
                if (deviceAdminInfoCreateDeviceAdminInfo != null) {
                    this.mAdmins.add(new DeviceAdminListItem(this.mContext, deviceAdminInfoCreateDeviceAdminInfo));
                }
            } catch (RemoteException unused) {
                Log.w(TAG, "Unable to load component: " + componentName);
            }
        }
    }

    private void addDeviceAdminBroadcastReceiversForProfile(Collection<ComponentName> collection, int i) {
        List<ResolveInfo> listQueryBroadcastReceiversAsUser = this.mPackageManager.queryBroadcastReceiversAsUser(new Intent("android.app.action.DEVICE_ADMIN_ENABLED"), 32896, i);
        if (listQueryBroadcastReceiversAsUser == null) {
            return;
        }
        for (ResolveInfo resolveInfo : listQueryBroadcastReceiversAsUser) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            ComponentName componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
            if (collection == null || !collection.contains(componentName)) {
                DeviceAdminInfo deviceAdminInfoCreateDeviceAdminInfo = createDeviceAdminInfo(this.mContext, resolveInfo.activityInfo);
                if (deviceAdminInfoCreateDeviceAdminInfo != null && deviceAdminInfoCreateDeviceAdminInfo.isVisible() && deviceAdminInfoCreateDeviceAdminInfo.getActivityInfo().applicationInfo.isInternal()) {
                    this.mAdmins.add(new DeviceAdminListItem(this.mContext, deviceAdminInfoCreateDeviceAdminInfo));
                }
            }
        }
    }

    private static DeviceAdminInfo createDeviceAdminInfo(Context context, ActivityInfo activityInfo) {
        try {
            return new DeviceAdminInfo(context, activityInfo);
        } catch (IOException | XmlPullParserException e) {
            Log.w(TAG, "Skipping " + activityInfo, e);
            return null;
        }
    }
}
