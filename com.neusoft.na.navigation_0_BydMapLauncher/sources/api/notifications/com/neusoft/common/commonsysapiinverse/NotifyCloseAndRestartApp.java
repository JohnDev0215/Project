package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCloseAndRestartApp extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private DummyStatus closeAndRestartApp;

    public NotifyCloseAndRestartApp() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.CloseAndRestartApp");
    }

    public NotifyCloseAndRestartApp(DummyStatus dummyStatus) {
        this();
        this.closeAndRestartApp = dummyStatus;
    }

    public DummyStatus getCloseAndRestartApp() {
        return this.closeAndRestartApp;
    }

    public NotifyCloseAndRestartApp setCloseAndRestartApp(DummyStatus dummyStatus) {
        this.closeAndRestartApp = dummyStatus;
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
