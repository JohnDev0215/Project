package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.WeatherInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyObserveWeatherInfo2System extends BaseNotification {
    private static final long serialVersionUID = -1478141320;

    @SerializedName("args")
    private WeatherInfo observeWeatherInfo2System;

    public NotifyObserveWeatherInfo2System() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ObserveWeatherInfo2System");
    }

    public NotifyObserveWeatherInfo2System(WeatherInfo weatherInfo) {
        this();
        this.observeWeatherInfo2System = weatherInfo;
    }

    public WeatherInfo getObserveWeatherInfo2System() {
        return this.observeWeatherInfo2System;
    }

    public NotifyObserveWeatherInfo2System setObserveWeatherInfo2System(WeatherInfo weatherInfo) {
        this.observeWeatherInfo2System = weatherInfo;
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
