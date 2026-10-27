package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyShowDiagnoseSetting extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private DummyStatus showDiagnoseSetting;

    public NotifyShowDiagnoseSetting() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ShowDiagnoseSetting");
    }

    public NotifyShowDiagnoseSetting(DummyStatus dummyStatus) {
        this();
        this.showDiagnoseSetting = dummyStatus;
    }

    public DummyStatus getShowDiagnoseSetting() {
        return this.showDiagnoseSetting;
    }

    public NotifyShowDiagnoseSetting setShowDiagnoseSetting(DummyStatus dummyStatus) {
        this.showDiagnoseSetting = dummyStatus;
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
