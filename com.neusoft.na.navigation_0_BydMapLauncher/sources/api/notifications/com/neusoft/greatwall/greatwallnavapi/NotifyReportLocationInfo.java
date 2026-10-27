package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.LocationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyReportLocationInfo extends BaseNotification {
    private static final long serialVersionUID = -1480914727;

    @SerializedName("args")
    private LocationInfo reportLocationInfo;

    public NotifyReportLocationInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.ReportLocationInfo");
    }

    public NotifyReportLocationInfo(LocationInfo locationInfo) {
        this();
        this.reportLocationInfo = locationInfo;
    }

    public LocationInfo getReportLocationInfo() {
        return this.reportLocationInfo;
    }

    public NotifyReportLocationInfo setReportLocationInfo(LocationInfo locationInfo) {
        this.reportLocationInfo = locationInfo;
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
