package api.notifications.com.neusoft.smart.smartsysapiinverse;

import api.structs.com.neusoft.smart.QueryWeatherInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyQueryCurrentWeatherInfo extends BaseNotification {
    private static final long serialVersionUID = -1481840201;

    @SerializedName("args")
    private QueryWeatherInfo queryCurrentWeatherInfo;

    public NotifyQueryCurrentWeatherInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartSysApiInverse.QueryCurrentWeatherInfo");
    }

    public NotifyQueryCurrentWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this();
        this.queryCurrentWeatherInfo = queryWeatherInfo;
    }

    public QueryWeatherInfo getQueryCurrentWeatherInfo() {
        return this.queryCurrentWeatherInfo;
    }

    public NotifyQueryCurrentWeatherInfo setQueryCurrentWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this.queryCurrentWeatherInfo = queryWeatherInfo;
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
