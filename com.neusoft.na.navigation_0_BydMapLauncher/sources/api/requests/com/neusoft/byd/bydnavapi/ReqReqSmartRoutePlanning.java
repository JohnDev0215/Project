package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.PlaceInfo;
import api.structs.com.neusoft.byd.StrategyType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqSmartRoutePlanning extends BaseRequest {
    private static final long serialVersionUID = -1480909062;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqSmartRoutePlanning() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqSmartRoutePlanning");
        this.arguments = new Arguments();
    }

    public ReqReqSmartRoutePlanning(PlaceInfo placeInfo, List<PlaceInfo> list, StrategyType strategyType) {
        this();
        this.arguments.setPlaceInfo(placeInfo);
        this.arguments.setPlaceInfoList(list);
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

        @SerializedName("PlaceInfo")
        private PlaceInfo placeInfo = new PlaceInfo();

        @SerializedName("PlaceInfoList")
        private List<PlaceInfo> placeInfoList = new ArrayList();

        @SerializedName("StrategyType")
        private StrategyType strategyType = StrategyType.INVALID;

        public Arguments() {
        }

        public Arguments(PlaceInfo placeInfo, List<PlaceInfo> list, StrategyType strategyType) {
            setPlaceInfo(placeInfo);
            setPlaceInfoList(list);
            setStrategyType(strategyType);
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
            return super.equals(obj) && arguments.placeInfo.equals(this.placeInfo) && arguments.placeInfoList.equals(this.placeInfoList) && arguments.strategyType.equals(this.strategyType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.placeInfo, this.placeInfoList, this.strategyType);
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
        if (obj instanceof ReqReqSmartRoutePlanning) {
            return super.equals(obj) && ((ReqReqSmartRoutePlanning) obj).arguments.equals(this.arguments);
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
