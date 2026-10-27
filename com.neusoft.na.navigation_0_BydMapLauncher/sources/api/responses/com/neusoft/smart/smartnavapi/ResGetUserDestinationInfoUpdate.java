package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.UserDestinationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetUserDestinationInfoUpdate extends BaseResponse {
    private static final long serialVersionUID = -1479066791;

    @SerializedName("args")
    private UserDestinationInfo userDestinationInfoUpdate;

    public ResGetUserDestinationInfoUpdate() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetUserDestinationInfoUpdate");
    }

    public ResGetUserDestinationInfoUpdate(UserDestinationInfo userDestinationInfo) {
        this();
        this.userDestinationInfoUpdate = userDestinationInfo;
    }

    public UserDestinationInfo getUserDestinationInfoUpdate() {
        return this.userDestinationInfoUpdate;
    }

    public ResGetUserDestinationInfoUpdate setUserDestinationInfoUpdate(UserDestinationInfo userDestinationInfo) {
        this.userDestinationInfoUpdate = userDestinationInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.userDestinationInfoUpdate = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetUserDestinationInfoUpdate)) {
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
