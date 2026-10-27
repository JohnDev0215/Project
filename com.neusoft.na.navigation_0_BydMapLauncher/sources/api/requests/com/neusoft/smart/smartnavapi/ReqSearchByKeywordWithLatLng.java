package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.LatLngInfo;
import api.structs.com.neusoft.smart.SearchActionType;
import api.structs.com.neusoft.smart.SearchType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqSearchByKeywordWithLatLng extends BaseRequest {
    private static final long serialVersionUID = -1479067877;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSearchByKeywordWithLatLng() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.SearchByKeywordWithLatLng");
        this.arguments = new Arguments();
    }

    public ReqSearchByKeywordWithLatLng(SearchActionType searchActionType, SearchType searchType, String str, String str2, LatLngInfo latLngInfo) {
        this();
        this.arguments.setAction(searchActionType);
        this.arguments.setSearchType(searchType);
        this.arguments.setKeywords(str);
        this.arguments.setSearchCity(str2);
        this.arguments.setLatLngInfo(latLngInfo);
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

        @SerializedName("SearchCity")
        private String searchCity = "";

        @SerializedName("LatLngInfo")
        private LatLngInfo latLngInfo = new LatLngInfo();

        public Arguments() {
        }

        public Arguments(SearchActionType searchActionType, SearchType searchType, String str, String str2, LatLngInfo latLngInfo) {
            setAction(searchActionType);
            setSearchType(searchType);
            setKeywords(str);
            setSearchCity(str2);
            setLatLngInfo(latLngInfo);
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

        public String getSearchCity() {
            return this.searchCity;
        }

        public Arguments setSearchCity(String str) {
            if (str == null) {
                throw new NullPointerException("searchCity cannot be null");
            }
            this.searchCity = str;
            return this;
        }

        public LatLngInfo getLatLngInfo() {
            return this.latLngInfo;
        }

        public Arguments setLatLngInfo(LatLngInfo latLngInfo) {
            this.latLngInfo = latLngInfo;
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
            return super.equals(obj) && arguments.action.equals(this.action) && arguments.searchType.equals(this.searchType) && arguments.keywords.equals(this.keywords) && arguments.searchCity.equals(this.searchCity) && arguments.latLngInfo.equals(this.latLngInfo);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.action, this.searchType, this.keywords, this.searchCity, this.latLngInfo);
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
        if (obj instanceof ReqSearchByKeywordWithLatLng) {
            return super.equals(obj) && ((ReqSearchByKeywordWithLatLng) obj).arguments.equals(this.arguments);
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
