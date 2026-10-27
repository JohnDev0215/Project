package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.NaviHMIPageStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyIsNaviHMIFullScreenPage extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private NaviHMIPageStatus isNaviHMIFullScreenPage;

    public NotifyIsNaviHMIFullScreenPage() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.IsNaviHMIFullScreenPage");
    }

    public NotifyIsNaviHMIFullScreenPage(NaviHMIPageStatus naviHMIPageStatus) {
        this();
        this.isNaviHMIFullScreenPage = naviHMIPageStatus;
    }

    public NaviHMIPageStatus getIsNaviHMIFullScreenPage() {
        return this.isNaviHMIFullScreenPage;
    }

    public NotifyIsNaviHMIFullScreenPage setIsNaviHMIFullScreenPage(NaviHMIPageStatus naviHMIPageStatus) {
        this.isNaviHMIFullScreenPage = naviHMIPageStatus;
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
