package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.NavigationStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviStatusChanged extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private NavigationStatus naviStatusChanged;

    public NotifyNaviStatusChanged() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.NaviStatusChanged");
    }

    public NotifyNaviStatusChanged(NavigationStatus navigationStatus) {
        this();
        this.naviStatusChanged = navigationStatus;
    }

    public NavigationStatus getNaviStatusChanged() {
        return this.naviStatusChanged;
    }

    public NotifyNaviStatusChanged setNaviStatusChanged(NavigationStatus navigationStatus) {
        this.naviStatusChanged = navigationStatus;
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
