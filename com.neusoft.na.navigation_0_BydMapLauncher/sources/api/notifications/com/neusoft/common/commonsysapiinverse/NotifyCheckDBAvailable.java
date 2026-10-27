package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCheckDBAvailable extends BaseNotification {
    private static final long serialVersionUID = -1478141529;

    @SerializedName("args")
    private DummyStatus checkDBAvailable;

    public NotifyCheckDBAvailable() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.CheckDBAvailable");
    }

    public NotifyCheckDBAvailable(DummyStatus dummyStatus) {
        this();
        this.checkDBAvailable = dummyStatus;
    }

    public DummyStatus getCheckDBAvailable() {
        return this.checkDBAvailable;
    }

    public NotifyCheckDBAvailable setCheckDBAvailable(DummyStatus dummyStatus) {
        this.checkDBAvailable = dummyStatus;
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
