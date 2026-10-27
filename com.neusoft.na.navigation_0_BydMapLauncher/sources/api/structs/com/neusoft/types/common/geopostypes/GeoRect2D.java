package api.structs.com.neusoft.types.common.geopostypes;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GeoRect2D implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137506;

    @SerializedName("SouthWest")
    private GeoPos2D southWest = new GeoPos2D();

    @SerializedName("NorthEast")
    private GeoPos2D northEast = new GeoPos2D();

    public GeoRect2D() {
    }

    public GeoRect2D(GeoPos2D geoPos2D, GeoPos2D geoPos2D2) {
        setSouthWest(geoPos2D);
        setNorthEast(geoPos2D2);
    }

    public GeoPos2D getSouthWest() {
        return this.southWest;
    }

    public GeoRect2D setSouthWest(GeoPos2D geoPos2D) {
        this.southWest = geoPos2D;
        return this;
    }

    public GeoPos2D getNorthEast() {
        return this.northEast;
    }

    public GeoRect2D setNorthEast(GeoPos2D geoPos2D) {
        this.northEast = geoPos2D;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoRect2D geoRect2D = (GeoRect2D) obj;
        return Objects.equals(this.southWest, geoRect2D.southWest) && Objects.equals(this.northEast, geoRect2D.northEast);
    }

    public int hashCode() {
        return Objects.hash(this.southWest, this.northEast);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
