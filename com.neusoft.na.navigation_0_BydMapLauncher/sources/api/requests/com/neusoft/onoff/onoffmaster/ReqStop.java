package api.requests.com.neusoft.onoff.onoffmaster;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqStop extends BaseRequest {
    private static final long serialVersionUID = -1477218882;

    @SerializedName("args")
    private Arguments arguments;

    public ReqStop() {
        super(EMessageType.Request, "OnOff", "com.neusoft.onoff.OnOffMaster.Stop");
        this.arguments = new Arguments();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.Stop");
    }

    public ReqStop(String str, Long l) {
        this();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.Stop");
        this.arguments.setProcessName(str);
        this.arguments.setTimeout(l);
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

        @SerializedName("ProcessName")
        private String processName = "";

        @SerializedName("Timeout")
        private Long timeout = 0L;

        public Arguments() {
        }

        public Arguments(String str, Long l) {
            setProcessName(str);
            setTimeout(l);
        }

        public String getProcessName() {
            return this.processName;
        }

        public Arguments setProcessName(String str) {
            if (str == null) {
                throw new NullPointerException("processName cannot be null");
            }
            this.processName = str;
            return this;
        }

        public Long getTimeout() {
            return this.timeout;
        }

        public Arguments setTimeout(Long l) {
            if (l == null) {
                throw new NullPointerException("timeout cannot be null");
            }
            this.timeout = l;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.processName.equals(this.processName) && arguments.timeout.equals(this.timeout);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.processName, this.timeout);
        }

        public String toString() {
            return EProjectInterfaceType.OCTA.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReqStop) {
            return super.equals(obj) && ((ReqStop) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.arguments);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OCTA;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
