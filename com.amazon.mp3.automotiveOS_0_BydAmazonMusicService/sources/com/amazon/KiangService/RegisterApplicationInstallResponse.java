package com.amazon.KiangService;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes2.dex */
public class RegisterApplicationInstallResponse implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.KiangService.RegisterApplicationInstallResponse");
    private String applicationInstallId;

    public String getApplicationInstallId() {
        return this.applicationInstallId;
    }

    public void setApplicationInstallId(String str) {
        this.applicationInstallId = str;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.applicationInstallId);
    }

    public boolean equals(Object obj) {
        if (obj instanceof RegisterApplicationInstallResponse) {
            return Helper.equals(this.applicationInstallId, ((RegisterApplicationInstallResponse) obj).applicationInstallId);
        }
        return false;
    }
}
