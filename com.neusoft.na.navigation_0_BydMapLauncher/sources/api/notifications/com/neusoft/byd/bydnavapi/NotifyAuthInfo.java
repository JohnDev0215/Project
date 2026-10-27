package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.AuthConditionInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyAuthInfo extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private AuthConditionInfo authInfo;

    public NotifyAuthInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.AuthInfo");
    }

    public NotifyAuthInfo(AuthConditionInfo authConditionInfo) {
        this();
        this.authInfo = authConditionInfo;
    }

    public AuthConditionInfo getAuthInfo() {
        return this.authInfo;
    }

    public NotifyAuthInfo setAuthInfo(AuthConditionInfo authConditionInfo) {
        this.authInfo = authConditionInfo;
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
