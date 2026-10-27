package com.android.systemui.imageloader;

import android.app.ActivityManager;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AppLogo {
    private String packageName;
    private ActivityManager.TaskDescription taskDescription;

    public AppLogo(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(this.packageName, ((AppLogo) o).packageName);
    }

    public int hashCode() {
        return Objects.hash(this.packageName);
    }
}
