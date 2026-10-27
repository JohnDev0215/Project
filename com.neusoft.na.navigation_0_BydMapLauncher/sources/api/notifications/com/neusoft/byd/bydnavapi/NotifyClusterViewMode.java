package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.ClusterViewMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyClusterViewMode extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private ClusterViewMode clusterViewMode;

    public NotifyClusterViewMode() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.ClusterViewMode");
    }

    public NotifyClusterViewMode(ClusterViewMode clusterViewMode) {
        this();
        this.clusterViewMode = clusterViewMode;
    }

    public ClusterViewMode getClusterViewMode() {
        return this.clusterViewMode;
    }

    public NotifyClusterViewMode setClusterViewMode(ClusterViewMode clusterViewMode) {
        this.clusterViewMode = clusterViewMode;
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
