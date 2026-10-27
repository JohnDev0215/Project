package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.GPSInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnGPSInfo extends BaseRequest {
    private static final long serialVersionUID = -1480909062;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnGPSInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.OnGPSInfo");
        this.arguments = new Arguments();
    }

    public ReqOnGPSInfo(Integer num, GPSInfo gPSInfo) {
        this();
        this.arguments.setTokenId(num);
        this.arguments.setGPSInfo(gPSInfo);
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

        @SerializedName("TokenId")
        private Integer tokenId = 0;

        @SerializedName("GPSInfo")
        private GPSInfo gPSInfo = new GPSInfo();

        public Arguments() {
        }

        public Arguments(Integer num, GPSInfo gPSInfo) {
            setTokenId(num);
            setGPSInfo(gPSInfo);
        }

        public Integer getTokenId() {
            return this.tokenId;
        }

        public Arguments setTokenId(Integer num) {
            if (num == null) {
                throw new NullPointerException("tokenId cannot be null");
            }
            this.tokenId = num;
            return this;
        }

        public GPSInfo getGPSInfo() {
            return this.gPSInfo;
        }

        public Arguments setGPSInfo(GPSInfo gPSInfo) {
            this.gPSInfo = gPSInfo;
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
            return super.equals(obj) && arguments.tokenId.equals(this.tokenId) && arguments.gPSInfo.equals(this.gPSInfo);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.tokenId, this.gPSInfo);
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
        if (obj instanceof ReqOnGPSInfo) {
            return super.equals(obj) && ((ReqOnGPSInfo) obj).arguments.equals(this.arguments);
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
