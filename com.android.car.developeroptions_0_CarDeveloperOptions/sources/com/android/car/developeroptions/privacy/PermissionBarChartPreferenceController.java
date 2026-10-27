package com.android.car.developeroptions.privacy;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.permission.PermissionControllerManager;
import android.permission.RuntimePermissionUsageInfo;
import android.provider.DeviceConfig;
import android.util.Log;
import android.view.View;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.widget.BarChartInfo;
import com.android.settingslib.widget.BarChartPreference;
import com.android.settingslib.widget.BarViewInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class PermissionBarChartPreferenceController extends BasePreferenceController implements PermissionControllerManager.OnPermissionUsageResultCallback, LifecycleObserver, OnCreate, OnStart, OnSaveInstanceState {
    private static final String KEY_PERMISSION_USAGE = "usage_infos";
    private static final String TAG = "BarChartPreferenceCtl";
    private BarChartPreference mBarChartPreference;
    List<RuntimePermissionUsageInfo> mOldUsageInfos;
    private PackageManager mPackageManager;
    private PrivacyDashboardFragment mParent;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
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

    public PermissionBarChartPreferenceController(Context context, String str) {
        super(context, str);
        this.mOldUsageInfos = new ArrayList();
        this.mPackageManager = context.getPackageManager();
    }

    public void setFragment(PrivacyDashboardFragment privacyDashboardFragment) {
        this.mParent = privacyDashboardFragment;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnCreate
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mOldUsageInfos = bundle.getParcelableArrayList(KEY_PERMISSION_USAGE);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnSaveInstanceState
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableList(KEY_PERMISSION_USAGE, this.mOldUsageInfos);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return Boolean.parseBoolean(DeviceConfig.getProperty("privacy", "permissions_hub_enabled")) ? 1 : 3;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mBarChartPreference = (BarChartPreference) preferenceScreen.findPreference(getPreferenceKey());
        BarChartInfo.Builder builder = new BarChartInfo.Builder();
        builder.setTitle(R.string.permission_bar_chart_title);
        builder.setDetails(R.string.permission_bar_chart_details);
        builder.setEmptyText(R.string.permission_bar_chart_empty_text);
        builder.setDetailsOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.privacy.-$$Lambda$PermissionBarChartPreferenceController$GloNZNsYMoQYpsMK2G8MizNmMus
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$0$PermissionBarChartPreferenceController(view);
            }
        });
        this.mBarChartPreference.initializeBarChart(builder.build());
        if (this.mOldUsageInfos.isEmpty()) {
            return;
        }
        this.mBarChartPreference.setBarViewInfos(createBarViews(this.mOldUsageInfos));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$PermissionBarChartPreferenceController(View view) {
        Intent intent = new Intent("android.intent.action.REVIEW_PERMISSION_USAGE");
        intent.putExtra("android.intent.extra.DURATION_MILLIS", TimeUnit.DAYS.toMillis(1L));
        this.mContext.startActivity(intent);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        if (isAvailable()) {
            this.mBarChartPreference.updateLoadingState(this.mOldUsageInfos.isEmpty());
            this.mParent.setLoadingEnabled(true);
            retrievePermissionUsageData();
        }
    }

    public void onPermissionUsageResult(List<RuntimePermissionUsageInfo> list) {
        list.sort(new Comparator() { // from class: com.android.car.developeroptions.privacy.-$$Lambda$PermissionBarChartPreferenceController$xYlc1RckJe8bxWOKQ4cAVI8o8cE
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return PermissionBarChartPreferenceController.lambda$onPermissionUsageResult$1((RuntimePermissionUsageInfo) obj, (RuntimePermissionUsageInfo) obj2);
            }
        });
        if (!areSamePermissionGroups(list)) {
            this.mBarChartPreference.setBarViewInfos(createBarViews(list));
            this.mOldUsageInfos = list;
        }
        this.mBarChartPreference.updateLoadingState(false);
        this.mParent.setLoadingEnabled(false);
    }

    static /* synthetic */ int lambda$onPermissionUsageResult$1(RuntimePermissionUsageInfo runtimePermissionUsageInfo, RuntimePermissionUsageInfo runtimePermissionUsageInfo2) {
        int appAccessCount = runtimePermissionUsageInfo2.getAppAccessCount() - runtimePermissionUsageInfo.getAppAccessCount();
        if (appAccessCount != 0) {
            return appAccessCount;
        }
        String name = runtimePermissionUsageInfo.getName();
        String name2 = runtimePermissionUsageInfo2.getName();
        if (name.equals("android.permission-group.LOCATION")) {
            return -1;
        }
        if (name2.equals("android.permission-group.LOCATION")) {
            return 1;
        }
        if (name.equals("android.permission-group.MICROPHONE")) {
            return -1;
        }
        if (name2.equals("android.permission-group.MICROPHONE")) {
            return 1;
        }
        if (name.equals("android.permission-group.CAMERA")) {
            return -1;
        }
        if (name2.equals("android.permission-group.CAMERA")) {
            return 1;
        }
        return runtimePermissionUsageInfo.getName().compareTo(runtimePermissionUsageInfo2.getName());
    }

    private void retrievePermissionUsageData() {
        ((PermissionControllerManager) this.mContext.getSystemService(PermissionControllerManager.class)).getPermissionUsages(false, (int) TimeUnit.DAYS.toMillis(1L), this.mContext.getMainExecutor(), this);
    }

    private BarViewInfo[] createBarViews(List<RuntimePermissionUsageInfo> list) {
        if (list.isEmpty()) {
            return null;
        }
        list.removeIf(new Predicate() { // from class: com.android.car.developeroptions.privacy.-$$Lambda$PermissionBarChartPreferenceController$JuLbnrL4afTERchac1gVei-_cJw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((RuntimePermissionUsageInfo) obj).getName().equals("android.permission-group.STORAGE");
            }
        });
        int iMin = Math.min(4, list.size());
        BarViewInfo[] barViewInfoArr = new BarViewInfo[iMin];
        for (int i = 0; i < iMin; i++) {
            final RuntimePermissionUsageInfo runtimePermissionUsageInfo = list.get(i);
            int appAccessCount = runtimePermissionUsageInfo.getAppAccessCount();
            CharSequence permissionGroupLabel = getPermissionGroupLabel(runtimePermissionUsageInfo.getName());
            barViewInfoArr[i] = new BarViewInfo(getPermissionGroupIcon(runtimePermissionUsageInfo.getName()), appAccessCount, permissionGroupLabel, this.mContext.getResources().getQuantityString(R.plurals.permission_bar_chart_label, appAccessCount, Integer.valueOf(appAccessCount)), permissionGroupLabel);
            barViewInfoArr[i].setClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.privacy.-$$Lambda$PermissionBarChartPreferenceController$U_anqYmCdcWUD16j9NkH5__3UX0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$createBarViews$3$PermissionBarChartPreferenceController(runtimePermissionUsageInfo, view);
                }
            });
        }
        return barViewInfoArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createBarViews$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createBarViews$3$PermissionBarChartPreferenceController(RuntimePermissionUsageInfo runtimePermissionUsageInfo, View view) {
        Intent intent = new Intent("android.intent.action.REVIEW_PERMISSION_USAGE");
        intent.putExtra("android.intent.extra.PERMISSION_GROUP_NAME", runtimePermissionUsageInfo.getName());
        intent.putExtra("android.intent.extra.DURATION_MILLIS", TimeUnit.DAYS.toMillis(1L));
        this.mContext.startActivity(intent);
    }

    private Drawable getPermissionGroupIcon(String str) {
        Drawable drawableLoadIcon = null;
        try {
            drawableLoadIcon = this.mPackageManager.getPermissionGroupInfo(str, 0).loadIcon(this.mPackageManager);
            drawableLoadIcon.setTintList(Utils.getColorAttr(this.mContext, android.R.attr.textColorSecondary));
            return drawableLoadIcon;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Cannot find group icon for " + str, e);
            return drawableLoadIcon;
        }
    }

    private CharSequence getPermissionGroupLabel(String str) {
        try {
            return this.mPackageManager.getPermissionGroupInfo(str, 0).loadLabel(this.mPackageManager);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Cannot find group label for " + str, e);
            return null;
        }
    }

    private boolean areSamePermissionGroups(List<RuntimePermissionUsageInfo> list) {
        if (list.size() != this.mOldUsageInfos.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            RuntimePermissionUsageInfo runtimePermissionUsageInfo = list.get(i);
            RuntimePermissionUsageInfo runtimePermissionUsageInfo2 = this.mOldUsageInfos.get(i);
            if (!runtimePermissionUsageInfo.getName().equals(runtimePermissionUsageInfo2.getName()) || runtimePermissionUsageInfo.getAppAccessCount() != runtimePermissionUsageInfo2.getAppAccessCount()) {
                return false;
            }
        }
        return true;
    }
}
