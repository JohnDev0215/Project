package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.RateLimiting;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySpeedLimitStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914019;

    @SerializedName("args")
    private RateLimiting speedLimitStatus;

    public NotifySpeedLimitStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.SpeedLimitStatus");
    }

    public NotifySpeedLimitStatus(RateLimiting rateLimiting) {
        this();
        this.speedLimitStatus = rateLimiting;
    }

    public RateLimiting getSpeedLimitStatus() {
        return this.speedLimitStatus;
    }

    public NotifySpeedLimitStatus setSpeedLimitStatus(RateLimiting rateLimiting) {
        this.speedLimitStatus = rateLimiting;
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
