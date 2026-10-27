package com.android.permissioncontroller.permission.ui.model;

import android.app.Application;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.UserHandle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.permission.data.AllPackageInfosLiveData;
import com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData;
import com.android.permissioncontroller.permission.data.UnusedAutoRevokedPackagesLiveData;
import com.android.permissioncontroller.permission.data.UsageStatsLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: AutoRevokeViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 !2\u00020\u0001:\u0003!\"#B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J&\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0010J\u001e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0005J\u001e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel;", "Landroidx/lifecycle/ViewModel;", "app", "Landroid/app/Application;", "sessionId", "", "(Landroid/app/Application;J)V", "autoRevokedPackageCategoriesLiveData", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$Months;", "", "Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$RevokedPackageInfo;", "getAutoRevokedPackageCategoriesLiveData", "()Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "areAutoRevokedPackagesLoaded", "", "disableApp", "", "packageName", "", "user", "Landroid/os/UserHandle;", "logAppInteraction", "action", "", "logAppView", "groupName", "isNew", "navigateToAppInfo", "requestUninstallApp", "fragment", "Landroidx/fragment/app/Fragment;", "Companion", "Months", "RevokedPackageInfo", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokeViewModel extends ViewModel {
    private final Application app;
    private final SmartAsyncMediatorLiveData<Map<Months, List<RevokedPackageInfo>>> autoRevokedPackageCategoriesLiveData;
    private final long sessionId;
    private static final long SIX_MONTHS_MILLIS = TimeUnit.DAYS.toMillis(180);
    private static final String LOG_TAG = AppPermissionViewModel.class.getSimpleName();

    public AutoRevokeViewModel(Application app, long sessionId) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        this.app = app;
        this.sessionId = sessionId;
        this.autoRevokedPackageCategoriesLiveData = (SmartAsyncMediatorLiveData) new SmartAsyncMediatorLiveData<Map<Months, ? extends List<? extends RevokedPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$autoRevokedPackageCategoriesLiveData$1
            private final UsageStatsLiveData usageStatsLiveData = UsageStatsLiveData.Companion.get(AutoRevokeViewModel.SIX_MONTHS_MILLIS);

            {
                addSource(UnusedAutoRevokedPackagesLiveData.INSTANCE, new Observer<Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>>>() { // from class: com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$autoRevokedPackageCategoriesLiveData$1.1
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>> map) {
                        onChanged2((Map<Pair<String, UserHandle>, ? extends Set<String>>) map);
                    }

                    /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                    public final void onChanged2(Map<Pair<String, UserHandle>, ? extends Set<String>> map) {
                        onUpdate();
                    }
                });
                addSource(AllPackageInfosLiveData.INSTANCE, new Observer<Map<UserHandle, ? extends List<? extends LightPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$autoRevokedPackageCategoriesLiveData$1.2
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends LightPackageInfo>> map) {
                        onChanged2((Map<UserHandle, ? extends List<LightPackageInfo>>) map);
                    }

                    /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                    public final void onChanged2(Map<UserHandle, ? extends List<LightPackageInfo>> map) {
                        onUpdate();
                    }
                });
                addSource(this.usageStatsLiveData, new Observer<Map<UserHandle, ? extends List<? extends UsageStats>>>() { // from class: com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$autoRevokedPackageCategoriesLiveData$1.3
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends UsageStats>> map) {
                        onChanged2((Map<UserHandle, ? extends List<UsageStats>>) map);
                    }

                    /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                    public final void onChanged2(Map<UserHandle, ? extends List<UsageStats>> map) {
                        onUpdate();
                    }
                });
            }

            @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
            public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) throws PackageManager.NameNotFoundException {
                AutoRevokeViewModel.Months months;
                if (!UnusedAutoRevokedPackagesLiveData.INSTANCE.getIsInitialized() || !this.usageStatsLiveData.getIsInitialized() || !AllPackageInfosLiveData.INSTANCE.getIsInitialized()) {
                    return Unit.INSTANCE;
                }
                Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>> value = UnusedAutoRevokedPackagesLiveData.INSTANCE.getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, "UnusedAutoRevokedPackagesLiveData.value!!");
                Set<Pair> mutableSet = CollectionsKt.toMutableSet(value.keySet());
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(AutoRevokeViewModel.Months.THREE, new ArrayList());
                linkedHashMap.put(AutoRevokeViewModel.Months.SIX, new ArrayList());
                ArrayList arrayList = new ArrayList();
                Map<UserHandle, ? extends List<? extends LightPackageInfo>> value2 = AllPackageInfosLiveData.INSTANCE.getValue();
                if (value2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value2, "AllPackageInfosLiveData.value!!");
                for (Map.Entry<UserHandle, ? extends List<? extends LightPackageInfo>> entry : value2.entrySet()) {
                    UserHandle key = entry.getKey();
                    List<? extends LightPackageInfo> value3 = entry.getValue();
                    ArrayList arrayList2 = new ArrayList();
                    for (LightPackageInfo lightPackageInfo : value3) {
                        Pair pair = TuplesKt.to(lightPackageInfo.getPackageName(), key);
                        if (!value.containsKey(pair) || (lightPackageInfo.getAppFlags() & 1) == 0) {
                            pair = null;
                        }
                        if (pair != null) {
                            arrayList2.add(pair);
                        }
                    }
                    arrayList.addAll(arrayList2);
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                Map<UserHandle, ? extends List<? extends UsageStats>> value4 = this.usageStatsLiveData.getValue();
                if (value4 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value4, "usageStatsLiveData.value!!");
                for (Map.Entry<UserHandle, ? extends List<? extends UsageStats>> entry2 : value4.entrySet()) {
                    UserHandle key2 = entry2.getKey();
                    for (UsageStats usageStats : entry2.getValue()) {
                        Pair pair2 = TuplesKt.to(usageStats.getPackageName(), key2);
                        if (value.containsKey(pair2)) {
                            Object obj = linkedHashMap.get(AutoRevokeViewModel.Months.THREE);
                            if (obj == null) {
                                Intrinsics.throwNpe();
                            }
                            List list = (List) obj;
                            String packageName = usageStats.getPackageName();
                            Intrinsics.checkExpressionValueIsNotNull(packageName, "stat.packageName");
                            boolean zContains = arrayList.contains(pair2);
                            Set<? extends String> set = value.get(pair2);
                            if (set == null) {
                                Intrinsics.throwNpe();
                            }
                            list.add(new AutoRevokeViewModel.RevokedPackageInfo(packageName, key2, zContains, set));
                            mutableSet.remove(pair2);
                        }
                    }
                }
                for (Pair pair3 : mutableSet) {
                    String str = (String) pair3.component1();
                    UserHandle userHandle = (UserHandle) pair3.component2();
                    long firstInstallTime = 0;
                    Map<UserHandle, ? extends List<? extends LightPackageInfo>> value5 = AllPackageInfosLiveData.INSTANCE.getValue();
                    if (value5 == null) {
                        Intrinsics.throwNpe();
                    }
                    List<? extends LightPackageInfo> list2 = value5.get(userHandle);
                    if (list2 == null) {
                        Intrinsics.throwNpe();
                    }
                    for (LightPackageInfo lightPackageInfo2 : list2) {
                        if (Intrinsics.areEqual(lightPackageInfo2.getPackageName(), str)) {
                            firstInstallTime = lightPackageInfo2.getFirstInstallTime();
                        }
                    }
                    if (jCurrentTimeMillis - firstInstallTime <= AutoRevokeViewModel.SIX_MONTHS_MILLIS) {
                        months = AutoRevokeViewModel.Months.THREE;
                    } else {
                        months = AutoRevokeViewModel.Months.SIX;
                    }
                    Context userContext = Utils.getUserContext(this.this$0.app, userHandle);
                    Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
                    userContext.getPackageManager().getLaunchIntentForPackage(str);
                    Pair pair4 = TuplesKt.to(str, userHandle);
                    Object obj2 = linkedHashMap.get(months);
                    if (obj2 == null) {
                        Intrinsics.throwNpe();
                    }
                    List list3 = (List) obj2;
                    boolean zContains2 = arrayList.contains(pair4);
                    Set<? extends String> set2 = value.get(pair4);
                    if (set2 == null) {
                        Intrinsics.throwNpe();
                    }
                    list3.add(new AutoRevokeViewModel.RevokedPackageInfo(str, userHandle, zContains2, set2));
                }
                postValue(linkedHashMap);
                return Unit.INSTANCE;
            }
        };
    }

    /* JADX INFO: compiled from: AutoRevokeViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$Months;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "THREE", "SIX", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public enum Months {
        THREE("three_months"),
        SIX("six_months");


        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String value;

        @JvmStatic
        public static final List<Months> allMonths() {
            return INSTANCE.allMonths();
        }

        Months(String value) {
            this.value = value;
        }

        public final String getValue() {
            return this.value;
        }

        /* JADX INFO: compiled from: AutoRevokeViewModel.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¨\u0006\u0006"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$Months$Companion;", "", "()V", "allMonths", "", "Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$Months;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            @JvmStatic
            public final List<Months> allMonths() {
                return CollectionsKt.listOf((Object[]) new Months[]{Months.THREE, Months.SIX});
            }
        }
    }

    /* JADX INFO: compiled from: AutoRevokeViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$RevokedPackageInfo;", "", "packageName", "", "user", "Landroid/os/UserHandle;", "shouldDisable", "", "revokedGroups", "", "(Ljava/lang/String;Landroid/os/UserHandle;ZLjava/util/Set;)V", "getPackageName", "()Ljava/lang/String;", "getRevokedGroups", "()Ljava/util/Set;", "getShouldDisable", "()Z", "getUser", "()Landroid/os/UserHandle;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class RevokedPackageInfo {
        private final String packageName;
        private final Set<String> revokedGroups;
        private final boolean shouldDisable;
        private final UserHandle user;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ RevokedPackageInfo copy$default(RevokedPackageInfo revokedPackageInfo, String str, UserHandle userHandle, boolean z, Set set, int i, Object obj) {
            if ((i & 1) != 0) {
                str = revokedPackageInfo.packageName;
            }
            if ((i & 2) != 0) {
                userHandle = revokedPackageInfo.user;
            }
            if ((i & 4) != 0) {
                z = revokedPackageInfo.shouldDisable;
            }
            if ((i & 8) != 0) {
                set = revokedPackageInfo.revokedGroups;
            }
            return revokedPackageInfo.copy(str, userHandle, z, set);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final UserHandle getUser() {
            return this.user;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getShouldDisable() {
            return this.shouldDisable;
        }

        public final Set<String> component4() {
            return this.revokedGroups;
        }

        public final RevokedPackageInfo copy(String packageName, UserHandle user, boolean shouldDisable, Set<String> revokedGroups) {
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(user, "user");
            Intrinsics.checkParameterIsNotNull(revokedGroups, "revokedGroups");
            return new RevokedPackageInfo(packageName, user, shouldDisable, revokedGroups);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RevokedPackageInfo)) {
                return false;
            }
            RevokedPackageInfo revokedPackageInfo = (RevokedPackageInfo) other;
            return Intrinsics.areEqual(this.packageName, revokedPackageInfo.packageName) && Intrinsics.areEqual(this.user, revokedPackageInfo.user) && this.shouldDisable == revokedPackageInfo.shouldDisable && Intrinsics.areEqual(this.revokedGroups, revokedPackageInfo.revokedGroups);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v4, types: [int] */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v8 */
        public int hashCode() {
            String str = this.packageName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            UserHandle userHandle = this.user;
            int iHashCode2 = (iHashCode + (userHandle != null ? userHandle.hashCode() : 0)) * 31;
            boolean z = this.shouldDisable;
            ?? r2 = z;
            if (z) {
                r2 = 1;
            }
            int i = (iHashCode2 + r2) * 31;
            Set<String> set = this.revokedGroups;
            return i + (set != null ? set.hashCode() : 0);
        }

        public String toString() {
            return "RevokedPackageInfo(packageName=" + this.packageName + ", user=" + this.user + ", shouldDisable=" + this.shouldDisable + ", revokedGroups=" + this.revokedGroups + ")";
        }

        public RevokedPackageInfo(String packageName, UserHandle user, boolean shouldDisable, Set<String> revokedGroups) {
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(user, "user");
            Intrinsics.checkParameterIsNotNull(revokedGroups, "revokedGroups");
            this.packageName = packageName;
            this.user = user;
            this.shouldDisable = shouldDisable;
            this.revokedGroups = revokedGroups;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final UserHandle getUser() {
            return this.user;
        }

        public final boolean getShouldDisable() {
            return this.shouldDisable;
        }

        public final Set<String> getRevokedGroups() {
            return this.revokedGroups;
        }
    }

    public final SmartAsyncMediatorLiveData<Map<Months, List<RevokedPackageInfo>>> getAutoRevokedPackageCategoriesLiveData() {
        return this.autoRevokedPackageCategoriesLiveData;
    }

    public final boolean areAutoRevokedPackagesLoaded() {
        return UnusedAutoRevokedPackagesLiveData.INSTANCE.getIsInitialized();
    }

    public final void navigateToAppInfo(String packageName, UserHandle user, long sessionId) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Context userContext = Utils.getUserContext(this.app, user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        Uri packageUri = Uri.parse("package:" + packageName);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", packageUri);
        intent.putExtra("android.intent.action.AUTO_REVOKE_PERMISSIONS", sessionId);
        intent.setFlags(268435456);
        userContext.startActivityAsUser(intent, user);
    }

    public final void requestUninstallApp(Fragment fragment, String packageName, UserHandle user) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Log.i(LOG_TAG, "sessionId: " + this.sessionId + ", Requesting uninstall of " + packageName + ", " + user);
        logAppInteraction(packageName, user, 1);
        StringBuilder sb = new StringBuilder();
        sb.append("package:");
        sb.append(packageName);
        Uri packageUri = Uri.parse(sb.toString());
        Intent uninstallIntent = new Intent("android.intent.action.UNINSTALL_PACKAGE", packageUri);
        uninstallIntent.putExtra("android.intent.extra.USER", user);
        fragment.startActivity(uninstallIntent);
    }

    public final void disableApp(String packageName, UserHandle user) throws PackageManager.NameNotFoundException {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Log.i(LOG_TAG, "sessionId: " + this.sessionId + ", Disabling " + packageName + ", " + user);
        logAppInteraction(packageName, user, 1);
        Context userContext = Utils.getUserContext(this.app, user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        userContext.getPackageManager().setApplicationEnabledSetting(packageName, 3, 0);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$logAppInteraction$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoRevokeViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$logAppInteraction$1", f = "AutoRevokeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $action;
        final /* synthetic */ String $packageName;
        final /* synthetic */ UserHandle $user;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(UserHandle userHandle, String str, int i, Continuation continuation) {
            super(2, continuation);
            this.$user = userHandle;
            this.$packageName = str;
            this.$action = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = AutoRevokeViewModel.this.new AnonymousClass1(this.$user, this.$packageName, this.$action, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            List<? extends LightPackageInfo> list;
            Object next;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            Map<UserHandle, ? extends List<? extends LightPackageInfo>> value = AllPackageInfosLiveData.INSTANCE.getValue();
            Integer numBoxInt = null;
            if (value != null && (list = value.get(this.$user)) != null) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    LightPackageInfo info = (LightPackageInfo) next;
                    if (Boxing.boxBoolean(Intrinsics.areEqual(info.getPackageName(), this.$packageName)).booleanValue()) {
                        break;
                    }
                }
                LightPackageInfo lightPackageInfo = (LightPackageInfo) next;
                if (lightPackageInfo != null) {
                    numBoxInt = Boxing.boxInt(lightPackageInfo.getUid());
                }
            }
            Integer uid = numBoxInt;
            if (uid != null) {
                PermissionControllerStatsLog.write(PermissionControllerStatsLog.AUTO_REVOKED_APP_INTERACTION, AutoRevokeViewModel.this.sessionId, uid.intValue(), this.$packageName, this.$action);
            }
            return Unit.INSTANCE;
        }
    }

    private final void logAppInteraction(String packageName, UserHandle user, int action) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new AnonymousClass1(user, packageName, action, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$logAppView$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoRevokeViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel$logAppView$1", f = "AutoRevokeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ boolean $isNew;
        final /* synthetic */ String $packageName;
        final /* synthetic */ UserHandle $user;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00571(UserHandle userHandle, String str, boolean z, String str2, Continuation continuation) {
            super(2, continuation);
            this.$user = userHandle;
            this.$packageName = str;
            this.$isNew = z;
            this.$groupName = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00571 c00571 = AutoRevokeViewModel.this.new C00571(this.$user, this.$packageName, this.$isNew, this.$groupName, completion);
            c00571.p$ = (CoroutineScope) obj;
            return c00571;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object next;
            int bucket;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            Map<UserHandle, ? extends List<? extends LightPackageInfo>> value = AllPackageInfosLiveData.INSTANCE.getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            List<? extends LightPackageInfo> list = value.get(this.$user);
            if (list == null) {
                Intrinsics.throwNpe();
            }
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                LightPackageInfo info = (LightPackageInfo) next;
                if (Boxing.boxBoolean(Intrinsics.areEqual(info.getPackageName(), this.$packageName)).booleanValue()) {
                    break;
                }
            }
            LightPackageInfo lightPackageInfo = (LightPackageInfo) next;
            Integer uid = lightPackageInfo != null ? Boxing.boxInt(lightPackageInfo.getUid()) : null;
            if (uid != null) {
                if (this.$isNew) {
                    bucket = 1;
                } else {
                    bucket = 2;
                }
                PermissionControllerStatsLog.write(PermissionControllerStatsLog.AUTO_REVOKE_FRAGMENT_APP_VIEWED, AutoRevokeViewModel.this.sessionId, uid.intValue(), this.$packageName, this.$groupName, bucket);
            }
            return Unit.INSTANCE;
        }
    }

    public final void logAppView(String packageName, UserHandle user, String groupName, boolean isNew) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new C00571(user, packageName, isNew, groupName, null), 2, null);
    }
}
