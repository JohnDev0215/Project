package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.AppStartupInfo;
import api.structs.com.neusoft.common.MessageType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqNaviAppStartupEventInfo extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqNaviAppStartupEventInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.NaviAppStartupEventInfo");
        this.arguments = new Arguments();
    }

    public ReqNaviAppStartupEventInfo(MessageType messageType, AppStartupInfo appStartupInfo) {
        this();
        this.arguments.setType(messageType);
        this.arguments.setInfo(appStartupInfo);
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

        @SerializedName("Type")
        private MessageType type = MessageType.UNKNOWN;

        @SerializedName("Info")
        private AppStartupInfo info = new AppStartupInfo();

        public Arguments() {
        }

        public Arguments(MessageType messageType, AppStartupInfo appStartupInfo) {
            setType(messageType);
            setInfo(appStartupInfo);
        }

        public MessageType getType() {
            return this.type;
        }

        public Arguments setType(MessageType messageType) {
            this.type = messageType;
            return this;
        }

        public AppStartupInfo getInfo() {
            return this.info;
        }

        public Arguments setInfo(AppStartupInfo appStartupInfo) {
            this.info = appStartupInfo;
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
            return super.equals(obj) && arguments.type.equals(this.type) && arguments.info.equals(this.info);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.type, this.info);
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
        if (obj instanceof ReqNaviAppStartupEventInfo) {
            return super.equals(obj) && ((ReqNaviAppStartupEventInfo) obj).arguments.equals(this.arguments);
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
