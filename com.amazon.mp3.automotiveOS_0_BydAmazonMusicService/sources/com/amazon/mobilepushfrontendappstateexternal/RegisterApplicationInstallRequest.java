package com.amazon.mobilepushfrontendappstateexternal;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.KiangService.ApplicationInformation;
import com.amazon.KiangService.PushInformation;

/* JADX INFO: loaded from: classes3.dex */
public class RegisterApplicationInstallRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontendappstateexternal.RegisterApplicationInstallRequest");
    private ApplicationInformation applicationInformation;
    private PushInformation pushInformation;

    public ApplicationInformation getApplicationInformation() {
        return this.applicationInformation;
    }

    public void setApplicationInformation(ApplicationInformation applicationInformation) {
        this.applicationInformation = applicationInformation;
    }

    public PushInformation getPushInformation() {
        return this.pushInformation;
    }

    public void setPushInformation(PushInformation pushInformation) {
        this.pushInformation = pushInformation;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.applicationInformation, this.pushInformation);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RegisterApplicationInstallRequest)) {
            return false;
        }
        RegisterApplicationInstallRequest registerApplicationInstallRequest = (RegisterApplicationInstallRequest) obj;
        return Helper.equals(this.applicationInformation, registerApplicationInstallRequest.applicationInformation) && Helper.equals(this.pushInformation, registerApplicationInstallRequest.pushInformation);
    }
}
