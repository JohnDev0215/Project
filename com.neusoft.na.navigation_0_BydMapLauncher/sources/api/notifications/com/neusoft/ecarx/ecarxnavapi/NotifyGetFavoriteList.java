package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.FavoriteCommandBase;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGetFavoriteList extends BaseNotification {
    private static final long serialVersionUID = -1481831801;

    @SerializedName("args")
    private FavoriteCommandBase getFavoriteList;

    public NotifyGetFavoriteList() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetFavoriteList");
    }

    public NotifyGetFavoriteList(FavoriteCommandBase favoriteCommandBase) {
        this();
        this.getFavoriteList = favoriteCommandBase;
    }

    public FavoriteCommandBase getGetFavoriteList() {
        return this.getFavoriteList;
    }

    public NotifyGetFavoriteList setGetFavoriteList(FavoriteCommandBase favoriteCommandBase) {
        this.getFavoriteList = favoriteCommandBase;
        return this;
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
