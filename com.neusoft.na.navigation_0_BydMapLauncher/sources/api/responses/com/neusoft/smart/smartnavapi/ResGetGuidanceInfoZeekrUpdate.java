package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.GuidanceInfoZeekr;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetGuidanceInfoZeekrUpdate extends BaseResponse {
    private static final long serialVersionUID = -1479066755;

    @SerializedName("args")
    private GuidanceInfoZeekr guidanceInfoZeekrUpdate;

    public ResGetGuidanceInfoZeekrUpdate() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetGuidanceInfoZeekrUpdate");
    }

    public ResGetGuidanceInfoZeekrUpdate(GuidanceInfoZeekr guidanceInfoZeekr) {
        this();
        this.guidanceInfoZeekrUpdate = guidanceInfoZeekr;
    }

    public GuidanceInfoZeekr getGuidanceInfoZeekrUpdate() {
        return this.guidanceInfoZeekrUpdate;
    }

    public ResGetGuidanceInfoZeekrUpdate setGuidanceInfoZeekrUpdate(GuidanceInfoZeekr guidanceInfoZeekr) {
        this.guidanceInfoZeekrUpdate = guidanceInfoZeekr;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.guidanceInfoZeekrUpdate = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetGuidanceInfoZeekrUpdate)) {
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
