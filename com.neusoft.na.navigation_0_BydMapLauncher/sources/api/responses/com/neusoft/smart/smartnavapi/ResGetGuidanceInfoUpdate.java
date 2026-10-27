package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.GuidanceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetGuidanceInfoUpdate extends BaseResponse {
    private static final long serialVersionUID = -1479066791;

    @SerializedName("args")
    private GuidanceInfo guidanceInfoUpdate;

    public ResGetGuidanceInfoUpdate() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetGuidanceInfoUpdate");
    }

    public ResGetGuidanceInfoUpdate(GuidanceInfo guidanceInfo) {
        this();
        this.guidanceInfoUpdate = guidanceInfo;
    }

    public GuidanceInfo getGuidanceInfoUpdate() {
        return this.guidanceInfoUpdate;
    }

    public ResGetGuidanceInfoUpdate setGuidanceInfoUpdate(GuidanceInfo guidanceInfo) {
        this.guidanceInfoUpdate = guidanceInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.guidanceInfoUpdate = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetGuidanceInfoUpdate)) {
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
