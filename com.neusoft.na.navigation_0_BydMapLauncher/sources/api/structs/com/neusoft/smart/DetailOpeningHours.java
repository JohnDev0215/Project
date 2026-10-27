package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DetailOpeningHours implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991337;

    @SerializedName("StartDayOfWeek")
    private Integer startDayOfWeek = 0;

    @SerializedName("EndDayOfWeek")
    private Integer endDayOfWeek = 0;

    @SerializedName("OpeningTimeList")
    private List<OpeningTime> openingTimeList = new ArrayList();

    @SerializedName("PriceIndex")
    private Integer priceIndex = 0;

    public DetailOpeningHours() {
    }

    public DetailOpeningHours(Integer num, Integer num2, List<OpeningTime> list, Integer num3) {
        setStartDayOfWeek(num);
        setEndDayOfWeek(num2);
        setOpeningTimeList(list);
        setPriceIndex(num3);
    }

    public Integer getStartDayOfWeek() {
        return this.startDayOfWeek;
    }

    public DetailOpeningHours setStartDayOfWeek(Integer num) {
        if (num == null) {
            throw new NullPointerException("startDayOfWeek cannot be null");
        }
        this.startDayOfWeek = num;
        return this;
    }

    public Integer getEndDayOfWeek() {
        return this.endDayOfWeek;
    }

    public DetailOpeningHours setEndDayOfWeek(Integer num) {
        if (num == null) {
            throw new NullPointerException("endDayOfWeek cannot be null");
        }
        this.endDayOfWeek = num;
        return this;
    }

    public List<OpeningTime> getOpeningTimeList() {
        return this.openingTimeList;
    }

    public DetailOpeningHours setOpeningTimeList(List<OpeningTime> list) {
        if (list == null) {
            throw new NullPointerException("openingTimeList cannot be null");
        }
        this.openingTimeList = list;
        return this;
    }

    public DetailOpeningHours setOpeningTimeList(OpeningTime... openingTimeArr) {
        return setOpeningTimeList(Arrays.asList(openingTimeArr));
    }

    public Integer getPriceIndex() {
        return this.priceIndex;
    }

    public DetailOpeningHours setPriceIndex(Integer num) {
        if (num == null) {
            throw new NullPointerException("priceIndex cannot be null");
        }
        this.priceIndex = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetailOpeningHours detailOpeningHours = (DetailOpeningHours) obj;
        return Objects.equals(this.startDayOfWeek, detailOpeningHours.startDayOfWeek) && Objects.equals(this.endDayOfWeek, detailOpeningHours.endDayOfWeek) && Objects.equals(this.openingTimeList, detailOpeningHours.openingTimeList) && Objects.equals(this.priceIndex, detailOpeningHours.priceIndex);
    }

    public int hashCode() {
        return Objects.hash(this.startDayOfWeek, this.endDayOfWeek, this.openingTimeList, this.priceIndex);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
