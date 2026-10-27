package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PoiInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyArrivedLastMileUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private PoiInfo arrivedLastMileUpdate;

    public NotifyArrivedLastMileUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.ArrivedLastMileUpdate");
    }

    public NotifyArrivedLastMileUpdate(PoiInfo poiInfo) {
        this();
        this.arrivedLastMileUpdate = poiInfo;
    }

    public PoiInfo getArrivedLastMileUpdate() {
        return this.arrivedLastMileUpdate;
    }

    public NotifyArrivedLastMileUpdate setArrivedLastMileUpdate(PoiInfo poiInfo) {
        this.arrivedLastMileUpdate = poiInfo;
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
