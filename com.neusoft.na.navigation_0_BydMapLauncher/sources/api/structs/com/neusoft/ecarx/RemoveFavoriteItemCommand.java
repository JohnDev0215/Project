package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RemoveFavoriteItemCommand implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912094;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("FavoriteId")
    private String favoriteId = "";

    @SerializedName("RemoveALL")
    private Boolean removeALL = false;

    public RemoveFavoriteItemCommand() {
    }

    public RemoveFavoriteItemCommand(Integer num, String str, Boolean bool) {
        setReqId(num);
        setFavoriteId(str);
        setRemoveALL(bool);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public RemoveFavoriteItemCommand setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public String getFavoriteId() {
        return this.favoriteId;
    }

    public RemoveFavoriteItemCommand setFavoriteId(String str) {
        if (str == null) {
            throw new NullPointerException("favoriteId cannot be null");
        }
        this.favoriteId = str;
        return this;
    }

    public Boolean getRemoveALL() {
        return this.removeALL;
    }

    public RemoveFavoriteItemCommand setRemoveALL(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("removeALL cannot be null");
        }
        this.removeALL = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RemoveFavoriteItemCommand removeFavoriteItemCommand = (RemoveFavoriteItemCommand) obj;
        return Objects.equals(this.reqId, removeFavoriteItemCommand.reqId) && Objects.equals(this.favoriteId, removeFavoriteItemCommand.favoriteId) && Objects.equals(this.removeALL, removeFavoriteItemCommand.removeALL);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.favoriteId, this.removeALL);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
