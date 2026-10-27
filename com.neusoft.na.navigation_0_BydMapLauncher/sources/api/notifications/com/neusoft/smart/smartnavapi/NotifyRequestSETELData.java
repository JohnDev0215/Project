package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.RawSETELDataInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestSETELData extends BaseNotification {
    private static final long serialVersionUID = -1479064063;

    @SerializedName("args")
    private RawSETELDataInfo requestSETELData;

    public NotifyRequestSETELData() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.RequestSETELData");
    }

    public NotifyRequestSETELData(RawSETELDataInfo rawSETELDataInfo) {
        this();
        this.requestSETELData = rawSETELDataInfo;
    }

    public RawSETELDataInfo getRequestSETELData() {
        return this.requestSETELData;
    }

    public NotifyRequestSETELData setRequestSETELData(RawSETELDataInfo rawSETELDataInfo) {
        this.requestSETELData = rawSETELDataInfo;
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
