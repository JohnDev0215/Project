package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.MapStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyMapStatusUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064808;

    @SerializedName("args")
    private MapStatus mapStatusUpdate;

    public NotifyMapStatusUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.MapStatusUpdate");
    }

    public NotifyMapStatusUpdate(MapStatus mapStatus) {
        this();
        this.mapStatusUpdate = mapStatus;
    }

    public MapStatus getMapStatusUpdate() {
        return this.mapStatusUpdate;
    }

    public NotifyMapStatusUpdate setMapStatusUpdate(MapStatus mapStatus) {
        this.mapStatusUpdate = mapStatus;
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
