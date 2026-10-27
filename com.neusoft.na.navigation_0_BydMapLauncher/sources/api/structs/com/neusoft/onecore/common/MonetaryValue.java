package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class MonetaryValue implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137622;

    @SerializedName("amount")
    private Float amount = Float.valueOf(0.0f);

    @SerializedName("currency")
    private String currency = "";

    public MonetaryValue() {
    }

    public MonetaryValue(Float f, String str) {
        setAmount(f);
        setCurrency(str);
    }

    public Float getAmount() {
        return this.amount;
    }

    public MonetaryValue setAmount(Float f) {
        if (f == null) {
            throw new NullPointerException("amount cannot be null");
        }
        this.amount = f;
        return this;
    }

    public String getCurrency() {
        return this.currency;
    }

    public MonetaryValue setCurrency(String str) {
        if (str == null) {
            throw new NullPointerException("currency cannot be null");
        }
        this.currency = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MonetaryValue monetaryValue = (MonetaryValue) obj;
        return Objects.equals(this.amount, monetaryValue.amount) && Objects.equals(this.currency, monetaryValue.currency);
    }

    public int hashCode() {
        return Objects.hash(this.amount, this.currency);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
