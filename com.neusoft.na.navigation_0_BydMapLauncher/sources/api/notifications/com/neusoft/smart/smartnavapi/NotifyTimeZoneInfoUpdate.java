package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.TimeZoneInformation;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyTimeZoneInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private TimeZoneInformation timeZoneInfoUpdate;

    public NotifyTimeZoneInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.TimeZoneInfoUpdate");
    }

    public NotifyTimeZoneInfoUpdate(TimeZoneInformation timeZoneInformation) {
        this();
        this.timeZoneInfoUpdate = timeZoneInformation;
    }

    public TimeZoneInformation getTimeZoneInfoUpdate() {
        return this.timeZoneInfoUpdate;
    }

    public NotifyTimeZoneInfoUpdate setTimeZoneInfoUpdate(TimeZoneInformation timeZoneInformation) {
        this.timeZoneInfoUpdate = timeZoneInformation;
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
