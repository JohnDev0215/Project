package api.notifications.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.RequestGwWTInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGwCurrentWTInfo extends BaseNotification {
    private static final long serialVersionUID = -1479992328;

    @SerializedName("args")
    private RequestGwWTInfo gwCurrentWTInfo;

    public NotifyGwCurrentWTInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.GwCurrentWTInfo");
    }

    public NotifyGwCurrentWTInfo(RequestGwWTInfo requestGwWTInfo) {
        this();
        this.gwCurrentWTInfo = requestGwWTInfo;
    }

    public RequestGwWTInfo getGwCurrentWTInfo() {
        return this.gwCurrentWTInfo;
    }

    public NotifyGwCurrentWTInfo setGwCurrentWTInfo(RequestGwWTInfo requestGwWTInfo) {
        this.gwCurrentWTInfo = requestGwWTInfo;
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
