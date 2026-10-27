package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestEnduranceMileage extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private DummyStatus requestEnduranceMileage;

    public NotifyRequestEnduranceMileage() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.RequestEnduranceMileage");
    }

    public NotifyRequestEnduranceMileage(DummyStatus dummyStatus) {
        this();
        this.requestEnduranceMileage = dummyStatus;
    }

    public DummyStatus getRequestEnduranceMileage() {
        return this.requestEnduranceMileage;
    }

    public NotifyRequestEnduranceMileage setRequestEnduranceMileage(DummyStatus dummyStatus) {
        this.requestEnduranceMileage = dummyStatus;
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
