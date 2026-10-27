package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class OffStreetParkingPrice implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991337;

    @SerializedName("GroupId")
    private Integer groupId = 0;

    @SerializedName("Amount")
    private String amount = "";

    @SerializedName("Currency")
    private String currency = "";

    @SerializedName("Unit")
    private String unit = "";

    public OffStreetParkingPrice() {
    }

    public OffStreetParkingPrice(Integer num, String str, String str2, String str3) {
        setGroupId(num);
        setAmount(str);
        setCurrency(str2);
        setUnit(str3);
    }

    public Integer getGroupId() {
        return this.groupId;
    }

    public OffStreetParkingPrice setGroupId(Integer num) {
        if (num == null) {
            throw new NullPointerException("groupId cannot be null");
        }
        this.groupId = num;
        return this;
    }

    public String getAmount() {
        return this.amount;
    }

    public OffStreetParkingPrice setAmount(String str) {
        if (str == null) {
            throw new NullPointerException("amount cannot be null");
        }
        this.amount = str;
        return this;
    }

    public String getCurrency() {
        return this.currency;
    }

    public OffStreetParkingPrice setCurrency(String str) {
        if (str == null) {
            throw new NullPointerException("currency cannot be null");
        }
        this.currency = str;
        return this;
    }

    public String getUnit() {
        return this.unit;
    }

    public OffStreetParkingPrice setUnit(String str) {
        if (str == null) {
            throw new NullPointerException("unit cannot be null");
        }
        this.unit = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OffStreetParkingPrice offStreetParkingPrice = (OffStreetParkingPrice) obj;
        return Objects.equals(this.groupId, offStreetParkingPrice.groupId) && Objects.equals(this.amount, offStreetParkingPrice.amount) && Objects.equals(this.currency, offStreetParkingPrice.currency) && Objects.equals(this.unit, offStreetParkingPrice.unit);
    }

    public int hashCode() {
        return Objects.hash(this.groupId, this.amount, this.currency, this.unit);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
