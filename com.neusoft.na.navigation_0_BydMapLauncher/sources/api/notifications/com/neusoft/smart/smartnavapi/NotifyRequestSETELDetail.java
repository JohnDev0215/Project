package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.RawSETELDetailDataInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestSETELDetail extends BaseNotification {
    private static final long serialVersionUID = -1479064063;

    @SerializedName("args")
    private RawSETELDetailDataInfo requestSETELDetail;

    public NotifyRequestSETELDetail() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.RequestSETELDetail");
    }

    public NotifyRequestSETELDetail(RawSETELDetailDataInfo rawSETELDetailDataInfo) {
        this();
        this.requestSETELDetail = rawSETELDetailDataInfo;
    }

    public RawSETELDetailDataInfo getRequestSETELDetail() {
        return this.requestSETELDetail;
    }

    public NotifyRequestSETELDetail setRequestSETELDetail(RawSETELDetailDataInfo rawSETELDetailDataInfo) {
        this.requestSETELDetail = rawSETELDetailDataInfo;
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
