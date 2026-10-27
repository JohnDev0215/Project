package com.amazon.mobilepushfrontendappstateexternal;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.KiangService.ApplicationInformation;
import com.amazon.KiangService.PushInformation;

/* JADX INFO: loaded from: classes3.dex */
public class UpdateApplicationInstallRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontendappstateexternal.UpdateApplicationInstallRequest");
    private ApplicationInformation applicationInformation;
    private String applicationInstallId;
    private String localeId;
    private String marketplaceId;
    private String osNotificationState;
    private PushInformation pushInformation;
    private Integer sequenceNumber;

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public String getApplicationInstallId() {
        return this.applicationInstallId;
    }

    public void setApplicationInstallId(String str) {
        this.applicationInstallId = str;
    }

    public PushInformation getPushInformation() {
        return this.pushInformation;
    }

    public void setPushInformation(PushInformation pushInformation) {
        this.pushInformation = pushInformation;
    }

    public String getLocaleId() {
        return this.localeId;
    }

    public void setLocaleId(String str) {
        this.localeId = str;
    }

    public ApplicationInformation getApplicationInformation() {
        return this.applicationInformation;
    }

    public void setApplicationInformation(ApplicationInformation applicationInformation) {
        this.applicationInformation = applicationInformation;
    }

    public Integer getSequenceNumber() {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(Integer num) {
        this.sequenceNumber = num;
    }

    public String getOsNotificationState() {
        return this.osNotificationState;
    }

    public void setOsNotificationState(String str) {
        this.osNotificationState = str;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.marketplaceId, this.applicationInstallId, this.pushInformation, this.localeId, this.applicationInformation, this.sequenceNumber, this.osNotificationState);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof UpdateApplicationInstallRequest)) {
            return false;
        }
        UpdateApplicationInstallRequest updateApplicationInstallRequest = (UpdateApplicationInstallRequest) obj;
        return Helper.equals(this.marketplaceId, updateApplicationInstallRequest.marketplaceId) && Helper.equals(this.applicationInstallId, updateApplicationInstallRequest.applicationInstallId) && Helper.equals(this.pushInformation, updateApplicationInstallRequest.pushInformation) && Helper.equals(this.localeId, updateApplicationInstallRequest.localeId) && Helper.equals(this.applicationInformation, updateApplicationInstallRequest.applicationInformation) && Helper.equals(this.sequenceNumber, updateApplicationInstallRequest.sequenceNumber) && Helper.equals(this.osNotificationState, updateApplicationInstallRequest.osNotificationState);
    }
}
