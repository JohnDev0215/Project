package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetViewPrivacyDetails extends BaseResponse {
    private static final long serialVersionUID = -1479065988;

    @SerializedName("args")
    private DummyStatus viewPrivacyDetails;

    public ResGetViewPrivacyDetails() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetViewPrivacyDetails");
    }

    public ResGetViewPrivacyDetails(DummyStatus dummyStatus) {
        this();
        this.viewPrivacyDetails = dummyStatus;
    }

    public DummyStatus getViewPrivacyDetails() {
        return this.viewPrivacyDetails;
    }

    public ResGetViewPrivacyDetails setViewPrivacyDetails(DummyStatus dummyStatus) {
        this.viewPrivacyDetails = dummyStatus;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.viewPrivacyDetails = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetViewPrivacyDetails)) {
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
