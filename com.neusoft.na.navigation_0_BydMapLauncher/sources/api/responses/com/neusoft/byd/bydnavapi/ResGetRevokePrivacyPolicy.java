package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.RevokePrivacyPolicyInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetRevokePrivacyPolicy extends BaseResponse {
    private static final long serialVersionUID = -1480908128;

    @SerializedName("args")
    private RevokePrivacyPolicyInfo revokePrivacyPolicy;

    public ResGetRevokePrivacyPolicy() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.GetRevokePrivacyPolicy");
    }

    public ResGetRevokePrivacyPolicy(RevokePrivacyPolicyInfo revokePrivacyPolicyInfo) {
        this();
        this.revokePrivacyPolicy = revokePrivacyPolicyInfo;
    }

    public RevokePrivacyPolicyInfo getRevokePrivacyPolicy() {
        return this.revokePrivacyPolicy;
    }

    public ResGetRevokePrivacyPolicy setRevokePrivacyPolicy(RevokePrivacyPolicyInfo revokePrivacyPolicyInfo) {
        this.revokePrivacyPolicy = revokePrivacyPolicyInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.revokePrivacyPolicy = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetRevokePrivacyPolicy)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OneCore;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
