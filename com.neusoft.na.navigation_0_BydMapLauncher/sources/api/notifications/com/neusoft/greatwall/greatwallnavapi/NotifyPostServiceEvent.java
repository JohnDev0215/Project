package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.ServiceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyPostServiceEvent extends BaseNotification {
    private static final long serialVersionUID = -1480913982;

    @SerializedName("args")
    private ServiceInfo postServiceEvent;

    public NotifyPostServiceEvent() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.PostServiceEvent");
    }

    public NotifyPostServiceEvent(ServiceInfo serviceInfo) {
        this();
        this.postServiceEvent = serviceInfo;
    }

    public ServiceInfo getPostServiceEvent() {
        return this.postServiceEvent;
    }

    public NotifyPostServiceEvent setPostServiceEvent(ServiceInfo serviceInfo) {
        this.postServiceEvent = serviceInfo;
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
