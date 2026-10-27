package com.permissionx.guolindev;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionX {
    public static PermissionCollection init(FragmentActivity fragmentActivity) {
        return new PermissionCollection(fragmentActivity);
    }

    public static PermissionCollection init(Fragment fragment) {
        return new PermissionCollection(fragment);
    }

    public static boolean isGranted(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }
}
