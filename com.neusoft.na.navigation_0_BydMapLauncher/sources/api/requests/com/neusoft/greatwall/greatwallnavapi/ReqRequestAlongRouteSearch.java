package api.requests.com.neusoft.greatwall.greatwallnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRequestAlongRouteSearch extends BaseRequest {
    private static final long serialVersionUID = -1481831650;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestAlongRouteSearch() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestAlongRouteSearch");
        this.arguments = new Arguments();
    }

    public ReqRequestAlongRouteSearch(Integer num, String str) {
        this();
        this.arguments.setReqId_In(num);
        this.arguments.setKeywords(str);
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

        @SerializedName("ReqId_In")
        private Integer reqId_In = 0;

        @SerializedName("Keywords")
        private String keywords = "";

        public Arguments() {
        }

        public Arguments(Integer num, String str) {
            setReqId_In(num);
            setKeywords(str);
        }

        public Integer getReqId_In() {
            return this.reqId_In;
        }

        public Arguments setReqId_In(Integer num) {
            if (num == null) {
                throw new NullPointerException("reqId_In cannot be null");
            }
            this.reqId_In = num;
            return this;
        }

        public String getKeywords() {
            return this.keywords;
        }

        public Arguments setKeywords(String str) {
            if (str == null) {
                throw new NullPointerException("keywords cannot be null");
            }
            this.keywords = str;
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
            return super.equals(obj) && arguments.reqId_In.equals(this.reqId_In) && arguments.keywords.equals(this.keywords);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.reqId_In, this.keywords);
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
        if (obj instanceof ReqRequestAlongRouteSearch) {
            return super.equals(obj) && ((ReqRequestAlongRouteSearch) obj).arguments.equals(this.arguments);
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
