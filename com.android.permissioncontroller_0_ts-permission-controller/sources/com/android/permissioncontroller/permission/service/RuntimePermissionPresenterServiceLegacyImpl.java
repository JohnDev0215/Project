package com.android.permissioncontroller.permission.service;

import android.content.pm.permission.RuntimePermissionPresentationInfo;
import android.permissionpresenterservice.RuntimePermissionPresenterService;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class RuntimePermissionPresenterServiceLegacyImpl extends RuntimePermissionPresenterService {
    public List<RuntimePermissionPresentationInfo> onGetAppPermissions(String packageName) {
        return Collections.emptyList();
    }
}
