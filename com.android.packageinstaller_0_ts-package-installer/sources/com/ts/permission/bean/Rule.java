package com.ts.permission.bean;

import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Rule extends BaseConfig implements Comparable<Rule> {
    public static final int ALL_DENIED = 3;
    public static final int ALL_GRANTED = 1;
    public static final int DEFAULT_STATE_DENIED = 2;
    public static final int DEFAULT_STATE_GRANTED = 1;
    public static final int MAJORTYPE_OTHER = 5;
    public static final int MAJORTYPE_PRESET = 3;
    public static final int MAJORTYPE_SHOP = 4;
    public static final int MAJORTYPE_SIGNATURE = 2;
    public static final int MAJORTYPE_WHITE = 1;
    public static final int PART_GRANTED = 2;
    private List<App> apps;
    private int authorizationType;
    private int defaultState;
    private boolean enable;
    private int majorType;
    private List<Permission> permissions;
    private int priority;

    public int getMajorType() {
        return this.majorType;
    }

    public void setMajorType(int majorType) {
        this.majorType = majorType;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getAuthorizationType() {
        return this.authorizationType;
    }

    public void setAuthorizationType(int authorizationType) {
        this.authorizationType = authorizationType;
    }

    public int getDefaultState() {
        return this.defaultState;
    }

    public void setDefaultState(int defaultState) {
        this.defaultState = defaultState;
    }

    public List<App> getApps() {
        return this.apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
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
        int i = this.majorType;
        if (i == 1) {
            checkApps();
        } else if (i == 2 || i == 3 || i == 4 || i == 5) {
            checkAuthorizationType();
        } else {
            Log.w("TSPS_CONFIG", "majorType error,the error majorType value is " + this.majorType);
            this.checkMark = this.checkMark + 1;
        }
        return super.isValid();
    }

    private void checkApps() {
        List<App> list = this.apps;
        if (list == null || list.size() == 0) {
            Log.w("TSPS_CONFIG", "apps cannot be null when majorType value is 1");
            return;
        }
        Iterator<App> iterator = this.apps.iterator();
        while (iterator.hasNext()) {
            App app = iterator.next();
            if (app == null) {
                iterator.remove();
            } else {
                boolean isValid = app.isValid();
                if (!isValid) {
                    iterator.remove();
                }
            }
        }
    }

    private void checkAuthorizationType() {
        int i = this.authorizationType;
        if (i != 1) {
            if (i == 2) {
                checkPermissions();
                return;
            }
            if (i != 3) {
                Log.w("TSPS_CONFIG", "Rule authorizationType error,the error authorizationType value is " + this.authorizationType);
                this.checkMark = this.checkMark + 1;
            }
        }
    }

    private void checkPermissions() {
        List<Permission> list = this.permissions;
        if (list == null || list.size() == 0) {
            Log.w("TSPS_CONFIG", "permissions cannot be null when rule authorizationType value is 2");
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

    @Override // java.lang.Comparable
    public int compareTo(Rule rule) {
        return rule.priority - this.priority;
    }
}
