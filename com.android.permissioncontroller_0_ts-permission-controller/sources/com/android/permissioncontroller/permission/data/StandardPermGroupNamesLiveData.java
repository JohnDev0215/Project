package com.android.permissioncontroller.permission.data;

import androidx.lifecycle.LiveData;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: StandardPermGroupNamesLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/android/permissioncontroller/permission/data/StandardPermGroupNamesLiveData;", "Landroidx/lifecycle/LiveData;", "", "", "()V", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class StandardPermGroupNamesLiveData extends LiveData<List<? extends String>> {
    public static final StandardPermGroupNamesLiveData INSTANCE;

    static {
        StandardPermGroupNamesLiveData standardPermGroupNamesLiveData = new StandardPermGroupNamesLiveData();
        INSTANCE = standardPermGroupNamesLiveData;
        standardPermGroupNamesLiveData.setValue(Utils.getPlatformPermissionGroups());
    }

    private StandardPermGroupNamesLiveData() {
    }
}
