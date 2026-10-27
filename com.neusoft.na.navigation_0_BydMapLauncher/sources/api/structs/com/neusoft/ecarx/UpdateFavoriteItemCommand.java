package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateFavoriteItemCommand implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912094;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("FavoriteItem")
    private FavoriteItem favoriteItem = new FavoriteItem();

    public UpdateFavoriteItemCommand() {
    }

    public UpdateFavoriteItemCommand(Integer num, FavoriteItem favoriteItem) {
        setReqId(num);
        setFavoriteItem(favoriteItem);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public UpdateFavoriteItemCommand setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public FavoriteItem getFavoriteItem() {
        return this.favoriteItem;
    }

    public UpdateFavoriteItemCommand setFavoriteItem(FavoriteItem favoriteItem) {
        this.favoriteItem = favoriteItem;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UpdateFavoriteItemCommand updateFavoriteItemCommand = (UpdateFavoriteItemCommand) obj;
        return Objects.equals(this.reqId, updateFavoriteItemCommand.reqId) && Objects.equals(this.favoriteItem, updateFavoriteItemCommand.favoriteItem);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.favoriteItem);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
