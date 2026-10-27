package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.AddFavoriteItemCommand;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestAddFavorite extends BaseNotification {
    private static final long serialVersionUID = -1481831801;

    @SerializedName("args")
    private AddFavoriteItemCommand requestAddFavorite;

    public NotifyRequestAddFavorite() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.RequestAddFavorite");
    }

    public NotifyRequestAddFavorite(AddFavoriteItemCommand addFavoriteItemCommand) {
        this();
        this.requestAddFavorite = addFavoriteItemCommand;
    }

    public AddFavoriteItemCommand getRequestAddFavorite() {
        return this.requestAddFavorite;
    }

    public NotifyRequestAddFavorite setRequestAddFavorite(AddFavoriteItemCommand addFavoriteItemCommand) {
        this.requestAddFavorite = addFavoriteItemCommand;
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
