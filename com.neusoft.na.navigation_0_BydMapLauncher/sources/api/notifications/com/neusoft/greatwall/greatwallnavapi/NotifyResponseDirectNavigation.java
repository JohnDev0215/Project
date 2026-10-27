package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.LocationResult;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyResponseDirectNavigation extends BaseNotification {
    private static final long serialVersionUID = -1480914727;

    @SerializedName("args")
    private LocationResult responseDirectNavigation;

    public NotifyResponseDirectNavigation() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.ResponseDirectNavigation");
    }

    public NotifyResponseDirectNavigation(LocationResult locationResult) {
        this();
        this.responseDirectNavigation = locationResult;
    }

    public LocationResult getResponseDirectNavigation() {
        return this.responseDirectNavigation;
    }

    public NotifyResponseDirectNavigation setResponseDirectNavigation(LocationResult locationResult) {
        this.responseDirectNavigation = locationResult;
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
