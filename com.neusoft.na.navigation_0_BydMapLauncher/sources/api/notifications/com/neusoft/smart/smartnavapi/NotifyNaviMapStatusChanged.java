package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.NavigationMapStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviMapStatusChanged extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private NavigationMapStatus naviMapStatusChanged;

    public NotifyNaviMapStatusChanged() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.NaviMapStatusChanged");
    }

    public NotifyNaviMapStatusChanged(NavigationMapStatus navigationMapStatus) {
        this();
        this.naviMapStatusChanged = navigationMapStatus;
    }

    public NavigationMapStatus getNaviMapStatusChanged() {
        return this.naviMapStatusChanged;
    }

    public NotifyNaviMapStatusChanged setNaviMapStatusChanged(NavigationMapStatus navigationMapStatus) {
        this.naviMapStatusChanged = navigationMapStatus;
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
