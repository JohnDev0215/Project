package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.UserHandle;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.LightPackageInfoLiveData;
import com.android.permissioncontroller.permission.data.PackageBroadcastReceiver;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermGroupInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroup;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermGroupLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0018B\u0017\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0013H\u0014J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0014R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermGroupLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/PermGroup;", "Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "app", "Landroid/app/Application;", "groupName", "", "(Landroid/app/Application;Ljava/lang/String;)V", "LOG_TAG", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "groupInfo", "Landroid/content/pm/PackageItemInfo;", "packageLiveDatas", "", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "onActive", "", "onInactive", "onPackageUpdate", "packageName", "onUpdate", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermGroupLiveData extends SmartUpdateMediatorLiveData<PermGroup> implements PackageBroadcastReceiver.PackageBroadcastListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String LOG_TAG;
    private final Application app;
    private final Context context;
    private PackageItemInfo groupInfo;
    private final String groupName;
    private final Map<String, LightPackageInfoLiveData> packageLiveDatas;

    private PermGroupLiveData(Application app, String groupName) {
        this.app = app;
        this.groupName = groupName;
        this.LOG_TAG = getClass().getSimpleName();
        Context applicationContext = this.app.getApplicationContext();
        if (applicationContext == null) {
            Intrinsics.throwNpe();
        }
        this.context = applicationContext;
        this.packageLiveDatas = new LinkedHashMap();
    }

    public /* synthetic */ PermGroupLiveData(Application app, String groupName, DefaultConstructorMarker $constructor_marker) {
        this(app, groupName);
    }

    @Override // com.android.permissioncontroller.permission.data.PackageBroadcastReceiver.PackageBroadcastListener
    public void onPackageUpdate(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        updateIfActive();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        Map permissionInfos = new LinkedHashMap();
        PackageItemInfo groupInfo = Utils.getGroupInfo(this.groupName, this.context);
        if (groupInfo == null) {
            PermGroupLiveData $this$run = this;
            Log.e($this$run.LOG_TAG, "Invalid permission group " + $this$run.groupName);
            INSTANCE.invalidateSingle($this$run.groupName);
            $this$run.setValue(null);
            return;
        }
        this.groupInfo = groupInfo;
        if (groupInfo == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupInfo");
        }
        if (groupInfo instanceof PermissionGroupInfo) {
            try {
                List<PermissionInfo> installedRuntimePermissionInfosForGroup = Utils.getInstalledRuntimePermissionInfosForGroup(this.context.getPackageManager(), this.groupName);
                Intrinsics.checkExpressionValueIsNotNull(installedRuntimePermissionInfosForGroup, "try {\n                  … return\n                }");
                for (PermissionInfo permInfo : installedRuntimePermissionInfosForGroup) {
                    String str = permInfo.name;
                    Intrinsics.checkExpressionValueIsNotNull(str, "permInfo.name");
                    Intrinsics.checkExpressionValueIsNotNull(permInfo, "permInfo");
                    permissionInfos.put(str, new LightPermInfo(permInfo));
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(this.LOG_TAG, "Invalid permission group " + this.groupName);
                INSTANCE.invalidateSingle(this.groupName);
                setValue(null);
                return;
            }
        } else {
            if (!(groupInfo instanceof PermissionInfo)) {
                return;
            }
            PackageItemInfo packageItemInfo = this.groupInfo;
            if (packageItemInfo == null) {
                Intrinsics.throwUninitializedPropertyAccessException("groupInfo");
            }
            String str2 = packageItemInfo.name;
            Intrinsics.checkExpressionValueIsNotNull(str2, "groupInfo.name");
            PackageItemInfo packageItemInfo2 = this.groupInfo;
            if (packageItemInfo2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("groupInfo");
            }
            if (packageItemInfo2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.content.pm.PermissionInfo");
            }
            permissionInfos.put(str2, new LightPermInfo((PermissionInfo) packageItemInfo2));
        }
        PackageItemInfo packageItemInfo3 = this.groupInfo;
        if (packageItemInfo3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupInfo");
        }
        PermGroup permGroup = new PermGroup(new LightPermGroupInfo(packageItemInfo3), permissionInfos);
        setValue(permGroup);
        Iterable $this$map$iv = permissionInfos.values();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(((LightPermInfo) item$iv$iv).getPackageName());
        }
        Set packageNames = CollectionsKt.toMutableSet((List) destination$iv$iv);
        PackageItemInfo packageItemInfo4 = this.groupInfo;
        if (packageItemInfo4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupInfo");
        }
        String str3 = packageItemInfo4.packageName;
        Intrinsics.checkExpressionValueIsNotNull(str3, "groupInfo.packageName");
        packageNames.add(str3);
        Function1 getLiveData = new Function1<String, LightPackageInfoLiveData>() { // from class: com.android.permissioncontroller.permission.data.PermGroupLiveData$onUpdate$getLiveData$1
            @Override // kotlin.jvm.functions.Function1
            public final LightPackageInfoLiveData invoke(String packageName) {
                Intrinsics.checkParameterIsNotNull(packageName, "packageName");
                LightPackageInfoLiveData.Companion companion = LightPackageInfoLiveData.INSTANCE;
                UserHandle userHandle = UserHandle.SYSTEM;
                Intrinsics.checkExpressionValueIsNotNull(userHandle, "UserHandle.SYSTEM");
                return (LightPackageInfoLiveData) DataRepositoryKt.get(companion, packageName, userHandle);
            }
        };
        SmartUpdateMediatorLiveData.setSourcesToDifference$default(this, packageNames, this.packageLiveDatas, getLiveData, null, 8, null);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        PackageBroadcastReceiver.INSTANCE.removeAllCallback(this);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        updateIfActive();
        super.onActive();
        PackageBroadcastReceiver.INSTANCE.addAllCallback(this);
    }

    /* JADX INFO: compiled from: PermGroupLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermGroupLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "", "Lcom/android/permissioncontroller/permission/data/PermGroupLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<String, PermGroupLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public PermGroupLiveData newValue(String key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new PermGroupLiveData(permissionControllerApplication, key, null);
        }
    }
}
