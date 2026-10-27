package com.android.car.developeroptions.applications.specialaccess.zenaccess;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ZenAccessController extends BasePreferenceController {
    private static final String TAG = "ZenAccessController";
    private final ActivityManager mActivityManager;

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

    public ZenAccessController(Context context, String str) {
        super(context, str);
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return isSupported(this.mActivityManager) ? 1 : 3;
    }

    public static boolean isSupported(ActivityManager activityManager) {
        return !activityManager.isLowRamDevice();
    }

    public static Set<String> getPackagesRequestingNotificationPolicyAccess() {
        ArraySet arraySet = new ArraySet();
        try {
            List list = AppGlobals.getPackageManager().getPackagesHoldingPermissions(new String[]{"android.permission.ACCESS_NOTIFICATION_POLICY"}, 0, ActivityManager.getCurrentUser()).getList();
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arraySet.add(((PackageInfo) it.next()).packageName);
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot reach packagemanager", e);
        }
        return arraySet;
    }

    public static Set<String> getAutoApprovedPackages(Context context) {
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(((NotificationManager) context.getSystemService(NotificationManager.class)).getEnabledNotificationListenerPackages());
        return arraySet;
    }

    public static boolean hasAccess(Context context, String str) {
        return ((NotificationManager) context.getSystemService(NotificationManager.class)).isNotificationPolicyAccessGrantedForPackage(str);
    }

    public static void setAccess(final Context context, final String str, final boolean z) {
        logSpecialPermissionChange(z, str, context);
        AsyncTask.execute(new Runnable() { // from class: com.android.car.developeroptions.applications.specialaccess.zenaccess.-$$Lambda$ZenAccessController$hwS4Cv7sUiV6CVQqfGvzKDCr4_Q
            @Override // java.lang.Runnable
            public final void run() {
                ((NotificationManager) context.getSystemService(NotificationManager.class)).setNotificationPolicyAccessGranted(str, z);
            }
        });
    }

    public static void deleteRules(final Context context, final String str) {
        AsyncTask.execute(new Runnable() { // from class: com.android.car.developeroptions.applications.specialaccess.zenaccess.-$$Lambda$ZenAccessController$BXDkEyx7HKau2vsun-efklEhxC8
            @Override // java.lang.Runnable
            public final void run() {
                ((NotificationManager) context.getSystemService(NotificationManager.class)).removeAutomaticZenRules(str);
            }
        });
    }

    static void logSpecialPermissionChange(boolean z, String str, Context context) {
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, z ? 768 : 769, str);
    }
}
