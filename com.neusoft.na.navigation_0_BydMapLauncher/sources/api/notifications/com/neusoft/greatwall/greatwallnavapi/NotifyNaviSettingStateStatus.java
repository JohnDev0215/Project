package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.NaviSettingState;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviSettingStateStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914727;

    @SerializedName("args")
    private NaviSettingState naviSettingStateStatus;

    public NotifyNaviSettingStateStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.NaviSettingStateStatus");
    }

    public NotifyNaviSettingStateStatus(NaviSettingState naviSettingState) {
        this();
        this.naviSettingStateStatus = naviSettingState;
    }

    public NaviSettingState getNaviSettingStateStatus() {
        return this.naviSettingStateStatus;
    }

    public NotifyNaviSettingStateStatus setNaviSettingStateStatus(NaviSettingState naviSettingState) {
        this.naviSettingStateStatus = naviSettingState;
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
