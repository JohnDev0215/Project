package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.RouteInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqNaviRouteFullView extends BaseRequest {
    private static final long serialVersionUID = -1480909062;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqNaviRouteFullView() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqNaviRouteFullView");
        this.arguments = new Arguments();
    }

    public ReqReqNaviRouteFullView(List<RouteInfo> list, Integer num) {
        this();
        this.arguments.setRouteInfoList(list);
        this.arguments.setHighlightIndex(num);
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

        @SerializedName("highlightIndex")
        private Integer highlightIndex = 0;

        public Arguments() {
        }

        public Arguments(List<RouteInfo> list, Integer num) {
            setRouteInfoList(list);
            setHighlightIndex(num);
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

        public Integer getHighlightIndex() {
            return this.highlightIndex;
        }

        public Arguments setHighlightIndex(Integer num) {
            if (num == null) {
                throw new NullPointerException("highlightIndex cannot be null");
            }
            this.highlightIndex = num;
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
            return super.equals(obj) && arguments.routeInfoList.equals(this.routeInfoList) && arguments.highlightIndex.equals(this.highlightIndex);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.routeInfoList, this.highlightIndex);
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
        if (obj instanceof ReqReqNaviRouteFullView) {
            return super.equals(obj) && ((ReqReqNaviRouteFullView) obj).arguments.equals(this.arguments);
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
