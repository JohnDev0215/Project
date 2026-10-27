package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CountryInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909922;

    @SerializedName("Country_ISO3166_1_alpha3")
    private String country_ISO3166_1_alpha3 = "";

    public CountryInfo() {
    }

    public CountryInfo(String str) {
        setCountry_ISO3166_1_alpha3(str);
    }

    public String getCountry_ISO3166_1_alpha3() {
        return this.country_ISO3166_1_alpha3;
    }

    public CountryInfo setCountry_ISO3166_1_alpha3(String str) {
        if (str == null) {
            throw new NullPointerException("country_ISO3166_1_alpha3 cannot be null");
        }
        this.country_ISO3166_1_alpha3 = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.country_ISO3166_1_alpha3, ((CountryInfo) obj).country_ISO3166_1_alpha3);
    }

    public int hashCode() {
        return Objects.hash(this.country_ISO3166_1_alpha3);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
