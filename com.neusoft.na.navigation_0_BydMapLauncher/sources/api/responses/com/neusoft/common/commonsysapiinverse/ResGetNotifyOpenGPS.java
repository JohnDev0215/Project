package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.CommonBooleanInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetNotifyOpenGPS extends BaseResponse {
    private static final long serialVersionUID = -1478143457;

    @SerializedName("args")
    private CommonBooleanInfo notifyOpenGPS;

    public ResGetNotifyOpenGPS() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.GetNotifyOpenGPS");
    }

    public ResGetNotifyOpenGPS(CommonBooleanInfo commonBooleanInfo) {
        this();
        this.notifyOpenGPS = commonBooleanInfo;
    }

    public CommonBooleanInfo getNotifyOpenGPS() {
        return this.notifyOpenGPS;
    }

    public ResGetNotifyOpenGPS setNotifyOpenGPS(CommonBooleanInfo commonBooleanInfo) {
        this.notifyOpenGPS = commonBooleanInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.notifyOpenGPS = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetNotifyOpenGPS)) {
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
