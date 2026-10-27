package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.AuthorizedStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRespAuthorizePrivacyAgreement extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private AuthorizedStatus respAuthorizePrivacyAgreement;

    public NotifyRespAuthorizePrivacyAgreement() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.RespAuthorizePrivacyAgreement");
    }

    public NotifyRespAuthorizePrivacyAgreement(AuthorizedStatus authorizedStatus) {
        this();
        this.respAuthorizePrivacyAgreement = authorizedStatus;
    }

    public AuthorizedStatus getRespAuthorizePrivacyAgreement() {
        return this.respAuthorizePrivacyAgreement;
    }

    public NotifyRespAuthorizePrivacyAgreement setRespAuthorizePrivacyAgreement(AuthorizedStatus authorizedStatus) {
        this.respAuthorizePrivacyAgreement = authorizedStatus;
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
