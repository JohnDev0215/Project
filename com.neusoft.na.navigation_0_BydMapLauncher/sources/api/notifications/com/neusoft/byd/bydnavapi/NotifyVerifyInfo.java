package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.VerifyConditionInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyVerifyInfo extends BaseNotification {
    private static final long serialVersionUID = -1479993189;

    @SerializedName("args")
    private VerifyConditionInfo verifyInfo;

    public NotifyVerifyInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.VerifyInfo");
    }

    public NotifyVerifyInfo(VerifyConditionInfo verifyConditionInfo) {
        this();
        this.verifyInfo = verifyConditionInfo;
    }

    public VerifyConditionInfo getVerifyInfo() {
        return this.verifyInfo;
    }

    public NotifyVerifyInfo setVerifyInfo(VerifyConditionInfo verifyConditionInfo) {
        this.verifyInfo = verifyConditionInfo;
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
