package api.notifications.com.neusoft.ecarx.ecarxsysapiinverse;

import api.structs.com.neusoft.ecarx.TunnelEntranceExitStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyTunnelStatus extends BaseNotification {
    private static final long serialVersionUID = -1478146092;

    @SerializedName("args")
    private TunnelEntranceExitStatus tunnelStatus;

    public NotifyTunnelStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxSysApiInverse.TunnelStatus");
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
