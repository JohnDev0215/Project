package api.notifications.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.CongestedRoadEntranceExitStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCongestedRoadStatus extends BaseNotification {
    private static final long serialVersionUID = -1479992328;

    @SerializedName("args")
    private CongestedRoadEntranceExitStatus congestedRoadStatus;

    public NotifyCongestedRoadStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.CongestedRoadStatus");
    }

    public NotifyCongestedRoadStatus(CongestedRoadEntranceExitStatus congestedRoadEntranceExitStatus) {
        this();
        this.congestedRoadStatus = congestedRoadEntranceExitStatus;
    }

    public CongestedRoadEntranceExitStatus getCongestedRoadStatus() {
        return this.congestedRoadStatus;
    }

    public NotifyCongestedRoadStatus setCongestedRoadStatus(CongestedRoadEntranceExitStatus congestedRoadEntranceExitStatus) {
        this.congestedRoadStatus = congestedRoadEntranceExitStatus;
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
