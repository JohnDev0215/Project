package com.amazonaws.mobile.auth.core.signin;

/* JADX INFO: loaded from: classes5.dex */
public interface SignInPermissionsHandler {
    int getPermissionRequestCode();

    void handleRequestPermissionsResult(int i, String[] strArr, int[] iArr);
}
