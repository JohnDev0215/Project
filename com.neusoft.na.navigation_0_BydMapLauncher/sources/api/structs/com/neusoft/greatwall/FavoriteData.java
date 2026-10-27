package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FavoriteData implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910053;

    @SerializedName("Type")
    private FavoriteType type = FavoriteType.HOME;

    @SerializedName("LocationInfo")
    private LocationInfo locationInfo = new LocationInfo();

    public FavoriteData() {
    }

    public FavoriteData(FavoriteType favoriteType, LocationInfo locationInfo) {
        setType(favoriteType);
        setLocationInfo(locationInfo);
    }

    public FavoriteType getType() {
        return this.type;
    }

    public FavoriteData setType(FavoriteType favoriteType) {
        this.type = favoriteType;
        return this;
    }

    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public FavoriteData setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FavoriteData favoriteData = (FavoriteData) obj;
        return Objects.equals(this.type, favoriteData.type) && Objects.equals(this.locationInfo, favoriteData.locationInfo);
    }

    public int hashCode() {
        return Objects.hash(this.type, this.locationInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
