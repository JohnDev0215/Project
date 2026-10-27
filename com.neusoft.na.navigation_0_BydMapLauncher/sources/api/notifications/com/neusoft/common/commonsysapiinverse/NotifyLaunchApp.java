package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyLaunchApp extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private DummyStatus launchApp;

    public NotifyLaunchApp() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.LaunchApp");
    }

    public NotifyLaunchApp(DummyStatus dummyStatus) {
        this();
        this.launchApp = dummyStatus;
    }

    public DummyStatus getLaunchApp() {
        return this.launchApp;
    }

    public NotifyLaunchApp setLaunchApp(DummyStatus dummyStatus) {
        this.launchApp = dummyStatus;
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
