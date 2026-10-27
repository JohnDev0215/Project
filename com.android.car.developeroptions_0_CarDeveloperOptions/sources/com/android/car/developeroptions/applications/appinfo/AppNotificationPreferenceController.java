package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.notification.AppNotificationSettings;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes.dex */
public class AppNotificationPreferenceController extends AppInfoPreferenceControllerBase {
    private final NotificationBackend mBackend;
    private String mChannelId;

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

    public AppNotificationPreferenceController(Context context, String str) {
        super(context, str);
        this.mChannelId = null;
        this.mBackend = new NotificationBackend();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase
    public void setParentFragment(AppInfoDashboardFragment appInfoDashboardFragment) {
        super.setParentFragment(appInfoDashboardFragment);
        if (appInfoDashboardFragment == null || appInfoDashboardFragment.getActivity() == null || appInfoDashboardFragment.getActivity().getIntent() == null) {
            return;
        }
        this.mChannelId = appInfoDashboardFragment.getActivity().getIntent().getStringExtra(":settings:fragment_args_key");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(getNotificationSummary(this.mParent.getAppEntry(), this.mContext, this.mBackend));
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase
    protected Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return AppNotificationSettings.class;
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase
    protected Bundle getArguments() {
        if (this.mChannelId == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(":settings:fragment_args_key", this.mChannelId);
        return bundle;
    }

    private CharSequence getNotificationSummary(ApplicationsState.AppEntry appEntry, Context context, NotificationBackend notificationBackend) {
        return getNotificationSummary(notificationBackend.loadAppRow(context, context.getPackageManager(), appEntry.info), context);
    }

    public static CharSequence getNotificationSummary(NotificationBackend.AppRow appRow, Context context) {
        if (appRow == null) {
            return "";
        }
        if (appRow.banned) {
            return context.getText(R.string.notifications_disabled);
        }
        int i = appRow.channelCount;
        if (i == 0) {
            return NotificationBackend.getSentSummary(context, appRow.sentByApp, false);
        }
        int i2 = appRow.blockedChannelCount;
        if (i == i2) {
            return context.getText(R.string.notifications_disabled);
        }
        if (i2 == 0) {
            return NotificationBackend.getSentSummary(context, appRow.sentByApp, false);
        }
        Resources resources = context.getResources();
        int i3 = appRow.blockedChannelCount;
        return context.getString(R.string.notifications_enabled_with_info, NotificationBackend.getSentSummary(context, appRow.sentByApp, false), resources.getQuantityString(R.plurals.notifications_categories_off, i3, Integer.valueOf(i3)));
    }
}
