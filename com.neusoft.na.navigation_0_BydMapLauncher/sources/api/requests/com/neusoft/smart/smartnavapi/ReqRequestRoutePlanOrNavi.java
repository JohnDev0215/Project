package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PoiInfo;
import api.structs.com.neusoft.smart.RequestRoutePlanOrNaviType;
import api.structs.com.neusoft.smart.StrategyType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqRequestRoutePlanOrNavi extends BaseRequest {
    private static final long serialVersionUID = -1479067840;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestRoutePlanOrNavi() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.RequestRoutePlanOrNavi");
        this.arguments = new Arguments();
    }

    public ReqRequestRoutePlanOrNavi(RequestRoutePlanOrNaviType requestRoutePlanOrNaviType, StrategyType strategyType, PoiInfo poiInfo, List<PoiInfo> list) {
        this();
        this.arguments.setAction(requestRoutePlanOrNaviType);
        this.arguments.setStrategy(strategyType);
        this.arguments.setDestPoiInfo(poiInfo);
        this.arguments.setViaPoiInfos(list);
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

        @SerializedName("Action")
        private RequestRoutePlanOrNaviType action = RequestRoutePlanOrNaviType.ROUTE_PLANNING;

        @SerializedName("Strategy")
        private StrategyType strategy = StrategyType.ACCORDING_PREFERENCE_SET_INSIDE_MAP;

        @SerializedName("DestPoiInfo")
        private PoiInfo destPoiInfo = new PoiInfo();

        @SerializedName("ViaPoiInfos")
        private List<PoiInfo> viaPoiInfos = new ArrayList();

        public Arguments() {
        }

        public Arguments(RequestRoutePlanOrNaviType requestRoutePlanOrNaviType, StrategyType strategyType, PoiInfo poiInfo, List<PoiInfo> list) {
            setAction(requestRoutePlanOrNaviType);
            setStrategy(strategyType);
            setDestPoiInfo(poiInfo);
            setViaPoiInfos(list);
        }

        public RequestRoutePlanOrNaviType getAction() {
            return this.action;
        }

        public Arguments setAction(RequestRoutePlanOrNaviType requestRoutePlanOrNaviType) {
            this.action = requestRoutePlanOrNaviType;
            return this;
        }

        public StrategyType getStrategy() {
            return this.strategy;
        }

        public Arguments setStrategy(StrategyType strategyType) {
            this.strategy = strategyType;
            return this;
        }

        public PoiInfo getDestPoiInfo() {
            return this.destPoiInfo;
        }

        public Arguments setDestPoiInfo(PoiInfo poiInfo) {
            this.destPoiInfo = poiInfo;
            return this;
        }

        public List<PoiInfo> getViaPoiInfos() {
            return this.viaPoiInfos;
        }

        public Arguments setViaPoiInfos(List<PoiInfo> list) {
            if (list == null) {
                throw new NullPointerException("viaPoiInfos cannot be null");
            }
            this.viaPoiInfos = list;
            return this;
        }

        public Arguments setViaPoiInfos(PoiInfo... poiInfoArr) {
            return setViaPoiInfos(Arrays.asList(poiInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.action.equals(this.action) && arguments.strategy.equals(this.strategy) && arguments.destPoiInfo.equals(this.destPoiInfo) && arguments.viaPoiInfos.equals(this.viaPoiInfos);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.action, this.strategy, this.destPoiInfo, this.viaPoiInfos);
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
        if (obj instanceof ReqRequestRoutePlanOrNavi) {
            return super.equals(obj) && ((ReqRequestRoutePlanOrNavi) obj).arguments.equals(this.arguments);
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
