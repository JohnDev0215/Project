package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class OpeningHours implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991394;

    @SerializedName("Day")
    private Integer day = 0;

    @SerializedName("TimePeriodList")
    private List<TimePeriod> timePeriodList = new ArrayList();

    public OpeningHours() {
    }

    public OpeningHours(Integer num, List<TimePeriod> list) {
        setDay(num);
        setTimePeriodList(list);
    }

    public Integer getDay() {
        return this.day;
    }

    public OpeningHours setDay(Integer num) {
        if (num == null) {
            throw new NullPointerException("day cannot be null");
        }
        this.day = num;
        return this;
    }

    public List<TimePeriod> getTimePeriodList() {
        return this.timePeriodList;
    }

    public OpeningHours setTimePeriodList(List<TimePeriod> list) {
        if (list == null) {
            throw new NullPointerException("timePeriodList cannot be null");
        }
        this.timePeriodList = list;
        return this;
    }

    public OpeningHours setTimePeriodList(TimePeriod... timePeriodArr) {
        return setTimePeriodList(Arrays.asList(timePeriodArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OpeningHours openingHours = (OpeningHours) obj;
        return Objects.equals(this.day, openingHours.day) && Objects.equals(this.timePeriodList, openingHours.timePeriodList);
    }

    public int hashCode() {
        return Objects.hash(this.day, this.timePeriodList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
