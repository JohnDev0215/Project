package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.DesktopMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySwitchDesktopMode extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private DesktopMode switchDesktopMode;

    public NotifySwitchDesktopMode() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.SwitchDesktopMode");
    }

    public NotifySwitchDesktopMode(DesktopMode desktopMode) {
        this();
        this.switchDesktopMode = desktopMode;
    }

    public DesktopMode getSwitchDesktopMode() {
        return this.switchDesktopMode;
    }

    public NotifySwitchDesktopMode setSwitchDesktopMode(DesktopMode desktopMode) {
        this.switchDesktopMode = desktopMode;
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
