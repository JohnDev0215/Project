package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ActivateResultDetails;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyResponseExpiredDate extends BaseNotification {
    private static final long serialVersionUID = -1478141529;

    @SerializedName("args")
    private ActivateResultDetails responseExpiredDate;

    public NotifyResponseExpiredDate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ResponseExpiredDate");
    }

    public NotifyResponseExpiredDate(ActivateResultDetails activateResultDetails) {
        this();
        this.responseExpiredDate = activateResultDetails;
    }

    public ActivateResultDetails getResponseExpiredDate() {
        return this.responseExpiredDate;
    }

    public NotifyResponseExpiredDate setResponseExpiredDate(ActivateResultDetails activateResultDetails) {
        this.responseExpiredDate = activateResultDetails;
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
