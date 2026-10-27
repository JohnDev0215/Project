package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.FavoritePoiInfo;
import api.structs.com.neusoft.smart.ResultCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetFavoritePois extends BaseResponse {
    private static final long serialVersionUID = -1479066885;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResGetFavoritePois() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetFavoritePois");
        this.arguments = new Arguments();
    }

    public ResGetFavoritePois(List<FavoritePoiInfo> list) {
        this();
        this.arguments.setFavoritePoiInfoList(list);
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

        @SerializedName("FavoritePoiInfoList")
        private List<FavoritePoiInfo> favoritePoiInfoList = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<FavoritePoiInfo> list) {
            setFavoritePoiInfoList(list);
        }

        public List<FavoritePoiInfo> getFavoritePoiInfoList() {
            return this.favoritePoiInfoList;
        }

        public Arguments setFavoritePoiInfoList(List<FavoritePoiInfo> list) {
            if (list == null) {
                throw new NullPointerException("favoritePoiInfoList cannot be null");
            }
            this.favoritePoiInfoList = list;
            return this;
        }

        public Arguments setFavoritePoiInfoList(FavoritePoiInfo... favoritePoiInfoArr) {
            return setFavoritePoiInfoList(Arrays.asList(favoritePoiInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).favoritePoiInfoList.equals(this.favoritePoiInfoList);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.favoritePoiInfoList);
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
        if (obj instanceof ResGetFavoritePois) {
            return super.equals(obj) && ((ResGetFavoritePois) obj).arguments.equals(this.arguments);
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
