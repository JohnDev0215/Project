package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.NaviPresentationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviPresentationInfo extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private NaviPresentationInfo naviPresentationInfo;

    public NotifyNaviPresentationInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.NaviPresentationInfo");
    }

    public NotifyNaviPresentationInfo(NaviPresentationInfo naviPresentationInfo) {
        this();
        this.naviPresentationInfo = naviPresentationInfo;
    }

    public NaviPresentationInfo getNaviPresentationInfo() {
        return this.naviPresentationInfo;
    }

    public NotifyNaviPresentationInfo setNaviPresentationInfo(NaviPresentationInfo naviPresentationInfo) {
        this.naviPresentationInfo = naviPresentationInfo;
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
