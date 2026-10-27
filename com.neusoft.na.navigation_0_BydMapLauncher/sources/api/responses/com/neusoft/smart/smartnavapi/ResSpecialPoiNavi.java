package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PoiInfo;
import api.structs.com.neusoft.smart.ResultCode;
import api.structs.com.neusoft.smart.RouteInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResSpecialPoiNavi extends BaseResponse {
    private static final long serialVersionUID = -1479066849;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResSpecialPoiNavi() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.SpecialPoiNavi");
        this.arguments = new Arguments();
    }

    public ResSpecialPoiNavi(ResultCode resultCode, PoiInfo poiInfo, PoiInfo poiInfo2, List<PoiInfo> list, List<RouteInfo> list2, Integer num) {
        this();
        this.arguments.setResult(resultCode);
        this.arguments.setStartPoiInfo(poiInfo);
        this.arguments.setEndPoiInfo(poiInfo2);
        this.arguments.setViaPoiInfos(list);
        this.arguments.setRouteInfoList(list2);
        this.arguments.setCurrentRouteIndex(num);
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

        @SerializedName("Result")
        private ResultCode result = ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT;

        @SerializedName("StartPoiInfo")
        private PoiInfo startPoiInfo = new PoiInfo();

        @SerializedName("EndPoiInfo")
        private PoiInfo endPoiInfo = new PoiInfo();

        @SerializedName("ViaPoiInfos")
        private List<PoiInfo> viaPoiInfos = new ArrayList();

        @SerializedName("RouteInfoList")
        private List<RouteInfo> routeInfoList = new ArrayList();

        @SerializedName("CurrentRouteIndex")
        private Integer currentRouteIndex = 0;

        public Arguments() {
        }

        public Arguments(ResultCode resultCode, PoiInfo poiInfo, PoiInfo poiInfo2, List<PoiInfo> list, List<RouteInfo> list2, Integer num) {
            setResult(resultCode);
            setStartPoiInfo(poiInfo);
            setEndPoiInfo(poiInfo2);
            setViaPoiInfos(list);
            setRouteInfoList(list2);
            setCurrentRouteIndex(num);
        }

        public ResultCode getResult() {
            return this.result;
        }

        public Arguments setResult(ResultCode resultCode) {
            this.result = resultCode;
            return this;
        }

        public PoiInfo getStartPoiInfo() {
            return this.startPoiInfo;
        }

        public Arguments setStartPoiInfo(PoiInfo poiInfo) {
            this.startPoiInfo = poiInfo;
            return this;
        }

        public PoiInfo getEndPoiInfo() {
            return this.endPoiInfo;
        }

        public Arguments setEndPoiInfo(PoiInfo poiInfo) {
            this.endPoiInfo = poiInfo;
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

        public List<RouteInfo> getRouteInfoList() {
            return this.routeInfoList;
        }

        public Arguments setRouteInfoList(List<RouteInfo> list) {
            if (list == null) {
                throw new NullPointerException("routeInfoList cannot be null");
            }
            this.routeInfoList = list;
            return this;
        }

        public Arguments setRouteInfoList(RouteInfo... routeInfoArr) {
            return setRouteInfoList(Arrays.asList(routeInfoArr));
        }

        public Integer getCurrentRouteIndex() {
            return this.currentRouteIndex;
        }

        public Arguments setCurrentRouteIndex(Integer num) {
            if (num == null) {
                throw new NullPointerException("currentRouteIndex cannot be null");
            }
            this.currentRouteIndex = num;
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
            return super.equals(obj) && arguments.result.equals(this.result) && arguments.startPoiInfo.equals(this.startPoiInfo) && arguments.endPoiInfo.equals(this.endPoiInfo) && arguments.viaPoiInfos.equals(this.viaPoiInfos) && arguments.routeInfoList.equals(this.routeInfoList) && arguments.currentRouteIndex.equals(this.currentRouteIndex);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.result, this.startPoiInfo, this.endPoiInfo, this.viaPoiInfos, this.routeInfoList, this.currentRouteIndex);
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
        if (obj instanceof ResSpecialPoiNavi) {
            return super.equals(obj) && ((ResSpecialPoiNavi) obj).arguments.equals(this.arguments);
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

    public void setCustomError(ResultCode resultCode) {
        this.customError = resultCode;
    }

    public ResultCode getCustomError() {
        return this.customError;
    }
}
