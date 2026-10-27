package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.NavAPPRunningStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNavigationRunningStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141529;

    @SerializedName("args")
    private NavAPPRunningStatus navigationRunningStatus;

    public NotifyNavigationRunningStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.NavigationRunningStatus");
    }

    public NotifyNavigationRunningStatus(NavAPPRunningStatus navAPPRunningStatus) {
        this();
        this.navigationRunningStatus = navAPPRunningStatus;
    }

    public NavAPPRunningStatus getNavigationRunningStatus() {
        return this.navigationRunningStatus;
    }

    public NotifyNavigationRunningStatus setNavigationRunningStatus(NavAPPRunningStatus navAPPRunningStatus) {
        this.navigationRunningStatus = navAPPRunningStatus;
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
