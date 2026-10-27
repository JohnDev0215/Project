package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroup;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SinglePermGroupPackagesUiInfoLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u001a2 \u0012\u001c\u0012\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0004\u0012\u00020\u00060\u00020\u0001:\u0001\u001aB\u0017\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0002\u0010\nJ\"\u0010\u0015\u001a\u00020\u00162\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0014\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0004\u0012\u00020\u00060\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/android/permissioncontroller/permission/data/SinglePermGroupPackagesUiInfoLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo;", "app", "Landroid/app/Application;", "permGroupName", "(Landroid/app/Application;Ljava/lang/String;)V", "appPermGroupLiveDatas", "", "Lcom/android/permissioncontroller/permission/data/AppPermGroupUiInfoLiveData;", "isCustomGroup", "", "permGroupLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupLiveData;", "permGroupPackagesLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesLiveData;", "shownPackages", "addAndRemoveAppPermGroupLiveDatas", "", "pkgs", "", "onUpdate", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class SinglePermGroupPackagesUiInfoLiveData extends SmartUpdateMediatorLiveData<Map<Pair<? extends String, ? extends UserHandle>, ? extends AppPermGroupUiInfo>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final Map<Pair<String, UserHandle>, AppPermGroupUiInfoLiveData> appPermGroupLiveDatas;
    private final boolean isCustomGroup;
    private final PermGroupLiveData permGroupLiveData;
    private final String permGroupName;
    private final PermGroupsPackagesLiveData permGroupPackagesLiveData;
    private final Map<Pair<String, UserHandle>, AppPermGroupUiInfo> shownPackages;

    private SinglePermGroupPackagesUiInfoLiveData(Application app, String permGroupName) {
        this.app = app;
        this.permGroupName = permGroupName;
        this.permGroupLiveData = PermGroupLiveData.INSTANCE.get(this.permGroupName);
        this.isCustomGroup = !Utils.getPlatformPermissionGroups().contains(this.permGroupName);
        this.permGroupPackagesLiveData = PermGroupsPackagesLiveData.INSTANCE.get(this.isCustomGroup);
        this.appPermGroupLiveDatas = new LinkedHashMap();
        this.shownPackages = new LinkedHashMap();
        addSource(this.permGroupLiveData, new Observer<PermGroup>() { // from class: com.android.permissioncontroller.permission.data.SinglePermGroupPackagesUiInfoLiveData.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(PermGroup newPermGroup) {
                if (newPermGroup == null) {
                    SinglePermGroupPackagesUiInfoLiveData.INSTANCE.invalidateSingle(SinglePermGroupPackagesUiInfoLiveData.this.permGroupName);
                    SinglePermGroupPackagesUiInfoLiveData.this.setValue(null);
                }
            }
        });
        addSource(this.permGroupPackagesLiveData, new Observer<Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>>>() { // from class: com.android.permissioncontroller.permission.data.SinglePermGroupPackagesUiInfoLiveData.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>> map) {
                onChanged2((Map<String, ? extends Set<Pair<String, UserHandle>>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends Set<Pair<String, UserHandle>>> map) {
                SinglePermGroupPackagesUiInfoLiveData.this.updateIfActive();
            }
        });
    }

    public /* synthetic */ SinglePermGroupPackagesUiInfoLiveData(Application app, String permGroupName, DefaultConstructorMarker $constructor_marker) {
        this(app, permGroupName);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        PermGroup value;
        Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>> value2 = this.permGroupPackagesLiveData.getValue();
        Set<? extends Pair<? extends String, ? extends UserHandle>> set = value2 != null ? value2.get(this.permGroupName) : null;
        if (set != null) {
            addAndRemoveAppPermGroupLiveDatas(CollectionsKt.toList(set));
            if (set.isEmpty() && (value = this.permGroupLiveData.getValue()) != null && value.getGroupInfo() != null) {
                setValue(MapsKt.emptyMap());
            }
        }
    }

    private final void addAndRemoveAppPermGroupLiveDatas(List<Pair<String, UserHandle>> pkgs) {
        setSourcesToDifference(pkgs, this.appPermGroupLiveDatas, new Function1<Pair<? extends String, ? extends UserHandle>, AppPermGroupUiInfoLiveData>() { // from class: com.android.permissioncontroller.permission.data.SinglePermGroupPackagesUiInfoLiveData$addAndRemoveAppPermGroupLiveDatas$getLiveData$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ AppPermGroupUiInfoLiveData invoke(Pair<? extends String, ? extends UserHandle> pair) {
                return invoke2((Pair<String, UserHandle>) pair);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final AppPermGroupUiInfoLiveData invoke2(Pair<String, UserHandle> key) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                return (AppPermGroupUiInfoLiveData) DataRepositoryKt.get(AppPermGroupUiInfoLiveData.INSTANCE, key.getFirst(), this.this$0.permGroupName, key.getSecond());
            }
        }, new Function1<Pair<? extends String, ? extends UserHandle>, Unit>() { // from class: com.android.permissioncontroller.permission.data.SinglePermGroupPackagesUiInfoLiveData.addAndRemoveAppPermGroupLiveDatas.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends UserHandle> pair) {
                invoke2((Pair<String, UserHandle>) pair);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Pair<String, UserHandle> key) {
                PermGroup value;
                Intrinsics.checkParameterIsNotNull(key, "key");
                AppPermGroupUiInfoLiveData appPermGroupUiInfoLiveData = (AppPermGroupUiInfoLiveData) SinglePermGroupPackagesUiInfoLiveData.this.appPermGroupLiveDatas.get(key);
                AppPermGroupUiInfo appPermGroupUiInfo = appPermGroupUiInfoLiveData != null ? appPermGroupUiInfoLiveData.getValue() : null;
                SinglePermGroupPackagesUiInfoLiveData.this.shownPackages.remove(key);
                if (appPermGroupUiInfo != null) {
                    SinglePermGroupPackagesUiInfoLiveData.this.shownPackages.put(key, appPermGroupUiInfo);
                } else if (appPermGroupUiInfoLiveData != null && appPermGroupUiInfoLiveData.getIsInitialized()) {
                    SinglePermGroupPackagesUiInfoLiveData.this.removeSource(appPermGroupUiInfoLiveData);
                    SinglePermGroupPackagesUiInfoLiveData.this.appPermGroupLiveDatas.remove(key);
                }
                Map $this$all$iv = SinglePermGroupPackagesUiInfoLiveData.this.appPermGroupLiveDatas;
                boolean z = true;
                if (!$this$all$iv.isEmpty()) {
                    Iterator it = $this$all$iv.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry element$iv = (Map.Entry) it.next();
                        if (!((AppPermGroupUiInfoLiveData) element$iv.getValue()).getIsInitialized()) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z && (value = SinglePermGroupPackagesUiInfoLiveData.this.permGroupLiveData.getValue()) != null && value.getGroupInfo() != null) {
                    SinglePermGroupPackagesUiInfoLiveData singlePermGroupPackagesUiInfoLiveData = SinglePermGroupPackagesUiInfoLiveData.this;
                    singlePermGroupPackagesUiInfoLiveData.setValue(MapsKt.toMap(singlePermGroupPackagesUiInfoLiveData.shownPackages));
                }
            }
        });
    }

    /* JADX INFO: compiled from: SinglePermGroupPackagesUiInfoLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/data/SinglePermGroupPackagesUiInfoLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "", "Lcom/android/permissioncontroller/permission/data/SinglePermGroupPackagesUiInfoLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<String, SinglePermGroupPackagesUiInfoLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public SinglePermGroupPackagesUiInfoLiveData newValue(String key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new SinglePermGroupPackagesUiInfoLiveData(permissionControllerApplication, key, null);
        }
    }
}
