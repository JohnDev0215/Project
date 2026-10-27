package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.PhoneInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCallUpStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private PhoneInfo callUpStatus;

    public NotifyCallUpStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.CallUpStatus");
    }

    public NotifyCallUpStatus(PhoneInfo phoneInfo) {
        this();
        this.callUpStatus = phoneInfo;
    }

    public PhoneInfo getCallUpStatus() {
        return this.callUpStatus;
    }

    public NotifyCallUpStatus setCallUpStatus(PhoneInfo phoneInfo) {
        this.callUpStatus = phoneInfo;
        return this;
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
