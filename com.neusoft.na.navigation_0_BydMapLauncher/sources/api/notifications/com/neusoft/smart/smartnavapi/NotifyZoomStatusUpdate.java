package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.ZoomStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyZoomStatusUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064808;

    @SerializedName("args")
    private ZoomStatus zoomStatusUpdate;

    public NotifyZoomStatusUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.ZoomStatusUpdate");
    }

    public NotifyZoomStatusUpdate(ZoomStatus zoomStatus) {
        this();
        this.zoomStatusUpdate = zoomStatus;
    }

    public ZoomStatus getZoomStatusUpdate() {
        return this.zoomStatusUpdate;
    }

    public NotifyZoomStatusUpdate setZoomStatusUpdate(ZoomStatus zoomStatus) {
        this.zoomStatusUpdate = zoomStatus;
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
