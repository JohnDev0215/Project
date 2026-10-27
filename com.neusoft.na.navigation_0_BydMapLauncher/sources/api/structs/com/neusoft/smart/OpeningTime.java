package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class OpeningTime implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991337;

    @SerializedName("StartHour")
    private Integer startHour = 0;

    @SerializedName("StartMinute")
    private Integer startMinute = 0;

    @SerializedName("EndHour")
    private Integer endHour = 0;

    @SerializedName("EndMinute")
    private Integer endMinute = 0;

    public OpeningTime() {
    }

    public OpeningTime(Integer num, Integer num2, Integer num3, Integer num4) {
        setStartHour(num);
        setStartMinute(num2);
        setEndHour(num3);
        setEndMinute(num4);
    }

    public Integer getStartHour() {
        return this.startHour;
    }

    public OpeningTime setStartHour(Integer num) {
        if (num == null) {
            throw new NullPointerException("startHour cannot be null");
        }
        this.startHour = num;
        return this;
    }

    public Integer getStartMinute() {
        return this.startMinute;
    }

    public OpeningTime setStartMinute(Integer num) {
        if (num == null) {
            throw new NullPointerException("startMinute cannot be null");
        }
        this.startMinute = num;
        return this;
    }

    public Integer getEndHour() {
        return this.endHour;
    }

    public OpeningTime setEndHour(Integer num) {
        if (num == null) {
            throw new NullPointerException("endHour cannot be null");
        }
        this.endHour = num;
        return this;
    }

    public Integer getEndMinute() {
        return this.endMinute;
    }

    public OpeningTime setEndMinute(Integer num) {
        if (num == null) {
            throw new NullPointerException("endMinute cannot be null");
        }
        this.endMinute = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OpeningTime openingTime = (OpeningTime) obj;
        return Objects.equals(this.startHour, openingTime.startHour) && Objects.equals(this.startMinute, openingTime.startMinute) && Objects.equals(this.endHour, openingTime.endHour) && Objects.equals(this.endMinute, openingTime.endMinute);
    }

    public int hashCode() {
        return Objects.hash(this.startHour, this.startMinute, this.endHour, this.endMinute);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
