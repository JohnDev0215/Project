package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.GuidedInfoStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGuidanceInfoStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914019;

    @SerializedName("args")
    private GuidedInfoStatus guidanceInfoStatus;

    public NotifyGuidanceInfoStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.GuidanceInfoStatus");
    }

    public NotifyGuidanceInfoStatus(GuidedInfoStatus guidedInfoStatus) {
        this();
        this.guidanceInfoStatus = guidedInfoStatus;
    }

    public GuidedInfoStatus getGuidanceInfoStatus() {
        return this.guidanceInfoStatus;
    }

    public NotifyGuidanceInfoStatus setGuidanceInfoStatus(GuidedInfoStatus guidedInfoStatus) {
        this.guidanceInfoStatus = guidedInfoStatus;
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
