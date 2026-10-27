package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.StrategyType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetSupportedRouteOptions extends BaseResponse {
    private static final long serialVersionUID = -1480908222;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetSupportedRouteOptions() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.GetSupportedRouteOptions");
        this.arguments = new Arguments();
    }

    public ResGetSupportedRouteOptions(List<StrategyType> list) {
        this();
        this.arguments.setStrategyTypeList(list);
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

        @SerializedName("StrategyTypeList")
        private List<StrategyType> strategyTypeList = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<StrategyType> list) {
            setStrategyTypeList(list);
        }

        public List<StrategyType> getStrategyTypeList() {
            return this.strategyTypeList;
        }

        public Arguments setStrategyTypeList(List<StrategyType> list) {
            if (list == null) {
                throw new NullPointerException("strategyTypeList cannot be null");
            }
            this.strategyTypeList = list;
            return this;
        }

        public Arguments setStrategyTypeList(StrategyType... strategyTypeArr) {
            return setStrategyTypeList(Arrays.asList(strategyTypeArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).strategyTypeList.equals(this.strategyTypeList);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.strategyTypeList);
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
        if (obj instanceof ResGetSupportedRouteOptions) {
            return super.equals(obj) && ((ResGetSupportedRouteOptions) obj).arguments.equals(this.arguments);
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
