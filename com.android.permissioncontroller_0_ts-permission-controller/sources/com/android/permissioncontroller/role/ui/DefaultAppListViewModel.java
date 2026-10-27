package com.android.permissioncontroller.role.ui;

import android.app.Application;
import android.os.Process;
import android.os.UserHandle;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class DefaultAppListViewModel extends AndroidViewModel {
    private final LiveData<List<RoleItem>> mLiveData;
    private final UserHandle mUser;
    private final LiveData<List<RoleItem>> mWorkLiveData;
    private final UserHandle mWorkProfile;

    public DefaultAppListViewModel(Application application) {
        super(application);
        this.mUser = Process.myUserHandle();
        RoleListSortFunction sortFunction = new RoleListSortFunction(application);
        this.mLiveData = Transformations.map(new RoleListLiveData(true, this.mUser, application), sortFunction);
        UserHandle workProfile = UserUtils.getWorkProfile(application);
        this.mWorkProfile = workProfile;
        this.mWorkLiveData = workProfile != null ? Transformations.map(new RoleListLiveData(true, this.mWorkProfile, application), sortFunction) : null;
    }

    public UserHandle getUser() {
        return this.mUser;
    }

    public LiveData<List<RoleItem>> getLiveData() {
        return this.mLiveData;
    }

    public boolean hasWorkProfile() {
        return this.mWorkProfile != null;
    }

    public UserHandle getWorkProfile() {
        return this.mWorkProfile;
    }

    public LiveData<List<RoleItem>> getWorkLiveData() {
        return this.mWorkLiveData;
    }
}
