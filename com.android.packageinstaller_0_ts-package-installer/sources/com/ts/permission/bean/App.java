package com.ts.permission.bean;

import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class App extends BaseConfig {
    private int authorizationType;
    private int defaultState;
    private List<String> packageNames;
    private List<Permission> permissions;

    public List<String> getPackageNames() {
        return this.packageNames;
    }

    public void setPackageNames(List<String> packageNames) {
        this.packageNames = packageNames;
    }

    public int getAuthorizationType() {
        return this.authorizationType;
    }

    public int getDefaultState() {
        return this.defaultState;
    }

    public void setDefaultState(int defaultState) {
        this.defaultState = defaultState;
    }

    public void setAuthorizationType(int authorizationType) {
        this.authorizationType = authorizationType;
    }

    public List<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override // com.ts.permission.bean.BaseConfig
    public boolean isValid() {
        this.checkMark = 0;
        List<String> list = this.packageNames;
        if (list == null || list.size() == 0) {
            Log.w("TSPS_CONFIG", "app packageNames cannot be null");
            this.checkMark++;
        }
        checkAuthorizationType();
        return this.checkMark <= 0;
    }

    private void checkAuthorizationType() {
        int i = this.authorizationType;
        if (i != 1) {
            if (i == 2) {
                checkPermissions();
                return;
            }
            if (i != 3) {
                Log.w("TSPS_CONFIG", "App authorizationType error,the error authorizationType value is " + this.authorizationType);
                this.checkMark = this.checkMark + 1;
            }
        }
    }

    private void checkPermissions() {
        List<Permission> list = this.permissions;
        if (list == null || list.size() == 0) {
            Log.w("TSPS_CONFIG", "permissions cannot be null when app authorizationType value is PART_GRANTED");
            return;
        }
        Iterator<Permission> iterator = this.permissions.iterator();
        while (iterator.hasNext()) {
            Permission permission = iterator.next();
            if (permission == null) {
                iterator.remove();
            } else {
                boolean isValid = permission.isValid();
                if (!isValid) {
                    iterator.remove();
                }
            }
        }
    }
}
