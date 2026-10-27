package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.BeepTone;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetPlayBeepTone extends BaseResponse {
    private static final long serialVersionUID = -1478144165;

    @SerializedName("args")
    private BeepTone playBeepTone;

    public ResGetPlayBeepTone() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.GetPlayBeepTone");
    }

    public ResGetPlayBeepTone(BeepTone beepTone) {
        this();
        this.playBeepTone = beepTone;
    }

    public BeepTone getPlayBeepTone() {
        return this.playBeepTone;
    }

    public ResGetPlayBeepTone setPlayBeepTone(BeepTone beepTone) {
        this.playBeepTone = beepTone;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.playBeepTone = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetPlayBeepTone)) {
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
