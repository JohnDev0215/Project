package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.FavoriteCommandBase;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGetLoginStatus extends BaseNotification {
    private static final long serialVersionUID = -1479064063;

    @SerializedName("args")
    private FavoriteCommandBase getLoginStatus;

    public NotifyGetLoginStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.GetLoginStatus");
    }

    public NotifyGetLoginStatus(FavoriteCommandBase favoriteCommandBase) {
        this();
        this.getLoginStatus = favoriteCommandBase;
    }

    public FavoriteCommandBase getGetLoginStatus() {
        return this.getLoginStatus;
    }

    public NotifyGetLoginStatus setGetLoginStatus(FavoriteCommandBase favoriteCommandBase) {
        this.getLoginStatus = favoriteCommandBase;
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
