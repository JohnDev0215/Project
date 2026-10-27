package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.LogOnOff;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetTraceLog extends BaseResponse {
    private static final long serialVersionUID = -1478144354;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetTraceLog() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.GetTraceLog");
        this.arguments = new Arguments();
    }

    public ResGetTraceLog(LogOnOff logOnOff) {
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

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        EMessageType eMessageType2 = EMessageType.Error;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResGetTraceLog) {
            return super.equals(obj) && ((ResGetTraceLog) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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
