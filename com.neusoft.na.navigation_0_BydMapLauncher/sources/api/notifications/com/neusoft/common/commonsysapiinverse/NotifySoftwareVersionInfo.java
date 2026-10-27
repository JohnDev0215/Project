package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.CommonStringInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySoftwareVersionInfo extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private CommonStringInfo softwareVersionInfo;

    public NotifySoftwareVersionInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SoftwareVersionInfo");
    }

    public NotifySoftwareVersionInfo(CommonStringInfo commonStringInfo) {
        this();
        this.softwareVersionInfo = commonStringInfo;
    }

    public CommonStringInfo getSoftwareVersionInfo() {
        return this.softwareVersionInfo;
    }

    public NotifySoftwareVersionInfo setSoftwareVersionInfo(CommonStringInfo commonStringInfo) {
        this.softwareVersionInfo = commonStringInfo;
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
