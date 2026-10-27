package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCloseMainActivity extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private DummyStatus closeMainActivity;

    public NotifyCloseMainActivity() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.CloseMainActivity");
    }

    public NotifyCloseMainActivity(DummyStatus dummyStatus) {
        this();
        this.closeMainActivity = dummyStatus;
    }

    public DummyStatus getCloseMainActivity() {
        return this.closeMainActivity;
    }

    public NotifyCloseMainActivity setCloseMainActivity(DummyStatus dummyStatus) {
        this.closeMainActivity = dummyStatus;
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
