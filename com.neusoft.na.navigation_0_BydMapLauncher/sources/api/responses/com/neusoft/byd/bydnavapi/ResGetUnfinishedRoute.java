package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.NaviCode;
import api.structs.com.neusoft.byd.PlaceInfo;
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
public class ResGetUnfinishedRoute extends BaseResponse {
    private static final long serialVersionUID = -1480908968;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetUnfinishedRoute() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.getUnfinishedRoute");
        this.arguments = new Arguments();
    }

    public ResGetUnfinishedRoute(NaviCode naviCode, StrategyType strategyType, PlaceInfo placeInfo, List<PlaceInfo> list) {
        this();
        this.arguments.setNaviCode(naviCode);
        this.arguments.setStrategyType(strategyType);
        this.arguments.setPlaceInfo(placeInfo);
        this.arguments.setPlaceInfoList(list);
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

        @SerializedName("PlaceInfo")
        private PlaceInfo placeInfo = new PlaceInfo();

        @SerializedName("PlaceInfoList")
        private List<PlaceInfo> placeInfoList = new ArrayList();

        public Arguments() {
        }

        public Arguments(NaviCode naviCode, StrategyType strategyType, PlaceInfo placeInfo, List<PlaceInfo> list) {
            setNaviCode(naviCode);
            setStrategyType(strategyType);
            setPlaceInfo(placeInfo);
            setPlaceInfoList(list);
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

        public PlaceInfo getPlaceInfo() {
            return this.placeInfo;
        }

        public Arguments setPlaceInfo(PlaceInfo placeInfo) {
            this.placeInfo = placeInfo;
            return this;
        }

        public List<PlaceInfo> getPlaceInfoList() {
            return this.placeInfoList;
        }

        public Arguments setPlaceInfoList(List<PlaceInfo> list) {
            if (list == null) {
                throw new NullPointerException("placeInfoList cannot be null");
            }
            this.placeInfoList = list;
            return this;
        }

        public Arguments setPlaceInfoList(PlaceInfo... placeInfoArr) {
            return setPlaceInfoList(Arrays.asList(placeInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.naviCode.equals(this.naviCode) && arguments.strategyType.equals(this.strategyType) && arguments.placeInfo.equals(this.placeInfo) && arguments.placeInfoList.equals(this.placeInfoList);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.naviCode, this.strategyType, this.placeInfo, this.placeInfoList);
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
        if (obj instanceof ResGetUnfinishedRoute) {
            return super.equals(obj) && ((ResGetUnfinishedRoute) obj).arguments.equals(this.arguments);
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
