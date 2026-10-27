package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.GeoPosition;
import api.structs.com.neusoft.smart.SearchActionType;
import api.structs.com.neusoft.smart.SearchType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqSearchAround extends BaseRequest {
    private static final long serialVersionUID = -1479067877;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSearchAround() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.SearchAround");
        this.arguments = new Arguments();
    }

    public ReqSearchAround(SearchActionType searchActionType, SearchType searchType, String str, Integer num, GeoPosition geoPosition) {
        this();
        this.arguments.setAction(searchActionType);
        this.arguments.setSearchType(searchType);
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

        @SerializedName("Action")
        private SearchActionType action = SearchActionType.SEARCH_IN_MAP;

        @SerializedName("SearchType")
        private SearchType searchType = SearchType.KEY_WORD_SEARCH;

        @SerializedName("Keywords")
        private String keywords = "";

        @SerializedName("SearchRadius")
        private Integer searchRadius = 0;

        @SerializedName("GeoPosition")
        private GeoPosition geoPosition = new GeoPosition();

        public Arguments() {
        }

        public Arguments(SearchActionType searchActionType, SearchType searchType, String str, Integer num, GeoPosition geoPosition) {
            setAction(searchActionType);
            setSearchType(searchType);
            setKeywords(str);
            setSearchRadius(num);
            setGeoPosition(geoPosition);
        }

        public SearchActionType getAction() {
            return this.action;
        }

        public Arguments setAction(SearchActionType searchActionType) {
            this.action = searchActionType;
            return this;
        }

        public SearchType getSearchType() {
            return this.searchType;
        }

        public Arguments setSearchType(SearchType searchType) {
            this.searchType = searchType;
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
            return super.equals(obj) && arguments.action.equals(this.action) && arguments.searchType.equals(this.searchType) && arguments.keywords.equals(this.keywords) && arguments.searchRadius.equals(this.searchRadius) && arguments.geoPosition.equals(this.geoPosition);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.action, this.searchType, this.keywords, this.searchRadius, this.geoPosition);
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
        if (obj instanceof ReqSearchAround) {
            return super.equals(obj) && ((ReqSearchAround) obj).arguments.equals(this.arguments);
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
