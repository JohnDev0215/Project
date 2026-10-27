package api.responses.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.PoiInfo;
import api.structs.com.neusoft.ecarx.ResultCode;
import api.structs.com.neusoft.ecarx.SuggestionCity;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResAlongTheWaySearch extends BaseResponse {
    private static final long serialVersionUID = -1481835614;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResAlongTheWaySearch() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.AlongTheWaySearch");
        this.arguments = new Arguments();
    }

    public ResAlongTheWaySearch(List<SuggestionCity> list, List<PoiInfo> list2) {
        this();
        this.arguments.setSuggestionCityList(list);
        this.arguments.setPoiInfoList(list2);
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

        @SerializedName("SuggestionCityList")
        private List<SuggestionCity> suggestionCityList = new ArrayList();

        @SerializedName("PoiInfoList")
        private List<PoiInfo> poiInfoList = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<SuggestionCity> list, List<PoiInfo> list2) {
            setSuggestionCityList(list);
            setPoiInfoList(list2);
        }

        public List<SuggestionCity> getSuggestionCityList() {
            return this.suggestionCityList;
        }

        public Arguments setSuggestionCityList(List<SuggestionCity> list) {
            if (list == null) {
                throw new NullPointerException("suggestionCityList cannot be null");
            }
            this.suggestionCityList = list;
            return this;
        }

        public Arguments setSuggestionCityList(SuggestionCity... suggestionCityArr) {
            return setSuggestionCityList(Arrays.asList(suggestionCityArr));
        }

        public List<PoiInfo> getPoiInfoList() {
            return this.poiInfoList;
        }

        public Arguments setPoiInfoList(List<PoiInfo> list) {
            if (list == null) {
                throw new NullPointerException("poiInfoList cannot be null");
            }
            this.poiInfoList = list;
            return this;
        }

        public Arguments setPoiInfoList(PoiInfo... poiInfoArr) {
            return setPoiInfoList(Arrays.asList(poiInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.suggestionCityList.equals(this.suggestionCityList) && arguments.poiInfoList.equals(this.poiInfoList);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.suggestionCityList, this.poiInfoList);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        EMessageType eMessageType2 = EMessageType.Error;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResAlongTheWaySearch) {
            return super.equals(obj) && ((ResAlongTheWaySearch) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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

    public void setCustomError(ResultCode resultCode) {
        this.customError = resultCode;
    }

    public ResultCode getCustomError() {
        return this.customError;
    }
}
