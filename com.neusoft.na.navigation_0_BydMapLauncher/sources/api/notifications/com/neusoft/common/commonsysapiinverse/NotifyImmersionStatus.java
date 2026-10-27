package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ImmersionStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyImmersionStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private ImmersionStatus immersionStatus;

    public NotifyImmersionStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ImmersionStatus");
    }

    public NotifyImmersionStatus(ImmersionStatus immersionStatus) {
        this();
        this.immersionStatus = immersionStatus;
    }

    public ImmersionStatus getImmersionStatus() {
        return this.immersionStatus;
    }

    public NotifyImmersionStatus setImmersionStatus(ImmersionStatus immersionStatus) {
        this.immersionStatus = immersionStatus;
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
