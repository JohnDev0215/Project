package com.permissionx.guolindev;

import android.os.Build;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.permissionx.guolindev.request.PermissionBuilder;
import com.permissionx.guolindev.request.RequestBackgroundLocationPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionCollection {
    private static final String TAG = "PermissionCollection";
    private FragmentActivity activity;
    private Fragment fragment;

    public PermissionCollection(FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
    }

    public PermissionCollection(Fragment fragment) {
        this.fragment = fragment;
    }

    public PermissionBuilder permissions(String... strArr) {
        return permissions(new ArrayList(Arrays.asList(strArr)));
    }

    public PermissionBuilder permissions(List<String> list) {
        boolean z;
        int i;
        HashSet hashSet = new HashSet(list);
        HashSet hashSet2 = new HashSet();
        if (hashSet.contains(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)) {
            int i2 = Build.VERSION.SDK_INT;
            Fragment fragment = this.fragment;
            if (fragment != null && fragment.getContext() != null) {
                i = this.fragment.getContext().getApplicationInfo().targetSdkVersion;
            } else {
                i = this.activity.getApplicationInfo().targetSdkVersion;
            }
            if (i2 < 30 || i < 30) {
                if (i2 < 29) {
                    hashSet.remove(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                    hashSet2.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                }
                z = false;
            } else {
                hashSet.remove(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                z = true;
            }
        } else {
            z = false;
        }
        return new PermissionBuilder(this.activity, this.fragment, hashSet, z, hashSet2);
    }
}
