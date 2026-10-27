package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.CurrentLocationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCurrentLocation extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private CurrentLocationInfo currentLocation;

    public NotifyCurrentLocation() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.CurrentLocation");
    }

    public NotifyCurrentLocation(CurrentLocationInfo currentLocationInfo) {
        this();
        this.currentLocation = currentLocationInfo;
    }

    public CurrentLocationInfo getCurrentLocation() {
        return this.currentLocation;
    }

    public NotifyCurrentLocation setCurrentLocation(CurrentLocationInfo currentLocationInfo) {
        this.currentLocation = currentLocationInfo;
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
