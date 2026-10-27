package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.Log;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.PackageBroadcastReceiver;
import com.android.permissioncontroller.permission.data.PermissionListenerMultiplexer;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LightPackageInfoLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 *2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001*B\u001f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u001eH\u0014J\b\u0010#\u001a\u00020\u001eH\u0014J\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010%\u001a\u00020\u001eH\u0016J\u0012\u0010&\u001a\u00020\u001e2\b\u0010'\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010(\u001a\u00020\u001eH\u0016J\b\u0010)\u001a\u00020\u001eH\u0003R\u0016\u0010\f\u001a\n \r*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "Lcom/android/permissioncontroller/permission/data/PermissionListenerMultiplexer$PermissionChangeCallback;", "app", "Landroid/app/Application;", "packageName", "", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Ljava/lang/String;Landroid/os/UserHandle;)V", "LOG_TAG", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "registeredUid", "", "Ljava/lang/Integer;", "uid", "userPackageInfosObserver", "Landroidx/lifecycle/Observer;", "", "getUserPackageInfosObserver", "()Landroidx/lifecycle/Observer;", "userPackagesLiveData", "Lcom/android/permissioncontroller/permission/data/UserPackageInfosLiveData;", "watchingUserPackagesLiveData", "", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "onInactive", "onPackageUpdate", "onPermissionChange", "setValue", "newValue", "updateAsync", "updateFromUserPackageInfosLiveData", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class LightPackageInfoLiveData extends SmartAsyncMediatorLiveData<LightPackageInfo> implements PackageBroadcastReceiver.PackageBroadcastListener, PermissionListenerMultiplexer.PermissionChangeCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String LOG_TAG;
    private final Application app;
    private Context context;
    private final String packageName;
    private Integer registeredUid;
    private Integer uid;
    private final UserHandle user;
    private final Observer<List<LightPackageInfo>> userPackageInfosObserver;
    private final UserPackageInfosLiveData userPackagesLiveData;
    private boolean watchingUserPackagesLiveData;

    private LightPackageInfoLiveData(Application app, String packageName, UserHandle user) throws PackageManager.NameNotFoundException {
        this.app = app;
        this.packageName = packageName;
        this.user = user;
        this.LOG_TAG = LightPackageInfoLiveData.class.getSimpleName();
        this.userPackagesLiveData = UserPackageInfosLiveData.INSTANCE.get(this.user);
        Context userContext = Utils.getUserContext(this.app, this.user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        this.context = userContext;
        this.userPackageInfosObserver = (Observer) new Observer<List<? extends LightPackageInfo>>() { // from class: com.android.permissioncontroller.permission.data.LightPackageInfoLiveData$userPackageInfosObserver$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends LightPackageInfo> list) {
                onChanged2((List<LightPackageInfo>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<LightPackageInfo> list) {
                this.this$0.updateFromUserPackageInfosLiveData();
            }
        };
    }

    public /* synthetic */ LightPackageInfoLiveData(Application app, String packageName, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName, user);
    }

    @Override // com.android.permissioncontroller.permission.data.PackageBroadcastReceiver.PackageBroadcastListener
    public void onPackageUpdate(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(LightPackageInfo newValue) {
        if (newValue != null) {
            int uid = newValue.getUid();
            Integer num = this.uid;
            if (num == null || uid != num.intValue()) {
                this.uid = Integer.valueOf(newValue.getUid());
                if (hasActiveObservers()) {
                    PermissionListenerMultiplexer.INSTANCE.addOrReplaceCallback(this.registeredUid, newValue.getUid(), this);
                    this.registeredUid = this.uid;
                }
            }
        }
        super.setValue(newValue);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public void updateAsync() {
        if (this.watchingUserPackagesLiveData) {
            removeSource(this.userPackagesLiveData);
            this.watchingUserPackagesLiveData = false;
        }
        super.updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        LightPackageInfo lightPackageInfo;
        if (job.isCancelled()) {
            return Unit.INSTANCE;
        }
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.packageName, 4096);
            Intrinsics.checkExpressionValueIsNotNull(packageInfo, "context.packageManager.g…eManager.GET_PERMISSIONS)");
            lightPackageInfo = new LightPackageInfo(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(this.LOG_TAG, "Package \"" + this.packageName + "\" not found");
            INSTANCE.invalidateSingle(TuplesKt.to(this.packageName, this.user));
            lightPackageInfo = null;
        }
        postValue(lightPackageInfo);
        return Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.PermissionListenerMultiplexer.PermissionChangeCallback
    public void onPermissionChange() {
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        PackageBroadcastReceiver.INSTANCE.addChangeCallback(this.packageName, this);
        Integer num = this.uid;
        if (num != null) {
            int it = num.intValue();
            this.registeredUid = this.uid;
            PermissionListenerMultiplexer.INSTANCE.addCallback(it, this);
        }
        if (this.userPackagesLiveData.hasActiveObservers() && !this.watchingUserPackagesLiveData && !this.userPackagesLiveData.getPermChangeStale()) {
            this.watchingUserPackagesLiveData = true;
            addSource(this.userPackagesLiveData, this.userPackageInfosObserver);
        } else {
            updateAsync();
        }
    }

    public final Observer<List<LightPackageInfo>> getUserPackageInfosObserver() {
        return this.userPackageInfosObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFromUserPackageInfosLiveData() {
        Object next;
        if (!this.userPackagesLiveData.getIsInitialized()) {
            return;
        }
        List<LightPackageInfo> value = this.userPackagesLiveData.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "userPackagesLiveData.value!!");
        Iterator<T> it = value.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            LightPackageInfo it2 = (LightPackageInfo) next;
            if (Intrinsics.areEqual(it2.getPackageName(), this.packageName)) {
                break;
            }
        }
        LightPackageInfo packageInfo = (LightPackageInfo) next;
        if (packageInfo != null) {
            if (!this.userPackagesLiveData.getIsStale()) {
                removeSource(UserPackageInfosLiveData.INSTANCE.get(this.user));
                this.watchingUserPackagesLiveData = false;
            }
            setValue(packageInfo);
            return;
        }
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData, com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        PackageBroadcastReceiver.INSTANCE.removeChangeCallback(this.packageName, this);
        Integer num = this.registeredUid;
        if (num != null) {
            int it = num.intValue();
            PermissionListenerMultiplexer.INSTANCE.removeCallback(it, this);
            this.registeredUid = (Integer) null;
        }
        if (this.watchingUserPackagesLiveData) {
            removeSource(this.userPackagesLiveData);
            this.watchingUserPackagesLiveData = false;
        }
    }

    /* JADX INFO: compiled from: LightPackageInfoLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<Pair<? extends String, ? extends UserHandle>, LightPackageInfoLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public LightPackageInfoLiveData newValue(Pair<String, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new LightPackageInfoLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), null);
        }
    }
}
