package api.requests.com.neusoft.onoff.onoffmaster;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqStart extends BaseRequest {
    private static final long serialVersionUID = -1477218882;

    @SerializedName("args")
    private Arguments arguments;

    public ReqStart() {
        super(EMessageType.Request, "OnOff", "com.neusoft.onoff.OnOffMaster.Start");
        this.arguments = new Arguments();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.Start");
    }

    public ReqStart(String str, List<String> list) {
        this();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.Start");
        this.arguments.setProcessName(str);
        this.arguments.setArgs(list);
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

        @SerializedName("Args")
        private List<String> args = new ArrayList();

        public Arguments() {
        }

        public Arguments(String str, List<String> list) {
            setProcessName(str);
            setArgs(list);
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

        public List<String> getArgs() {
            return this.args;
        }

        public Arguments setArgs(List<String> list) {
            if (list == null) {
                throw new NullPointerException("args cannot be null");
            }
            this.args = list;
            return this;
        }

        public Arguments setArgs(String... strArr) {
            return setArgs(Arrays.asList(strArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.processName.equals(this.processName) && arguments.args.equals(this.args);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.processName, this.args);
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
        if (obj instanceof ReqStart) {
            return super.equals(obj) && ((ReqStart) obj).arguments.equals(this.arguments);
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
