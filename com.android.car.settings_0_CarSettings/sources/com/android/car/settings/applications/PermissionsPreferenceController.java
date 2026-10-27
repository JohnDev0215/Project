package com.android.car.settings.applications;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.text.ListFormatter;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.applications.PermissionsSummaryHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PermissionsPreferenceController extends PreferenceController<Preference> {
    private static final Logger LOG = new Logger((Class<?>) PermissionsPreferenceController.class);
    private String mPackageName;
    private final PermissionsSummaryHelper.PermissionsResultCallback mPermissionCallback;
    private String mSummary;

    public PermissionsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPermissionCallback = new PermissionsSummaryHelper.PermissionsResultCallback() { // from class: com.android.car.settings.applications.PermissionsPreferenceController.1
            @Override // com.android.settingslib.applications.PermissionsSummaryHelper.PermissionsResultCallback
            public void onPermissionSummaryResult(int standardGrantedPermissionCount, int requestedPermissionCount, int additionalGrantedPermissionCount, List<CharSequence> grantedGroupLabels) {
                Resources res = PermissionsPreferenceController.this.getContext().getResources();
                if (requestedPermissionCount == 0) {
                    PermissionsPreferenceController.this.mSummary = res.getString(R.string.runtime_permissions_summary_no_permissions_requested);
                } else {
                    ArrayList<CharSequence> list = new ArrayList<>(grantedGroupLabels);
                    if (additionalGrantedPermissionCount > 0) {
                        list.add(res.getQuantityString(R.plurals.runtime_permissions_additional_count, additionalGrantedPermissionCount, Integer.valueOf(additionalGrantedPermissionCount)));
                    }
                    if (list.isEmpty()) {
                        PermissionsPreferenceController.this.mSummary = res.getString(R.string.runtime_permissions_summary_no_permissions_granted);
                    } else {
                        PermissionsPreferenceController.this.mSummary = ListFormatter.getInstance().format(list);
                    }
                }
                PermissionsPreferenceController.this.refreshUi();
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mPackageName == null) {
            throw new IllegalStateException("PackageName should be set before calling this function");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        PermissionsSummaryHelper.getPermissionSummary(getContext(), this.mPackageName, this.mPermissionCallback);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(getSummary());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
        intent.putExtra("android.intent.extra.PACKAGE_NAME", this.mPackageName);
        try {
            getContext().startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            LOG.w("No app can handle android.intent.action.MANAGE_APP_PERMISSIONS");
            return true;
        }
    }

    private CharSequence getSummary() {
        if (TextUtils.isEmpty(this.mSummary)) {
            return getContext().getString(R.string.computing_size);
        }
        return this.mSummary;
    }
}
