package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCloseApp extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private DummyStatus closeApp;

    public NotifyCloseApp() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.CloseApp");
    }

    public NotifyCloseApp(DummyStatus dummyStatus) {
        this();
        this.closeApp = dummyStatus;
    }

    public DummyStatus getCloseApp() {
        return this.closeApp;
    }

    public NotifyCloseApp setCloseApp(DummyStatus dummyStatus) {
        this.closeApp = dummyStatus;
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
