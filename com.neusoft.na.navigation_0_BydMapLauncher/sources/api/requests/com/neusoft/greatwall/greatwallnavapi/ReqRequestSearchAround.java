package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.GeoPosition;
import api.structs.com.neusoft.greatwall.SearchActionType;
import api.structs.com.neusoft.greatwall.SearchCategory;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRequestSearchAround extends BaseRequest {
    private static final long serialVersionUID = -1481831650;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestSearchAround() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestSearchAround");
        this.arguments = new Arguments();
    }

    public ReqRequestSearchAround(Integer num, SearchActionType searchActionType, SearchCategory searchCategory, String str, Integer num2, GeoPosition geoPosition) {
        this();
        this.arguments.setReqId_In(num);
        this.arguments.setAction(searchActionType);
        this.arguments.setCategory(searchCategory);
        this.arguments.setKeywords(str);
        this.arguments.setSearchRadius(num2);
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

        @SerializedName("ReqId_In")
        private Integer reqId_In = 0;

        @SerializedName("Action")
        private SearchActionType action = SearchActionType.SEARCH_IN_MAP;

        @SerializedName("Category")
        private SearchCategory category = SearchCategory.KEY_WORD_SEARCH;

        @SerializedName("Keywords")
        private String keywords = "";

        @SerializedName("SearchRadius")
        private Integer searchRadius = 0;

        @SerializedName("LonLat")
        private GeoPosition lonLat = new GeoPosition();

        public Arguments() {
        }

        public Arguments(Integer num, SearchActionType searchActionType, SearchCategory searchCategory, String str, Integer num2, GeoPosition geoPosition) {
            setReqId_In(num);
            setAction(searchActionType);
            setCategory(searchCategory);
            setKeywords(str);
            setSearchRadius(num2);
            setLonLat(geoPosition);
        }

        public Integer getReqId_In() {
            return this.reqId_In;
        }

        public Arguments setReqId_In(Integer num) {
            if (num == null) {
                throw new NullPointerException("reqId_In cannot be null");
            }
            this.reqId_In = num;
            return this;
        }

        public SearchActionType getAction() {
            return this.action;
        }

        public Arguments setAction(SearchActionType searchActionType) {
            this.action = searchActionType;
            return this;
        }

        public SearchCategory getCategory() {
            return this.category;
        }

        public Arguments setCategory(SearchCategory searchCategory) {
            this.category = searchCategory;
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
            return super.equals(obj) && arguments.reqId_In.equals(this.reqId_In) && arguments.action.equals(this.action) && arguments.category.equals(this.category) && arguments.keywords.equals(this.keywords) && arguments.searchRadius.equals(this.searchRadius) && arguments.lonLat.equals(this.lonLat);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.reqId_In, this.action, this.category, this.keywords, this.searchRadius, this.lonLat);
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
        if (obj instanceof ReqRequestSearchAround) {
            return super.equals(obj) && ((ReqRequestSearchAround) obj).arguments.equals(this.arguments);
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
