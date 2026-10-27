package com.android.permissioncontroller.permission.data;

import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllPackageInfosLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0014J \u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0002R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/android/permissioncontroller/permission/data/AllPackageInfosLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Landroid/os/UserHandle;", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "()V", "userPackageInfos", "", "userPackageInfosLiveDatas", "Lcom/android/permissioncontroller/permission/data/UserPackageInfosLiveData;", "onUpdate", "", "onUserPackageUpdates", "user", "packageInfos", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AllPackageInfosLiveData extends SmartUpdateMediatorLiveData<Map<UserHandle, ? extends List<? extends LightPackageInfo>>> {
    public static final AllPackageInfosLiveData INSTANCE;
    private static final Map<UserHandle, List<LightPackageInfo>> userPackageInfos;
    private static final Map<UserHandle, UserPackageInfosLiveData> userPackageInfosLiveDatas;

    static {
        AllPackageInfosLiveData allPackageInfosLiveData = new AllPackageInfosLiveData();
        INSTANCE = allPackageInfosLiveData;
        userPackageInfosLiveDatas = new LinkedHashMap();
        userPackageInfos = new LinkedHashMap();
        allPackageInfosLiveData.addSource(UsersLiveData.INSTANCE, new Observer<List<? extends UserHandle>>() { // from class: com.android.permissioncontroller.permission.data.AllPackageInfosLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends UserHandle> list) {
                onChanged2((List<UserHandle>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<UserHandle> list) {
                AllPackageInfosLiveData.INSTANCE.updateIfActive();
            }
        });
    }

    private AllPackageInfosLiveData() {
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        List<? extends UserHandle> users = UsersLiveData.INSTANCE.getValue();
        if (users != null) {
            AllPackageInfosLiveData$onUpdate$1$getLiveData$1 allPackageInfosLiveData$onUpdate$1$getLiveData$1 = new Function1<UserHandle, UserPackageInfosLiveData>() { // from class: com.android.permissioncontroller.permission.data.AllPackageInfosLiveData$onUpdate$1$getLiveData$1
                @Override // kotlin.jvm.functions.Function1
                public final UserPackageInfosLiveData invoke(UserHandle user) {
                    Intrinsics.checkParameterIsNotNull(user, "user");
                    return UserPackageInfosLiveData.INSTANCE.get(user);
                }
            };
            AllPackageInfosLiveData allPackageInfosLiveData = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(users, "users");
            allPackageInfosLiveData.setSourcesToDifference(users, userPackageInfosLiveDatas, allPackageInfosLiveData$onUpdate$1$getLiveData$1, new Function1<UserHandle, Unit>() { // from class: com.android.permissioncontroller.permission.data.AllPackageInfosLiveData$onUpdate$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(UserHandle userHandle) {
                    invoke2(userHandle);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(UserHandle user) {
                    Intrinsics.checkParameterIsNotNull(user, "user");
                    AllPackageInfosLiveData allPackageInfosLiveData2 = AllPackageInfosLiveData.INSTANCE;
                    AllPackageInfosLiveData allPackageInfosLiveData3 = AllPackageInfosLiveData.INSTANCE;
                    UserPackageInfosLiveData userPackageInfosLiveData = (UserPackageInfosLiveData) AllPackageInfosLiveData.userPackageInfosLiveDatas.get(user);
                    allPackageInfosLiveData2.onUserPackageUpdates(user, userPackageInfosLiveData != null ? userPackageInfosLiveData.getValue() : null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onUserPackageUpdates(UserHandle user, List<LightPackageInfo> packageInfos) {
        if (packageInfos == null) {
            userPackageInfos.remove(user);
        } else {
            userPackageInfos.put(user, packageInfos);
        }
        Map<UserHandle, UserPackageInfosLiveData> map = userPackageInfosLiveDatas;
        boolean z = true;
        if (!map.isEmpty()) {
            Iterator<Map.Entry<UserHandle, UserPackageInfosLiveData>> it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!it.next().getValue().getIsInitialized()) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            setValue(MapsKt.toMap(userPackageInfos));
        }
    }
}
