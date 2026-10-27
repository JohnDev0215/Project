package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.DelPrivacyPolicyInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetDelPrivacyPolicyInfo extends BaseResponse {
    private static final long serialVersionUID = -1480908128;

    @SerializedName("args")
    private DelPrivacyPolicyInfo delPrivacyPolicyInfo;

    public ResGetDelPrivacyPolicyInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.GetDelPrivacyPolicyInfo");
    }

    public ResGetDelPrivacyPolicyInfo(DelPrivacyPolicyInfo delPrivacyPolicyInfo) {
        this();
        this.delPrivacyPolicyInfo = delPrivacyPolicyInfo;
    }

    public DelPrivacyPolicyInfo getDelPrivacyPolicyInfo() {
        return this.delPrivacyPolicyInfo;
    }

    public ResGetDelPrivacyPolicyInfo setDelPrivacyPolicyInfo(DelPrivacyPolicyInfo delPrivacyPolicyInfo) {
        this.delPrivacyPolicyInfo = delPrivacyPolicyInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.delPrivacyPolicyInfo = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetDelPrivacyPolicyInfo)) {
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
