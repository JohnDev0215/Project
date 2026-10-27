package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.SearchActionType;
import api.structs.com.neusoft.ecarx.SearchType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqAlongTheWaySearch extends BaseRequest {
    private static final long serialVersionUID = -1481836607;

    @SerializedName("args")
    private Arguments arguments;

    public ReqAlongTheWaySearch() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.AlongTheWaySearch");
        this.arguments = new Arguments();
    }

    public ReqAlongTheWaySearch(SearchActionType searchActionType, SearchType searchType) {
        this();
        this.arguments.setAction(searchActionType);
        this.arguments.setSearchType(searchType);
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

        public Arguments() {
        }

        public Arguments(SearchActionType searchActionType, SearchType searchType) {
            setAction(searchActionType);
            setSearchType(searchType);
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

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.action.equals(this.action) && arguments.searchType.equals(this.searchType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.action, this.searchType);
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
        if (obj instanceof ReqAlongTheWaySearch) {
            return super.equals(obj) && ((ReqAlongTheWaySearch) obj).arguments.equals(this.arguments);
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
