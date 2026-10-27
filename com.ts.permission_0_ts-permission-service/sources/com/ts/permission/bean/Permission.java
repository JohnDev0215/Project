package com.ts.permission.bean;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class Permission extends BaseConfig {
    private String name;
    private int status;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override // com.ts.permission.bean.BaseConfig
    public boolean isValid() {
        this.checkMark = 0;
        if (TextUtils.isEmpty(this.name)) {
            Log.w("TSPS_CONFIG", "permission name cannot be null");
            this.checkMark++;
        }
        return this.checkMark <= 0;
    }
}
