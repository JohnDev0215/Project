package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.GeoPosition;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRequestDirectNavigation extends BaseRequest {
    private static final long serialVersionUID = -1481831707;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestDirectNavigation() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestDirectNavigation");
        this.arguments = new Arguments();
    }

    public ReqRequestDirectNavigation(String str, GeoPosition geoPosition) {
        this();
        this.arguments.setPoiName(str);
        this.arguments.setLonLat(geoPosition);
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

        @SerializedName("PoiName")
        private String poiName = "";

        @SerializedName("LonLat")
        private GeoPosition lonLat = new GeoPosition();

        public Arguments() {
        }

        public Arguments(String str, GeoPosition geoPosition) {
            setPoiName(str);
            setLonLat(geoPosition);
        }

        public String getPoiName() {
            return this.poiName;
        }

        public Arguments setPoiName(String str) {
            if (str == null) {
                throw new NullPointerException("poiName cannot be null");
            }
            this.poiName = str;
            return this;
        }

        public GeoPosition getLonLat() {
            return this.lonLat;
        }

        public Arguments setLonLat(GeoPosition geoPosition) {
            this.lonLat = geoPosition;
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
            return super.equals(obj) && arguments.poiName.equals(this.poiName) && arguments.lonLat.equals(this.lonLat);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.poiName, this.lonLat);
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
        if (obj instanceof ReqRequestDirectNavigation) {
            return super.equals(obj) && ((ReqRequestDirectNavigation) obj).arguments.equals(this.arguments);
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
