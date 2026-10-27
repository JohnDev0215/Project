package api.responses.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.ViewInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetCurrentViewInfoStatus extends BaseResponse {
    private static final long serialVersionUID = -1480915907;

    @SerializedName("args")
    private ViewInfo currentViewInfoStatus;

    public ResGetCurrentViewInfoStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.GetCurrentViewInfoStatus");
    }

    public ResGetCurrentViewInfoStatus(ViewInfo viewInfo) {
        this();
        this.currentViewInfoStatus = viewInfo;
    }

    public ViewInfo getCurrentViewInfoStatus() {
        return this.currentViewInfoStatus;
    }

    public ResGetCurrentViewInfoStatus setCurrentViewInfoStatus(ViewInfo viewInfo) {
        this.currentViewInfoStatus = viewInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.currentViewInfoStatus = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetCurrentViewInfoStatus)) {
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
