package com.android.permissioncontroller.permission.data;

import com.android.permissioncontroller.permission.data.DataRepository;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DataRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aP\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004*\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0006\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\b\u001a\u0002H\u0003H\u0086\u0002¢\u0006\u0002\u0010\t\u001ad\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\n\"\b\b\u0003\u0010\u0001*\u00020\u0004* \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\n0\u000b\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\b\u001a\u0002H\u00032\u0006\u0010\f\u001a\u0002H\nH\u0086\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"get", "V", "K1", "K2", "Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "Lkotlin/Pair;", "k1", "k2", "(Lcom/android/permissioncontroller/permission/data/DataRepository;Ljava/lang/Object;Ljava/lang/Object;)Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "K3", "Lkotlin/Triple;", "k3", "(Lcom/android/permissioncontroller/permission/data/DataRepository;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 2, mv = {1, 1, 16})
public final class DataRepositoryKt {
    public static final <K1, K2, V extends DataRepository.InactiveTimekeeper> V get(DataRepository<Pair<K1, K2>, V> get, K1 k1, K2 k2) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        return (V) get.get(TuplesKt.to(k1, k2));
    }

    public static final <K1, K2, K3, V extends DataRepository.InactiveTimekeeper> V get(DataRepository<Triple<K1, K2, K3>, V> get, K1 k1, K2 k2, K3 k3) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        return (V) get.get(new Triple<>(k1, k2, k3));
    }
}
