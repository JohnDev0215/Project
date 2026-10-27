package com.android.permissioncontroller.role.ui.specialappaccess;

import android.app.Application;
import android.os.Process;
import android.os.UserHandle;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.android.permissioncontroller.role.ui.RoleItem;
import com.android.permissioncontroller.role.ui.RoleListLiveData;
import com.android.permissioncontroller.role.ui.RoleListSortFunction;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class SpecialAppAccessListViewModel extends AndroidViewModel {
    private final LiveData<List<RoleItem>> mLiveData;

    public SpecialAppAccessListViewModel(Application application) {
        super(application);
        UserHandle user = Process.myUserHandle();
        RoleListLiveData liveData = new RoleListLiveData(false, user, application);
        UserHandle workProfile = UserUtils.getWorkProfile(application);
        RoleListSortFunction sortFunction = new RoleListSortFunction(application);
        if (workProfile == null) {
            this.mLiveData = Transformations.map(liveData, sortFunction);
        } else {
            RoleListLiveData workLiveData = new RoleListLiveData(false, workProfile, application);
            this.mLiveData = Transformations.map(new MergeRoleListLiveData(liveData, workLiveData), sortFunction);
        }
    }

    public LiveData<List<RoleItem>> getLiveData() {
        return this.mLiveData;
    }
}
