package api.responses.com.neusoft.ecarx.ecarxsysapiinverse;

import api.structs.com.neusoft.ecarx.TunnelEntranceExitStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetTunnelStatus extends BaseResponse {
    private static final long serialVersionUID = -1478146149;

    @SerializedName("args")
    private TunnelEntranceExitStatus tunnelStatus;

    public ResGetTunnelStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxSysApiInverse.GetTunnelStatus");
    }

    public ResGetTunnelStatus(TunnelEntranceExitStatus tunnelEntranceExitStatus) {
        this();
        this.tunnelStatus = tunnelEntranceExitStatus;
    }

    public TunnelEntranceExitStatus getTunnelStatus() {
        return this.tunnelStatus;
    }

    public ResGetTunnelStatus setTunnelStatus(TunnelEntranceExitStatus tunnelEntranceExitStatus) {
        this.tunnelStatus = tunnelEntranceExitStatus;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.tunnelStatus = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetTunnelStatus)) {
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
