package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class NaviPresentationInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140386;

    @SerializedName("PrivacyPolicyStatus")
    private Boolean privacyPolicyStatus = false;

    @SerializedName("ActivationStatus")
    private Boolean activationStatus = false;

    public NaviPresentationInfo() {
    }

    public NaviPresentationInfo(Boolean bool, Boolean bool2) {
        setPrivacyPolicyStatus(bool);
        setActivationStatus(bool2);
    }

    public Boolean getPrivacyPolicyStatus() {
        return this.privacyPolicyStatus;
    }

    public NaviPresentationInfo setPrivacyPolicyStatus(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("privacyPolicyStatus cannot be null");
        }
        this.privacyPolicyStatus = bool;
        return this;
    }

    public Boolean getActivationStatus() {
        return this.activationStatus;
    }

    public NaviPresentationInfo setActivationStatus(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("activationStatus cannot be null");
        }
        this.activationStatus = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NaviPresentationInfo naviPresentationInfo = (NaviPresentationInfo) obj;
        return Objects.equals(this.privacyPolicyStatus, naviPresentationInfo.privacyPolicyStatus) && Objects.equals(this.activationStatus, naviPresentationInfo.activationStatus);
    }

    public int hashCode() {
        return Objects.hash(this.privacyPolicyStatus, this.activationStatus);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
