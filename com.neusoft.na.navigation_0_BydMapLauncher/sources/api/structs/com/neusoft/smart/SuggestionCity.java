package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SuggestionCity implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991300;

    @SerializedName("SuggestionNum")
    private Integer suggestionNum = 0;

    @SerializedName("CityName")
    private String cityName = "";

    @SerializedName("CityCode")
    private String cityCode = "";

    public SuggestionCity() {
    }

    public SuggestionCity(Integer num, String str, String str2) {
        setSuggestionNum(num);
        setCityName(str);
        setCityCode(str2);
    }

    public Integer getSuggestionNum() {
        return this.suggestionNum;
    }

    public SuggestionCity setSuggestionNum(Integer num) {
        if (num == null) {
            throw new NullPointerException("suggestionNum cannot be null");
        }
        this.suggestionNum = num;
        return this;
    }

    public String getCityName() {
        return this.cityName;
    }

    public SuggestionCity setCityName(String str) {
        if (str == null) {
            throw new NullPointerException("cityName cannot be null");
        }
        this.cityName = str;
        return this;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public SuggestionCity setCityCode(String str) {
        if (str == null) {
            throw new NullPointerException("cityCode cannot be null");
        }
        this.cityCode = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SuggestionCity suggestionCity = (SuggestionCity) obj;
        return Objects.equals(this.suggestionNum, suggestionCity.suggestionNum) && Objects.equals(this.cityName, suggestionCity.cityName) && Objects.equals(this.cityCode, suggestionCity.cityCode);
    }

    public int hashCode() {
        return Objects.hash(this.suggestionNum, this.cityName, this.cityCode);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
