package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PoiDetailInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991337;

    @SerializedName("Score")
    private String score = "";

    @SerializedName("ChargingPriceInfoList")
    private List<ChargingPriceInfo> chargingPriceInfoList = new ArrayList();

    @SerializedName("PriceOpeningHoursList")
    private List<DetailOpeningHours> priceOpeningHoursList = new ArrayList();

    @SerializedName("OffStreetParkingPriceList")
    private List<OffStreetParkingPrice> offStreetParkingPriceList = new ArrayList();

    @SerializedName("DetailOpeningHoursList")
    private List<DetailOpeningHours> detailOpeningHoursList = new ArrayList();

    @SerializedName("PhoneNumber")
    private List<String> phoneNumber = new ArrayList();

    @SerializedName("Address")
    private String address = "";

    public PoiDetailInfo() {
    }

    public PoiDetailInfo(String str, List<ChargingPriceInfo> list, List<DetailOpeningHours> list2, List<OffStreetParkingPrice> list3, List<DetailOpeningHours> list4, List<String> list5, String str2) {
        setScore(str);
        setChargingPriceInfoList(list);
        setPriceOpeningHoursList(list2);
        setOffStreetParkingPriceList(list3);
        setDetailOpeningHoursList(list4);
        setPhoneNumber(list5);
        setAddress(str2);
    }

    public String getScore() {
        return this.score;
    }

    public PoiDetailInfo setScore(String str) {
        if (str == null) {
            throw new NullPointerException("score cannot be null");
        }
        this.score = str;
        return this;
    }

    public List<ChargingPriceInfo> getChargingPriceInfoList() {
        return this.chargingPriceInfoList;
    }

    public PoiDetailInfo setChargingPriceInfoList(List<ChargingPriceInfo> list) {
        if (list == null) {
            throw new NullPointerException("chargingPriceInfoList cannot be null");
        }
        this.chargingPriceInfoList = list;
        return this;
    }

    public PoiDetailInfo setChargingPriceInfoList(ChargingPriceInfo... chargingPriceInfoArr) {
        return setChargingPriceInfoList(Arrays.asList(chargingPriceInfoArr));
    }

    public List<DetailOpeningHours> getPriceOpeningHoursList() {
        return this.priceOpeningHoursList;
    }

    public PoiDetailInfo setPriceOpeningHoursList(List<DetailOpeningHours> list) {
        if (list == null) {
            throw new NullPointerException("priceOpeningHoursList cannot be null");
        }
        this.priceOpeningHoursList = list;
        return this;
    }

    public PoiDetailInfo setPriceOpeningHoursList(DetailOpeningHours... detailOpeningHoursArr) {
        return setPriceOpeningHoursList(Arrays.asList(detailOpeningHoursArr));
    }

    public List<OffStreetParkingPrice> getOffStreetParkingPriceList() {
        return this.offStreetParkingPriceList;
    }

    public PoiDetailInfo setOffStreetParkingPriceList(List<OffStreetParkingPrice> list) {
        if (list == null) {
            throw new NullPointerException("offStreetParkingPriceList cannot be null");
        }
        this.offStreetParkingPriceList = list;
        return this;
    }

    public PoiDetailInfo setOffStreetParkingPriceList(OffStreetParkingPrice... offStreetParkingPriceArr) {
        return setOffStreetParkingPriceList(Arrays.asList(offStreetParkingPriceArr));
    }

    public List<DetailOpeningHours> getDetailOpeningHoursList() {
        return this.detailOpeningHoursList;
    }

    public PoiDetailInfo setDetailOpeningHoursList(List<DetailOpeningHours> list) {
        if (list == null) {
            throw new NullPointerException("detailOpeningHoursList cannot be null");
        }
        this.detailOpeningHoursList = list;
        return this;
    }

    public PoiDetailInfo setDetailOpeningHoursList(DetailOpeningHours... detailOpeningHoursArr) {
        return setDetailOpeningHoursList(Arrays.asList(detailOpeningHoursArr));
    }

    public List<String> getPhoneNumber() {
        return this.phoneNumber;
    }

    public PoiDetailInfo setPhoneNumber(List<String> list) {
        if (list == null) {
            throw new NullPointerException("phoneNumber cannot be null");
        }
        this.phoneNumber = list;
        return this;
    }

    public PoiDetailInfo setPhoneNumber(String... strArr) {
        return setPhoneNumber(Arrays.asList(strArr));
    }

    public String getAddress() {
        return this.address;
    }

    public PoiDetailInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PoiDetailInfo poiDetailInfo = (PoiDetailInfo) obj;
        return Objects.equals(this.score, poiDetailInfo.score) && Objects.equals(this.chargingPriceInfoList, poiDetailInfo.chargingPriceInfoList) && Objects.equals(this.priceOpeningHoursList, poiDetailInfo.priceOpeningHoursList) && Objects.equals(this.offStreetParkingPriceList, poiDetailInfo.offStreetParkingPriceList) && Objects.equals(this.detailOpeningHoursList, poiDetailInfo.detailOpeningHoursList) && Objects.equals(this.phoneNumber, poiDetailInfo.phoneNumber) && Objects.equals(this.address, poiDetailInfo.address);
    }

    public int hashCode() {
        return Objects.hash(this.score, this.chargingPriceInfoList, this.priceOpeningHoursList, this.offStreetParkingPriceList, this.detailOpeningHoursList, this.phoneNumber, this.address);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
