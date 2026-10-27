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
public class ResReqNaviInfo extends BaseResponse {
    private static final long serialVersionUID = -1480908186;

    @SerializedName("args")
    private Arguments arguments;

    public ResReqNaviInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.ReqNaviInfo");
        this.arguments = new Arguments();
    }

    public ResReqNaviInfo(NaviCode naviCode, StrategyType strategyType, PlaceInfo placeInfo, PlaceInfo placeInfo2, List<PlaceInfo> list) {
        this();
        this.arguments.setNaviCode(naviCode);
        this.arguments.setStrategyType(strategyType);
        this.arguments.setStartPosition(placeInfo);
        this.arguments.setDestinationPosition(placeInfo2);
        this.arguments.setViaPositions(list);
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

        @SerializedName("StartPosition")
        private PlaceInfo startPosition = new PlaceInfo();

        @SerializedName("DestinationPosition")
        private PlaceInfo destinationPosition = new PlaceInfo();

        @SerializedName("ViaPositions")
        private List<PlaceInfo> viaPositions = new ArrayList();

        public Arguments() {
        }

        public Arguments(NaviCode naviCode, StrategyType strategyType, PlaceInfo placeInfo, PlaceInfo placeInfo2, List<PlaceInfo> list) {
            setNaviCode(naviCode);
            setStrategyType(strategyType);
            setStartPosition(placeInfo);
            setDestinationPosition(placeInfo2);
            setViaPositions(list);
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

        public PlaceInfo getStartPosition() {
            return this.startPosition;
        }

        public Arguments setStartPosition(PlaceInfo placeInfo) {
            this.startPosition = placeInfo;
            return this;
        }

        public PlaceInfo getDestinationPosition() {
            return this.destinationPosition;
        }

        public Arguments setDestinationPosition(PlaceInfo placeInfo) {
            this.destinationPosition = placeInfo;
            return this;
        }

        public List<PlaceInfo> getViaPositions() {
            return this.viaPositions;
        }

        public Arguments setViaPositions(List<PlaceInfo> list) {
            if (list == null) {
                throw new NullPointerException("viaPositions cannot be null");
            }
            this.viaPositions = list;
            return this;
        }

        public Arguments setViaPositions(PlaceInfo... placeInfoArr) {
            return setViaPositions(Arrays.asList(placeInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.naviCode.equals(this.naviCode) && arguments.strategyType.equals(this.strategyType) && arguments.startPosition.equals(this.startPosition) && arguments.destinationPosition.equals(this.destinationPosition) && arguments.viaPositions.equals(this.viaPositions);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.naviCode, this.strategyType, this.startPosition, this.destinationPosition, this.viaPositions);
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
        if (obj instanceof ResReqNaviInfo) {
            return super.equals(obj) && ((ResReqNaviInfo) obj).arguments.equals(this.arguments);
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
