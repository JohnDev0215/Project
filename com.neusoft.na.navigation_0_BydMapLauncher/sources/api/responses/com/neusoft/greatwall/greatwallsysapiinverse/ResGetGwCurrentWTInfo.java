package api.responses.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.RequestGwWTInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetGwCurrentWTInfo extends BaseResponse {
    private static final long serialVersionUID = -1479992386;

    @SerializedName("args")
    private RequestGwWTInfo gwCurrentWTInfo;

    public ResGetGwCurrentWTInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.GetGwCurrentWTInfo");
    }

    public ResGetGwCurrentWTInfo(RequestGwWTInfo requestGwWTInfo) {
        this();
        this.gwCurrentWTInfo = requestGwWTInfo;
    }

    public RequestGwWTInfo getGwCurrentWTInfo() {
        return this.gwCurrentWTInfo;
    }

    public ResGetGwCurrentWTInfo setGwCurrentWTInfo(RequestGwWTInfo requestGwWTInfo) {
        this.gwCurrentWTInfo = requestGwWTInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.gwCurrentWTInfo = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetGwCurrentWTInfo)) {
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
