package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.FavoritePoiInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotifyFavoritesInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832640;

    @SerializedName("args")
    private List<FavoritePoiInfo> favoritesInfoUpdate;

    public NotifyFavoritesInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.FavoritesInfoUpdate");
    }

    public NotifyFavoritesInfoUpdate(List<FavoritePoiInfo> list) {
        this();
        this.favoritesInfoUpdate = list;
    }

    public List<FavoritePoiInfo> getFavoritesInfoUpdate() {
        return this.favoritesInfoUpdate;
    }

    public NotifyFavoritesInfoUpdate setFavoritesInfoUpdate(List<FavoritePoiInfo> list) {
        if (list == null) {
            throw new NullPointerException("favoritesInfoUpdate cannot be null");
        }
        this.favoritesInfoUpdate = list;
        return this;
    }

    public NotifyFavoritesInfoUpdate setFavoritesInfoUpdate(FavoritePoiInfo... favoritePoiInfoArr) {
        return setFavoritesInfoUpdate(Arrays.asList(favoritePoiInfoArr));
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
