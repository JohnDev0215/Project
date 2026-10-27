package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.MuteStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetMuteModeUpdate extends BaseResponse {
    private static final long serialVersionUID = -1479066754;

    @SerializedName("args")
    private MuteStatus muteModeUpdate;

    public ResGetMuteModeUpdate() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetMuteModeUpdate");
    }

    public ResGetMuteModeUpdate(MuteStatus muteStatus) {
        this();
        this.muteModeUpdate = muteStatus;
    }

    public MuteStatus getMuteModeUpdate() {
        return this.muteModeUpdate;
    }

    public ResGetMuteModeUpdate setMuteModeUpdate(MuteStatus muteStatus) {
        this.muteModeUpdate = muteStatus;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.muteModeUpdate = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetMuteModeUpdate)) {
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
