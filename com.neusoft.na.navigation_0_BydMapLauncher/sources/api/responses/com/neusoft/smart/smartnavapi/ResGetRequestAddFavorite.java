package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.AddFavoriteItemCommand;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetRequestAddFavorite extends BaseResponse {
    private static final long serialVersionUID = -1479066014;

    @SerializedName("args")
    private AddFavoriteItemCommand requestAddFavorite;

    public ResGetRequestAddFavorite() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetRequestAddFavorite");
    }

    public ResGetRequestAddFavorite(AddFavoriteItemCommand addFavoriteItemCommand) {
        this();
        this.requestAddFavorite = addFavoriteItemCommand;
    }

    public AddFavoriteItemCommand getRequestAddFavorite() {
        return this.requestAddFavorite;
    }

    public ResGetRequestAddFavorite setRequestAddFavorite(AddFavoriteItemCommand addFavoriteItemCommand) {
        this.requestAddFavorite = addFavoriteItemCommand;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.requestAddFavorite = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetRequestAddFavorite)) {
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
