package api.responses.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.FavoriteCommandBase;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetGetFavoriteList extends BaseResponse {
    private static final long serialVersionUID = -1481835462;

    @SerializedName("args")
    private FavoriteCommandBase getFavoriteList;

    public ResGetGetFavoriteList() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetGetFavoriteList");
    }

    public ResGetGetFavoriteList(FavoriteCommandBase favoriteCommandBase) {
        this();
        this.getFavoriteList = favoriteCommandBase;
    }

    public FavoriteCommandBase getGetFavoriteList() {
        return this.getFavoriteList;
    }

    public ResGetGetFavoriteList setGetFavoriteList(FavoriteCommandBase favoriteCommandBase) {
        this.getFavoriteList = favoriteCommandBase;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.getFavoriteList = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetGetFavoriteList)) {
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
