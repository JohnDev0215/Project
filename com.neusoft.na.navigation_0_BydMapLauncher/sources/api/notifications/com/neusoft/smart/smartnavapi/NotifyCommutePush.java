package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.CommutePushInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCommutePush extends BaseNotification {
    private static final long serialVersionUID = -1479064063;

    @SerializedName("args")
    private CommutePushInfo commutePush;

    public NotifyCommutePush() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.CommutePush");
    }

    public NotifyCommutePush(CommutePushInfo commutePushInfo) {
        this();
        this.commutePush = commutePushInfo;
    }

    public CommutePushInfo getCommutePush() {
        return this.commutePush;
    }

    public NotifyCommutePush setCommutePush(CommutePushInfo commutePushInfo) {
        this.commutePush = commutePushInfo;
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
