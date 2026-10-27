package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CurrentWeatherInfoState implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481839210;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("Status")
    private Integer status = 0;

    @SerializedName("WeatherForecastInfo")
    private CurrentWeatherForecastInfo weatherForecastInfo = new CurrentWeatherForecastInfo();

    public CurrentWeatherInfoState() {
    }

    public CurrentWeatherInfoState(Integer num, Integer num2, CurrentWeatherForecastInfo currentWeatherForecastInfo) {
        setReqId(num);
        setStatus(num2);
        setWeatherForecastInfo(currentWeatherForecastInfo);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public CurrentWeatherInfoState setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public CurrentWeatherInfoState setStatus(Integer num) {
        if (num == null) {
            throw new NullPointerException("status cannot be null");
        }
        this.status = num;
        return this;
    }

    public CurrentWeatherForecastInfo getWeatherForecastInfo() {
        return this.weatherForecastInfo;
    }

    public CurrentWeatherInfoState setWeatherForecastInfo(CurrentWeatherForecastInfo currentWeatherForecastInfo) {
        this.weatherForecastInfo = currentWeatherForecastInfo;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CurrentWeatherInfoState currentWeatherInfoState = (CurrentWeatherInfoState) obj;
        return Objects.equals(this.reqId, currentWeatherInfoState.reqId) && Objects.equals(this.status, currentWeatherInfoState.status) && Objects.equals(this.weatherForecastInfo, currentWeatherInfoState.weatherForecastInfo);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.status, this.weatherForecastInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
