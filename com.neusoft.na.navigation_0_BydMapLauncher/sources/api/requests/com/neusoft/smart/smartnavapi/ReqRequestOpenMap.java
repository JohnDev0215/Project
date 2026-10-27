package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.GeoPosition;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqRequestOpenMap extends BaseRequest {
    private static final long serialVersionUID = -1479067746;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestOpenMap() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.RequestOpenMap");
        this.arguments = new Arguments();
    }

    public ReqRequestOpenMap(GeoPosition geoPosition) {
        this();
        this.arguments.setGeoPosition(geoPosition);
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

        @SerializedName("GeoPosition")
        private GeoPosition geoPosition = new GeoPosition();

        public Arguments() {
        }

        public Arguments(GeoPosition geoPosition) {
            setGeoPosition(geoPosition);
        }

        public GeoPosition getGeoPosition() {
            return this.geoPosition;
        }

        public Arguments setGeoPosition(GeoPosition geoPosition) {
            this.geoPosition = geoPosition;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).geoPosition.equals(this.geoPosition);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.geoPosition);
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
