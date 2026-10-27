package com.amazon.avod.media.contentcache.internal.admittance;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import com.amazon.avod.media.contentcache.internal.CacheConfig;
import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ForegroundApplicationAdmittancePolicy implements AdmittancePolicy {
    private final ActivityManager mActivityManager;
    private final CacheConfig mCacheConfig;

    public ForegroundApplicationAdmittancePolicy(Context context) {
        this((ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME), CacheConfig.INSTANCE);
    }

    ForegroundApplicationAdmittancePolicy(ActivityManager activityManager, CacheConfig cacheConfig) {
        this.mActivityManager = (ActivityManager) Preconditions.checkNotNull(activityManager);
        this.mCacheConfig = (CacheConfig) Preconditions.checkNotNull(cacheConfig);
    }

    @Override // com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicy
    public AdmittancePolicyResult canAdmit(WritableCacheRecord writableCacheRecord) {
        if (!isCachingPermitted()) {
            return AdmittancePolicyResult.DENIED_CACHING_NOT_PERMITTED;
        }
        return AdmittancePolicyResult.ALLOWED;
    }

    @SuppressLint({"NewApi"})
    boolean isCachingPermitted() {
        if (!this.mCacheConfig.shouldBlockCachingOnForegroundApplication()) {
            return true;
        }
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = this.mActivityManager.getRunningTasks(1);
            if (runningTasks.isEmpty()) {
                DLog.warnf("Could not detect foreground application; empty list returned");
                return true;
            }
            String packageName = runningTasks.get(0).topActivity.getPackageName();
            Iterator<String> it = this.mCacheConfig.getForegroundApplicationPrefixWhitelist().iterator();
            while (it.hasNext()) {
                if (packageName.startsWith(it.next())) {
                    DLog.logf("Foreground application is %s, permitting caching", packageName);
                    return true;
                }
            }
            DLog.logf("Foreground application is %s, preventing caching", packageName);
            return false;
        } catch (SecurityException e) {
            DLog.warnf("Could not detect foreground application; caught SecurityException %s", e);
            return true;
        }
    }
}
