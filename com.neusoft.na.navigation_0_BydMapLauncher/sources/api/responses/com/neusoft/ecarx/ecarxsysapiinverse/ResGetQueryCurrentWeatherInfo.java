package api.responses.com.neusoft.ecarx.ecarxsysapiinverse;

import api.structs.com.neusoft.ecarx.QueryWeatherInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetQueryCurrentWeatherInfo extends BaseResponse {
    private static final long serialVersionUID = -1478146092;

    @SerializedName("args")
    private QueryWeatherInfo queryCurrentWeatherInfo;

    public ResGetQueryCurrentWeatherInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxSysApiInverse.GetQueryCurrentWeatherInfo");
    }

    public ResGetQueryCurrentWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this();
        this.queryCurrentWeatherInfo = queryWeatherInfo;
    }

    public QueryWeatherInfo getQueryCurrentWeatherInfo() {
        return this.queryCurrentWeatherInfo;
    }

    public ResGetQueryCurrentWeatherInfo setQueryCurrentWeatherInfo(QueryWeatherInfo queryWeatherInfo) {
        this.queryCurrentWeatherInfo = queryWeatherInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.queryCurrentWeatherInfo = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetQueryCurrentWeatherInfo)) {
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
