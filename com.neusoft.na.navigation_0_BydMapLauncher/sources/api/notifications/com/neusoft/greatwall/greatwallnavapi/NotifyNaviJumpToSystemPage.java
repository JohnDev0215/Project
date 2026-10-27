package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.SystemPage;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviJumpToSystemPage extends BaseNotification {
    private static final long serialVersionUID = -1480913982;

    @SerializedName("args")
    private SystemPage naviJumpToSystemPage;

    public NotifyNaviJumpToSystemPage() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.NaviJumpToSystemPage");
    }

    public NotifyNaviJumpToSystemPage(SystemPage systemPage) {
        this();
        this.naviJumpToSystemPage = systemPage;
    }

    public SystemPage getNaviJumpToSystemPage() {
        return this.naviJumpToSystemPage;
    }

    public NotifyNaviJumpToSystemPage setNaviJumpToSystemPage(SystemPage systemPage) {
        this.naviJumpToSystemPage = systemPage;
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
