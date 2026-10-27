package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FutureWeatherInfoState implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838501;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("Status")
    private Integer status = 0;

    @SerializedName("Publish_Time")
    private String publish_Time = "";

    @SerializedName("FutureWeatherForecastInfos")
    private List<FutureWeatherForecastInfo> futureWeatherForecastInfos = new ArrayList();

    public FutureWeatherInfoState() {
    }

    public FutureWeatherInfoState(Integer num, Integer num2, String str, List<FutureWeatherForecastInfo> list) {
        setReqId(num);
        setStatus(num2);
        setPublish_Time(str);
        setFutureWeatherForecastInfos(list);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public FutureWeatherInfoState setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public FutureWeatherInfoState setStatus(Integer num) {
        if (num == null) {
            throw new NullPointerException("status cannot be null");
        }
        this.status = num;
        return this;
    }

    public String getPublish_Time() {
        return this.publish_Time;
    }

    public FutureWeatherInfoState setPublish_Time(String str) {
        if (str == null) {
            throw new NullPointerException("publish_Time cannot be null");
        }
        this.publish_Time = str;
        return this;
    }

    public List<FutureWeatherForecastInfo> getFutureWeatherForecastInfos() {
        return this.futureWeatherForecastInfos;
    }

    public FutureWeatherInfoState setFutureWeatherForecastInfos(List<FutureWeatherForecastInfo> list) {
        if (list == null) {
            throw new NullPointerException("futureWeatherForecastInfos cannot be null");
        }
        this.futureWeatherForecastInfos = list;
        return this;
    }

    public FutureWeatherInfoState setFutureWeatherForecastInfos(FutureWeatherForecastInfo... futureWeatherForecastInfoArr) {
        return setFutureWeatherForecastInfos(Arrays.asList(futureWeatherForecastInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FutureWeatherInfoState futureWeatherInfoState = (FutureWeatherInfoState) obj;
        return Objects.equals(this.reqId, futureWeatherInfoState.reqId) && Objects.equals(this.status, futureWeatherInfoState.status) && Objects.equals(this.publish_Time, futureWeatherInfoState.publish_Time) && Objects.equals(this.futureWeatherForecastInfos, futureWeatherInfoState.futureWeatherForecastInfos);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.status, this.publish_Time, this.futureWeatherForecastInfos);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
