package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySwitchNaviToBackground extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private DummyStatus switchNaviToBackground;

    public NotifySwitchNaviToBackground() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SwitchNaviToBackground");
    }

    public NotifySwitchNaviToBackground(DummyStatus dummyStatus) {
        this();
        this.switchNaviToBackground = dummyStatus;
    }

    public DummyStatus getSwitchNaviToBackground() {
        return this.switchNaviToBackground;
    }

    public NotifySwitchNaviToBackground setSwitchNaviToBackground(DummyStatus dummyStatus) {
        this.switchNaviToBackground = dummyStatus;
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
