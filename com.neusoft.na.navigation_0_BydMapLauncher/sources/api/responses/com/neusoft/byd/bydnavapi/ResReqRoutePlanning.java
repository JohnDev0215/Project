package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.NaviCode;
import api.structs.com.neusoft.byd.RouteInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResReqRoutePlanning extends BaseResponse {
    private static final long serialVersionUID = -1480908186;

    @SerializedName("args")
    private Arguments arguments;

    public ResReqRoutePlanning() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.ReqRoutePlanning");
        this.arguments = new Arguments();
    }

    public ResReqRoutePlanning(NaviCode naviCode, List<RouteInfo> list) {
        this();
        this.arguments.setNaviCode(naviCode);
        this.arguments.setRouteInfoList(list);
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

        @SerializedName("RouteInfoList")
        private List<RouteInfo> routeInfoList = new ArrayList();

        public Arguments() {
        }

        public Arguments(NaviCode naviCode, List<RouteInfo> list) {
            setNaviCode(naviCode);
            setRouteInfoList(list);
        }

        public NaviCode getNaviCode() {
            return this.naviCode;
        }

        public Arguments setNaviCode(NaviCode naviCode) {
            this.naviCode = naviCode;
            return this;
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

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.naviCode.equals(this.naviCode) && arguments.routeInfoList.equals(this.routeInfoList);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.naviCode, this.routeInfoList);
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
        if (obj instanceof ResReqRoutePlanning) {
            return super.equals(obj) && ((ResReqRoutePlanning) obj).arguments.equals(this.arguments);
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
