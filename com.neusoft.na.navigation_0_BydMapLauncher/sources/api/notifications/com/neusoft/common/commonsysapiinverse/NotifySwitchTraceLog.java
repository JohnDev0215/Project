package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.LogOnOff;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySwitchTraceLog extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private LogOnOff switchTraceLog;

    public NotifySwitchTraceLog() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SwitchTraceLog");
    }

    public NotifySwitchTraceLog(LogOnOff logOnOff) {
        this();
        this.switchTraceLog = logOnOff;
    }

    public LogOnOff getSwitchTraceLog() {
        return this.switchTraceLog;
    }

    public NotifySwitchTraceLog setSwitchTraceLog(LogOnOff logOnOff) {
        this.switchTraceLog = logOnOff;
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
