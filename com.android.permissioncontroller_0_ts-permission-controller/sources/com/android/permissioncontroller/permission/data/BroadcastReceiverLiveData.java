package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import com.android.launcher3.icons.cache.BaseIconCache;
import com.android.permissioncontroller.DumpableLog;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.PackageBroadcastReceiver;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: BroadcastReceiverLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001a2\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u00020\u00042\u00020\u0005:\u0001\u001aB%\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0012H\u0014J\b\u0010\u0017\u001a\u00020\u0012H\u0014J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0003H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/android/permissioncontroller/permission/data/BroadcastReceiverLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "", "Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "Lcom/android/permissioncontroller/permission/data/HasIntentAction;", "app", "Landroid/app/Application;", "intentAction", "permission", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;Landroid/os/UserHandle;)V", "DEBUG", "", "getIntentAction", "()Ljava/lang/String;", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "onInactive", "onPackageUpdate", "packageName", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class BroadcastReceiverLiveData extends SmartAsyncMediatorLiveData<Set<? extends String>> implements PackageBroadcastReceiver.PackageBroadcastListener, HasIntentAction {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean DEBUG;
    private final Application app;
    private final String intentAction;
    private final String permission;
    private final UserHandle user;

    public BroadcastReceiverLiveData(Application app, String intentAction, String permission, UserHandle user) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(intentAction, "intentAction");
        Intrinsics.checkParameterIsNotNull(permission, "permission");
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.app = app;
        this.intentAction = intentAction;
        this.permission = permission;
        this.user = user;
    }

    @Override // com.android.permissioncontroller.permission.data.HasIntentAction
    public String getIntentAction() {
        return this.intentAction;
    }

    @Override // com.android.permissioncontroller.permission.data.PackageBroadcastReceiver.PackageBroadcastListener
    public void onPackageUpdate(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) throws Throwable {
        ActivityInfo activityInfo;
        ActivityInfo activityInfo2;
        if (job.isCancelled()) {
            return Unit.INSTANCE;
        }
        Context userContext = Utils.getUserContext(this.app, this.user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "getUserContext(app, user)");
        Iterable iterableQueryBroadcastReceivers = userContext.getPackageManager().queryBroadcastReceivers(new Intent(getIntentAction()), 130);
        Intrinsics.checkExpressionValueIsNotNull(iterableQueryBroadcastReceivers, "getUserContext(app, user…ageManager.GET_META_DATA)");
        Iterable $this$mapNotNull$iv = iterableQueryBroadcastReceivers;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            ResolveInfo resolveInfo = (ResolveInfo) element$iv$iv$iv;
            String str = ((Intrinsics.areEqual((resolveInfo == null || (activityInfo2 = resolveInfo.activityInfo) == null) ? null : activityInfo2.permission, this.permission) ^ true) || resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null) ? null : activityInfo.packageName;
            if (str != null) {
                destination$iv$iv.add(str);
            }
        }
        Iterable $this$mapNotNull$iv2 = (List) destination$iv$iv;
        Set packageNames = CollectionsKt.toSet($this$mapNotNull$iv2);
        if (this.DEBUG) {
            DumpableLog dumpableLog = DumpableLog.INSTANCE;
            String LOG_TAG = SmartUpdateMediatorLiveData.INSTANCE.getLOG_TAG();
            Intrinsics.checkExpressionValueIsNotNull(LOG_TAG, "LOG_TAG");
            DumpableLog.i$default(dumpableLog, LOG_TAG, "Detected " + StringsKt.substringAfterLast$default(getIntentAction(), BaseIconCache.EMPTY_CLASS_NAME, (String) null, 2, (Object) null) + "s: " + packageNames, null, 4, null);
        }
        postValue(packageNames);
        return Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        PackageBroadcastReceiver.INSTANCE.addAllCallback(this);
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData, com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        PackageBroadcastReceiver.INSTANCE.removeAllCallback(this);
    }

    /* JADX INFO: compiled from: BroadcastReceiverLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00052\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/BroadcastReceiverLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Lkotlin/Triple;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/BroadcastReceiverLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<Triple<? extends String, ? extends String, ? extends UserHandle>, BroadcastReceiverLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public BroadcastReceiverLiveData newValue(Triple<String, String, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new BroadcastReceiverLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), key.getThird());
        }
    }
}
