package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.SoftwareVerInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviSoftwareVersionInfo extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private SoftwareVerInfo naviSoftwareVersionInfo;

    public NotifyNaviSoftwareVersionInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.NaviSoftwareVersionInfo");
    }

    public NotifyNaviSoftwareVersionInfo(SoftwareVerInfo softwareVerInfo) {
        this();
        this.naviSoftwareVersionInfo = softwareVerInfo;
    }

    public SoftwareVerInfo getNaviSoftwareVersionInfo() {
        return this.naviSoftwareVersionInfo;
    }

    public NotifyNaviSoftwareVersionInfo setNaviSoftwareVersionInfo(SoftwareVerInfo softwareVerInfo) {
        this.naviSoftwareVersionInfo = softwareVerInfo;
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
