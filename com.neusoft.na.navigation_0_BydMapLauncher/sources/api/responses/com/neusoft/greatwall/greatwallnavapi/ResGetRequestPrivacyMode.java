package api.responses.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.PrivacyModeCommand;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetRequestPrivacyMode extends BaseResponse {
    private static final long serialVersionUID = -1480915907;

    @SerializedName("args")
    private PrivacyModeCommand requestPrivacyMode;

    public ResGetRequestPrivacyMode() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.GetRequestPrivacyMode");
    }

    public ResGetRequestPrivacyMode(PrivacyModeCommand privacyModeCommand) {
        this();
        this.requestPrivacyMode = privacyModeCommand;
    }

    public PrivacyModeCommand getRequestPrivacyMode() {
        return this.requestPrivacyMode;
    }

    public ResGetRequestPrivacyMode setRequestPrivacyMode(PrivacyModeCommand privacyModeCommand) {
        this.requestPrivacyMode = privacyModeCommand;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.requestPrivacyMode = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetRequestPrivacyMode)) {
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
