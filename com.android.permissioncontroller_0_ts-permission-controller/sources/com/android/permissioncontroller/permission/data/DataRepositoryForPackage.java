package com.android.permissioncontroller.permission.data;

import com.android.permissioncontroller.permission.data.DataRepository;
import com.android.permissioncontroller.permission.data.DataRepository.InactiveTimekeeper;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DataRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "K", "V", "Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "()V", "invalidateAllForPackage", "", "packageName", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public abstract class DataRepositoryForPackage<K, V extends DataRepository.InactiveTimekeeper> extends DataRepository<K, V> {
    public final void invalidateAllForPackage(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        synchronized (getLock()) {
            for (Object key : CollectionsKt.toSet(getData().keySet())) {
                if ((key instanceof Pair) || ((key instanceof Triple) && Intrinsics.areEqual(((Triple) key).getFirst(), packageName))) {
                    getData().remove(key);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
