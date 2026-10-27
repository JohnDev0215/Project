package api.requests.com.neusoft.common.commonsysapiinverse;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetSystemTimeFormat extends BaseRequest {
    private static final long serialVersionUID = -1478145193;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetSystemTimeFormat() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetSystemTimeFormat");
        this.arguments = new Arguments();
    }

    public ReqSetSystemTimeFormat(Integer num) {
        this();
        this.arguments.setSystemTimeFormat(num);
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

        @SerializedName("SystemTimeFormat")
        private Integer systemTimeFormat = 0;

        public Arguments() {
        }

        public Arguments(Integer num) {
            setSystemTimeFormat(num);
        }

        public Integer getSystemTimeFormat() {
            return this.systemTimeFormat;
        }

        public Arguments setSystemTimeFormat(Integer num) {
            if (num == null) {
                throw new NullPointerException("systemTimeFormat cannot be null");
            }
            this.systemTimeFormat = num;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).systemTimeFormat.equals(this.systemTimeFormat);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.systemTimeFormat);
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
        if (obj instanceof ReqSetSystemTimeFormat) {
            return super.equals(obj) && ((ReqSetSystemTimeFormat) obj).arguments.equals(this.arguments);
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
