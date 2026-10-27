package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.CountryInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCountryChangedStatus extends BaseNotification {
    private static final long serialVersionUID = -1480913982;

    @SerializedName("args")
    private CountryInfo countryChangedStatus;

    public NotifyCountryChangedStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.CountryChangedStatus");
    }

    public NotifyCountryChangedStatus(CountryInfo countryInfo) {
        this();
        this.countryChangedStatus = countryInfo;
    }

    public CountryInfo getCountryChangedStatus() {
        return this.countryChangedStatus;
    }

    public NotifyCountryChangedStatus setCountryChangedStatus(CountryInfo countryInfo) {
        this.countryChangedStatus = countryInfo;
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
