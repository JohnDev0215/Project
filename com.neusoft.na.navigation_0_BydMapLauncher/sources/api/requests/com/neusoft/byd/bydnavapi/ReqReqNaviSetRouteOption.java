package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.StrategyType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqNaviSetRouteOption extends BaseRequest {
    private static final long serialVersionUID = -1480909119;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqNaviSetRouteOption() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqNaviSetRouteOption");
        this.arguments = new Arguments();
    }

    public ReqReqNaviSetRouteOption(StrategyType strategyType) {
        this();
        this.arguments.setStrategyType(strategyType);
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

        @SerializedName("StrategyType")
        private StrategyType strategyType = StrategyType.INVALID;

        public Arguments() {
        }

        public Arguments(StrategyType strategyType) {
            setStrategyType(strategyType);
        }

        public StrategyType getStrategyType() {
            return this.strategyType;
        }

        public Arguments setStrategyType(StrategyType strategyType) {
            this.strategyType = strategyType;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).strategyType.equals(this.strategyType);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.strategyType);
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
        if (obj instanceof ReqReqNaviSetRouteOption) {
            return super.equals(obj) && ((ReqReqNaviSetRouteOption) obj).arguments.equals(this.arguments);
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
