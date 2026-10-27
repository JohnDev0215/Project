package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AddFavoriteItemCommand implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990497;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("FavoriteItemList")
    private List<FavoriteItem> favoriteItemList = new ArrayList();

    public AddFavoriteItemCommand() {
    }

    public AddFavoriteItemCommand(Integer num, List<FavoriteItem> list) {
        setReqId(num);
        setFavoriteItemList(list);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public AddFavoriteItemCommand setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public List<FavoriteItem> getFavoriteItemList() {
        return this.favoriteItemList;
    }

    public AddFavoriteItemCommand setFavoriteItemList(List<FavoriteItem> list) {
        if (list == null) {
            throw new NullPointerException("favoriteItemList cannot be null");
        }
        this.favoriteItemList = list;
        return this;
    }

    public AddFavoriteItemCommand setFavoriteItemList(FavoriteItem... favoriteItemArr) {
        return setFavoriteItemList(Arrays.asList(favoriteItemArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AddFavoriteItemCommand addFavoriteItemCommand = (AddFavoriteItemCommand) obj;
        return Objects.equals(this.reqId, addFavoriteItemCommand.reqId) && Objects.equals(this.favoriteItemList, addFavoriteItemCommand.favoriteItemList);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.favoriteItemList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
