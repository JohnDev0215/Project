package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.CommuteStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCommuteInfo extends BaseNotification {
    private static final long serialVersionUID = -1479063969;

    @SerializedName("args")
    private CommuteStatus commuteInfo;

    public NotifyCommuteInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.CommuteInfo");
    }

    public NotifyCommuteInfo(CommuteStatus commuteStatus) {
        this();
        this.commuteInfo = commuteStatus;
    }

    public CommuteStatus getCommuteInfo() {
        return this.commuteInfo;
    }

    public NotifyCommuteInfo setCommuteInfo(CommuteStatus commuteStatus) {
        this.commuteInfo = commuteStatus;
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
