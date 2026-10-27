package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ChargingStationCount implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991394;

    @SerializedName("TotalCount")
    private String totalCount = "";

    @SerializedName("AvailableCount")
    private String availableCount = "";

    public ChargingStationCount() {
    }

    public ChargingStationCount(String str, String str2) {
        setTotalCount(str);
        setAvailableCount(str2);
    }

    public String getTotalCount() {
        return this.totalCount;
    }

    public ChargingStationCount setTotalCount(String str) {
        if (str == null) {
            throw new NullPointerException("totalCount cannot be null");
        }
        this.totalCount = str;
        return this;
    }

    public String getAvailableCount() {
        return this.availableCount;
    }

    public ChargingStationCount setAvailableCount(String str) {
        if (str == null) {
            throw new NullPointerException("availableCount cannot be null");
        }
        this.availableCount = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChargingStationCount chargingStationCount = (ChargingStationCount) obj;
        return Objects.equals(this.totalCount, chargingStationCount.totalCount) && Objects.equals(this.availableCount, chargingStationCount.availableCount);
    }

    public int hashCode() {
        return Objects.hash(this.totalCount, this.availableCount);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
