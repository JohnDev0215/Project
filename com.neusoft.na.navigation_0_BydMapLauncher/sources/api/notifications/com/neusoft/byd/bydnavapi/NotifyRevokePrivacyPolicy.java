package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.RevokePrivacyPolicyInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRevokePrivacyPolicy extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private RevokePrivacyPolicyInfo revokePrivacyPolicy;

    public NotifyRevokePrivacyPolicy() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.RevokePrivacyPolicy");
    }

    public NotifyRevokePrivacyPolicy(RevokePrivacyPolicyInfo revokePrivacyPolicyInfo) {
        this();
        this.revokePrivacyPolicy = revokePrivacyPolicyInfo;
    }

    public RevokePrivacyPolicyInfo getRevokePrivacyPolicy() {
        return this.revokePrivacyPolicy;
    }

    public NotifyRevokePrivacyPolicy setRevokePrivacyPolicy(RevokePrivacyPolicyInfo revokePrivacyPolicyInfo) {
        this.revokePrivacyPolicy = revokePrivacyPolicyInfo;
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
