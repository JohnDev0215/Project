package com.android.permissioncontroller.role.ui.specialappaccess;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.ui.ManageRoleHolderStateLiveData;
import com.android.permissioncontroller.role.ui.RoleLiveData;
import com.android.permissioncontroller.role.ui.RoleSortFunction;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class SpecialAppAccessViewModel extends AndroidViewModel {
    private static final String LOG_TAG = SpecialAppAccessViewModel.class.getSimpleName();
    private final ArrayMap<String, ManageRoleHolderStateLiveData> mManageRoleHolderStateLiveDatas;
    private final Role mRole;
    private final LiveData<List<Pair<ApplicationInfo, Boolean>>> mRoleLiveData;

    public interface ManageRoleHolderStateObserver {
        void onManageRoleHolderStateChanged(ManageRoleHolderStateLiveData manageRoleHolderStateLiveData, int i);
    }

    public SpecialAppAccessViewModel(Role role, Application application) {
        super(application);
        this.mManageRoleHolderStateLiveDatas = new ArrayMap<>();
        this.mRole = role;
        UserHandle user = Process.myUserHandle();
        RoleLiveData roleLiveData = new RoleLiveData(role, user, application);
        UserHandle workProfile = UserUtils.getWorkProfile(application);
        RoleSortFunction sortFunction = new RoleSortFunction(application);
        if (workProfile == null) {
            this.mRoleLiveData = Transformations.map(roleLiveData, sortFunction);
        } else {
            RoleLiveData workRoleLiveData = new RoleLiveData(role, workProfile, application);
            this.mRoleLiveData = Transformations.map(new MergeRoleLiveData(roleLiveData, workRoleLiveData), sortFunction);
        }
    }

    public LiveData<List<Pair<ApplicationInfo, Boolean>>> getRoleLiveData() {
        return this.mRoleLiveData;
    }

    public void observeManageRoleHolderState(LifecycleOwner owner, final ManageRoleHolderStateObserver observer) {
        int manageRoleHolderStateLiveDatasSize = this.mManageRoleHolderStateLiveDatas.size();
        for (int i = 0; i < manageRoleHolderStateLiveDatasSize; i++) {
            final ManageRoleHolderStateLiveData liveData = this.mManageRoleHolderStateLiveDatas.valueAt(i);
            liveData.observe(owner, new Observer() { // from class: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$SpecialAppAccessViewModel$SuOIuu1TTK6xD6r4iPKx1Z1xiwU
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    observer.onManageRoleHolderStateChanged(liveData, ((Integer) obj).intValue());
                }
            });
        }
    }

    private ManageRoleHolderStateLiveData getManageRoleHolderStateLiveData(String key, LifecycleOwner owner, final ManageRoleHolderStateObserver observer) {
        ManageRoleHolderStateLiveData liveData = this.mManageRoleHolderStateLiveDatas.get(key);
        if (liveData == null) {
            final ManageRoleHolderStateLiveData liveData2 = new ManageRoleHolderStateLiveData();
            liveData2.observe(owner, new Observer() { // from class: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$SpecialAppAccessViewModel$YGzTcvkIJyS9kCm0culQ4dOl6x4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    observer.onManageRoleHolderStateChanged(liveData2, ((Integer) obj).intValue());
                }
            });
            this.mManageRoleHolderStateLiveDatas.put(key, liveData2);
            return liveData2;
        }
        return liveData;
    }

    public void setSpecialAppAccessAsUser(String packageName, boolean allow, UserHandle user, String key, LifecycleOwner owner, ManageRoleHolderStateObserver observer) {
        ManageRoleHolderStateLiveData liveData = getManageRoleHolderStateLiveData(key, owner, observer);
        if (liveData.getValue().intValue() != 0) {
            Log.i(LOG_TAG, "Trying to set special app access while another request is on-going");
        } else {
            liveData.setRoleHolderAsUser(this.mRole.getName(), packageName, allow, 0, user, getApplication());
        }
    }

    public static class Factory implements ViewModelProvider.Factory {
        private Application mApplication;
        private Role mRole;

        public Factory(Role role, Application application) {
            this.mRole = role;
            this.mApplication = application;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return new SpecialAppAccessViewModel(this.mRole, this.mApplication);
        }
    }
}
