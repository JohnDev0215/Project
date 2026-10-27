package com.amazon.KiangService;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ApplicationInformation implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.KiangService.ApplicationInformation");
    private Map<String, String> appMetaData;
    private String applicationIdentifier;
    private String applicationVersion;
    private String associateTag;
    private DeviceUniqueID deviceUniqueId;
    private String hardwareIdentifier;
    private String osIdentifier;
    private String osVersion;
    private String ubid;

    public Map<String, String> getAppMetaData() {
        return this.appMetaData;
    }

    public String getOsIdentifier() {
        return this.osIdentifier;
    }

    public void setOsIdentifier(String str) {
        this.osIdentifier = str;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public void setApplicationVersion(String str) {
        this.applicationVersion = str;
    }

    public String getApplicationIdentifier() {
        return this.applicationIdentifier;
    }

    public void setApplicationIdentifier(String str) {
        this.applicationIdentifier = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getHardwareIdentifier() {
        return this.hardwareIdentifier;
    }

    public void setHardwareIdentifier(String str) {
        this.hardwareIdentifier = str;
    }

    public String getAssociateTag() {
        return this.associateTag;
    }

    public void setAssociateTag(String str) {
        this.associateTag = str;
    }

    public DeviceUniqueID getDeviceUniqueId() {
        return this.deviceUniqueId;
    }

    public void setDeviceUniqueId(DeviceUniqueID deviceUniqueID) {
        this.deviceUniqueId = deviceUniqueID;
    }

    public String getUbid() {
        return this.ubid;
    }

    public void setUbid(String str) {
        this.ubid = str;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.appMetaData, this.osIdentifier, this.applicationVersion, this.applicationIdentifier, this.osVersion, this.hardwareIdentifier, this.associateTag, this.deviceUniqueId, this.ubid);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ApplicationInformation)) {
            return false;
        }
        ApplicationInformation applicationInformation = (ApplicationInformation) obj;
        return Helper.equals(this.appMetaData, applicationInformation.appMetaData) && Helper.equals(this.osIdentifier, applicationInformation.osIdentifier) && Helper.equals(this.applicationVersion, applicationInformation.applicationVersion) && Helper.equals(this.applicationIdentifier, applicationInformation.applicationIdentifier) && Helper.equals(this.osVersion, applicationInformation.osVersion) && Helper.equals(this.hardwareIdentifier, applicationInformation.hardwareIdentifier) && Helper.equals(this.associateTag, applicationInformation.associateTag) && Helper.equals(this.deviceUniqueId, applicationInformation.deviceUniqueId) && Helper.equals(this.ubid, applicationInformation.ubid);
    }
}
