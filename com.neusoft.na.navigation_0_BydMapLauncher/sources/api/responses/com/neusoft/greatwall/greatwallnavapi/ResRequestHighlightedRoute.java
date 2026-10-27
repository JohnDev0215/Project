package api.responses.com.neusoft.greatwall.greatwallnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResRequestHighlightedRoute extends BaseResponse {
    private static final long serialVersionUID = -1480916747;

    @SerializedName("args")
    private Arguments arguments;

    public ResRequestHighlightedRoute() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestHighlightedRoute");
        this.arguments = new Arguments();
    }

    public ResRequestHighlightedRoute(Integer num, Integer num2) {
        this();
        this.arguments.setResultCode(num);
        this.arguments.setTotalRouteCount(num2);
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

        @SerializedName("TotalRouteCount")
        private Integer totalRouteCount = 0;

        public Arguments() {
        }

        public Arguments(Integer num, Integer num2) {
            setResultCode(num);
            setTotalRouteCount(num2);
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

        public Integer getTotalRouteCount() {
            return this.totalRouteCount;
        }

        public Arguments setTotalRouteCount(Integer num) {
            if (num == null) {
                throw new NullPointerException("totalRouteCount cannot be null");
            }
            this.totalRouteCount = num;
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
            return super.equals(obj) && arguments.resultCode.equals(this.resultCode) && arguments.totalRouteCount.equals(this.totalRouteCount);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.resultCode, this.totalRouteCount);
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
        if (obj instanceof ResRequestHighlightedRoute) {
            return super.equals(obj) && ((ResRequestHighlightedRoute) obj).arguments.equals(this.arguments);
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
