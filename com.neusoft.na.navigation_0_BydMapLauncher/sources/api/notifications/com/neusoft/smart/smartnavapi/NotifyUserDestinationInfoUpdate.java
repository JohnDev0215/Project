package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.UserDestinationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyUserDestinationInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064808;

    @SerializedName("args")
    private UserDestinationInfo userDestinationInfoUpdate;

    public NotifyUserDestinationInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.UserDestinationInfoUpdate");
    }

    public NotifyUserDestinationInfoUpdate(UserDestinationInfo userDestinationInfo) {
        this();
        this.userDestinationInfoUpdate = userDestinationInfo;
    }

    public UserDestinationInfo getUserDestinationInfoUpdate() {
        return this.userDestinationInfoUpdate;
    }

    public NotifyUserDestinationInfoUpdate setUserDestinationInfoUpdate(UserDestinationInfo userDestinationInfo) {
        this.userDestinationInfoUpdate = userDestinationInfo;
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
