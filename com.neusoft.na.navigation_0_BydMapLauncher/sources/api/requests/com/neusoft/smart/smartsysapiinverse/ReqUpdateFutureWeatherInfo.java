package api.requests.com.neusoft.smart.smartsysapiinverse;

import api.structs.com.neusoft.smart.FutureWeatherInfoState;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqUpdateFutureWeatherInfo extends BaseRequest {
    private static final long serialVersionUID = -1482757123;

    @SerializedName("args")
    private Arguments arguments;

    public ReqUpdateFutureWeatherInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartSysApiInverse.UpdateFutureWeatherInfo");
        this.arguments = new Arguments();
    }

    public ReqUpdateFutureWeatherInfo(FutureWeatherInfoState futureWeatherInfoState) {
        this();
        this.arguments.setWeatherInfo(futureWeatherInfoState);
    }

    public Arguments getArguments() {
        return this.arguments;
    }

    public void setArguments(Arguments arguments) {
        if (arguments != null) {
            this.arguments = arguments;
            return;
        }
        throw new IllegalArgumentException("Arguments mustn't be null!");
    }

    public static class Arguments {

        @SerializedName("WeatherInfo")
        private FutureWeatherInfoState weatherInfo = new FutureWeatherInfoState();

        public Arguments() {
        }

        public Arguments(FutureWeatherInfoState futureWeatherInfoState) {
            setWeatherInfo(futureWeatherInfoState);
        }

        public FutureWeatherInfoState getWeatherInfo() {
            return this.weatherInfo;
        }

        public Arguments setWeatherInfo(FutureWeatherInfoState futureWeatherInfoState) {
            this.weatherInfo = futureWeatherInfoState;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).weatherInfo.equals(this.weatherInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.weatherInfo);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReqUpdateFutureWeatherInfo) {
            return super.equals(obj) && ((ReqUpdateFutureWeatherInfo) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.arguments);
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
