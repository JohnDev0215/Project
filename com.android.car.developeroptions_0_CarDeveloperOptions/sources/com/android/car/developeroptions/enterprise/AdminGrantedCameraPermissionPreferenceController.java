package com.android.car.developeroptions.enterprise;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class AdminGrantedCameraPermissionPreferenceController extends AdminGrantedPermissionsPreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "enterprise_privacy_number_camera_access_packages";
    }

    public AdminGrantedCameraPermissionPreferenceController(Context context, boolean z) {
        super(context, z, new String[]{"android.permission.CAMERA"});
    }
}
