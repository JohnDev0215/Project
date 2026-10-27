package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.DelPrivacyPolicyInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDelPrivacyPolicyInfo extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private DelPrivacyPolicyInfo delPrivacyPolicyInfo;

    public NotifyDelPrivacyPolicyInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.DelPrivacyPolicyInfo");
    }

    public NotifyDelPrivacyPolicyInfo(DelPrivacyPolicyInfo delPrivacyPolicyInfo) {
        this();
        this.delPrivacyPolicyInfo = delPrivacyPolicyInfo;
    }

    public DelPrivacyPolicyInfo getDelPrivacyPolicyInfo() {
        return this.delPrivacyPolicyInfo;
    }

    public NotifyDelPrivacyPolicyInfo setDelPrivacyPolicyInfo(DelPrivacyPolicyInfo delPrivacyPolicyInfo) {
        this.delPrivacyPolicyInfo = delPrivacyPolicyInfo;
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
