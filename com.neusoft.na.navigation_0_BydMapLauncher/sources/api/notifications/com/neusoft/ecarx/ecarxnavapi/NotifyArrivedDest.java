package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.ResultCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyArrivedDest extends BaseNotification {
    private static final long serialVersionUID = -1481832583;

    @SerializedName("args")
    private ResultCode arrivedDest;

    public NotifyArrivedDest() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.ArrivedDest");
    }

    public NotifyArrivedDest(ResultCode resultCode) {
        this();
        this.arrivedDest = resultCode;
    }

    public ResultCode getArrivedDest() {
        return this.arrivedDest;
    }

    public NotifyArrivedDest setArrivedDest(ResultCode resultCode) {
        this.arrivedDest = resultCode;
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
