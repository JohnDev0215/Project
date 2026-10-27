package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyViewPrivacyDetails extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private DummyStatus viewPrivacyDetails;

    public NotifyViewPrivacyDetails() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.ViewPrivacyDetails");
    }

    public NotifyViewPrivacyDetails(DummyStatus dummyStatus) {
        this();
        this.viewPrivacyDetails = dummyStatus;
    }

    public DummyStatus getViewPrivacyDetails() {
        return this.viewPrivacyDetails;
    }

    public NotifyViewPrivacyDetails setViewPrivacyDetails(DummyStatus dummyStatus) {
        this.viewPrivacyDetails = dummyStatus;
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
