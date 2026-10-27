package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.CommonStringInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyLocalDBVersionInfo extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private CommonStringInfo localDBVersionInfo;

    public NotifyLocalDBVersionInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.LocalDBVersionInfo");
    }

    public NotifyLocalDBVersionInfo(CommonStringInfo commonStringInfo) {
        this();
        this.localDBVersionInfo = commonStringInfo;
    }

    public CommonStringInfo getLocalDBVersionInfo() {
        return this.localDBVersionInfo;
    }

    public NotifyLocalDBVersionInfo setLocalDBVersionInfo(CommonStringInfo commonStringInfo) {
        this.localDBVersionInfo = commonStringInfo;
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
