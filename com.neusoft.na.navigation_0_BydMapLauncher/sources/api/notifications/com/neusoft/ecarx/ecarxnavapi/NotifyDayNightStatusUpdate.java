package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.DayNightStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDayNightStatusUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832698;

    @SerializedName("args")
    private DayNightStatus dayNightStatusUpdate;

    public NotifyDayNightStatusUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.DayNightStatusUpdate");
    }

    public NotifyDayNightStatusUpdate(DayNightStatus dayNightStatus) {
        this();
        this.dayNightStatusUpdate = dayNightStatus;
    }

    public DayNightStatus getDayNightStatusUpdate() {
        return this.dayNightStatusUpdate;
    }

    public NotifyDayNightStatusUpdate setDayNightStatusUpdate(DayNightStatus dayNightStatus) {
        this.dayNightStatusUpdate = dayNightStatus;
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
