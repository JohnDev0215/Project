package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.MuteType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviMuteStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914727;

    @SerializedName("args")
    private MuteType naviMuteStatus;

    public NotifyNaviMuteStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.NaviMuteStatus");
    }

    public NotifyNaviMuteStatus(MuteType muteType) {
        this();
        this.naviMuteStatus = muteType;
    }

    public MuteType getNaviMuteStatus() {
        return this.naviMuteStatus;
    }

    public NotifyNaviMuteStatus setNaviMuteStatus(MuteType muteType) {
        this.naviMuteStatus = muteType;
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
