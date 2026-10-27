package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PoiInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetArrivedLastMileUpdate extends BaseResponse {
    private static final long serialVersionUID = -1479065988;

    @SerializedName("args")
    private PoiInfo arrivedLastMileUpdate;

    public ResGetArrivedLastMileUpdate() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetArrivedLastMileUpdate");
    }

    public ResGetArrivedLastMileUpdate(PoiInfo poiInfo) {
        this();
        this.arrivedLastMileUpdate = poiInfo;
    }

    public PoiInfo getArrivedLastMileUpdate() {
        return this.arrivedLastMileUpdate;
    }

    public ResGetArrivedLastMileUpdate setArrivedLastMileUpdate(PoiInfo poiInfo) {
        this.arrivedLastMileUpdate = poiInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.arrivedLastMileUpdate = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetArrivedLastMileUpdate)) {
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
