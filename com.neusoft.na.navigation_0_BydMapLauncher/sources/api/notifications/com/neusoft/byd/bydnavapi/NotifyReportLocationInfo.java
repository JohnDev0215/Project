package api.notifications.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.LocationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyReportLocationInfo extends BaseNotification {
    private static final long serialVersionUID = -1479993225;

    @SerializedName("args")
    private LocationInfo reportLocationInfo;

    public NotifyReportLocationInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.byd.BydNavApi.ReportLocationInfo");
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
