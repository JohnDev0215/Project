package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.UpdateFavoriteItemCommand;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestUpdateFavorite extends BaseNotification {
    private static final long serialVersionUID = -1481831744;

    @SerializedName("args")
    private UpdateFavoriteItemCommand requestUpdateFavorite;

    public NotifyRequestUpdateFavorite() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.RequestUpdateFavorite");
    }

    public NotifyRequestUpdateFavorite(UpdateFavoriteItemCommand updateFavoriteItemCommand) {
        this();
        this.requestUpdateFavorite = updateFavoriteItemCommand;
    }

    public UpdateFavoriteItemCommand getRequestUpdateFavorite() {
        return this.requestUpdateFavorite;
    }

    public NotifyRequestUpdateFavorite setRequestUpdateFavorite(UpdateFavoriteItemCommand updateFavoriteItemCommand) {
        this.requestUpdateFavorite = updateFavoriteItemCommand;
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
