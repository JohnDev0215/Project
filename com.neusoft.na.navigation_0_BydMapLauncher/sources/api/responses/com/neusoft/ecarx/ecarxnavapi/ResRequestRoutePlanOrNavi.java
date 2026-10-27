package api.responses.com.neusoft.ecarx.ecarxnavapi;

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
public class ResRequestRoutePlanOrNavi extends BaseResponse {
    private static final long serialVersionUID = -1481835614;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResRequestRoutePlanOrNavi() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.RequestRoutePlanOrNavi");
        this.arguments = new Arguments();
    }

    public ResRequestRoutePlanOrNavi(List<RouteInfo> list) {
        this();
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

        @SerializedName("RouteInfoList")
        private List<RouteInfo> routeInfoList = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<RouteInfo> list) {
            setRouteInfoList(list);
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
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).routeInfoList.equals(this.routeInfoList);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.routeInfoList);
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
        if (obj instanceof ResRequestRoutePlanOrNavi) {
            return super.equals(obj) && ((ResRequestRoutePlanOrNavi) obj).arguments.equals(this.arguments);
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
