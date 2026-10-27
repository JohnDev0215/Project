package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.AppFocusInfo;
import api.structs.com.neusoft.common.MessageType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqAppFocusEventInfo extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqAppFocusEventInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.AppFocusEventInfo");
        this.arguments = new Arguments();
    }

    public ReqAppFocusEventInfo(MessageType messageType, AppFocusInfo appFocusInfo) {
        this();
        this.arguments.setType(messageType);
        this.arguments.setInfo(appFocusInfo);
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
        private AppFocusInfo info = new AppFocusInfo();

        public Arguments() {
        }

        public Arguments(MessageType messageType, AppFocusInfo appFocusInfo) {
            setType(messageType);
            setInfo(appFocusInfo);
        }

        public MessageType getType() {
            return this.type;
        }

        public Arguments setType(MessageType messageType) {
            this.type = messageType;
            return this;
        }

        public AppFocusInfo getInfo() {
            return this.info;
        }

        public Arguments setInfo(AppFocusInfo appFocusInfo) {
            this.info = appFocusInfo;
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
        if (obj instanceof ReqAppFocusEventInfo) {
            return super.equals(obj) && ((ReqAppFocusEventInfo) obj).arguments.equals(this.arguments);
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
