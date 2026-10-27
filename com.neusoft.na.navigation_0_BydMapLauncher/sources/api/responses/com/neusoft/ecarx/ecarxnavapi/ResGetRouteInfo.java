package api.responses.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.PoiInfo;
import api.structs.com.neusoft.ecarx.ResultCode;
import api.structs.com.neusoft.ecarx.RouteInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetRouteInfo extends BaseResponse {
    private static final long serialVersionUID = -1481835651;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResGetRouteInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetRouteInfo");
        this.arguments = new Arguments();
    }

    public ResGetRouteInfo(PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list, List<PoiInfo> list2) {
        this();
        this.arguments.setStartPoi(poiInfo);
        this.arguments.setEndPoi(poiInfo2);
        this.arguments.setRouteInfo(list);
        this.arguments.setViaPois(list2);
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

        @SerializedName("StartPoi")
        private PoiInfo startPoi = new PoiInfo();

        @SerializedName("EndPoi")
        private PoiInfo endPoi = new PoiInfo();

        @SerializedName("RouteInfo")
        private List<RouteInfo> routeInfo = new ArrayList();

        @SerializedName("ViaPois")
        private List<PoiInfo> viaPois = new ArrayList();

        public Arguments() {
        }

        public Arguments(PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list, List<PoiInfo> list2) {
            setStartPoi(poiInfo);
            setEndPoi(poiInfo2);
            setRouteInfo(list);
            setViaPois(list2);
        }

        public PoiInfo getStartPoi() {
            return this.startPoi;
        }

        public Arguments setStartPoi(PoiInfo poiInfo) {
            this.startPoi = poiInfo;
            return this;
        }

        public PoiInfo getEndPoi() {
            return this.endPoi;
        }

        public Arguments setEndPoi(PoiInfo poiInfo) {
            this.endPoi = poiInfo;
            return this;
        }

        public List<RouteInfo> getRouteInfo() {
            return this.routeInfo;
        }

        public Arguments setRouteInfo(List<RouteInfo> list) {
            if (list == null) {
                throw new NullPointerException("routeInfo cannot be null");
            }
            this.routeInfo = list;
            return this;
        }

        public Arguments setRouteInfo(RouteInfo... routeInfoArr) {
            return setRouteInfo(Arrays.asList(routeInfoArr));
        }

        public List<PoiInfo> getViaPois() {
            return this.viaPois;
        }

        public Arguments setViaPois(List<PoiInfo> list) {
            if (list == null) {
                throw new NullPointerException("viaPois cannot be null");
            }
            this.viaPois = list;
            return this;
        }

        public Arguments setViaPois(PoiInfo... poiInfoArr) {
            return setViaPois(Arrays.asList(poiInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.startPoi.equals(this.startPoi) && arguments.endPoi.equals(this.endPoi) && arguments.routeInfo.equals(this.routeInfo) && arguments.viaPois.equals(this.viaPois);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.startPoi, this.endPoi, this.routeInfo, this.viaPois);
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
        if (obj instanceof ResGetRouteInfo) {
            return super.equals(obj) && ((ResGetRouteInfo) obj).arguments.equals(this.arguments);
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
