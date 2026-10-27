package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.TrafficConditionInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetTrafficConditionUpdate extends BaseResponse {
    private static final long serialVersionUID = -1479066046;

    @SerializedName("args")
    private TrafficConditionInfo trafficConditionUpdate;

    public ResGetTrafficConditionUpdate() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetTrafficConditionUpdate");
    }

    public ResGetTrafficConditionUpdate(TrafficConditionInfo trafficConditionInfo) {
        this();
        this.trafficConditionUpdate = trafficConditionInfo;
    }

    public TrafficConditionInfo getTrafficConditionUpdate() {
        return this.trafficConditionUpdate;
    }

    public ResGetTrafficConditionUpdate setTrafficConditionUpdate(TrafficConditionInfo trafficConditionInfo) {
        this.trafficConditionUpdate = trafficConditionInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.trafficConditionUpdate = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetTrafficConditionUpdate)) {
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
