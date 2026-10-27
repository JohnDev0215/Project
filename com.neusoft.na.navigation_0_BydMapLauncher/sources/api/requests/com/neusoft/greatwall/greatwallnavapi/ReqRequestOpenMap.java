package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.LocationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRequestOpenMap extends BaseRequest {
    private static final long serialVersionUID = -1481831744;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestOpenMap() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestOpenMap");
        this.arguments = new Arguments();
    }

    public ReqRequestOpenMap(LocationInfo locationInfo) {
        this();
        this.arguments.setLocationInfo(locationInfo);
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

        @SerializedName("LocationInfo")
        private LocationInfo locationInfo = new LocationInfo();

        public Arguments() {
        }

        public Arguments(LocationInfo locationInfo) {
            setLocationInfo(locationInfo);
        }

        public LocationInfo getLocationInfo() {
            return this.locationInfo;
        }

        public Arguments setLocationInfo(LocationInfo locationInfo) {
            this.locationInfo = locationInfo;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).locationInfo.equals(this.locationInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.locationInfo);
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
        if (obj instanceof ReqRequestOpenMap) {
            return super.equals(obj) && ((ReqRequestOpenMap) obj).arguments.equals(this.arguments);
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
