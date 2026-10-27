package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ActivateCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyAccessCodingAvailable extends BaseNotification {
    private static final long serialVersionUID = -1478141529;

    @SerializedName("args")
    private ActivateCode accessCodingAvailable;

    public NotifyAccessCodingAvailable() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.AccessCodingAvailable");
    }

    public NotifyAccessCodingAvailable(ActivateCode activateCode) {
        this();
        this.accessCodingAvailable = activateCode;
    }

    public ActivateCode getAccessCodingAvailable() {
        return this.accessCodingAvailable;
    }

    public NotifyAccessCodingAvailable setAccessCodingAvailable(ActivateCode activateCode) {
        this.accessCodingAvailable = activateCode;
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
