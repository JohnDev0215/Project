package api.notifications.com.neusoft.ecarx.ecarxsysapiinverse;

import api.structs.com.neusoft.ecarx.AuthorizationStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyAuthStatus extends BaseNotification {
    private static final long serialVersionUID = -1478145404;

    @SerializedName("args")
    private AuthorizationStatus authStatus;

    public NotifyAuthStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxSysApiInverse.AuthStatus");
    }

    public NotifyAuthStatus(AuthorizationStatus authorizationStatus) {
        this();
        this.authStatus = authorizationStatus;
    }

    public AuthorizationStatus getAuthStatus() {
        return this.authStatus;
    }

    public NotifyAuthStatus setAuthStatus(AuthorizationStatus authorizationStatus) {
        this.authStatus = authorizationStatus;
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
