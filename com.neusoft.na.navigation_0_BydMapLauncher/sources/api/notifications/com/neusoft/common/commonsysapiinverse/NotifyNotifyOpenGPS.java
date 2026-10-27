package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.CommonBooleanInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNotifyOpenGPS extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private CommonBooleanInfo notifyOpenGPS;

    public NotifyNotifyOpenGPS() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.NotifyOpenGPS");
    }

    public NotifyNotifyOpenGPS(CommonBooleanInfo commonBooleanInfo) {
        this();
        this.notifyOpenGPS = commonBooleanInfo;
    }

    public CommonBooleanInfo getNotifyOpenGPS() {
        return this.notifyOpenGPS;
    }

    public NotifyNotifyOpenGPS setNotifyOpenGPS(CommonBooleanInfo commonBooleanInfo) {
        this.notifyOpenGPS = commonBooleanInfo;
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
