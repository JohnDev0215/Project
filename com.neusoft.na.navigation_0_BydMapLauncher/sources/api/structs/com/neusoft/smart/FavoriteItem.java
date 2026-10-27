package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FavoriteItem implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990497;

    @SerializedName("FavoriteType")
    private FavoriteType favoriteType = FavoriteType.GENERAL_FAVORITES;

    @SerializedName("PoiInfo")
    private PoiInfo poiInfo = new PoiInfo();

    @SerializedName("FavoriteId")
    private String favoriteId = "";

    @SerializedName("PhoneNumbers")
    private List<String> phoneNumbers = new ArrayList();

    @SerializedName("URL")
    private String uRL = "";

    @SerializedName("E_Mail")
    private String e_Mail = "";

    @SerializedName("CategoryInfos")
    private List<CategoryInfo> categoryInfos = new ArrayList();

    @SerializedName("NameInfos")
    private List<NameInfo> nameInfos = new ArrayList();

    @SerializedName("PoiDataSource")
    private String poiDataSource = "";

    @SerializedName("AccessibleEntries")
    private List<GeoPosition> accessibleEntries = new ArrayList();

    @SerializedName("LocationId")
    private List<Integer> locationId = new ArrayList();

    @SerializedName("ModifiedName")
    private String modifiedName = "";

    @SerializedName("DataProvider")
    private Integer dataProvider = 0;

    public FavoriteItem() {
    }

    public FavoriteItem(FavoriteType favoriteType, PoiInfo poiInfo, String str, List<String> list, String str2, String str3, List<CategoryInfo> list2, List<NameInfo> list3, String str4, List<GeoPosition> list4, List<Integer> list5, String str5, Integer num) {
        setFavoriteType(favoriteType);
        setPoiInfo(poiInfo);
        setFavoriteId(str);
        setPhoneNumbers(list);
        setURL(str2);
        setE_Mail(str3);
        setCategoryInfos(list2);
        setNameInfos(list3);
        setPoiDataSource(str4);
        setAccessibleEntries(list4);
        setLocationId(list5);
        setModifiedName(str5);
        setDataProvider(num);
    }

    public FavoriteType getFavoriteType() {
        return this.favoriteType;
    }

    public FavoriteItem setFavoriteType(FavoriteType favoriteType) {
        this.favoriteType = favoriteType;
        return this;
    }

    public PoiInfo getPoiInfo() {
        return this.poiInfo;
    }

    public FavoriteItem setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
        return this;
    }

    public String getFavoriteId() {
        return this.favoriteId;
    }

    public FavoriteItem setFavoriteId(String str) {
        if (str == null) {
            throw new NullPointerException("favoriteId cannot be null");
        }
        this.favoriteId = str;
        return this;
    }

    public List<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public FavoriteItem setPhoneNumbers(List<String> list) {
        if (list == null) {
            throw new NullPointerException("phoneNumbers cannot be null");
        }
        this.phoneNumbers = list;
        return this;
    }

    public FavoriteItem setPhoneNumbers(String... strArr) {
        return setPhoneNumbers(Arrays.asList(strArr));
    }

    public String getURL() {
        return this.uRL;
    }

    public FavoriteItem setURL(String str) {
        if (str == null) {
            throw new NullPointerException("uRL cannot be null");
        }
        this.uRL = str;
        return this;
    }

    public String getE_Mail() {
        return this.e_Mail;
    }

    public FavoriteItem setE_Mail(String str) {
        if (str == null) {
            throw new NullPointerException("e_Mail cannot be null");
        }
        this.e_Mail = str;
        return this;
    }

    public List<CategoryInfo> getCategoryInfos() {
        return this.categoryInfos;
    }

    public FavoriteItem setCategoryInfos(List<CategoryInfo> list) {
        if (list == null) {
            throw new NullPointerException("categoryInfos cannot be null");
        }
        this.categoryInfos = list;
        return this;
    }

    public FavoriteItem setCategoryInfos(CategoryInfo... categoryInfoArr) {
        return setCategoryInfos(Arrays.asList(categoryInfoArr));
    }

    public List<NameInfo> getNameInfos() {
        return this.nameInfos;
    }

    public FavoriteItem setNameInfos(List<NameInfo> list) {
        if (list == null) {
            throw new NullPointerException("nameInfos cannot be null");
        }
        this.nameInfos = list;
        return this;
    }

    public FavoriteItem setNameInfos(NameInfo... nameInfoArr) {
        return setNameInfos(Arrays.asList(nameInfoArr));
    }

    public String getPoiDataSource() {
        return this.poiDataSource;
    }

    public FavoriteItem setPoiDataSource(String str) {
        if (str == null) {
            throw new NullPointerException("poiDataSource cannot be null");
        }
        this.poiDataSource = str;
        return this;
    }

    public List<GeoPosition> getAccessibleEntries() {
        return this.accessibleEntries;
    }

    public FavoriteItem setAccessibleEntries(List<GeoPosition> list) {
        if (list == null) {
            throw new NullPointerException("accessibleEntries cannot be null");
        }
        this.accessibleEntries = list;
        return this;
    }

    public FavoriteItem setAccessibleEntries(GeoPosition... geoPositionArr) {
        return setAccessibleEntries(Arrays.asList(geoPositionArr));
    }

    public List<Integer> getLocationId() {
        return this.locationId;
    }

    public FavoriteItem setLocationId(List<Integer> list) {
        if (list == null) {
            throw new NullPointerException("locationId cannot be null");
        }
        this.locationId = list;
        return this;
    }

    public FavoriteItem setLocationId(Integer... numArr) {
        return setLocationId(Arrays.asList(numArr));
    }

    public String getModifiedName() {
        return this.modifiedName;
    }

    public FavoriteItem setModifiedName(String str) {
        if (str == null) {
            throw new NullPointerException("modifiedName cannot be null");
        }
        this.modifiedName = str;
        return this;
    }

    public Integer getDataProvider() {
        return this.dataProvider;
    }

    public FavoriteItem setDataProvider(Integer num) {
        if (num == null) {
            throw new NullPointerException("dataProvider cannot be null");
        }
        this.dataProvider = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FavoriteItem favoriteItem = (FavoriteItem) obj;
        return Objects.equals(this.favoriteType, favoriteItem.favoriteType) && Objects.equals(this.poiInfo, favoriteItem.poiInfo) && Objects.equals(this.favoriteId, favoriteItem.favoriteId) && Objects.equals(this.phoneNumbers, favoriteItem.phoneNumbers) && Objects.equals(this.uRL, favoriteItem.uRL) && Objects.equals(this.e_Mail, favoriteItem.e_Mail) && Objects.equals(this.categoryInfos, favoriteItem.categoryInfos) && Objects.equals(this.nameInfos, favoriteItem.nameInfos) && Objects.equals(this.poiDataSource, favoriteItem.poiDataSource) && Objects.equals(this.accessibleEntries, favoriteItem.accessibleEntries) && Objects.equals(this.locationId, favoriteItem.locationId) && Objects.equals(this.modifiedName, favoriteItem.modifiedName) && Objects.equals(this.dataProvider, favoriteItem.dataProvider);
    }

    public int hashCode() {
        return Objects.hash(this.favoriteType, this.poiInfo, this.favoriteId, this.phoneNumbers, this.uRL, this.e_Mail, this.categoryInfos, this.nameInfos, this.poiDataSource, this.accessibleEntries, this.locationId, this.modifiedName, this.dataProvider);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
