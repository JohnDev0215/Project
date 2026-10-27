package com.bugsnag.android;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ActivityBreadcrumbCollector.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001BI\u0012B\u0010\u0002\u001a>\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0003¢\u0006\u0002\u0010\fJ\u0018\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J)\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0010\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0018\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u0010\u0010 \u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016RJ\u0010\u0002\u001a>\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00040\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/bugsnag/android/ActivityBreadcrumbCollector;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "cb", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "message", "", "", "method", "", "(Lkotlin/jvm/functions/Function2;)V", "prevState", "Ljava/util/WeakHashMap;", "Landroid/app/Activity;", "getActivityName", "kotlin.jvm.PlatformType", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "leaveBreadcrumb", "lifecycleCallback", "hasBundle", "", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/Boolean;)V", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class ActivityBreadcrumbCollector implements Application.ActivityLifecycleCallbacks {
    private final Function2<String, Map<String, ? extends Object>, Unit> cb;
    private final WeakHashMap<Activity, String> prevState = new WeakHashMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    public ActivityBreadcrumbCollector(Function2<? super String, ? super Map<String, ? extends Object>, Unit> function2) {
        this.cb = function2;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        leaveBreadcrumb(activity, "onCreate()", Boolean.valueOf(savedInstanceState != null));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        leaveBreadcrumb$default(this, activity, "onStart()", null, 4, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        leaveBreadcrumb$default(this, activity, "onResume()", null, 4, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        leaveBreadcrumb$default(this, activity, "onPause()", null, 4, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        leaveBreadcrumb$default(this, activity, "onStop()", null, 4, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        leaveBreadcrumb$default(this, activity, "onSaveInstanceState()", null, 4, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        leaveBreadcrumb$default(this, activity, "onDestroy()", null, 4, null);
        this.prevState.remove(activity);
    }

    private final String getActivityName(Activity activity) {
        return activity.getClass().getSimpleName();
    }

    static /* synthetic */ void leaveBreadcrumb$default(ActivityBreadcrumbCollector activityBreadcrumbCollector, Activity activity, String str, Boolean bool, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        activityBreadcrumbCollector.leaveBreadcrumb(activity, str, bool);
    }

    private final void leaveBreadcrumb(Activity activity, String lifecycleCallback, Boolean hasBundle) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (hasBundle != null) {
            linkedHashMap.put("hasBundle", hasBundle);
        }
        String str = this.prevState.get(activity);
        if (str != null) {
            linkedHashMap.put("previous", str);
        }
        String activityName = getActivityName(activity);
        this.cb.mo4invoke(activityName + '#' + lifecycleCallback, linkedHashMap);
        this.prevState.put(activity, lifecycleCallback);
    }
}
