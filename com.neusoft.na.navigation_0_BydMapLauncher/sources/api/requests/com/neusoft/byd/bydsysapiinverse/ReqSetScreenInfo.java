package api.requests.com.neusoft.byd.bydsysapiinverse;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetScreenInfo extends BaseRequest {
    private static final long serialVersionUID = -1481837473;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetScreenInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydSysApiInverse.SetScreenInfo");
        this.arguments = new Arguments();
    }

    public ReqSetScreenInfo(Integer num, Integer num2) {
        this();
        this.arguments.setWidth(num);
        this.arguments.setHeight(num2);
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

        @SerializedName("width")
        private Integer width = 0;

        @SerializedName("height")
        private Integer height = 0;

        public Arguments() {
        }

        public Arguments(Integer num, Integer num2) {
            setWidth(num);
            setHeight(num2);
        }

        public Integer getWidth() {
            return this.width;
        }

        public Arguments setWidth(Integer num) {
            if (num == null) {
                throw new NullPointerException("width cannot be null");
            }
            this.width = num;
            return this;
        }

        public Integer getHeight() {
            return this.height;
        }

        public Arguments setHeight(Integer num) {
            if (num == null) {
                throw new NullPointerException("height cannot be null");
            }
            this.height = num;
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
            return super.equals(obj) && arguments.width.equals(this.width) && arguments.height.equals(this.height);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.width, this.height);
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
        if (obj instanceof ReqSetScreenInfo) {
            return super.equals(obj) && ((ReqSetScreenInfo) obj).arguments.equals(this.arguments);
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
