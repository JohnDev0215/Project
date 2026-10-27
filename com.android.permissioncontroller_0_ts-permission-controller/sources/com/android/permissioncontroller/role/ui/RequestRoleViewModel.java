package com.android.permissioncontroller.role.ui;

import android.app.Application;
import android.os.Process;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.permissioncontroller.role.model.Role;

/* JADX INFO: loaded from: classes8.dex */
public class RequestRoleViewModel extends DefaultAppViewModel {
    public RequestRoleViewModel(Role role, Application application) {
        super(role, Process.myUserHandle(), application);
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
            return new RequestRoleViewModel(this.mRole, this.mApplication);
        }
    }
}
