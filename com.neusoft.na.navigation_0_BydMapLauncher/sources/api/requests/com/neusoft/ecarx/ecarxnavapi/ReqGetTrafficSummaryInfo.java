package api.requests.com.neusoft.ecarx.ecarxnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqGetTrafficSummaryInfo extends BaseRequest {
    private static final long serialVersionUID = -1481837321;

    @SerializedName("args")
    private Arguments arguments;

    public ReqGetTrafficSummaryInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetTrafficSummaryInfo");
        this.arguments = new Arguments();
    }

    public ReqGetTrafficSummaryInfo(String str) {
        this();
        this.arguments.setTrafficText(str);
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

        @SerializedName("TrafficText")
        private String trafficText = "";

        public Arguments() {
        }

        public Arguments(String str) {
            setTrafficText(str);
        }

        public String getTrafficText() {
            return this.trafficText;
        }

        public Arguments setTrafficText(String str) {
            if (str == null) {
                throw new NullPointerException("trafficText cannot be null");
            }
            this.trafficText = str;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).trafficText.equals(this.trafficText);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.trafficText);
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
        if (obj instanceof ReqGetTrafficSummaryInfo) {
            return super.equals(obj) && ((ReqGetTrafficSummaryInfo) obj).arguments.equals(this.arguments);
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
