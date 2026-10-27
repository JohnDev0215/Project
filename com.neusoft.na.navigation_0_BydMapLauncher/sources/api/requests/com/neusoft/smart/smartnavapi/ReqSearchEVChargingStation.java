package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.GeoPosition;
import api.structs.com.neusoft.smart.SearchEVType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqSearchEVChargingStation extends BaseRequest {
    private static final long serialVersionUID = -1479067840;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSearchEVChargingStation() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.SearchEVChargingStation");
        this.arguments = new Arguments();
    }

    public ReqSearchEVChargingStation(SearchEVType searchEVType, String str, Integer num, GeoPosition geoPosition) {
        this();
        this.arguments.setSearchEVType(searchEVType);
        this.arguments.setKeywords(str);
        this.arguments.setSearchRadius(num);
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

        @SerializedName("SearchEVType")
        private SearchEVType searchEVType = SearchEVType.SEARCH_EV_CHARGING_STATION_DEFAULT;

        @SerializedName("Keywords")
        private String keywords = "";

        @SerializedName("SearchRadius")
        private Integer searchRadius = 0;

        @SerializedName("GeoPosition")
        private GeoPosition geoPosition = new GeoPosition();

        public Arguments() {
        }

        public Arguments(SearchEVType searchEVType, String str, Integer num, GeoPosition geoPosition) {
            setSearchEVType(searchEVType);
            setKeywords(str);
            setSearchRadius(num);
            setGeoPosition(geoPosition);
        }

        public SearchEVType getSearchEVType() {
            return this.searchEVType;
        }

        public Arguments setSearchEVType(SearchEVType searchEVType) {
            this.searchEVType = searchEVType;
            return this;
        }

        public String getKeywords() {
            return this.keywords;
        }

        public Arguments setKeywords(String str) {
            if (str == null) {
                throw new NullPointerException("keywords cannot be null");
            }
            this.keywords = str;
            return this;
        }

        public Integer getSearchRadius() {
            return this.searchRadius;
        }

        public Arguments setSearchRadius(Integer num) {
            if (num == null) {
                throw new NullPointerException("searchRadius cannot be null");
            }
            this.searchRadius = num;
            return this;
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
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.searchEVType.equals(this.searchEVType) && arguments.keywords.equals(this.keywords) && arguments.searchRadius.equals(this.searchRadius) && arguments.geoPosition.equals(this.geoPosition);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.searchEVType, this.keywords, this.searchRadius, this.geoPosition);
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
        if (obj instanceof ReqSearchEVChargingStation) {
            return super.equals(obj) && ((ReqSearchEVChargingStation) obj).arguments.equals(this.arguments);
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
