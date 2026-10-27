package api.requests.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.GwCurrentWTInfoState;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqResponseGwCurrentWTInfoState extends BaseRequest {
    private static final long serialVersionUID = -1479992422;

    @SerializedName("args")
    private Arguments arguments;

    public ReqResponseGwCurrentWTInfoState() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.ResponseGwCurrentWTInfoState");
        this.arguments = new Arguments();
    }

    public ReqResponseGwCurrentWTInfoState(GwCurrentWTInfoState gwCurrentWTInfoState) {
        this();
        this.arguments.setWeatherInfo(gwCurrentWTInfoState);
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
        private GwCurrentWTInfoState weatherInfo = new GwCurrentWTInfoState();

        public Arguments() {
        }

        public Arguments(GwCurrentWTInfoState gwCurrentWTInfoState) {
            setWeatherInfo(gwCurrentWTInfoState);
        }

        public GwCurrentWTInfoState getWeatherInfo() {
            return this.weatherInfo;
        }

        public Arguments setWeatherInfo(GwCurrentWTInfoState gwCurrentWTInfoState) {
            this.weatherInfo = gwCurrentWTInfoState;
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
        if (obj instanceof ReqResponseGwCurrentWTInfoState) {
            return super.equals(obj) && ((ReqResponseGwCurrentWTInfoState) obj).arguments.equals(this.arguments);
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
