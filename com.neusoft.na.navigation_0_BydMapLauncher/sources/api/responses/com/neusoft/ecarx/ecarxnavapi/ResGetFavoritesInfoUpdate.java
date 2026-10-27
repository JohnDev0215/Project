package api.responses.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.FavoritePoiInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetFavoritesInfoUpdate extends BaseResponse {
    private static final long serialVersionUID = -1481835520;

    @SerializedName("args")
    private List<FavoritePoiInfo> favoritesInfoUpdate;

    public ResGetFavoritesInfoUpdate() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetFavoritesInfoUpdate");
    }

    public ResGetFavoritesInfoUpdate(List<FavoritePoiInfo> list) {
        this();
        this.favoritesInfoUpdate = list;
    }

    public List<FavoritePoiInfo> getFavoritesInfoUpdate() {
        return this.favoritesInfoUpdate;
    }

    public ResGetFavoritesInfoUpdate setFavoritesInfoUpdate(List<FavoritePoiInfo> list) {
        if (list == null) {
            throw new NullPointerException("favoritesInfoUpdate cannot be null");
        }
        this.favoritesInfoUpdate = list;
        return this;
    }

    public ResGetFavoritesInfoUpdate setFavoritesInfoUpdate(FavoritePoiInfo... favoritePoiInfoArr) {
        return setFavoritesInfoUpdate(Arrays.asList(favoritePoiInfoArr));
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.favoritesInfoUpdate = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetFavoritesInfoUpdate)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
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
