package com.android.permissioncontroller.permission.service;

import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Process;
import android.os.UserHandle;
import androidx.core.app.NotificationCompat;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerProto;
import com.android.permissioncontroller.permission.data.AppPermGroupUiInfoLiveData;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.PackagePermissionsLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.data.UserPackageInfosLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntConsumer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: PermissionControllerServiceModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J?\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u00072#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u0001H\n¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\t0\rJ$\u0010\u0011\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0011\u0010\u0019\u001a\u00020\u001aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ.\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u00142\u001e\u0010\u0017\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020 0\u001f0\u00130\u001eJ6\u0010!\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u0013H\u0002J\u0006\u0010$\u001a\u00020\tR\u0018\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lcom/android/permissioncontroller/permission/service/PermissionControllerServiceModel;", "", NotificationCompat.CATEGORY_SERVICE, "Lcom/android/permissioncontroller/permission/service/PermissionControllerServiceImpl;", "(Lcom/android/permissioncontroller/permission/service/PermissionControllerServiceImpl;)V", "observedLiveDatas", "", "Landroidx/lifecycle/LiveData;", "observeAndCheckForLifecycleState", "", "T", "liveData", "onChangedFun", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "t", "onCountPermissionAppsLiveData", "permissionNames", "", "", "flags", "", "callback", "Ljava/util/function/IntConsumer;", "onDump", "Lcom/android/permissioncontroller/PermissionControllerProto$PermissionControllerDumpProto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onGetAppPermissions", "packageName", "Landroidx/core/util/Consumer;", "Lkotlin/Pair;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo;", "onPackagesLoadedForCountPermissionApps", "packageInfos", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "removeObservers", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermissionControllerServiceModel {
    private final List<LiveData<?>> observedLiveDatas;
    private final PermissionControllerServiceImpl service;

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PermissionControllerServiceModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086@"}, d2 = {"onDump", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/android/permissioncontroller/PermissionControllerProto$PermissionControllerDumpProto;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.PermissionControllerServiceModel", f = "PermissionControllerServiceModel.kt", i = {0}, l = {284}, m = "onDump", n = {"this"}, s = {"L$0"})
    static final class C00501 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00501(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PermissionControllerServiceModel.this.onDump(this);
        }
    }

    public PermissionControllerServiceModel(PermissionControllerServiceImpl service) {
        Intrinsics.checkParameterIsNotNull(service, "service");
        this.service = service;
        this.observedLiveDatas = new ArrayList();
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$observeAndCheckForLifecycleState$1, reason: invalid class name */
    /* JADX INFO: compiled from: PermissionControllerServiceModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$observeAndCheckForLifecycleState$1", f = "PermissionControllerServiceModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LiveData $liveData;
        final /* synthetic */ Function1 $onChangedFun;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LiveData liveData, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$liveData = liveData;
            this.$onChangedFun = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = PermissionControllerServiceModel.this.new AnonymousClass1(this.$liveData, this.$onChangedFun, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            Lifecycle lifecycle = PermissionControllerServiceModel.this.service.getLifecycle();
            Intrinsics.checkExpressionValueIsNotNull(lifecycle, "service.lifecycle");
            if (lifecycle.getCurrentState() != Lifecycle.State.STARTED) {
                PermissionControllerServiceModel.this.service.setLifecycleToStarted();
            }
            if (!this.$liveData.hasActiveObservers()) {
                PermissionControllerServiceModel.this.observedLiveDatas.add(this.$liveData);
                this.$liveData.observe(PermissionControllerServiceModel.this.service, new Observer<T>() { // from class: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.observeAndCheckForLifecycleState.1.1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t) {
                    }
                });
            }
            final Ref.BooleanRef updated = new Ref.BooleanRef();
            updated.element = false;
            Observer observer = new Observer<T>() { // from class: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$observeAndCheckForLifecycleState$1$observer$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(T data) {
                    if (updated.element) {
                        return;
                    }
                    if (((this.this$0.$liveData instanceof SmartUpdateMediatorLiveData) && !((SmartUpdateMediatorLiveData) this.this$0.$liveData).getIsStale()) || !(this.this$0.$liveData instanceof SmartUpdateMediatorLiveData)) {
                        this.this$0.$onChangedFun.invoke(data);
                        this.this$0.$liveData.removeObserver(this);
                        updated.element = true;
                    }
                }
            };
            LiveData liveData = this.$liveData;
            if (liveData instanceof SmartUpdateMediatorLiveData) {
                ((SmartUpdateMediatorLiveData) liveData).observeStale(PermissionControllerServiceModel.this.service, observer);
            } else {
                liveData.observe(PermissionControllerServiceModel.this.service, observer);
            }
            return Unit.INSTANCE;
        }
    }

    public final <T> void observeAndCheckForLifecycleState(LiveData<T> liveData, Function1<? super T, Unit> onChangedFun) {
        Intrinsics.checkParameterIsNotNull(liveData, "liveData");
        Intrinsics.checkParameterIsNotNull(onChangedFun, "onChangedFun");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass1(liveData, onChangedFun, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$removeObservers$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PermissionControllerServiceModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$removeObservers$1", f = "PermissionControllerServiceModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C00531(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00531 c00531 = PermissionControllerServiceModel.this.new C00531(completion);
            c00531.p$ = (CoroutineScope) obj;
            return c00531;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            for (LiveData liveData : PermissionControllerServiceModel.this.observedLiveDatas) {
                liveData.removeObservers(PermissionControllerServiceModel.this.service);
            }
            PermissionControllerServiceModel.this.observedLiveDatas.clear();
            return Unit.INSTANCE;
        }
    }

    public final void removeObservers() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00531(null), 2, null);
    }

    public final void onCountPermissionAppsLiveData(final List<String> permissionNames, final int flags, final IntConsumer callback) {
        Intrinsics.checkParameterIsNotNull(permissionNames, "permissionNames");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        UserPackageInfosLiveData.Companion companion = UserPackageInfosLiveData.INSTANCE;
        UserHandle userHandleMyUserHandle = Process.myUserHandle();
        Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle, "Process.myUserHandle()");
        UserPackageInfosLiveData packageInfosLiveData = companion.get(userHandleMyUserHandle);
        observeAndCheckForLifecycleState(packageInfosLiveData, new Function1<List<LightPackageInfo>, Unit>() { // from class: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.onCountPermissionAppsLiveData.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<LightPackageInfo> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<LightPackageInfo> list) {
                PermissionControllerServiceModel.this.onPackagesLoadedForCountPermissionApps(permissionNames, flags, callback, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPackagesLoadedForCountPermissionApps(List<String> permissionNames, int flags, final IntConsumer callback, List<LightPackageInfo> packageInfos) {
        int i;
        Iterator<LightPackageInfo> it;
        if (packageInfos == null) {
            callback.accept(0);
            return;
        }
        final boolean countSystem = (flags & 2) != 0;
        final boolean countOnlyGranted = (flags & 1) != 0;
        Map permToGroup = new LinkedHashMap();
        for (String permName : permissionNames) {
            try {
                PermissionInfo permInfo = this.service.getPackageManager().getPermissionInfo(permName, 0);
                if (Utils.isPermissionDangerousInstalledNotRemoved(permInfo)) {
                    permToGroup.put(permName, Utils.getGroupOfPermission(permInfo));
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        List<Set> uiLiveDatasPerPackage = new ArrayList();
        final Ref.IntRef numLiveDatas = new Ref.IntRef();
        numLiveDatas.element = 0;
        Iterator<LightPackageInfo> it2 = packageInfos.iterator();
        while (it2.hasNext()) {
            LightPackageInfo next = it2.next();
            String packageName = next.getPackageName();
            List<String> listComponent3 = next.component3();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (String permName2 : permToGroup.keySet()) {
                if (!listComponent3.contains(permName2)) {
                    it = it2;
                } else {
                    AppPermGroupUiInfoLiveData.Companion companion = AppPermGroupUiInfoLiveData.INSTANCE;
                    Object obj = permToGroup.get(permName2);
                    if (obj == null) {
                        Intrinsics.throwNpe();
                    }
                    UserHandle userHandleMyUserHandle = Process.myUserHandle();
                    it = it2;
                    Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle, "Process.myUserHandle()");
                    linkedHashSet.add(DataRepositoryKt.get(companion, packageName, obj, userHandleMyUserHandle));
                }
                it2 = it;
            }
            Iterator<LightPackageInfo> it3 = it2;
            if (!linkedHashSet.isEmpty()) {
                uiLiveDatasPerPackage.add(linkedHashSet);
                numLiveDatas.element += linkedHashSet.size();
            }
            it2 = it3;
        }
        if (numLiveDatas.element != 0) {
            i = 0;
        } else {
            i = 0;
            callback.accept(0);
        }
        final Ref.IntRef packagesWithPermission = new Ref.IntRef();
        packagesWithPermission.element = i;
        final Ref.IntRef numPermAppsChecked = new Ref.IntRef();
        numPermAppsChecked.element = i;
        for (Set<AppPermGroupUiInfoLiveData> packageUiInfoLiveDatas : uiLiveDatasPerPackage) {
            final Ref.BooleanRef packageAdded = new Ref.BooleanRef();
            packageAdded.element = false;
            for (AppPermGroupUiInfoLiveData packageUiInfoLiveData : packageUiInfoLiveDatas) {
                observeAndCheckForLifecycleState(packageUiInfoLiveData, new Function1<AppPermGroupUiInfo, Unit>() { // from class: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.onPackagesLoadedForCountPermissionApps.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AppPermGroupUiInfo appPermGroupUiInfo) {
                        invoke2(appPermGroupUiInfo);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AppPermGroupUiInfo uiInfo) {
                        numPermAppsChecked.element++;
                        if (uiInfo != null && uiInfo.getShouldShow() && (!uiInfo.isSystem() || countSystem)) {
                            boolean granted = (uiInfo.getPermGrantState() == AppPermGroupUiInfo.PermGrantState.PERMS_DENIED || uiInfo.getPermGrantState() == AppPermGroupUiInfo.PermGrantState.PERMS_ASK) ? false : true;
                            if (granted || (!countOnlyGranted && !packageAdded.element)) {
                                packageAdded.element = true;
                                packagesWithPermission.element++;
                            }
                        }
                        if (numPermAppsChecked.element == numLiveDatas.element) {
                            callback.accept(packagesWithPermission.element);
                        }
                    }
                });
            }
        }
    }

    public final void onGetAppPermissions(final String packageName, final Consumer<List<Pair<String, AppPermGroupUiInfo>>> callback) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        PackagePermissionsLiveData.Companion companion = PackagePermissionsLiveData.INSTANCE;
        UserHandle userHandleMyUserHandle = Process.myUserHandle();
        Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle, "Process.myUserHandle()");
        PackagePermissionsLiveData packageGroupsLiveData = (PackagePermissionsLiveData) DataRepositoryKt.get(companion, packageName, userHandleMyUserHandle);
        observeAndCheckForLifecycleState(packageGroupsLiveData, new Function1<Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.onGetAppPermissions.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends List<? extends String>> map) {
                invoke2((Map<String, ? extends List<String>>) map);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Map<String, ? extends List<String>> map) {
                List<String> groupNames;
                Set<String> setKeySet;
                if (map == null || (setKeySet = map.keySet()) == null || (groupNames = CollectionsKt.toMutableList((Collection) setKeySet)) == null) {
                    groupNames = new ArrayList();
                }
                groupNames.remove(PackagePermissionsLiveData.NON_RUNTIME_NORMAL_PERMS);
                final ArrayList arrayList = new ArrayList();
                if (groupNames.isEmpty()) {
                    callback.accept(arrayList);
                }
                final Ref.IntRef numLiveDatasUpdated = new Ref.IntRef();
                numLiveDatasUpdated.element = 0;
                for (final String groupName : groupNames) {
                    AppPermGroupUiInfoLiveData.Companion companion2 = AppPermGroupUiInfoLiveData.INSTANCE;
                    String str = packageName;
                    UserHandle userHandleMyUserHandle2 = Process.myUserHandle();
                    Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle2, "Process.myUserHandle()");
                    AppPermGroupUiInfoLiveData uiInfoLiveData = (AppPermGroupUiInfoLiveData) DataRepositoryKt.get(companion2, str, groupName, userHandleMyUserHandle2);
                    final List list = groupNames;
                    PermissionControllerServiceModel.this.observeAndCheckForLifecycleState(uiInfoLiveData, new Function1<AppPermGroupUiInfo, Unit>() { // from class: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.onGetAppPermissions.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AppPermGroupUiInfo appPermGroupUiInfo) {
                            invoke2(appPermGroupUiInfo);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AppPermGroupUiInfo uiInfo) {
                            numLiveDatasUpdated.element++;
                            if (uiInfo != null && uiInfo.getShouldShow()) {
                                arrayList.add(TuplesKt.to(groupName, uiInfo));
                            }
                            if (numLiveDatasUpdated.element == list.size()) {
                                callback.accept(arrayList);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object onDump(kotlin.coroutines.Continuation<? super com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProto> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.C00501
            if (r0 == 0) goto L14
            r0 = r9
            com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$1 r0 = (com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.C00501) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$1 r0 = new com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            if (r3 == 0) goto L37
            if (r3 != r4) goto L2f
            java.lang.Object r2 = r0.L$0
            com.android.permissioncontroller.permission.service.PermissionControllerServiceModel r2 = (com.android.permissioncontroller.permission.service.PermissionControllerServiceModel) r2
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L50
        L2f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L37:
            kotlin.ResultKt.throwOnFailure(r1)
            r5 = 9000(0x2328, double:4.4466E-320)
            com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$2 r3 = new com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$2
            r7 = 0
            r3.<init>(r7)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r0.L$0 = r8
            r0.label = r4
            java.lang.Object r3 = kotlinx.coroutines.TimeoutKt.withTimeout(r5, r3, r0)
            if (r3 != r2) goto L4f
            return r2
        L4f:
            r2 = r8
        L50:
            java.lang.String r4 = "withTimeout(9000) {\n    …       .build()\n        }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel.onDump(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$2, reason: invalid class name */
    /* JADX INFO: compiled from: PermissionControllerServiceModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Lcom/android/permissioncontroller/PermissionControllerProto$PermissionControllerDumpProto;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$2", f = "PermissionControllerServiceModel.kt", i = {0, 0, 0, 1, 1, 1}, l = {289, 290}, m = "invokeSuspend", n = {"$this$withTimeout", "autoRevokeDump", "dumpedLogs", "$this$withTimeout", "autoRevokeDump", "dumpedLogs"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PermissionControllerProto.PermissionControllerDumpProto>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        private CoroutineScope p$;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = PermissionControllerServiceModel.this.new AnonymousClass2(completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PermissionControllerProto.PermissionControllerDumpProto> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            CoroutineScope $this$withTimeout;
            PermissionControllerProto.PermissionControllerDumpProto.Builder builderNewBuilder;
            Object obj;
            Deferred dumpedLogs;
            Deferred autoRevokeDump;
            Object objAwait;
            PermissionControllerProto.PermissionControllerDumpProto.Builder builder;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                $this$withTimeout = this.p$;
                Deferred autoRevokeDump2 = BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new PermissionControllerServiceModel$onDump$2$autoRevokeDump$1(this, null), 2, null);
                Deferred dumpedLogs2 = BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new PermissionControllerServiceModel$onDump$2$dumpedLogs$1(null), 2, null);
                builderNewBuilder = PermissionControllerProto.PermissionControllerDumpProto.newBuilder();
                this.L$0 = $this$withTimeout;
                this.L$1 = autoRevokeDump2;
                this.L$2 = dumpedLogs2;
                this.L$3 = builderNewBuilder;
                this.label = 1;
                Object objAwait2 = autoRevokeDump2.await(this);
                if (objAwait2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objAwait2;
                dumpedLogs = dumpedLogs2;
                autoRevokeDump = autoRevokeDump2;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    builder = (PermissionControllerProto.PermissionControllerDumpProto.Builder) this.L$3;
                    ResultKt.throwOnFailure($result);
                    objAwait = $result;
                    return builder.addAllLogs((Iterable) objAwait).build();
                }
                PermissionControllerProto.PermissionControllerDumpProto.Builder builder2 = (PermissionControllerProto.PermissionControllerDumpProto.Builder) this.L$3;
                dumpedLogs = (Deferred) this.L$2;
                autoRevokeDump = (Deferred) this.L$1;
                $this$withTimeout = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure($result);
                builderNewBuilder = builder2;
                obj = $result;
            }
            PermissionControllerProto.PermissionControllerDumpProto.Builder autoRevoke = builderNewBuilder.setAutoRevoke((AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto) obj);
            this.L$0 = $this$withTimeout;
            this.L$1 = autoRevokeDump;
            this.L$2 = dumpedLogs;
            this.L$3 = autoRevoke;
            this.label = 2;
            objAwait = dumpedLogs.await(this);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
            builder = autoRevoke;
            return builder.addAllLogs((Iterable) objAwait).build();
        }
    }
}
