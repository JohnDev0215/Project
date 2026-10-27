package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import com.android.permissioncontroller.PermissionControllerApplication;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermissionListenerMultiplexer.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\tJ%\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\u0016\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermissionListenerMultiplexer;", "Landroid/content/pm/PackageManager$OnPermissionsChangedListener;", "()V", "app", "Landroid/app/Application;", "callbacks", "", "", "", "Lcom/android/permissioncontroller/permission/data/PermissionListenerMultiplexer$PermissionChangeCallback;", "pm", "Landroid/content/pm/PackageManager;", "kotlin.jvm.PlatformType", "addCallback", "", "uid", "callback", "addOrReplaceCallback", "oldUid", "newUid", "(Ljava/lang/Integer;ILcom/android/permissioncontroller/permission/data/PermissionListenerMultiplexer$PermissionChangeCallback;)V", "onPermissionsChanged", "removeCallback", "PermissionChangeCallback", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermissionListenerMultiplexer implements PackageManager.OnPermissionsChangedListener {
    public static final PermissionListenerMultiplexer INSTANCE = new PermissionListenerMultiplexer();
    private static final Application app;
    private static final Map<Integer, List<PermissionChangeCallback>> callbacks;
    private static final PackageManager pm;

    /* JADX INFO: compiled from: PermissionListenerMultiplexer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermissionListenerMultiplexer$PermissionChangeCallback;", "", "onPermissionChange", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public interface PermissionChangeCallback {
        void onPermissionChange();
    }

    static {
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        app = permissionControllerApplication;
        callbacks = new LinkedHashMap();
        Context applicationContext = app.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "app.applicationContext");
        pm = applicationContext.getPackageManager();
    }

    private PermissionListenerMultiplexer() {
    }

    public void onPermissionsChanged(int uid) {
        Iterable list;
        List<PermissionChangeCallback> list2 = callbacks.get(Integer.valueOf(uid));
        if (list2 == null || (list = CollectionsKt.toList(list2)) == null) {
            return;
        }
        Iterable $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            PermissionChangeCallback callback = (PermissionChangeCallback) element$iv;
            callback.onPermissionChange();
        }
    }

    public final void addOrReplaceCallback(Integer oldUid, int newUid, PermissionChangeCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (oldUid != null) {
            removeCallback(oldUid.intValue(), callback);
        }
        addCallback(newUid, callback);
    }

    public final void addCallback(int uid, PermissionChangeCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        boolean wasEmpty = callbacks.isEmpty();
        Map<Integer, List<PermissionChangeCallback>> map = callbacks;
        Integer numValueOf = Integer.valueOf(uid);
        Object value$iv = map.get(numValueOf);
        if (value$iv == null) {
            ArrayList arrayList = new ArrayList();
            map.put(numValueOf, arrayList);
            value$iv = arrayList;
        }
        ((List) value$iv).add(callback);
        if (wasEmpty) {
            pm.addOnPermissionsChangeListener(this);
        }
    }

    public final void removeCallback(int uid, PermissionChangeCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (!callbacks.containsKey(Integer.valueOf(uid))) {
            return;
        }
        List<PermissionChangeCallback> list = callbacks.get(Integer.valueOf(uid));
        if (list == null) {
            Intrinsics.throwNpe();
        }
        if (!list.remove(callback)) {
            return;
        }
        List<PermissionChangeCallback> list2 = callbacks.get(Integer.valueOf(uid));
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        if (list2.isEmpty()) {
            callbacks.remove(Integer.valueOf(uid));
        }
        if (callbacks.isEmpty()) {
            pm.removeOnPermissionsChangeListener(this);
        }
    }
}
