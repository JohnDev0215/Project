package com.android.phone.euicc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.telephony.euicc.EuiccManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.euicc.EuiccConnector;
import com.android.internal.telephony.util.TelephonyUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class EuiccUiDispatcherActivity extends Activity {
    private ThreadPoolExecutor mExecutor;
    private PermissionManager mPermissionManager;

    protected void onDispatchFailure() {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPermissionManager = (PermissionManager) getSystemService("permission");
        this.mExecutor = new ThreadPoolExecutor(1, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory(this) { // from class: com.android.phone.euicc.EuiccUiDispatcherActivity.1
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "EuiccService #" + this.mCount.getAndIncrement());
            }
        });
        try {
            Intent intentResolveEuiccUiIntent = resolveEuiccUiIntent();
            if (intentResolveEuiccUiIntent == null) {
                setResult(0);
                onDispatchFailure();
            } else {
                intentResolveEuiccUiIntent.setFlags(33554432);
                startActivity(intentResolveEuiccUiIntent);
            }
        } finally {
            finish();
        }
    }

    @VisibleForTesting
    Intent resolveEuiccUiIntent() {
        if (!((EuiccManager) getSystemService("euicc")).isEnabled()) {
            Log.w("EuiccUiDispatcher", "eUICC not enabled");
            return null;
        }
        Intent euiccUiIntent = getEuiccUiIntent();
        if (euiccUiIntent == null) {
            Log.w("EuiccUiDispatcher", "Unable to handle intent");
            return null;
        }
        revokePermissionFromLuiApps(euiccUiIntent);
        ActivityInfo activityInfoFindBestActivity = findBestActivity(euiccUiIntent);
        if (activityInfoFindBestActivity == null) {
            Log.w("EuiccUiDispatcher", "Could not resolve activity for intent: " + euiccUiIntent);
            return null;
        }
        grantDefaultPermissionsToLuiApp(activityInfoFindBestActivity);
        euiccUiIntent.setComponent(new ComponentName(activityInfoFindBestActivity.packageName, activityInfoFindBestActivity.name));
        return euiccUiIntent;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected android.content.Intent getEuiccUiIntent() {
        /*
            r4 = this;
            android.content.Intent r0 = r4.getIntent()
            java.lang.String r0 = r0.getAction()
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            android.content.Intent r4 = r4.getIntent()
            r1.putExtras(r4)
            int r4 = r0.hashCode()
            r2 = -344086803(0xffffffffeb7da6ed, float:-3.0664652E26)
            r3 = 1
            if (r4 == r2) goto L2e
            r2 = -281938662(0xffffffffef31f51a, float:-5.5075156E28)
            if (r4 == r2) goto L24
            goto L38
        L24:
            java.lang.String r4 = "android.telephony.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L38
            r4 = r3
            goto L39
        L2e:
            java.lang.String r4 = "android.telephony.euicc.action.MANAGE_EMBEDDED_SUBSCRIPTIONS"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L38
            r4 = 0
            goto L39
        L38:
            r4 = -1
        L39:
            if (r4 == 0) goto L5b
            if (r4 == r3) goto L55
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = "Unsupported action: "
            r4.append(r1)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.String r0 = "EuiccUiDispatcher"
            android.util.Log.w(r0, r4)
            r4 = 0
            return r4
        L55:
            java.lang.String r4 = "android.service.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION"
            r1.setAction(r4)
            goto L60
        L5b:
            java.lang.String r4 = "android.service.euicc.action.MANAGE_EMBEDDED_SUBSCRIPTIONS"
            r1.setAction(r4)
        L60:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.euicc.EuiccUiDispatcherActivity.getEuiccUiIntent():android.content.Intent");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.mExecutor.shutdownNow();
        super.onDestroy();
    }

    @VisibleForTesting
    ActivityInfo findBestActivity(Intent intent) {
        return EuiccConnector.findBestActivity(getPackageManager(), intent);
    }

    @VisibleForTesting
    protected void grantDefaultPermissionsToLuiApp(ActivityInfo activityInfo) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            this.mPermissionManager.grantDefaultPermissionsToLuiApp(activityInfo.packageName, UserHandle.of(UserHandle.myUserId()), this.mExecutor, new Consumer() { // from class: com.android.phone.euicc.-$$Lambda$EuiccUiDispatcherActivity$Ie7_uxPVBDl2acIBrHPeK5IJFGQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    EuiccUiDispatcherActivity.lambda$grantDefaultPermissionsToLuiApp$0(countDownLatch, (Boolean) obj);
                }
            });
            TelephonyUtils.waitUntilReady(countDownLatch, 15000L);
        } catch (RuntimeException e) {
            Log.e("EuiccUiDispatcher", "Failed to grant permissions to active LUI app.", e);
        }
    }

    static /* synthetic */ void lambda$grantDefaultPermissionsToLuiApp$0(CountDownLatch countDownLatch, Boolean bool) {
        if (bool.booleanValue()) {
            countDownLatch.countDown();
        } else {
            Log.e("EuiccUiDispatcher", "Failed to revoke LUI app permissions.");
        }
    }

    @VisibleForTesting
    protected void revokePermissionFromLuiApps(Intent intent) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            Set<String> allLuiAppPackageNames = getAllLuiAppPackageNames(intent);
            this.mPermissionManager.revokeDefaultPermissionsFromLuiApps((String[]) allLuiAppPackageNames.toArray(new String[allLuiAppPackageNames.size()]), UserHandle.of(UserHandle.myUserId()), this.mExecutor, new Consumer() { // from class: com.android.phone.euicc.-$$Lambda$EuiccUiDispatcherActivity$-gNDNL2z_TY4paiXvy3jkov6qBI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    EuiccUiDispatcherActivity.lambda$revokePermissionFromLuiApps$1(countDownLatch, (Boolean) obj);
                }
            });
            TelephonyUtils.waitUntilReady(countDownLatch, 15000L);
        } catch (RuntimeException e) {
            Log.e("EuiccUiDispatcher", "Failed to revoke LUI app permissions.");
            throw e;
        }
    }

    static /* synthetic */ void lambda$revokePermissionFromLuiApps$1(CountDownLatch countDownLatch, Boolean bool) {
        if (bool.booleanValue()) {
            countDownLatch.countDown();
        } else {
            Log.e("EuiccUiDispatcher", "Failed to revoke LUI app permissions.");
        }
    }

    private Set<String> getAllLuiAppPackageNames(Intent intent) {
        List<ResolveInfo> listQueryIntentServices = getPackageManager().queryIntentServices(intent, 269484096);
        HashSet hashSet = new HashSet();
        Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            if (serviceInfo != null) {
                hashSet.add(serviceInfo.packageName);
            }
        }
        return hashSet;
    }
}
