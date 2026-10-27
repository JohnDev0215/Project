package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.PoiInfo;
import api.structs.com.neusoft.ecarx.RouteDestActionType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqModifyNaviVia extends BaseRequest {
    private static final long serialVersionUID = -1481836606;

    @SerializedName("args")
    private Arguments arguments;

    public ReqModifyNaviVia() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.ModifyNaviVia");
        this.arguments = new Arguments();
    }

    public ReqModifyNaviVia(RouteDestActionType routeDestActionType, PoiInfo poiInfo) {
        this();
        this.arguments.setAction(routeDestActionType);
        this.arguments.setViaPoi(poiInfo);
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
        private RouteDestActionType action = RouteDestActionType.ROUTE_DEST_DEL;

        @SerializedName("ViaPoi")
        private PoiInfo viaPoi = new PoiInfo();

        public Arguments() {
        }

        public Arguments(RouteDestActionType routeDestActionType, PoiInfo poiInfo) {
            setAction(routeDestActionType);
            setViaPoi(poiInfo);
        }

        public RouteDestActionType getAction() {
            return this.action;
        }

        public Arguments setAction(RouteDestActionType routeDestActionType) {
            this.action = routeDestActionType;
            return this;
        }

        public PoiInfo getViaPoi() {
            return this.viaPoi;
        }

        public Arguments setViaPoi(PoiInfo poiInfo) {
            this.viaPoi = poiInfo;
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
            return super.equals(obj) && arguments.action.equals(this.action) && arguments.viaPoi.equals(this.viaPoi);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.action, this.viaPoi);
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
        if (obj instanceof ReqModifyNaviVia) {
            return super.equals(obj) && ((ReqModifyNaviVia) obj).arguments.equals(this.arguments);
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
