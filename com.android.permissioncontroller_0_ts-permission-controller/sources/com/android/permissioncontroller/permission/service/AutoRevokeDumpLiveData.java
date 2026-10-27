package com.android.permissioncontroller.permission.service;

import android.app.usage.UsageStats;
import android.content.Context;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.AllPackageInfosLiveData;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData;
import com.android.permissioncontroller.permission.data.PackagePermissionsLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.data.UnusedAutoRevokedPackagesLiveData;
import com.android.permissioncontroller.permission.data.UsageStatsLiveData;
import com.android.permissioncontroller.permission.data.UsersLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoRevokePermissions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u001c\u001d\u001e\u001fB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001a\u001a\u00020\u001bH\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\n\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R4\u0010\u0010\u001a(\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpData;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isUpdating", "", "packages", "Lcom/android/permissioncontroller/permission/data/AllPackageInfosLiveData;", "pkgPermGroupNames", "", "Lkotlin/Pair;", "Landroid/os/UserHandle;", "", "Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData;", "pkgPermGroups", "Lcom/android/permissioncontroller/permission/data/LightAppPermGroupLiveData;", "revokedPermGroupNames", "Lcom/android/permissioncontroller/permission/data/UnusedAutoRevokedPackagesLiveData;", "services", "Lcom/android/permissioncontroller/permission/service/ExemptServicesLiveData;", "usages", "Lcom/android/permissioncontroller/permission/data/UsageStatsLiveData;", "users", "Lcom/android/permissioncontroller/permission/data/UsersLiveData;", "onUpdate", "", "AutoRevokeDumpData", "AutoRevokeDumpGroupData", "AutoRevokeDumpPackageData", "AutoRevokeDumpUserData", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
final class AutoRevokeDumpLiveData extends SmartUpdateMediatorLiveData<AutoRevokeDumpData> {
    private boolean isUpdating;
    private final AllPackageInfosLiveData packages;
    private Map<Pair<UserHandle, String>, PackagePermissionsLiveData> pkgPermGroupNames;
    private final Map<Pair<UserHandle, String>, Map<String, LightAppPermGroupLiveData>> pkgPermGroups;
    private final UnusedAutoRevokedPackagesLiveData revokedPermGroupNames;
    private Map<UserHandle, ExemptServicesLiveData> services;
    private final UsageStatsLiveData usages;
    private final UsersLiveData users;

    public AutoRevokeDumpLiveData(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.users = UsersLiveData.INSTANCE;
        this.usages = (UsageStatsLiveData) DataRepositoryKt.get(UsageStatsLiveData.INSTANCE, Long.valueOf(AutoRevokePermissions.getUnusedThresholdMs(context)), 2);
        this.packages = AllPackageInfosLiveData.INSTANCE;
        this.revokedPermGroupNames = UnusedAutoRevokedPackagesLiveData.INSTANCE;
        this.pkgPermGroups = new LinkedHashMap();
        addSource(this.revokedPermGroupNames, new Observer<Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>>>() { // from class: com.android.permissioncontroller.permission.service.AutoRevokeDumpLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>> map) {
                onChanged2((Map<Pair<String, UserHandle>, ? extends Set<String>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<Pair<String, UserHandle>, ? extends Set<String>> map) {
                AutoRevokeDumpLiveData.this.updateIfActive();
            }
        });
        addSource(this.users, new Observer<List<? extends UserHandle>>() { // from class: com.android.permissioncontroller.permission.service.AutoRevokeDumpLiveData.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends UserHandle> list) {
                onChanged2((List<UserHandle>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<UserHandle> list) {
                Iterable iterableValues;
                Map map = AutoRevokeDumpLiveData.this.services;
                if (map != null && (iterableValues = map.values()) != null) {
                    Iterable $this$forEach$iv = iterableValues;
                    for (Object element$iv : $this$forEach$iv) {
                        ExemptServicesLiveData it = (ExemptServicesLiveData) element$iv;
                        AutoRevokeDumpLiveData.this.removeSource(it);
                    }
                }
                AutoRevokeDumpLiveData.this.services = (Map) null;
                AutoRevokeDumpLiveData.this.updateIfActive();
            }
        });
        addSource(this.usages, new Observer<Map<UserHandle, ? extends List<? extends UsageStats>>>() { // from class: com.android.permissioncontroller.permission.service.AutoRevokeDumpLiveData.3
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends UsageStats>> map) {
                onChanged2((Map<UserHandle, ? extends List<UsageStats>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<UserHandle, ? extends List<UsageStats>> map) {
                AutoRevokeDumpLiveData.this.updateIfActive();
            }
        });
        addSource(this.packages, new Observer<Map<UserHandle, ? extends List<? extends LightPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.service.AutoRevokeDumpLiveData.4
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends LightPackageInfo>> map) {
                onChanged2((Map<UserHandle, ? extends List<LightPackageInfo>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<UserHandle, ? extends List<LightPackageInfo>> map) {
                Iterable iterableValues;
                Iterable iterableValues2;
                Map map2 = AutoRevokeDumpLiveData.this.pkgPermGroupNames;
                if (map2 != null && (iterableValues2 = map2.values()) != null) {
                    Iterable $this$forEach$iv = iterableValues2;
                    for (Object element$iv : $this$forEach$iv) {
                        AutoRevokeDumpLiveData.this.removeSource((PackagePermissionsLiveData) element$iv);
                    }
                }
                AutoRevokeDumpLiveData.this.pkgPermGroupNames = (Map) null;
                Iterable $this$forEach$iv2 = AutoRevokeDumpLiveData.this.pkgPermGroups.values();
                for (Object element$iv2 : $this$forEach$iv2) {
                    Map it = (Map) element$iv2;
                    if (it != null && (iterableValues = it.values()) != null) {
                        Iterable $this$forEach$iv3 = iterableValues;
                        for (Object element$iv3 : $this$forEach$iv3) {
                            AutoRevokeDumpLiveData.this.removeSource((LightAppPermGroupLiveData) element$iv3);
                        }
                    }
                }
                AutoRevokeDumpLiveData.this.updateIfActive();
            }
        });
    }

    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpData;", "", "users", "", "Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpUserData;", "(Ljava/util/List;)V", "getUsers", "()Ljava/util/List;", "component1", "copy", "dumpUsers", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$PerUserProto;", "equals", "", "other", "hashCode", "", "toString", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class AutoRevokeDumpData {
        private final List<AutoRevokeDumpUserData> users;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AutoRevokeDumpData copy$default(AutoRevokeDumpData autoRevokeDumpData, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = autoRevokeDumpData.users;
            }
            return autoRevokeDumpData.copy(list);
        }

        public final List<AutoRevokeDumpUserData> component1() {
            return this.users;
        }

        public final AutoRevokeDumpData copy(List<AutoRevokeDumpUserData> users) {
            Intrinsics.checkParameterIsNotNull(users, "users");
            return new AutoRevokeDumpData(users);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof AutoRevokeDumpData) && Intrinsics.areEqual(this.users, ((AutoRevokeDumpData) other).users);
            }
            return true;
        }

        public int hashCode() {
            List<AutoRevokeDumpUserData> list = this.users;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "AutoRevokeDumpData(users=" + this.users + ")";
        }

        public AutoRevokeDumpData(List<AutoRevokeDumpUserData> users) {
            Intrinsics.checkParameterIsNotNull(users, "users");
            this.users = users;
        }

        public final List<AutoRevokeDumpUserData> getUsers() {
            return this.users;
        }

        public final List<AutoRevokePermissionsProto.PerUserProto> dumpUsers() {
            Iterable $this$map$iv = this.users;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                AutoRevokeDumpUserData it = (AutoRevokeDumpUserData) item$iv$iv;
                destination$iv$iv.add(it.dump());
            }
            return (List) destination$iv$iv;
        }
    }

    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpUserData;", "", "user", "Landroid/os/UserHandle;", "pkgs", "", "Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpPackageData;", "(Landroid/os/UserHandle;Ljava/util/List;)V", "getPkgs", "()Ljava/util/List;", "getUser", "()Landroid/os/UserHandle;", "component1", "component2", "copy", "dump", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$PerUserProto;", "equals", "", "other", "hashCode", "", "toString", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class AutoRevokeDumpUserData {
        private final List<AutoRevokeDumpPackageData> pkgs;
        private final UserHandle user;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AutoRevokeDumpUserData copy$default(AutoRevokeDumpUserData autoRevokeDumpUserData, UserHandle userHandle, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                userHandle = autoRevokeDumpUserData.user;
            }
            if ((i & 2) != 0) {
                list = autoRevokeDumpUserData.pkgs;
            }
            return autoRevokeDumpUserData.copy(userHandle, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UserHandle getUser() {
            return this.user;
        }

        public final List<AutoRevokeDumpPackageData> component2() {
            return this.pkgs;
        }

        public final AutoRevokeDumpUserData copy(UserHandle user, List<AutoRevokeDumpPackageData> pkgs) {
            Intrinsics.checkParameterIsNotNull(user, "user");
            Intrinsics.checkParameterIsNotNull(pkgs, "pkgs");
            return new AutoRevokeDumpUserData(user, pkgs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AutoRevokeDumpUserData)) {
                return false;
            }
            AutoRevokeDumpUserData autoRevokeDumpUserData = (AutoRevokeDumpUserData) other;
            return Intrinsics.areEqual(this.user, autoRevokeDumpUserData.user) && Intrinsics.areEqual(this.pkgs, autoRevokeDumpUserData.pkgs);
        }

        public int hashCode() {
            UserHandle userHandle = this.user;
            int iHashCode = (userHandle != null ? userHandle.hashCode() : 0) * 31;
            List<AutoRevokeDumpPackageData> list = this.pkgs;
            return iHashCode + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "AutoRevokeDumpUserData(user=" + this.user + ", pkgs=" + this.pkgs + ")";
        }

        public AutoRevokeDumpUserData(UserHandle user, List<AutoRevokeDumpPackageData> pkgs) {
            Intrinsics.checkParameterIsNotNull(user, "user");
            Intrinsics.checkParameterIsNotNull(pkgs, "pkgs");
            this.user = user;
            this.pkgs = pkgs;
        }

        public final UserHandle getUser() {
            return this.user;
        }

        public final List<AutoRevokeDumpPackageData> getPkgs() {
            return this.pkgs;
        }

        public final AutoRevokePermissionsProto.PerUserProto dump() {
            AutoRevokePermissionsProto.PerUserProto.Builder dump = AutoRevokePermissionsProto.PerUserProto.newBuilder().setUserId(this.user.getIdentifier());
            Iterable $this$forEach$iv = this.pkgs;
            for (Object element$iv : $this$forEach$iv) {
                AutoRevokeDumpPackageData it = (AutoRevokeDumpPackageData) element$iv;
                dump.addPackages(it.dump());
            }
            AutoRevokePermissionsProto.PerUserProto perUserProtoBuild = dump.build();
            Intrinsics.checkExpressionValueIsNotNull(perUserProtoBuild, "dump.build()");
            return perUserProtoBuild;
        }
    }

    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0014J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\nHÆ\u0003JX\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\nHÆ\u0001¢\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0003HÖ\u0001J\t\u0010(\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006)"}, d2 = {"Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpPackageData;", "", "uid", "", "packageName", "", "firstInstallTime", "", "lastTimeVisible", "implementedServices", "", "groups", "Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpGroupData;", "(ILjava/lang/String;JLjava/lang/Long;Ljava/util/List;Ljava/util/List;)V", "getFirstInstallTime", "()J", "getGroups", "()Ljava/util/List;", "getImplementedServices", "getLastTimeVisible", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPackageName", "()Ljava/lang/String;", "getUid", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ILjava/lang/String;JLjava/lang/Long;Ljava/util/List;Ljava/util/List;)Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpPackageData;", "dump", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$PackageProto;", "equals", "", "other", "hashCode", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class AutoRevokeDumpPackageData {
        private final long firstInstallTime;
        private final List<AutoRevokeDumpGroupData> groups;
        private final List<String> implementedServices;
        private final Long lastTimeVisible;
        private final String packageName;
        private final int uid;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AutoRevokeDumpPackageData copy$default(AutoRevokeDumpPackageData autoRevokeDumpPackageData, int i, String str, long j, Long l, List list, List list2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = autoRevokeDumpPackageData.uid;
            }
            if ((i2 & 2) != 0) {
                str = autoRevokeDumpPackageData.packageName;
            }
            String str2 = str;
            if ((i2 & 4) != 0) {
                j = autoRevokeDumpPackageData.firstInstallTime;
            }
            long j2 = j;
            if ((i2 & 8) != 0) {
                l = autoRevokeDumpPackageData.lastTimeVisible;
            }
            Long l2 = l;
            if ((i2 & 16) != 0) {
                list = autoRevokeDumpPackageData.implementedServices;
            }
            List list3 = list;
            if ((i2 & 32) != 0) {
                list2 = autoRevokeDumpPackageData.groups;
            }
            return autoRevokeDumpPackageData.copy(i, str2, j2, l2, list3, list2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getUid() {
            return this.uid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getFirstInstallTime() {
            return this.firstInstallTime;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getLastTimeVisible() {
            return this.lastTimeVisible;
        }

        public final List<String> component5() {
            return this.implementedServices;
        }

        public final List<AutoRevokeDumpGroupData> component6() {
            return this.groups;
        }

        public final AutoRevokeDumpPackageData copy(int uid, String packageName, long firstInstallTime, Long lastTimeVisible, List<String> implementedServices, List<AutoRevokeDumpGroupData> groups) {
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(implementedServices, "implementedServices");
            Intrinsics.checkParameterIsNotNull(groups, "groups");
            return new AutoRevokeDumpPackageData(uid, packageName, firstInstallTime, lastTimeVisible, implementedServices, groups);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AutoRevokeDumpPackageData)) {
                return false;
            }
            AutoRevokeDumpPackageData autoRevokeDumpPackageData = (AutoRevokeDumpPackageData) other;
            return this.uid == autoRevokeDumpPackageData.uid && Intrinsics.areEqual(this.packageName, autoRevokeDumpPackageData.packageName) && this.firstInstallTime == autoRevokeDumpPackageData.firstInstallTime && Intrinsics.areEqual(this.lastTimeVisible, autoRevokeDumpPackageData.lastTimeVisible) && Intrinsics.areEqual(this.implementedServices, autoRevokeDumpPackageData.implementedServices) && Intrinsics.areEqual(this.groups, autoRevokeDumpPackageData.groups);
        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.uid) * 31;
            String str = this.packageName;
            int iHashCode2 = (((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Long.hashCode(this.firstInstallTime)) * 31;
            Long l = this.lastTimeVisible;
            int iHashCode3 = (iHashCode2 + (l != null ? l.hashCode() : 0)) * 31;
            List<String> list = this.implementedServices;
            int iHashCode4 = (iHashCode3 + (list != null ? list.hashCode() : 0)) * 31;
            List<AutoRevokeDumpGroupData> list2 = this.groups;
            return iHashCode4 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "AutoRevokeDumpPackageData(uid=" + this.uid + ", packageName=" + this.packageName + ", firstInstallTime=" + this.firstInstallTime + ", lastTimeVisible=" + this.lastTimeVisible + ", implementedServices=" + this.implementedServices + ", groups=" + this.groups + ")";
        }

        public AutoRevokeDumpPackageData(int uid, String packageName, long firstInstallTime, Long lastTimeVisible, List<String> implementedServices, List<AutoRevokeDumpGroupData> groups) {
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(implementedServices, "implementedServices");
            Intrinsics.checkParameterIsNotNull(groups, "groups");
            this.uid = uid;
            this.packageName = packageName;
            this.firstInstallTime = firstInstallTime;
            this.lastTimeVisible = lastTimeVisible;
            this.implementedServices = implementedServices;
            this.groups = groups;
        }

        public final int getUid() {
            return this.uid;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final long getFirstInstallTime() {
            return this.firstInstallTime;
        }

        public final Long getLastTimeVisible() {
            return this.lastTimeVisible;
        }

        public final List<String> getImplementedServices() {
            return this.implementedServices;
        }

        public final List<AutoRevokeDumpGroupData> getGroups() {
            return this.groups;
        }

        public final AutoRevokePermissionsProto.PackageProto dump() {
            AutoRevokePermissionsProto.PackageProto.Builder dump = AutoRevokePermissionsProto.PackageProto.newBuilder().setUid(this.uid).setPackageName(this.packageName).setFirstInstallTime(this.firstInstallTime);
            Long l = this.lastTimeVisible;
            if (l != null) {
                l.longValue();
                Intrinsics.checkExpressionValueIsNotNull(dump, "dump");
                dump.setLastTimeVisible(this.lastTimeVisible.longValue());
            }
            Iterable $this$forEach$iv = this.implementedServices;
            for (Object element$iv : $this$forEach$iv) {
                String it = (String) element$iv;
                dump.addImplementedServices(it);
            }
            Iterable $this$forEach$iv2 = this.groups;
            for (Object element$iv2 : $this$forEach$iv2) {
                AutoRevokeDumpGroupData it2 = (AutoRevokeDumpGroupData) element$iv2;
                dump.addGroups(it2.dump());
            }
            AutoRevokePermissionsProto.PackageProto packageProtoBuild = dump.build();
            Intrinsics.checkExpressionValueIsNotNull(packageProtoBuild, "dump.build()");
            return packageProtoBuild;
        }
    }

    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003JO\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\u0013\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/android/permissioncontroller/permission/service/AutoRevokeDumpLiveData$AutoRevokeDumpGroupData;", "", "groupName", "", "isFixed", "", "isAnyGrantedIncludingAppOp", "isGrantedByDefault", "isGrantedByRole", "isUserSensitive", "isAutoRevoked", "(Ljava/lang/String;ZZZZZZ)V", "getGroupName", "()Ljava/lang/String;", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "dump", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$PermissionGroupProto;", "equals", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class AutoRevokeDumpGroupData {
        private final String groupName;
        private final boolean isAnyGrantedIncludingAppOp;
        private final boolean isAutoRevoked;
        private final boolean isFixed;
        private final boolean isGrantedByDefault;
        private final boolean isGrantedByRole;
        private final boolean isUserSensitive;

        public static /* synthetic */ AutoRevokeDumpGroupData copy$default(AutoRevokeDumpGroupData autoRevokeDumpGroupData, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = autoRevokeDumpGroupData.groupName;
            }
            if ((i & 2) != 0) {
                z = autoRevokeDumpGroupData.isFixed;
            }
            boolean z7 = z;
            if ((i & 4) != 0) {
                z2 = autoRevokeDumpGroupData.isAnyGrantedIncludingAppOp;
            }
            boolean z8 = z2;
            if ((i & 8) != 0) {
                z3 = autoRevokeDumpGroupData.isGrantedByDefault;
            }
            boolean z9 = z3;
            if ((i & 16) != 0) {
                z4 = autoRevokeDumpGroupData.isGrantedByRole;
            }
            boolean z10 = z4;
            if ((i & 32) != 0) {
                z5 = autoRevokeDumpGroupData.isUserSensitive;
            }
            boolean z11 = z5;
            if ((i & 64) != 0) {
                z6 = autoRevokeDumpGroupData.isAutoRevoked;
            }
            return autoRevokeDumpGroupData.copy(str, z7, z8, z9, z10, z11, z6);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getGroupName() {
            return this.groupName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsFixed() {
            return this.isFixed;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsAnyGrantedIncludingAppOp() {
            return this.isAnyGrantedIncludingAppOp;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsGrantedByDefault() {
            return this.isGrantedByDefault;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsGrantedByRole() {
            return this.isGrantedByRole;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsUserSensitive() {
            return this.isUserSensitive;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getIsAutoRevoked() {
            return this.isAutoRevoked;
        }

        public final AutoRevokeDumpGroupData copy(String groupName, boolean isFixed, boolean isAnyGrantedIncludingAppOp, boolean isGrantedByDefault, boolean isGrantedByRole, boolean isUserSensitive, boolean isAutoRevoked) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            return new AutoRevokeDumpGroupData(groupName, isFixed, isAnyGrantedIncludingAppOp, isGrantedByDefault, isGrantedByRole, isUserSensitive, isAutoRevoked);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AutoRevokeDumpGroupData)) {
                return false;
            }
            AutoRevokeDumpGroupData autoRevokeDumpGroupData = (AutoRevokeDumpGroupData) other;
            return Intrinsics.areEqual(this.groupName, autoRevokeDumpGroupData.groupName) && this.isFixed == autoRevokeDumpGroupData.isFixed && this.isAnyGrantedIncludingAppOp == autoRevokeDumpGroupData.isAnyGrantedIncludingAppOp && this.isGrantedByDefault == autoRevokeDumpGroupData.isGrantedByDefault && this.isGrantedByRole == autoRevokeDumpGroupData.isGrantedByRole && this.isUserSensitive == autoRevokeDumpGroupData.isUserSensitive && this.isAutoRevoked == autoRevokeDumpGroupData.isAutoRevoked;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v12 */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v16 */
        /* JADX WARN: Type inference failed for: r1v17 */
        /* JADX WARN: Type inference failed for: r1v18 */
        /* JADX WARN: Type inference failed for: r1v19 */
        /* JADX WARN: Type inference failed for: r1v20 */
        /* JADX WARN: Type inference failed for: r1v3, types: [int] */
        /* JADX WARN: Type inference failed for: r1v5, types: [int] */
        /* JADX WARN: Type inference failed for: r1v7, types: [int] */
        /* JADX WARN: Type inference failed for: r1v9, types: [int] */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v2 */
        public int hashCode() {
            String str = this.groupName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.isFixed;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int i = (iHashCode + r1) * 31;
            boolean z2 = this.isAnyGrantedIncludingAppOp;
            ?? r12 = z2;
            if (z2) {
                r12 = 1;
            }
            int i2 = (i + r12) * 31;
            boolean z3 = this.isGrantedByDefault;
            ?? r13 = z3;
            if (z3) {
                r13 = 1;
            }
            int i3 = (i2 + r13) * 31;
            boolean z4 = this.isGrantedByRole;
            ?? r14 = z4;
            if (z4) {
                r14 = 1;
            }
            int i4 = (i3 + r14) * 31;
            boolean z5 = this.isUserSensitive;
            ?? r15 = z5;
            if (z5) {
                r15 = 1;
            }
            int i5 = (i4 + r15) * 31;
            boolean z6 = this.isAutoRevoked;
            return i5 + (z6 ? 1 : z6);
        }

        public String toString() {
            return "AutoRevokeDumpGroupData(groupName=" + this.groupName + ", isFixed=" + this.isFixed + ", isAnyGrantedIncludingAppOp=" + this.isAnyGrantedIncludingAppOp + ", isGrantedByDefault=" + this.isGrantedByDefault + ", isGrantedByRole=" + this.isGrantedByRole + ", isUserSensitive=" + this.isUserSensitive + ", isAutoRevoked=" + this.isAutoRevoked + ")";
        }

        public AutoRevokeDumpGroupData(String groupName, boolean isFixed, boolean isAnyGrantedIncludingAppOp, boolean isGrantedByDefault, boolean isGrantedByRole, boolean isUserSensitive, boolean isAutoRevoked) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            this.groupName = groupName;
            this.isFixed = isFixed;
            this.isAnyGrantedIncludingAppOp = isAnyGrantedIncludingAppOp;
            this.isGrantedByDefault = isGrantedByDefault;
            this.isGrantedByRole = isGrantedByRole;
            this.isUserSensitive = isUserSensitive;
            this.isAutoRevoked = isAutoRevoked;
        }

        public final String getGroupName() {
            return this.groupName;
        }

        public final boolean isFixed() {
            return this.isFixed;
        }

        public final boolean isAnyGrantedIncludingAppOp() {
            return this.isAnyGrantedIncludingAppOp;
        }

        public final boolean isGrantedByDefault() {
            return this.isGrantedByDefault;
        }

        public final boolean isGrantedByRole() {
            return this.isGrantedByRole;
        }

        public final boolean isUserSensitive() {
            return this.isUserSensitive;
        }

        public final boolean isAutoRevoked() {
            return this.isAutoRevoked;
        }

        public final AutoRevokePermissionsProto.PermissionGroupProto dump() {
            AutoRevokePermissionsProto.PermissionGroupProto permissionGroupProtoBuild = AutoRevokePermissionsProto.PermissionGroupProto.newBuilder().setGroupName(this.groupName).setIsFixed(this.isFixed).setIsAnyGrantedIncludingAppop(this.isAnyGrantedIncludingAppOp).setIsGrantedByDefault(this.isGrantedByDefault).setIsGrantedByRole(this.isGrantedByRole).setIsUserSensitive(this.isUserSensitive).setIsAutoRevoked(this.isAutoRevoked).build();
            Intrinsics.checkExpressionValueIsNotNull(permissionGroupProtoBuild, "PermissionGroupProto.new…                 .build()");
            return permissionGroupProtoBuild;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x02b9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:283:? A[LOOP:11: B:114:0x026e->B:283:?, LOOP_END, SYNTHETIC] */
    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onUpdate() {
        /*
            Method dump skipped, instruction units count: 1283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.AutoRevokeDumpLiveData.onUpdate():void");
    }
}
