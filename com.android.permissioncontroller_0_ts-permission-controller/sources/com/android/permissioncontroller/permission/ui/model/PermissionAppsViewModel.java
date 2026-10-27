package com.android.permissioncontroller.permission.ui.model;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.FragmentKt;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.AllPackageInfosLiveData;
import com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData;
import com.android.permissioncontroller.permission.data.SinglePermGroupPackagesUiInfoLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.ui.Category;
import com.android.permissioncontroller.permission.ui.LocationProviderInterceptDialog;
import com.android.permissioncontroller.permission.utils.KotlinUtilsKt;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: PermissionAppsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 .2\u00020\u0001:\u0002-.B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010 \u001a\u00020\u000eJ&\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u0016\u0010*\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'J\u000e\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\t\u001a\u00060\nR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u000e0\u000e0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u001c\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u000e0\u000e0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u001f\u0010\u001e\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u000e0\u000e0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/PermissionAppsViewModel;", "Landroidx/lifecycle/ViewModel;", "state", "Landroidx/lifecycle/SavedStateHandle;", "app", "Landroid/app/Application;", "groupName", "", "(Landroidx/lifecycle/SavedStateHandle;Landroid/app/Application;Ljava/lang/String;)V", "categorizedAppsLiveData", "Lcom/android/permissioncontroller/permission/ui/model/PermissionAppsViewModel$CategorizedAppsLiveData;", "getCategorizedAppsLiveData", "()Lcom/android/permissioncontroller/permission/ui/model/PermissionAppsViewModel$CategorizedAppsLiveData;", "value", "", PermissionAppsViewModel.CREATION_LOGGED_KEY, "getCreationLogged", "()Z", "setCreationLogged", "(Z)V", "hasSystemAppsLiveData", "Landroidx/lifecycle/MutableLiveData;", "kotlin.jvm.PlatformType", "getHasSystemAppsLiveData", "()Landroidx/lifecycle/MutableLiveData;", "packagesWithFullFileAccess", "", "Lcom/android/permissioncontroller/permission/data/FullStoragePermissionAppsLiveData$FullStoragePackageState;", "shouldShowSystemLiveData", "getShouldShowSystemLiveData", "showAllowAlwaysStringLiveData", "getShowAllowAlwaysStringLiveData", "arePackagesLoaded", "navigateToAppPermission", "", "fragment", "Landroidx/fragment/app/Fragment;", "packageName", "user", "Landroid/os/UserHandle;", "args", "Landroid/os/Bundle;", "packageHasFullStorage", "updateShowSystem", PermissionAppsViewModel.SHOULD_SHOW_SYSTEM_KEY, "CategorizedAppsLiveData", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermissionAppsViewModel extends ViewModel {
    public static final String CREATION_LOGGED_KEY = "creationLogged";
    public static final String HAS_SYSTEM_APPS_KEY = "hasSystem";
    public static final String SHOULD_SHOW_SYSTEM_KEY = "showSystem";
    public static final String SHOW_ALWAYS_ALLOWED = "showAlways";
    private final Application app;
    private final CategorizedAppsLiveData categorizedAppsLiveData;
    private final String groupName;
    private final MutableLiveData<Boolean> hasSystemAppsLiveData;
    private List<FullStoragePermissionAppsLiveData.FullStoragePackageState> packagesWithFullFileAccess;
    private final MutableLiveData<Boolean> shouldShowSystemLiveData;
    private final MutableLiveData<Boolean> showAllowAlwaysStringLiveData;
    private final SavedStateHandle state;

    public PermissionAppsViewModel(SavedStateHandle state, Application app, String groupName) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        this.state = state;
        this.app = app;
        this.groupName = groupName;
        MutableLiveData<Boolean> liveData = state.getLiveData(SHOULD_SHOW_SYSTEM_KEY, false);
        Intrinsics.checkExpressionValueIsNotNull(liveData, "state.getLiveData(SHOULD_SHOW_SYSTEM_KEY, false)");
        this.shouldShowSystemLiveData = liveData;
        MutableLiveData<Boolean> liveData2 = this.state.getLiveData(HAS_SYSTEM_APPS_KEY, true);
        Intrinsics.checkExpressionValueIsNotNull(liveData2, "state.getLiveData(HAS_SYSTEM_APPS_KEY, true)");
        this.hasSystemAppsLiveData = liveData2;
        MutableLiveData<Boolean> liveData3 = this.state.getLiveData(SHOW_ALWAYS_ALLOWED, false);
        Intrinsics.checkExpressionValueIsNotNull(liveData3, "state.getLiveData(SHOW_ALWAYS_ALLOWED, false)");
        this.showAllowAlwaysStringLiveData = liveData3;
        this.categorizedAppsLiveData = new CategorizedAppsLiveData(this, this.groupName);
        this.packagesWithFullFileAccess = CollectionsKt.emptyList();
    }

    public final MutableLiveData<Boolean> getShouldShowSystemLiveData() {
        return this.shouldShowSystemLiveData;
    }

    public final MutableLiveData<Boolean> getHasSystemAppsLiveData() {
        return this.hasSystemAppsLiveData;
    }

    public final MutableLiveData<Boolean> getShowAllowAlwaysStringLiveData() {
        return this.showAllowAlwaysStringLiveData;
    }

    public final CategorizedAppsLiveData getCategorizedAppsLiveData() {
        return this.categorizedAppsLiveData;
    }

    public final void updateShowSystem(boolean showSystem) {
        if (!Intrinsics.areEqual(Boolean.valueOf(showSystem), this.state.get(SHOULD_SHOW_SYSTEM_KEY))) {
            this.state.set(SHOULD_SHOW_SYSTEM_KEY, Boolean.valueOf(showSystem));
        }
    }

    public final boolean getCreationLogged() {
        Boolean bool = (Boolean) this.state.get(CREATION_LOGGED_KEY);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final void setCreationLogged(boolean value) {
        this.state.set(CREATION_LOGGED_KEY, Boolean.valueOf(value));
    }

    /* JADX INFO: compiled from: PermissionAppsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002+\u0012'\u0012%\u0012\u0004\u0012\u00020\u0003\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u00040\u0002¢\u0006\u0002\b\b0\u0001B\r\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/PermissionAppsViewModel$CategorizedAppsLiveData;", "Landroidx/lifecycle/MediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/ui/Category;", "", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "Lkotlin/jvm/JvmSuppressWildcards;", "groupName", "(Lcom/android/permissioncontroller/permission/ui/model/PermissionAppsViewModel;Ljava/lang/String;)V", "packagesUiInfoLiveData", "Lcom/android/permissioncontroller/permission/data/SinglePermGroupPackagesUiInfoLiveData;", "update", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public final class CategorizedAppsLiveData extends MediatorLiveData<Map<Category, List<Pair<String, UserHandle>>>> {
        private final SinglePermGroupPackagesUiInfoLiveData packagesUiInfoLiveData;
        final /* synthetic */ PermissionAppsViewModel this$0;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AppPermGroupUiInfo.PermGrantState.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED.ordinal()] = 1;
                $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_FOREGROUND_ONLY.ordinal()] = 2;
                $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_ALWAYS.ordinal()] = 3;
                $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_DENIED.ordinal()] = 4;
                $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_ASK.ordinal()] = 5;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [T, com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData] */
        /* JADX WARN: Type inference failed for: r1v24, types: [T, com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData] */
        public CategorizedAppsLiveData(PermissionAppsViewModel $outer, String groupName) {
            List listEmptyList;
            Iterable iterable;
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            this.this$0 = $outer;
            this.packagesUiInfoLiveData = SinglePermGroupPackagesUiInfoLiveData.INSTANCE.get(groupName);
            final Ref.ObjectRef fullStorageLiveData = new Ref.ObjectRef();
            fullStorageLiveData.element = (FullStoragePermissionAppsLiveData) 0;
            if (Intrinsics.areEqual(groupName, "android.permission-group.STORAGE")) {
                fullStorageLiveData.element = FullStoragePermissionAppsLiveData.INSTANCE;
                addSource(FullStoragePermissionAppsLiveData.INSTANCE, new Observer<List<? extends FullStoragePermissionAppsLiveData.FullStoragePackageState>>() { // from class: com.android.permissioncontroller.permission.ui.model.PermissionAppsViewModel.CategorizedAppsLiveData.1
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(List<? extends FullStoragePermissionAppsLiveData.FullStoragePackageState> list) {
                        onChanged2((List<FullStoragePermissionAppsLiveData.FullStoragePackageState>) list);
                    }

                    /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                    public final void onChanged2(List<FullStoragePermissionAppsLiveData.FullStoragePackageState> fullAccessPackages) {
                        if (!Intrinsics.areEqual(fullAccessPackages, CategorizedAppsLiveData.this.this$0.packagesWithFullFileAccess)) {
                            PermissionAppsViewModel permissionAppsViewModel = CategorizedAppsLiveData.this.this$0;
                            Intrinsics.checkExpressionValueIsNotNull(fullAccessPackages, "fullAccessPackages");
                            List<FullStoragePermissionAppsLiveData.FullStoragePackageState> $this$filter$iv = fullAccessPackages;
                            Collection destination$iv$iv = new ArrayList();
                            for (Object element$iv$iv : $this$filter$iv) {
                                FullStoragePermissionAppsLiveData.FullStoragePackageState it = (FullStoragePermissionAppsLiveData.FullStoragePackageState) element$iv$iv;
                                if (it.isGranted()) {
                                    destination$iv$iv.add(element$iv$iv);
                                }
                            }
                            permissionAppsViewModel.packagesWithFullFileAccess = (List) destination$iv$iv;
                            if (CategorizedAppsLiveData.this.packagesUiInfoLiveData.getIsInitialized()) {
                                CategorizedAppsLiveData.this.update();
                            }
                        }
                    }
                });
            }
            addSource(this.packagesUiInfoLiveData, new Observer<Map<Pair<? extends String, ? extends UserHandle>, ? extends AppPermGroupUiInfo>>() { // from class: com.android.permissioncontroller.permission.ui.model.PermissionAppsViewModel.CategorizedAppsLiveData.2
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Map<Pair<? extends String, ? extends UserHandle>, ? extends AppPermGroupUiInfo> map) {
                    onChanged2((Map<Pair<String, UserHandle>, AppPermGroupUiInfo>) map);
                }

                /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                public final void onChanged2(Map<Pair<String, UserHandle>, AppPermGroupUiInfo> map) {
                    if (((FullStoragePermissionAppsLiveData) fullStorageLiveData.element) == null || ((FullStoragePermissionAppsLiveData) fullStorageLiveData.element).getIsInitialized()) {
                        CategorizedAppsLiveData.this.update();
                    }
                }
            });
            addSource($outer.getShouldShowSystemLiveData(), new Observer<Boolean>() { // from class: com.android.permissioncontroller.permission.ui.model.PermissionAppsViewModel.CategorizedAppsLiveData.3
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Boolean bool) {
                    if (((FullStoragePermissionAppsLiveData) fullStorageLiveData.element) == null || ((FullStoragePermissionAppsLiveData) fullStorageLiveData.element).getIsInitialized()) {
                        CategorizedAppsLiveData.this.update();
                    }
                }
            });
            if ((((FullStoragePermissionAppsLiveData) fullStorageLiveData.element) == null || ((FullStoragePermissionAppsLiveData) fullStorageLiveData.element).getIsInitialized()) && this.packagesUiInfoLiveData.getIsInitialized()) {
                FullStoragePermissionAppsLiveData fullStoragePermissionAppsLiveData = (FullStoragePermissionAppsLiveData) fullStorageLiveData.element;
                if (fullStoragePermissionAppsLiveData == null || (iterable = (List) fullStoragePermissionAppsLiveData.getValue()) == null) {
                    listEmptyList = CollectionsKt.emptyList();
                } else {
                    Iterable $this$filter$iv = iterable;
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        FullStoragePermissionAppsLiveData.FullStoragePackageState it = (FullStoragePermissionAppsLiveData.FullStoragePackageState) element$iv$iv;
                        if (it.isGranted()) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                    listEmptyList = (List) destination$iv$iv;
                }
                $outer.packagesWithFullFileAccess = listEmptyList;
                update();
            }
        }

        public final void update() {
            boolean hasSystem;
            Category category;
            boolean z;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Boolean bool = (Boolean) this.this$0.state.get(PermissionAppsViewModel.SHOULD_SHOW_SYSTEM_KEY);
            boolean showSystem = bool != null ? bool.booleanValue() : false;
            linkedHashMap.put(Category.ALLOWED, new ArrayList());
            linkedHashMap.put(Category.ALLOWED_FOREGROUND, new ArrayList());
            linkedHashMap.put(Category.ASK, new ArrayList());
            linkedHashMap.put(Category.DENIED, new ArrayList());
            Map<Pair<? extends String, ? extends UserHandle>, ? extends AppPermGroupUiInfo> value = this.packagesUiInfoLiveData.getValue();
            if (value == null) {
                CategorizedAppsLiveData $this$run = this;
                if ($this$run.packagesUiInfoLiveData.getIsInitialized()) {
                    $this$run.setValue(linkedHashMap);
                    return;
                }
                return;
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "packagesUiInfoLiveData.v…     return\n            }");
            int i = 1;
            if (!value.isEmpty()) {
                Iterator<Map.Entry<Pair<? extends String, ? extends UserHandle>, ? extends AppPermGroupUiInfo>> it = value.entrySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        Map.Entry<Pair<? extends String, ? extends UserHandle>, ? extends AppPermGroupUiInfo> next = it.next();
                        if (next.getValue().isSystem() && next.getValue().getShouldShow()) {
                            hasSystem = true;
                            break;
                        }
                    } else {
                        hasSystem = false;
                        break;
                    }
                }
            } else {
                hasSystem = false;
            }
            if (!Intrinsics.areEqual(Boolean.valueOf(hasSystem), this.this$0.state.get(PermissionAppsViewModel.HAS_SYSTEM_APPS_KEY))) {
                this.this$0.state.set(PermissionAppsViewModel.HAS_SYSTEM_APPS_KEY, Boolean.valueOf(hasSystem));
            }
            boolean showAlwaysAllowedString = false;
            for (Map.Entry<Pair<? extends String, ? extends UserHandle>, ? extends AppPermGroupUiInfo> entry : value.entrySet()) {
                Pair<? extends String, ? extends UserHandle> key = entry.getKey();
                AppPermGroupUiInfo uiInfo = entry.getValue();
                if (uiInfo.getShouldShow() && (!uiInfo.isSystem() || showSystem)) {
                    if (uiInfo.getPermGrantState() == AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_ALWAYS || uiInfo.getPermGrantState() == AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_FOREGROUND_ONLY) {
                        showAlwaysAllowedString = true;
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[uiInfo.getPermGrantState().ordinal()];
                    if (i2 == i) {
                        category = Category.ALLOWED;
                    } else if (i2 == 2) {
                        category = Category.ALLOWED_FOREGROUND;
                    } else if (i2 == 3) {
                        category = Category.ALLOWED;
                    } else if (i2 == 4) {
                        category = Category.DENIED;
                    } else {
                        if (i2 != 5) {
                            throw new NoWhenBranchMatchedException();
                        }
                        category = Category.ASK;
                    }
                    if (Intrinsics.areEqual(this.this$0.groupName, "android.permission-group.STORAGE")) {
                        Iterable $this$any$iv = this.this$0.packagesWithFullFileAccess;
                        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                            Iterator it2 = $this$any$iv.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    Object element$iv = it2.next();
                                    FullStoragePermissionAppsLiveData.FullStoragePackageState it3 = (FullStoragePermissionAppsLiveData.FullStoragePackageState) element$iv;
                                    if (!it3.isLegacy() && it3.isGranted() && Intrinsics.areEqual(TuplesKt.to(it3.getPackageName(), it3.getUser()), key)) {
                                        z = true;
                                        break;
                                    }
                                } else {
                                    z = false;
                                    break;
                                }
                            }
                        } else {
                            z = false;
                        }
                        if (z) {
                            category = Category.ALLOWED;
                        }
                    }
                    Object obj = linkedHashMap.get(category);
                    if (obj == null) {
                        Intrinsics.throwNpe();
                    }
                    ((List) obj).add(key);
                }
                i = 1;
            }
            this.this$0.getShowAllowAlwaysStringLiveData().setValue(Boolean.valueOf(showAlwaysAllowedString));
            setValue(linkedHashMap);
        }
    }

    public final boolean packageHasFullStorage(String packageName, UserHandle user) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Iterable $this$any$iv = this.packagesWithFullFileAccess;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            FullStoragePermissionAppsLiveData.FullStoragePackageState it = (FullStoragePermissionAppsLiveData.FullStoragePackageState) element$iv;
            if (Intrinsics.areEqual(it.getPackageName(), packageName) && Intrinsics.areEqual(it.getUser(), user)) {
                return true;
            }
        }
        return false;
    }

    public final boolean arePackagesLoaded() {
        return AllPackageInfosLiveData.INSTANCE.getIsInitialized();
    }

    public final void navigateToAppPermission(Fragment fragment, String packageName, UserHandle user, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(args, "args");
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "fragment.activity!!");
        if (LocationUtils.isLocationGroupAndProvider(activity, this.groupName, packageName)) {
            Intent intent = new Intent(activity, (Class<?>) LocationProviderInterceptDialog.class);
            intent.putExtra("android.intent.extra.PACKAGE_NAME", packageName);
            activity.startActivityAsUser(intent, user);
        } else if (LocationUtils.isLocationGroupAndControllerExtraPackage(activity, this.groupName, packageName)) {
            LocationUtils.startLocationControllerExtraPackageSettings(activity, user);
        } else {
            KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.perm_apps_to_app, args);
        }
    }
}
