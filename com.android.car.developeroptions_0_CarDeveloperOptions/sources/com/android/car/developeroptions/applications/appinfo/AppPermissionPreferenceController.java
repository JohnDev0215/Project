package com.android.car.developeroptions.applications.appinfo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.icu.text.ListFormatter;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.PermissionsSummaryHelper;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppPermissionPreferenceController extends AppInfoPreferenceControllerBase {
    private static final String EXTRA_HIDE_INFO_BUTTON = "hideInfoButton";
    private static final String TAG = "PermissionPrefControl";
    private String mPackageName;
    final PermissionsSummaryHelper.PermissionsResultCallback mPermissionCallback;

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppPermissionPreferenceController(Context context, String str) {
        super(context, str);
        this.mPermissionCallback = new PermissionsSummaryHelper.PermissionsResultCallback() { // from class: com.android.car.developeroptions.applications.appinfo.AppPermissionPreferenceController.1
            @Override // com.android.settingslib.applications.PermissionsSummaryHelper.PermissionsResultCallback
            public void onPermissionSummaryResult(int i, int i2, int i3, List<CharSequence> list) {
                String string;
                Resources resources = ((AbstractPreferenceController) AppPermissionPreferenceController.this).mContext.getResources();
                if (i2 == 0) {
                    string = resources.getString(R.string.runtime_permissions_summary_no_permissions_requested);
                    AppPermissionPreferenceController.this.mPreference.setEnabled(false);
                } else {
                    ArrayList arrayList = new ArrayList(list);
                    if (i3 > 0) {
                        arrayList.add(resources.getQuantityString(R.plurals.runtime_permissions_additional_count, i3, Integer.valueOf(i3)));
                    }
                    if (arrayList.size() == 0) {
                        string = resources.getString(R.string.runtime_permissions_summary_no_permissions_granted);
                    } else {
                        string = ListFormatter.getInstance().format(arrayList);
                    }
                    AppPermissionPreferenceController.this.mPreference.setEnabled(true);
                }
                AppPermissionPreferenceController.this.mPreference.setSummary(string);
            }
        };
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        PermissionsSummaryHelper.getPermissionSummary(this.mContext, this.mPackageName, this.mPermissionCallback);
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        startManagePermissionsActivity();
        return true;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    private void startManagePermissionsActivity() {
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
        intent.putExtra("android.intent.extra.PACKAGE_NAME", this.mParent.getAppEntry().info.packageName);
        intent.putExtra(EXTRA_HIDE_INFO_BUTTON, true);
        try {
            this.mParent.getActivity().startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException unused) {
            Log.w(TAG, "No app can handle android.intent.action.MANAGE_APP_PERMISSIONS");
        }
    }
}
