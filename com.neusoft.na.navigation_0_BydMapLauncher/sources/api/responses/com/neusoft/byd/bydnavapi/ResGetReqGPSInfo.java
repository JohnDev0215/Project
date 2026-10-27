package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.TokenId;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetReqGPSInfo extends BaseResponse {
    private static final long serialVersionUID = -1480908128;

    @SerializedName("args")
    private TokenId reqGPSInfo;

    public ResGetReqGPSInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.GetReqGPSInfo");
    }

    public ResGetReqGPSInfo(TokenId tokenId) {
        this();
        this.reqGPSInfo = tokenId;
    }

    public TokenId getReqGPSInfo() {
        return this.reqGPSInfo;
    }

    public ResGetReqGPSInfo setReqGPSInfo(TokenId tokenId) {
        this.reqGPSInfo = tokenId;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.reqGPSInfo = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetReqGPSInfo)) {
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
