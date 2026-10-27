package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.DestHistoryInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDestinationHistoryListStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914019;

    @SerializedName("args")
    private List<DestHistoryInfo> destinationHistoryListStatus;

    public NotifyDestinationHistoryListStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.DestinationHistoryListStatus");
    }

    public NotifyDestinationHistoryListStatus(List<DestHistoryInfo> list) {
        this();
        this.destinationHistoryListStatus = list;
    }

    public List<DestHistoryInfo> getDestinationHistoryListStatus() {
        return this.destinationHistoryListStatus;
    }

    public NotifyDestinationHistoryListStatus setDestinationHistoryListStatus(List<DestHistoryInfo> list) {
        if (list == null) {
            throw new NullPointerException("destinationHistoryListStatus cannot be null");
        }
        this.destinationHistoryListStatus = list;
        return this;
    }

    public NotifyDestinationHistoryListStatus setDestinationHistoryListStatus(DestHistoryInfo... destHistoryInfoArr) {
        return setDestinationHistoryListStatus(Arrays.asList(destHistoryInfoArr));
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
