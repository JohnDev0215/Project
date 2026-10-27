package com.android.permissioncontroller.permission.model.livedatatypes;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoRevokeState.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/AutoRevokeState;", "", "isEnabledGlobal", "", "isEnabledForApp", "revocableGroupNames", "", "", "(ZZLjava/util/List;)V", "()Z", "getRevocableGroupNames", "()Ljava/util/List;", "shouldShowSwitch", "getShouldShowSwitch", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokeState {
    private final boolean isEnabledForApp;
    private final boolean isEnabledGlobal;
    private final List<String> revocableGroupNames;
    private final boolean shouldShowSwitch;

    public AutoRevokeState(boolean isEnabledGlobal, boolean isEnabledForApp, List<String> revocableGroupNames) {
        Intrinsics.checkParameterIsNotNull(revocableGroupNames, "revocableGroupNames");
        this.isEnabledGlobal = isEnabledGlobal;
        this.isEnabledForApp = isEnabledForApp;
        this.revocableGroupNames = revocableGroupNames;
        this.shouldShowSwitch = !revocableGroupNames.isEmpty();
    }

    /* JADX INFO: renamed from: isEnabledGlobal, reason: from getter */
    public final boolean getIsEnabledGlobal() {
        return this.isEnabledGlobal;
    }

    /* JADX INFO: renamed from: isEnabledForApp, reason: from getter */
    public final boolean getIsEnabledForApp() {
        return this.isEnabledForApp;
    }

    public final List<String> getRevocableGroupNames() {
        return this.revocableGroupNames;
    }

    public final boolean getShouldShowSwitch() {
        return this.shouldShowSwitch;
    }
}
