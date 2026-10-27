package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.DataRepository;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.UidSensitivityState;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: UserSensitivityLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00020\u0001:\u0001\u001bB\u001f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0015\u001a\u00020\u000eH\u0002J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lcom/android/permissioncontroller/permission/data/UserSensitivityLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/UidSensitivityState;", "app", "Landroid/app/Application;", "uid", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;ILandroid/os/UserHandle;)V", "context", "Landroid/content/Context;", "getAllUids", "", "packageLiveDatas", "", "", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "userPackageInfosLiveData", "Lcom/android/permissioncontroller/permission/data/UserPackageInfosLiveData;", "getAndObservePackageLiveDatas", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class UserSensitivityLiveData extends SmartAsyncMediatorLiveData<Map<Integer, ? extends UidSensitivityState>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final Context context;
    private final boolean getAllUids;
    private final Map<String, LightPackageInfoLiveData> packageLiveDatas;
    private final int uid;
    private final UserHandle user;
    private final UserPackageInfosLiveData userPackageInfosLiveData;

    private UserSensitivityLiveData(Application app, int uid, UserHandle user) {
        this.app = app;
        this.uid = uid;
        this.user = user;
        this.packageLiveDatas = new LinkedHashMap();
        this.userPackageInfosLiveData = UserPackageInfosLiveData.INSTANCE.get(this.user);
        this.getAllUids = this.uid == -1;
        try {
            Context userContext = Utils.getUserContext(this.app, this.user);
            Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
            this.context = userContext;
            if (this.getAllUids) {
                addSource(this.userPackageInfosLiveData, new Observer<List<LightPackageInfo>>() { // from class: com.android.permissioncontroller.permission.data.UserSensitivityLiveData.1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(List<LightPackageInfo> list) {
                        UserSensitivityLiveData.this.updateIfActive();
                    }
                });
                addSource(LauncherPackagesLiveData.INSTANCE, new Observer<Set<? extends String>>() { // from class: com.android.permissioncontroller.permission.data.UserSensitivityLiveData.2
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(Set<? extends String> set) {
                        onChanged2((Set<String>) set);
                    }

                    /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                    public final void onChanged2(Set<String> set) {
                        UserSensitivityLiveData.this.updateIfActive();
                    }
                });
            } else {
                updateIfActive();
            }
        } catch (PackageManager.NameNotFoundException cannotHappen) {
            throw new IllegalStateException(cannotHappen);
        }
    }

    public /* synthetic */ UserSensitivityLiveData(Application app, int uid, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, uid, user);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.lang.Boolean] */
    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        List<LightPackageInfo> value;
        Object answer$iv;
        boolean pkgHasLauncherIcon;
        int flags;
        int iIntValue;
        boolean z;
        boolean z2;
        PackageManager pm = this.context.getPackageManager();
        int i = 1;
        if (!this.getAllUids) {
            boolean uidHasPackages = getAndObservePackageLiveDatas();
            if (uidHasPackages) {
                Map<String, LightPackageInfoLiveData> map = this.packageLiveDatas;
                if (!map.isEmpty()) {
                    Iterator<Map.Entry<String, LightPackageInfoLiveData>> it = map.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        Map.Entry<String, LightPackageInfoLiveData> next = it.next();
                        if (!Boxing.boxBoolean(next.getValue().getIsInitialized() && next.getValue().getValue() == null).booleanValue()) {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = true;
                }
                if (!z) {
                    Map<String, LightPackageInfoLiveData> map2 = this.packageLiveDatas;
                    if (!map2.isEmpty()) {
                        Iterator<Map.Entry<String, LightPackageInfoLiveData>> it2 = map2.entrySet().iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (!Boxing.boxBoolean(it2.next().getValue().getIsInitialized()).booleanValue()) {
                                    z2 = false;
                                    break;
                                }
                            } else {
                                z2 = true;
                                break;
                            }
                        }
                    } else {
                        z2 = true;
                    }
                    if (!z2) {
                        return Unit.INSTANCE;
                    }
                }
            }
            this.packageLiveDatas.clear();
            INSTANCE.invalidateSingle(TuplesKt.to(Boxing.boxInt(this.uid), this.user));
            postValue(null);
            return Unit.INSTANCE;
        }
        if (this.getAllUids) {
            value = this.userPackageInfosLiveData.getValue();
            if (value == null) {
                return value == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? value : Unit.INSTANCE;
            }
        } else {
            Map<String, LightPackageInfoLiveData> map3 = this.packageLiveDatas;
            Collection destination$iv$iv = new ArrayList();
            Iterator<Map.Entry<String, LightPackageInfoLiveData>> it3 = map3.entrySet().iterator();
            while (it3.hasNext()) {
                LightPackageInfo value2 = it3.next().getValue().getValue();
                if (value2 != null) {
                    destination$iv$iv.add(value2);
                }
            }
            value = (List) destination$iv$iv;
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "if (getAllUids) {\n      …t.value.value }\n        }");
        if (job.isCancelled()) {
            return Unit.INSTANCE;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<String> runtimePerms = Utils.getRuntimePlatformPermissionNames();
        for (LightPackageInfo pkg : value) {
            Integer numBoxInt = Boxing.boxInt(pkg.getUid());
            Object value$iv = linkedHashMap.get(numBoxInt);
            if (value$iv == null) {
                answer$iv = new UidSensitivityState(new LinkedHashSet(), new LinkedHashMap());
                linkedHashMap.put(numBoxInt, answer$iv);
            } else {
                answer$iv = value$iv;
            }
            UidSensitivityState userSensitiveState = (UidSensitivityState) answer$iv;
            userSensitiveState.getPackages().add(pkg);
            if (!this.getAllUids) {
                pkgHasLauncherIcon = KotlinUtils.INSTANCE.packageHasLaunchIntent(this.context, pkg.getPackageName());
            } else {
                ?? BoxBoolean = (Set) LauncherPackagesLiveData.INSTANCE.getValue();
                if (BoxBoolean == 0 || (BoxBoolean = Boxing.boxBoolean(BoxBoolean.contains(pkg.getPackageName()))) == 0) {
                    return BoxBoolean == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? BoxBoolean : Unit.INSTANCE;
                }
                pkgHasLauncherIcon = BoxBoolean.booleanValue();
            }
            int i2 = (pkg.getAppFlags() & i) != 0 ? i : 0;
            List<String> requestedPermissions = pkg.getRequestedPermissions();
            Intrinsics.checkExpressionValueIsNotNull(runtimePerms, "runtimePerms");
            for (String perm : CollectionsKt.intersect(requestedPermissions, runtimePerms)) {
                if (i2 != 0 && !pkgHasLauncherIcon) {
                    if (((pm.getPermissionFlags(perm, pkg.getPackageName(), this.user) & 32) != 0 ? i : 0) != 0) {
                        flags = 0;
                    } else {
                        flags = 256;
                    }
                } else {
                    flags = Utils.FLAGS_ALWAYS_USER_SENSITIVE;
                }
                Integer previousFlags = userSensitiveState.getPermStates().get(perm);
                if (previousFlags != null) {
                    if (pkg.getUid() < 10000) {
                        iIntValue = previousFlags.intValue() & flags;
                    } else {
                        iIntValue = previousFlags.intValue() | flags;
                    }
                    flags = iIntValue;
                }
                Map<String, Integer> permStates = userSensitiveState.getPermStates();
                Intrinsics.checkExpressionValueIsNotNull(perm, "perm");
                permStates.put(perm, Boxing.boxInt(flags));
                i = 1;
            }
            if (!job.isCancelled()) {
                i = 1;
            } else {
                return Unit.INSTANCE;
            }
        }
        postValue(linkedHashMap);
        return Unit.INSTANCE;
    }

    private final boolean getAndObservePackageLiveDatas() {
        List packageNames;
        String[] packagesForUid = this.app.getPackageManager().getPackagesForUid(this.uid);
        if (packagesForUid == null || (packageNames = ArraysKt.toList(packagesForUid)) == null) {
            packageNames = CollectionsKt.emptyList();
        }
        SmartUpdateMediatorLiveData.setSourcesToDifference$default(this, packageNames, this.packageLiveDatas, new Function1<String, LightPackageInfoLiveData>() { // from class: com.android.permissioncontroller.permission.data.UserSensitivityLiveData$getAndObservePackageLiveDatas$getLiveData$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LightPackageInfoLiveData invoke(String packageName) {
                Intrinsics.checkParameterIsNotNull(packageName, "packageName");
                return (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, packageName, this.this$0.user);
            }
        }, null, 8, null);
        return !packageNames.isEmpty();
    }

    /* JADX INFO: compiled from: UserSensitivityLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0004H\u0086\u0002J\u0011\u0010\u0007\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H\u0086\u0002J\u001c\u0010\n\u001a\u00020\u00052\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\f"}, d2 = {"Lcom/android/permissioncontroller/permission/data/UserSensitivityLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/UserSensitivityLiveData;", "()V", "get", "user", "uid", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<Pair<? extends Integer, ? extends UserHandle>, UserSensitivityLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public /* bridge */ /* synthetic */ DataRepository.InactiveTimekeeper newValue(Pair<? extends Integer, ? extends UserHandle> pair) {
            return newValue2((Pair<Integer, UserHandle>) pair);
        }

        /* JADX INFO: renamed from: newValue, reason: avoid collision after fix types in other method */
        protected UserSensitivityLiveData newValue2(Pair<Integer, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new UserSensitivityLiveData(permissionControllerApplication, key.getFirst().intValue(), key.getSecond(), null);
        }

        public final UserSensitivityLiveData get(int uid) {
            if (uid == -1) {
                throw new IllegalArgumentException("Cannot get single uid livedata without a valid uid");
            }
            Integer numValueOf = Integer.valueOf(uid);
            UserHandle userHandleForUid = UserHandle.getUserHandleForUid(uid);
            Intrinsics.checkExpressionValueIsNotNull(userHandleForUid, "UserHandle.getUserHandleForUid(uid)");
            return (UserSensitivityLiveData) DataRepositoryKt.get(this, numValueOf, userHandleForUid);
        }

        public final UserSensitivityLiveData get(UserHandle user) {
            Intrinsics.checkParameterIsNotNull(user, "user");
            return (UserSensitivityLiveData) DataRepositoryKt.get(this, -1, user);
        }
    }
}
