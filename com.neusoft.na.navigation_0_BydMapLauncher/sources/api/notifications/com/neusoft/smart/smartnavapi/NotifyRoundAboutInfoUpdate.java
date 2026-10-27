package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.RoundAboutInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRoundAboutInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064808;

    @SerializedName("args")
    private RoundAboutInfo roundAboutInfoUpdate;

    public NotifyRoundAboutInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.RoundAboutInfoUpdate");
    }

    public NotifyRoundAboutInfoUpdate(RoundAboutInfo roundAboutInfo) {
        this();
        this.roundAboutInfoUpdate = roundAboutInfo;
    }

    public RoundAboutInfo getRoundAboutInfoUpdate() {
        return this.roundAboutInfoUpdate;
    }

    public NotifyRoundAboutInfoUpdate setRoundAboutInfoUpdate(RoundAboutInfo roundAboutInfo) {
        this.roundAboutInfoUpdate = roundAboutInfo;
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
