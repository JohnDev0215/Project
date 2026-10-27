package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ChargingPriceInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991337;

    @SerializedName("ChargingPrice")
    private String chargingPrice = "";

    @SerializedName("ChargingCurrency")
    private String chargingCurrency = "";

    @SerializedName("ChargingUnit")
    private String chargingUnit = "";

    @SerializedName("ChargingUnitAmount")
    private String chargingUnitAmount = "";

    public ChargingPriceInfo() {
    }

    public ChargingPriceInfo(String str, String str2, String str3, String str4) {
        setChargingPrice(str);
        setChargingCurrency(str2);
        setChargingUnit(str3);
        setChargingUnitAmount(str4);
    }

    public String getChargingPrice() {
        return this.chargingPrice;
    }

    public ChargingPriceInfo setChargingPrice(String str) {
        if (str == null) {
            throw new NullPointerException("chargingPrice cannot be null");
        }
        this.chargingPrice = str;
        return this;
    }

    public String getChargingCurrency() {
        return this.chargingCurrency;
    }

    public ChargingPriceInfo setChargingCurrency(String str) {
        if (str == null) {
            throw new NullPointerException("chargingCurrency cannot be null");
        }
        this.chargingCurrency = str;
        return this;
    }

    public String getChargingUnit() {
        return this.chargingUnit;
    }

    public ChargingPriceInfo setChargingUnit(String str) {
        if (str == null) {
            throw new NullPointerException("chargingUnit cannot be null");
        }
        this.chargingUnit = str;
        return this;
    }

    public String getChargingUnitAmount() {
        return this.chargingUnitAmount;
    }

    public ChargingPriceInfo setChargingUnitAmount(String str) {
        if (str == null) {
            throw new NullPointerException("chargingUnitAmount cannot be null");
        }
        this.chargingUnitAmount = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChargingPriceInfo chargingPriceInfo = (ChargingPriceInfo) obj;
        return Objects.equals(this.chargingPrice, chargingPriceInfo.chargingPrice) && Objects.equals(this.chargingCurrency, chargingPriceInfo.chargingCurrency) && Objects.equals(this.chargingUnit, chargingPriceInfo.chargingUnit) && Objects.equals(this.chargingUnitAmount, chargingPriceInfo.chargingUnitAmount);
    }

    public int hashCode() {
        return Objects.hash(this.chargingPrice, this.chargingCurrency, this.chargingUnit, this.chargingUnitAmount);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
