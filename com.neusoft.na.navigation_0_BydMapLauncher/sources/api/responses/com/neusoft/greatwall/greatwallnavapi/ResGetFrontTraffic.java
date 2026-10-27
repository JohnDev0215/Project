package api.responses.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.FrontTrafficInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetFrontTraffic extends BaseResponse {
    private static final long serialVersionUID = -1480915944;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetFrontTraffic() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.GetFrontTraffic");
        this.arguments = new Arguments();
    }

    public ResGetFrontTraffic(Integer num, FrontTrafficInfo frontTrafficInfo) {
        this();
        this.arguments.setResultCode(num);
        this.arguments.setFrontTrafficInfo(frontTrafficInfo);
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

        @SerializedName("ResultCode")
        private Integer resultCode = 0;

        @SerializedName("FrontTrafficInfo")
        private FrontTrafficInfo frontTrafficInfo = new FrontTrafficInfo();

        public Arguments() {
        }

        public Arguments(Integer num, FrontTrafficInfo frontTrafficInfo) {
            setResultCode(num);
            setFrontTrafficInfo(frontTrafficInfo);
        }

        public Integer getResultCode() {
            return this.resultCode;
        }

        public Arguments setResultCode(Integer num) {
            if (num == null) {
                throw new NullPointerException("resultCode cannot be null");
            }
            this.resultCode = num;
            return this;
        }

        public FrontTrafficInfo getFrontTrafficInfo() {
            return this.frontTrafficInfo;
        }

        public Arguments setFrontTrafficInfo(FrontTrafficInfo frontTrafficInfo) {
            this.frontTrafficInfo = frontTrafficInfo;
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
            return super.equals(obj) && arguments.resultCode.equals(this.resultCode) && arguments.frontTrafficInfo.equals(this.frontTrafficInfo);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.resultCode, this.frontTrafficInfo);
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
        if (obj instanceof ResGetFrontTraffic) {
            return super.equals(obj) && ((ResGetFrontTraffic) obj).arguments.equals(this.arguments);
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
