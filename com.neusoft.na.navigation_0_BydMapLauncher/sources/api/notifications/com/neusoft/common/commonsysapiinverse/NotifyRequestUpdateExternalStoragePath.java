package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestUpdateExternalStoragePath extends BaseNotification {
    private static final long serialVersionUID = -1478141529;

    @SerializedName("args")
    private DummyStatus requestUpdateExternalStoragePath;

    public NotifyRequestUpdateExternalStoragePath() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.RequestUpdateExternalStoragePath");
    }

    public NotifyRequestUpdateExternalStoragePath(DummyStatus dummyStatus) {
        this();
        this.requestUpdateExternalStoragePath = dummyStatus;
    }

    public DummyStatus getRequestUpdateExternalStoragePath() {
        return this.requestUpdateExternalStoragePath;
    }

    public NotifyRequestUpdateExternalStoragePath setRequestUpdateExternalStoragePath(DummyStatus dummyStatus) {
        this.requestUpdateExternalStoragePath = dummyStatus;
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
