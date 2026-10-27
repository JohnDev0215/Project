package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.WaypointInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRequestSetWaypoint extends BaseRequest {
    private static final long serialVersionUID = -1481831613;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestSetWaypoint() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestSetWaypoint");
        this.arguments = new Arguments();
    }

    public ReqRequestSetWaypoint(WaypointInfo waypointInfo) {
        this();
        this.arguments.setWaypoint(waypointInfo);
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

        @SerializedName("Waypoint")
        private WaypointInfo waypoint = new WaypointInfo();

        public Arguments() {
        }

        public Arguments(WaypointInfo waypointInfo) {
            setWaypoint(waypointInfo);
        }

        public WaypointInfo getWaypoint() {
            return this.waypoint;
        }

        public Arguments setWaypoint(WaypointInfo waypointInfo) {
            this.waypoint = waypointInfo;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).waypoint.equals(this.waypoint);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.waypoint);
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
        if (obj instanceof ReqRequestSetWaypoint) {
            return super.equals(obj) && ((ReqRequestSetWaypoint) obj).arguments.equals(this.arguments);
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
