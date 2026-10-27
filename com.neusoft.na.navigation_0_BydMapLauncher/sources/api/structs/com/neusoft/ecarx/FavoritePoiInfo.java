package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FavoritePoiInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480913027;

    @SerializedName("FavoriteType")
    private FavoriteType favoriteType = FavoriteType.GENERAL_FAVORITES;

    @SerializedName("PoiInfo")
    private PoiInfo poiInfo = new PoiInfo();

    public FavoritePoiInfo() {
    }

    public FavoritePoiInfo(FavoriteType favoriteType, PoiInfo poiInfo) {
        setFavoriteType(favoriteType);
        setPoiInfo(poiInfo);
    }

    public FavoriteType getFavoriteType() {
        return this.favoriteType;
    }

    public FavoritePoiInfo setFavoriteType(FavoriteType favoriteType) {
        this.favoriteType = favoriteType;
        return this;
    }

    public PoiInfo getPoiInfo() {
        return this.poiInfo;
    }

    public FavoritePoiInfo setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FavoritePoiInfo favoritePoiInfo = (FavoritePoiInfo) obj;
        return Objects.equals(this.favoriteType, favoritePoiInfo.favoriteType) && Objects.equals(this.poiInfo, favoritePoiInfo.poiInfo);
    }

    public int hashCode() {
        return Objects.hash(this.favoriteType, this.poiInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
