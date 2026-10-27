package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.NaviCode;
import api.structs.com.neusoft.byd.StrategyType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResReqNaviGetRouteOption extends BaseResponse {
    private static final long serialVersionUID = -1480908222;

    @SerializedName("args")
    private Arguments arguments;

    public ResReqNaviGetRouteOption() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.ReqNaviGetRouteOption");
        this.arguments = new Arguments();
    }

    public ResReqNaviGetRouteOption(NaviCode naviCode, StrategyType strategyType) {
        this();
        this.arguments.setNaviCode(naviCode);
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

        @SerializedName("NaviCode")
        private NaviCode naviCode = NaviCode.OK;

        @SerializedName("StrategyType")
        private StrategyType strategyType = StrategyType.INVALID;

        public Arguments() {
        }

        public Arguments(NaviCode naviCode, StrategyType strategyType) {
            setNaviCode(naviCode);
            setStrategyType(strategyType);
        }

        public NaviCode getNaviCode() {
            return this.naviCode;
        }

        public Arguments setNaviCode(NaviCode naviCode) {
            this.naviCode = naviCode;
            return this;
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
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.naviCode.equals(this.naviCode) && arguments.strategyType.equals(this.strategyType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.naviCode, this.strategyType);
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
        if (obj instanceof ResReqNaviGetRouteOption) {
            return super.equals(obj) && ((ResReqNaviGetRouteOption) obj).arguments.equals(this.arguments);
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
