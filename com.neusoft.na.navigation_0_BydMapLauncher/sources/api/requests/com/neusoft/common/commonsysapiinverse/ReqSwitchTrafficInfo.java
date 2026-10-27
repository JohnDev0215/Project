package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.TrafficMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSwitchTrafficInfo extends BaseRequest {
    private static final long serialVersionUID = -1478145287;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSwitchTrafficInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SwitchTrafficInfo");
        this.arguments = new Arguments();
    }

    public ReqSwitchTrafficInfo(TrafficMode trafficMode) {
        this();
        this.arguments.setTrafficMode(trafficMode);
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

        @SerializedName("TrafficMode")
        private TrafficMode trafficMode = TrafficMode.TRAFFIC_ON;

        public Arguments() {
        }

        public Arguments(TrafficMode trafficMode) {
            setTrafficMode(trafficMode);
        }

        public TrafficMode getTrafficMode() {
            return this.trafficMode;
        }

        public Arguments setTrafficMode(TrafficMode trafficMode) {
            this.trafficMode = trafficMode;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).trafficMode.equals(this.trafficMode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.trafficMode);
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
        if (obj instanceof ReqSwitchTrafficInfo) {
            return super.equals(obj) && ((ReqSwitchTrafficInfo) obj).arguments.equals(this.arguments);
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
