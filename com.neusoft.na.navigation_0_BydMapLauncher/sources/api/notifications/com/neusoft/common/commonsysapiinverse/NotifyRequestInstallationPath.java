package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestInstallationPath extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private DummyStatus requestInstallationPath;

    public NotifyRequestInstallationPath() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.RequestInstallationPath");
    }

    public NotifyRequestInstallationPath(DummyStatus dummyStatus) {
        this();
        this.requestInstallationPath = dummyStatus;
    }

    public DummyStatus getRequestInstallationPath() {
        return this.requestInstallationPath;
    }

    public NotifyRequestInstallationPath setRequestInstallationPath(DummyStatus dummyStatus) {
        this.requestInstallationPath = dummyStatus;
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
