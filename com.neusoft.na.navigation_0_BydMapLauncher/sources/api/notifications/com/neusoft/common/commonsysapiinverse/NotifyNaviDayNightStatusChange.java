package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.NaviDayNightStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviDayNightStatusChange extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private NaviDayNightStatus naviDayNightStatusChange;

    public NotifyNaviDayNightStatusChange() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.NaviDayNightStatusChange");
    }

    public NotifyNaviDayNightStatusChange(NaviDayNightStatus naviDayNightStatus) {
        this();
        this.naviDayNightStatusChange = naviDayNightStatus;
    }

    public NaviDayNightStatus getNaviDayNightStatusChange() {
        return this.naviDayNightStatusChange;
    }

    public NotifyNaviDayNightStatusChange setNaviDayNightStatusChange(NaviDayNightStatus naviDayNightStatus) {
        this.naviDayNightStatusChange = naviDayNightStatus;
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
