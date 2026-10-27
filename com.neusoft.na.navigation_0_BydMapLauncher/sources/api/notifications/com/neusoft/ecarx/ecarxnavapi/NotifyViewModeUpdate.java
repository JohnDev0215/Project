package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.ViewModeStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyViewModeUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832640;

    @SerializedName("args")
    private ViewModeStatus viewModeUpdate;

    public NotifyViewModeUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.ViewModeUpdate");
    }

    public NotifyViewModeUpdate(ViewModeStatus viewModeStatus) {
        this();
        this.viewModeUpdate = viewModeStatus;
    }

    public ViewModeStatus getViewModeUpdate() {
        return this.viewModeUpdate;
    }

    public NotifyViewModeUpdate setViewModeUpdate(ViewModeStatus viewModeStatus) {
        this.viewModeUpdate = viewModeStatus;
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
