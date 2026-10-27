package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.FavoriteType;
import api.structs.com.neusoft.ecarx.GeoPosition;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqEditFavoritePois extends BaseRequest {
    private static final long serialVersionUID = -1481837321;

    @SerializedName("args")
    private Arguments arguments;

    public ReqEditFavoritePois() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.EditFavoritePois");
        this.arguments = new Arguments();
    }

    public ReqEditFavoritePois(FavoriteType favoriteType, String str, String str2, GeoPosition geoPosition, String str3) {
        this();
        this.arguments.setFavoriteType(favoriteType);
        this.arguments.setPoiName(str);
        this.arguments.setAddress(str2);
        this.arguments.setGeoPosition(geoPosition);
        this.arguments.setCoordType(str3);
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

        @SerializedName("FavoriteType")
        private FavoriteType favoriteType = FavoriteType.GENERAL_FAVORITES;

        @SerializedName("PoiName")
        private String poiName = "";

        @SerializedName("Address")
        private String address = "";

        @SerializedName("GeoPosition")
        private GeoPosition geoPosition = new GeoPosition();

        @SerializedName("CoordType")
        private String coordType = "";

        public Arguments() {
        }

        public Arguments(FavoriteType favoriteType, String str, String str2, GeoPosition geoPosition, String str3) {
            setFavoriteType(favoriteType);
            setPoiName(str);
            setAddress(str2);
            setGeoPosition(geoPosition);
            setCoordType(str3);
        }

        public FavoriteType getFavoriteType() {
            return this.favoriteType;
        }

        public Arguments setFavoriteType(FavoriteType favoriteType) {
            this.favoriteType = favoriteType;
            return this;
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

        public String getAddress() {
            return this.address;
        }

        public Arguments setAddress(String str) {
            if (str == null) {
                throw new NullPointerException("address cannot be null");
            }
            this.address = str;
            return this;
        }

        public GeoPosition getGeoPosition() {
            return this.geoPosition;
        }

        public Arguments setGeoPosition(GeoPosition geoPosition) {
            this.geoPosition = geoPosition;
            return this;
        }

        public String getCoordType() {
            return this.coordType;
        }

        public Arguments setCoordType(String str) {
            if (str == null) {
                throw new NullPointerException("coordType cannot be null");
            }
            this.coordType = str;
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
            return super.equals(obj) && arguments.favoriteType.equals(this.favoriteType) && arguments.poiName.equals(this.poiName) && arguments.address.equals(this.address) && arguments.geoPosition.equals(this.geoPosition) && arguments.coordType.equals(this.coordType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.favoriteType, this.poiName, this.address, this.geoPosition, this.coordType);
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
        if (obj instanceof ReqEditFavoritePois) {
            return super.equals(obj) && ((ReqEditFavoritePois) obj).arguments.equals(this.arguments);
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
