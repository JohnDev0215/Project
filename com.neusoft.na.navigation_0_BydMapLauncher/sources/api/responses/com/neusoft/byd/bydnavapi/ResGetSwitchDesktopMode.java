package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.DesktopMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetSwitchDesktopMode extends BaseResponse {
    private static final long serialVersionUID = -1480908092;

    @SerializedName("args")
    private DesktopMode switchDesktopMode;

    public ResGetSwitchDesktopMode() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.GetSwitchDesktopMode");
    }

    public ResGetSwitchDesktopMode(DesktopMode desktopMode) {
        this();
        this.switchDesktopMode = desktopMode;
    }

    public DesktopMode getSwitchDesktopMode() {
        return this.switchDesktopMode;
    }

    public ResGetSwitchDesktopMode setSwitchDesktopMode(DesktopMode desktopMode) {
        this.switchDesktopMode = desktopMode;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.switchDesktopMode = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetSwitchDesktopMode)) {
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
