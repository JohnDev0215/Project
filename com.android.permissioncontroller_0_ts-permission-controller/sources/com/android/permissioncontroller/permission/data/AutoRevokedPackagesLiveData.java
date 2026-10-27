package com.android.permissioncontroller.permission.data;

import android.os.UserHandle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermState;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
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
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: AutoRevokedPackagesLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002&\u0012\"\u0012 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00060\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0014J\b\u0010\u0013\u001a\u00020\u0011H\u0002R,\u0010\b\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\r\u001a \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u000e\u0012\u0004\u0012\u00020\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/android/permissioncontroller/permission/data/AutoRevokedPackagesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "", "()V", "packageAutoRevokedPermsList", "", "", "packagePermGroupsLiveDatas", "Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData;", "permStateLiveDatas", "Lkotlin/Triple;", "Lcom/android/permissioncontroller/permission/data/PermStateLiveData;", "observePermStateLiveDatas", "", "onUpdate", "postCopyOfMap", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokedPackagesLiveData extends SmartUpdateMediatorLiveData<Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>>> {
    public static final AutoRevokedPackagesLiveData INSTANCE;
    private static final Map<Pair<String, UserHandle>, Set<String>> packageAutoRevokedPermsList;
    private static final Map<Pair<String, UserHandle>, PackagePermissionsLiveData> packagePermGroupsLiveDatas;
    private static final Map<Triple<String, String, UserHandle>, PermStateLiveData> permStateLiveDatas;

    static {
        AutoRevokedPackagesLiveData autoRevokedPackagesLiveData = new AutoRevokedPackagesLiveData();
        INSTANCE = autoRevokedPackagesLiveData;
        autoRevokedPackagesLiveData.addSource(AllPackageInfosLiveData.INSTANCE, new Observer<Map<UserHandle, ? extends List<? extends LightPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.data.AutoRevokedPackagesLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends LightPackageInfo>> map) {
                onChanged2((Map<UserHandle, ? extends List<LightPackageInfo>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<UserHandle, ? extends List<LightPackageInfo>> map) {
                AutoRevokedPackagesLiveData.INSTANCE.updateIfActive();
            }
        });
        permStateLiveDatas = new LinkedHashMap();
        packagePermGroupsLiveDatas = new LinkedHashMap();
        packageAutoRevokedPermsList = new LinkedHashMap();
    }

    private AutoRevokedPackagesLiveData() {
    }

    public static final /* synthetic */ Map access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData $this) {
        return packageAutoRevokedPermsList;
    }

    public static final /* synthetic */ Map access$getPackagePermGroupsLiveDatas$p(AutoRevokedPackagesLiveData $this) {
        return packagePermGroupsLiveDatas;
    }

    public static final /* synthetic */ Map access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData $this) {
        return permStateLiveDatas;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        Pair pair;
        if (!AllPackageInfosLiveData.INSTANCE.getIsInitialized()) {
            return;
        }
        List packageNames = new ArrayList();
        Map<UserHandle, ? extends List<? extends LightPackageInfo>> value = AllPackageInfosLiveData.INSTANCE.getValue();
        if (value == null) {
            value = MapsKt.emptyMap();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "AllPackageInfosLiveData.value ?: emptyMap()");
        for (Map.Entry<UserHandle, ? extends List<? extends LightPackageInfo>> entry : value.entrySet()) {
            UserHandle user = entry.getKey();
            Iterable $this$mapNotNull$iv = (List) entry.getValue();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                LightPackageInfo pkg = (LightPackageInfo) element$iv$iv$iv;
                if (pkg.getEnabled()) {
                    pair = TuplesKt.to(pkg.getPackageName(), user);
                } else {
                    pair = null;
                }
                if (pair != null) {
                    destination$iv$iv.add(pair);
                }
            }
            packageNames.addAll((List) destination$iv$iv);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00332(packageNames, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.AutoRevokedPackagesLiveData$onUpdate$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoRevokedPackagesLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.AutoRevokedPackagesLiveData$onUpdate$2", f = "AutoRevokedPackagesLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00332 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List $packageNames;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00332(List list, Continuation continuation) {
            super(2, continuation);
            this.$packageNames = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00332 c00332 = new C00332(this.$packageNames, completion);
            c00332.p$ = (CoroutineScope) obj;
            return c00332;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00332) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            Pair mapAndListDifferences = KotlinUtils.INSTANCE.getMapAndListDifferences(this.$packageNames, AutoRevokedPackagesLiveData.access$getPackagePermGroupsLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE));
            Iterable toAdd = (Set) mapAndListDifferences.component1();
            Set<Pair> toRemove = (Set) mapAndListDifferences.component2();
            for (Pair pkg : toRemove) {
                PackagePermissionsLiveData packagePermissionsLiveData = (PackagePermissionsLiveData) AutoRevokedPackagesLiveData.access$getPackagePermGroupsLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).remove(pkg);
                if (packagePermissionsLiveData != null) {
                    AutoRevokedPackagesLiveData.INSTANCE.removeSource(packagePermissionsLiveData);
                    Map<String, ? extends List<? extends String>> value = packagePermissionsLiveData.getValue();
                    if (value != null) {
                        Intrinsics.checkExpressionValueIsNotNull(value, "packagePermissionsLiveData.value ?: continue");
                        Iterator<Map.Entry<String, ? extends List<? extends String>>> it = value.entrySet().iterator();
                        while (it.hasNext()) {
                            String groupName = it.next().getKey();
                            AutoRevokedPackagesLiveData autoRevokedPackagesLiveData = AutoRevokedPackagesLiveData.INSTANCE;
                            PermStateLiveData permStateLiveData = (PermStateLiveData) AutoRevokedPackagesLiveData.access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).remove(new Triple(pkg.getFirst(), groupName, pkg.getSecond()));
                            if (permStateLiveData != null) {
                                autoRevokedPackagesLiveData.removeSource(permStateLiveData);
                            }
                        }
                        AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE).remove(pkg);
                    }
                }
            }
            if (!toRemove.isEmpty()) {
                AutoRevokedPackagesLiveData.INSTANCE.postCopyOfMap();
            }
            Iterable $this$forEach$iv = toAdd;
            for (Object element$iv : $this$forEach$iv) {
                Pair it2 = (Pair) element$iv;
                AutoRevokedPackagesLiveData.access$getPackagePermGroupsLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).put(it2, PackagePermissionsLiveData.INSTANCE.get(it2));
            }
            Iterable $this$forEach$iv2 = toAdd;
            for (Object element$iv2 : $this$forEach$iv2) {
                Pair userPackage = (Pair) element$iv2;
                AutoRevokedPackagesLiveData autoRevokedPackagesLiveData2 = AutoRevokedPackagesLiveData.INSTANCE;
                Object obj = AutoRevokedPackagesLiveData.access$getPackagePermGroupsLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).get(userPackage);
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                autoRevokedPackagesLiveData2.addSource((LiveData) obj, new Observer<Map<String, ? extends List<? extends String>>>() { // from class: com.android.permissioncontroller.permission.data.AutoRevokedPackagesLiveData$onUpdate$2$2$1
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends List<? extends String>> map) {
                        onChanged2((Map<String, ? extends List<String>>) map);
                    }

                    /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                    public final void onChanged2(Map<String, ? extends List<String>> map) {
                        Map $this$all$iv = AutoRevokedPackagesLiveData.access$getPackagePermGroupsLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE);
                        boolean z = true;
                        if (!$this$all$iv.isEmpty()) {
                            Iterator it3 = $this$all$iv.entrySet().iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    break;
                                }
                                Map.Entry element$iv3 = (Map.Entry) it3.next();
                                if (!((PackagePermissionsLiveData) element$iv3.getValue()).getIsInitialized()) {
                                    z = false;
                                    break;
                                }
                            }
                        }
                        if (z) {
                            AutoRevokedPackagesLiveData.INSTANCE.observePermStateLiveDatas();
                        }
                    }
                });
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void observePermStateLiveDatas() {
        Map<Pair<String, UserHandle>, PackagePermissionsLiveData> map;
        int $i$f$flatMap;
        Map<Pair<String, UserHandle>, PackagePermissionsLiveData> map2;
        int $i$f$flatMapTo;
        Iterator<Map.Entry<Pair<String, UserHandle>, PackagePermissionsLiveData>> it;
        Collection destination$iv$iv;
        Set<String> setKeySet;
        Set permGroups;
        List packageGroups = new ArrayList();
        Map<Pair<String, UserHandle>, PackagePermissionsLiveData> map3 = packagePermGroupsLiveDatas;
        int $i$f$flatMap2 = 0;
        Collection destination$iv$iv2 = new ArrayList();
        Map<Pair<String, UserHandle>, PackagePermissionsLiveData> map4 = map3;
        int $i$f$flatMapTo2 = 0;
        Iterator<Map.Entry<Pair<String, UserHandle>, PackagePermissionsLiveData>> it2 = map4.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<Pair<String, UserHandle>, PackagePermissionsLiveData> next = it2.next();
            Pair<String, UserHandle> key = next.getKey();
            PackagePermissionsLiveData liveData = next.getValue();
            Map<String, ? extends List<? extends String>> value = liveData.getValue();
            if (value != null && (setKeySet = value.keySet()) != null && (permGroups = CollectionsKt.toMutableSet(setKeySet)) != null) {
                permGroups.remove(PackagePermissionsLiveData.NON_RUNTIME_NORMAL_PERMS);
                Set $this$map$iv = permGroups;
                map = map3;
                Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    int $i$f$flatMap3 = $i$f$flatMap2;
                    String it3 = (String) item$iv$iv;
                    destination$iv$iv3.add(new Triple(key.getFirst(), it3, key.getSecond()));
                    $i$f$flatMap2 = $i$f$flatMap3;
                    map4 = map4;
                    $i$f$flatMapTo2 = $i$f$flatMapTo2;
                    it2 = it2;
                }
                $i$f$flatMap = $i$f$flatMap2;
                map2 = map4;
                $i$f$flatMapTo = $i$f$flatMapTo2;
                it = it2;
                destination$iv$iv = (List) destination$iv$iv3;
            } else {
                map = map3;
                $i$f$flatMap = $i$f$flatMap2;
                map2 = map4;
                $i$f$flatMapTo = $i$f$flatMapTo2;
                it = it2;
                destination$iv$iv = CollectionsKt.emptyList();
            }
            Collection list$iv$iv = destination$iv$iv;
            CollectionsKt.addAll(destination$iv$iv2, list$iv$iv);
            map3 = map;
            $i$f$flatMap2 = $i$f$flatMap;
            map4 = map2;
            $i$f$flatMapTo2 = $i$f$flatMapTo;
            it2 = it;
        }
        packageGroups.addAll((List) destination$iv$iv2);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass2(packageGroups, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.AutoRevokedPackagesLiveData$observePermStateLiveDatas$2, reason: invalid class name */
    /* JADX INFO: compiled from: AutoRevokedPackagesLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.AutoRevokedPackagesLiveData$observePermStateLiveDatas$2", f = "AutoRevokedPackagesLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List $packageGroups;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List list, Continuation continuation) {
            super(2, continuation);
            this.$packageGroups = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$packageGroups, completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            Pair mapAndListDifferences = KotlinUtils.INSTANCE.getMapAndListDifferences(this.$packageGroups, AutoRevokedPackagesLiveData.access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE));
            Set<Triple> toAdd = (Set) mapAndListDifferences.component1();
            Set<Triple> toRemove = (Set) mapAndListDifferences.component2();
            for (Triple packagePermGroup : toRemove) {
                AutoRevokedPackagesLiveData autoRevokedPackagesLiveData = AutoRevokedPackagesLiveData.INSTANCE;
                PermStateLiveData permStateLiveData = (PermStateLiveData) AutoRevokedPackagesLiveData.access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).remove(packagePermGroup);
                if (permStateLiveData != null) {
                    autoRevokedPackagesLiveData.removeSource(permStateLiveData);
                    Pair packageUser = TuplesKt.to(packagePermGroup.getFirst(), packagePermGroup.getThird());
                    Set set = (Set) AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE).get(packageUser);
                    if (set != null) {
                        Boxing.boxBoolean(set.remove(packagePermGroup.getSecond()));
                    }
                    Set set2 = (Set) AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE).get(packageUser);
                    if (set2 != null && set2.isEmpty()) {
                        AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE).remove(packageUser);
                    }
                }
            }
            if (!toRemove.isEmpty()) {
                AutoRevokedPackagesLiveData.INSTANCE.postCopyOfMap();
            }
            for (Triple packagePermGroup2 : toAdd) {
                AutoRevokedPackagesLiveData.access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).put(packagePermGroup2, PermStateLiveData.INSTANCE.get(packagePermGroup2));
            }
            for (final Triple packagePermGroup3 : toAdd) {
                Object obj = AutoRevokedPackagesLiveData.access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).get(packagePermGroup3);
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                final PermStateLiveData permStateLiveData2 = (PermStateLiveData) obj;
                final Pair packageUser2 = TuplesKt.to(packagePermGroup3.getFirst(), packagePermGroup3.getThird());
                AutoRevokedPackagesLiveData.INSTANCE.addSource(permStateLiveData2, new Observer<Map<String, ? extends PermState>>() { // from class: com.android.permissioncontroller.permission.data.AutoRevokedPackagesLiveData.observePermStateLiveDatas.2.1
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends PermState> map) {
                        onChanged2((Map<String, PermState>) map);
                    }

                    /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                    public final void onChanged2(Map<String, PermState> map) {
                        boolean added = false;
                        if (map == null && permStateLiveData2.getIsInitialized()) {
                            AutoRevokedPackagesLiveData.access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE).remove(packagePermGroup3);
                            AutoRevokedPackagesLiveData.INSTANCE.removeSource(permStateLiveData2);
                        } else if (map != null) {
                            Iterator<Map.Entry<String, PermState>> it = map.entrySet().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                PermState state = it.next().getValue();
                                if ((state.getPermFlags() & 131072) != 0) {
                                    Map $this$getOrPut$iv = AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE);
                                    Pair pair = packageUser2;
                                    Object value$iv = $this$getOrPut$iv.get(pair);
                                    if (value$iv == null) {
                                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                                        $this$getOrPut$iv.put(pair, linkedHashSet);
                                        value$iv = linkedHashSet;
                                    }
                                    ((Set) value$iv).add(packagePermGroup3.getSecond());
                                    added = true;
                                }
                            }
                        }
                        boolean z = true;
                        if (!added) {
                            Set set3 = (Set) AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE).get(packageUser2);
                            if (set3 != null) {
                                set3.remove(packagePermGroup3.getSecond());
                            }
                            Set set4 = (Set) AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE).get(packageUser2);
                            if (set4 != null && set4.isEmpty()) {
                                AutoRevokedPackagesLiveData.access$getPackageAutoRevokedPermsList$p(AutoRevokedPackagesLiveData.INSTANCE).remove(packageUser2);
                            }
                        }
                        Map $this$all$iv = AutoRevokedPackagesLiveData.access$getPermStateLiveDatas$p(AutoRevokedPackagesLiveData.INSTANCE);
                        if (!$this$all$iv.isEmpty()) {
                            Iterator it2 = $this$all$iv.entrySet().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                Map.Entry element$iv = (Map.Entry) it2.next();
                                if (!((PermStateLiveData) element$iv.getValue()).getIsInitialized()) {
                                    z = false;
                                    break;
                                }
                            }
                        }
                        if (z) {
                            AutoRevokedPackagesLiveData.INSTANCE.postCopyOfMap();
                        }
                    }
                });
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postCopyOfMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Pair<String, UserHandle>, Set<String>> entry : packageAutoRevokedPermsList.entrySet()) {
            linkedHashMap.put(entry.getKey(), CollectionsKt.toSet(entry.getValue()));
        }
        postValue(linkedHashMap);
    }
}
