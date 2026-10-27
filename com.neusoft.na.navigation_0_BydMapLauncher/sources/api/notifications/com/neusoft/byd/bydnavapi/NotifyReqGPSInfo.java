package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.TokenId;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyReqGPSInfo extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private TokenId reqGPSInfo;

    public NotifyReqGPSInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.ReqGPSInfo");
    }

    public NotifyReqGPSInfo(TokenId tokenId) {
        this();
        this.reqGPSInfo = tokenId;
    }

    public TokenId getReqGPSInfo() {
        return this.reqGPSInfo;
    }

    public NotifyReqGPSInfo setReqGPSInfo(TokenId tokenId) {
        this.reqGPSInfo = tokenId;
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
