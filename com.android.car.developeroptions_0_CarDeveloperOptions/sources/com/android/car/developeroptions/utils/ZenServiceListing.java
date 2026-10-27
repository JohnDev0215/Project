package com.android.car.developeroptions.utils;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.ArraySet;
import android.util.Slog;
import com.android.car.developeroptions.utils.ManagedServiceSettings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ZenServiceListing {
    private final ManagedServiceSettings.Config mConfig;
    private final Context mContext;
    private final NotificationManager mNm;
    private final Set<ComponentInfo> mApprovedComponents = new ArraySet();
    private final List<Callback> mZenCallbacks = new ArrayList();

    public interface Callback {
        void onComponentsReloaded(Set<ComponentInfo> set);
    }

    public ZenServiceListing(Context context, ManagedServiceSettings.Config config) {
        this.mContext = context;
        this.mConfig = config;
        this.mNm = (NotificationManager) context.getSystemService("notification");
    }

    public ComponentInfo findService(ComponentName componentName) {
        if (componentName == null) {
            return null;
        }
        for (ComponentInfo componentInfo : this.mApprovedComponents) {
            if (new ComponentName(componentInfo.packageName, componentInfo.name).equals(componentName)) {
                return componentInfo;
            }
        }
        return null;
    }

    public void addZenCallback(Callback callback) {
        this.mZenCallbacks.add(callback);
    }

    public void removeZenCallback(Callback callback) {
        this.mZenCallbacks.remove(callback);
    }

    public void reloadApprovedServices() {
        this.mApprovedComponents.clear();
        List enabledNotificationListenerPackages = this.mNm.getEnabledNotificationListenerPackages();
        ArrayList<ComponentInfo> arrayList = new ArrayList();
        getServices(this.mConfig, arrayList, this.mContext.getPackageManager());
        getActivities(this.mConfig, arrayList, this.mContext.getPackageManager());
        for (ComponentInfo componentInfo : arrayList) {
            String packageName = componentInfo.getComponentName().getPackageName();
            if (this.mNm.isNotificationPolicyAccessGrantedForPackage(packageName) || enabledNotificationListenerPackages.contains(packageName)) {
                this.mApprovedComponents.add(componentInfo);
            }
        }
        if (this.mApprovedComponents.isEmpty()) {
            return;
        }
        Iterator<Callback> it = this.mZenCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onComponentsReloaded(this.mApprovedComponents);
        }
    }

    private static void getServices(ManagedServiceSettings.Config config, List<ComponentInfo> list, PackageManager packageManager) {
        List listQueryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent(config.intentAction), 132, ActivityManager.getCurrentUser());
        int size = listQueryIntentServicesAsUser.size();
        for (int i = 0; i < size; i++) {
            ServiceInfo serviceInfo = ((ResolveInfo) listQueryIntentServicesAsUser.get(i)).serviceInfo;
            if (!config.permission.equals(serviceInfo.permission)) {
                Slog.w(config.tag, "Skipping " + config.noun + " service " + serviceInfo.packageName + "/" + serviceInfo.name + ": it does not require the permission " + config.permission);
            } else if (list != null) {
                list.add(serviceInfo);
            }
        }
    }

    private static void getActivities(ManagedServiceSettings.Config config, List<ComponentInfo> list, PackageManager packageManager) {
        List listQueryIntentActivitiesAsUser = packageManager.queryIntentActivitiesAsUser(new Intent(config.configIntentAction), 129, ActivityManager.getCurrentUser());
        int size = listQueryIntentActivitiesAsUser.size();
        for (int i = 0; i < size; i++) {
            ActivityInfo activityInfo = ((ResolveInfo) listQueryIntentActivitiesAsUser.get(i)).activityInfo;
            if (list != null) {
                list.add(activityInfo);
            }
        }
    }
}
