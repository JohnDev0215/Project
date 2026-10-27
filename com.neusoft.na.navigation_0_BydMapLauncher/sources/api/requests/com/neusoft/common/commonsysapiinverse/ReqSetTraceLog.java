package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.LogOnOff;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetTraceLog extends BaseRequest {
    private static final long serialVersionUID = -1478145310;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetTraceLog() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetTraceLog");
        this.arguments = new Arguments();
    }

    public ReqSetTraceLog(LogOnOff logOnOff) {
        this();
        this.arguments.setLogOnOff(logOnOff);
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

        @SerializedName("LogOnOff")
        private LogOnOff logOnOff = LogOnOff.ENABLE_TRACE_LOG;

        public Arguments() {
        }

        public Arguments(LogOnOff logOnOff) {
            setLogOnOff(logOnOff);
        }

        public LogOnOff getLogOnOff() {
            return this.logOnOff;
        }

        public Arguments setLogOnOff(LogOnOff logOnOff) {
            this.logOnOff = logOnOff;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).logOnOff.equals(this.logOnOff);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.logOnOff);
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
        if (obj instanceof ReqSetTraceLog) {
            return super.equals(obj) && ((ReqSetTraceLog) obj).arguments.equals(this.arguments);
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
