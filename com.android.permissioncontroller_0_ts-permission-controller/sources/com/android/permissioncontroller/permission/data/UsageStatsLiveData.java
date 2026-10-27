package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.DataRepository;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: UsageStatsLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00020\u0001:\u0001\u0013B!\b\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u000eH\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/android/permissioncontroller/permission/data/UsageStatsLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "Landroid/os/UserHandle;", "", "Landroid/app/usage/UsageStats;", "app", "Landroid/app/Application;", "searchTimeMs", "", "interval", "", "(Landroid/app/Application;JI)V", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class UsageStatsLiveData extends SmartAsyncMediatorLiveData<Map<UserHandle, ? extends List<? extends UsageStats>>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final int interval;
    private final long searchTimeMs;

    private UsageStatsLiveData(Application app, long searchTimeMs, int interval) {
        this.app = app;
        this.searchTimeMs = searchTimeMs;
        this.interval = interval;
        addSource(UsersLiveData.INSTANCE, new Observer<List<? extends UserHandle>>() { // from class: com.android.permissioncontroller.permission.data.UsageStatsLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends UserHandle> list) {
                onChanged2((List<UserHandle>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<UserHandle> list) {
                UsageStatsLiveData.this.updateIfActive();
            }
        });
    }

    public /* synthetic */ UsageStatsLiveData(Application app, long searchTimeMs, int interval, DefaultConstructorMarker $constructor_marker) {
        this(app, searchTimeMs, interval);
    }

    /* synthetic */ UsageStatsLiveData(Application application, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(application, j, (i2 & 4) != 0 ? 2 : i);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        if (!UsersLiveData.INSTANCE.getIsInitialized()) {
            return Unit.INSTANCE;
        }
        long now = System.currentTimeMillis();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<? extends UserHandle> value = UsersLiveData.INSTANCE.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        for (UserHandle user : value) {
            if (!Utils.isUserDisabledOrWorkProfile(user)) {
                Object systemService = Utils.getUserContext(this.app, user).getSystemService((Class<Object>) UsageStatsManager.class);
                if (systemService == null) {
                    Intrinsics.throwNpe();
                }
                UsageStatsManager statsManager = (UsageStatsManager) systemService;
                List<UsageStats> listQueryUsageStats = statsManager.queryUsageStats(this.interval, now - this.searchTimeMs, now);
                if (listQueryUsageStats != null) {
                    linkedHashMap.put(user, listQueryUsageStats);
                }
            }
        }
        postValue(linkedHashMap);
        return Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        updateIfActive();
    }

    /* JADX INFO: compiled from: UsageStatsLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0086\u0002J\u001c\u0010\t\u001a\u00020\u00052\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\u000b"}, d2 = {"Lcom/android/permissioncontroller/permission/data/UsageStatsLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "Lkotlin/Pair;", "", "", "Lcom/android/permissioncontroller/permission/data/UsageStatsLiveData;", "()V", "get", "interval", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<Pair<? extends Long, ? extends Integer>, UsageStatsLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public /* bridge */ /* synthetic */ DataRepository.InactiveTimekeeper newValue(Pair<? extends Long, ? extends Integer> pair) {
            return newValue2((Pair<Long, Integer>) pair);
        }

        /* JADX INFO: renamed from: newValue, reason: avoid collision after fix types in other method */
        protected UsageStatsLiveData newValue2(Pair<Long, Integer> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new UsageStatsLiveData(permissionControllerApplication, key.getFirst().longValue(), key.getSecond().intValue(), null);
        }

        public final UsageStatsLiveData get(long interval) {
            return get(TuplesKt.to(Long.valueOf(interval), 2));
        }
    }
}
