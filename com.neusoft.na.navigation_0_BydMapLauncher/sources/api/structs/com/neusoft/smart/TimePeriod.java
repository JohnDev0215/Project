package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class TimePeriod implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991394;

    @SerializedName("OpeningTime")
    private Integer openingTime = 0;

    @SerializedName("ClosingTime")
    private Integer closingTime = 0;

    public TimePeriod() {
    }

    public TimePeriod(Integer num, Integer num2) {
        setOpeningTime(num);
        setClosingTime(num2);
    }

    public Integer getOpeningTime() {
        return this.openingTime;
    }

    public TimePeriod setOpeningTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("openingTime cannot be null");
        }
        this.openingTime = num;
        return this;
    }

    public Integer getClosingTime() {
        return this.closingTime;
    }

    public TimePeriod setClosingTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("closingTime cannot be null");
        }
        this.closingTime = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TimePeriod timePeriod = (TimePeriod) obj;
        return Objects.equals(this.openingTime, timePeriod.openingTime) && Objects.equals(this.closingTime, timePeriod.closingTime);
    }

    public int hashCode() {
        return Objects.hash(this.openingTime, this.closingTime);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
