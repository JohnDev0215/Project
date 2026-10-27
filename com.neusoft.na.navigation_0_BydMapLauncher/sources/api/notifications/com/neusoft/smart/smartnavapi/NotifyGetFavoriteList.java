package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.FavoriteCommandBase;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGetFavoriteList extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private FavoriteCommandBase getFavoriteList;

    public NotifyGetFavoriteList() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.GetFavoriteList");
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
