package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.RestoreGuidanceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyAvailableRestoreGuidance extends BaseNotification {
    private static final long serialVersionUID = -1481832546;

    @SerializedName("args")
    private RestoreGuidanceInfo availableRestoreGuidance;

    public NotifyAvailableRestoreGuidance() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.AvailableRestoreGuidance");
    }

    public NotifyAvailableRestoreGuidance(RestoreGuidanceInfo restoreGuidanceInfo) {
        this();
        this.availableRestoreGuidance = restoreGuidanceInfo;
    }

    public RestoreGuidanceInfo getAvailableRestoreGuidance() {
        return this.availableRestoreGuidance;
    }

    public NotifyAvailableRestoreGuidance setAvailableRestoreGuidance(RestoreGuidanceInfo restoreGuidanceInfo) {
        this.availableRestoreGuidance = restoreGuidanceInfo;
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
