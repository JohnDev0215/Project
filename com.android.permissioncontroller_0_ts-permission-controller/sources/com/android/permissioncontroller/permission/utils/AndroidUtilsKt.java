package com.android.permissioncontroller.permission.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.os.UserHandle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

/* JADX INFO: compiled from: AndroidUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\r\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005\u001aS\u0010\u0010\u001a\u00020\f*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000e2*\u0010\u0016\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u00180\u0017\"\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\u0010\u001a\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"IPC", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getIPC", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "IPC_THREAD_POOL_COUNT", "", "application", "Landroid/app/Application;", "Landroid/content/Context;", "getApplication", "(Landroid/content/Context;)Landroid/app/Application;", "ensureMainThread", "", "getUid", "Landroid/os/UserHandle;", "appId", "updatePermissionFlags", "Landroid/content/pm/PackageManager;", "permissionName", "", "packageName", "user", "flags", "", "Lkotlin/Pair;", "", "(Landroid/content/pm/PackageManager;Ljava/lang/String;Ljava/lang/String;Landroid/os/UserHandle;[Lkotlin/Pair;)V", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 2, mv = {1, 1, 16})
public final class AndroidUtilsKt {
    private static final ExecutorCoroutineDispatcher IPC;
    public static final int IPC_THREAD_POOL_COUNT = 8;

    public static final Application getApplication(Context application) {
        Intrinsics.checkParameterIsNotNull(application, "$this$application");
        if (application instanceof Application) {
            return (Application) application;
        }
        if (!(application instanceof Activity)) {
            if (!(application instanceof Service)) {
                if (application instanceof ContextWrapper) {
                    Context baseContext = ((ContextWrapper) application).getBaseContext();
                    Intrinsics.checkExpressionValueIsNotNull(baseContext, "baseContext");
                    return getApplication(baseContext);
                }
                Context applicationContext = application.getApplicationContext();
                if (applicationContext != null) {
                    return (Application) applicationContext;
                }
                throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
            }
            Application application2 = ((Service) application).getApplication();
            Intrinsics.checkExpressionValueIsNotNull(application2, "application");
            return application2;
        }
        Application application3 = ((Activity) application).getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application3, "application");
        return application3;
    }

    static {
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(8);
        Intrinsics.checkExpressionValueIsNotNull(executorServiceNewFixedThreadPool, "Executors.newFixedThread…ol(IPC_THREAD_POOL_COUNT)");
        IPC = ExecutorsKt.from(executorServiceNewFixedThreadPool);
    }

    public static final ExecutorCoroutineDispatcher getIPC() {
        return IPC;
    }

    public static final void ensureMainThread() {
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            throw new IllegalStateException("Only meant to be used on the main thread".toString());
        }
    }

    public static final void updatePermissionFlags(PackageManager updatePermissionFlags, String permissionName, String packageName, UserHandle user, Pair<Integer, Boolean>... flags) {
        Intrinsics.checkParameterIsNotNull(updatePermissionFlags, "$this$updatePermissionFlags");
        Intrinsics.checkParameterIsNotNull(permissionName, "permissionName");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(flags, "flags");
        int accumulator$iv = 0;
        for (Pair<Integer, Boolean> pair : flags) {
            int mask = accumulator$iv;
            int flag = pair.component1().intValue();
            accumulator$iv = mask | flag;
        }
        int mask2 = accumulator$iv;
        int accumulator$iv2 = 0;
        for (Pair<Integer, Boolean> pair2 : flags) {
            int mask3 = accumulator$iv2;
            int flag2 = pair2.component1().intValue();
            boolean flagValue = pair2.component2().booleanValue();
            if (flagValue) {
                mask3 |= flag2;
            }
            accumulator$iv2 = mask3;
        }
        int value = accumulator$iv2;
        updatePermissionFlags.updatePermissionFlags(permissionName, packageName, mask2, value, user);
    }

    public static final int getUid(UserHandle getUid, int appId) {
        Intrinsics.checkParameterIsNotNull(getUid, "$this$getUid");
        return (getUid.getIdentifier() * 100000) + (appId % 100000);
    }
}
