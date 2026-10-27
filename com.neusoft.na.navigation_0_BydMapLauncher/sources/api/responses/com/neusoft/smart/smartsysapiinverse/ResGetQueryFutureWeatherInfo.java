package api.responses.com.neusoft.smart.smartsysapiinverse;

import api.structs.com.neusoft.smart.QueryWeatherInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetQueryFutureWeatherInfo extends BaseResponse {
    private static final long serialVersionUID = -1482757029;

    @SerializedName("args")
    private QueryWeatherInfo queryFutureWeatherInfo;

    public ResGetQueryFutureWeatherInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartSysApiInverse.GetQueryFutureWeatherInfo");
    }

    public ResGetQueryFutureWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this();
        this.queryFutureWeatherInfo = queryWeatherInfo;
    }

    public QueryWeatherInfo getQueryFutureWeatherInfo() {
        return this.queryFutureWeatherInfo;
    }

    public ResGetQueryFutureWeatherInfo setQueryFutureWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this.queryFutureWeatherInfo = queryWeatherInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.queryFutureWeatherInfo = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetQueryFutureWeatherInfo)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
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
