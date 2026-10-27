package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.UserHandle;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: PreinstalledUserPackageInfosLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00040\u0001:\u0001\u0010B\u0017\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u000bH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PreinstalledUserPackageInfosLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "Lkotlin/jvm/JvmSuppressWildcards;", "app", "Landroid/app/Application;", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Landroid/os/UserHandle;)V", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PreinstalledUserPackageInfosLiveData extends SmartAsyncMediatorLiveData<List<LightPackageInfo>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final UserHandle user;

    private PreinstalledUserPackageInfosLiveData(Application app, UserHandle user) {
        this.app = app;
        this.user = user;
    }

    public /* synthetic */ PreinstalledUserPackageInfosLiveData(Application app, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, user);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        if (job.isCancelled()) {
            return Unit.INSTANCE;
        }
        Log.i("PreinstalledUserPackageInfos", "updating PreinstalledUserPackageInfosLiveData for user " + this.user.getIdentifier());
        Context applicationContext = this.app.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "app.applicationContext");
        Iterable packageInfos = applicationContext.getPackageManager().getInstalledPackagesAsUser(2109440, this.user.getIdentifier());
        Intrinsics.checkExpressionValueIsNotNull(packageInfos, "packageInfos");
        Iterable $this$map$iv = packageInfos;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PackageInfo packageInfo = (PackageInfo) item$iv$iv;
            Intrinsics.checkExpressionValueIsNotNull(packageInfo, "packageInfo");
            destination$iv$iv.add(new LightPackageInfo(packageInfo));
        }
        postValue((List) destination$iv$iv);
        return Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        if (getValue() == null) {
            updateAsync();
        }
    }

    /* JADX INFO: compiled from: PreinstalledUserPackageInfosLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PreinstalledUserPackageInfosLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/PreinstalledUserPackageInfosLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<UserHandle, PreinstalledUserPackageInfosLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public PreinstalledUserPackageInfosLiveData newValue(UserHandle key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new PreinstalledUserPackageInfosLiveData(permissionControllerApplication, key, null);
        }
    }
}
