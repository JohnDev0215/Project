package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.RemoveFavoriteItemCommand;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestRemoveFavorite extends BaseNotification {
    private static final long serialVersionUID = -1481831744;

    @SerializedName("args")
    private RemoveFavoriteItemCommand requestRemoveFavorite;

    public NotifyRequestRemoveFavorite() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.RequestRemoveFavorite");
    }

    public NotifyRequestRemoveFavorite(RemoveFavoriteItemCommand removeFavoriteItemCommand) {
        this();
        this.requestRemoveFavorite = removeFavoriteItemCommand;
    }

    public RemoveFavoriteItemCommand getRequestRemoveFavorite() {
        return this.requestRemoveFavorite;
    }

    public NotifyRequestRemoveFavorite setRequestRemoveFavorite(RemoveFavoriteItemCommand removeFavoriteItemCommand) {
        this.requestRemoveFavorite = removeFavoriteItemCommand;
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
