package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.android.permissioncontroller.PermissionControllerApplication;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: PackageBroadcastReceiver.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005J\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005J\u0018\u0010\u001c\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000f\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "allCallbacks", "", "Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "app", "Landroid/app/Application;", "changeCallbacks", "", "", "intentFilter", "Landroid/content/IntentFilter;", "addAllCallback", "", "listener", "addChangeCallback", "packageName", "getNumListeners", "", "hasNoListeners", "", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "removeAllCallback", "removeChangeCallback", "PackageBroadcastListener", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PackageBroadcastReceiver extends BroadcastReceiver {
    public static final PackageBroadcastReceiver INSTANCE = new PackageBroadcastReceiver();
    private static final Set<PackageBroadcastListener> allCallbacks;
    private static final Application app;
    private static final Map<String, Set<PackageBroadcastListener>> changeCallbacks;
    private static final IntentFilter intentFilter;

    /* JADX INFO: compiled from: PackageBroadcastReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "", "onPackageUpdate", "", "packageName", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public interface PackageBroadcastListener {
        void onPackageUpdate(String packageName);
    }

    static {
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        app = permissionControllerApplication;
        IntentFilter $this$apply = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        $this$apply.addAction("android.intent.action.PACKAGE_REMOVED");
        $this$apply.addAction("android.intent.action.PACKAGE_REPLACED");
        $this$apply.addAction("android.intent.action.PACKAGE_CHANGED");
        $this$apply.addDataScheme("package");
        intentFilter = $this$apply;
        changeCallbacks = new LinkedHashMap();
        allCallbacks = new LinkedHashSet();
    }

    private PackageBroadcastReceiver() {
    }

    public static final /* synthetic */ Set access$getAllCallbacks$p(PackageBroadcastReceiver $this) {
        return allCallbacks;
    }

    public static final /* synthetic */ Application access$getApp$p(PackageBroadcastReceiver $this) {
        return app;
    }

    public static final /* synthetic */ Map access$getChangeCallbacks$p(PackageBroadcastReceiver $this) {
        return changeCallbacks;
    }

    public static final /* synthetic */ IntentFilter access$getIntentFilter$p(PackageBroadcastReceiver $this) {
        return intentFilter;
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$addChangeCallback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PackageBroadcastReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$addChangeCallback$1", f = "PackageBroadcastReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PackageBroadcastListener $listener;
        final /* synthetic */ String $packageName;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00341(String str, PackageBroadcastListener packageBroadcastListener, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
            this.$listener = packageBroadcastListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00341 c00341 = new C00341(this.$packageName, this.$listener, completion);
            c00341.p$ = (CoroutineScope) obj;
            return c00341;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            boolean wasEmpty = PackageBroadcastReceiver.INSTANCE.hasNoListeners();
            Map $this$getOrPut$iv = PackageBroadcastReceiver.access$getChangeCallbacks$p(PackageBroadcastReceiver.INSTANCE);
            String str = this.$packageName;
            Object value$iv = $this$getOrPut$iv.get(str);
            if (value$iv == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                $this$getOrPut$iv.put(str, linkedHashSet);
                value$iv = linkedHashSet;
            }
            ((Set) value$iv).add(this.$listener);
            if (wasEmpty) {
                PackageBroadcastReceiver.access$getApp$p(PackageBroadcastReceiver.INSTANCE).getApplicationContext().registerReceiverForAllUsers(PackageBroadcastReceiver.INSTANCE, PackageBroadcastReceiver.access$getIntentFilter$p(PackageBroadcastReceiver.INSTANCE), null, null);
            }
            return Unit.INSTANCE;
        }
    }

    public final void addChangeCallback(String packageName, PackageBroadcastListener listener) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00341(packageName, listener, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$addAllCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: PackageBroadcastReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$addAllCallback$1", f = "PackageBroadcastReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PackageBroadcastListener $listener;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PackageBroadcastListener packageBroadcastListener, Continuation continuation) {
            super(2, continuation);
            this.$listener = packageBroadcastListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$listener, completion);
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
            boolean wasEmpty = PackageBroadcastReceiver.INSTANCE.hasNoListeners();
            PackageBroadcastReceiver.access$getAllCallbacks$p(PackageBroadcastReceiver.INSTANCE).add(this.$listener);
            if (wasEmpty) {
                PackageBroadcastReceiver.access$getApp$p(PackageBroadcastReceiver.INSTANCE).getApplicationContext().registerReceiverForAllUsers(PackageBroadcastReceiver.INSTANCE, PackageBroadcastReceiver.access$getIntentFilter$p(PackageBroadcastReceiver.INSTANCE), null, null);
            }
            return Unit.INSTANCE;
        }
    }

    public final void addAllCallback(PackageBroadcastListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass1(listener, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$removeAllCallback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PackageBroadcastReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$removeAllCallback$1", f = "PackageBroadcastReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PackageBroadcastListener $listener;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00351(PackageBroadcastListener packageBroadcastListener, Continuation continuation) {
            super(2, continuation);
            this.$listener = packageBroadcastListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00351 c00351 = new C00351(this.$listener, completion);
            c00351.p$ = (CoroutineScope) obj;
            return c00351;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            boolean wasEmpty = PackageBroadcastReceiver.INSTANCE.hasNoListeners();
            if (PackageBroadcastReceiver.access$getAllCallbacks$p(PackageBroadcastReceiver.INSTANCE).remove(this.$listener) && PackageBroadcastReceiver.INSTANCE.hasNoListeners() && !wasEmpty) {
                PackageBroadcastReceiver.access$getApp$p(PackageBroadcastReceiver.INSTANCE).getApplicationContext().unregisterReceiver(PackageBroadcastReceiver.INSTANCE);
            }
            return Unit.INSTANCE;
        }
    }

    public final void removeAllCallback(PackageBroadcastListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00351(listener, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$removeChangeCallback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PackageBroadcastReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.PackageBroadcastReceiver$removeChangeCallback$1", f = "PackageBroadcastReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PackageBroadcastListener $listener;
        final /* synthetic */ String $packageName;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00361(String str, PackageBroadcastListener packageBroadcastListener, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
            this.$listener = packageBroadcastListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00361 c00361 = new C00361(this.$packageName, this.$listener, completion);
            c00361.p$ = (CoroutineScope) obj;
            return c00361;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            boolean wasEmpty = PackageBroadcastReceiver.INSTANCE.hasNoListeners();
            Set callbackSet = (Set) PackageBroadcastReceiver.access$getChangeCallbacks$p(PackageBroadcastReceiver.INSTANCE).get(this.$packageName);
            if (callbackSet != null) {
                callbackSet.remove(this.$listener);
                if (callbackSet.isEmpty()) {
                    Map mapAccess$getChangeCallbacks$p = PackageBroadcastReceiver.access$getChangeCallbacks$p(PackageBroadcastReceiver.INSTANCE);
                    String str = this.$packageName;
                    if (mapAccess$getChangeCallbacks$p == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
                    }
                    TypeIntrinsics.asMutableMap(mapAccess$getChangeCallbacks$p).remove(str);
                }
                if (PackageBroadcastReceiver.INSTANCE.hasNoListeners() && !wasEmpty) {
                    PackageBroadcastReceiver.access$getApp$p(PackageBroadcastReceiver.INSTANCE).getApplicationContext().unregisterReceiver(PackageBroadcastReceiver.INSTANCE);
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void removeChangeCallback(String packageName, PackageBroadcastListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00361(packageName, listener, null), 2, null);
    }

    private final int getNumListeners() {
        int numListeners = allCallbacks.size();
        Iterator<Map.Entry<String, Set<PackageBroadcastListener>>> it = changeCallbacks.entrySet().iterator();
        while (it.hasNext()) {
            numListeners += it.next().getValue().size();
        }
        return numListeners;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasNoListeners() {
        return getNumListeners() == 0;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String packageName;
        Set<PackageBroadcastListener> set;
        List<PackageBroadcastListener> callbacks;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Uri data = intent.getData();
        if (data == null || (packageName = data.getSchemeSpecificPart()) == null) {
            return;
        }
        for (PackageBroadcastListener callback : CollectionsKt.toList(allCallbacks)) {
            callback.onPackageUpdate(packageName);
        }
        if ((!Intrinsics.areEqual(intent.getAction(), "android.intent.action.PACKAGE_ADDED")) && (set = changeCallbacks.get(packageName)) != null && (callbacks = CollectionsKt.toList(set)) != null) {
            for (PackageBroadcastListener callback2 : callbacks) {
                callback2.onPackageUpdate(packageName);
            }
        }
        if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.PACKAGE_REMOVED")) {
            LightPackageInfoLiveData.INSTANCE.invalidateAllForPackage(packageName);
            PermStateLiveData.INSTANCE.invalidateAllForPackage(packageName);
            PackagePermissionsLiveData.INSTANCE.invalidateAllForPackage(packageName);
            AutoRevokeStateLiveData.INSTANCE.invalidateAllForPackage(packageName);
            LightAppPermGroupLiveData.INSTANCE.invalidateAllForPackage(packageName);
            AppPermGroupUiInfoLiveData.INSTANCE.invalidateAllForPackage(packageName);
        }
    }
}
