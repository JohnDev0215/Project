package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DayTime implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137622;

    @SerializedName("Year")
    private Integer year = 0;

    @SerializedName("Month")
    private Integer month = 0;

    @SerializedName("Day")
    private Integer day = 0;

    @SerializedName("Hour")
    private Integer hour = 0;

    @SerializedName("Minute")
    private Integer minute = 0;

    @SerializedName("Second")
    private Integer second = 0;

    public DayTime() {
    }

    public DayTime(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6) {
        setYear(num);
        setMonth(num2);
        setDay(num3);
        setHour(num4);
        setMinute(num5);
        setSecond(num6);
    }

    public Integer getYear() {
        return this.year;
    }

    public DayTime setYear(Integer num) {
        if (num == null) {
            throw new NullPointerException("year cannot be null");
        }
        this.year = num;
        return this;
    }

    public Integer getMonth() {
        return this.month;
    }

    public DayTime setMonth(Integer num) {
        if (num == null) {
            throw new NullPointerException("month cannot be null");
        }
        this.month = num;
        return this;
    }

    public Integer getDay() {
        return this.day;
    }

    public DayTime setDay(Integer num) {
        if (num == null) {
            throw new NullPointerException("day cannot be null");
        }
        this.day = num;
        return this;
    }

    public Integer getHour() {
        return this.hour;
    }

    public DayTime setHour(Integer num) {
        if (num == null) {
            throw new NullPointerException("hour cannot be null");
        }
        this.hour = num;
        return this;
    }

    public Integer getMinute() {
        return this.minute;
    }

    public DayTime setMinute(Integer num) {
        if (num == null) {
            throw new NullPointerException("minute cannot be null");
        }
        this.minute = num;
        return this;
    }

    public Integer getSecond() {
        return this.second;
    }

    public DayTime setSecond(Integer num) {
        if (num == null) {
            throw new NullPointerException("second cannot be null");
        }
        this.second = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DayTime dayTime = (DayTime) obj;
        return Objects.equals(this.year, dayTime.year) && Objects.equals(this.month, dayTime.month) && Objects.equals(this.day, dayTime.day) && Objects.equals(this.hour, dayTime.hour) && Objects.equals(this.minute, dayTime.minute) && Objects.equals(this.second, dayTime.second);
    }

    public int hashCode() {
        return Objects.hash(this.year, this.month, this.day, this.hour, this.minute, this.second);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
