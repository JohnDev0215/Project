package android.ext.services.watchdog;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.provider.DeviceConfig;
import android.service.watchdog.ExplicitHealthCheckService;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ExplicitHealthCheckServiceImpl extends ExplicitHealthCheckService {
    final Map<String, ExplicitHealthChecker> mSupportedCheckers = new ConcurrentHashMap();

    public void onCreate() {
        super.onCreate();
        initHealthCheckers();
    }

    public void onRequestHealthCheck(String str) {
        ExplicitHealthChecker explicitHealthChecker = this.mSupportedCheckers.get(str);
        if (explicitHealthChecker != null) {
            explicitHealthChecker.request();
            return;
        }
        Log.w("ExplicitHealthCheckServiceImpl", "Ignoring request for explicit health check for unsupported package " + str);
    }

    public void onCancelHealthCheck(String str) {
        ExplicitHealthChecker explicitHealthChecker = this.mSupportedCheckers.get(str);
        if (explicitHealthChecker != null) {
            explicitHealthChecker.cancel();
            return;
        }
        Log.w("ExplicitHealthCheckServiceImpl", "Ignoring request to cancel explicit health check for unsupported package " + str);
    }

    public List<ExplicitHealthCheckService.PackageConfig> onGetSupportedPackages() {
        ArrayList arrayList = new ArrayList();
        long j = DeviceConfig.getLong("rollback", "watchdog_request_timeout_millis", 86400000L);
        long j2 = j > 0 ? j : 86400000L;
        Iterator<ExplicitHealthChecker> it = this.mSupportedCheckers.values().iterator();
        while (it.hasNext()) {
            arrayList.add(new ExplicitHealthCheckService.PackageConfig(it.next().getSupportedPackageName(), j2));
        }
        return arrayList;
    }

    public List<String> onGetRequestedPackages() {
        ArrayList arrayList = new ArrayList();
        for (ExplicitHealthChecker explicitHealthChecker : this.mSupportedCheckers.values()) {
            if (explicitHealthChecker.isPending()) {
                arrayList.add(explicitHealthChecker.getSupportedPackageName());
            }
        }
        return arrayList;
    }

    private void initHealthCheckers() {
        ComponentName componentNameResolveSystemService = resolveSystemService(new Intent("android.net.INetworkStackConnector"), getPackageManager(), 0);
        if (componentNameResolveSystemService != null) {
            String packageName = componentNameResolveSystemService.getPackageName();
            this.mSupportedCheckers.put(packageName, new NetworkChecker(this, packageName));
        } else {
            Log.i("ExplicitHealthCheckServiceImpl", "Network stack module not found");
        }
    }

    private ComponentName resolveSystemService(Intent intent, PackageManager packageManager, int i) {
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, i);
        ComponentName componentName = null;
        if (listQueryIntentServices == null) {
            return null;
        }
        for (int i2 = 0; i2 < listQueryIntentServices.size(); i2++) {
            ResolveInfo resolveInfo = listQueryIntentServices.get(i2);
            if ((resolveInfo.serviceInfo.applicationInfo.flags & 1) != 0) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                ComponentName componentName2 = new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name);
                if (componentName != null) {
                    throw new IllegalStateException("Multiple system services handle " + this + ": " + componentName + ", " + componentName2);
                }
                componentName = componentName2;
            }
        }
        return componentName;
    }
}
