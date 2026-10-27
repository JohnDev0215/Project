package api.notifications.com.neusoft.smart.smartsysapiinverse;

import api.structs.com.neusoft.smart.QueryWeatherInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyQueryFutureWeatherInfo extends BaseNotification {
    private static final long serialVersionUID = -1481839456;

    @SerializedName("args")
    private QueryWeatherInfo queryFutureWeatherInfo;

    public NotifyQueryFutureWeatherInfo() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartSysApiInverse.QueryFutureWeatherInfo");
    }

    public NotifyQueryFutureWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this();
        this.queryFutureWeatherInfo = queryWeatherInfo;
    }

    public QueryWeatherInfo getQueryFutureWeatherInfo() {
        return this.queryFutureWeatherInfo;
    }

    public NotifyQueryFutureWeatherInfo setQueryFutureWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this.queryFutureWeatherInfo = queryWeatherInfo;
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
