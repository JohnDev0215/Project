package api.responses.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.CongestedRoadEntranceExitStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetCongestedRoadStatus extends BaseResponse {
    private static final long serialVersionUID = -1479992386;

    @SerializedName("args")
    private CongestedRoadEntranceExitStatus congestedRoadStatus;

    public ResGetCongestedRoadStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.GetCongestedRoadStatus");
    }

    public ResGetCongestedRoadStatus(CongestedRoadEntranceExitStatus congestedRoadEntranceExitStatus) {
        this();
        this.congestedRoadStatus = congestedRoadEntranceExitStatus;
    }

    public CongestedRoadEntranceExitStatus getCongestedRoadStatus() {
        return this.congestedRoadStatus;
    }

    public ResGetCongestedRoadStatus setCongestedRoadStatus(CongestedRoadEntranceExitStatus congestedRoadEntranceExitStatus) {
        this.congestedRoadStatus = congestedRoadEntranceExitStatus;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.congestedRoadStatus = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetCongestedRoadStatus)) {
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
