package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.GuidedStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGuidanceStatus extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private GuidedStatus guidanceStatus;

    public NotifyGuidanceStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.GuidanceStatus");
    }

    public NotifyGuidanceStatus(GuidedStatus guidedStatus) {
        this();
        this.guidanceStatus = guidedStatus;
    }

    public GuidedStatus getGuidanceStatus() {
        return this.guidanceStatus;
    }

    public NotifyGuidanceStatus setGuidanceStatus(GuidedStatus guidedStatus) {
        this.guidanceStatus = guidedStatus;
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
