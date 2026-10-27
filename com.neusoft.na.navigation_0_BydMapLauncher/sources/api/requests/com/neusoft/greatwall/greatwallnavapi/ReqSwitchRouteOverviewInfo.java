package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.RouteOverviewMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSwitchRouteOverviewInfo extends BaseRequest {
    private static final long serialVersionUID = -1481831650;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSwitchRouteOverviewInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.SwitchRouteOverviewInfo");
        this.arguments = new Arguments();
    }

    public ReqSwitchRouteOverviewInfo(RouteOverviewMode routeOverviewMode) {
        this();
        this.arguments.setOverviewMode(routeOverviewMode);
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

        @SerializedName("OverviewMode")
        private RouteOverviewMode overviewMode = RouteOverviewMode.ROUTE_OVERVIEW_OFF;

        public Arguments() {
        }

        public Arguments(RouteOverviewMode routeOverviewMode) {
            setOverviewMode(routeOverviewMode);
        }

        public RouteOverviewMode getOverviewMode() {
            return this.overviewMode;
        }

        public Arguments setOverviewMode(RouteOverviewMode routeOverviewMode) {
            this.overviewMode = routeOverviewMode;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).overviewMode.equals(this.overviewMode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.overviewMode);
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
        if (obj instanceof ReqSwitchRouteOverviewInfo) {
            return super.equals(obj) && ((ReqSwitchRouteOverviewInfo) obj).arguments.equals(this.arguments);
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
