package api.notifications.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.TunnelEntranceExitStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyTunnelStatus extends BaseNotification {
    private static final long serialVersionUID = -1479992328;

    @SerializedName("args")
    private TunnelEntranceExitStatus tunnelStatus;

    public NotifyTunnelStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.TunnelStatus");
    }

    public NotifyTunnelStatus(TunnelEntranceExitStatus tunnelEntranceExitStatus) {
        this();
        this.tunnelStatus = tunnelEntranceExitStatus;
    }

    public TunnelEntranceExitStatus getTunnelStatus() {
        return this.tunnelStatus;
    }

    public NotifyTunnelStatus setTunnelStatus(TunnelEntranceExitStatus tunnelEntranceExitStatus) {
        this.tunnelStatus = tunnelEntranceExitStatus;
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
