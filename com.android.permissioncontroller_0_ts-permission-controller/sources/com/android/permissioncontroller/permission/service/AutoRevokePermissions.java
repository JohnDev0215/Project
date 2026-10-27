package com.android.permissioncontroller.permission.service;

import android.app.usage.UsageStats;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import androidx.preference.PreferenceManager;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: compiled from: AutoRevokePermissions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0010\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0010H\u0002\u001a\u000e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0010\u001a\u000e\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0010\u001a!\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!\u001a!\u0010\"\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020$H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010%\u001a5\u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020$0'0\b2\u0006\u0010\u0019\u001a\u00020\u00102\b\b\u0002\u0010(\u001a\u00020\u0005H\u0083@ø\u0001\u0000¢\u0006\u0002\u0010)\u001a\f\u0010*\u001a\u00020\u0010*\u00020\u0010H\u0002\u001a\u0014\u0010+\u001a\u00020\u0010*\u00020\u00102\u0006\u0010#\u001a\u00020$H\u0002\u001a\u001a\u0010,\u001a\u0002H-\"\u0006\b\u0000\u0010-\u0018\u0001*\u00020\u0010H\u0082\b¢\u0006\u0002\u0010.\u001a\u0014\u0010/\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\tH\u0002\u001a\u001a\u00100\u001a\u00020\u0005*\b\u0012\u0004\u0012\u0002010\b2\u0006\u00102\u001a\u00020\tH\u0002\u001a \u00100\u001a\u00020\u0005*\b\u0012\u0004\u0012\u0002010\b2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0018\u0010\u000f\u001a\u00020\u0005*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"AUTO_REVOKE_ENABLED", "", "DEBUG", "DEBUG_OVERRIDE_THRESHOLDS", "DEFAULT_CHECK_FREQUENCY_MS", "", "DEFAULT_UNUSED_THRESHOLD_MS", "EXEMPT_PERMISSIONS", "", "", "LOG_TAG", "PREF_KEY_FIRST_BOOT_TIME", "SERVER_LOG_ID", "", "SKIP_NEXT_RUN", "firstBootTime", "Landroid/content/Context;", "getFirstBootTime", "(Landroid/content/Context;)J", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "(Landroid/content/Context;)Landroid/content/SharedPreferences;", "dumpAutoRevokePermissions", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$AutoRevokePermissionsDumpProto;", "context", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCheckFrequencyMs", "getUnusedThresholdMs", "isAutoRevokeEnabled", "isPackageAutoRevokeExempt", "pkg", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "(Landroid/content/Context;Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPackageAutoRevokePermanentlyExempt", "user", "Landroid/os/UserHandle;", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;Landroid/os/UserHandle;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "revokePermissionsOnUnusedApps", "Lkotlin/Pair;", "sessionId", "(Landroid/content/Context;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forParentUser", "forUser", "getSystemService", "T", "(Landroid/content/Context;)Ljava/lang/Object;", "isPackageCrossProfile", "lastTimeVisible", "Landroid/app/usage/UsageStats;", "pkgName", "pkgNames", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 2, mv = {1, 1, 16})
public final class AutoRevokePermissions {
    private static final boolean AUTO_REVOKE_ENABLED = true;
    private static final boolean DEBUG = true;
    private static final boolean DEBUG_OVERRIDE_THRESHOLDS = false;
    private static final String LOG_TAG = "AutoRevokePermissions";
    private static boolean SKIP_NEXT_RUN;
    private static final List<String> EXEMPT_PERMISSIONS = CollectionsKt.listOf("android.permission.ACTIVITY_RECOGNITION");
    private static final long DEFAULT_UNUSED_THRESHOLD_MS = TimeUnit.DAYS.toMillis(90);
    private static final long DEFAULT_CHECK_FREQUENCY_MS = TimeUnit.DAYS.toMillis(15);
    private static final int SERVER_LOG_ID = 16;
    private static final String PREF_KEY_FIRST_BOOT_TIME = "first_boot_time";

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.AutoRevokePermissions$dumpAutoRevokePermissions$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@"}, d2 = {"dumpAutoRevokePermissions", "", "context", "Landroid/content/Context;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$AutoRevokePermissionsDumpProto;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions", f = "AutoRevokePermissions.kt", i = {0, 0, 0, 1, 1, 1}, l = {182, 183}, m = "dumpAutoRevokePermissions", n = {"context", "teamFoodSettings", "dumpData", "context", "teamFoodSettings", "dumpData"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AutoRevokePermissions.dumpAutoRevokePermissions(null, this);
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.AutoRevokePermissions$isPackageAutoRevokeExempt$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086@"}, d2 = {"isPackageAutoRevokeExempt", "", "context", "Landroid/content/Context;", "pkg", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions", f = "AutoRevokePermissions.kt", i = {0, 0, 0, 0}, l = {494}, m = "isPackageAutoRevokeExempt", n = {"context", "pkg", "packageName", "packageUid"}, s = {"L$0", "L$1", "L$2", "I$0"})
    static final class C00461 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00461(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AutoRevokePermissions.isPackageAutoRevokeExempt(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.AutoRevokePermissions$isPackageAutoRevokePermanentlyExempt$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086@"}, d2 = {"isPackageAutoRevokePermanentlyExempt", "", "pkg", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "user", "Landroid/os/UserHandle;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions", f = "AutoRevokePermissions.kt", i = {0, 0, 1, 1}, l = {455, 467}, m = "isPackageAutoRevokePermanentlyExempt", n = {"pkg", "user", "pkg", "user"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C00471 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00471(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AutoRevokePermissions.isPackageAutoRevokePermanentlyExempt(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.AutoRevokePermissions$revokePermissionsOnUnusedApps$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001e\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\b0\u0007H\u0083@"}, d2 = {"revokePermissionsOnUnusedApps", "", "context", "Landroid/content/Context;", "sessionId", "", "continuation", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions", f = "AutoRevokePermissions.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {246, 252, 1084}, m = "revokePermissionsOnUnusedApps", n = {"context", "sessionId", "now", "firstBootTime", "context", "sessionId", "now", "firstBootTime", "allPackagesByUser", "allPackagesByUserByUid", "unusedApps", "context", "sessionId", "now", "firstBootTime", "allPackagesByUser", "allPackagesByUserByUid", "unusedApps", "userStats", "revokedApps", "userManager", "user", "userApps", "$this$forEachInParallel$iv", "context$iv", "scope$iv", "$this$mapInParallel$iv$iv", "$this$map$iv$iv$iv", "$this$mapTo$iv$iv$iv$iv", "destination$iv$iv$iv$iv", "item$iv$iv$iv$iv", "it$iv$iv"}, s = {"L$0", "J$0", "J$1", "J$2", "L$0", "J$0", "J$1", "J$2", "L$1", "L$2", "L$3", "L$0", "J$0", "J$1", "J$2", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19"})
    static final class C00481 extends ContinuationImpl {
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$20;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C00481(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AutoRevokePermissions.revokePermissionsOnUnusedApps(null, 0L, this);
        }
    }

    public static final long getUnusedThresholdMs(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (TeamfoodSettings.Companion.get(context) == null) {
            return DeviceConfig.getLong("permissions", Utils.PROPERTY_AUTO_REVOKE_UNUSED_THRESHOLD_MILLIS, DEFAULT_UNUSED_THRESHOLD_MS);
        }
        TeamfoodSettings teamfoodSettings = TeamfoodSettings.Companion.get(context);
        if (teamfoodSettings == null) {
            Intrinsics.throwNpe();
        }
        return teamfoodSettings.getUnusedThresholdMs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getCheckFrequencyMs(Context context) {
        if (TeamfoodSettings.Companion.get(context) == null) {
            return DeviceConfig.getLong("permissions", Utils.PROPERTY_AUTO_REVOKE_CHECK_FREQUENCY_MILLIS, DEFAULT_CHECK_FREQUENCY_MS);
        }
        TeamfoodSettings teamfoodSettings = TeamfoodSettings.Companion.get(context);
        if (teamfoodSettings == null) {
            Intrinsics.throwNpe();
        }
        return teamfoodSettings.getCheckFrequencyMs();
    }

    public static final boolean isAutoRevokeEnabled(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return getCheckFrequencyMs(context) > 0 && getUnusedThresholdMs(context) > 0 && getUnusedThresholdMs(context) != LongCompanionObject.MAX_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object dumpAutoRevokePermissions(android.content.Context r17, kotlin.coroutines.Continuation<? super com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto> r18) {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.AutoRevokePermissions.dumpAutoRevokePermissions(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x06cf  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x076e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:119:0x067e -> B:120:0x06c9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:125:0x0734 -> B:126:0x0755). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final /* synthetic */ java.lang.Object revokePermissionsOnUnusedApps(android.content.Context r52, long r53, kotlin.coroutines.Continuation<? super java.util.List<kotlin.Pair<java.lang.String, android.os.UserHandle>>> r55) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.AutoRevokePermissions.revokePermissionsOnUnusedApps(android.content.Context, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object revokePermissionsOnUnusedApps$default(Context context, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return revokePermissionsOnUnusedApps(context, j, continuation);
    }

    private static final long lastTimeVisible(List<UsageStats> list, List<String> list2) {
        long result = 0;
        for (UsageStats stat : list) {
            if (list2.contains(stat.getPackageName())) {
                result = Math.max(result, stat.getLastTimeVisible());
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long lastTimeVisible(List<UsageStats> list, String pkgName) {
        return lastTimeVisible(list, (List<String>) CollectionsKt.listOf(pkgName));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object isPackageAutoRevokePermanentlyExempt(com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r28, android.os.UserHandle r29, kotlin.coroutines.Continuation<? super java.lang.Boolean> r30) {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.AutoRevokePermissions.isPackageAutoRevokePermanentlyExempt(com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo, android.os.UserHandle, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object isPackageAutoRevokeExempt(android.content.Context r13, com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r14, kotlin.coroutines.Continuation<? super java.lang.Boolean> r15) {
        /*
            boolean r0 = r15 instanceof com.android.permissioncontroller.permission.service.AutoRevokePermissions.C00461
            if (r0 == 0) goto L14
            r0 = r15
            com.android.permissioncontroller.permission.service.AutoRevokePermissions$isPackageAutoRevokeExempt$1 r0 = (com.android.permissioncontroller.permission.service.AutoRevokePermissions.C00461) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.android.permissioncontroller.permission.service.AutoRevokePermissions$isPackageAutoRevokeExempt$1 r0 = new com.android.permissioncontroller.permission.service.AutoRevokePermissions$isPackageAutoRevokeExempt$1
            r0.<init>(r15)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r9 = 0
            r10 = 1
            if (r1 == 0) goto L48
            if (r1 != r10) goto L40
            r1 = r9
            r2 = 0
            int r1 = r0.I$0
            java.lang.Object r3 = r0.L$2
            r2 = r3
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r0.L$1
            r14 = r3
            com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r14 = (com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo) r14
            java.lang.Object r3 = r0.L$0
            r13 = r3
            android.content.Context r13 = (android.content.Context) r13
            kotlin.ResultKt.throwOnFailure(r7)
            r12 = r1
            r1 = r7
            goto L7d
        L40:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L48:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r11 = r14.getPackageName()
            int r12 = r14.getUid()
            com.android.permissioncontroller.permission.data.AppOpLiveData$Companion r1 = com.android.permissioncontroller.permission.data.AppOpLiveData.INSTANCE
            com.android.permissioncontroller.permission.data.DataRepository r1 = (com.android.permissioncontroller.permission.data.DataRepository) r1
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            java.lang.String r3 = "android:auto_revoke_permissions_if_unused"
            com.android.permissioncontroller.permission.data.DataRepository$InactiveTimekeeper r1 = com.android.permissioncontroller.permission.data.DataRepositoryKt.get(r1, r11, r3, r2)
            com.android.permissioncontroller.permission.data.AppOpLiveData r1 = (com.android.permissioncontroller.permission.data.AppOpLiveData) r1
            r2 = 0
            r3 = 0
            r5 = 3
            r6 = 0
            r0.L$0 = r13
            r0.L$1 = r14
            r0.L$2 = r11
            r0.I$0 = r12
            r0.label = r10
            r4 = r0
            java.lang.Object r1 = com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData.getInitializedValue$default(r1, r2, r3, r4, r5, r6)
            if (r1 != r8) goto L7c
            return r8
        L7c:
            r2 = r11
        L7d:
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            r3 = 3
            if (r1 != r3) goto La4
        L88:
            int r3 = r14.getTargetSdkVersion()
            r4 = 29
            if (r3 > r4) goto L9f
            com.android.permissioncontroller.permission.service.TeamfoodSettings$Companion r3 = com.android.permissioncontroller.permission.service.TeamfoodSettings.Companion
            com.android.permissioncontroller.permission.service.TeamfoodSettings r3 = r3.get(r13)
            if (r3 == 0) goto L9e
            boolean r3 = r3.getEnabledForPreRApps()
            if (r3 == r10) goto L9f
        L9e:
            r9 = r10
        L9f:
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            return r3
        La4:
            if (r1 == 0) goto La7
            r9 = r10
        La7:
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.AutoRevokePermissions.isPackageAutoRevokeExempt(android.content.Context, com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean isPackageCrossProfile(Context $this$isPackageCrossProfile, String pkg) {
        return $this$isPackageCrossProfile.getPackageManager().checkPermission("android.permission.INTERACT_ACROSS_PROFILES", pkg) == 0 || $this$isPackageCrossProfile.getPackageManager().checkPermission("android.permission.INTERACT_ACROSS_USERS", pkg) == 0 || $this$isPackageCrossProfile.getPackageManager().checkPermission("android.permission.INTERACT_ACROSS_USERS_FULL", pkg) == 0;
    }

    private static final Context forUser(Context $this$forUser, UserHandle user) throws PackageManager.NameNotFoundException {
        Context userContext = Utils.getUserContext(AndroidUtilsKt.getApplication($this$forUser), user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(application, user)");
        return userContext;
    }

    private static final Context forParentUser(Context $this$forParentUser) {
        Context parentUserContext = Utils.getParentUserContext($this$forParentUser);
        Intrinsics.checkExpressionValueIsNotNull(parentUserContext, "Utils.getParentUserContext(this)");
        return parentUserContext;
    }

    private static final /* synthetic */ <T> T getSystemService(Context context) {
        Intrinsics.reifiedOperationMarker(4, "T");
        T t = (T) context.getSystemService(Object.class);
        if (t == null) {
            Intrinsics.throwNpe();
        }
        return t;
    }

    public static final SharedPreferences getSharedPreferences(Context sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "$this$sharedPreferences");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(sharedPreferences);
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…ltSharedPreferences(this)");
        return defaultSharedPreferences;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getFirstBootTime(Context $this$firstBootTime) {
        long time = getSharedPreferences($this$firstBootTime).getLong(PREF_KEY_FIRST_BOOT_TIME, -1L);
        if (time > 0) {
            return time;
        }
        long time2 = System.currentTimeMillis();
        getSharedPreferences($this$firstBootTime).edit().putLong(PREF_KEY_FIRST_BOOT_TIME, time2).apply();
        return time2;
    }
}
