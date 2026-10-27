package com.android.permissioncontroller.role.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class PreferredActivity {
    private final RequiredActivity mActivity;
    private final List<IntentFilterData> mIntentFilterDatas;

    public PreferredActivity(RequiredActivity activity, List<IntentFilterData> intentFilterDatas) {
        this.mActivity = activity;
        this.mIntentFilterDatas = intentFilterDatas;
    }

    public RequiredActivity getActivity() {
        return this.mActivity;
    }

    public List<IntentFilterData> getIntentFilterDatas() {
        return this.mIntentFilterDatas;
    }

    public void configure(String packageName, Context context) {
        PreferredActivity preferredActivity = this;
        ComponentName packageActivity = preferredActivity.mActivity.getQualifyingComponentForPackage(packageName, context);
        if (packageActivity == null) {
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        int intentFilterDatasSize = preferredActivity.mIntentFilterDatas.size();
        int i = 0;
        while (i < intentFilterDatasSize) {
            IntentFilterData intentFilterData = preferredActivity.mIntentFilterDatas.get(i);
            IntentFilter intentFilter = intentFilterData.createIntentFilter();
            intentFilter.addCategory("android.intent.category.DEFAULT");
            int match = intentFilterData.getDataScheme() != null ? 2097152 : 1048576;
            Intent intent = intentFilterData.createIntent();
            List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 851968);
            List<ComponentName> set = new ArrayList<>();
            int resolveInfosSize = resolveInfos.size();
            for (int resolveInfosIndex = 0; resolveInfosIndex < resolveInfosSize; resolveInfosIndex++) {
                ResolveInfo resolveInfo = resolveInfos.get(resolveInfosIndex);
                ComponentName componentName = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                set.add(componentName);
            }
            packageManager.replacePreferredActivity(intentFilter, match, set, packageActivity);
            i++;
            preferredActivity = this;
        }
    }

    public String toString() {
        return "PreferredActivity{mActivity=" + this.mActivity + ", mIntentFilterDatas=" + this.mIntentFilterDatas + '}';
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PreferredActivity that = (PreferredActivity) object;
        if (Objects.equals(this.mActivity, that.mActivity) && Objects.equals(this.mIntentFilterDatas, that.mIntentFilterDatas)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mActivity, this.mIntentFilterDatas);
    }
}
