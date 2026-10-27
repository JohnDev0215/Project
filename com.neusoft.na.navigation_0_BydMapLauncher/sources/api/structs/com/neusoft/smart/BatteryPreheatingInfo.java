package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class BatteryPreheatingInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990403;

    @SerializedName("RemainTimeToDest")
    private Integer remainTimeToDest = 0;

    @SerializedName("DistanceToDest")
    private Integer distanceToDest = 0;

    @SerializedName("SocAtDestination")
    private Integer socAtDestination = 0;

    @SerializedName("IsChargingStation")
    private Boolean isChargingStation = false;

    @SerializedName("ChargingStationPwr")
    private Double chargingStationPwr = Double.valueOf(0.0d);

    public BatteryPreheatingInfo() {
    }

    public BatteryPreheatingInfo(Integer num, Integer num2, Integer num3, Boolean bool, Double d) {
        setRemainTimeToDest(num);
        setDistanceToDest(num2);
        setSocAtDestination(num3);
        setIsChargingStation(bool);
        setChargingStationPwr(d);
    }

    public Integer getRemainTimeToDest() {
        return this.remainTimeToDest;
    }

    public BatteryPreheatingInfo setRemainTimeToDest(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainTimeToDest cannot be null");
        }
        this.remainTimeToDest = num;
        return this;
    }

    public Integer getDistanceToDest() {
        return this.distanceToDest;
    }

    public BatteryPreheatingInfo setDistanceToDest(Integer num) {
        if (num == null) {
            throw new NullPointerException("distanceToDest cannot be null");
        }
        this.distanceToDest = num;
        return this;
    }

    public Integer getSocAtDestination() {
        return this.socAtDestination;
    }

    public BatteryPreheatingInfo setSocAtDestination(Integer num) {
        if (num == null) {
            throw new NullPointerException("socAtDestination cannot be null");
        }
        this.socAtDestination = num;
        return this;
    }

    public Boolean getIsChargingStation() {
        return this.isChargingStation;
    }

    public BatteryPreheatingInfo setIsChargingStation(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isChargingStation cannot be null");
        }
        this.isChargingStation = bool;
        return this;
    }

    public Double getChargingStationPwr() {
        return this.chargingStationPwr;
    }

    public BatteryPreheatingInfo setChargingStationPwr(Double d) {
        if (d == null) {
            throw new NullPointerException("chargingStationPwr cannot be null");
        }
        this.chargingStationPwr = d;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BatteryPreheatingInfo batteryPreheatingInfo = (BatteryPreheatingInfo) obj;
        return Objects.equals(this.remainTimeToDest, batteryPreheatingInfo.remainTimeToDest) && Objects.equals(this.distanceToDest, batteryPreheatingInfo.distanceToDest) && Objects.equals(this.socAtDestination, batteryPreheatingInfo.socAtDestination) && Objects.equals(this.isChargingStation, batteryPreheatingInfo.isChargingStation) && Objects.equals(this.chargingStationPwr, batteryPreheatingInfo.chargingStationPwr);
    }

    public int hashCode() {
        return Objects.hash(this.remainTimeToDest, this.distanceToDest, this.socAtDestination, this.isChargingStation, this.chargingStationPwr);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
