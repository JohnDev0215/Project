package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.ViewInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCurrentViewInfoStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914019;

    @SerializedName("args")
    private ViewInfo currentViewInfoStatus;

    public NotifyCurrentViewInfoStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.CurrentViewInfoStatus");
    }

    public NotifyCurrentViewInfoStatus(ViewInfo viewInfo) {
        this();
        this.currentViewInfoStatus = viewInfo;
    }

    public ViewInfo getCurrentViewInfoStatus() {
        return this.currentViewInfoStatus;
    }

    public NotifyCurrentViewInfoStatus setCurrentViewInfoStatus(ViewInfo viewInfo) {
        this.currentViewInfoStatus = viewInfo;
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
